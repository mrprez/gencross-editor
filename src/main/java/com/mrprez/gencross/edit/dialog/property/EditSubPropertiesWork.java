package com.mrprez.gencross.edit.dialog.property;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.propertiesList.PropertiesListEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class EditSubPropertiesWork implements BackgroundWork {
	private PropertyEditor propertyEditor;
	private Property property;
	
	
	public EditSubPropertiesWork(PropertyEditor propertyEditor, Property property) {
		super();
		this.propertyEditor = propertyEditor;
		this.property = property;
	}

	@Override
	public void doInBackground() throws Exception {
		PropertiesListEditor propertiesListEditor = new PropertiesListEditor(property.getSubProperties(), propertyEditor);
		PropertiesList propertiesList = propertiesListEditor.edit();
		if(propertiesList!=null){
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return null;
	}

	

}
