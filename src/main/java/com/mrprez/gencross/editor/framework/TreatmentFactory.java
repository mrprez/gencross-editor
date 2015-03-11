package com.mrprez.gencross.editor.framework;

import java.awt.Component;

public class TreatmentFactory {
	
	
	public static Treatment buildSimpleTreatment(Component component, SimpleTask... simpleTasks){
		for(int i=1; i<simpleTasks.length; i++){
			simpleTasks[i-1].setNextTask(simpleTasks[i]);
		}
		return new Treatment(simpleTasks[0], component);
	}

}
