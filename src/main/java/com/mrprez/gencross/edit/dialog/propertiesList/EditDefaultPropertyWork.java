package com.mrprez.gencross.edit.dialog.propertiesList;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class EditDefaultPropertyWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public EditDefaultPropertyWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		Property defaultProperty = propertiesListEditor.getPropertiesList().getDefaultProperty();
		PropertyEditor propertyEditor = new PropertyEditor(defaultProperty, propertiesListEditor);
		defaultProperty = propertyEditor.edit();
		if(defaultProperty!=null){
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return null;
	}

}
