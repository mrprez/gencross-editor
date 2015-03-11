package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.Cursor;

public class CursorRunnable implements Runnable {
	private Component component;
	private Cursor cursor;
	

	public CursorRunnable(Component component, Cursor cursor) {
		super();
		this.component = component;
		this.cursor = cursor;
	}
	
	public CursorRunnable(Component component, int cursorType) {
		super();
		this.component = component;
		this.cursor = Cursor.getPredefinedCursor(cursorType);
	}


	@Override
	public void run() {
		component.setCursor(cursor);
	}

}
