package com.mrprez.gencross.editor.framework;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class DialogTask implements ComponentTask {
	protected Treatment treatment;

	@Override
	public Window getComponent() throws Exception {
		Window window = buildWindow();
		postionWindow(window);
		window.addWindowListener(new ContinueTreatmentListener(treatment));
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
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	private class ContinueTreatmentListener implements WindowListener {
		private Treatment treatment;
		
		public ContinueTreatmentListener(Treatment treatment) {
			super();
			this.treatment = treatment;
		}

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowClosed(WindowEvent event) {
			treatment.notify();
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
