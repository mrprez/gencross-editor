package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingUtilities;


public class Treatment extends Thread {
	private Task firstTask;
	private static Treatment runningTreatment;
	private Component component;
	private Treatment parentTreatment;
	private boolean pause = false;
	
	
	public Treatment(Task task, Component component) {
		super();
		this.firstTask = task;
		this.component = component;
	}
	
	
	public Treatment buildChildTreatment(Task task, Component component){
		Treatment childTreatment = new Treatment(task, component);
		childTreatment.parentTreatment = this;
		return childTreatment;
	}
	
	
	public synchronized void lauchChildTreatment(Task task, Component component) throws InterruptedException{
		Treatment childTreatment = buildChildTreatment(task, component);
		childTreatment.start();
		this.wait();
	}

	
	public synchronized void lauchChildTreatment(Task task) throws InterruptedException{
		lauchChildTreatment(task, component);
	}
	
	private boolean isParentTreatment(Treatment potentialParent){
		if(parentTreatment==null){
			return false;
		}
		if(parentTreatment==potentialParent){
			return true;
		}
		return parentTreatment.isParentTreatment(potentialParent);
	}

	
	@Override
	public void run() {
		synchronized (Treatment.class) {
			if(runningTreatment!=null && ( ! isParentTreatment(runningTreatment) )){
				System.out.println("Another treatment is running");
				return;
			}
			runningTreatment = this;
		}
		try{
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
			
			Task task = firstTask;
			
			while(task!=null){
				if(task instanceof TreatmentAwareTask){
					((TreatmentAwareTask)task).setTreatment(this);
				}
				if(task instanceof EdtTask){
					SwingUtilities.invokeAndWait(new EdtRunnable((EdtTask) task));
				}else if(task instanceof BackgroundTask){
					((BackgroundTask)task).doInBackground();
				}else if(task instanceof ComponentTask){
					Component component = new ComponentRunnable((ComponentTask) task).getComponent();
					SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
					pause();
					SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
				}
				task = task.getNextTask();
			}
			
			if(parentTreatment!=null){
				parentTreatment.notify();
			}
			
		}catch(Exception e){
			ErrorFrame.displayError(e);
		}finally{
			synchronized (Treatment.class) {
				runningTreatment = null;
				SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	public synchronized void pause() throws InterruptedException{
		pause = true;
		wait();
	}
	
	
	public synchronized void doContinue(){
		notify();
		pause = false;
	}
	
	
	
	
	
	private class ComponentRunnable implements Runnable{
		private Component component;
		private ComponentTask componentTask;
		private Exception exception;
		
		public ComponentRunnable(ComponentTask componentTask) {
			super();
			this.componentTask = componentTask;
		}
		
		@Override
		public void run() {
			try {
				component = componentTask.getComponent();
			} catch (Exception e) {
				exception = e;
			}
		}
		public Component getComponent() throws Exception{
			SwingUtilities.invokeAndWait(this);
			if(exception!=null){
				throw exception;
			}
			return component;
		}
	}
	

}
