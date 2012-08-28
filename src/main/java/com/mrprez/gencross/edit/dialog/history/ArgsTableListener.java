package com.mrprez.gencross.edit.dialog.history;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ArgsTableListener implements TableModelListener {
	private DefaultTableModel tableModel;
	private HistoryItemEditor historyItemEditor;
	
	
	public ArgsTableListener(DefaultTableModel tableModel, HistoryItemEditor historyItemEditor) {
		super();
		this.tableModel = tableModel;
		this.historyItemEditor = historyItemEditor;
	}




	@Override
	public void tableChanged(TableModelEvent event) {
		int row = event.getLastRow();
		String key = (String) tableModel.getValueAt(row, 0);
		if(row >= tableModel.getRowCount()-1){
			if(!key.isEmpty()){
				tableModel.insertRow(tableModel.getRowCount(), new String[]{"",""});
			}
		}else if(key.isEmpty()){
			int confirm = JOptionPane.showConfirmDialog(historyItemEditor, "Etes-vous sur de vouloir supprimer cette ligne?","Confirmation",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION){
				tableModel.removeRow(row);
			}else{
				tableModel.setValueAt("Cle"+(row+1), row, 0);
			}
		}
	}

}
