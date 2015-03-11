package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionTreatment implements ActionListener {
	
	private Treatment treatment;
	
	public ActionTreatment(Treatment treatment){
		super();
		this.treatment = treatment;
	}
	
	public ActionTreatment(Task task, Component component){
		super();
		this.treatment = new Treatment(task, component);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		treatment.start();
	}

	

}
