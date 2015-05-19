package com.mrprez.gencross.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.mrprez.gencross.editor.download.DownloadPluginDescriptorsTask;
import com.mrprez.gencross.editor.framework.ActionTreatment;
import com.mrprez.gencross.editor.open.ChooseFileToOpenTask;
import com.mrprez.gencross.editor.param.DisplayParamTask;
import com.mrprez.gencross.editor.upload.UploadPersonnageTask;

public class GencrossEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private static GencrossEditor instance;
	
	private String username;
	private String token;
	private XmlPanel xmlPanel;
	private Integer personnageId;
	private String gencrossWebUrl = "http://localhost:8181/gencross-web";
	
	
	
	private GencrossEditor(){
		super();
		JTabbedPane tabbedPane = new JTabbedPane();
		xmlPanel = new XmlPanel();
		tabbedPane.addTab("XML", xmlPanel);
		
		this.setJMenuBar(buildMenuBar());
		setLayout(new BorderLayout());
		
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
		JMenu optionMenu = new JMenu("Options");
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		
		JMenuItem openMenuItem = new JMenuItem("Ouvrir");
		openMenuItem.addActionListener(new ActionTreatment(new ChooseFileToOpenTask(), this));
		fileMenu.add(openMenuItem);
		
		JMenuItem downloadMenuItem = new JMenuItem("Récupérer");
		downloadMenuItem.addActionListener(new ActionTreatment(new DownloadPluginDescriptorsTask(), this));
		fileMenu.add(downloadMenuItem);
		
		JMenuItem uploadMenuItem = new JMenuItem("Envoyer");
		uploadMenuItem.addActionListener(new ActionTreatment(new UploadPersonnageTask(), this));
		fileMenu.add(uploadMenuItem);
		
		
		JMenuItem paramMenuItem = new JMenuItem("Paramètres");
		paramMenuItem.addActionListener(new ActionTreatment(new DisplayParamTask(), this));
		optionMenu.add(paramMenuItem);
		
		return menuBar;
	}
	

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
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
	
	
	public void setPersonnage(String xml){
		xmlPanel.setPersonnageXml(xml);
	}

	
	public String getPersonnage(){
		return xmlPanel.getPersonnageXml();
	}


	public Integer getPersonnageId() {
		return personnageId;
	}


	public void setPersonnageId(Integer personnageId) {
		this.personnageId = personnageId;
	}
	
	public synchronized String getGencrossWebUrl() {
		return gencrossWebUrl;
	}

	public synchronized void setGencrossWebUrl(String gencrossWebUrl) {
		this.gencrossWebUrl = gencrossWebUrl;
	}
}
