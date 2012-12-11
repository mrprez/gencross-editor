package com.mrprez.gencross.edit.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.util.IteratorEnumeration;

public class PropertyNode implements TreeNode {
	private Property property;
	private TreeNode parent;
	private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	
	
	public PropertyNode(Property property, TreeNode parent) {
		super();
		this.property = property;
		update();
	}

	@Override
	public Enumeration<TreeNode> children() {
		refresh();
		return new IteratorEnumeration<TreeNode>(children.iterator());
	}

	@Override
	public boolean getAllowsChildren() {
		return property.getSubProperties()!=null;
	}

	@Override
	public TreeNode getChildAt(int index) {
		refresh();
		return children.get(index);
	}

	@Override
	public int getChildCount() {
		if(property.getSubProperties()==null){
			return 0;
		}
		return property.getSubProperties().size() + 1;
	}

	@Override
	public int getIndex(TreeNode treeNode) {
		refresh();
		return children.indexOf(treeNode);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		refresh();
		return children.isEmpty();
	}

	@Override
	public String toString() {
		return property.getFullName();
	}
	
	public Property getProperty(){
		return property;
	}

	public void update(){
		children.clear();
		if(property.getSubProperties()!=null){
			for(Property subProperty : property.getSubProperties()){
				children.add(new PropertyNode(subProperty, this));
			}
			children.add(new AddNode());
		}
	}
	
	public void refresh(){
		if(property.getSubProperties()==null){
			if(!children.isEmpty()){
				update();
			}
			return;
		}
		if(children.size()-1!=property.getSubProperties().size()){
			update();
			return;
		}
		Iterator<Property> subPropertyIt = property.getSubProperties().iterator();
		Iterator<TreeNode> treeIt = children.iterator();
		while(subPropertyIt.hasNext()){
			Property subProperty = subPropertyIt.next();
			Property treeProperty = ((PropertyNode)treeIt.next()).getProperty();
			if(subProperty != treeProperty){
				update();
				return;
			}
		}
		for(TreeNode childNode : children){
			if(childNode instanceof PropertyNode){
				((PropertyNode)childNode).refresh();
			}
		}
	}
	

}
