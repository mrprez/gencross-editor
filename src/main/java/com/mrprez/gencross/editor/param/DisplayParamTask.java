package com.mrprez.gencross.editor.param;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.StandardDialog;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayParamTask extends StandardDialog {
	private JLabel wsUrlLabel = new JLabel("URL des web services gencross :");
	private JTextField wsUrlField = new JTextField();
	private JLabel lookFeelLabel = new JLabel("Look & Feel :");
	private JComboBox<String> lookFeelField = buildLookFellComboBox();

	
	@Override
	public JDialog buildDialog() {
		JDialog dialog = new JDialog(GencrossEditor.getInstance());
		dialog.setTitle("Paramètres");
		
		wsUrlField.setText(GencrossEditor.getInstance().getGencrossWebUrl());
		
		GroupLayout layout = new GroupLayout(dialog.getRootPane());
		dialog.getRootPane().setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addComponent(wsUrlLabel)
						.addComponent(lookFeelLabel)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup()
						.addComponent(wsUrlField)
						.addComponent(lookFeelField)
					)
				)
				.addGroup(layout.createSequentialGroup()
					.addComponent(okButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(cancelButton)
				)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(wsUrlLabel)
				.addComponent(wsUrlField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(lookFeelLabel)
				.addComponent(lookFeelField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(okButton)
				.addComponent(cancelButton)
			)
			.addContainerGap()
		);
		
		return dialog;
	}
	
	
	private JComboBox<String> buildLookFellComboBox(){
		JComboBox<String> combo = new JComboBox<>();
		
		LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
		
		for(LookAndFeelInfo lookAndFeelInfo : lookAndFeelInfos){
			combo.addItem(lookAndFeelInfo.getName());
			if(lookAndFeelInfo.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())){
				combo.setSelectedItem(lookAndFeelInfo.getName());
			}
		}
		
		System.out.println(UIManager.getLookAndFeel().getClass().getName());
		
		combo.setSelectedItem(UIManager.getLookAndFeel());
		
		return combo;
	}
	

	@Override
	public Task getValidTask() {
		return new EdtParamValidationTask(this);
	}

	@Override
	public Task getCancelTask() {
		return null;
	}
	
	public String getSelectedLookAndFeelClassName(){
		LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
		for(LookAndFeelInfo lookAndFeelInfo : lookAndFeelInfos){
			if(lookAndFeelInfo.getName().equals(lookFeelField.getSelectedItem())){
				return lookAndFeelInfo.getClassName();
			}
		}
		return UIManager.getLookAndFeel().getClass().getName();
	}
	
	public String getWsUrl(){
		return wsUrlField.getText();
	}

}
