package com.mrprez.gencross.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import com.mrprez.gencross.editor.download.DownloadPluginDescriptorsTask;
import com.mrprez.gencross.editor.framework.ActionTreatment;
import com.mrprez.gencross.editor.open.ChooseFileToOpenTask;
import com.mrprez.gencross.editor.param.DisplayParamTask;
import com.mrprez.gencross.editor.save.ChooseFileToSaveTask;
import com.mrprez.gencross.editor.upload.UploadPersonnageTask;

public class GencrossEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private static GencrossEditor instance;
	
	private String username;
	private String token;
	private XmlPanel xmlPanel;
	private Integer personnageId;
	private String gencrossWebUrl = "http://localhost:8181/gencross-web/ws";
	private boolean textModified = false;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Fichier");
	private JMenu optionMenu = new JMenu("Options");
	private JMenuItem openMenuItem = new JMenuItem("Ouvrir");
	private JMenuItem saveAsMenuItem = new JMenuItem("Sauvegarder sous...");
	private JMenuItem downloadMenuItem = new JMenuItem("Récupérer");
	private JMenuItem uploadMenuItem = new JMenuItem("Envoyer");
	private JMenuItem paramMenuItem = new JMenuItem("Paramètres");
	
	
	private GencrossEditor() throws IOException{
		super();
		this.setIconImage(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/icone_GenCrossEditor.jpg")));
		this.setTitle("Gencross-Editor");
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
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		
		openMenuItem.addActionListener(new ActionTreatment(new ChooseFileToOpenTask(), this));
		fileMenu.add(openMenuItem);
		
		saveAsMenuItem.addActionListener(new ActionTreatment(new ChooseFileToSaveTask(), this));
		saveAsMenuItem.setEnabled(false);
		fileMenu.add(saveAsMenuItem);
		
		downloadMenuItem.addActionListener(new ActionTreatment(new DownloadPluginDescriptorsTask(), this));
		fileMenu.add(downloadMenuItem);
		
		uploadMenuItem.addActionListener(new ActionTreatment(new UploadPersonnageTask(), this));
		uploadMenuItem.setEnabled(false);
		fileMenu.add(uploadMenuItem);
		
		
		paramMenuItem.addActionListener(new ActionTreatment(new DisplayParamTask(), this));
		optionMenu.add(paramMenuItem);
		
		return menuBar;
	}
	

	public static void main(String[] args) throws IOException {
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
		setTextModified(false);
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
	
	public XmlPanel getXmlPanel(){
		return xmlPanel;
	}


	public boolean isTextModified() {
		return textModified;
	}


	public void setTextModified(boolean textModified) {
		this.textModified = textModified;
		saveAsMenuItem.setEnabled(textModified);
		uploadMenuItem.setEnabled(textModified);
	}

	
}
