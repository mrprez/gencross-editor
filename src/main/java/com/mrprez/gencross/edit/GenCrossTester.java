package com.mrprez.gencross.edit;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.mrprez.gencross.ui.GenCrossUI;

public class GenCrossTester extends GenCrossUI {

	private WindowListener closeListener = new WindowListener() {
		@Override
		public void windowOpened(WindowEvent arg0) {
		}
		@Override
		public void windowIconified(WindowEvent arg0) {
		}
		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}
		@Override
		public void windowDeactivated(WindowEvent arg0) {
		}
		@Override
		public void windowClosing(WindowEvent arg0) {
			getPersoFrame().getHistoryFrame().dispose();
			getPersoFrame().getEditCommentFrame().dispose();
			getPersoFrame().getViewCommentFrame().dispose();
			getPersoFrame().dispose();
		}
		@Override
		public void windowClosed(WindowEvent arg0) {
		}
		@Override
		public void windowActivated(WindowEvent arg0) {
		}
	};
	
	
	public GenCrossTester(File personnageRepository) throws HeadlessException, CloneNotSupportedException, IOException, URISyntaxException {
		super(personnageRepository);
		for(int i=0; i<getPersoFrame().getWindowListeners().length; i++){
			getPersoFrame().removeWindowListener(getPersoFrame().getWindowListeners()[i]);
		}
		getPersoFrame().addWindowListener(closeListener);
		getPersoFrame().setJMenuBar(null);
	}
	
	

}
