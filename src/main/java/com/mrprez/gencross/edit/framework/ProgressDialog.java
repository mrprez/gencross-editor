package com.mrprez.gencross.edit.framework;

import java.awt.Dialog;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;

import com.mrprez.gencross.edit.GenCrossEditor;

public class ProgressDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JProgressBar progressBar = new JProgressBar();
	private JButton okButton = new JButton("Ok");
	
	public ProgressDialog(){
		super(GenCrossEditor.getInstance(), Dialog.ModalityType.APPLICATION_MODAL);
		okButton.setEnabled(false);
		okButton.addActionListener(new CloseListener(this));
		
		GroupLayout layout = new GroupLayout(getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(progressBar)
				.addComponent(okButton)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(progressBar)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(okButton)
			.addContainerGap()
		);
		pack();
	}
	
	public void setValue(int value){
		if(value==100){
			okButton.setEnabled(true);
		}
		progressBar.setValue(value);
	}

}
