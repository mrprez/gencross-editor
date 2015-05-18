package com.mrprez.gencross.editor.framework;

public class EdtRunnable implements Runnable {
	private EdtTask edtTask;
	
	private Exception exception;

	
	public EdtRunnable(EdtTask edtTask) {
		super();
		this.edtTask = edtTask;
	}


	@Override
	public void run() {
		try {
			edtTask.doInEdt();
		} catch (Exception e) {
			exception = e;
		}
	}


	public Exception getException() {
		return exception;
	}
	
	
	
	

}
