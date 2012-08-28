package com.mrprez.gencross.edit.dialog.property;

import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class PropertyEditorFieldEnabilityWork implements EdtWork {
	private PropertyEditor propertyEditor;
	

	public PropertyEditorFieldEnabilityWork(PropertyEditor propertyEditor) {
		super();
		this.propertyEditor = propertyEditor;
	}

	@Override
	public void doInEdt() throws Exception {
		propertyEditor.fieldEnability();
	}

	@Override
	public Work getNextWork() {
		return null;
	}

	

}
