/*
 * Created on Nov 10, 2006
 */
package orbe.gui.jmx;

/**
 * Interface de management.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeManagerMBean.java,v 1.2 2006/11/22 11:35:03 guinnessman Exp $
 */
public interface OrbeManagerMBean {

	void setTiledOrbeViewDebug(boolean debug);

	boolean getTiledOrbeViewDebug();

	void setLayeredOrbeRendererDebug(boolean debug);

	boolean getLayeredOrbeRendererDebug();

	boolean isLayerTextDebug();
	
	void setLayerTextDebug(boolean layerTextDebug);

	void refresh();

}
