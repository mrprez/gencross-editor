package com.mrprez.gencross.edit.framework;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CloseEditDialogListener implements WindowListener {
	private EditDialog<?> editDialog;
	
	
	public CloseEditDialogListener(EditDialog<?> editDialog) {
		super();
		this.editDialog = editDialog;
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		synchronized(editDialog){
			editDialog.result = null;
			editDialog.notify();
		}
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// Auto-generated method stub
		
	}

	

}
