package com.mrprez.gencross.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class GencrossFileFilter extends FileFilter {
	
	@Override
	public String getDescription() {
		return "Gencross file (*.gcr)";
	}
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()){
			return true;
		}
		String name = f.getName();
		if(name.contains(".")){
			System.out.println(name+" => "+name.substring(name.lastIndexOf(".")));
			return name.substring(name.lastIndexOf(".")).equalsIgnoreCase(".GCR");
		}
		return false;
	}

}
