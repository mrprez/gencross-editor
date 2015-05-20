package com.mrprez.gencross.editor.commons;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class SetTextModifiedTask implements EdtTask {
	private boolean textModified;
	
	
	
	public SetTextModifiedTask(boolean textModified) {
		super();
		this.textModified = textModified;
	}
	

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		if(GencrossEditor.getInstance().isTextModified() != textModified){
			GencrossEditor.getInstance().setTextModified(textModified);
		}
	}

}
