package com.mrprez.gencross.editor.framework.task;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class ShowConfirmTask implements EdtTask {
	private Component parentComponent;
	private String message;
	private String title;
	private int optionType;
	private int messageType;
	private Icon icon;
	private Task nextTask;
	private Map<Integer, Task> nextTaskMap = new HashMap<Integer, Task>();
	private int result;
	
	

	public ShowConfirmTask(Component parentComponent, String message) {
		super();
		this.parentComponent = parentComponent;
		this.message = message;
	}
	

	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInEdt() throws Exception {
		result = JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, icon);
		nextTask = nextTaskMap.get(result);
	}
	
	
	public void setNextTask(int option, Task task){
		nextTaskMap.put(option, task);
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
	
	public int getOptionType() {
		return optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	 
	
	

}
