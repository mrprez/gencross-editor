package com.mrprez.gencross.edit.framework;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingUtilities;

public class CursorUtilities implements Runnable{
	private Component component;
	private int predefinedCursor;
	
	
	public CursorUtilities(Component component, int predefinedCursor) {
		super();
		this.component = component;
		this.predefinedCursor = predefinedCursor;
	}

	public static void setWaitCursor(Component component){
		Runnable runnable = new CursorUtilities(component, Cursor.WAIT_CURSOR);
		SwingUtilities.invokeLater(runnable);
	}
	
	public static void setDefaultCursor(Component component){
		Runnable runnable = new CursorUtilities(component, Cursor.DEFAULT_CURSOR);
		SwingUtilities.invokeLater(runnable);
	}

	@Override
	public void run() {
		component.setCursor(Cursor.getPredefinedCursor(predefinedCursor));
	}

}
