package com.mrprez.gencross.edit.xml;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import com.mrprez.gencross.edit.error.ErrorFrame;

public class TypeTextKeyListener implements CaretListener {
	private JTextArea textArea;
	private JLabel positionLabel;
	
	
	public TypeTextKeyListener(JTextArea textArea, JLabel positionLabel) {
		super();
		this.textArea = textArea;
		this.positionLabel = positionLabel;
	}

	
	@Override
	public void caretUpdate(CaretEvent arg0) {
		try {
			int position = textArea.getCaretPosition();
			String beforeText = textArea.getText(0, position);
			String lines[] = beforeText.split("\n", -1);
			String lastLine = lines[lines.length - 1];
			positionLabel.setText("" + (lastLine.length()+1) + " : " + lines.length);
		} catch (BadLocationException e) {
			ErrorFrame.displayError(e);
		}
	}

}
