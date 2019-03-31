/*
 * Created on Sep 18, 2007
 */
package orbe.gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.tool.Tool;
import orbe.gui.map.tool.ToolFactory;
import orbe.gui.message.OrbeMessageTool;

public class ToolbarTool extends AbstractToolbarElement {

	private String toolId;

	private JToggleButton button;

	private Tool tool;

	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public void createToolbarItem(JToolBar j, View view) {
		super.createToolbarItem(j, view);
		// Gets the tool
		tool = ToolFactory.getInstance().getTool(toolId);
		if (tool == null) {
			throw new IllegalStateException("Cannot find tool with ID=" + toolId);
		}

		// Get the icon
		String iconId = "IconTool." + toolId;
		Icon icon = GUIUtils.getIcon(iconId, IconSize.MEDIUM);

		// Button
		button = new JToggleButton(icon, false);
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				doAction();
			}

		});

		// Tool change
		Bus.get().subscribe(OrbeMessageTool.class, new Subscriber<OrbeMessageTool>() {

			public void receive(OrbeMessageTool message) {
				Tool selectedTool = message.getValue();
				button.setSelected(selectedTool == tool);
			}

		});

		// Ok
		j.add(button);
	}

	protected void doAction() {
		Bus.get().publish(new OrbeMessageTool(tool));
	}

	@Override
	protected void onContextChanged(OrbeContext context) {
		boolean hasMap = context != null;
		button.setEnabled(hasMap);
		button.setSelected(false);
	}

}
