package com.mrprez.gencross.edit.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.util.IteratorEnumeration;

public class PersonnageNode implements TreeNode {
	private Personnage personnage;
	private ArrayList<PropertyNode> children = new ArrayList<PropertyNode>();
	

	
	public PersonnageNode(Personnage personnage) {
		super();
		setPersonnage(personnage);
	}

	@Override
	public Enumeration<PropertyNode> children() {
		refresh();
		return new IteratorEnumeration<PropertyNode>(children.iterator());
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		refresh();
		return children.get(arg0);
	}

	@Override
	public int getChildCount() {
		refresh();
		return children.size();
	}

	@Override
	public int getIndex(TreeNode arg0) {
		refresh();
		return children.indexOf(arg0);
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
	
	public void setPersonnage(Personnage personnage){
		this.personnage = personnage;
		update();
	}

	public Personnage getPersonnage() {
		return personnage;
	}
	
	public void refresh(){
		if(children.size()!=personnage.size()){
			update();
			return;
		}
		Iterator<Property> personnageIt = personnage.iterator();
		Iterator<PropertyNode> treeIt = children.iterator();
		
		while(personnageIt.hasNext() && treeIt.hasNext()){
			Property personnageProperty = personnageIt.next();
			Property treeProperty = treeIt.next().getProperty();
			if(personnageProperty != treeProperty){
				update();
				return;
			}
		}
		
		for(PropertyNode propertyNode : children){
			propertyNode.refresh();
		}
		
	}
	
	public void update(){
		children.clear();
		Iterator<Property> it = personnage.iterator();
		while(it.hasNext()){
			children.add(new PropertyNode(it.next(), this));
		}
	}
	
	

}
