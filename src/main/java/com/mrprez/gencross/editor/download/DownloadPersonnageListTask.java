package com.mrprez.gencross.editor.download;

import java.util.Map;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.service.PersonnageService;

public class DownloadPersonnageListTask implements BackgroundTask {
	
	private PluginDescriptor pluginDescriptor;
	
	private Map<Integer,String> personnageMap;
	
	
	
	
	public DownloadPersonnageListTask(PluginDescriptor pluginDescriptor) {
		super();
		this.pluginDescriptor = pluginDescriptor;
	}

	@Override
	public Task getNextTask() {
		return new ChoosePersonnageTask(personnageMap);
	}

	@Override
	public void doInBackground() throws Exception {
		PersonnageService personnageService = new PersonnageService();
		personnageMap = personnageService.getPersonnageLabels(pluginDescriptor);
	}

	

	
}
