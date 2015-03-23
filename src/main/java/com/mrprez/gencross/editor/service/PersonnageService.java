package com.mrprez.gencross.editor.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mrprez.gencross.disk.PluginDescriptor;
import com.mrprez.gencross.ws.api.IPersonnageService;

public class PersonnageService {
	private IPersonnageService personnageService;
	
	
	public PersonnageService() throws MalformedURLException{
		super();
		URL wsdlUrl = new URL("http://localhost:8181/gencross-web/personnageService?wsdl");
		QName qname = new QName("http://service.web.gencross.mrprez.com/", "PersonnageServiceService");
		Service service = Service.create(wsdlUrl, qname);
		service.setHandlerResolver(new ServiceHandlerResolver());
		QName qnamePort = new QName("http://service.web.gencross.mrprez.com/", "PersonnageServicePort");
		personnageService = service.getPort(qnamePort, IPersonnageService.class);
	}
	
	
	
	public PluginDescriptor[] getPluginList() throws Exception{
		return personnageService.getPluginList();
	}
	
	
	public Map<Integer,String> getPersonnageLabels(PluginDescriptor pluginDescriptor) throws Exception{
		return personnageService.getPersonnageLabels(pluginDescriptor);
	}
	
}
