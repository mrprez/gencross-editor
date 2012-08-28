package com.mrprez.gencross.edit.tree;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.GenCrossEditorPanel;
import com.mrprez.gencross.edit.framework.Treatment;

public class TreePanel extends GenCrossEditorPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPointPanel = new JPanel();
	private JPanel rightPointPanel = new JPanel();
	private PropertyTree propertyTree;
	private JScrollPane treeScrollPane;
	
	private ImageIcon modifyIcon = new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/modify-icone.png")));
	
	
	
	public TreePanel(Personnage personnage) throws HeadlessException, IOException{
		super();
		propertyTree = new PropertyTree(personnage);
		treeScrollPane = new JScrollPane(propertyTree);
		
		// Points Panel
		leftPointPanel.setLayout(new GridBagLayout());
		rightPointPanel.setLayout(new GridBagLayout());
		leftPointPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		rightPointPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		// Layout
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(leftPointPanel)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(rightPointPanel)
				)
				.addComponent(treeScrollPane)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
				.addComponent(leftPointPanel)
				.addComponent(rightPointPanel)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(treeScrollPane)
			.addContainerGap()
		);
		
		
	}
	
	public void reinitPointPoolPanels() throws HeadlessException, IOException{
		// PointPool
		leftPointPanel.removeAll();
		rightPointPanel.removeAll();
		
		Personnage personnage = GenCrossEditor.getInstance().getPersonnage();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(2, 5, 2, 15);
		int gridy = 0;
		int column = 0;
		for(String name : personnage.getPointPools().keySet()){
			JPanel currentPanel = column==0?leftPointPanel:rightPointPanel;
			constraints.gridy = gridy;
			constraints.gridx = 0;
			constraints.weightx = 1.0;
			currentPanel.add(new JLabel(name),constraints);
			constraints.weightx = 0.0;
			constraints.gridx = 1;
			currentPanel.add(new JLabel(personnage.getPointPools().get(name).toString()), constraints);
			constraints.gridx = 2;
			JButton editIcon = new JButton(modifyIcon);
			editIcon.setPreferredSize(new Dimension(modifyIcon.getIconWidth()+4, modifyIcon.getIconHeight()+4));
			editIcon.addActionListener(new Treatment(new EditPointPoolWork(personnage.getPointPools().get(name))));//(currentPanel, personnage.getPointPools().get(name)));
			currentPanel.add(editIcon, constraints);
			gridy++;
			column = column==0?1:0;
		}
		JPanel currentPanel = column==0?leftPointPanel:rightPointPanel;
		constraints.gridy = gridy;
		constraints.gridx = 0;
		//constraints.gridwidth = 3;
		JButton addButton = new JButton("Ajouter");
		addButton.addActionListener(new Treatment(new AddPointPoolWork()));
		currentPanel.add(addButton, constraints);
		
		SwingUtilities.updateComponentTreeUI(leftPointPanel);
		SwingUtilities.updateComponentTreeUI(rightPointPanel);

		
	}

	@Override
	public Personnage impact() {
		return propertyTree.getPersonnage();
	}

	@Override
	public void refresh() throws Exception {
		if(propertyTree.getPersonnage()!=GenCrossEditor.getInstance().getPersonnage()){
			propertyTree.setPersonnage(GenCrossEditor.getInstance().getPersonnage());
			reinitPointPoolPanels();
		}else{
			propertyTree.refresh();
			reinitPointPoolPanels();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	public PropertyTree getPropertyTree() {
		return propertyTree;
	}
	
	

	

	

	
	
	

}
