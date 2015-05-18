package com.mrprez.gencross.editor.framework;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class DialogTask implements EdtTask {
	private UserTask userTask;
	
	
	
	public Window getComponent() throws Exception {
		Window window = buildWindow();
		postionWindow(window);
		window.setVisible(true);
		return window;
	}
	
	public void postionWindow(Window window){
		window.pack();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle desktop = ge.getMaximumWindowBounds();
		window.setLocation((desktop.width-window.getWidth())/2, (desktop.height-window.getHeight())/2);
	}
	
	public abstract Window buildWindow();

	
	@Override
	public final Task getNextTask() {
		return userTask;
	}
	
	
	public final void setNextTask(Task nextTask) {
		userTask.setNextTask(nextTask);
	}

	@Override
	public void doInEdt() throws Exception {
		Window window = buildWindow();
		window.addWindowListener(new ContinueTreatmentListener(userTask));
		postionWindow(window);
		window.setVisible(true);
		userTask = new UserTask(window);
	}
	

	private class ContinueTreatmentListener implements WindowListener {
		private UserTask userTask;
		
		public ContinueTreatmentListener(UserTask userTask) {
			super();
			this.userTask = userTask;
		}

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowClosed(WindowEvent event) {
			userTask.fire();
		}

		@Override
		public void windowClosing(WindowEvent e) {}

		@Override
		public void windowDeactivated(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowOpened(WindowEvent e) {}
		
	}



	
	

}
