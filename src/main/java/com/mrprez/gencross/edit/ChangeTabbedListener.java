package com.mrprez.gencross.edit;

import java.util.Collection;

import javax.swing.JOptionPane;
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
						JOptionPane.showMessageDialog(actualPanel, getErrorsText(actualPanel.getDataErrors()), "Impossible de changer d'onglet", JOptionPane.ERROR_MESSAGE);
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
	
	
	private String getErrorsText(Collection<String> errors){
		if(errors.size()==1){
			return errors.iterator().next();
		} else {
			StringBuilder sb = new StringBuilder("Plusieurs erreurs: <ul>");
			for(String error : errors) {
				sb.append("<li>" + error + "</li>");
			}
			sb.append("</ul>");
			return sb.toString();
		}
	}
	

	

}
