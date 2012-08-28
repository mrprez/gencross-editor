package com.mrprez.gencross.edit.dialog.propertiesList;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class AddOptionWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public AddOptionWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
		Property option = new Property("", propertiesList.getOwner());
		PropertyEditor propertyEditor = new PropertyEditor(option, propertiesListEditor);
		option = propertyEditor.edit();
		if(option!=null){
			propertiesList.getOptions().put(option.getFullName(), option);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new RefreshPropertiesListWork(propertiesListEditor);
	}

}
