package com.mrprez.gencross.edit.dialog.propertiesList;

import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class PropertiesListEditorFieldEnabilityWork implements EdtWork {
	private PropertiesListEditor propertiesListEditor;
	

	public PropertiesListEditorFieldEnabilityWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInEdt() throws Exception {
		propertiesListEditor.fieldEnability();
	}

	@Override
	public Work getNextWork() {
		return null;
	}

	

}
