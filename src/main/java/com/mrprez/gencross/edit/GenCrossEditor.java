package com.mrprez.gencross.edit;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.edit.error.ErrorFrame;
import com.mrprez.gencross.edit.framework.Treatment;
import com.mrprez.gencross.edit.listeners.ListenerPanel;
import com.mrprez.gencross.edit.tree.TreePanel;
import com.mrprez.gencross.edit.xml.XmlPanel;

public class GenCrossEditor extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public final static String PERSONNAGE_REPOSITORY_NAME = "repository";
	
	private static GenCrossEditor instance;
	
	private JFileChooser fileChooser = new JFileChooser();
	
	private Personnage personnage = new Personnage();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Fichier");
	private JMenuItem openItem = new JMenuItem("Ouvrir");
	private JMenuItem openDistantItem = new JMenuItem("Ouvrir à distance");
	private JMenuItem saveAsItem = new JMenuItem("Sauvegarder sous");
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private TreePanel treePanel;
	private XmlPanel xmlPanel;
	private ListenerPanel listenerPanel;
	private List<GenCrossEditorPanel> panelList = new ArrayList<GenCrossEditorPanel>();
	
	
	public static void main(String[] args) throws Exception {
		try {
			File personnageRepository = new File(getExecutionDirectory().getParentFile(),PERSONNAGE_REPOSITORY_NAME);
			PersonnageFactory.initInstance(personnageRepository);
			instance = new GenCrossEditor();
			instance.init();
			instance.setTitle("GenCrossEditor");
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
		openItem.addActionListener(new Treatment(new OpenWork()));
		openDistantItem.addActionListener(new Treatment(new DistantPersonnageWork()));
		saveAsItem.addActionListener(new Treatment(new SaveAsWork()));
		
		// Panels
		treePanel = new TreePanel(personnage);
		xmlPanel = new XmlPanel(personnage);
		listenerPanel = new ListenerPanel(personnage);
		tabbedPane.addTab("Arbre", treePanel);
		tabbedPane.addTab("Listeners", listenerPanel);
		tabbedPane.addTab("Source XML", xmlPanel);
		panelList.add(treePanel);
		panelList.add(listenerPanel);
		panelList.add(xmlPanel);
		
		tabbedPane.addChangeListener(new ChangeTabbedListener(tabbedPane));
		
		add(tabbedPane);
		
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


	public Personnage getPersonnage() {
		return personnage;
	}
	
	public void setTabbedPanEnability(int index, boolean enable){
		tabbedPane.setEnabledAt(index, enable);
		if(!enable && tabbedPane.getSelectedIndex()==index){
			tabbedPane.setSelectedIndex(index==0?1:0);
		}
	}
	
	public boolean getTabbedPaneEnability(int index){
		return tabbedPane.isEnabledAt(index);
	}

	public void setPersonnage(Personnage personnage) throws Exception {
		this.personnage = personnage;
		for(GenCrossEditorPanel editorPanel : panelList){
			editorPanel.refresh();
		}
	}
	
	public void setXmlSource(String xmlText){
		xmlPanel.setText(xmlText);
	}


	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public TreePanel getTreePanel(){
		return treePanel;
	}
	
	public JFileChooser getFileChooser(){
		return fileChooser;
	}
	
	
	
	
	
	

}
