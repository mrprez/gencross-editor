package com.mrprez.gencross.editor.framework.task;

import java.awt.Component;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class HideTask implements EdtTask {
	private Task nextTask;
	private Component component;
	
	
	public HideTask(Component component) {
		super();
		this.component = component;
	}

	public void setTask(Task nextTask){
		this.nextTask = nextTask;
	}
	
	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInEdt() throws Exception {
		component.setVisible(false);

	}

}
