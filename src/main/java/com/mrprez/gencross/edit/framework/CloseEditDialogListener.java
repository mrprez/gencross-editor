package com.mrprez.gencross.edit.framework;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CloseEditDialogListener implements WindowListener {
	private Window window;
	
	
	public CloseEditDialogListener(Window window) {
		super();
		this.window = window;
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
		synchronized(window){
			window.notify();
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
