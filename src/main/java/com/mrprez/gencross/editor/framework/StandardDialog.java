package com.mrprez.gencross.editor.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;

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
		
		dialog.pack();
		
		dialog.setVisible(true);
	}
	
	
	
	
	
	
}
