/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map;

import java.math.BigDecimal;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Priority;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.view.AbstractView;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.controler.OrbePanel;
import orbe.gui.map.tool.Tool;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.gui.message.OrbeMessageTool;
import orbe.gui.message.OrbeMessageViewZoom;
import orbe.model.OrbeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrbeContent extends AbstractView {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(OrbeContent.class);

	private OrbeContext context;

	private OrbePanel panel;

	private JScrollPane scrollPane;
	
	@Override
	public void init() {
		super.init();
		register();

		panel = new OrbePanel(this);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
	}
	
	@Override
	public void setViewData(Object viewData) {
		super.setViewData(viewData);
		log.debug("Set view data as " + viewData);
		OrbeContext newContext = (OrbeContext) viewData;
		if (newContext != context) {
			context = newContext;
			setup();
		} else {
			// Nothing to do
		}
	}

	/**
	 * Setup the panel with the map.
	 */
	protected void setup() {
		panel.setContext(context);
	}

	protected void reset() {
		panel.reset();
	}

	protected OrbeMap getMap() {
		return context != null ? context.getMap() : null;
	}

	protected void register() {
		Bus bus = Bus.get();

		bus.subscribe(OrbeMessageContextSettings.class, new Subscriber<OrbeMessageContextSettings>() {

			public void receive(OrbeMessageContextSettings message) {
				OrbeMap map = message.getValue();
				if (context != null && map == context.getMap()) {
					reset();
				}
			}

		});

		bus.subscribe(OrbeMessageViewZoom.class, new Subscriber<OrbeMessageViewZoom>() {

			public void receive(OrbeMessageViewZoom message) {
				BigDecimal zoom = message.getValue();
				log.debug("Changing zoom to " + zoom);
				panel.getView().getViewSettings().setZoom(zoom);
				reset();
			}

		});

		/*
		 * The traitement doit être effectué en premier afin que l'outil soit
		 * complètement paramétré.
		 */
		bus.subscribe(OrbeMessageTool.class, new Subscriber<OrbeMessageTool>() {

			public void receive(OrbeMessageTool message) {
				Tool tool = message.getValue();
				log.debug("Changing tool to " + tool);
				panel.setTool(tool);
			}

		}, Priority.HIGHEST);

	}

	public OrbePanel getPanel() {
		return panel;
	}

	public ViewContainer createViewContainer() {
		return null;
	}

	public JComponent getComponent() {
		return scrollPane;
	}

	public void setEnabled(boolean enabled) {
	}

	public void validate(ValidationReport validationReport) {
	}

}
