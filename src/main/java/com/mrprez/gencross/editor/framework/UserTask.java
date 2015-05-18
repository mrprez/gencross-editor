package com.mrprez.gencross.editor.framework;

import java.awt.Cursor;
import java.awt.Window;

import javax.swing.SwingUtilities;


public final class UserTask implements Task {
	private Task nextTask;
	private boolean fired = false;
	private Window component;
	
	
	public UserTask(Window component) {
		super();
		this.component = component;
	}


	public synchronized void waitUser() throws InterruptedException{
		if( ! fired ){
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
			wait();
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
		}
	}
	
	
	public synchronized void fire(Task nextTask){
		fired = true;
		this.nextTask = nextTask;
		notify();
	}
	
	
	public synchronized void fire(){
		fired = true;
		notify();
	}
	
	
	public Task getNextTask() {
		return nextTask;
	}
	public void setNextTask(Task nextTask) {
		this.nextTask = nextTask;
	}
	public boolean isFired() {
		return fired;
	}
	public void setFired(boolean fired) {
		this.fired = fired;
	}
	public Window getComponent() {
		return component;
	}
	public void setComponent(Window component) {
		this.component = component;
	}
	

}
