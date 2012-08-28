package com.mrprez.gencross.edit.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mrprez.gencross.edit.error.ErrorFrame;

public class SimpleEDTAction implements ActionListener {
	private Object object;
	private Method method;
	
	
	public SimpleEDTAction(Object object, String methodName) throws SecurityException, NoSuchMethodException{
		this.object = object;
		method = object.getClass().getMethod(methodName);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			method.invoke(object);
		} catch (IllegalArgumentException e) {
			ErrorFrame.displayError(e);
		} catch (IllegalAccessException e) {
			ErrorFrame.displayError(e);
		} catch (InvocationTargetException e) {
			ErrorFrame.displayError(e);
		}
	}

}
