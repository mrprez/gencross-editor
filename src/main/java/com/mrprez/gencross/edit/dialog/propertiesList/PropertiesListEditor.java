package com.mrprez.gencross.edit.dialog.propertiesList;

import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.PropertiesList;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.framework.EditDialog;
import com.mrprez.gencross.edit.framework.SimpleEDTAction;
import com.mrprez.gencross.edit.framework.Treatment;

public class PropertiesListEditor extends EditDialog<PropertiesList> {
	private static final long serialVersionUID = 1L;
	
	private PropertiesList propertiesList;
	
	private JLabel fixeLabel = new JLabel("Fixe");
	private JCheckBox fixeCheckBox = new JCheckBox();
	private JLabel openLabel = new JLabel("Ouverte");
	private JCheckBox openCheckBox = new JCheckBox();
	private JLabel canRemoveElementLabel = new JLabel("Sous-éléments supprimables");
	private JComboBox canRemoveElementList = new JComboBox(new String[]{"DEFAUT","OUI", "NON"});
	private JLabel defaultPropertyLabel = new JLabel("Propriété par défaut");
	private JCheckBox defaultPropertyCheckBox = new JCheckBox();
	private JButton addRemoveDefaultPropertyButton = new JButton("Ajouer");
	private JButton editDefaultPropertyButton = new JButton("Editer");
	private JLabel propertiesLabel = new JLabel("Propriétés");
	private DefaultListModel propertiesModel = new DefaultListModel();
	private JList propertiesJList = new JList(propertiesModel);
	private JScrollPane propertiesScroller = new JScrollPane(propertiesJList);
	private JButton addPropertyButton = new JButton("Ajouter");
	private JButton editPropertyButton = new JButton("Editer");
	private JButton removePropertyButton = new JButton("Supprimer");
	
	private JLabel optionsLabel = new JLabel("Options");
	private DefaultListModel optionsModel = new DefaultListModel();
	private JList optionsList = new JList(optionsModel);
	private JScrollPane optionsScroller = new JScrollPane(optionsList);
	private JButton addOptionButton = new JButton("Ajouter");
	private JButton editOptionButton = new JButton("Editer");
	private JButton removeOptionButton = new JButton("Supprimer");
	
	
	private JButton validateButton = new JButton("OK");
	
	
	public PropertiesListEditor(PropertiesList propertiesList, Frame frame) {
		super(frame);
		this.propertiesList = propertiesList;
	}
	
	public PropertiesListEditor(PropertiesList propertiesList, Dialog dialog) {
		super(dialog);
		this.propertiesList = propertiesList;
	}
	
	
	public void init() throws SecurityException, NoSuchMethodException{
		fixeCheckBox.setSelected(propertiesList.isFixe());
		openCheckBox.setSelected(propertiesList.isOpen());
		if(propertiesList.getCanRemoveElement()==null){
			canRemoveElementList.setSelectedIndex(0);
		}else{
			canRemoveElementList.setSelectedIndex(propertiesList.getCanRemoveElement().booleanValue()?1:2);
		}
		defaultPropertyCheckBox.setEnabled(false);
		
		// Ajout des listener sur les boutons
		addRemoveDefaultPropertyButton.addActionListener(new Treatment(new AddRemoveDefautPropertyWork(this)));
		editDefaultPropertyButton.addActionListener(new Treatment(new EditDefaultPropertyWork(this)));
		addPropertyButton.addActionListener(new Treatment(new AddPropertyWork(this)));
		editPropertyButton.addActionListener(new Treatment(new EditPropertyWork(this)));
		removePropertyButton.addActionListener(new Treatment(new RemovePropertyWork(this)));
		addOptionButton.addActionListener(new Treatment(new AddOptionWork(this)));
		editOptionButton.addActionListener(new Treatment(new EditOptionWork(this)));
		removeOptionButton.addActionListener(new Treatment(new RemoveOptionWork(this)));
		
		validateButton.addActionListener(new SimpleEDTAction(this, "submit"));
		
		initLayout();
		
		refreshLists();
		
		fieldEnability();
	}
	
	public void fieldEnability(){
		defaultPropertyCheckBox.setSelected(propertiesList.getDefaultProperty()!=null);
		addRemoveDefaultPropertyButton.setText(propertiesList.getDefaultProperty()==null?"Ajouter":"Suppr.");
		editDefaultPropertyButton.setEnabled(propertiesList.getDefaultProperty()!=null);
		
	}
	
	private void initLayout(){
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(fixeLabel)
						.addComponent(openLabel)
						.addComponent(canRemoveElementLabel)
						.addComponent(defaultPropertyLabel)
						.addComponent(propertiesLabel)
						.addComponent(addPropertyButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(editPropertyButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(removePropertyButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(optionsLabel)
						.addComponent(addOptionButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(editOptionButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(removeOptionButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
						.addComponent(fixeCheckBox)
						.addComponent(openCheckBox)
						.addComponent(canRemoveElementList)
						.addGroup(layout.createSequentialGroup()
							.addComponent(defaultPropertyCheckBox)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(addRemoveDefaultPropertyButton)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(editDefaultPropertyButton)
						)
						.addComponent(propertiesScroller)
						.addComponent(optionsScroller)
					)
				)
				.addComponent(validateButton, GroupLayout.Alignment.CENTER)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(fixeLabel)
				.addComponent(fixeCheckBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(openLabel)
				.addComponent(openCheckBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(canRemoveElementLabel)
				.addComponent(canRemoveElementList)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(defaultPropertyLabel)
				.addComponent(defaultPropertyCheckBox)
				.addComponent(addRemoveDefaultPropertyButton)
				.addComponent(editDefaultPropertyButton)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
				.addGroup(layout.createSequentialGroup()
					.addComponent(propertiesLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(addPropertyButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(editPropertyButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(removePropertyButton)
				)
				.addComponent(propertiesScroller, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
				.addGroup(layout.createSequentialGroup()
					.addComponent(optionsLabel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(addOptionButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(editOptionButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(removeOptionButton)
				)
				.addComponent(optionsScroller, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
			.addGap(20)
			.addComponent(validateButton)
			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
	}
	
	public void refreshLists(){
		propertiesModel.clear();
		for(String propertyName : propertiesList.getProperties().keySet()){
			propertiesModel.addElement(propertyName);
		}
		
		optionsModel.clear();
		for(String optionName : propertiesList.getOptions().keySet()){
			optionsModel.addElement(optionName);
		}
	}
	
	public String findError(){
		if(openCheckBox.isSelected() && !defaultPropertyCheckBox.isSelected()){
			return "Si la liste est ouverte,il faut une propriété par défaut";
		}
		
		return null;
	}
	
	public void submit(){
		String error = findError();
		if(findError()!=null){
			JOptionPane.showMessageDialog(this, error, "Liste de propriétés invalide", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		propertiesList.setFixe(fixeCheckBox.isSelected());
		propertiesList.setOpen(openCheckBox.isSelected());
		if(canRemoveElementList.getSelectedIndex()==1){
			propertiesList.setCanRemoveElement(Boolean.TRUE);
		}else if(canRemoveElementList.getSelectedIndex()==2){
			propertiesList.setCanRemoveElement(Boolean.FALSE);
		}else{
			propertiesList.setCanRemoveElement(null);
		}
		
		validateData();
	}
	
	@Override
	public PropertiesList getResult() {
		return propertiesList;
	}
	
	public PropertiesList getPropertiesList(){
		return propertiesList;
	}
	
	public Property getSelectedProperty(){
		if(propertiesJList.getSelectedValue()==null){
			return null;
		}
		return propertiesList.getProperties().get((String)propertiesJList.getSelectedValue());
	}
	
	public Property getSelectedOption(){
		if(optionsList.getSelectedValue()==null){
			return null;
		}
		return propertiesList.getOptions().get((String)optionsList.getSelectedValue());
	}
	
	public Object[] getSelectedPropertiesName(){
		return propertiesJList.getSelectedValues();
	}
	
	public Object[] getSelectedOptionsName(){
		return optionsList.getSelectedValues();
	}

}
