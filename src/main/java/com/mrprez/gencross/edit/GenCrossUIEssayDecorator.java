package com.mrprez.gencross.edit;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.ui.GenCrossUI;
import com.mrprez.gencross.ui.persoframe.PersoFrame;

public class GenCrossUIEssayDecorator {
	
	
	public GenCrossUI buildGenCrossUIEssay(Personnage personnage) throws InterruptedException, HeadlessException, CloneNotSupportedException, IOException, URISyntaxException{
		GenCrossUI.main(new String[0]);
		GenCrossUI genCrossUI = GenCrossUI.getInstance();
		genCrossUI.getPersoFrame().setJMenuBar(null);
		
		genCrossUI.setPersonnage(personnage);
		genCrossUI.reinit();
		return genCrossUI;
	}
	
	
	
	
	
	
	
	

}
