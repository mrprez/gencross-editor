package com.mrprez.gencross.edit.util;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorEnumeration<E> implements Enumeration<E> {
	private Iterator<E> iterator;
	
	
	public IteratorEnumeration(Iterator<E> iterator) {
		super();
		this.iterator = iterator;
	}

	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public E nextElement() {
		return iterator.next();
	}

}
