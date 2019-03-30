/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.controler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.core.View;
import orbe.gui.context.OrbeContext;
import orbe.gui.jmx.OrbeMessageMgtRefresh;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.core.OrbeView;
import orbe.gui.map.core.ToolSettings;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.LayeredOrbeRenderer;
import orbe.gui.map.scale.ScaleMath;
import orbe.gui.map.tool.Tool;
import orbe.gui.map.view.TiledOrbeView;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.model.PointDecimal;

public class OrbePanel extends JPanel implements OrbeControler, Scrollable {

	private OrbeContext context;

	private Rectangle displayZone;

	private Tool tool;

	private OrbeView view;

	private ToolSettings toolSettings;

	private ViewSettings viewSettings;

	private View guifView;

	private JComponent editionComponent;

	private Shape editionShape;

	private Shape outlinedShape;

	public OrbePanel(View view) {
		setLayout(null);
		guifView = view;
		setRequestFocusEnabled(true);
		setFocusable(true);
	}

	public void setContext(OrbeContext ctx) {
		this.context = ctx;
		if (context != null) {
			setup();
		} else {
			setEditionComponent(null);
			setTool(null);
			setDisplaySize(new Dimension());
		}
		repaint();
	}

	public void setup() {
		if (context != null) {
			viewSettings = new ViewSettings();
			toolSettings = new ToolSettings(this);
			// Register for management
			Bus.get().subscribe(OrbeMessageMgtRefresh.class, new Subscriber<OrbeMessageMgtRefresh>() {

				public void receive(OrbeMessageMgtRefresh message) {
					reset();
					repaint();
				}

			});
			// Register for change of the settings
			Bus.get().subscribe(OrbeMessageContextSettings.class, new Subscriber<OrbeMessageContextSettings>() {

				public void receive(OrbeMessageContextSettings message) {
					reset();
					repaint();
				}

			});
			// Creates the view
			reset();
		}
	}

	public void reset() {
		if (context != null) {
			PointDecimal centerPX = null;
			if (view != null && context != null) {
				centerPX = getCenterPX();
			}
			LayeredOrbeRenderer renderer = new LayeredOrbeRenderer();
			view = new TiledOrbeView(context.getMap(), viewSettings);
			view.setRenderer(renderer);
			// Get the size of the displaying area
			Dimension size = view.getDisplaySize(context.getMap());
			// Adjust the size
			setDisplaySize(size);
			// Restore the center
			if (centerPX != null) {
				setCenterPX(centerPX);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		if (context != null) {
			Graphics2D g = (Graphics2D) g1;
			Rectangle clip = g.getClipBounds();
			if (clip == null) {
				clip = new Rectangle();
			} else {
				clip = clip.intersection(displayZone);
			}
			Rectangle2D.Double zone = new Rectangle2D.Double(clip.x, clip.y, clip.width, clip.height);
			paint(g, zone);
		}
	}

	public void setDisplaySize(Dimension size) {
		setSize(size);
		setPreferredSize(size);
		displayZone = new Rectangle(0, 0, size.width, size.height);
		setBounds(displayZone);
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool t) {
		if (tool != null) {
			removeMouseListener(tool);
			removeMouseMotionListener(tool);
			removeKeyListener(tool);
			tool.unregister();
		}
		if (context != null) {
			tool = t;
			if (tool != null) {
				tool.setControler(this);
				addMouseListener(tool);
				addMouseMotionListener(tool);
				addKeyListener(tool);
				tool.register();
				this.requestFocusInWindow();
			}
		}
		setCursor(Cursor.getDefaultCursor());
	}

	public PointDecimal getCenterPX() {
		// Get the PX center of the visible area
		Rectangle visibleRect = getVisibleRect();
		int cx = visibleRect.x + visibleRect.width / 2;
		int cy = visibleRect.y + visibleRect.height / 2;
		Point c = new Point(cx, cy);
		// Converts to KM
		return ScaleMath.scaleScreenToPX(context.getMap(), view.getViewSettings(), c);
	}

	public void setCenterPX(PointDecimal pointPX) {
		// Converts to Screen
		Point c = ScaleMath.scalePXToScreen(context.getMap(), view.getViewSettings(), pointPX);
		// Calculates the new visible rect
		Rectangle visibleRect = getVisibleRect();
		int width = visibleRect.width;
		int height = visibleRect.height;
		Rectangle newVisibleRect = new Rectangle(c.x - width / 2, c.y - height / 2, width, height);
		scrollRectToVisible(newVisibleRect);
	}

	public void repaint(Shape shape, boolean refresh) {
		if (refresh) {
			refresh(shape);
		}
		repaint(shape.getBounds());
	}

	public OrbeContext getContext() {
		return context;
	}

	public OrbeView getView() {
		return view;
	}

	protected void paint(Graphics2D g, Double zone) {
		view.paint(context.getMap(), g, zone);
		// Forme temporaire
		if (editionShape != null && editionShape.intersects(zone)) {
			g.setStroke(new BasicStroke(1f));
			g.setColor(Color.BLACK);
			g.draw(editionShape);
		}
		// Outline
		if (outlinedShape != null && outlinedShape.intersects(zone)) {
			g.setStroke(new BasicStroke(2f));
			g.setColor(Color.YELLOW);
			g.draw(outlinedShape);
		}
	}

	public void refresh(Shape shape) {
		Rectangle r = shape.getBounds();
		int extension = 2;
		r.grow(extension, extension);
		Double bounds = new Double(r.x, r.y, r.width, r.height);
		view.refresh(context.getMap(), bounds);
		repaint(r);
	}

	public ToolSettings getToolSettings() {
		return toolSettings;
	}

	public View getGUIFView() {
		return guifView;
	}

	public Dimension getPreferredScrollableViewportSize() {
		if (view != null && context != null) {
			return view.getDisplaySize(context.getMap());
		} else {
			return getPreferredSize();
		}
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		if (orientation == SwingConstants.HORIZONTAL) {
			return visibleRect.width / 2;
		} else {
			return visibleRect.height / 2;
		}
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		if (orientation == SwingConstants.HORIZONTAL) {
			return visibleRect.width / 10;
		} else {
			return visibleRect.height / 10;
		}
	}

	public void setEditionComponent(JComponent component) {
		if (editionComponent != null) {
			remove(editionComponent);
		}
		editionComponent = component;
		if (editionComponent != null) {
			add(editionComponent);
			editionComponent.requestFocusInWindow();
		}
		validate();
		repaint();
	}

	public Graphics2D getGraphics2D() {
		return (Graphics2D) getGraphics();
	}

	/**
	 * @see orbe.gui.map.core.OrbeControler#getComponent()
	 */
	public JComponent getComponent() {
		return this;
	}

	public void ensureVisibility(Rectangle zone) {
		if (!getVisibleRect().contains(zone)) {
			scrollRectToVisible(zone);
		}
	}

	public void setEditionShape(Shape shape) {
		// Zone à rafraichir
		Rectangle zone = null;
		// Ancienne forme
		if (editionShape != null) {
			zone = editionShape.getBounds();
		}
		// Nouvelle forme
		editionShape = shape;
		if (editionShape != null) {
			if (zone != null) {
				zone = zone.union(editionShape.getBounds());
			} else {
				zone = editionShape.getBounds();
			}
		}
		// Refresh
		if (zone != null) {
			zone.grow(5, 5);
			repaint(zone);
		}
	}

	public Shape getOutlinedShape() {
		return outlinedShape;
	}

	public void setOutlinedShape(Shape shape) {
		// Zone à rafraichir
		Rectangle zone = null;
		// Ancienne forme
		if (outlinedShape != null) {
			zone = outlinedShape.getBounds();
		}
		// Nouvelle forme
		outlinedShape = shape;
		if (outlinedShape != null) {
			if (zone != null) {
				zone = zone.union(outlinedShape.getBounds());
			} else {
				zone = outlinedShape.getBounds();
			}
		}
		// Refresh
		if (zone != null) {
			zone.grow(5, 5);
			repaint(zone);
		}
	}
}
