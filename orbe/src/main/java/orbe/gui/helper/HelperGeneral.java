/*
 * Created on Oct 3, 2006
 */
package orbe.gui.helper;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import orbe.model.Unit;
import orbe.model.style.HexSymbol;
import orbe.model.style.LineDotType;
import orbe.model.style.LineGradType;
import orbe.model.style.LineType;
import orbe.model.style.RepositoryHexSymbol;

public class HelperGeneral {

	/**
	 * @return List of all available fonts.
	 */
	public List<Font> getFonts() {
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

	private Font createFont(String name) {
		return new Font(name, Font.PLAIN, 1);
	}

	public List<Unit> getUnitList() {
		return Arrays.asList(Unit.values());
	}

	public List<HexSymbol> getSymbols() {
		return RepositoryHexSymbol.getInstance().getSymbols();
	}

	public List<LineType> getLineTypes() {
		return Arrays.asList(LineType.values());
	}

	public List<LineDotType> getLineDotTypes() {
		return Arrays.asList(LineDotType.values());
	}

	public List<LineGradType> getLineGradTypes() {
		return Arrays.asList(LineGradType.values());
	}

}
