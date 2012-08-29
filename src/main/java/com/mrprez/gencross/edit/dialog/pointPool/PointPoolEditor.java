package com.mrprez.gencross.edit.dialog.pointPool;

import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mrprez.gencross.PoolPoint;
import com.mrprez.gencross.edit.framework.EditDialog;
import com.mrprez.gencross.edit.framework.SimpleEDTAction;

public class PointPoolEditor extends EditDialog<PoolPoint> {
	private static final long serialVersionUID = 1L;
	
	private PoolPoint poolPoint;
	
	private JLabel nameLabel = new JLabel("Nom");
	private JTextField nameField = new JTextField(20);
	private JLabel pointsLabel = new JLabel("Nb de points");
	private JTextField remainingField = new JTextField(3);
	private JLabel separator = new JLabel(" / ");
	private JTextField totalField = new JTextField(3);
	private JLabel toEmptyLabel = new JLabel("A vider");
	private JCheckBox toEmptyCheckBox = new JCheckBox();
	private JButton validationButton = new JButton("OK");
	
	
	public PointPoolEditor(Frame frame, PoolPoint poolPoint) {
		super(frame);
		this.poolPoint = poolPoint;
	}
	
	@Override
	public void init() throws Exception {
		setTitle(poolPoint.getName());
		
		nameField.setText(poolPoint.getName());
		remainingField.setText(""+poolPoint.getRemaining());
		totalField.setText(""+poolPoint.getTotal());
		toEmptyCheckBox.setSelected(poolPoint.isToEmpty());
		
		validationButton.addActionListener(new SimpleEDTAction(this,"submit"));
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(nameLabel)
						.addComponent(pointsLabel)
						.addComponent(toEmptyLabel)
					)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(nameField)
						.addGroup(layout.createSequentialGroup()
							.addComponent(remainingField)
							.addComponent(separator)
							.addComponent(totalField)
						)
						.addComponent(toEmptyCheckBox)
					)
				)
				.addComponent(validationButton)
			)
			.addContainerGap()
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(layout.createParallelGroup()
				.addComponent(nameLabel)
				.addComponent(nameField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(pointsLabel)
				.addComponent(remainingField)
				.addComponent(separator)
				.addComponent(totalField)
			)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(layout.createParallelGroup()
				.addComponent(toEmptyLabel)
				.addComponent(toEmptyCheckBox)
			)
			.addGap(20)
			.addComponent(validationButton)
			.addContainerGap()
		);
		
	}
	
	@Override
	public PoolPoint getResult() {
		return poolPoint;
	}
	
	public String findError(){
		if(nameField.getText().trim().isEmpty()){
			return "Nom du pool de point non-renseigné";
		}
		if(!remainingField.getText().matches("[-]?[0-9]+")){
			return "Nombre de points restant invalide";
		}
		if(!totalField.getText().matches("[-]?[0-9]+")){
			return "Nombre de points restant invalide";
		}
		return null;
	}
	
	public void submit(){
		String error = findError();
		if(error!=null){
			JOptionPane.showMessageDialog(this, error, "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		poolPoint.setName(nameField.getText());
		poolPoint.setRemaining(Integer.parseInt(remainingField.getText()));
		poolPoint.setTotal(Integer.parseInt(totalField.getText()));
		poolPoint.setToEmpty(toEmptyCheckBox.isSelected());
		
		validateData();
	}

	
}
