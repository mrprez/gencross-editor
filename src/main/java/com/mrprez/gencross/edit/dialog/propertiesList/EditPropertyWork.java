package com.mrprez.gencross.edit.dialog.propertiesList;

import java.util.ArrayList;
import java.util.List;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class EditPropertyWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public EditPropertyWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		Property property = propertiesListEditor.getSelectedProperty();
		if(property!=null){
			String oldPropertyName = new String(property.getFullName());
			PropertyEditor propertyEditor = new PropertyEditor(propertiesListEditor.getSelectedProperty(), propertiesListEditor);
			propertyEditor.edit();
			if(!property.getFullName().equals(oldPropertyName)){
				refreshKeys();
			}
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}
	
	private void refreshKeys(){
		PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
		List<Property> saveList = new ArrayList<Property>(propertiesList.size());
		saveList.addAll(propertiesList);
		propertiesList.clear();
		for(Property property : saveList){
			propertiesList.add(property);
		}
	}

	@Override
	public Work getNextWork() {
		return new RefreshPropertiesListWork(propertiesListEditor);
	}

}
