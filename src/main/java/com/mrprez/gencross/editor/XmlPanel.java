package com.mrprez.gencross.editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.SwingUtilities;

public class XmlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel positionLabel = new JLabel("Curseur 0 : 0");
	
	
	
	public XmlPanel(){
		super();
		
		textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public synchronized void keyPressed(KeyEvent e) {
				if( ! GencrossEditor.getInstance().isTextModified()){
					GencrossEditor.getInstance().setTextModified(true);
				}
			}
		});
		
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				updatePosition();
			}
		});
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(scrollPane)
				.addComponent(positionLabel)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(scrollPane)
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addComponent(positionLabel)
			.addContainerGap()
		);
		
	}
	
	public void setPersonnageXml(String xml){
		textArea.setText(xml);
		SwingUtilities.invokeLater(new ScrollBarUp());
	}

	public String getPersonnageXml() {
		return textArea.getText();
	}
	
	
	private void updatePosition(){
		String before = textArea.getText().substring(0, textArea.getCaretPosition());
		int i=-1;
		int line=0;
		while(before.indexOf('\n', i+1)>=0){
			i=before.indexOf('\n', i+1);
			line++;
		}
		positionLabel.setText("Curseur "+line+" : "+(before.length()-i-1));
	}
	
	
	private class ScrollBarUp implements Runnable{
		@Override
		public void run() {
			scrollPane.getVerticalScrollBar().setValue(0);
		}
	}

}
