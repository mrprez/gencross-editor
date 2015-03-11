package com.mrprez.gencross.editor.framework;

public abstract class SimpleTask implements Task {
	private Task nextTask;

	
	@Override
	public Task getNextTask() {
		return nextTask;
	}


	public void setNextTask(Task nextTask) {
		this.nextTask = nextTask;
	}
	

}
