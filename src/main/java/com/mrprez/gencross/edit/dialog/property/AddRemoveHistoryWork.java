package com.mrprez.gencross.edit.dialog.property;

import javax.swing.JOptionPane;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.history.HistoryItemEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.Work;
import com.mrprez.gencross.history.HistoryItem;

public class AddRemoveHistoryWork implements BackgroundWork {
	private PropertyEditor propertyEditor;
	
	
	public AddRemoveHistoryWork(PropertyEditor propertyEditor) {
		super();
		this.propertyEditor = propertyEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		Property property = propertyEditor.getProperty();
		if(property.getActualDefaultHistory()!=null){
			removeHistory();
		}else{
			addHistory();
		}
	}
	
	private void addHistory() throws Exception {
		HistoryItem historyItem = HistoryItem.FREE_HISTORY_ITEM.clone();
		HistoryItemEditor historyItemEditor = new HistoryItemEditor(propertyEditor, historyItem);
		historyItem = historyItemEditor.edit();
		if(historyItem!=null){
			historyItem.setOldValue(null);
			historyItem.setNewValue(null);
			historyItem.setAction(0);
			historyItem.setPhase(null);
			historyItem.setAbsoluteName(null);
			propertyEditor.getProperty().setDefaultHistory(historyItem);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}
	
	private void removeHistory() throws Exception {
		Property property = propertyEditor.getProperty();
		int confirm = OptionPane.showConfirmDialog(propertyEditor, "Etes-vous sûr de vouloir supprimer la liste de sous propriétés?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if(confirm==JOptionPane.YES_OPTION){
			property.removeSubProperties();
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new PropertyEditorFieldEnabilityWork(propertyEditor);
	}

}
