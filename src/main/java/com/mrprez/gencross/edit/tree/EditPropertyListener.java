package com.mrprez.gencross.edit.tree;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;


public class EditPropertyListener implements BackgroundWork {
	private PropertyTree propertyTree;
	private Work nextWork = new UpdateTreeWork();
	
	
	public EditPropertyListener(PropertyTree propertyTree){
		super();
		this.propertyTree = propertyTree;
	}
	

	@Override
	public void doInBackground() throws Exception {
		if(propertyTree.getSelectionPath()!=null){
			PropertyNode node = (PropertyNode) propertyTree.getSelectionPath().getLastPathComponent();
			PropertyEditor propertyEditor = new PropertyEditor(node.getProperty(), GenCrossEditor.getInstance());
			Property result = propertyEditor.edit();
			if(result!=null){
				GenCrossEditor.getInstance().getTreePanel().setChanged(true);
			}
		}
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}

	




	

	

}
