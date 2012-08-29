package com.mrprez.gencross.edit.dialog.propertiesList;

import javax.swing.JOptionPane;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.Work;

public class AddRemoveDefautPropertyWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public AddRemoveDefautPropertyWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
		if(propertiesList.getDefaultProperty()==null){
			addDefaultProperty();
		}else{
			removeDefaultProperty();
		}
		
	}
	
	private void addDefaultProperty() throws Exception{
		PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
		Property defaultProperty = new Property("Default Property", propertiesList.getOwner());
		PropertyEditor propertyEditor = new PropertyEditor(defaultProperty, propertiesListEditor);
		defaultProperty = propertyEditor.edit();
		if(defaultProperty!=null){
			propertiesList.setDefaultProperty(defaultProperty);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}
	
	private void removeDefaultProperty(){
		int confirm = OptionPane.showConfirmDialog(propertiesListEditor, "Etes-vous sûr de vouloir supprimer la propriété par défaut?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if(confirm==JOptionPane.YES_OPTION){
			PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
			propertiesList.setDefaultProperty(null);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new PropertiesListEditorFieldEnabilityWork(propertiesListEditor);
	}

}
