package com.mrprez.gencross.edit;

import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;
import com.mrprez.gencross.edit.xml.XmlPanel;

public class SetInvalidXmlEdtWork implements EdtWork {
	private String xml;
	private String personnageName;
	private Work nextWork;
	
		
	public SetInvalidXmlEdtWork(String xml, String personnageName) {
		super();
		this.xml = xml;
		this.personnageName = personnageName;
	}

	@Override
	public void doInEdt() throws Exception {
		GenCrossEditor.getInstance().setPersonnageName(personnageName);
		XmlPanel xmlPanel = GenCrossEditor.getInstance().getXmlPanel();
		xmlPanel.setText(xml);
		GenCrossEditor.getInstance().getTabbedPane().setSelectedComponent(xmlPanel);
	}
	
	@Override
	public Work getNextWork() {
		return nextWork;
	}

	public void setNextWork(Work nextWork) {
		this.nextWork = nextWork;
	}

	

}
