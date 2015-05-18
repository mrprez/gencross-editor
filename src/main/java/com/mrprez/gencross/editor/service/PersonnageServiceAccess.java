package com.mrprez.gencross.editor.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.ws.api.IPersonnageService;
import com.mrprez.gencross.ws.api.bo.PersonnageLabel;

public class PersonnageServiceAccess {
	private IPersonnageService personnageService;
	
	
	public PersonnageServiceAccess() throws MalformedURLException{
		super();
		URL wsdlUrl = new URL("http://localhost:8181/gencross-web/personnageService?wsdl");
		QName qname = new QName("http://service.web.gencross.mrprez.com/", "PersonnageServiceService");
		Service service = Service.create(wsdlUrl, qname);
		service.setHandlerResolver(new ServiceHandlerResolver());
		QName qnamePort = new QName("http://service.web.gencross.mrprez.com/", "PersonnageServicePort");
		personnageService = service.getPort(qnamePort, IPersonnageService.class);
	}
	
	
	
	public PluginDescriptor[] getPluginList() throws Exception{
		PluginDescriptor[] pluginList = personnageService.getPluginList();
		System.out.print("Get PLugin List: ");
		for(PluginDescriptor pluginDescriptor : pluginList){
			System.out.print(pluginDescriptor);
			System.out.print(" / ");
		}
		System.out.println("");
		return pluginList;
	}
	
	
	public PersonnageLabel[] getPersonnageLabels(PluginDescriptor pluginDescriptor) throws Exception{
		return personnageService.getPersonnageLabels(pluginDescriptor);
	}
	
	public String getPersonnage(int id) throws Exception{
		return new String(personnageService.getPersonnage(id), "UTF-8");
	}
	
	public void savePersonnage(int personnageId, String xml) throws Exception{
		personnageService.savePersonnage(personnageId, xml.getBytes("UTF-8"));
		
	}
	
}
