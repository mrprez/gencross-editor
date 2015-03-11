package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingUtilities;


public class Treatment extends Thread {
	private Task firstTask;
	private static Boolean runningTreatment = Boolean.FALSE;
	private Component component;
	
	
	public Treatment(Task task, Component component) {
		super();
		this.firstTask = task;
		this.component = component;
	}


	
	@Override
	public void run() {
		try{
			synchronized (runningTreatment) {
				if(runningTreatment){
					System.out.println("Another treatment is running");
					return;
				}
				runningTreatment = true;
			}
			SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.WAIT_CURSOR));
			
			Task task = firstTask;
			
			while(task!=null){
				if(task instanceof EdtTask){
					SwingUtilities.invokeAndWait(new EdtRunnable((EdtTask) task));
				}else if(task instanceof BackgroundTask){
					((BackgroundTask)task).doInBackground();
				}
				task = task.getNextTask();
			}
		}catch(Exception e){
			ErrorFrame.displayError(e);
		}finally{
			synchronized (runningTreatment) {
				runningTreatment = Boolean.FALSE;
				SwingUtilities.invokeLater(new CursorRunnable(component, Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	
	
	
	
	
	

}
