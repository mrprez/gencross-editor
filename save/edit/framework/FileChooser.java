package com.mrprez.gencross.edit.framework;

import java.awt.Component;
import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class FileChooser extends JFileChooser implements Runnable {
	private static final long serialVersionUID = 1L;
	private Component parent;
	private String approveButtonText;
	private int result;
	private int dialogType;
	
	
	@Override
	public void run() {
		switch(dialogType){
		case OPEN_DIALOG:
			result = super.showOpenDialog(parent);
			break;
		case SAVE_DIALOG:
			result = super.showSaveDialog(parent);
			break;
		default:
			result = super.showDialog(parent, approveButtonText);
		}
	}

	@Override
	public int showDialog(Component parent, String approveButtonText) throws HeadlessException {
		try {
			if(SwingUtilities.isEventDispatchThread()){
				return super.showDialog(parent, approveButtonText);
			}
			this.parent = parent;
			this.approveButtonText = approveButtonText;
			dialogType = CUSTOM_DIALOG;
			SwingUtilities.invokeAndWait(this);
			return result;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return JFileChooser.CANCEL_OPTION;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return JFileChooser.CANCEL_OPTION;
		}
	}
	
	@Override
	public int showOpenDialog(Component parent) throws HeadlessException {
		try{
			this.parent = parent;
			dialogType = OPEN_DIALOG;
			SwingUtilities.invokeAndWait(this);
			return result;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return JFileChooser.CANCEL_OPTION;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return JFileChooser.CANCEL_OPTION;
		}
	}
	
	

}
