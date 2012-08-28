package com.mrprez.gencross.edit.dialog.propertiesList;

import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class RefreshPropertiesListWork implements EdtWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public RefreshPropertiesListWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInEdt() throws Exception {
		propertiesListEditor.refreshLists();
	}

	@Override
	public Work getNextWork() {
		return null;
	}

	

}
