package com.mrprez.gencross.editor.download;

import javax.swing.JOptionPane;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class ChoosePluginTask implements EdtTask {
	private PluginDescriptor[] pluginDescriptorList;
	
	
	public ChoosePluginTask(PluginDescriptor[] pluginDescriptorList) {
		super();
		this.pluginDescriptorList = pluginDescriptorList;
	}

	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		JOptionPane.showOptionDialog(GencrossEditor.getInstance(), "Choisissez un plugin", "Choisissez un plugin", JOptionPane.OK_CANCEL_OPTION, 0, null, pluginDescriptorList, null);
		
	}

}
