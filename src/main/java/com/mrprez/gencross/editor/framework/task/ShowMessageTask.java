package com.mrprez.gencross.editor.framework.task;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class ShowMessageTask implements EdtTask {
	private Component parentComponent;
	private String message;
	private String title;
	private int messageType;
	private Icon icon;
	private Task nextTask;
	
	
	

	public ShowMessageTask(Component parentComponent, String message) {
		super();
		this.parentComponent = parentComponent;
		this.message = message;
	}
	
	
	public ShowMessageTask(Component parentComponent, String message, String title, int messageType) {
		super();
		this.parentComponent = parentComponent;
		this.message = message;
		this.title = title;
		this.messageType = messageType;
	}
	

	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInEdt() throws Exception {
		JOptionPane.showMessageDialog(parentComponent, message, title, messageType, icon);
		
	}
	

	public Component getParentComponent() {
		return parentComponent;
	}

	public void setParentComponent(Component parentComponent) {
		this.parentComponent = parentComponent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	
	public void setNextTask(Task nextTask){
		this.nextTask = nextTask;
	}
	
	

}
