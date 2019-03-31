/*
 * Created on 6 dec. 06
 */
package orbe.gui.export;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.AbstractField;
import orbe.gui.form.FormExport;

/**
 * Repr�sentation graphique du decoupage
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormFieldSlices.java,v 1.1 2006/12/06 12:00:35 guinnessman Exp $
 */
public class FieldSlices extends AbstractField {

	private PanelSlices panelSlices;
	
	@Override
	protected void init() {
		setReadOnly(true);
		panelSlices = new PanelSlices();
	}
	
	public JComponent getComponent() {
		return panelSlices;
	}
	
	public void setValidationError(ValidationError validationError) {
	}
	
	public Object getFieldData(Object formData) {
		return null;
	}
	
	public void setEnabled(boolean enabled) {
	}
	
	public void setFieldData(Object formData, Object fieldData) {
		FormExport form = (FormExport) formData;
		panelSlices.setSlices(form.getSlicesH(), form.getSlicesV());
	}

	protected static class PanelSlices extends JPanel {

		private int slicesH = 1;

		private int slicesV = 1;
		
		/**
		 * Default constructor.
		 */
		public PanelSlices() {
			setBorder(BorderFactory.createEtchedBorder());
		}

		@Override
		protected void paintComponent(Graphics gg) {
			Graphics2D g = (Graphics2D) gg;
			// Background
			super.paintComponent(gg);
			// Width and height
			int width = getWidth();
			int height = getHeight();
			// Ratios
			float sliceWidth = (float) width / slicesH;
			float sliceHeight = (float) height / slicesV;
			// Forme � dessiner
			GeneralPath path = new GeneralPath();
			// Dessin H
			for (int i = 1; i < slicesH; i++) {
				float x = sliceWidth * i;
				path.moveTo(x, 0);
				path.lineTo(x, height);
			}
			// Dessin V
			for (int i = 1; i < slicesV; i++) {
				float y = sliceHeight * i;
				path.moveTo(0, y);
				path.lineTo(width, y);
			}
			// Dessin
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke());
			g.draw(path);
		}

		public void setSlices(int slicesH, int slicesV) {
			this.slicesH = slicesH;
			this.slicesV = slicesV;
			repaint();
		}

	}

}
