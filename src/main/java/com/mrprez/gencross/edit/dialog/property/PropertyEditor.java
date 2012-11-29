package com.mrprez.gencross.edit.dialog.property;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.Property;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.framework.EditDialog;
import com.mrprez.gencross.edit.framework.SimpleEDTAction;
import com.mrprez.gencross.edit.framework.Treatment;
import com.mrprez.gencross.renderer.Renderer;
import com.mrprez.gencross.value.DoubleValue;
import com.mrprez.gencross.value.IntValue;
import com.mrprez.gencross.value.StringValue;
import com.mrprez.gencross.value.Value;

public class PropertyEditor extends EditDialog<Property> {
	private static final long serialVersionUID = 1L;
	
	private Property property;
	
	private JLabel nameLabel = new JLabel("Nom");
	private JTextField nameField = new JTextField(20);
	private JLabel specificationLabel = new JLabel("Spécification");
	private JCheckBox specificationCheckBox = new JCheckBox();
	private JTextField specificationField = new JTextField();
	private JLabel editableLabel = new JLabel("Editable");
	private JCheckBox editableCheckBox = new JCheckBox();
	private JLabel canBeRemovedLabel = new JLabel("Supprimable");
	private JCheckBox canBeRemovedCheckBox = new JCheckBox();
	private JLabel valueTypeLabel = new JLabel("Type de valeur");
	private JComboBox valueTypeCombo = new JComboBox(new String[]{"Aucune", "Texte", "Entier", "Décimal"});
	private JLabel valueLabel = new JLabel("Valeur");
	private JTextField valueField = new JTextField(20);
	private JLabel offsetLabel = new JLabel("Offset");
	private JTextField offsetField = new JTextField(20);
	private JLabel maxLabel = new JLabel("Max");
	private JTextField  maxField = new JTextField(20);
	private JLabel minLabel = new JLabel("Min");
	private JTextField minField = new JTextField(20);
	private JLabel defaultHistoryLabel = new JLabel("Historique par défaut");
	private JCheckBox defaultHistoryCheckBox = new JCheckBox();
	private JButton addRemoveDefaultHistoryButton = new JButton("Ajouter");
	private JButton editDefaultHistoryButton = new JButton("Editer");
	private JLabel rendererLabel = new JLabel("Classe de rendu");
	private JTextField rendererField = new JTextField(20);
	private JLabel choiceLabel = new JLabel("Choix de valeurs");
	private JCheckBox choiceCheckBox = new JCheckBox();
	private JButton addOptionButton = new JButton("Ajouter");
	private JButton editOptionButton = new JButton("Editer");
	private JButton removeOptionButton = new JButton("Supprimer");
	private DefaultListModel choiceListModel = new DefaultListModel();
	private JList choiceList = new JList(choiceListModel);
	private JScrollPane choiceScroller = new JScrollPane(choiceList);
	private JLabel commentLabel = new JLabel("Commentaire");
	private JTextArea commentTextArea = new JTextArea(5,20);
	private JScrollPane commentScrollPane = new JScrollPane(commentTextArea);
	private JLabel subPropertieslabel = new JLabel("Liste de sous-propriétés");
	private JCheckBox subPropertiesCheckBox = new JCheckBox();
	private JButton addRemoveSubPropertiesButton = new JButton();
	private JButton editSubPropertiesButton = new JButton("Editer");
	
	
	private JButton validateButton = new JButton("OK");
	
	public PropertyEditor(Property property, Frame frame) {
		super(frame);
		this.property = property.clone();
	}
	
	public PropertyEditor(Property property, Dialog dialog) {
		super(dialog);
		this.property = property.clone();
	}
	
	
	public void init() throws SecurityException, NoSuchMethodException {
		// Création des listeners
		valueTypeCombo.addActionListener(new SimpleEDTAction(this,"fieldEnability"));
		choiceCheckBox.addActionListener(new SimpleEDTAction(this,"fieldEnability"));
		
		specificationCheckBox.addActionListener(new SimpleEDTAction(this,"fieldEnability"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Edit - "+property.getName());
		
		// Name
		nameField.setText(property.getName());
		//Specification
		specificationCheckBox.setSelected(property.getSpecification()!=null);
		if(property.getSpecification()!=null){
			specificationField.setText(property.getSpecification());
		}
		// ValueType
		if(property.getValue()==null){
			valueTypeCombo.setSelectedIndex(0);
		}else if(property.getValue() instanceof StringValue){
			valueTypeCombo.setSelectedIndex(1);
		}else if(property.getValue() instanceof IntValue){
			valueTypeCombo.setSelectedIndex(2);
		}else if(property.getValue() instanceof DoubleValue){
			valueTypeCombo.setSelectedIndex(3);
		}
		// Value et Offset
		if(property.getValue()!=null){
			valueField.setText(property.getValue().toString());
			if(property.getValue().getOffset()!=null){
				offsetField.setText(property.getValue().getOffset().toString());
			}
		}
		// Max
		if(property.getMax()!=null){
			maxField.setText(property.getMax().toString());
		}
		// Min
		if(property.getMin()!=null){
			minField.setText(property.getMin().toString());
		}
		// DefaultHistory
		defaultHistoryCheckBox.setEnabled(false);
		editDefaultHistoryButton.addActionListener(new Treatment(new EditDefaultHistoryWork(this)));
		addRemoveDefaultHistoryButton.addActionListener(new Treatment(new AddRemoveHistoryWork(this)));
		// Renderer
		if(property.getRenderer()!=Renderer.DEFAULT_RENDERER){
			rendererField.setText(property.getRenderer().getClass().getName());
		}
		// Choix de valeur
		choiceCheckBox.setSelected(property.getOptions()!=null);
		if(property.getOptions()!=null){
			for(Value option : property.getOptions()){
				choiceListModel.addElement(option.getString());
			}
			choiceList.setModel(choiceListModel);
		}
		int buttonWidth = addOptionButton.getPreferredSize().width;
		buttonWidth = Math.max(buttonWidth, editOptionButton.getPreferredSize().width);
		buttonWidth = Math.max(buttonWidth, removeOptionButton.getPreferredSize().width);
		addOptionButton.setPreferredSize(new Dimension(buttonWidth, addOptionButton.getPreferredSize().height));
		editOptionButton.setPreferredSize(new Dimension(buttonWidth, editOptionButton.getPreferredSize().height));
		removeOptionButton.setPreferredSize(new Dimension(buttonWidth, removeOptionButton.getPreferredSize().height));
		addOptionButton.addActionListener(new SimpleEDTAction(this,"addOption"));
		editOptionButton.addActionListener(new SimpleEDTAction(this,"editOption"));
		removeOptionButton.addActionListener(new SimpleEDTAction(this, "removeSelectedOptions"));
		// Comment
		commentTextArea.setText(property.getComment());
		commentScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		// ValidateButton
		validateButton.addActionListener(new SimpleEDTAction(this, "submit"));
		// SubProperties
		subPropertiesCheckBox.setEnabled(false);
		subPropertiesCheckBox.setSelected(property.getSubProperties()!=null);
		addRemoveSubPropertiesButton.addActionListener(new Treatment(new AddRemoveSubPropertiesWork(this, property)));
		editSubPropertiesButton.addActionListener(new Treatment(new EditSubPropertiesWork(this, property)));
		// Calcul des "enable" des champs
		fieldEnability();
		
		initLayout();
	}
	
	public void fieldEnability(){
		if(valueTypeCombo.getSelectedIndex()==0){
			valueField.setEnabled(false);
			offsetField.setEnabled(false);
			minField.setEnabled(false);
			maxField.setEnabled(false);
			choiceCheckBox.setSelected(false);
		}else if(valueTypeCombo.getSelectedIndex()==1){
			valueField.setEnabled(true);
			offsetField.setEnabled(true);
			minField.setEnabled(false);
			maxField.setEnabled(false);
		}else if(valueTypeCombo.getSelectedIndex()==2){
			valueField.setEnabled(true);
			offsetField.setEnabled(true);
			minField.setEnabled(true);
			maxField.setEnabled(true);
		}else if(valueTypeCombo.getSelectedIndex()==3){
			valueField.setEnabled(true);
			offsetField.setEnabled(true);
			minField.setEnabled(true);
			maxField.setEnabled(true);
		}
		specificationField.setEnabled(specificationCheckBox.isSelected());
		removeOptionButton.setVisible(choiceCheckBox.isSelected());
		editOptionButton.setVisible(choiceCheckBox.isSelected());
		addOptionButton.setVisible(choiceCheckBox.isSelected());
		choiceScroller.setVisible(choiceCheckBox.isSelected());
		editSubPropertiesButton.setEnabled(property.getSubProperties()!=null);
		subPropertiesCheckBox.setSelected(property.getSubProperties()!=null);
		if(property.getSubProperties()==null){
			addRemoveSubPropertiesButton.setText("Ajouter");
		}else{
			addRemoveSubPropertiesButton.setText("Suppr.");
		}
		defaultHistoryCheckBox.setSelected(property.getActualDefaultHistory()!=null);
		editDefaultHistoryButton.setEnabled(property.getActualDefaultHistory()!=null);
		addRemoveDefaultHistoryButton.setText(property.getActualDefaultHistory()==null?"Ajouter":"Suppr.");
		
		pack();
		
	}

	
	private void initLayout(){
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(nameLabel)
						.addComponent(specificationLabel)
						.addComponent(editableLabel)
						.addComponent(canBeRemovedLabel)
						.addComponent(valueTypeLabel)
						.addComponent(valueLabel)
						.addComponent(offsetLabel)
						.addComponent(maxLabel)
						.addComponent(minLabel)
						.addComponent(defaultHistoryLabel)
						.addComponent(rendererLabel)
						.addComponent(choiceLabel)
						.addComponent(addOptionButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(editOptionButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(removeOptionButton,GroupLayout.Alignment.CENTER,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
						.addComponent(commentLabel)
						.addComponent(subPropertieslabel)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
						.addComponent(nameField)
						.addGroup(layout.createSequentialGroup()
							.addComponent(specificationCheckBox)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(specificationField)
						)
						.addComponent(editableCheckBox)
						.addComponent(canBeRemovedCheckBox)
						.addComponent(valueTypeCombo)
						.addComponent(valueField)
						.addComponent(offsetField)
						.addComponent(maxField)
						.addComponent(minField)
						.addGroup(layout.createSequentialGroup()
							.addComponent(defaultHistoryCheckBox)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(addRemoveDefaultHistoryButton)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(editDefaultHistoryButton)
						)
						.addComponent(rendererField)
						.addComponent(choiceCheckBox)
						.addComponent(choiceScroller)
						.addComponent(commentScrollPane)
						.addGroup(layout.createSequentialGroup()
							.addComponent(subPropertiesCheckBox)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(addRemoveSubPropertiesButton)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(editSubPropertiesButton)
						)
							
					)
				)
				.addComponent(validateButton, GroupLayout.Alignment.CENTER)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(nameLabel)
				.addComponent(nameField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(specificationLabel)
				.addComponent(specificationCheckBox)
				.addComponent(specificationField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(editableLabel)
				.addComponent(editableCheckBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(canBeRemovedLabel)
				.addComponent(canBeRemovedCheckBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(valueTypeLabel)
				.addComponent(valueTypeCombo)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(valueLabel)
				.addComponent(valueField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(offsetLabel)
				.addComponent(offsetField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(maxLabel)
				.addComponent(maxField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(minLabel)
				.addComponent(minField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(defaultHistoryLabel)
				.addComponent(defaultHistoryCheckBox)
				.addComponent(addRemoveDefaultHistoryButton)
				.addComponent(editDefaultHistoryButton)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(rendererLabel)
				.addComponent(rendererField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(choiceLabel)
				.addComponent(choiceCheckBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addGroup(layout.createSequentialGroup()
					.addComponent(addOptionButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(editOptionButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(removeOptionButton)
				)
				.addComponent(choiceScroller, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, true)
				.addComponent(commentLabel)
				.addComponent(commentScrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(subPropertieslabel)
				.addComponent(subPropertiesCheckBox)
				.addComponent(addRemoveSubPropertiesButton)
				.addComponent(editSubPropertiesButton)
			)
			.addGap(20)
			.addComponent(validateButton)
			.addContainerGap()
		);
	}
	
	public void addOption(){
		String option = JOptionPane.showInputDialog(this, "Nouvelle option");
		if(option != null) {
			choiceListModel.addElement(option);
			pack();
		}
	}
	
	public void editOption(){
		int selectedIndex = choiceList.getSelectedIndex();
		if(selectedIndex >= 0){
			Object oldValue = choiceList.getSelectedValue();
			String option = JOptionPane.showInputDialog(this, "Editer", oldValue);
			if(option != null){
				choiceListModel.setElementAt(option, selectedIndex);
				pack();
			}
		}
	}
	
	public void removeSelectedOptions(){
		while(choiceList.getSelectedIndices().length>0){
			choiceListModel.remove(choiceList.getSelectedIndices()[0]);
		}
		pack();
	}
	
	private String findError(){
		if(nameField.getText().isEmpty()){
			return "Le nom ne doit pas être vide";
		}
		if(valueTypeCombo.getSelectedIndex()==2){
			if(!valueField.getText().matches("[-]?[0-9]+")){
				return "La valeur n'est pas un entier";
			}
			if(!offsetField.getText().isEmpty() && !offsetField.getText().matches("[-]?[0-9]+")){
				return "L'offset n'est pas un entier";
			}
			if(!maxField.getText().isEmpty() && !maxField.getText().matches("[-]?[0-9]+")){
				return "Le maximum n'est pas un entier";
			}
			if(!minField.getText().isEmpty() &&!minField.getText().matches("[-]?[0-9]+")){
				return "Le minimum n'est pas un entier";
			}
		}
		if(valueTypeCombo.getSelectedIndex()==3){
			if(!valueField.getText().matches("[-]?[0-9]+([.][0-9]+)?")){
				return "La valeur n'est pas un décimal";
			}
			if(!offsetField.getText().isEmpty() && !offsetField.getText().matches("[-]?[0-9]+([.][0-9]+)?")){
				return "L'offset n'est pas un décimal";
			}
			if(!maxField.getText().isEmpty() && !maxField.getText().matches("[-]?[0-9]+([.][0-9]+)?")){
				return "Le maximum n'est pas un décimal";
			}
			if(!minField.getText().isEmpty() && !minField.getText().matches("[-]?[0-9]+([.][0-9]+)?")){
				return "Le minimum n'est pas un décimal";
			}
		}
		if(!rendererField.getText().isEmpty()){
			try {
				property.setRenderer((Renderer) GenCrossEditor.getInstance().getRepositoryManager().getRepositoryClassLoader().loadClass(rendererField.getText()).newInstance());
			} catch (InstantiationException e) {
				return "Impossible de charger la class de rendu: "+e.getMessage();
			} catch (IllegalAccessException e) {
				return "Impossible de charger la class de rendu: "+e.getMessage();
			} catch (ClassNotFoundException e) {
				return "Impossible de charger la class de rendu: "+e.getMessage();
			}
		}
		if(choiceCheckBox.isSelected()){
			for(int i=0; i<choiceListModel.size(); i++){
				if(valueTypeCombo.getSelectedIndex()==2){
					if(!choiceListModel.getElementAt(i).toString().matches("[-]?[0-9]+")){
						return "L'option "+choiceListModel.getElementAt(i)+" n'est pas un entier";
					}
				}else if(valueTypeCombo.getSelectedIndex()==3){
					if(!choiceListModel.getElementAt(i).toString().matches("[-]?[0-9]+[.]?[0-9]+")){
						return "L'option "+choiceListModel.getElementAt(i)+" n'est pas un décimal";
					}
				}
			}
		}
		return null;
	}
	
	public void submit() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String error = findError();
		if(error!=null){
			JOptionPane.showMessageDialog(this, error, "Propriété invalide", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		property.setName(nameField.getText());
		property.setSpecification(specificationCheckBox.isSelected()?specificationField.getText():null);
		property.setEditable(editableCheckBox.isSelected());
		property.setRemovable(canBeRemovedCheckBox.isSelected());
		Value value = null;
		Value min = null;
		Value max = null;
		if(valueTypeCombo.getSelectedIndex()==1){
			value = new StringValue(valueField.getText());
			if(!offsetField.getText().isEmpty()){
				String offset = offsetField.getText();
				value.setOffset(offset);
			}
		}else if(valueTypeCombo.getSelectedIndex()==2){
			value = new IntValue(Integer.parseInt(valueField.getText()));
			if(!offsetField.getText().isEmpty()){
				int offset = Integer.parseInt(offsetField.getText());
				if(offset!=1){
					value.setOffset(offset);
				}
			}
			if(!minField.getText().isEmpty()){
				min = new IntValue(Integer.parseInt(minField.getText()));
			}
			if(!maxField.getText().isEmpty()){
				max = new IntValue(Integer.parseInt(maxField.getText()));
			}
		}else if(valueTypeCombo.getSelectedIndex()==3){
			value = new DoubleValue(Double.parseDouble(valueField.getText()));
			if(!offsetField.getText().isEmpty()){
				double offset = Double.parseDouble(offsetField.getText());
				if(offset!=1.0){
					value.setOffset(offset);
				}
			}
			if(!minField.getText().isEmpty()){
				min = new DoubleValue(Double.parseDouble(minField.getText()));
			}
			if(!maxField.getText().isEmpty()){
				max = new DoubleValue(Double.parseDouble(maxField.getText()));
			}
		}
		property.setValue(value);
		property.setMin(min);
		property.setMax(max);
		if(!rendererField.getText().isEmpty()){
			property.setRenderer((Renderer) GenCrossEditor.getInstance().getRepositoryManager().getRepositoryClassLoader().loadClass(rendererField.getText()).newInstance());
		}else{
			property.setRenderer(null);
		}
		if(choiceCheckBox.isSelected() && valueTypeCombo.getSelectedIndex()>0){
			List<Value> options = new ArrayList<Value>();
			for(int i=0; i<choiceListModel.size(); i++){
				if(valueTypeCombo.getSelectedIndex()==1){
					options.add(new StringValue(choiceListModel.getElementAt(i).toString()));
				}else if(valueTypeCombo.getSelectedIndex()==2){
					options.add(new IntValue(Integer.parseInt(choiceListModel.getElementAt(i).toString())));
				}else if(valueTypeCombo.getSelectedIndex()==3){
					options.add(new DoubleValue(Double.parseDouble(choiceListModel.getElementAt(i).toString())));
				}
			}
			property.setOptions(options);
		}else{
			property.setOptions((List<Value>) null);
		}
		validateData();
	}

	@Override
	public Property getResult() {
		return property;
	}
	
	public Property getProperty(){
		return property;
	}
	
}
