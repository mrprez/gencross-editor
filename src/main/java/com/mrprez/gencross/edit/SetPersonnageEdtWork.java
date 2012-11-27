package com.mrprez.gencross.edit;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class SetPersonnageEdtWork implements EdtWork {
	public String personnageName;
	public Personnage personnage;
	public Work nextWork;
	

	public SetPersonnageEdtWork(String personnageName, Personnage personnage) {
		super();
		this.personnageName = personnageName;
		this.personnage = personnage;
	}
	
	
	@Override
	public void doInEdt() throws Exception {
		GenCrossEditor.getInstance().setPersonnageName(personnageName);
		GenCrossEditor.getInstance().setPersonnage(personnage);
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}
	
	public void setNextWork(Work nextWork){
		this.nextWork = nextWork;
	}

	



}
