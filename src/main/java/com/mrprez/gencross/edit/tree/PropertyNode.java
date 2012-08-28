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
	private ArrayList<PropertyNode> children = new ArrayList<PropertyNode>();
	
	
	public PropertyNode(Property property, TreeNode parent) {
		super();
		this.property = property;
		update();
	}

	@Override
	public Enumeration<PropertyNode> children() {
		refresh();
		return new IteratorEnumeration<PropertyNode>(children.iterator());
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
		return property.getSubProperties().size();
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
		}
	}
	
	public void refresh(){
		if(property.getSubProperties()==null){
			if(!children.isEmpty()){
				update();
			}
			return;
		}
		if(children.size()!=property.getSubProperties().size()){
			update();
			return;
		}
		Iterator<Property> subPropertyIt = property.getSubProperties().iterator();
		Iterator<PropertyNode> treeIt = children.iterator();
		while(subPropertyIt.hasNext()){
			Property subProperty = subPropertyIt.next();
			Property treeProperty = treeIt.next().getProperty();
			if(subProperty != treeProperty){
				update();
				return;
			}
		}
		for(PropertyNode propertyNode : children){
			propertyNode.refresh();
		}
	}
	

}
