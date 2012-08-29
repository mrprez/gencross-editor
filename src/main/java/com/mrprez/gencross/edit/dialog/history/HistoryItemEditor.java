package com.mrprez.gencross.edit.dialog.history;

import java.awt.Dialog;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.framework.EditDialog;
import com.mrprez.gencross.edit.framework.SimpleEDTAction;
import com.mrprez.gencross.history.HistoryItem;
import com.mrprez.gencross.value.DoubleValue;
import com.mrprez.gencross.value.IntValue;
import com.mrprez.gencross.value.StringValue;
import com.mrprez.gencross.value.Value;

public class HistoryItemEditor extends EditDialog<HistoryItem> {
	private static final long serialVersionUID = 1L;
	
	private JLabel classLabel = new JLabel("Classe");
	private JTextField classField = new JTextField();
	private DefaultTableModel argsModel = new DefaultTableModel(new Object[]{"Cl�", "Valeur"}, 0);
	private JTable argsTable = new JTable(argsModel);
	private JScrollPane argsPane = new JScrollPane(argsTable);
	private JLabel pointPoolLabel = new JLabel("Pool de points");
	private DefaultComboBoxModel pointPoolModel = new DefaultComboBoxModel();
	private JComboBox pointPoolComboBox = new JComboBox(pointPoolModel);
	private JLabel phaseLabel = new JLabel("Phase");
	private JComboBox phaseComboBox = new JComboBox();
	private JLabel absoluteNameLabel = new JLabel("Nom absolue");
	private JTextField absoluteNameField = new JTextField();
	private JLabel oldValueLabel = new JLabel("Ancienne valeur");
	private JComboBox oldValueTypeComboBox = new JComboBox(new String[]{"Aucune", "Texte", "Entier", "D�cimal"});
	private JTextField oldValueField = new JTextField();
	private JLabel newValueLabel = new JLabel("Nouvelle valeur");
	private JComboBox newValueTypeComboBox = new JComboBox(new String[]{"Aucune", "Texte", "Entier", "D�cimal"});
	private JTextField newValueField = new JTextField();
	private JLabel actionLabel = new JLabel("type d'action");
	private JComboBox actionComboBox = new JComboBox(new String[]{"D�faut","Cr�ation","Modification","Suppression"});
	private JLabel dateLabel = new JLabel("Date");
	private JTextField yearField = new JTextField(4);
	private JLabel dateSeparator1 = new JLabel("/");
	private JTextField monthField = new JTextField(2);
	private JLabel dateSeparator2 = new JLabel("/");
	private JTextField dayField = new JTextField(2);
	private JTextField hourField = new JTextField(2);
	private JLabel dateSeparator3 = new JLabel(":");
	private JTextField minuteField = new JTextField(2);
	private JLabel dateSeparator4 = new JLabel(":");
	private JTextField secondField = new JTextField(2);
	private JLabel dateSeparator5 = new JLabel(",");
	private JTextField milliSecondField = new JTextField(3);
	
	private JButton validationButton = new JButton("OK");
	
	private HistoryItem historyItem;
	
	
	public HistoryItemEditor(Dialog dialog, HistoryItem historyItem) {
		super(dialog);
		this.historyItem = historyItem;
		setTitle("El�ment d'Historique");
	}

	@Override
	public HistoryItem getResult() {
		return historyItem;
	}

	@Override
	public void init() throws Exception {
		// Class
		classField.setText(historyItem.getClass().getCanonicalName());
		// Point pool
		pointPoolModel.removeAllElements();
		for(String pointPoolName : GenCrossEditor.getInstance().getPersonnage().getPointPools().keySet()){
			pointPoolModel.addElement(pointPoolName);
			if(pointPoolName.equals(historyItem.getPointPool())){
				pointPoolModel.setSelectedItem(pointPoolName);
			}
		}
		// Phase
		phaseComboBox.addItem("");
		for(String phase : GenCrossEditor.getInstance().getPersonnage().getPhaseList()){
			phaseComboBox.addItem(phase);
			if(phase.equals(historyItem.getPhase())){
				phaseComboBox.setSelectedItem(phase);
			}
		}
		// Action
		actionComboBox.setSelectedIndex(historyItem.getAction());
		// Absolute Name
		absoluteNameField.setText(historyItem.getAbsoluteName());
		// Old Value
		if(historyItem.getOldValue()!=null){
			oldValueField.setText(historyItem.getOldValue().toString());
		}
		oldValueTypeComboBox.addActionListener(new SimpleEDTAction(this, "valueFieldEnability"));
		// New Value
		if(historyItem.getNewValue()!=null){
			newValueField.setText(historyItem.getNewValue().toString());
		}
		newValueTypeComboBox.addActionListener(new SimpleEDTAction(this, "valueFieldEnability"));
		valueFieldEnability();
		// Date
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(historyItem.getDate());
		yearField.setText(new SimpleDateFormat("yyyy").format(historyItem.getDate()));
		monthField.setText(new SimpleDateFormat("MM").format(historyItem.getDate()));
		dayField.setText(new SimpleDateFormat("dd").format(historyItem.getDate()));
		hourField.setText(new SimpleDateFormat("HH").format(historyItem.getDate()));
		minuteField.setText(new SimpleDateFormat("mm").format(historyItem.getDate()));
		secondField.setText(new SimpleDateFormat("ss").format(historyItem.getDate()));
		milliSecondField.setText(new SimpleDateFormat("SSS").format(historyItem.getDate()));
		// Args
		for(String key : historyItem.getArgs().keySet()){
			argsModel.addRow(new String[]{key,historyItem.getArgs().get(key)});
		}
		argsModel.addRow(new String[]{"",""});
		argsPane.setPreferredSize(new Dimension(argsTable.getPreferredSize().width, argsTable.getPreferredSize().height*2));
		argsModel.addTableModelListener(new ArgsTableListener(argsModel, this));
		// ValidateButton
		validationButton.addActionListener(new SimpleEDTAction(this, "submit"));
		
		initLayout();
		
	}
	
	
	
	private void initLayout(){
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(classLabel)
						.addComponent(pointPoolLabel)
						.addComponent(phaseLabel)
						.addComponent(actionLabel)
						.addComponent(absoluteNameLabel)
						.addComponent(oldValueLabel)
						.addComponent(newValueLabel)
						.addComponent(dateLabel)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
						.addComponent(classField)
						.addComponent(pointPoolComboBox)
						.addComponent(phaseComboBox)
						.addComponent(actionComboBox)
						.addComponent(absoluteNameField)
						.addGroup(layout.createSequentialGroup()
							.addComponent(oldValueTypeComboBox, GroupLayout.DEFAULT_SIZE, oldValueTypeComboBox.getMinimumSize().width, oldValueTypeComboBox.getMinimumSize().width)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(oldValueField)
						)
						.addGroup(layout.createSequentialGroup()
							.addComponent(newValueTypeComboBox, GroupLayout.DEFAULT_SIZE, newValueTypeComboBox.getMinimumSize().width, newValueTypeComboBox.getMinimumSize().width)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(newValueField)
						)
						.addGroup(layout.createSequentialGroup()
							.addComponent(yearField)
							.addComponent(dateSeparator1)
							.addComponent(monthField)
							.addComponent(dateSeparator2)
							.addComponent(dayField)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(hourField)
							.addComponent(dateSeparator3)
							.addComponent(minuteField)
							.addComponent(dateSeparator4)
							.addComponent(secondField)
							.addComponent(dateSeparator5)
							.addComponent(milliSecondField)
						)
					)
				)
				.addComponent(argsPane)
				.addComponent(validationButton, GroupLayout.Alignment.CENTER)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(classLabel)
				.addComponent(classField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(pointPoolLabel)
				.addComponent(pointPoolComboBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(phaseLabel)
				.addComponent(phaseComboBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(actionLabel)
				.addComponent(actionComboBox)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(absoluteNameLabel)
				.addComponent(absoluteNameField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(oldValueLabel)
				.addComponent(oldValueTypeComboBox)
				.addComponent(oldValueField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(newValueLabel)
				.addComponent(newValueTypeComboBox)
				.addComponent(newValueField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(dateLabel)
				.addComponent(yearField)
				.addComponent(dateSeparator1)
				.addComponent(monthField)
				.addComponent(dateSeparator2)
				.addComponent(dayField)
				.addComponent(hourField)
				.addComponent(dateSeparator3)
				.addComponent(minuteField)
				.addComponent(dateSeparator4)
				.addComponent(secondField)
				.addComponent(dateSeparator5)
				.addComponent(milliSecondField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(argsPane)
			.addGap(20)
			.addComponent(validationButton)
			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
	}
	
	public void valueFieldEnability(){
		oldValueField.setEnabled(oldValueTypeComboBox.getSelectedIndex()!=0);
		newValueField.setEnabled(newValueTypeComboBox.getSelectedIndex()!=0);
	}
	
	public String findError(){
		// Class
		try {
			GenCrossEditor.getInstance().getRepositoryManager().getRepositoryClassLoader().loadClass(classField.getText());
		} catch (ClassNotFoundException e) {
			return "La classe \""+classField.getText()+"\" est introuvable";
		}
		// Old Value
		if(oldValueTypeComboBox.getSelectedIndex()==2){
			if(!oldValueField.getText().matches("[-]?[0-9]+")){
				return "L'ancienne valeur n'est pas un entier";
			}
		}
		if(oldValueTypeComboBox.getSelectedIndex()==3){
			if(!oldValueField.getText().matches("[-]?[0-9]+([.][0-9]+)?")){
				return "L'ancienne valeur n'est pas un d�cimal";
			}
		}
		//New Value
		if(newValueTypeComboBox.getSelectedIndex()==2){
			if(!newValueField.getText().matches("[-]?[0-9]+")){
				return "La nouvelle valeur n'est pas un entier";
			}
		}
		if(newValueTypeComboBox.getSelectedIndex()==3){
			if(!newValueField.getText().matches("[-]?[0-9]+([.][0-9]+)?")){
				return "La nouvelle valeur n'est pas un d�cimal";
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		try {
			dateFormat.parse(getStringDate());
		} catch (ParseException e) {
			return "Date invalide: "+e.getMessage();
		}
		
		return null;
	}
	
	public void submit() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, ParseException{
		String error = findError();
		if(error!=null){
			JOptionPane.showMessageDialog(this, error, "Historique invalide", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Element historyElement = new DefaultElement("historyItem");
		// Class
		historyElement.addAttribute("class", classField.getText());
		// Action
		historyElement.addAttribute("action", ""+actionComboBox.getSelectedIndex());
		// Point pool
		historyElement.addElement("pointPool").setText((String) pointPoolComboBox.getSelectedItem());
		// Date
		Date date = new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(getStringDate());
		historyElement.addElement("date").setText(""+date.getTime());
		// AbsoluteName
		historyItem.setAbsoluteName(absoluteNameField.getText());
		if(!absoluteNameField.getText().trim().isEmpty()){
			historyElement.addElement("absoluteName").setText(absoluteNameField.getText());
		}
		// Old Value
		Value oldValue;
		switch(oldValueTypeComboBox.getSelectedIndex()){
		case 1:
			oldValue = new StringValue(oldValueField.getText());
			break;
		case 2:
			oldValue = new IntValue(Integer.parseInt(oldValueField.getText()));
			break;
		case 3:
			oldValue = new DoubleValue(Double.parseDouble(oldValueField.getText()));
			break;
		default:
			oldValue = null;
		}
		if(oldValue!=null){
			historyElement.addElement("oldValue").add(oldValue.getXML());
		}
		// New Value
		Value newValue;
		switch(newValueTypeComboBox.getSelectedIndex()){
		case 1:
			newValue = new StringValue(newValueField.getText());
			break;
		case 2:
			newValue = new IntValue(Integer.parseInt(newValueField.getText()));
			break;
		case 3:
			newValue = new DoubleValue(Double.parseDouble(newValueField.getText()));
			break;
		default:
			newValue = null;
		}
		if(newValue!=null){
			historyElement.addElement("newValue").add(newValue.getXML());
		}
		// Phase
		if(phaseComboBox.getSelectedIndex()!=0){
			historyElement.addElement("phase").setText((String)phaseComboBox.getSelectedItem());
		}
		// Args
		Element argsElement = historyElement.addElement("args");
		for(int i=0; i<argsModel.getRowCount()-1; i++){
			String key = (String) argsModel.getValueAt(i, 0);
			String value = (String) argsModel.getValueAt(i, 1);
			Element argElement = argsElement.addElement("arg");
			argElement.addAttribute("key", key);
			argElement.setText(value);
		}
		
		this.historyItem = HistoryItem.createHistoryItem(historyElement, GenCrossEditor.getInstance().getRepositoryManager().getRepositoryClassLoader());
		
		validateData();
	}
	
	
	private String getStringDate(){
		return yearField.getText()+monthField.getText()+dayField.getText()+hourField.getText()+minuteField.getText()+secondField.getText()+milliSecondField.getText();
	}

}
