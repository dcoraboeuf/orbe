/*
 * Created on Nov 13, 2006
 */
package orbe.gui.map;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.AbstractField;
import net.sf.doolin.gui.field.FieldValidationReport;
import net.sf.doolin.gui.form.Form;
import net.sf.doolin.gui.service.GUIStrings;

/**
 * Edition d'une liste avec visualisation du détail (maître / détail).
 * 
 * @param <E>
 *            Type d'élément géré par la liste.
 * @param <F>
 *            Type of the field value, as received by the field at
 *            initialisation.
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractFormFieldMasterDetail<E, F> extends AbstractField {

	/**
	 * Graphical list
	 */
	private JList list;

	/**
	 * Model of elements
	 */
	private ElementListModel modelList;

	private FieldValidationReport fieldValidationReport;

	private View view;

	private Form formDetail;

	private JButton buttonCreate;

	private JButton buttonSave;

	private JButton buttonDelete;

	private JButton buttonCancel;

	/**
	 * Default element used for displaying empty selection
	 */
	private E emptyElement;

	private boolean onCreate;

	/**
	 * Current selected element
	 */
	private E selectedElement;

	@Override
	protected void init() {
		setReadOnly(true);
		view = getForm().getView();

		// Overall container
		overallContainer = new JPanel(new BorderLayout());
		// Dual container
		dualContainer = new JPanel(new GridLayout(1, 2));
		overallContainer.add(dualContainer, BorderLayout.CENTER);
		// Error panel
		fieldValidationReport = new FieldValidationReport();
		fieldValidationReport.init(getForm());
		overallContainer.add(fieldValidationReport.getComponent(), BorderLayout.NORTH);
		fieldValidationReport.getComponent().setVisible(false);

		// Default element used for displaying empty selection
		emptyElement = createEmptyElement();

		// Assemblage
		list = createList();
		dualContainer.add(new JScrollPane(list));
		dualContainer.add(createPanelDetail());

		// Gestion des événements

		list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					@SuppressWarnings("unchecked")
					E element = (E) list.getSelectedValue();
					onSelect(element);
				}
			}

		});

		buttonCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onCancel();
			}

		});

		buttonDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onDelete();
			}

		});

		buttonCreate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onCreate();
			}

		});

		buttonSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onSave();
			}

		});
	}

	public JComponent getComponent() {
		return dualContainer;
	}

	/**
	 * Creates a element that is used for displaying an empty selection.
	 * 
	 * @return Element to display.
	 */
	protected abstract E createEmptyElement();

	protected void onSave() {
		if (selectedElement != null) {
			// Validation de la page de détail
			ValidationReport report = new ValidationReport();
			formDetail.validate(report);
			if (report.isOk()) {
				modelList.update(selectedElement);
				list.clearSelection();
			} else {
				view.display(report);
			}
		}
	}

	protected void onDelete() {
		if (selectedElement != null) {
			// Checks for deletion
			if (modelIsProtected(fieldValue, selectedElement)) {
				GUIUtils.getAlertManager().message(view, "FormFieldMasterDetail.Delete.default");
			}
			// Confirmation
			else if (GUIUtils.getAlertManager().confirm(view, "FormFieldMasterDetail.Delete.prompt")) {
				delete(selectedElement);
				onSelect(null);
			}
		}
	}

	/**
	 * Checks if an element can be deleted by the user.
	 * 
	 * @param e
	 *            Element to check
	 * @return <code>true</code> if the element can be deleted,
	 *         <code>false</code> otherwise.
	 */
	protected abstract boolean modelIsProtected(F field, E e);

	protected void onCreate() {
		try {
			onCreate = true;
			E e = modelCreate(fieldValue);
			modelList.add(e);
			list.setSelectedValue(e, true);
		} finally {
			onCreate = false;
		}
	}

	protected void onSelect(E e) {
		if (!onCreate) {
			clean();
		}
		if (e == null) {
			selectedElement = null;
			formDetail.setFormData(emptyElement);
			buttonCreate.setVisible(true);
			buttonSave.setVisible(false);
			buttonDelete.setVisible(false);
			buttonCancel.setVisible(false);
			formDetail.getComponent().setVisible(false);
		} else {
			selectedElement = e;
			formDetail.setFormData(selectedElement);
			buttonCreate.setVisible(false);
			buttonSave.setVisible(true);
			buttonDelete.setVisible(true);
			buttonCancel.setVisible(true);
			formDetail.getComponent().setVisible(true);
		}
	}

	private void clean() {
		List<E> model = modelList(fieldValue);
		for (E e : model) {
			if (modelIsInvalid(fieldValue, e)) {
				delete(e);
			}
		}
	}

	/**
	 * Tests if the element can be safely inserted in the list.
	 * 
	 * @param e
	 *            Element to test
	 * @return Result of test
	 */
	protected abstract boolean modelIsInvalid(F field, E e);

	private void delete(E e) {
		modelDelete(fieldValue, e);
		modelList.remove(e);
	}

	/**
	 * Deletes the element from the model
	 * 
	 * @param e
	 *            Element to delete
	 */
	protected abstract void modelDelete(F field, E e);

	private JPanel createPanelDetail() {

		// Page de détail
		formDetail.setView(view);
		formDetail.init();

		// Boutons de contrôle

		buttonCreate = createButton("FormFieldMasterDetail.Create");
		buttonSave = createButton("FormFieldMasterDetail.Save");
		buttonDelete = createButton("FormFieldMasterDetail.Delete");
		buttonCancel = createButton("FormFieldMasterDetail.Cancel");

		buttonCreate.setVisible(true);

		// Panel d'assemblage
		JPanel panelDetail = new JPanel(new BorderLayout(4, 4));
		panelDetail.add(formDetail.getComponent(), BorderLayout.CENTER);

		JPanel panelCommands = new JPanel(new FlowLayout(FlowLayout.TRAILING, 4, 4));
		panelCommands.add(buttonCreate);
		panelCommands.add(buttonSave);
		panelCommands.add(buttonDelete);
		panelCommands.add(buttonCancel);
		panelDetail.add(panelCommands, BorderLayout.PAGE_END);

		return panelDetail;
	}

	private JButton createButton(String code) {
		JButton button = new JButton(GUIStrings.get(code));
		button.setVisible(false);
		return button;
	}

	private JList createList() {
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(getCellRenderer());
		return list;
	}

	/**
	 * @return Cell renderer used for the list
	 */
	protected abstract ListCellRenderer getCellRenderer();

	private F fieldValue;

	private JPanel dualContainer;

	private JPanel overallContainer;

	public Object getFieldData(Object formData) {
		return fieldValue;
	}

	public void setValidationError(ValidationError validationError) {
		if (validationError != null) {
			ValidationReport report = new ValidationReport();
			report.add(validationError);
			fieldValidationReport.setFieldData(null, report);
			fieldValidationReport.getComponent().setVisible(true);
		} else {
			fieldValidationReport.setFieldData(null, null);
			fieldValidationReport.getComponent().setVisible(false);
		}
	}

	public void setEnabled(boolean enabled) {
		list.setEnabled(enabled);
	}

	@SuppressWarnings("unchecked")
	public void setFieldData(Object formData, Object fieldData) {
		this.fieldValue = (F) fieldData;
		modelList = new ElementListModel();
		list.setModel(modelList);
		onSelect(null);
	}

	/**
	 * List of initial elements.
	 */
	protected abstract List<E> modelList(F fieldValue);

	/**
	 * Create a new element
	 */
	protected abstract E modelCreate(F fieldValue);

	/**
	 * Model used for the list
	 */
	protected class ElementListModel extends AbstractListModel {

		private List<E> elements;

		public ElementListModel() {
			elements = new ArrayList<E>(modelList(fieldValue));
		}

		public void update(E e) {
			int index = elements.indexOf(e);
			fireContentsChanged(e, index, index);
		}

		public void remove(E e) {
			int index = elements.indexOf(e);
			elements.remove(e);
			fireIntervalRemoved(this, index, index);
		}

		public void add(E e) {
			int size = elements.size();
			elements.add(e);
			fireIntervalAdded(this, size, size);
		}

		public Object getElementAt(int index) {
			return elements.get(index);
		}

		public int getSize() {
			return elements.size();
		}

	}

	/**
	 * Checks if there is currently an edited element. If yes, this element is
	 * first validated.
	 */
	public void validate(ValidationReport validationReport) {
		super.validate(validationReport);
		if (selectedElement != null) {
			formDetail.validate(validationReport);
		}
	}

	protected void onCancel() {
		list.clearSelection();
	}

	public Form getFormDetail() {
		return formDetail;
	}

	public void setFormDetail(Form formDetail) {
		this.formDetail = formDetail;
	}

}
