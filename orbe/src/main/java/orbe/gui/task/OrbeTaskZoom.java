/*
 * Created on Nov 15, 2006
 */
package orbe.gui.task;

import java.awt.Dimension;
import java.math.BigDecimal;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageViewZoom;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;

/**
 * Effectue le zoom sur l'image.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskZoom.java,v 1.3 2006/11/29 15:22:24 guinnessman Exp $
 */
public class OrbeTaskZoom extends OrbeTask {

	private BigDecimal zoomFactor;

	private PointDecimal centerPX;

	public OrbeTaskZoom(OrbeControler orbeControler, BigDecimal zoom) {
		super(orbeControler);
		zoomFactor = zoom;
	}

	@Override
	public void process() {
		// Calcul de la taille résultante de l'image
		OrbeMap map = getControler().getContext().getMap();
		Dimension size = getControler().getView().getDisplaySize(map);
		long memory = 3 * 2* size.width * size.height; /* 3 octets par pixels et deux fois l'image pour l'offscreen Swing */
		long availableMemory = getAvailableMemory();
		if (memory > availableMemory) {
			System.gc();
			availableMemory = getAvailableMemory();
			if (memory > availableMemory) {
				boolean ok = GUIUtils.getAlertManager().confirm(getControler().getGUIFView(), "OrbeTaskZoom.memoryPrompt");
				if (!ok) {
					return;
				}
			}
		}
		// Sauvegarde du centre
		if (centerPX == null) {
			centerPX = getControler().getCenterPX();
		}
		// Sauvegarde du zoom pour restauration
		BigDecimal oldZoomFactor = getControler().getView().getViewSettings().getZoom();
		// Publication pour MAJ
		Bus.get().publish(new OrbeMessageViewZoom(zoomFactor));
		// Restauration du centre
		getControler().setCenterPX(centerPX);
		// Pr�paration de l'annulation
		zoomFactor = oldZoomFactor;
	}

	protected long getAvailableMemory() {
		long maxMemory = Runtime.getRuntime().maxMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long availableMemory = (maxMemory - totalMemory + freeMemory);
		return availableMemory;
	}

	public PointDecimal getCenterPX() {
		return centerPX;
	}

	public void setCenterPX(PointDecimal centerPX) {
		this.centerPX = centerPX;
	}

	/**
	 * @return <code>false</code> car cette tâche ne modifie pas la structure
	 *         du fichier Orbe.
	 * @see orbe.gui.task.OrbeTask#isSetDirty()
	 */
	@Override
	public boolean isSetDirty() {
		return false;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskZoom.name");
	}

}
