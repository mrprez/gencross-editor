package com.mrprez.gencross.editor.framework;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.task.HideTask;

public abstract class StandardDialog implements EdtTask {
	private UserTask userTask;
	private HideTask hideTask;

	protected final JButton okButton = new JButton("OK");
	protected final JButton cancelButton = new JButton("Annuler");
	
	
	
	public abstract JDialog buildDialog();
	
	
	public abstract Task getValidTask();
	
	public abstract Task getCancelTask();
	
	
	public void setNextTask(Task nextTask){
		hideTask.setTask(nextTask);
	}

	@Override
	public Task getNextTask() {
		return userTask;
	}

	@Override
	public void doInEdt() throws Exception {
		JDialog dialog = buildDialog();
		hideTask = new HideTask(dialog);
		userTask = new UserTask(dialog);
		userTask.setNextTask(hideTask);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideTask.setTask(getValidTask());
				userTask.fire();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideTask.setTask(getCancelTask());
				userTask.fire();
			}
		});
		
		dialog.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {
				hideTask.setTask(getCancelTask());
				userTask.fire();
			}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		
		addListeners(dialog);
		
		dialog.pack();
		
		Rectangle editorBounds = GencrossEditor.getInstance().getBounds();
		dialog.setLocation(editorBounds.x + (editorBounds.width-dialog.getWidth()) / 2, editorBounds.y + (editorBounds.height - dialog.getHeight())/2);
		
		dialog.setVisible(true);
	}
	
	
	
	protected void addListeners(Container container){
		for(Component component : container.getComponents()){
			component.addKeyListener(new EnterCancelListener());
			if(component instanceof Container){
				addListeners((Container) component);
			}
		}
	}
	
	
	private class EnterCancelListener implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				okButton.doClick();
			}
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
				cancelButton.doClick();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
	}
	
	
	
	
	
}
