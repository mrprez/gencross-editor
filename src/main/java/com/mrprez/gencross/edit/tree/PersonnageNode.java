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
	private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	

	
	public PersonnageNode(Personnage personnage) {
		super();
		setPersonnage(personnage);
	}

	@Override
	public Enumeration<TreeNode> children() {
		refresh();
		return new IteratorEnumeration<TreeNode>(children.iterator());
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
		if(children.size()!=personnage.size() + 1){
			update();
			return;
		}
		Iterator<Property> personnageIt = personnage.iterator();
		Iterator<TreeNode> treeIt = children.iterator();
		
		while(personnageIt.hasNext() && treeIt.hasNext()){
			Property personnageProperty = personnageIt.next();
			Property treeProperty = ((PropertyNode)treeIt.next()).getProperty();
			if(personnageProperty != treeProperty){
				update();
				return;
			}
		}
		
		for(TreeNode treeNode : children){
			if(treeNode instanceof PropertyNode){
				((PropertyNode)treeNode).refresh();
			}
		}
		
	}
	
	public void update(){
		children.clear();
		Iterator<Property> it = personnage.iterator();
		while(it.hasNext()){
			children.add(new PropertyNode(it.next(), this));
		}
		children.add(new AddNode());
	}
	
	

}
