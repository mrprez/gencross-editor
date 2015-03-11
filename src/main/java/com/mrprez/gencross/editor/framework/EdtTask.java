package com.mrprez.gencross.editor.framework;


public interface EdtTask extends Task {

	public abstract void doInEdt() throws Exception;

}
