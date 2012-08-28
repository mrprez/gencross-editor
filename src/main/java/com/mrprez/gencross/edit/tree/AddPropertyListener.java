package com.mrprez.gencross.edit.tree;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class AddPropertyListener implements BackgroundWork {
	private Work nextWork = new UpdateTreeWork();
	

	

	@Override
	public void doInBackground() throws Exception {
		Personnage personnage = GenCrossEditor.getInstance().getPersonnage();
		Property property = new Property("", personnage);
		PropertyEditor propertyEditor = new PropertyEditor(property, GenCrossEditor.getInstance());
		property = propertyEditor.edit();
		if(property!=null){
			personnage.addProperty(property);
			GenCrossEditor.getInstance().getTreePanel().setChanged(true);
		}
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}


}
