package com.mrprez.gencross.editor.run;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.ui.GenCrossUI;

public class GencrossUiTask implements EdtTask {
	private Personnage personnage;
	
	

	public GencrossUiTask(Personnage personnage) {
		super();
		this.personnage = personnage;
	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		GenCrossUI genCrossUI = GenCrossUI.getInstance();
		if(genCrossUI==null){
			genCrossUI = initGenCrossUI();
		}
		
		genCrossUI.setPersonnage(personnage);
		genCrossUI.getPersoFrame().reinit();
	}
	
	
	private GenCrossUI initGenCrossUI() throws HeadlessException, CloneNotSupportedException, IOException, URISyntaxException{
		GenCrossUI genCrossUI = GenCrossUI.initInstance(GenCrossUI.getExecutionDirectory());
		for(int i=0; i<genCrossUI.getPersoFrame().getJMenuBar().getMenuCount(); i++){
			if( ! genCrossUI.getPersoFrame().getJMenuBar().getMenu(i).getText().equals("Affichage") ){
				genCrossUI.getPersoFrame().getJMenuBar().getMenu(i).setEnabled(false);
			}
		}
		for(WindowListener windowListener : genCrossUI.getPersoFrame().getWindowListeners()){
			genCrossUI.getPersoFrame().removeWindowListener(windowListener);
		}
		genCrossUI.getPersoFrame().addWindowListener(new CloseGencrossUiListener());
		return genCrossUI;
	}
	
	
	public static class CloseGencrossUiListener implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			GenCrossUI.getCurrentPersoFrame().getHistoryFrame().dispose();
			GenCrossUI.getCurrentPersoFrame().dispose();
			GenCrossUI.unsetInstance();
		}
		
		@Override
		public void windowOpened(WindowEvent e) {}
		
		@Override
		public void windowIconified(WindowEvent e) {}
		
		@Override
		public void windowDeiconified(WindowEvent e) {}
		
		@Override
		public void windowDeactivated(WindowEvent e) {}
		
		@Override
		public void windowClosed(WindowEvent e) {}
		
		@Override
		public void windowActivated(WindowEvent e) {}
	}
}
