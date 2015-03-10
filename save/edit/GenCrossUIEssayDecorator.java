package com.mrprez.gencross.edit;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JMenu;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.persoframe.menu.PersoMenuBar;

public class GenCrossUIEssayDecorator {
	
	
	public GenCrossUI buildGenCrossUIEssay(Personnage personnage) throws InterruptedException, HeadlessException, CloneNotSupportedException, IOException, URISyntaxException{
		GenCrossUI.main(new String[0]);
		GenCrossUI genCrossUI;
		if(GenCrossUI.getInstance() == null){
			genCrossUI = GenCrossUI.initInstance(GenCrossEditor.getInstance().getRepositoryManager().getRepository());
		} else {
			GenCrossUI.reinit();
			genCrossUI = GenCrossUI.getInstance();
		}
		decorateMenuBar((PersoMenuBar) genCrossUI.getPersoFrame().getJMenuBar());
		
		genCrossUI.setPersonnage(personnage);
		genCrossUI.getPersoFrame().reinit();
		return genCrossUI;
	}
	
	
	public void decorateMenuBar(PersoMenuBar persoMenuBar){
		for(int i=0; i<persoMenuBar.getMenuCount(); i++){
			JMenu menu = persoMenuBar.getMenu(i);
			menu.setEnabled(false);
		}
		persoMenuBar.getAffichageMenu().setEnabled(true);
	}
	
	
	
	
	
	
	
	

}
