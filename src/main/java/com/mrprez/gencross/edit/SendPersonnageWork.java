package com.mrprez.gencross.edit;

import com.mrprez.gencross.edit.dialog.distant.WebServiceClient;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class SendPersonnageWork implements BackgroundWork {

	@Override
	public Work getNextWork() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doInBackground() throws Exception {
		WebServiceClient webServiceClient = new WebServiceClient(GenCrossEditor.getServerAddress());
		if(GenCrossEditor.getInstance().isValidXml()){
			webServiceClient.savePersonnage(GenCrossEditor.getPersonnageId(), GenCrossEditor.getInstance().getXml().getBytes("UTF-8"));
		}
	}

}
