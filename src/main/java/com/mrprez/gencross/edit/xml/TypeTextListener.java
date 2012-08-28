package com.mrprez.gencross.edit.xml;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mrprez.gencross.edit.GenCrossEditorPanel;

public class TypeTextListener implements KeyListener {
	private GenCrossEditorPanel panel;

	
	public TypeTextListener(GenCrossEditorPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		panel.setChanged(true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		panel.setChanged(true);
	}

}
