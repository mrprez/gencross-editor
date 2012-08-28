package com.mrprez.gencross.edit.dialog.propertiesList;

import java.util.ArrayList;
import java.util.List;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class EditOptionWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public EditOptionWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		Property option = propertiesListEditor.getSelectedOption();
		if(option!=null){
			String oldName = new String(option.getFullName());
			PropertyEditor propertyEditor = new PropertyEditor(option, propertiesListEditor);
			propertyEditor.edit();
			if(!oldName.equals(option.getFullName())){
				refreshKeys();
			}
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}
	
	private void refreshKeys(){
		PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
		List<Property> saveList = new ArrayList<Property>(propertiesList.getOptions().size());
		saveList.addAll(propertiesList.getOptions().values());
		propertiesList.getOptions().clear();
		for(Property option : saveList){
			propertiesList.getOptions().put(option.getFullName(), option);
		}
	}

	@Override
	public Work getNextWork() {
		return new RefreshPropertiesListWork(propertiesListEditor);
	}

}
