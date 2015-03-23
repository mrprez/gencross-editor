package com.mrprez.gencross.editor.login;

import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.ActionTreatment;
import com.mrprez.gencross.editor.framework.ComponentTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.Treatment;

public class DisplayLoginTask extends JDialog implements ComponentTask {
	private static final long serialVersionUID = 1L;

	private JLabel loginLabel = new JLabel("Login:");
	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JTextField loginField = new JTextField(15);
	private JTextField passwordField = new JPasswordField(15);
	private JButton validateButton = new JButton("OK");
	private JButton cancelButton = new JButton("Annuler");
	
	

	public DisplayLoginTask(){
		super(GencrossEditor.getInstance(), "Authentification", false);
		GroupLayout layout = new GroupLayout(getRootPane());
		getRootPane().setLayout(layout);
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
				.addComponent(validateButton)
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
				.addComponent(validateButton)
				.addComponent(cancelButton)
			)
			.addContainerGap()
		);
		
		pack();
		
		Rectangle editorBounds = GencrossEditor.getInstance().getBounds();
		setLocation(editorBounds.x + (editorBounds.width-getWidth()) / 2, editorBounds.y + (editorBounds.height - getHeight())/2);
	}
	
	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public Window getComponent() throws Exception {
		setVisible(true);
		return this;
	}
	
	
	@Override
	public void setTreatment(Treatment treatment) {
		validateButton.addActionListener(new ActionTreatment(new ValidateLogin(this), this, treatment));
	}

	public String getLogin(){
		return loginField.getText();
	}
	
	public String getPassword(){
		return passwordField.getText();
	}

	

}
