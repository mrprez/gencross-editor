package com.mrprez.gencross.edit;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.edit.error.ErrorFrame;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.Work;

public class OpenWork implements BackgroundWork{
	
	private Personnage personnage;
	private String xmlSource;
	private String title;
	private NewPersonnageEdtWork newPersonnageEdtWork;
	
	
	public String readXmlSource(File gcrFile) throws Exception{
		InputStream is = PersonnageFactory.getInstance().getInputStreamFromGcr(gcrFile);
		InputStreamReader reader = new InputStreamReader(is, "UTF-8");
		StringBuilder sb = new StringBuilder();
		try{
			int i;
			while((i=reader.read())>=0){
				sb.append((char)i);
			}
		}finally{
			is.close();
		}
		return sb.toString();
	}

	@Override
	public void doInBackground() throws Exception {
		JFileChooser fileChooser = GenCrossEditor.getInstance().getFileChooser();
		int returnCode = fileChooser.showOpenDialog(GenCrossEditor.getInstance());
		xmlSource = null;
		personnage = null;
		newPersonnageEdtWork = null;
		if(returnCode == JFileChooser.APPROVE_OPTION) {
			try{
				title = "GenCrossEditor - "+fileChooser.getSelectedFile().getName();
				xmlSource = readXmlSource(fileChooser.getSelectedFile());
				personnage = PersonnageFactory.getInstance().createPersonnageFromGcr(fileChooser.getSelectedFile());
				newPersonnageEdtWork = new NewPersonnageEdtWork(title, personnage, xmlSource);
			}catch(Throwable e){
				ErrorFrame.displayError(e);
				returnCode = OptionPane.showConfirmDialog(GenCrossEditor.getInstance(), "Voulez-vous charger l'XML source?", "Impossible de charger ce personnage", JOptionPane.YES_NO_OPTION);
				if(returnCode == JOptionPane.YES_OPTION){
					newPersonnageEdtWork = new NewPersonnageEdtWork(title, xmlSource);
				}
			}
		}
		
	}

	@Override
	public Work getNextWork() {
		return newPersonnageEdtWork;
	}

	

	

}
