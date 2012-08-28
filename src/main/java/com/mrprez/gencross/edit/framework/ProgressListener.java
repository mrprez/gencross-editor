package com.mrprez.gencross.edit.framework;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.mrprez.gencross.edit.GenCrossEditor;

public class ProgressListener implements PropertyChangeListener {
	private ProgressDialog progressDialog = null;
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if("progress".equals(event.getPropertyName())) {
			if(progressDialog==null){
				progressDialog = new ProgressDialog();
				progressDialog.pack();
				progressDialog.setLocation(GenCrossEditor.getInstance().getSize().width/2-progressDialog.getSize().width/2+GenCrossEditor.getInstance().getLocation().x,
						GenCrossEditor.getInstance().getSize().height/2-progressDialog.getSize().height/2+GenCrossEditor.getInstance().getLocation().y);
				progressDialog.setVisible(true);
			}
			progressDialog.setValue((Integer) event.getNewValue());
        }
		
	}

}
