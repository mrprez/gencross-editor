package com.mrprez.gencross.edit.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.error.ErrorFrame;
import com.mrprez.gencross.edit.framework.ReflectivBackgroundWork;
import com.mrprez.gencross.edit.framework.Treatment;

public class XmlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextArea xmlArea = new JTextArea();
	private JScrollPane xmlScrollPane = new JScrollPane(xmlArea);
	private JLabel positionLabel = new JLabel("1 : 1");
	private JButton xmlValidationButton = new JButton("Valider");
	
	

	public XmlPanel() throws IOException, SecurityException, NoSuchMethodException {
		super();
		
		xmlScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
		xmlScrollPane.setPreferredSize(this.getSize());
		
		xmlArea.addCaretListener(new TypeTextKeyListener(xmlArea, positionLabel));
		xmlArea.setTabSize(2);
		
		xmlValidationButton.addActionListener(new Treatment(new ReflectivBackgroundWork(this, "validateXml"), true, GenCrossEditor.getInstance()));
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(xmlScrollPane)
				.addComponent(positionLabel, GroupLayout.Alignment.LEADING)
				.addComponent(xmlValidationButton)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(xmlScrollPane)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(positionLabel)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(xmlValidationButton)
			.addContainerGap()
		);
	}
	
	public void setText(String xmlText){
		xmlArea.setText(xmlText);
	}
	
	public String getText(){
		return xmlArea.getText();
	}

	public void validateXml() {
		if(isValidXml()){
			JOptionPane.showMessageDialog(GenCrossEditor.getInstance(), "XML valide", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public boolean isValidXml(){
		ByteArrayInputStream is = new ByteArrayInputStream(xmlArea.getText().getBytes(Charset.forName("UTF-8")));
		try {
			GenCrossEditor.getInstance().getPersonnageFactory().loadPersonnage(is);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorFrame.displayError(e);
			return false;
		}
		return true;
	}
	
	

}
