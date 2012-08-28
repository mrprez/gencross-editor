package com.mrprez.gencross.edit;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class NewPersonnageEdtWork implements EdtWork {
	public String title;
	public Personnage personnage;
	public String xmlSource;
	

	public NewPersonnageEdtWork(String title, Personnage personnage, String xmlSource) {
		super();
		this.title = title;
		this.personnage = personnage;
		this.xmlSource = xmlSource;
	}
	
	public NewPersonnageEdtWork(String title, String xmlSource) {
		super();
		this.title = title;
		this.xmlSource = xmlSource;
	}

	@Override
	public void doInEdt() throws Exception {
		GenCrossEditor.getInstance().setTitle(title);
		GenCrossEditor.getInstance().setXmlSource(xmlSource);
		if(personnage!=null){
			GenCrossEditor.getInstance().setPersonnage(personnage);
			GenCrossEditor.getInstance().setTabbedPanEnability(0, true);
		}else{
			GenCrossEditor.getInstance().setTabbedPanEnability(0, false);
		}

	}

	@Override
	public Work getNextWork() {
		return null;
	}

	



}
