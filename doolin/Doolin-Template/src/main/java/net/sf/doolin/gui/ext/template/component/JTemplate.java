/*
 * Created on 6 janv. 2006
 */
package net.sf.doolin.gui.ext.template.component;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;

import net.sf.doolin.gui.core.action.ActionURLStreamHandler;
import net.sf.doolin.gui.core.icon.IconURLStreamHandler;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.ext.template.Template;
import net.sf.doolin.gui.ext.template.TemplateUtils;
import net.sf.doolin.gui.service.DesktopService;
import net.sf.doolin.util.URLUtils;
import net.sf.doolin.util.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Component that displays a template.
 * 
 * @author Damien Coraboeuf
 * @version $Id: JTemplate.java,v 1.1 2007/08/15 15:13:31 guinnessman Exp $
 */
public class JTemplate extends JEditorPane {
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(JTemplate.class);

	/**
	 * Registers URL for icons.
	 */
	static {
		URLUtils.registerURLStreamHandler(IconURLStreamHandler.PROTOCOL_ICON, new IconURLStreamHandler());
		URLUtils.registerURLStreamHandler(ActionURLStreamHandler.PROTOCOL_ACTION, new ActionURLStreamHandler());
	}

	/**
	 * Format to display (MIME type)
	 */
	private String format;

	/**
	 * Path to the template
	 */
	private String path;

	/**
	 * Type of template
	 */
	private String type;

	/**
	 * Context
	 */
	private transient Map<String, Object> context = new HashMap<String, Object>();

	/**
	 * Callback for actions
	 */
	private ActionCallback actionCallback;

	private HyperlinkListener hyperlinkListener;

	/**
	 * Constructor
	 */
	public JTemplate() {
		setEditable(false);
	}

	/**
	 * @return Format to display (MIME type)
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            Format to display (MIME type)
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return Path to the template
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            Path to the template
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return Type of template
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            Type of template
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Context used to fill the template
	 */
	public Map<String, Object> getContext() {
		return context;
	}

	/**
	 * @param context
	 *            Context used to fill the template
	 */
	public void setContext(Map<String, Object> context) {
		this.context = context;
		setup();
	}

	/**
	 * Setup the template content
	 */
	public void setup() {
		// Editor kit
		EditorKit editorKit;
		if ("text/html".equals(format)) {
			editorKit = new HTMLEditorKit();
			// Listens for the links
			if (hyperlinkListener == null) {
				addHyperlinkListener(createListener());
			}
		} else if ("text/plain".equals(format)) {
			editorKit = createDefaultEditorKit();
		} else {
			log.warn("No format has beed specified, using default format");
			editorKit = createDefaultEditorKit();
		}
		setEditorKit(editorKit);
		// Creates the template
		Template template = TemplateUtils.getTemplateManager().getTemplateEngine(type).getTemplate(path);
		String text = TemplateUtils.generateString(template, context);
		// Installs the text
		Document document = editorKit.createDefaultDocument();
		try {
			BufferedReader reader = new BufferedReader(new StringReader(text));
			try {
				editorKit.read(reader, document, 0);
				setDocument(document);
			} finally {
				reader.close();
			}
		} catch (Exception ex) {
			log.error("Cannot read the template", ex);
		}
	}

	/**
	 * Creates an hyperlink listener which is suitable for the HTML page. It
	 * listens to the <code>action</code> pseudo-protocol and forwards every
	 * other link to the browser.
	 * 
	 * @return Hyperlink listener
	 * @see URLUtils#registerURLStreamHandler(String, java.net.URLStreamHandler)
	 * @see ActionURLStreamHandler
	 * @see DesktopService#browse(URL)
	 */
	protected HyperlinkListener createListener() {
		if (hyperlinkListener == null) {
			hyperlinkListener = new HyperlinkListener() {

				public void hyperlinkUpdate(HyperlinkEvent e) {
					if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
						URL url = e.getURL();
						String protocol = url.getProtocol();
						// Action protocol
						if (ActionURLStreamHandler.PROTOCOL_ACTION.equals(protocol)) {
							String actionId = url.getPath();
							Map<String, String> request = Utils.parseQuery(url);
							doAction(actionId, request);
						}
						// Default handling
						else {
							GUIUtils.getDesktopService().browse(url);
						}
					}
				}

			};
		}
		return hyperlinkListener;
	}

	/**
	 * Executes a command. By default, does nothing.
	 * 
	 * @param actionId
	 *            ID of the action to execute.
	 * @param params
	 *            Parameters for the execution context
	 */
	protected void doAction(String actionId, Map<String, String> params) {
		if (actionCallback != null) {
			actionCallback.doAction(actionId, params);
		}
	}

	/**
	 * Returns the associated action callback
	 * 
	 * @return Action callback
	 * @see #setActionCallback(ActionCallback)
	 */
	public ActionCallback getActionCallback() {
		return actionCallback;
	}

	/**
	 * Sets the associated action callback that will be called for any
	 * <code>action</code> URL in the generated template.
	 * 
	 * @param actionCallback
	 *            Action callback
	 */
	public void setActionCallback(ActionCallback actionCallback) {
		this.actionCallback = actionCallback;
	}
}
