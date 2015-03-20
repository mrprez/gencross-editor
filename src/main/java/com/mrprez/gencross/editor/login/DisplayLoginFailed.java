package com.mrprez.gencross.editor.login;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayLoginFailed implements EdtTask {
	private Component parentComponent;
	
	
	
	public DisplayLoginFailed(Component parentComponent) {
		super();
		this.parentComponent = parentComponent;
	}

	
	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		JOptionPane.showMessageDialog(parentComponent, "L'authentification a échoué. Vérifiez vos login et mot de passe.", "Authentification échouée", JOptionPane.ERROR_MESSAGE);
	}

}
