package com.mrprez.gencross.edit;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.disk.RepositoryManager;
import com.mrprez.gencross.edit.error.ErrorFrame;
import com.mrprez.gencross.edit.framework.Treatment;
import com.mrprez.gencross.edit.xml.XmlPanel;

public class GenCrossEditor extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public final static String PERSONNAGE_REPOSITORY_NAME = "repository";
	private final static String SIMPLE_TITLE = "GenCrossEditor";
	private final static String TITLE_SEPARATOR = " - ";
	
	private static GenCrossEditor instance;
	
	private JFileChooser fileChooser = new JFileChooser();
	
	private RepositoryManager repositoryManager;
	private PersonnageFactory personnageFactory;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Fichier");
	private JMenuItem openItem = new JMenuItem("Ouvrir");
	private JMenuItem openDistantItem = new JMenuItem("Ouvrir à distance");
	private JMenuItem saveAsItem = new JMenuItem("Sauvegarder sous");
	private JMenuItem testItem = new JMenuItem("Tester");
	
	private XmlPanel xmlPanel;
	
	
	public static void main(String[] args) throws Exception {
		try {
			instance = new GenCrossEditor();
			instance.init();
			instance.setTitle(SIMPLE_TITLE);
			instance.setIconImage(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/icone_GenCrossEditor.jpg")));
			instance.setVisible(true);
		} catch (HeadlessException e) {
			ErrorFrame.displayError(e);
		} catch (IOException e) {
			ErrorFrame.displayError(e);
		} catch (URISyntaxException e) {
			ErrorFrame.displayError(e);
		}
	}
	
	
	public void init() throws Exception{
		repositoryManager = new RepositoryManager(new File(getExecutionDirectory(), PERSONNAGE_REPOSITORY_NAME));
		personnageFactory = new PersonnageFactory(repositoryManager.getRepositoryClassLoader());
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle desktop = ge.getMaximumWindowBounds();
		setPreferredSize(new Dimension(desktop.width/2, desktop.height));
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// MenuBar
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(openItem);
		fileMenu.add(openDistantItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(testItem);
		openItem.addActionListener(new Treatment(new OpenWork()));
		openDistantItem.addActionListener(new Treatment(new DistantPersonnageWork()));
		saveAsItem.addActionListener(new Treatment(new SaveAsWork()));
		testItem.addActionListener(new Treatment(new TestWork()));
		
		// Panels
		xmlPanel = new XmlPanel();
		
		add(xmlPanel);
		
		pack();
	}
	
	
	public static GenCrossEditor getInstance() {
		return instance;
	}
	
	public static File getExecutionDirectory() throws URISyntaxException{
		URL jarUrl = GenCrossEditor.class.getProtectionDomain().getCodeSource().getLocation();
		File jarFile = new File(jarUrl.toURI());
		return jarFile.getParentFile();
	}


	public void setPersonnageName(String personnageName) {
		if(personnageName != null) {
			setTitle(SIMPLE_TITLE + TITLE_SEPARATOR + personnageName);
		} else {
			setTitle(SIMPLE_TITLE);
		}
	}
	
	public JFileChooser getFileChooser(){
		return fileChooser;
	}

	public RepositoryManager getRepositoryManager() {
		return repositoryManager;
	}

	public PersonnageFactory getPersonnageFactory() {
		return personnageFactory;
	}
	
	public void loadXml(String xml){
		xmlPanel.setText(xml);
	}
	
	public String getXml(){
		return xmlPanel.getText();
	}
	
}
