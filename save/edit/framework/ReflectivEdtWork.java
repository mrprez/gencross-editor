package com.mrprez.gencross.edit.framework;

import java.lang.reflect.Method;

public class ReflectivEdtWork implements EdtWork {
	private Object object;
	private Method method;
	private Work nextWork;
	
	
	public ReflectivEdtWork(Object object, String methodName) throws SecurityException, NoSuchMethodException{
		this.object = object;
		method = object.getClass().getMethod(methodName);
	}

	@Override
	public void doInEdt() throws Exception {
		method.invoke(object);
	}

	@Override
	public Work getNextWork() {
		return nextWork;
	}

	public void setNextWork(Work nextWork) {
		this.nextWork = nextWork;
	}

}
