package com.mrprez.gencross.edit.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPopupMenu implements MouseListener {
	private PropertyTree propertyTree;
	
	
	public DisplayPopupMenu(PropertyTree propertyTree) {
		super();
		this.propertyTree = propertyTree;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getButton()==MouseEvent.BUTTON3){
			propertyTree.displayPopupMenu(event.getX(), event.getY());
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

}
