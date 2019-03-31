/*
 * Created on 6 dec. 06
 */
package orbe.gui.export;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.AbstractGUITask;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.monitor.TaskMonitor;
import net.sf.doolin.gui.monitor.TaskMonitorFactory;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.util.CodeException;
import orbe.gui.IViews;
import orbe.gui.form.FormExport;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.view.TiledOrbeView;
import orbe.model.OrbeMap;

import org.apache.commons.lang.StringUtils;

/**
 * tâche d'export.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ExportTask.java,v 1.2 2006/12/06 13:48:13 guinnessman Exp $
 */
public class ExportTask extends AbstractGUITask {

	/**
	 * Formulaire d'export
	 */
	private FormExport form;

	/**
	 * Contr�leur actuel
	 */
	private OrbeControler controler;

	/**
	 * Moteur de rendu
	 */
	private TiledOrbeView view;

	private ImageWriter imageWriter;

	public ExportTask(FormExport form, OrbeControler controler) {
		this.form = form;
		this.controler = controler;
	}
	
	public void launch () {
		// Get the main frame
		View frame = GUIUtils.getViewManager().getOpenedView(IViews.ID_FRAME_MAIN);
		// Monitor for the task
		String monitorTitle = GUIStrings.get("ExportTask.monitor.title");
		String monitorMessage = GUIStrings.get("ExportTask.monitor.message", form.getFile().getName());
		TaskMonitor monitor = GUIUtils.getService(TaskMonitorFactory.class).createMonitor(frame);
		monitor.setCancellable(true);
		monitor.setDoCycle(false);
		monitor.setTitle(monitorTitle);
		monitor.setMessage(monitorMessage);
		// Launches the monitor
		monitor.start(this);
	}

	public String getName() {
		return "Export to " + form.getFile();
	}

	protected ImageWriter getPNGWriter() {
		// Get a writer for PNG
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
		if (writers.hasNext()) {
			// Get the PNG writer
			ImageWriter imageWriter = writers.next();
			// Ok
			return imageWriter;
		} else {
			throw new RuntimeException("No registered driver for PNG format.");
		}
	}

	public void execute() {
		// Initialisation d'une vue
		fireProgress(1, GUIStrings.get("ExportTask.note.init"));
		init();
		// Get the list of zones to write
		List<ExportedZone> exportedZones = getExportedZones();
		getTaskListener().setMaximum(exportedZones.size() + 1);
		// For all zones
		for (ExportedZone xZone : exportedZones) {
			fireProgress(0, getNote(xZone));
			export(xZone);
			fireProgress(1, getNote(xZone));
		}
	}

	protected String getNote(ExportedZone xZone) {
		if (xZone.index != null) {
			return GUIStrings.get("ExportTask.note.exportZone", xZone.index.x, xZone.index.y);
		} else {
			return GUIStrings.get("ExportTask.note.exportGeneral");
		}
	}

	protected void export(ExportedZone xZone) {
		// Get the file for this zone
		File file = getFile(xZone);
		try {
			// Prepares to write to this zone
			ImageOutputStream ios = ImageIO.createImageOutputStream(file);
			imageWriter.setOutput(ios);
			try {
				Rectangle r = xZone.zone;
				Rectangle2D.Double zone = new Rectangle2D.Double(r.x, r.y, r.width, r.height);
				// Renders the image for the zone
				OrbeMap map = form.getContext().getMap();
				view.refresh(map, zone);
				// Get the image for the zone
				BufferedImage image = view.getImage().getSubimage(r.x, r.y, r.width, r.height);
				// Renders this image in the file
				imageWriter.write(image);
			} finally {
				ios.close();
			}
		} catch (IOException ex) {
			throw new CodeException("Error.Export.IO", ex, file, ex);
		}
	}

	private File getFile(ExportedZone zone) {
		File root = form.getFile();
		if (zone.index == null) {
			return root;
		} else {
			String name = root.getName();
			String prefix = StringUtils.substringBeforeLast(name, ".");
			String extension = StringUtils.substringAfterLast(name, ".");
			String newName = prefix + "." + zone.index.x + "." + zone.index.y + "." + extension;
			return new File(root.getParentFile(), newName);
		}
	}

	protected void init() {
		// Get a writer for PNG
		imageWriter = getPNGWriter();
		// View
		OrbeMap map = form.getContext().getMap();
		ViewSettings viewSettings = new ViewSettings();
		view = new TiledOrbeView(map, viewSettings);
		// R�utilisation du m�me renderer
		view.setRenderer(controler.getView().getRenderer());
	}

	/**
	 * 
	 * @return Liste des zones � exporter
	 */
	protected List<ExportedZone> getExportedZones() {
		// Carte
		OrbeMap map = form.getContext().getMap();
		// Dimensions
		Dimension size = view.getDisplaySize(map);
		// decoupage
		if (form.isSlices()) {
			Rectangle zone = new Rectangle(size);
			ArrayList<ExportedZone> xZones = new ArrayList<ExportedZone>();
			int slicesH = form.getSlicesH();
			int slicesV = form.getSlicesV();
			int sliceWidth = size.width / slicesH;
			int sliceHeight = size.height / slicesV;
			for (int i = 0; i < slicesH; i++) {
				int thisSliceX = i * sliceWidth - form.getMargin();
				int thisSliceWidth = sliceWidth + 2 * form.getMargin();
				for (int j = 0; j < slicesV; j++) {
					int thisSliceY = j * sliceHeight - form.getMargin();
					int thisSliceHeight = sliceHeight + 2 * form.getMargin();
					Rectangle thisSliceZone = new Rectangle(thisSliceX, thisSliceY, thisSliceWidth, thisSliceHeight);
					thisSliceZone = thisSliceZone.intersection(zone);
					if (i == slicesH - 1) {
						if (thisSliceZone.x + thisSliceZone.width < zone.width) {
							thisSliceZone.width += (zone.width - (thisSliceZone.x + thisSliceZone.width));
						}
					}
					if (j == slicesV - 1) {
						if (thisSliceZone.y + thisSliceZone.height < zone.height) {
							thisSliceZone.height += (zone.height - (thisSliceZone.y + thisSliceZone.height));
						}
					}
					Point thisSliceIndex = new Point(i, j);
					ExportedZone xZone = new ExportedZone(thisSliceZone, thisSliceIndex);
					xZones.add(xZone);
				}
			}
			return xZones;
		}
		// Pas de decoupage
		else {
			Rectangle zone = new Rectangle(size);
			Point index = null;
			ExportedZone xZone = new ExportedZone(zone, index);
			return Collections.singletonList(xZone);
		}
	}

	/**
	 * Definition for an exported zone
	 */
	protected static class ExportedZone {
		/**
		 * Graphic zone
		 */
		public Rectangle zone;

		/**
		 * Index
		 */
		public Point index;

		public ExportedZone(Rectangle zone, Point index) {
			this.zone = zone;
			this.index = index;
		}
	}

}
