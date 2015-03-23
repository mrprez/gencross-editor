package com.mrprez.gencross.editor.download;

import javax.swing.JOptionPane;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class ChoosePluginTask implements EdtTask {
	private PluginDescriptor[] pluginDescriptorList;
	
	private PluginDescriptor pluginDescriptor;
	
	
	public ChoosePluginTask(PluginDescriptor[] pluginDescriptorList) {
		super();
		this.pluginDescriptorList = pluginDescriptorList;
	}

	@Override
	public Task getNextTask() {
		return new DownloadPersonnageListTask(pluginDescriptor);
	}

	@Override
	public void doInEdt() throws Exception {
		pluginDescriptor = (PluginDescriptor) JOptionPane.showInputDialog(GencrossEditor.getInstance(), "Choisissez un plugin", "Choisissez un plugin", JOptionPane.QUESTION_MESSAGE, null, pluginDescriptorList, null);
	}

}
