package com.mrprez.gencross.edit.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Treatment;
import com.mrprez.gencross.edit.framework.Work;

public class PropertyTreeMouseListener implements MouseListener {
	private PropertyTree propertyTree;
	
	
	public PropertyTreeMouseListener(PropertyTree propertyTree) {
		super();
		this.propertyTree = propertyTree;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getButton()==MouseEvent.BUTTON3){
			propertyTree.displayPopupMenu(event.getX(), event.getY());
		} else if(event.getButton()==MouseEvent.BUTTON1){
			if(propertyTree.getSelectionPath()!=null){
				Object selectedComponent = propertyTree.getSelectionPath().getLastPathComponent();
				if(selectedComponent instanceof AddNode){
					addProperty((AddNode) selectedComponent);
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	private void addProperty(AddNode addNode){
		new Treatment(new BackgroundWork() {
			
			@Override
			public Work getNextWork() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void doInBackground() throws Exception {
				
			}
		});
	}

}
