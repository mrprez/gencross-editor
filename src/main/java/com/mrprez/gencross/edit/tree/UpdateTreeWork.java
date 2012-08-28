package com.mrprez.gencross.edit.tree;

import javax.swing.SwingUtilities;

import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class UpdateTreeWork implements EdtWork {
	private Work nextWork;
	

	@Override
	public void doInEdt() throws Exception {
		SwingUtilities.updateComponentTreeUI(GenCrossEditor.getInstance());
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}

	public void setNextWork(Work nextWork) {
		this.nextWork = nextWork;
	}

}
