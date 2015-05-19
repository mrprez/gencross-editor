package com.mrprez.gencross.editor.param;

import javax.swing.UIManager;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class EdtParamValidationTask implements EdtTask {
	private DisplayParamTask displayParamTask;
	
	
	public EdtParamValidationTask(DisplayParamTask displayParamTask) {
		super();
		this.displayParamTask = displayParamTask;
	}

	@Override
	public Task getNextTask() {
		return new BackgroundParamValidationTask(displayParamTask);
	}

	@Override
	public void doInEdt() throws Exception {
		UIManager.setLookAndFeel(displayParamTask.getSelectedLookAndFeelClassName());
	}

}
