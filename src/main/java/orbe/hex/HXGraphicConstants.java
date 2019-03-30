/*
 * Created on 19 oct. 06
 */
package orbe.hex;

public interface HXGraphicConstants {

	double HXDIM_H_OFFSET = 0.5;

	double HXDIM_H_WIDTH = 1;

	double HXDIM_V_OFFSET1 = 1.0 / (2.0 * Math.sqrt(3.0));

	double HXDIM_V_OFFSET2 = Math.sqrt(3.0) / 2.0;

	double HXDIM_V_HEIGHT = 2.0 / Math.sqrt(3.0);

	double HXDIM_V_EXTRA = HXDIM_V_HEIGHT - HXDIM_V_OFFSET2;

	double HXDIM_SIDE = 1.0 / Math.sqrt(3.0);
}
