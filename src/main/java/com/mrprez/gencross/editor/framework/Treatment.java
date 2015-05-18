package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingUtilities;


public class Treatment extends Thread {
	private static final String THREAD_NAME = "Treatment Thread";
	
	private static Treatment runningTreatment;
	
	private Task firstTask;
	private Component component;
	private Treatment parentTreatment;
	private boolean interrupt = false;
	
	
	public Treatment(Task task, Component component) {
		super();
		this.firstTask = task;
		this.component = component;
		super.setName(THREAD_NAME);
	}
	
	
	protected Treatment buildChildTreatment(Task task, Component component){
		Treatment childTreatment = new Treatment(task, component);
		childTreatment.parentTreatment = this;
		return childTreatment;
	}
	
	
	public synchronized void lauchChildTreatment(Task task, Component component) {
		Treatment childTreatment = buildChildTreatment(task, component);
		if( ! Thread.currentThread().getName().equals(THREAD_NAME)){
			throw new RuntimeException("Child treatment cannot be lauch from no treatment thread");
		}
		try{
			childTreatment.start();
			System.out.println(this.getId()+" - wait child treatment "+childTreatment.getId());
			wait();
			System.out.println(this.getId()+" - restart after child treatment "+childTreatment.getId());
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
				ErrorFrame.displayError(new Exception(firstTask.getClass().getSimpleName()+": Un autre traitement est en cours"));
				return;
			}
			runningTreatment = this;
			System.out.println("Start treatment: "+this.getId());
		}
		try{
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
			
			Task task = firstTask;
			
			while(task!=null && interrupt==false){
				System.out.println(this.getId()+" - Start Task: "+task.getClass().getSimpleName());
				if(task instanceof TreatmentAwareTask){
					((TreatmentAwareTask)task).setTreatment(this);
				}
				if(task instanceof EdtTask){
					EdtRunnable edtRunnable = new EdtRunnable((EdtTask) task);
					SwingUtilities.invokeAndWait(edtRunnable);
					if(edtRunnable.getException() != null){
						throw edtRunnable.getException();
					}
				}else if(task instanceof BackgroundTask){
					((BackgroundTask)task).doInBackground();
				}else if(task instanceof UserTask){
					((UserTask) task).waitUser();
				}
				System.out.println(this.getId()+" - End Task: "+task.getClass().getSimpleName());
				task = task.getNextTask();
			}
			
			System.out.println("End treatment: "+this.getId());
			if(parentTreatment!=null){
				synchronized (parentTreatment) {
					System.out.println(parentTreatment.getId()+" - notify");
					parentTreatment.notify();
					runningTreatment = parentTreatment;
				}
			}else{
				runningTreatment = null;
			}
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
			
		}catch(Exception e){
			interruptTreatment();
			runningTreatment = null;
			ErrorFrame.displayError(e);
		}
	}
	
	
	private synchronized void interruptTreatment(){
		interrupt = true;
		notify();
		if(parentTreatment!=null){
			parentTreatment.interruptTreatment();
		}
		SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
	}
	
	

}
