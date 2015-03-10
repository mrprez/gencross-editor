package com.mrprez.gencross.edit.framework;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mrprez.gencross.edit.error.ErrorFrame;

public abstract class EditDialog<T> extends JDialog implements Runnable {
	private static final long serialVersionUID = 1L;
	
	T result;
	protected JButton validationButton = new JButton("OK");
	private ActionListener validationListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			validateData();
		}
	};
	
	
	public EditDialog(Dialog arg0) {
		super(arg0);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		addWindowListener(new CloseEditDialogListener(this));
		validationButton.addActionListener(validationListener);
	}

	public EditDialog(Frame arg0) {
		super(arg0);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		addWindowListener(new CloseEditDialogListener(this));
		validationButton.addActionListener(validationListener);
	}

	/**
	 * Appelle l'EDT pour afficher la boite de dialogue.
	 * Attend la fermeture de la boite de dialogue pour retourner son résultat.
	 * @return
	 * @throws Exception
	 */
	public synchronized T edit() throws Exception{
		if(SwingUtilities.isEventDispatchThread()){
			throw new Exception("\"edit\" method cannot be called in EDT");
		}
		SwingUtilities.invokeLater(this);
		result = null;
		wait();
		return result;
	}
	
	/**
	 * Valide la boite de dialogue. Cette methode doit être appelée par le listener du bouton "valider" de la boite de dialogue.
	 */
	public synchronized void validateData(){
		String error = findError();
		if(error != null){
			JOptionPane.showMessageDialog(this, error, "Donnée invalide", JOptionPane.ERROR_MESSAGE);
			return;
		}
		super.setVisible(false);
		try{
			result = getResult();
		}catch(Exception e){
			ErrorFrame.displayError(e);
		}finally{
			notify();
		}
	}
	
	/**
	 * Récupère la valeur des champs de la boite de dialogue pour construire ce qu'elle doit renvoyer.
	 * @return
	 * @throws Exception 
	 */
	public abstract T getResult() throws Exception;
	
	/**
	 * Initialise les champs de la boite de dialogue.
	 * @throws Exception
	 */
	public abstract void init() throws Exception;
	
	/**
	 * Valide les données du formulaire.
	 * @return l'intitulée de l'erreur à afficher à l'utilisateur ou null si les données sont valides.
	 */
	protected abstract String findError();

	@Override
	public void run() {
		try {
			init();
			pack();
			int x = getOwner().getX()+(getOwner().getWidth()-getWidth())/2;
			int y = getOwner().getY()+(getOwner().getHeight()-getHeight())/2;
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle desktop = ge.getMaximumWindowBounds();
			if(x+getWidth()>desktop.getWidth()){
				x = (int) (desktop.getWidth() - getWidth());
			}
			if(y+getHeight()>desktop.getHeight()){
				y = (int) (desktop.getHeight() - getHeight());
			}
			if(x<0){
				x = 0;
			}
			if(y<0){
				y = 0;
			}
			setLocation(x, y);
			setVisible(true);
		} catch (Exception e) {
			ErrorFrame.displayError(e);
		}
	}

}
