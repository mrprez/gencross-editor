package com.mrprez.gencross.edit.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.GenCrossEditorPanel;
import com.mrprez.gencross.edit.error.ErrorFrame;
import com.mrprez.gencross.edit.framework.SimpleEDTAction;

public class XmlPanel extends GenCrossEditorPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextArea xmlArea = new JTextArea();
	private JScrollPane xmlScrollPane = new JScrollPane(xmlArea);
	private JButton xmlValidationButton = new JButton("Valider");
	
	
	

	public XmlPanel(Personnage personnage) throws IOException, SecurityException, NoSuchMethodException {
		super();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PersonnageSaver.savePersonnage(GenCrossEditor.getInstance().getPersonnage(), os);
		setText(new String(os.toByteArray(), Charset.forName("UTF-8")));
		
		xmlScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
		xmlScrollPane.setPreferredSize(this.getSize());
		
		xmlValidationButton.addActionListener(new SimpleEDTAction(this, "validateXml"));
		xmlArea.addKeyListener(new TypeTextListener(this));
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(xmlScrollPane)
				.addComponent(xmlValidationButton)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(xmlScrollPane)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(xmlValidationButton)
			.addContainerGap()
		);
	}
	
	public void validateXml(){
		if(isDataValid()){
			JTabbedPane tabbedPane = GenCrossEditor.getInstance().getTabbedPane();
			for(int i=0; i<tabbedPane.getTabCount(); i++){
				tabbedPane.setEnabledAt(i, true);
			}
		}
	}
	
	public void setText(String xmlText){
		xmlArea.setText(xmlText);
	}

	@Override
	public Personnage impact() throws Exception {
		ByteArrayInputStream is = new ByteArrayInputStream(xmlArea.getText().getBytes(Charset.forName("UTF-8")));
		return PersonnageFactory.getInstance().createPersonnage(is);
	}

	@Override
	public void refresh() throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PersonnageSaver.savePersonnage(GenCrossEditor.getInstance().getPersonnage(), os);
		setText(new String(os.toByteArray(), Charset.forName("UTF-8")));
	}

	@Override
	public boolean isDataValid() {
		ByteArrayInputStream is = new ByteArrayInputStream(xmlArea.getText().getBytes(Charset.forName("UTF-8")));
		try {
			PersonnageFactory.getInstance().createPersonnage(is);
		} catch (Exception e) {
			ErrorFrame.displayError(e);
			return false;
		}
		return true;
	}
	
	

}
