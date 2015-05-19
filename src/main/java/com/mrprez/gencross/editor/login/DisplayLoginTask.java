package com.mrprez.gencross.editor.login;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.StandardDialog;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayLoginTask extends StandardDialog {
	private JLabel loginLabel = new JLabel("Login:");
	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JTextField loginField = new JTextField(15);
	private JTextField passwordField = new JPasswordField(15);
	
	
	public DisplayLoginTask(){
		super();
	}
	
	public DisplayLoginTask(String username, String password){
		super();
		loginField.setText(username);
		passwordField.setText(password);
	}

	public JDialog buildDialog() {
		JDialog dialog = new JDialog(GencrossEditor.getInstance(), "Authentification", false);
		GroupLayout layout = new GroupLayout(dialog.getRootPane());
		dialog.getRootPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
			.addGroup(layout.createSequentialGroup()
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
			)
			.addGroup(Alignment.CENTER, layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(okButton)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(cancelButton)
				.addContainerGap()
			)
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
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(okButton)
				.addComponent(cancelButton)
			)
			.addContainerGap()
		);
		
		return dialog;
	}
	
	
	public String getLogin(){
		return loginField.getText();
	}
	
	public String getPassword(){
		return passwordField.getText();
	}

	
	@Override
	public Task getValidTask() {
		return new ValidateLogin(this);
	}

	@Override
	public Task getCancelTask() {
		return null;
	}

	

}
