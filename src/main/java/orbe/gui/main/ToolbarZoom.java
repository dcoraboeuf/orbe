/*
 * Created on Sep 19, 2007
 */
package orbe.gui.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JToolBar;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.core.View;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.OrbeContent;
import orbe.gui.map.controler.OrbePanel;
import orbe.gui.message.OrbeMessageViewZoom;
import orbe.gui.task.OrbeTaskZoom;

public class ToolbarZoom extends AbstractToolbarElement {

	protected static final int CBO_WIDTH = 80;

	private static final String[] ZOOMS = { "0.1", "0.25", "0.5", "0.75", "1", "1.5", "2", "5", "10" };

	private JComboBox cbo;

	private NumberFormat format = NumberFormat.getPercentInstance();

	private boolean onChange;

	private View view;

	@Override
	public void createToolbarItem(JToolBar j, View view) {
		// Default
		super.createToolbarItem(j, view);
		this.view = view;

		cbo = new JComboBox();
		cbo.setPreferredSize(new Dimension(CBO_WIDTH, CBO_HEIGHT));
		cbo.setMaximumSize(new Dimension(CBO_WIDTH, CBO_HEIGHT));
		cbo.setEditable(true);
		cbo.setEnabled(false);
		for (String zoom : ZOOMS) {
			BigDecimal factor = new BigDecimal(zoom);
			String text = format.format(factor);
			cbo.addItem(text);
		}
		cbo.setSelectedItem(format.format(1));

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				doZoom();
			}

		});

		Bus.get().subscribe(OrbeMessageViewZoom.class, new Subscriber<OrbeMessageViewZoom>() {

			public void receive(OrbeMessageViewZoom message) {
				BigDecimal zoom = message.getValue();
				setZoom(zoom);
			}

		});

		j.add(cbo);
	}

	public OrbePanel getControler() {
		OrbeContent content = (OrbeContent) view;
		return content.getPanel();
	}

	protected void doZoom() {
		if (!onChange) {
			String text = (String) cbo.getSelectedItem();
			try {
				Number number = format.parse(text);
				BigDecimal zoomFactor = new BigDecimal(number.toString());
				OrbeTaskZoom task = new OrbeTaskZoom(getControler(), zoomFactor);
				task.process();
			} catch (ParseException ex) {
				// Does nothing until the zoom is fixed
			}
		}
	}

	protected void setZoom(BigDecimal zoom) {
		String text = format.format(zoom);
		try {
			onChange = true;
			cbo.setSelectedItem(text);
		} finally {
			onChange = false;
		}
	}

	@Override
	protected void onContextChanged(OrbeContext context) {
		cbo.setEnabled(context != null);
		setZoom(BigDecimal.ONE);
	}

}
