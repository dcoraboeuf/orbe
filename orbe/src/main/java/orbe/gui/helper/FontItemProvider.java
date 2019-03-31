/*
 * Created on Sep 20, 2007
 */
package orbe.gui.helper;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.doolin.gui.field.helper.ItemProvider;

public class FontItemProvider implements ItemProvider {

	private Font createFont(String name) {
		return new Font(name, Font.PLAIN, 1);
	}

	public List getItems() {
		ArrayList<Font> list = new ArrayList<Font>();
		// Java fonts
		list.add(createFont("Serif"));
		list.add(createFont("SansSerif"));
		list.add(createFont("Monospaced"));
		list.add(createFont("Dialog"));
		list.add(createFont("DialogInput"));
		// Separator
		list.add(null);
		// System fonts
		List<Font> systemFonts = getSystemFonts();
		list.addAll(systemFonts);
		return list;
	}

	private List<Font> getSystemFonts() {
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		List<Font> systemFonts = new ArrayList<Font>(Arrays.asList(fonts));
		Collections.sort(systemFonts, new Comparator<Font>() {

			public int compare(Font o1, Font o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
		return systemFonts;
	}

}
