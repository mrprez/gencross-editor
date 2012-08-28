package com.mrprez.gencross.edit.dialog.propertiesList;

import javax.swing.JOptionPane;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.Work;

public class RemoveOptionWork implements BackgroundWork {
	private PropertiesListEditor propertiesListEditor;
	
	
	public RemoveOptionWork(PropertiesListEditor propertiesListEditor) {
		super();
		this.propertiesListEditor = propertiesListEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		int confirm = OptionPane.showConfirmDialog(propertiesListEditor, "Etes-vous sûr de vouloir supprimer ces options?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if(confirm==JOptionPane.YES_OPTION){
			Object optionsName[] = propertiesListEditor.getSelectedOptionsName();
			PropertiesList propertiesList = propertiesListEditor.getPropertiesList();
			for(int i=0; i<optionsName.length; i++){
				propertiesList.getOptions().remove(optionsName[i]);
			}
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new RefreshPropertiesListWork(propertiesListEditor);
	}

}
