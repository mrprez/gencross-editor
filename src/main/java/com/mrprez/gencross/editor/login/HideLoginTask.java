package com.mrprez.gencross.editor.login;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class HideLoginTask implements EdtTask {
	private DisplayLoginTask displayLoginTask;
	
	
	public HideLoginTask(DisplayLoginTask displayLoginTask) {
		super();
		this.displayLoginTask = displayLoginTask;
	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		displayLoginTask.dispose();
	}

}
