package com.mrprez.gencross.edit.dialog.property;

import javax.swing.JOptionPane;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.propertiesList.PropertiesListEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.Work;

public class AddRemoveSubPropertiesWork implements BackgroundWork {
	private PropertyEditor propertyEditor;
	private Property property;
	private Work nextWork;
	

	public AddRemoveSubPropertiesWork(PropertyEditor propertyEditor, Property property) {
		super();
		this.propertyEditor = propertyEditor;
		this.property = property;
		nextWork = new PropertyEditorFieldEnabilityWork(propertyEditor);
	}

	
	public void addSubProperties() throws Exception{
		PropertiesList propertiesList = new PropertiesList(property);
		PropertiesListEditor propertiesListEditor = new PropertiesListEditor(propertiesList, propertyEditor);
		propertiesList = propertiesListEditor.edit();
		if(propertiesList!=null){
			property.setSubProperties(propertiesList);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}
	
	public void removeSubProperties(){
		int confirm = OptionPane.showConfirmDialog(propertyEditor, "Etes-vous sûr de vouloir supprimer la liste de sous propriétés?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if(confirm==JOptionPane.YES_OPTION){
			property.removeSubProperties();
		}
	}

	@Override
	public void doInBackground() throws Exception {
		if(property.getSubProperties()==null){
			addSubProperties();
		}else{
			removeSubProperties();
		}
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}


}
