package com.mrprez.gencross.edit.framework;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import com.mrprez.gencross.edit.error.ErrorFrame;

public abstract class EditDialog<T> extends JDialog implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private T result;
	
	
	public EditDialog(Dialog arg0) {
		super(arg0);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		addWindowListener(new CloseEditDialogListener(this));
	}

	public EditDialog(Frame arg0) {
		super(arg0);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		addWindowListener(new CloseEditDialogListener(this));
	}

	public synchronized T edit() throws Exception{
		if(SwingUtilities.isEventDispatchThread()){
			throw new Exception("\"edit\" method cannot be called in EDT");
		}
		SwingUtilities.invokeLater(this);
		result = null;
		wait();
		return result;
	}
	
	public synchronized void validateData(){
		dispose();
		result = getResult();
	}
	
	public abstract T getResult();
	
	public abstract void init() throws Exception;

	@Override
	public void run() {
		try {
			init();
			pack();
			int x = getOwner().getX()+(getOwner().getWidth()-getWidth())/2;
			int y = getOwner().getY()+(getOwner().getHeight()-getHeight())/2;
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle desktop = ge.getMaximumWindowBounds();
			if(x+getWidth()>desktop.getWidth()){
				x = (int) (desktop.getWidth() - getWidth());
			}
			if(y+getHeight()>desktop.getHeight()){
				y = (int) (desktop.getHeight() - getHeight());
			}
			if(x<0){
				x = 0;
			}
			if(y<0){
				y = 0;
			}
			setLocation(x, y);
			setVisible(true);
		} catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
