package com.mrprez.gencross.edit.dialog.property;

import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.history.HistoryItemEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;
import com.mrprez.gencross.history.HistoryItem;

public class EditDefaultHistoryWork implements BackgroundWork {
	private PropertyEditor propertyEditor;
	
	
	public EditDefaultHistoryWork(PropertyEditor propertyEditor) {
		super();
		this.propertyEditor = propertyEditor;
	}

	@Override
	public void doInBackground() throws Exception {
		HistoryItem defaultHistory = propertyEditor.getProperty().getActualDefaultHistory();
		HistoryItemEditor historyItemEditor = new HistoryItemEditor(propertyEditor, defaultHistory);
		HistoryItem newDefaultHistory = historyItemEditor.edit();
		if(newDefaultHistory!=null){
			newDefaultHistory.setOldValue(null);
			newDefaultHistory.setNewValue(null);
			newDefaultHistory.setAction(0);
			newDefaultHistory.setPhase(null);
			newDefaultHistory.setAbsoluteName(null);
			propertyEditor.getProperty().setDefaultHistory(newDefaultHistory);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return new PropertyEditorFieldEnabilityWork(propertyEditor);
	}

}
