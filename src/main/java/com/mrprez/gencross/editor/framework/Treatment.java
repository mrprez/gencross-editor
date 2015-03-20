package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingUtilities;


public class Treatment extends Thread {
	private Task firstTask;
	private static Treatment runningTreatment;
	private Component component;
	private Treatment parentTreatment;
	private boolean interrupt = false;
	
	
	public Treatment(Task task, Component component) {
		super();
		this.firstTask = task;
		this.component = component;
	}
	
	
	private Treatment buildChildTreatment(Task task, Component component){
		Treatment childTreatment = new Treatment(task, component);
		childTreatment.parentTreatment = this;
		return childTreatment;
	}
	
	
	public synchronized void lauchChildTreatment(Task task, Component component) {
		Treatment childTreatment = buildChildTreatment(task, component);
		try{
			childTreatment.start();
			wait();
		}catch(InterruptedException ie){
			interruptTreatment();
			ErrorFrame.displayError(ie);
		}
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
				ErrorFrame.displayError(new Exception("Un autre traitement est en cours"));
			}
			runningTreatment = this;
		}
		try{
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
			
			Task task = firstTask;
			
			while(task!=null && interrupt==false){
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
					wait();
					SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
				}
				task = task.getNextTask();
			}
			
			if(parentTreatment!=null){
				synchronized (parentTreatment) {
					parentTreatment.notify();
					runningTreatment = parentTreatment;
				}
			}else{
				runningTreatment = null;
			}
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
			
		}catch(Exception e){
			interruptTreatment();
			ErrorFrame.displayError(e);
		}
	}
	
	
	private synchronized void interruptTreatment(){
		interrupt = true;
		notify();
		if(parentTreatment!=null){
			parentTreatment.interruptTreatment();
		}
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
