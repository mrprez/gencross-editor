package com.mrprez.gencross.edit;

import java.util.Collection;

import javax.swing.JPanel;

import com.mrprez.gencross.Personnage;

public abstract class GenCrossEditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean changed = false;


	public abstract void refresh() throws Exception;
	
	public abstract Personnage impact() throws Exception;
	
	public abstract Collection<String> getDataErrors();
	
	public boolean isDataValid(){
		Collection<String> errors = getDataErrors();
		return errors == null || errors.isEmpty();
	}
	
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	


}
