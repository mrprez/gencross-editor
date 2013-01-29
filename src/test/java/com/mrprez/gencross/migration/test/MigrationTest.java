package com.mrprez.gencross.migration.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.exception.PersonnageVersionException;

public class MigrationTest {
	
	
	public MigrationTest() {
		super();
	}
	
	@Test
	public void test() throws Exception {
		URL dataUrl = ClassLoader.getSystemResource("data");
		File dataDir = new File(dataUrl.getFile());
		File outDir = new File(dataDir.getParentFile(), "out");
		outDir.mkdir();
		orderLostFiles(dataDir);
		
		PersonnageFactory personnageFactory = new PersonnageFactory();
		PersonnageFactory personnageMigrationFactory = new PersonnageFactory(true);
		
		File pluginDirs[] = dataDir.listFiles();
		
		for(int i=0; i<pluginDirs.length; i++){
			File pluginDir = pluginDirs[i];
			File xmlFiles[] = pluginDir.listFiles(); 
			for(int j=0; j<xmlFiles.length; j++){
				File xmlFile = xmlFiles[j];
				System.out.println(xmlFile.getAbsolutePath());
				Personnage personnage;
				try{
					personnage = personnageFactory.loadPersonnageFromXml(xmlFile);
				}catch(PersonnageVersionException pve){
					personnage = personnageMigrationFactory.loadPersonnageFromXml(xmlFile);
				}
				File outPluginDir = new File(outDir, pluginDir.getName());
				outPluginDir.mkdir();
				PersonnageSaver.savePersonnage(personnage, new File(outPluginDir, xmlFile.getName()));
			}
			
		}
	}
	
	private void orderLostFiles(File dataDir) throws IOException{
		File files[] = dataDir.listFiles();
		for(int i=0; i<files.length; i++){
			File file = files[i];
			if(file.isFile()){
				String pluginName = getPluginName(file);
				File pluginDir = new File(dataDir, pluginName);
				pluginDir.mkdir();
				File dest = new File(pluginDir, file.getName());
				file.renameTo(dest);
			}
		}
	}
	
	private String getPluginName(File xmlFile) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(xmlFile));
		try{
			String line;
			while((line=reader.readLine()) != null){
				if(line.startsWith("<personnage ")){
					if(line.contains("name=\"")){
						int start = line.indexOf("name=\"")+6;
						int end = line.indexOf("\"", start);
						return line.substring(start, end);
					} else if(line.contains("class=\"")){
						int start = line.indexOf("class=\"")+7;
						int end = line.indexOf("\"", start);
						line = line.substring(start, end);
						return line.substring(line.lastIndexOf('.')+1);
					}
				}
			}
		}finally{
			reader.close();
		}
		throw new RuntimeException(xmlFile.getAbsolutePath()+" is not confom");
	}
	

}
