package com.mrprez.gencross.edit;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import com.mrprez.gencross.edit.error.ErrorFrame;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;
import com.mrprez.gencross.edit.util.DecryptInputStream;

public class OpenWork implements BackgroundWork{
	
	private String personnageName;
	private Work nextWork;
	
	
	public String readXmlSource(File gcrFile) throws Exception{
		InputStream is = DecryptInputStream.buildDecryptInputStream(gcrFile);
		InputStreamReader reader = new InputStreamReader(is, "UTF-8");
		StringBuilder sb = new StringBuilder();
		try{
			int i;
			while((i=reader.read())>=0){
				sb.append((char)i);
			}
		}finally{
			is.close();
		}
		return sb.toString();
	}

	@Override
	public void doInBackground() throws Exception {
		JFileChooser fileChooser = GenCrossEditor.getInstance().getFileChooser();
		int returnCode = fileChooser.showOpenDialog(GenCrossEditor.getInstance());
		nextWork = null;
		if(returnCode == JFileChooser.APPROVE_OPTION) {
			try{
				personnageName = "GenCrossEditor - "+fileChooser.getSelectedFile().getName();
				String xmlSource = readXmlSource(fileChooser.getSelectedFile());
				nextWork = new SetXmlEdtWork(personnageName, xmlSource);
			}catch(Throwable e){
				ErrorFrame.displayError(e);
			}
		}
		
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}

	

	

}
