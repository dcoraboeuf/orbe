/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import net.sf.doolin.gui.field.support.AbstractFieldSupportFactory;
import net.sf.doolin.gui.field.support.ButtonSupport;
import net.sf.doolin.gui.field.support.CheckSupport;
import net.sf.doolin.gui.field.support.ColorSupport;
import net.sf.doolin.gui.field.support.ComboSupport;
import net.sf.doolin.gui.field.support.DecimalSupport;
import net.sf.doolin.gui.field.support.FileSupport;
import net.sf.doolin.gui.field.support.IntSupport;
import net.sf.doolin.gui.field.support.MultipleSelectionSupport;
import net.sf.doolin.gui.field.support.PasswordSupport;
import net.sf.doolin.gui.field.support.StaticSupport;
import net.sf.doolin.gui.field.support.TextSupport;
import net.sf.doolin.gui.field.support.ValidationReportFieldSupport;
import net.sf.doolin.gui.table.support.SwingTableSupport;
import net.sf.doolin.gui.table.support.TableSupport;
import net.sf.doolin.gui.tree.support.SwingTreeSupport;
import net.sf.doolin.gui.tree.support.TreeSupport;

/**
 * Factory for default support classes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingFieldSupportFactory.java,v 1.2 2007/08/01 15:58:47
 *          guinnessman Exp $
 */
public class SwingFieldSupportFactory extends AbstractFieldSupportFactory {

	@Override
	protected void init() {
		registerFieldSupport(StaticSupport.class, SwingStaticSupport.class);
		registerFieldSupport(CheckSupport.class, SwingCheckSupport.class);
		registerFieldSupport(TextSupport.class, SwingTextSupport.class);
		registerFieldSupport(ButtonSupport.class, SwingButtonSupport.class);
		registerFieldSupport(ComboSupport.class, SwingComboSupport.class);
		registerFieldSupport(TreeSupport.class, SwingTreeSupport.class);
		registerFieldSupport(TableSupport.class, SwingTableSupport.class);
		registerFieldSupport(FileSupport.class, SwingFileSupport.class);
		registerFieldSupport(MultipleSelectionSupport.class, SwingMultipleSelectionSupport.class);
		registerFieldSupport(PasswordSupport.class, SwingPasswordSupport.class);
		registerFieldSupport(ValidationReportFieldSupport.class, SwingValidationReportFieldSupport.class);
		registerFieldSupport(IntSupport.class, SwingIntSupport.class);
		registerFieldSupport(DecimalSupport.class, SwingDecimalSupport.class);
		registerFieldSupport(ColorSupport.class, SwingColorSupport.class);
	}

}
