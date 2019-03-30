/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.core;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.math.BigDecimal;

public class ViewSettings {

	private BigDecimal zoom;

	private transient AffineTransform zoomTransform;

	/**
	 * Default constructor.
	 */
	public ViewSettings() {
		setZoom(BigDecimal.ONE);
	}

	public BigDecimal getZoom() {
		return zoom;
	}

	public void setZoom(BigDecimal zoom) {
		this.zoom = zoom;
		double zoomFactor = zoom.doubleValue();
		zoomTransform = AffineTransform.getScaleInstance(zoomFactor, zoomFactor);
	}

	public Shape zoom(Shape shape) {
		return zoomTransform.createTransformedShape(shape);
	}

	public AffineTransform getZoomTransform() {
		return zoomTransform;
	}

}
