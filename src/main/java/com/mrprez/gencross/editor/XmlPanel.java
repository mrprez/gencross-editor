package com.mrprez.gencross.editor;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class XmlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	
	
	public XmlPanel(){
		super();
		
		textArea = new JTextArea();
		textArea.setTabSize(4);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scrollPane)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scrollPane)
			.addContainerGap()
		);
		
	}
	
	public void setPersonnageXml(String xml){
		textArea.setText(xml);
	}

	public String getPersonnageXml() {
		return textArea.getText();
	}

}
