package com.mrprez.gencross.editor.download;

import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.service.PersonnageServiceAccess;

public class DownloadPersonnageTask implements BackgroundTask {
	private int personnageId;
	private String personnageXml;
	
	
	public DownloadPersonnageTask(int personnageId) {
		super();
		this.personnageId = personnageId;
	}

	@Override
	public Task getNextTask() {
		return new DisplayPersonnageTask(personnageXml);
	}

	@Override
	public void doInBackground() throws Exception {
		PersonnageServiceAccess personnageService = new PersonnageServiceAccess();
		personnageXml = personnageService.getPersonnage(personnageId);
	}

}
