package com.mrprez.gencross.editor.task;

import java.awt.Window;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.DialogTask;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayLoginTask extends DialogTask {
	private JLabel loginLabel = new JLabel("Login:");
	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JTextField loginField = new JTextField(15);
	private JTextField passwordField = new JTextField(15);
	
	

	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Window buildWindow() {
		return buildDialogBox();
	}

	private JDialog buildDialogBox(){
		JDialog dialog = new JDialog(GencrossEditor.getInstance(), "Authentification", true);
		GroupLayout layout = new GroupLayout(dialog.getRootPane());
		dialog.getRootPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(loginLabel)
				.addComponent(passwordLabel)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(loginField)
				.addComponent(passwordField)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(loginLabel)
				.addComponent(loginField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(passwordLabel)
				.addComponent(passwordField)
			)
			.addContainerGap()
		);
		return dialog;
	}
	

}
