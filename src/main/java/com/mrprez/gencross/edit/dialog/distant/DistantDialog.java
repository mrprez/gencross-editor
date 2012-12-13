package com.mrprez.gencross.edit.dialog.distant;

import java.awt.Component;
import java.awt.Frame;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListCellRenderer;
import javax.xml.rpc.ServiceException;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.SetXmlEdtWork;
import com.mrprez.gencross.edit.framework.OptionPane;
import com.mrprez.gencross.edit.framework.ReflectivBackgroundWork;
import com.mrprez.gencross.edit.framework.ReflectivEdtWork;
import com.mrprez.gencross.edit.framework.Treatment;
import com.mrprez.gencross.edit.framework.Work;

public class DistantDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static String DEFAULT_ADRESS_SERVER = "http://localhost:8181/gencross-web/axis/PersonnageService";
	private static String VALID_VERSION = "validVersion";
	private static String CURRENT_VERSION = "currentVersion";
	
	private static DistantDialog instance;
	
	private WebServiceClient webServiceClient;
	private List<PluginDescriptor> pluginList;
	private Map<String, Integer> personnageMap;
	private SetXmlEdtWork setXmlEdtWork = new SetXmlEdtWork(null, null);
	
	private JLabel serverAdressLabel = new JLabel("Adresse du serveur");
	private JTextField serverAdressField = new JTextField(DEFAULT_ADRESS_SERVER);
	private JButton connectServerButton = new JButton("Connexion");
	private JLabel pluginListLabel = new JLabel("Types de personnages");
	private JComboBox pluginComboBox = new JComboBox();
	private JButton pluginButton = new JButton("OK");
	private JLabel personnageListLabel = new JLabel("Personnages");
	private JComboBox personnageComboBox = new JComboBox();
	private JButton personnageButton = new JButton("Valider");
	
	
	public DistantDialog(Frame frame) throws Exception {
		super(frame);
		init();
	}

	public void init() throws Exception {
		
		Work connectWork = new ReflectivBackgroundWork(this, "loadPluginList", new ReflectivEdtWork(this, "refreshPluginComboBox"));
		connectServerButton.addActionListener(new Treatment(connectWork, true, this));
		
		pluginComboBox.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				PluginDescriptor pd = (PluginDescriptor) arg1;
				if(pd==null){
					return new JLabel(" - ");
				}
				return new JLabel(pd.getName()+" (v "+pd.getVersion()+")");
			}
		});
		Work loadPersoListWork = new ReflectivBackgroundWork(this, "loadPersonnageList", new ReflectivEdtWork(this, "refreshPersonnageComboBox"));
		pluginButton.addActionListener(new Treatment(loadPersoListWork, true, this));
		
		Work loadPersoWork = new ReflectivBackgroundWork(this, "loadPersonnage", setXmlEdtWork);
		setXmlEdtWork.setNextWork(new ReflectivEdtWork(this, "dispose"));
		personnageButton.addActionListener(new Treatment(loadPersoWork, true, this));
		
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
		pack();
	}
	
	
	public synchronized static DistantDialog getInstance() throws Exception{
		if(instance==null){
			instance = new DistantDialog(GenCrossEditor.getInstance());
		}
		return instance;
	}
	
	public void loadPluginList() throws ServiceException, MalformedURLException, RemoteException {
		pluginList = null;
		personnageMap = null;
		GenCrossEditor.setServerAddress(serverAdressField.getText().trim());
		webServiceClient = new WebServiceClient(GenCrossEditor.getServerAddress());
		pluginList = new ArrayList<PluginDescriptor>(webServiceClient.loadPluginList());
		refreshPluginComboBox();
	}
	public void refreshPluginComboBox(){
		pluginComboBox.removeAllItems();
		if(pluginList==null || pluginList.isEmpty()){
			pluginButton.setEnabled(false);
			pluginComboBox.setEnabled(false);
		}else{
			pluginButton.setEnabled(true);
			pluginComboBox.setEnabled(true);
			for(PluginDescriptor plugin : pluginList){
				pluginComboBox.addItem(plugin);
			}
		}
		refreshPersonnageComboBox();
	}
	
	public void loadPersonnageList() throws ServiceException, RemoteException {
		PluginDescriptor pluginDescriptor = (PluginDescriptor) pluginComboBox.getSelectedItem();
		personnageMap = webServiceClient.findPersonnageList(pluginDescriptor);
		
	}
	public void refreshPersonnageComboBox(){
		personnageComboBox.removeAllItems();
		if(personnageMap==null || personnageMap.isEmpty()){
			personnageButton.setEnabled(false);
			personnageComboBox.setEnabled(false);
		}else{
			personnageButton.setEnabled(true);
			personnageComboBox.setEnabled(true);
			for(String persoDesc : personnageMap.keySet()){
				personnageComboBox.addItem(persoDesc);
			}
		}
	}
	
	public void loadPersonnage() throws UnsupportedEncodingException, Exception {
		if(personnageComboBox.getItemCount()==0 || personnageComboBox.getSelectedItem()==null){
			OptionPane.showMessageDialog(this, "Aucun personnage de sélectionné.", "Action impossible", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String personnageDescriptor = (String) personnageComboBox.getSelectedItem();
		Integer personnageId = personnageMap.get(personnageDescriptor);
		GenCrossEditor.setPersonnageId(personnageId);
		byte xml[] = webServiceClient.loadPersonnage(personnageId.intValue());
		setXmlEdtWork.setXml(new String(xml, "UTF-8"));
		setXmlEdtWork.setPersonnageName(personnageDescriptor);
	}
	
}
