package com.mrprez.gencross.editor.framework;

public class EdtRunnable implements Runnable {
	private EdtTask edtTask;

	
	public EdtRunnable(EdtTask edtTask) {
		super();
		this.edtTask = edtTask;
	}


	@Override
	public void run() {
		try {
			edtTask.doInEdt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
