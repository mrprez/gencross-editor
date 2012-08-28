package com.mrprez.gencross.edit.tree;

import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class ReinitPointPoolPanelsWork implements EdtWork {
	private Work nextWork;
	
	
	@Override
	public void doInEdt() throws Exception {
		GenCrossEditor.getInstance().getTreePanel().reinitPointPoolPanels();
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}

	public void setNextWork(Work nextWork) {
		this.nextWork = nextWork;
	}
	
	

}
