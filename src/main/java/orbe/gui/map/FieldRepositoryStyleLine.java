/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import java.util.List;

import javax.swing.ListCellRenderer;

import orbe.model.style.RepositoryStyleLine;
import orbe.model.style.StyleLine;

import org.apache.commons.lang.StringUtils;

/**
 * Edition des styles de ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormFieldRepositoryTextStyles.java,v 1.1 2006/11/17 10:56:38
 *          guinnessman Exp $
 */
public class FieldRepositoryStyleLine extends AbstractFormFieldMasterDetail<StyleLine, RepositoryStyleLine> {

	@Override
	protected StyleLine createEmptyElement() {
		StyleLine style = RepositoryStyleLine.createDefaultStyle();
		style.setName("");
		return style;
	}

	@Override
	protected ListCellRenderer getCellRenderer() {
		return new CellRendererStyleLine();
	}

	@Override
	protected StyleLine modelCreate(RepositoryStyleLine repository) {
		return repository.create();
	}

	@Override
	protected void modelDelete(RepositoryStyleLine repository, StyleLine e) {
		repository.remove(e);
	}

	@Override
	protected boolean modelIsInvalid(RepositoryStyleLine repository, StyleLine e) {
		return StringUtils.isBlank(e.getName());
	}

	@Override
	protected boolean modelIsProtected(RepositoryStyleLine repository, StyleLine e) {
		return e.getId() == RepositoryStyleLine.DEFAUL_STYLE_ID;
	}

	@Override
	protected List<StyleLine> modelList(RepositoryStyleLine repository) {
		return repository.getStyles();
	}

}
