package com.mrprez.gencross.editor;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class XmlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	
	
	public XmlPanel(){
		super();
		
		textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createLoweredBevelBorder());
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(textArea)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(textArea)
			.addContainerGap()
		);
		
	}

}
