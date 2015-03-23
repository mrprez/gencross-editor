package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionTreatment implements ActionListener {
	
	private Task task;
	private Component component;
	private Treatment parentTreatment;
	
	
	public ActionTreatment(Task task, Component component){
		super();
		this.task = task;
		this.component = component;
	}
	
	public ActionTreatment(Task task, Component component, Treatment parentTreatment){
		super();
		this.task = task;
		this.component = component;
		this.parentTreatment = parentTreatment;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(parentTreatment!=null){
			parentTreatment.buildChildTreatment(task, component).start();
		}else{
			Treatment treatment = new Treatment(task, component);
			synchronized (treatment) {
				treatment.start();
			}
		}
	}

	

}
