package com.mrprez.gencross.edit;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.error.ErrorFrame;

public class ChangeTabbedListener implements ChangeListener {
	private GenCrossEditorPanel actualPanel;
	private JTabbedPane tabbedPane;
	
	
	public ChangeTabbedListener(JTabbedPane tabbedPane) {
		super();
		this.actualPanel = (GenCrossEditorPanel) tabbedPane.getSelectedComponent();
		this.tabbedPane = tabbedPane;
	}


	@Override
	public void stateChanged(ChangeEvent event) {
		try {
			GenCrossEditorPanel selectedPanel = (GenCrossEditorPanel) tabbedPane.getSelectedComponent();
			if(selectedPanel!=actualPanel){
				if(actualPanel.isChanged()){
					if(!actualPanel.isDataValid()){
						for(int i=0; i<tabbedPane.getComponentCount(); i++){
							if(tabbedPane.getComponentAt(i)!=actualPanel){
								tabbedPane.setEnabledAt(i, false);
							}else{
								tabbedPane.setSelectedIndex(i);
							}
						}
					}else{
						Personnage newPersonnage = actualPanel.impact();
						GenCrossEditor.getInstance().setPersonnage(newPersonnage);
						for(int i=0; i<tabbedPane.getComponentCount(); i++){
							GenCrossEditorPanel panel = (GenCrossEditorPanel)tabbedPane.getComponentAt(i);
							panel.refresh();
							panel.setChanged(false);
						}
					}
				}
				actualPanel = selectedPanel;
			}
		} catch (Exception e) {
			tabbedPane.setSelectedComponent(actualPanel);
			ErrorFrame.displayError(e);
		}
	}
	

	

}
