/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.core.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.MessageCodes;
import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.icon.IconManager;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.DesktopService;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.swing.SwingUtils;

/**
 * Standard action for displaying an "About..." dialog box.
 * 
 * The displayed dialog's title is set according to the key defined by the
 * <code>title</code> property.
 * 
 * The displayed dialog's text is set according to the key defined by the
 * <code>text</code> property. If <code>html</code> is set to
 * <code>true</code>, the text is rendered as HTML and any link is made
 * active.
 * 
 * An image is put in the dialog is <code>logo</code> is not <code>null</code>.
 * The image is looked using the <code>{@link IconManager}</code> and the
 * requested size is <code>{@link IconSize#BIG}</code>.
 * 
 * The position of the logo relatively to the text is set using the
 * <code>layout</code> property.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionAbout.java,v 1.2 2007/08/15 09:05:30 guinnessman Exp $
 */
public class ActionAbout extends AbstractAction {

	/**
	 * Positions the image at the top of the dialog
	 * 
	 * @see #setLayout(String)
	 */
	public final static String LAYOUT_TOP = "top";

	/**
	 * Positions the image at the left of the dialog
	 * 
	 * @see #setLayout(String)
	 */
	public final static String LAYOUT_LEFT = "left";

	private String title;

	private String text;

	private String logo;

	private String layout = LAYOUT_LEFT;

	private boolean html;

	/**
	 * Displayed dialog
	 */
	protected JDialog dialog;

	@Override
	protected void process() {
		// Title
		String dialogTitle = GUIUtils.evaluate(title, null, null);
		// Text
		String dialogText = GUIUtils.evaluate(text, null, null);
		// Text panel
		JPanel panelText = new JPanel(new BorderLayout());
		panelText.setBackground(Color.WHITE);
		panelText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelText.add(createText(dialogText), BorderLayout.CENTER);
		// Ok button
		JPanel panelOk = new JPanel(new FlowLayout(4, 4, FlowLayout.TRAILING));
		panelOk.setBackground(Color.WHITE);
		JButton buttonOk = new JButton(GUIStrings.get(MessageCodes.BUTTON_OK));
		panelOk.add(buttonOk);
		// Content & buttons
		JPanel panelContent = new JPanel(new BorderLayout());
		panelContent.add(panelText, BorderLayout.CENTER);
		panelContent.add(panelOk, BorderLayout.PAGE_END);
		// Main panel
		JPanel panelMain = new JPanel(new BorderLayout());
		panelMain.setBackground(Color.WHITE);
		panelMain.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelMain.add(panelContent, BorderLayout.CENTER);
		// Logo image
		if (StringUtils.isNotBlank(logo)) {
			Icon logoImage = GUIUtils.getIcon(logo, IconSize.BIG);
			Object constraint;
			if (LAYOUT_LEFT.equals(layout)) {
				constraint = BorderLayout.LINE_START;
			} else {
				constraint = BorderLayout.PAGE_START;
			}
			JLabel labelLogo = new JLabel(logoImage);
			labelLogo.setOpaque(false);
			labelLogo.setVerticalAlignment(SwingConstants.TOP);
			panelMain.add(labelLogo, constraint);
		}
		// Dialog
		dialog = SwingUtils.createWindow(JDialog.class, getView().getComponent());
		dialog.setModal(true);
		dialog.setUndecorated(true);
		dialog.setTitle(dialogTitle);
		dialog.setContentPane(panelMain);
		dialog.pack();
		dialog.setLocationRelativeTo(getView().getComponent());
		// Ok button
		dialog.getRootPane().setDefaultButton(buttonOk);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		// Displays
		dialog.setVisible(true);
	}

	/**
	 * Creates the text component
	 * 
	 * @param dialogText
	 *            Text to display
	 * @return Component to be displayed for the text
	 */
	private Component createText(String dialogText) {
		if (html) {
			return createHTML(dialogText);
		} else {
			return new JLabel(dialogText);
		}
	}

	/**
	 * Creates an HTML container for the text
	 * 
	 * @param dialogText
	 *            Text to display
	 * @return Component to be displayed for the text
	 */
	protected Component createHTML(String dialogText) {
		HTMLEditorKit editorKit = new HTMLEditorKit();
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setEditorKit(editorKit);
		// Listens for the links
		editorPane.addHyperlinkListener(new HyperlinkListener() {

			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					URL url = e.getURL();
					GUIUtils.getService(DesktopService.class).browse(url);
				}
			}

		});
		// HTML content
		try {
			// Get a reader on the HTML
			BufferedReader reader = new BufferedReader(new StringReader(dialogText));
			try {
				// Creates the document
				HTMLDocument document = (HTMLDocument) editorKit.createDefaultDocument();
				// Reads the document
				editorKit.read(reader, document, 0);
				// Ok
				editorPane.setDocument(document);
			} finally {
				reader.close();
			}
		} catch (Exception ex) {
			throw new RuntimeException("Cannot parse About text as HTML", ex);
		}
		// Ok
		return editorPane;
	}

	/**
	 * Returns the <code>html</code> property.
	 * 
	 * @return <code>html</code> property.
	 */
	public boolean isHtml() {
		return html;
	}

	/**
	 * Sets the <code>html</code> property.
	 * 
	 * @param html
	 *            <code>html</code> property.
	 */
	@Configurable(mandatory = false, description = "Defines if the text is formatted as HTML", defaultsTo = "false")
	public void setHtml(boolean html) {
		this.html = html;
	}

	/**
	 * Returns the <code>layout</code> property.
	 * 
	 * @return <code>layout</code> property.
	 */
	public String getLayout() {
		return layout;
	}

	/**
	 * Sets the <code>layout</code> property.
	 * 
	 * @param layout
	 *            <code>layout</code> property.
	 */
	@Configurable(mandatory = false, description = "Defines the position of the image along the text", defaultsTo = "LEFT")
	public void setLayout(String layout) {
		this.layout = layout;
	}

	/**
	 * Returns the <code>logo</code> property.
	 * 
	 * @return <code>logo</code> property.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Sets the <code>logo</code> property.
	 * 
	 * @param logo
	 *            <code>logo</code> property.
	 */
	@Configurable(mandatory = false, description = "Icon ID of the logo")
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * Returns the <code>text</code> property.
	 * 
	 * @return <code>text</code> property.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the <code>text</code> property.
	 * 
	 * @param text
	 *            <code>text</code> property.
	 */
	@Configurable(mandatory = true, description = "Text to be displayed in the dialog")
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the <code>title</code> property.
	 * 
	 * @return <code>title</code> property.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the <code>title</code> property.
	 * 
	 * @param title
	 *            <code>title</code> property.
	 */
	@Configurable(mandatory = true, description = "Dialog title")
	public void setTitle(String title) {
		this.title = title;
	}

}
