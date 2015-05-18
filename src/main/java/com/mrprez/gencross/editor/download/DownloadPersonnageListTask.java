package com.mrprez.gencross.editor.download;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.service.PersonnageServiceAccess;
import com.mrprez.gencross.ws.api.bo.PersonnageLabel;

public class DownloadPersonnageListTask implements BackgroundTask {
	
	private PluginDescriptor pluginDescriptor;
	
	private PersonnageLabel[] personnageMap;
	
	
	
	
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
		PersonnageServiceAccess personnageService = new PersonnageServiceAccess();
		personnageMap = personnageService.getPersonnageLabels(pluginDescriptor);
	}

	

	
}
