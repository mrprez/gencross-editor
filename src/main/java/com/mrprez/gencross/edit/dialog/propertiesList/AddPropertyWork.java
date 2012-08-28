package com.mrprez.gencross.edit.dialog.propertiesList;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class AddPropertyWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public AddPropertyWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		Property property = new Property("", propertiesListEditor.getPropertiesList().getOwner());
		PropertyEditor propertyEditor = new PropertyEditor(property, propertiesListEditor);
		property = propertyEditor.edit();
		if(property!=null){
			propertiesListEditor.getPropertiesList().add(property);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new RefreshPropertiesListWork(propertiesListEditor);
	}

	

}
