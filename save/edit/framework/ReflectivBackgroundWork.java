package com.mrprez.gencross.edit.framework;

import java.lang.reflect.Method;

public class ReflectivBackgroundWork implements BackgroundWork {
	private Object object;
	private Method method;
	private Work nextWork;
	
	
	public ReflectivBackgroundWork(Object object, String methodName) throws SecurityException, NoSuchMethodException{
		this.object = object;
		method = object.getClass().getMethod(methodName);
	}
	public ReflectivBackgroundWork(Object object, String methodName, Work nextWork) throws SecurityException, NoSuchMethodException{
		this.nextWork = nextWork;
		this.object = object;
		method = object.getClass().getMethod(methodName);
	}
	

	@Override
	public void doInBackground() throws Exception {
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
