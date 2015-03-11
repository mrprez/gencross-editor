package com.mrprez.gencross.editor;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.editor.framework.ActionTreatment;
import com.mrprez.gencross.editor.task.DownloadPersonnageTask;

public class GencrossEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private static GencrossEditor instance;
	
	private Personnage personnage;
	private String username;
	private String token;
	private XmlPanel xmlPanel;
	
	
	private GencrossEditor(){
		super();
		JTabbedPane tabbedPane = new JTabbedPane();
		xmlPanel = new XmlPanel();
		tabbedPane.addTab("XML", xmlPanel);
		
		this.setJMenuBar(buildMenuBar());
		this.add(tabbedPane);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle desktop = ge.getMaximumWindowBounds();
		int size = Math.min(desktop.width, desktop.height);
		int x = Math.max(0, desktop.width-desktop.height) / 2;
		int y = Math.max(0, desktop.height-desktop.width) / 2;
		setPreferredSize(new Dimension(size, size));
		setBounds(x, y, size, size);
		
		pack();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	private JMenuBar buildMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Fichier");
		menuBar.add(fileMenu);
		JMenuItem downloadMenuItem = new JMenuItem("Récupérartion");
		downloadMenuItem.addActionListener(new ActionTreatment(new DownloadPersonnageTask(), this));
		fileMenu.add(downloadMenuItem);
		return menuBar;
	}
	

	public static void main(String[] args) {
		instance = new GencrossEditor();
		instance.setVisible(true);

	}
	
	public String getUsername() {
		return username;
	}


	public String getToken() {
		return token;
	}


	public void setUsernameToken(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public static GencrossEditor getInstance(){
		return instance;
	}

}
