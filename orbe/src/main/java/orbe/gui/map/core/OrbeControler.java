/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.core;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.View;

import orbe.gui.context.OrbeContext;
import orbe.gui.map.tool.Tool;
import orbe.model.PointDecimal;

public interface OrbeControler {

	OrbeContext getContext();

	OrbeView getView();

	void refresh(Shape shape);

	void setDisplaySize(Dimension size);

	void setTool(Tool tool);

	Tool getTool();

	void setCursor(Cursor object);

	void setCenterPX(PointDecimal pointPX);

	PointDecimal getCenterPX();

	void repaint(Shape shape, boolean b);

	ToolSettings getToolSettings();

	View getGUIFView();

	void setEditionComponent(JComponent component);

	Graphics2D getGraphics2D();

	/**
	 * @return Composant sous-jacent à la gestion par le controleur.
	 */
	JComponent getComponent();

	/**
	 * This method is called to make sure that the given rectangle is visible
	 * inside the view
	 * 
	 * @param zone
	 *            Zone to be visible.
	 */
	void ensureVisibility(Rectangle zone);

	/**
	 * Fixe une forme temporaire � afficher au-dessus de toutes les couches
	 */
	void setEditionShape(Shape shape);

	/**
	 * Fixe une forme temporaire à afficher au-dessus de toutes les couches.
	 * 
	 * @param shape
	 */
	void setOutlinedShape(Shape shape);

}
