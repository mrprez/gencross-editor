package com.mrprez.gencross.edit.listeners;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.GenCrossEditor;
import com.mrprez.gencross.edit.GenCrossEditorPanel;
import com.mrprez.gencross.listener.Listener;
import com.mrprez.gencross.listener.PassToNextPhaseListener;
import com.mrprez.gencross.listener.PropertyListener;

public class ListenerPanel extends GenCrossEditorPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane(contentPanel);
	private JPanel beforeAddPanel = new JPanel();
	private JPanel afterAddPanel = new JPanel();
	private JPanel beforeChangePanel = new JPanel();
	private JPanel afterChangePanel = new JPanel();
	private JPanel beforeRemovePanel = new JPanel();
	private JPanel afterRemovePanel = new JPanel();
	private JPanel phasePanel = new JPanel();
	private JPanel formulaPanel = new JPanel();
	
	
	public ListenerPanel(Personnage personnage) throws Exception {
		super();
		beforeAddPanel.setBorder(BorderFactory.createTitledBorder("Listener avant ajout"));
		afterAddPanel.setBorder(BorderFactory.createTitledBorder("Listener aprés ajout"));
		beforeChangePanel.setBorder(BorderFactory.createTitledBorder("Listener avant changement"));
		afterChangePanel.setBorder(BorderFactory.createTitledBorder("Listener aprés changement"));
		beforeRemovePanel.setBorder(BorderFactory.createTitledBorder("Listener avant suppression"));
		afterRemovePanel.setBorder(BorderFactory.createTitledBorder("Listener aprés suppression"));
		phasePanel.setBorder(BorderFactory.createTitledBorder("Listener de phase"));
		formulaPanel.setBorder(BorderFactory.createTitledBorder("Formules"));
		
		GroupLayout layout = new GroupLayout(contentPanel);
		contentPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(beforeAddPanel)
				.addComponent(afterAddPanel)
				.addComponent(beforeChangePanel)
				.addComponent(afterChangePanel)
				.addComponent(beforeRemovePanel)
				.addComponent(afterRemovePanel)
				.addComponent(phasePanel)
				.addComponent(formulaPanel)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(beforeAddPanel)
			.addComponent(afterAddPanel)
			.addComponent(beforeChangePanel)
			.addComponent(afterChangePanel)
			.addComponent(beforeRemovePanel)
			.addComponent(afterRemovePanel)
			.addComponent(phasePanel)
			.addComponent(formulaPanel)
			.addContainerGap()
		);
		
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		add(scrollPane, constraints);
		
		refresh();
	}

	@Override
	public Personnage impact() throws Exception {
		return GenCrossEditor.getInstance().getPersonnage();
	}

	@Override
	public void refresh() throws Exception {
		Personnage personnage = GenCrossEditor.getInstance().getPersonnage();
		
		refreshPanelWithListener(beforeAddPanel, personnage.getBeforeAddPropertyListeners());
		refreshPanelWithListener(afterAddPanel, personnage.getAfterAddPropertyListeners());
		refreshPanelWithListener(beforeChangePanel, personnage.getBeforeChangeValueListeners());
		refreshPanelWithListener(afterChangePanel, personnage.getAfterChangeValueListeners());
		refreshPanelWithListener(beforeRemovePanel, personnage.getBeforeDeletePropertyListeners());
		refreshPanelWithListener(afterRemovePanel, personnage.getAfterDeletePropertyListeners());
		refreshPanelWithListener(phasePanel, personnage.getPassToNextPhaseListeners());
		
		
		
	}
	
	private void refreshPanelWithListener(JPanel panel, Collection<? extends Listener> listeners){
		GridBagConstraints nameConstraints = new GridBagConstraints(0,0,1,1,0.5,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
		GridBagConstraints patternConstraints = new GridBagConstraints(1,0,1,1,0.5,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
		GridBagConstraints editConstraints = new GridBagConstraints(2,0,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
		GridBagConstraints removeConstraints = new GridBagConstraints(3,0,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
		
		panel.removeAll();
		panel.setLayout(new GridBagLayout());
		for(Listener listener : listeners){
			nameConstraints.gridy++;
			patternConstraints.gridy++;
			editConstraints.gridy++;
			removeConstraints.gridy++;
			panel.add(new JLabel(listener.getName()==null?"Listener anonyme":listener.getName()), nameConstraints);
			if(listener instanceof PropertyListener){
				panel.add(new JLabel(((PropertyListener)listener).getPattern()), patternConstraints);
			}
			if(listener instanceof PassToNextPhaseListener){
				panel.add(new JLabel(((PassToNextPhaseListener)listener).getPhase()), patternConstraints);
			}
			panel.add(new JButton("Editer"), editConstraints);
			panel.add(new JButton("Suppr."), removeConstraints);
		}
		
	}

}
