package com.mrprez.gencross.edit.framework;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.error.ErrorFrame;

public class Treatment implements Runnable, ActionListener, KeyListener {
	private Work firstWork;
	private Work currentWork;
	private static Boolean runningTreatment = Boolean.FALSE;
	private boolean busy = false;
	private Component component;
	
	
	public Treatment(Work work) {
		super();
		this.firstWork = work;
	}
	
	public Treatment(Work work, boolean busy, Component component) {
		super();
		this.firstWork = work;
		this.busy = busy;
		this.component = component;
	}



	@Override
	public void run() {
		try{
			if(currentWork instanceof EdtWork){
				EdtWork edtWork = (EdtWork)currentWork;
				edtWork.doInEdt();
				Work nextWork = edtWork.getNextWork();
				if(nextWork!=null){
					execute(nextWork);
				}
			}else if(currentWork instanceof BackgroundWork){
				BackgroundWork backgroundWork = (BackgroundWork)currentWork;
				backgroundWork.doInBackground();
				Work nextWork = backgroundWork.getNextWork();
				if(nextWork!=null){
					execute(nextWork);
				}
			}
			if(currentWork.getNextWork()==null && busy){
				synchronized (runningTreatment) {
					runningTreatment = Boolean.FALSE;
					CursorUtilities.setDefaultCursor(component);
				}
			}
		}catch(Exception e){
			if(busy){
				synchronized (runningTreatment) {
					runningTreatment = Boolean.FALSE;
					CursorUtilities.setDefaultCursor(component);
				}
			}
			ErrorFrame.displayError(e);
		}
	}
	
	private void execute(Work work){
		currentWork = work;
		if(work instanceof EdtWork){
			SwingUtilities.invokeLater(this);
		}else if(work instanceof BackgroundWork){
			Thread thread = new Thread(this);
			thread.start();
		}
	}
	
	public void launch(){
		if(busy){
			synchronized (runningTreatment) {
				if(runningTreatment.booleanValue()){
					if(SwingUtilities.isEventDispatchThread()){
						JOptionPane.showMessageDialog(GenCrossEditor.getInstance(), "Traitement en cours, veuillez patienter.", "Traitement en cours", JOptionPane.ERROR_MESSAGE);
					}else{
						OptionPane.showMessageDialog(GenCrossEditor.getInstance(), "Traitement en cours, veuillez patienter.", "Traitement en cours", JOptionPane.ERROR_MESSAGE);
					}
					return;
				}
				runningTreatment = Boolean.TRUE;
				CursorUtilities.setWaitCursor(component);
			}
		}
		execute(firstWork);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		launch();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_ENTER){
			launch();
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_ENTER){
			launch();
		}
	}
	
	
	

}