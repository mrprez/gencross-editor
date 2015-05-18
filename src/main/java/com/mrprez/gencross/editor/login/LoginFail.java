package com.mrprez.gencross.editor.login;

import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class LoginFail implements EdtTask {
	private String username;
	private String password;
	
	
	

	public LoginFail(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public Task getNextTask() {
		return new DisplayLoginTask(username, password);
	}

	@Override
	public void doInEdt() throws Exception {
		JOptionPane.showMessageDialog(null, "L'authentification a échoué", "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);

	}

}
