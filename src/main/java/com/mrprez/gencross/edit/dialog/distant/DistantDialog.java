package com.mrprez.gencross.edit.dialog.distant;

import java.awt.Component;
import java.awt.Frame;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListCellRenderer;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.framework.EditDialog;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.ReflectivBackgroundWork;
import com.mrprez.gencross.edit.framework.ReflectivEdtWork;
import com.mrprez.gencross.edit.framework.Treatment;
import com.mrprez.gencross.edit.framework.Work;
import com.mrprez.gencross.edit.webservice.Exception_Exception;
import com.mrprez.gencross.edit.webservice.PersonnageDescription;
import com.mrprez.gencross.edit.webservice.PersonnageService;
import com.mrprez.gencross.edit.webservice.PersonnageServiceFactory;
import com.mrprez.gencross.edit.webservice.PluginDescription;

public class DistantDialog extends EditDialog<Personnage> {
	private static final long serialVersionUID = 1L;
	private static String DEFAULT_ADRESS_SERVER = "http://localhost:8181/gencross-web/services/PersonnageService";
	private static String VALID_VERSION = "validVersion";
	private static String CURRENT_VERSION = "currentVersion";
	
	private static DistantDialog instance;
	
	private PersonnageService personnageService;
	private List<PluginDescription> pluginList;
	private List<PersonnageDescription> personnageList;
	private Personnage personnage;
	
	private JLabel serverAdressLabel = new JLabel("Adresse du serveur");
	private JTextField serverAdressField = new JTextField(DEFAULT_ADRESS_SERVER);
	private JButton connectServerButton = new JButton("Connexion");
	private JLabel pluginListLabel = new JLabel("Types de personnages");
	private JComboBox pluginComboBox = new JComboBox();
	private JButton pluginButton = new JButton("OK");
	private JLabel personnageListLabel = new JLabel("Personnages");
	private JComboBox personnageComboBox = new JComboBox();
	private JButton personnageButton = new JButton("Valider");
	
	
	public DistantDialog(Frame frame) {
		super(frame);
	}

	@Override
	public Personnage getResult() {
		return personnage;
	}

	@Override
	public void init() throws Exception {
		
		Work connectWork = new ReflectivBackgroundWork(this, "loadPluginList", new ReflectivEdtWork(this, "refreshPluginComboBox"));
		connectServerButton.addActionListener(new Treatment(connectWork, true, this));
		
		pluginComboBox.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				PluginDescription pg = (PluginDescription) arg1;
				if(pg==null){
					return new JLabel(" - ");
				}
				return new JLabel(pg.getName()+" (v "+pg.getVersion()+")");
			}
		});
		Work loadPersoWork = new ReflectivBackgroundWork(this, "loadPersonnageList", new ReflectivEdtWork(this, "refreshPersonnageComboBox"));
		pluginButton.addActionListener(new Treatment(loadPersoWork, true, this));
		
		personnageComboBox.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				PersonnageDescription pd = (PersonnageDescription) arg1;
				if(pd==null){
					return new JLabel(" - ");
				}
				if(pd.getGmName()!=null && pd.getPlayerName()!=null){
					return new JLabel(pd.getName()+" (MJ:"+pd.getGmName()+", PJ:"+pd.getPlayerName()+")");
				} else if(pd.getGmName()!=null && pd.getPlayerName()==null){
					return new JLabel(pd.getName()+" (MJ:"+pd.getPlayerName()+")");
				}if(pd.getGmName()==null && pd.getPlayerName()!=null){
					return new JLabel(pd.getName()+" (PJ:"+pd.getPlayerName()+")");
				}else{
					return new JLabel(pd.getName());
				}
			}
		});
		personnageButton.addActionListener(new Treatment(new ReflectivBackgroundWork(this, "loadPersonnage", new ReflectivEdtWork(this, "validateData"))));
		
		refreshPluginComboBox();
		
		initLayout();
	}
	
	private void initLayout(){
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(serverAdressLabel)
				.addComponent(pluginListLabel)
				.addComponent(personnageListLabel)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(serverAdressField)
				.addComponent(pluginComboBox)
				.addComponent(personnageComboBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(connectServerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(pluginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(personnageButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(serverAdressLabel)
				.addComponent(serverAdressField)
				.addComponent(connectServerButton)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(pluginListLabel)
				.addComponent(pluginComboBox)
				.addComponent(pluginButton)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(personnageListLabel)
				.addComponent(personnageComboBox)
				.addComponent(personnageButton)
			)
			.addContainerGap()
		);
	}
	
	
	public synchronized static DistantDialog getInstance(){
		if(instance==null){
			instance = new DistantDialog(GenCrossEditor.getInstance());
		}
		return instance;
	}
	
	public void loadPluginList() throws MalformedURLException, Exception_Exception {
		pluginList = null;
		personnageList = null;
		personnageService = PersonnageServiceFactory.buildService(serverAdressField.getText());
		pluginList = personnageService.getAvailablePersonnageList();
	}
	public void refreshPluginComboBox(){
		pluginComboBox.removeAllItems();
		if(pluginList==null || pluginList.isEmpty()){
			pluginButton.setEnabled(false);
			pluginComboBox.setEnabled(false);
		}else{
			pluginButton.setEnabled(true);
			pluginComboBox.setEnabled(true);
			for(PluginDescription plugin : pluginList){
				pluginComboBox.addItem(plugin);
			}
		}
		refreshPersonnageComboBox();
	}
	
	public void loadPersonnageList() throws Exception_Exception{
		PluginDescription pluginDescription = (PluginDescription) pluginComboBox.getSelectedItem();
		personnageList = personnageService.getPersonnageList(pluginDescription.getName());
	}
	public void refreshPersonnageComboBox(){
		personnageComboBox.removeAllItems();
		if(personnageList==null || personnageList.isEmpty()){
			personnageButton.setEnabled(false);
			personnageComboBox.setEnabled(false);
		}else{
			personnageButton.setEnabled(true);
			personnageComboBox.setEnabled(true);
			for(PersonnageDescription persoDesc : personnageList){
				personnageComboBox.addItem(persoDesc);
			}
		}
	}
	
	public void loadPersonnage() throws UnsupportedEncodingException, Exception {
		if(personnageComboBox.getItemCount()==0 || personnageComboBox.getSelectedItem()==null){
			OptionPane.showMessageDialog(this, "Aucun personnage de sélectionné.", "Action impossible", JOptionPane.ERROR_MESSAGE);
			return;
		}
		PersonnageDescription pd = (PersonnageDescription)personnageComboBox.getSelectedItem();
		byte xml[] = personnageService.getPersonnage(pd.getId(), CURRENT_VERSION);
		personnage = GenCrossEditor.getInstance().getPersonnageFactory().loadPersonnage(new ByteArrayInputStream(xml));
		
		//super.validateData();
	}

}
