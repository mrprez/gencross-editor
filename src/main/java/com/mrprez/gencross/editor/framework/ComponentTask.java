package com.mrprez.gencross.editor.framework;

import java.awt.Window;

public interface ComponentTask extends TreatmentAwareTask {
	
	public Window getComponent() throws Exception;

}
