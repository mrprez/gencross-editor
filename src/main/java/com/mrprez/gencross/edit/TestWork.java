package com.mrprez.gencross.edit;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.edit.framework.EdtWork;
import com.mrprez.gencross.edit.framework.Work;

public class TestWork implements EdtWork {

	@Override
	public Work getNextWork() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		InputStream is = new ByteArrayInputStream(GenCrossEditor.getInstance().getXml().getBytes("UTF-8"));
		Personnage personnage = GenCrossEditor.getInstance().getPersonnageFactory().loadPersonnage(is);
		
		GenCrossTester genCrossTester = new GenCrossTester(GenCrossEditor.getInstance().getRepositoryManager().getRepository());
		
		genCrossTester.setPersonnage(personnage);

	}

}
