/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import java.util.List;

import javax.swing.ListCellRenderer;

import org.apache.commons.lang.StringUtils;

import orbe.model.style.RepositoryTextStyle;
import orbe.model.style.TextStyle;

/**
 * Edition des styles de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormFieldRepositoryTextStyles.java,v 1.1 2006/11/17 10:56:38 guinnessman Exp $
 */
public class FieldRepositoryTextStyles extends AbstractFormFieldMasterDetail<TextStyle, RepositoryTextStyle> {

	@Override
	protected TextStyle createEmptyElement() {
		TextStyle style = RepositoryTextStyle.createDefaultStyle();
		style.setName("");
		return style;
	}

	@Override
	protected ListCellRenderer getCellRenderer() {
		return new CellRendererTextStyle();
	}

	@Override
	protected TextStyle modelCreate(RepositoryTextStyle repository) {
		return repository.create();
	}

	@Override
	protected void modelDelete(RepositoryTextStyle repository, TextStyle e) {
		repository.remove(e);
	}

	@Override
	protected boolean modelIsInvalid(RepositoryTextStyle repository, TextStyle e) {
		return StringUtils.isBlank(e.getName());
	}

	@Override
	protected boolean modelIsProtected(RepositoryTextStyle repository, TextStyle e) {
		return e.getId() == RepositoryTextStyle.DEFAUL_STYLE_ID;
	}

	@Override
	protected List<TextStyle> modelList(RepositoryTextStyle repository) {
		return repository.getStyles();
	}

}
