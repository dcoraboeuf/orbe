/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.ext.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.Strings;
import net.sf.doolin.util.Version;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.Context;
import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.plugin.AbstractApplicationManagerPlugin;

/**
 * Plugin that configures a database at startup. It is able to managed automatic
 * updates.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PluginDB.java,v 1.3 2007/08/15 09:05:34 guinnessman Exp $
 */
public class PluginDB extends AbstractApplicationManagerPlugin {

	/**
	 * Attribute for scripts to execute before creating / updating the DB
	 */
	public static final String APPLICATION_DBUPDATE_PRE = "DBUpdate.Pre";

	/**
	 * Attribute for scripts to execute after creating / updating the DB
	 */
	public static final String APPLICATION_DBUPDATE_POST = "DBUpdate.Post";

	/**
	 * 
	 */
	private static final String ANY = "Any";

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(PluginDB.class);

	/**
	 * Bundles
	 */
	static {
		Strings.add("net.sf.doolin.gui.ext.db.DBUpdate");
	}

	/**
	 * Resource containing the initial SQL script.
	 */
	private String resourceInitialization;

	/**
	 * Pattern for the resource containing the update
	 */
	private String resourceUpdate;

	/**
	 * Properties containing the version definitions.
	 */
	private String resourceVersions;

	/**
	 * Version to setup
	 */
	private String version;

	/**
	 * Table containing the version information
	 */
	private String versionTable;

	/**
	 * Column containing the version name
	 */
	private String versionColumnName;

	/**
	 * Column containing the version timestamp
	 */
	private String versionColumnTimestamp;

	/**
	 * JDBC driver class name
	 */
	private String jdbcDriver;

	/**
	 * JDBC URL
	 */
	private String jdbcURL;

	/**
	 * JDBC user name
	 */
	private String jdbcUser;

	/**
	 * JDBC password
	 */
	private String jdbcPassword;

	/**
	 * SQL code to execute at shutdown.
	 */
	private String sqlAtShutdown;

	@Override
	protected void init(ApplicationManager application) {
		log.info("Initialization of the DB for version " + version);
		if (preInit()) {
			if (init()) {
				postInit();
			}
		}
	}

	/**
	 * Pre-initialisation
	 * 
	 * @return Result of the pre-initialisation
	 */
	protected boolean preInit() {
		return true;
	}

	/**
	 * Post-initialisation
	 */
	protected void postInit() {
	}

	/**
	 * Creates a connection.
	 * 
	 * @return Connection to be used
	 * @throws SQLException
	 *             If there is problem while initializing the connection
	 */
	protected Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
		return connection;
	}

	/**
	 * Initialisation
	 * 
	 * @return Result of the initialization
	 */
	protected boolean init() {
		log.info("Checking the DB");
		try {
			// Registers the driver
			Class.forName(jdbcDriver);
			// Get a connection
			Connection connection = getConnection();
			boolean ok;
			try {
				// Pre scripts
				executeScripts(connection, APPLICATION_DBUPDATE_PRE);
				// Get the metadata
				DatabaseMetaData metaData = connection.getMetaData();
				// Get the list of tables
				ResultSet tables = metaData.getTables(null, null, versionTable, null);
				if (tables.next()) {
					log.info("DB is already created");
					// Get the current version
					String currentVersion = getCurrentVersion(connection);
					log.info("DB current version is " + currentVersion);
					// Different version
					if (!StringUtils.equals(version, currentVersion)) {
						log.info("DB must be patched");
						applyPatches(connection, currentVersion);
						setVersion(connection);
						ok = true;
					} else {
						log.info("DB version is ok ; no change is required");
						ok = true;
					}
				} else {
					log.info("The DB must be created");
					createTables(connection);
					setVersion(connection);
					ok = true;
				}
				// Post scripts
				if (ok) {
					executeScripts(connection, APPLICATION_DBUPDATE_POST);
				}
				// End
				return ok;
			} finally {
				connection.close();
			}
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException("PluginDB.CannotGetConnection", ex);
		}
	}

	/**
	 * Creates the tables
	 * 
	 * @param connection
	 *            Connection to be used
	 */
	protected void createTables(Connection connection) {
		try {
			Statement st = connection.createStatement();
			try {
				// Reads the batch file
				String sql = readResource(resourceInitialization);
				st.execute(sql);
			} finally {
				st.close();
			}
			// Apply patch for full version
			applyPatches(connection, ANY);
		} catch (Exception ex) {
			throw new CodeException("PluginDB.CannotInitialize", ex);
		}
	}

	/**
	 * Changes the version
	 * 
	 * @param connection
	 *            The version to set.
	 * @throws SQLException
	 */
	protected void setVersion(Connection connection) throws SQLException {
		Statement st = connection.createStatement();
		try {
			// Delete all previous lines
			st.execute("DELETE FROM " + versionTable);
		} finally {
			st.close();
		}
		// Adds the current version
		PreparedStatement ps = connection.prepareStatement("INSERT INTO " + versionTable + " (" + versionColumnName + ", " + versionColumnTimestamp + ") VALUES (?, ?)");
		try {
			ps.setString(1, version);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}

	/**
	 * Applies one patch
	 * 
	 * @param connection
	 *            Connection to be used
	 * @param patch
	 *            Patch to apply
	 */
	protected void applyPatch(Connection connection, String patch) {
		log.info("Applying patch " + patch + "...");
		try {
			// Read the update
			String updatePath = MessageFormat.format(resourceUpdate, patch);
			runScript(connection, updatePath);
			// Ok
			log.info("End of patch " + patch);
		} catch (Exception ex) {
			throw new CodeException("PluginDB.CannotApplyPatch", ex, new Object[] { patch, ex.getLocalizedMessage() });
		}
	}

	/**
	 * Applies patches for the current version
	 * 
	 * @param connection
	 *            Connection to be used
	 * @param currentVersion
	 *            Version which patches must be applied from.
	 */
	protected void applyPatches(Connection connection, String currentVersion) {
		// Loads the version properties
		Properties versionProperties = net.sf.doolin.util.IOUtils.readProperties(resourceVersions);
		// Get the list of patches from the current version
		String patchList = versionProperties.getProperty(currentVersion);
		// If not specific patch is defined, read from the Any
		if (patchList == null) {
			patchList = versionProperties.getProperty(ANY);
			if (patchList == null) {
				throw new CodeException("PluginDB.CannotFindPatchList");
			} else {
				patchList = reduce(patchList, currentVersion);
			}
		}
		if (StringUtils.isBlank(patchList)) {
			log.info("No patch is needed for version " + currentVersion);
		} else {
			log.info("List of patches to apply : " + patchList);
			String[] patches = StringUtils.split(patchList, ",");
			// Applies all patches
			for (int i = 0; i < patches.length; i++) {
				String patch = patches[i];
				applyPatch(connection, patch);
			}
		}
	}

	/**
	 * Reduces the patch list from current version
	 * 
	 * @param patchList
	 *            Full list of patches
	 * @param currentVersionValue
	 *            Starting version
	 * @return Reduced list of patches
	 */
	protected String reduce(String patchList, String currentVersionValue) {
		Version currentVersion = new Version(currentVersionValue);
		String[] patches = StringUtils.split(patchList, ",");
		ArrayList<String> toApply = new ArrayList<String>();
		for (String patch : patches) {
			Version patchVersion = new Version(patch);
			if (currentVersion.compareTo(patchVersion) < 0) {
				toApply.add(patch);
			}
		}
		return StringUtils.join(toApply.toArray(), ",");
	}

	/**
	 * Get the current installed version
	 * 
	 * @param connection
	 *            Connection to use
	 * @return Current version of the databas
	 * @throws SQLException
	 *             If there is a problem while accessing the database
	 */
	protected String getCurrentVersion(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT " + versionColumnName + ", " + versionColumnTimestamp + " FROM " + versionTable);
		try {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString(1);
				Timestamp ts = rs.getTimestamp(2);
				log.info("Last installation date is " + ts);
				return name;
			} else {
				return null;
			}
		} finally {
			ps.close();
		}
	}

	/**
	 * Executes a list of scripts. The given attribute is used to fetch a
	 * context attribute whose value contains a comma-separated list of resource
	 * paths to SQL scripts.
	 * 
	 * @param connection
	 *            Connection to use
	 * @param attributeName
	 *            Attribute that contains the list of scripts
	 * @throws IOException
	 *             If an error occurs while reading the scripts
	 * @throws SQLException
	 *             If an error occurs while executing the scripts
	 * @see Application#getContext()
	 * @see Context#getAttribute(String)
	 * @see #runScript(Connection, String)
	 */
	protected void executeScripts(Connection connection, String attributeName) throws IOException, SQLException {
		// Executes some arbitrary scripts
		String scripts = (String) Application.getContext().getAttribute(attributeName);
		if (StringUtils.isNotBlank(scripts)) {
			String[] listScripts = StringUtils.split(scripts, ";");
			for (String script : listScripts) {
				log.info("Executing script " + script);
				runScript(connection, script);
			}
		}
	}

	/**
	 * Runs one script which is found using a resource path
	 * 
	 * @param connection
	 *            Connection to use
	 * @param scriptPath
	 *            Resource path to the script
	 * @throws IOException
	 *             If an error occurs while reading the script
	 * @throws SQLException
	 *             If an error occurs while executing the script
	 * @see #readResource(String)
	 */
	protected void runScript(Connection connection, String scriptPath) throws IOException, SQLException {
		String sql = readResource(scriptPath);
		// Applies the update
		Statement st = connection.createStatement();
		try {
			st.execute(sql);
		} finally {
			st.close();
		}
	}

	/**
	 * Reads resource as a string
	 * 
	 * @param path
	 *            Resource path
	 * @return Resource content as a string
	 * @throws IOException
	 *             If there is problem while reading the resource content
	 */
	protected String readResource(String path) throws IOException {
		InputStream in = getClass().getResourceAsStream(path);
		if (in == null) {
			throw new CodeException("PluginDB.CannotGetResource", path);
		} else {
			try {
				String text = IOUtils.toString(in);
				return text;
			} finally {
				in.close();
			}
		}
	}

	@Override
	protected void shutdown(ApplicationManager application) {
		if (StringUtils.isNotBlank(sqlAtShutdown)) {
			log.info("Shutdown the DB");
			try {
				// Get the connection
				Connection connection = getConnection();
				try {
					// Shutdown
					Statement st = connection.createStatement();
					st.execute(sqlAtShutdown);
				} finally {
					connection.close();
				}
			} catch (CodeException ex) {
				throw ex;
			} catch (Exception ex) {
				throw new CodeException("PluginDB.CannotExecuteSQLAtShutdown", ex);
			}
		}
	}

	/**
	 * @return JDBC driver to use
	 */
	public String getJdbcDriver() {
		return jdbcDriver;
	}

	/**
	 * @param jdbcDriver
	 *            JDBC driver to use
	 */
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	/**
	 * @return Password for the DB user
	 */
	public String getJdbcPassword() {
		return jdbcPassword;
	}

	/**
	 * @param jdbcPassword
	 *            Password for the DB user
	 */
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	/**
	 * @return JDBC URL to the database
	 */
	public String getJdbcURL() {
		return jdbcURL;
	}

	/**
	 * @param jdbcURL
	 *            JDBC URL to the database
	 */
	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

	/**
	 * @return Database user
	 */
	public String getJdbcUser() {
		return jdbcUser;
	}

	/**
	 * @param jdbcUser
	 *            Database user
	 */
	public void setJdbcUser(String jdbcUser) {
		this.jdbcUser = jdbcUser;
	}

	/**
	 * @return Resource path to the initialization script
	 */
	public String getResourceInitialization() {
		return resourceInitialization;
	}

	/**
	 * @param resourceInitialization
	 *            Resource path to the initialization script
	 */
	public void setResourceInitialization(String resourceInitialization) {
		this.resourceInitialization = resourceInitialization;
	}

	/**
	 * @return Resource path to the update script (it contains a {0} token for
	 *         the version placeholder)
	 */
	public String getResourceUpdate() {
		return resourceUpdate;
	}

	/**
	 * @param resourceUpdate
	 *            Resource path to the update script (it contains a {0} token
	 *            for the version placeholder)
	 */
	public void setResourceUpdate(String resourceUpdate) {
		this.resourceUpdate = resourceUpdate;
	}

	/**
	 * @return Resource path to the version definitions property file
	 */
	public String getResourceVersions() {
		return resourceVersions;
	}

	/**
	 * @param resourceVersions
	 *            Resource path to the version definitions property file
	 */
	public void setResourceVersions(String resourceVersions) {
		this.resourceVersions = resourceVersions;
	}

	/**
	 * @return SQL instruction to execute at shutdown
	 */
	public String getSqlAtShutdown() {
		return sqlAtShutdown;
	}

	/**
	 * @param sqlAtShutdown
	 *            SQL instruction to execute at shutdown
	 */
	public void setSqlAtShutdown(String sqlAtShutdown) {
		this.sqlAtShutdown = sqlAtShutdown;
	}

	/**
	 * @return Target version of the database
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            Target version of the database
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return Name of the column that contains the version
	 */
	public String getVersionColumnName() {
		return versionColumnName;
	}

	/**
	 * @param versionColumnName
	 *            Name of the column that contains the version
	 */
	public void setVersionColumnName(String versionColumnName) {
		this.versionColumnName = versionColumnName;
	}

	/**
	 * @return Name of the column that contains the timestamp
	 */
	public String getVersionColumnTimestamp() {
		return versionColumnTimestamp;
	}

	/**
	 * @param versionColumnTimestamp
	 *            Name of the column that contains the timestamp
	 */
	public void setVersionColumnTimestamp(String versionColumnTimestamp) {
		this.versionColumnTimestamp = versionColumnTimestamp;
	}

	/**
	 * @return Name of the table that contains the version information
	 */
	public String getVersionTable() {
		return versionTable;
	}

	/**
	 * @param versionTable
	 *            Name of the table that contains the version information
	 */
	public void setVersionTable(String versionTable) {
		this.versionTable = versionTable;
	}

}
