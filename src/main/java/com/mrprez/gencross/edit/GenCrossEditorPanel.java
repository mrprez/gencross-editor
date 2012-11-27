package com.mrprez.gencross.edit;

import javax.swing.JPanel;

import com.mrprez.gencross.Personnage;

public abstract class GenCrossEditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean changed = false;


	public abstract void refresh() throws Exception;
	
	public abstract Personnage impact() throws Exception;
	
	public abstract boolean isDataValid();
	
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	


}
