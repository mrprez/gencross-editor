package com.mrprez.gencross.edit;

import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class SetXmlEdtWork implements EdtWork {
	private String xml;
	private String personnageName;
	
	
	public SetXmlEdtWork(String personnageName,String xml) {
		super();
		this.personnageName = personnageName;
		this.xml = xml;
	}

	@Override
	public Work getNextWork() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		if(xml != null){
			GenCrossEditor.getInstance().setPersonnageName(personnageName);
			GenCrossEditor.getInstance().loadXml(xml);
		}
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getPersonnageName() {
		return personnageName;
	}

	public void setPersonnageName(String personnageName) {
		this.personnageName = personnageName;
	}
	
	

}