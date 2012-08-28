package com.mrprez.gencross.edit;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.dialog.distant.DistantDialog;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class DistantPersonnageWork implements BackgroundWork {

	@Override
	public Work getNextWork() {
		return null;
	}

	@Override
	public void doInBackground() throws Exception {
		Personnage personnage = DistantDialog.getInstance().edit();
		
		
		
		/*PluginDescription pluginDescription = choosePlugin();
		if(pluginDescription==null){
			return;
		}
		
		choosePersonnage(pluginDescription.getName());*/
		
	}
	
	/*private PluginDescription choosePlugin() throws MalformedURLException, Exception_Exception{
		JComboBox comboBox = new JComboBox(WebServiceClient.getPersonnageAvailableList().toArray());
		comboBox.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				PluginDescription pg = (PluginDescription) arg1;
				return new JLabel(pg.getName()+" (v "+pg.getVersion()+")");
			}
		});
		int result = OptionPane.showConfirmDialog(GenCrossEditor.getInstance(), comboBox, "Choix du type de personnage", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.CANCEL_OPTION){
			return null;
		}
		return (PluginDescription) comboBox.getSelectedItem();
	}
	
	private PersonnageDescription choosePersonnage(String type) throws MalformedURLException, Exception_Exception{
		JComboBox comboBox = new JComboBox(WebServiceClient.getPersonnageList(type).toArray());
		comboBox.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
				PersonnageDescription pg = (PersonnageDescription) arg1;
				StringBuilder sb = new StringBuilder(pg.getName());
				
				if(pg.getGmName()!=null && pg.getPlayerName()!=null){
					sb.append(" (MJ="+pg.getGmName()+", PJ="+pg.getPlayerName()+")");
				}else if(pg.getGmName()!=null && pg.getPlayerName()==null){
					sb.append(" (MJ="+pg.getGmName()+")");
				}else if(pg.getGmName()==null && pg.getPlayerName()!=null){
					sb.append(" (PJ="+pg.getPlayerName()+")");
				}
				return new JLabel(sb.toString());
			}
		});
		int result = OptionPane.showConfirmDialog(GenCrossEditor.getInstance(), comboBox, "Choix du personnage", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.CANCEL_OPTION){
			return null;
		}
		return (PersonnageDescription) comboBox.getSelectedItem();
		
	}*/

}
