/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map.tool;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.CursorFactory;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.cursor.ICursors;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.options.PanelOptions;
import orbe.gui.map.options.PanelOptionsText;
import orbe.gui.map.renderer.TextRenderer;
import orbe.gui.map.tool.text.ToolTextDelegate;
import orbe.gui.map.tool.text.ToolTextDelegateMove;
import orbe.gui.map.tool.text.ToolTextDelegateRotate;
import orbe.gui.task.OrbeTask;
import orbe.gui.task.OrbeTaskText;
import orbe.gui.task.OrbeTaskTextDelete;
import orbe.gui.task.OrbeTaskTextNew;
import orbe.gui.task.OrbeTaskTextStyle;
import orbe.model.OrbeMap;
import orbe.model.element.ElementFlag;
import orbe.model.element.text.OrbeText;
import orbe.model.element.text.OrbeTextList;
import orbe.model.style.TextStyle;

import org.apache.commons.lang.StringUtils;

/**
 * Outil de création et gestion du texte.
 * 
 * Les configurations sont les suivantes : <table border="1">
 * <tr>
 * <th>Outil sur le texte</th>
 * <th>Modificateur</th>
 * <th>Mode</th>
 * <th>Curseur</th>
 * </tr>
 * <tr>
 * <td>Non</td>
 * <td>n/a</td>
 * <td>Création de texte</td>
 * <td>Texte</td>
 * </tr>
 * <tr>
 * <td rowspan="3">Oui</td>
 * <td>Aucun</td>
 * <td>
 * <ul>
 * <li>Edition de texte sur simple clic
 * <li>Edition du style sur clic droit
 * </ul>
 * </td>
 * <td>Texte (édition)</td>
 * </tr>
 * <tr>
 * <td>Shift</td>
 * <td>Déplacement du texte</td>
 * <td>Déplacement</td>
 * </tr>
 * <tr>
 * <td>Ctrl</td>
 * <td>Rotation du texte</td>
 * <td>Rotation</td>
 * </tr>
 * </table>
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolText.java,v 1.17 2006/11/24 16:50:54 guinnessman Exp $
 */
public class ToolText extends AbstractToolMap {

	/**
	 * Current edited text
	 */
	private OrbeText editedText;

	/**
	 * Edition field
	 */
	private JTextField editionField;

	/**
	 * Current delegate
	 */
	private ToolTextDelegate toolTextDelegate;

	private Cursor cursorText;

	private Cursor cursorMove;

	private Cursor cursorEdit;

	private Cursor cursorRotate;

	public ToolText() {
		CursorFactory cursorFactory = GUIUtils.getService(CursorFactory.class);
		cursorText = cursorFactory.getCursor(ICursors.TEXT);
		cursorEdit = cursorFactory.getCursor(ICursors.TEXT_EDIT);
		cursorRotate = cursorFactory.getCursor(ICursors.ROTATE);
		cursorMove = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
	}

	/**
	 * @see ITool#TEXT
	 * @see orbe.gui.map.tool.Tool#getId()
	 */
	public String getId() {
		return ITool.TEXT;
	}

	/**
	 * Retrieves a text that is under the mouse cursor.
	 * 
	 * @param p
	 *            Position of the mouse
	 * @return Text under the mouse or <code>null</code> if there isn't.
	 */
	public OrbeText getText(Point p) {
		// Graphics environment
		Graphics2D g = getControler().getGraphics2D();
		// Get the map
		OrbeMap map = getMap();
		// Get the view settings
		ViewSettings viewSettings = getViewSettings();
		// Get the list of texts
		OrbeTextList list = map.getTextList();
		// Loops in reverse order (in order to retrieve the top-most elements)
		List<OrbeText> elements = list.getElements();
		int count = elements.size();
		for (int i = count - 1; i >= 0; i--) {
			OrbeText text = elements.get(i);
			// Get zone of the text
			Shape zone = TextRenderer.getTextScreenZone(text, map, viewSettings, g);
			// If intersection, we get the text
			if (zone.contains(p)) {
				return text;
			}
		}
		// Nothing has been found
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Only simple clics are taken into account
		if (e.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		// Get any text selected
		OrbeText text = getText(e.getPoint());
		// If no text, this is an edition
		if (text == null) {
			// If a text is currently edited, validate it
			if (editedText != null) {
				ValidationResult result = editValidate();
				// Si la validation n'a détecté aucun changement, on passe
				// aussitôt en mode création
				if (result == ValidationResult.VALIDATED_NOCHANGE) {
					create(e);
				}
			} else {
				create(e);
			}
		}
		// If there is a text, all depends on the modifier
		else {
			// No modifiers => Edit
			if (!e.isShiftDown() && !e.isControlDown()) {
				// If a text is currently edited, validate it
				if (editedText != null) {
					ValidationResult result = editValidate();
					if (result != ValidationResult.FAILURE) {
						// Enters in edition
						edit(text);
					}
				}
				// Enters directly in edition
				else {
					edit(text);
				}
			}
		}
	}

	/**
	 * Creates a text.
	 * 
	 * @param e
	 *            Trigger event
	 */
	protected void create(MouseEvent e) {
		// Creates a default text element
		OrbeText text = new OrbeText();
		// Default style
		text.setStyle(getControler().getToolSettings().getTextStyle());
		// No text
		text.setValue("");
		// Position depends on the mouse event
		text.setPosition(getHX(e.getPoint()));
		// Flags as new for edition
		text.setFlag(ElementFlag.FLAG_NEW, true);
		// Edits the text
		edit(text);
	}

	/**
	 * Enters in edition.
	 * 
	 * @param text
	 *            Text which is edited.
	 */
	protected void edit(OrbeText text) {
		// New edited text
		editedText = text;
		// Graphics environment
		final Graphics2D g = getControler().getGraphics2D();
		// Flags as edited
		text.setFlag(ElementFlag.FLAG_EDITED, true);
		// Refreshes the corresponding zone (this deletes the text)
		Rectangle zone = TextRenderer.getRefreshingScreenZone(editedText, getMap(), getViewSettings(), g);
		getControler().refresh(zone);
		// Creates a text field for the edition
		editionField = new JTextField(15);
		// Font
		editionField.setFont(TextRenderer.getFont(editedText, getMap(), getViewSettings()));
		// Configure the field
		editionField.setForeground(editedText.getStyle().getFontColor());
		editionField.setBackground(SystemColor.window);
		editionField.setBorder(null);
		// Get the position of the field
		TextRenderer.setPosition(editionField, editedText, getMap(), getViewSettings(), g);
		// Content
		editionField.setText(editedText.getValue());

		// Ajustement de la taille en fonction du nombre de caractères
		editionField.setDocument(new PlainDocument() {

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				// Défaut
				super.insertString(offs, str, a);
				// Nouvelle zone de texte
				String oldValue = editedText.getValue();
				try {
					editedText.setValue(editionField.getText());
					TextRenderer.setPosition(editionField, editedText, getMap(), getViewSettings(), g);
					// Refreshes the container
					getControler().setEditionComponent(editionField);
				} finally {
					editedText.setValue(oldValue);
				}
			}

		});

		// Content
		editionField.setText(editedText.getValue());

		// Tracks ESCAPE & ENTER key
		editionField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\n' || e.getKeyChar() == '\r') {
					editValidate();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					editCancel();
				}
			}

		});

		// Adds the field to the panel
		getControler().setEditionComponent(editionField);
	}

	/**
	 * Annulation de l'édition
	 */
	protected void editCancel() {
		if (editedText != null && editionField != null) {
			// Removes edition status
			editClear();
		} else {
			throw new IllegalStateException("No text is edited.");
		}
	}

	/**
	 * Validation de l'édition.
	 * 
	 * @return Résultat de la validation.
	 */
	protected ValidationResult editValidate() {
		if (editedText != null && editionField != null) {
			// Controls the text
			String value = editionField.getText();
			if (StringUtils.isBlank(value)) {
				// For a new text field, it means that creation is just
				// cancelled
				if (editedText.getFlag(ElementFlag.FLAG_NEW)) {
					// Just clear edition
					editClear();
					return ValidationResult.VALIDATED_NOCHANGE;
				}
				// For an existing text, it is not valid
				else {
					GUIUtils.getAlertManager().message(getGUIFView(), "ToolText.Edition.NoText");
					return ValidationResult.FAILURE;
				}
			}
			value = StringUtils.trim(value);
			// Contrôle du texte
			/*
			 * Si le texte n'a pas changé, aucune édition n'est nécessaire
			 */
			String oldValue = editedText.getValue();
			String newValue = editionField.getText();
			if (StringUtils.equals(oldValue, newValue)) {
				editClear();
				return ValidationResult.VALIDATED_NOCHANGE;
			}
			// Edition task
			OrbeTask task;
			boolean isNew = editedText.getFlag(ElementFlag.FLAG_NEW);
			if (isNew) {
				task = new OrbeTaskTextNew(getControler(), editedText, value);
			} else {
				task = new OrbeTaskText(getControler(), editedText, value);
			}
			task.process();
			getContext().addEdit(task);
			// Removes edition status
			editClear();
			// Ok
			return ValidationResult.VALIDATED;
		} else {
			throw new IllegalStateException("No text is edited.");
		}
	}

	/**
	 * Clears any edition
	 */
	protected void editClear() {
		if (editedText != null && getContext() != null) {
			// Graphics environment
			Graphics2D g = getControler().getGraphics2D();
			// Flags as no longer edited
			editedText.setFlag(ElementFlag.FLAG_EDITED, false);
			// Refreshes the corresponding zone (this deletes the text)
			Rectangle zone = TextRenderer.getRefreshingScreenZone(editedText, getMap(), getViewSettings(), g);
			getControler().refresh(zone);
		}
		editedText = null;
		editionField = null;
		getControler().setEditionComponent(null);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		OrbeText text = getText(p);
		if (text == null) {
			setCursor(cursorText);
		} else if (e.isShiftDown()) {
			setCursor(cursorMove);
		} else if (e.isControlDown()) {
			setCursor(cursorRotate);
		} else {
			setCursor(cursorEdit);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (toolTextDelegate != null) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				toolTextDelegate.cancel();
				toolTextDelegate = null;
			}
		} else {
			super.keyPressed(e);
			Point p = getControler().getComponent().getMousePosition();
			OrbeText text = getText(p);
			if (text != null) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					setCursor(cursorRotate);
				} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					setCursor(cursorMove);
				} else {
					setCursor(cursorEdit);
				}
			} else {
				setCursor(cursorText);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		setCursor(cursorText);
	}

	/**
	 * Gestion des outils de déplacement et de rotation.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Shift => Move
		if (e.isShiftDown()) {
			OrbeText text = getText(e.getPoint());
			if (text != null) {
				toolTextDelegate = new ToolTextDelegateMove(this, text);
				toolTextDelegate.start(e);
			}
		}
		// Control => Rotate
		else if (e.isControlDown()) {
			OrbeText text = getText(e.getPoint());
			if (text != null) {
				toolTextDelegate = new ToolTextDelegateRotate(this, text);
				toolTextDelegate.start(e);
			}
		}
	}

	/**
	 * Gestion des outils de déplacement et de rotation.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (toolTextDelegate != null) {
			toolTextDelegate.drag(e);
		} else {
			super.mouseDragged(e);
		}
	}

	/**
	 * Gestion du focus
	 * 
	 * @see orbe.gui.map.tool.AbstractTool#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (editionField != null) {
			editionField.requestFocusInWindow();
		} else {
			super.mouseEntered(e);
		}
	}

	/**
	 * Nettoyage de l'édition.
	 * 
	 * @see orbe.gui.map.tool.AbstractTool#unregister()
	 */
	@Override
	public void unregister() {
		if (editionField != null) {
			editCancel();
		}
		super.unregister();
	}

	/**
	 * Options
	 */
	private PanelOptionsText panelOptions;

	@Override
	public PanelOptions getPanelOptions() {
		if (panelOptions == null) {
			panelOptions = new PanelOptionsText();
		}
		return panelOptions;
	}

	/**
	 * Gestion du menu contextuel sur un texte.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (toolTextDelegate != null) {
			toolTextDelegate.end(e);
			toolTextDelegate = null;
		} else if (e.isPopupTrigger() && !e.isShiftDown() && !e.isControlDown()) {
			Point p = e.getPoint();
			OrbeText text = getText(p);
			if (text != null) {
				// Validation de tout texte existant
				if (editedText != null) {
					ValidationResult result = editValidate();
					if (result == ValidationResult.FAILURE) {
						return;
					}
				}
				// Clic droit sur un texte => affichage du menu des styles
				JPopupMenu menu = createMenuStyles(text);
				// Displays the menu
				menu.show(getControler().getComponent(), p.x, p.y);
			}
		}
	}

	/**
	 * Création d'un menu contextuel pour un texte donné.
	 * 
	 * @param text
	 *            Texte concerné.
	 * @return Menu à afficher.
	 */
	protected JPopupMenu createMenuStyles(final OrbeText text) {
		JPopupMenu menu = new JPopupMenu();
		// Suppression du texte
		JMenuItem itemDelete = new JMenuItem();
		itemDelete.setText(GUIStrings.get("ToolText.Menu.Delete", text.getValue()));
		itemDelete.setIcon(GUIUtils.getIcon("IconDelete", IconSize.SMALL));
		itemDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editDelete(text);
			}

		});
		menu.add(itemDelete);
		// Liste des styles
		menu.addSeparator();
		List<TextStyle> styles = getMap().getSettings().getTextStyles().getStyles();
		for (final TextStyle style : styles) {
			JMenuItem item = new JMenuItem();
			// Texte du menu
			item.setText(style.getName());
			// Font du menu
			Font font = style.getFont1();
			font = font.deriveFont(item.getFont().getSize2D());
			item.setFont(font);
			// Couleur
			item.setForeground(style.getFontColor());
			// Action
			item.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					editStyle(text, style);
				}

			});
			// Ok
			menu.add(item);
		}
		// Ok
		return menu;
	}

	/**
	 * Suppression d'un texte.
	 * 
	 * @param text
	 *            Texte à supprimer.
	 */
	protected void editDelete(OrbeText text) {
		if (GUIUtils.getAlertManager().confirm(getGUIFView(), "ToolText.Delete.prompt", text.getValue())) {
			OrbeTaskTextDelete task = new OrbeTaskTextDelete(getControler(), text);
			task.process();
			getContext().addEdit(task);
		}
	}

	/**
	 * Changement du style d'un texte.
	 * 
	 * @param text
	 *            Texte à changer.
	 * @param style
	 *            Style à appliquer.
	 */
	protected void editStyle(OrbeText text, TextStyle style) {
		if (style.getId() != text.getStyle().getId()) {
			OrbeTaskTextStyle task = new OrbeTaskTextStyle(getControler(), text, style);
			task.process();
			getContext().addEdit(task);
		}
	}

}
