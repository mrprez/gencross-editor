package com.mrprez.gencross.editor;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.mrprez.gencross.Personnage;

public class GencrossEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private static GencrossEditor gencrossEditor;
	
	private Personnage personnage;
	private XmlPanel xmlPanel;
	
	
	private GencrossEditor(){
		super();
		JTabbedPane tabbedPane = new JTabbedPane();
		xmlPanel = new XmlPanel();
		tabbedPane.addTab("XML", xmlPanel);
		
		this.add(tabbedPane);
		
		this.repaint();
	}
	

	public static void main(String[] args) {
		gencrossEditor = new GencrossEditor();
		gencrossEditor.setVisible(true);

	}

}
