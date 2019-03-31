/*
 * Created on Nov 10, 2006
 */
package orbe.gui.jmx;

import net.sf.doolin.bus.Bus;

/**
 * Implémentation pour le management JMX.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeManager.java,v 1.2 2006/11/22 11:35:03 guinnessman Exp $
 */
public class OrbeManager implements OrbeManagerMBean {
	
	/**
	 * Unique instance
	 */
	private static OrbeManager instance = null;

	/**
	 * Get the instance
	 */
	public static OrbeManager getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized OrbeManager createInstance() {
		if (instance != null) {
			return instance;
		} else {
			OrbeManager temp = new OrbeManager();
			instance = temp;
			return instance;
		}
	}
	
	/**
	 * Variables
	 */

	private boolean layeredOrbeRendererDebug = false;
	private boolean tiledOrbeViewDebug = false;
	private boolean layerTextDebug = false;

	/**
	 * Initialization
	 */
	private OrbeManager() {
		
	}

	public boolean getLayeredOrbeRendererDebug() {
		return layeredOrbeRendererDebug;
	}

	public boolean getTiledOrbeViewDebug() {
		return tiledOrbeViewDebug;
	}

	public void setLayeredOrbeRendererDebug(boolean debug) {
		layeredOrbeRendererDebug = debug;
		refresh();
	}

	public void setTiledOrbeViewDebug(boolean debug) {
		tiledOrbeViewDebug = debug;
		refresh();
	}

	public void refresh() {
		Bus.get().publish(new OrbeMessageMgtRefresh());
	}

	/**
	 * @return the layerTextDebug
	 */
	public boolean isLayerTextDebug() {
		return layerTextDebug;
	}

	/**
	 * @param layerTextDebug the layerTextDebug to set
	 */
	public void setLayerTextDebug(boolean layerTextDebug) {
		this.layerTextDebug = layerTextDebug;
		refresh();
	}

}
