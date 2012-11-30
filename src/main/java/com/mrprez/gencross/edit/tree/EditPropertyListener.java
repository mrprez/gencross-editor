package com.mrprez.gencross.edit.tree;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.dialog.property.PropertyEditor;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;
import com.mrprez.gencross.renderer.Renderer;


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
			Property oldProperty = node.getProperty();
			PropertyEditor propertyEditor = new PropertyEditor(oldProperty, GenCrossEditor.getInstance());
			propertyEditor.setFullNameEnability(false);
			Property resultProperty = propertyEditor.edit();
			if(resultProperty!=null){
				GenCrossEditor.getInstance().getTreePanel().setChanged(true);
				oldProperty.setComment(resultProperty.getComment());
				oldProperty.setDefaultHistory(resultProperty.getDefaultHistory());
				oldProperty.setEditable(resultProperty.isEditable());
				oldProperty.setMax(resultProperty.getMax());
				oldProperty.setMin(resultProperty.getMin());
				oldProperty.setOptions(resultProperty.getOptions());
				oldProperty.setRemovable(resultProperty.isRemovable());
				oldProperty.setSpecification(resultProperty.getSpecification());
				oldProperty.setSubProperties(resultProperty.getSubProperties());
				oldProperty.setValue(resultProperty.getValue());
				if(resultProperty.getRenderer() != Renderer.DEFAULT_RENDERER){
					oldProperty.setRenderer(resultProperty.getRenderer());
				}else{
					oldProperty.setRenderer(null);
				}
			}
		}
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}


}
