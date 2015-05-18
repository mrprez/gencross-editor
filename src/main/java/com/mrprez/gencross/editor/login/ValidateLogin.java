package com.mrprez.gencross.editor.login;

import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.service.LoginService;

public class ValidateLogin implements BackgroundTask {
	private LoginService loginService = new LoginService();
	
	private DisplayLoginTask displayLoginTask;
	private boolean success;
	

	public ValidateLogin(DisplayLoginTask displayLoginTask) {
		super();
		this.displayLoginTask = displayLoginTask;
	}

	@Override
	public Task getNextTask() {
		if(success){
			return null;
		}else{
			return new LoginFail(displayLoginTask.getLogin(), displayLoginTask.getPassword());
		}
	}

	@Override
	public void doInBackground() throws Exception {
		success = loginService.authenticate(displayLoginTask.getLogin(), displayLoginTask.getPassword());
	}

	

}
