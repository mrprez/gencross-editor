package com.mrprez.gencross.editor.login;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayLoginTask implements EdtTask {
	private JLabel loginLabel = new JLabel("Login:");
	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JTextField loginField = new JTextField(15);
	private JTextField passwordField = new JTextField(15);
	private JButton validateButton = new JButton("OK");
	private JButton cancelButton = new JButton("Annuler");
	
	

	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		JDialog dialog = buildDialogBox();
		dialog.setVisible(true);
		
	}
	
	private JDialog buildDialogBox(){
		JDialog dialog = new JDialog(GencrossEditor.getInstance(), "Authentification", true);
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
		
		validateButton.addActionListener(});
		
		dialog.pack();
		
		Rectangle editorBounds = GencrossEditor.getInstance().getBounds();
		dialog.setLocation(editorBounds.x + (editorBounds.width-dialog.getWidth()) / 2, editorBounds.y + (editorBounds.height - dialog.getHeight())/2);
		
		return dialog;
	}

	
	

}
