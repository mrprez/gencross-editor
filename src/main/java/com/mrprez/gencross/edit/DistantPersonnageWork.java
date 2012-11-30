package com.mrprez.gencross.edit;

import com.mrprez.gencross.edit.dialog.distant.DistantDialog;
import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class DistantPersonnageWork implements EdtWork {

	@Override
	public Work getNextWork() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		DistantDialog.getInstance().setVisible(true);
	}
	
	

}
