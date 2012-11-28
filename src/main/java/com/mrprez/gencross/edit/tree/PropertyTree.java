package com.mrprez.gencross.edit.tree;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.framework.Treatment;

public class PropertyTree extends JTree {
	private static final long serialVersionUID = 1L;
	private PersonnageNode top;
	private JPopupMenu popupMenu = new JPopupMenu();
	private JMenuItem editMenuItem = new JMenuItem("Edit");
	private JMenuItem addMenuItem = new JMenuItem("Add");
	

	public PropertyTree(Personnage personnage) throws HeadlessException, IOException{
		super(new PersonnageNode(personnage));
		ToolTipManager.sharedInstance().registerComponent(this);
		setBorder(BorderFactory.createLoweredBevelBorder());
		top = (PersonnageNode) this.getModel().getRoot();
		setRootVisible(false);
		addKeyListener(new Treatment(new EditPropertyListener(this)));
		popupMenu.add(editMenuItem);
		popupMenu.add(addMenuItem);
		editMenuItem.addActionListener(new Treatment(new EditPropertyListener(this)));
		addMenuItem.addActionListener(new Treatment(new AddPropertyListener()));
		addMouseListener(new DisplayPopupMenu(this));
	}
	
	public void refresh(){
		top.refresh();
	}
	
	public void expandAll(DefaultMutableTreeNode node){
		this.expandPath(new TreePath(node.getPath()));
		for(int i=0; i<node.getChildCount();i++){
			expandAll((DefaultMutableTreeNode)node.getChildAt(i));
		}
	}
	
	public void displayPopupMenu(int x, int y){
		editMenuItem.setEnabled(getSelectionPath()!=null);
		popupMenu.show(this, x, y);
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		TreePath treePath = this.getClosestPathForLocation(event.getX(), event.getY());
		if(treePath==null){
			return super.getToolTipText();
		}
		Property property = ((PropertyNode)treePath.getLastPathComponent()).getProperty();
		
		if(property.getComment()!=null){
			return property.getComment().length()<200?property.getComment():(property.getComment().substring(0, 200)+"...");
		}
		return super.getToolTipText();
	}
	
	public Personnage getPersonnage() {
		return top.getPersonnage();
	}
	
	public void setPersonnage(Personnage personnage){
		top.setPersonnage(personnage);
		top.refresh();
	}
	
	

}
