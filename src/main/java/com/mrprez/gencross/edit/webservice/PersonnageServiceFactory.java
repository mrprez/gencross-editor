package com.mrprez.gencross.edit.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PersonnageServiceFactory {
	private static QName PersonnageServicePort = new QName("http://service.web.gencross.mrprez.com/", "PersonnageServicePort");
	private static QName SERVICE = new QName("http://service.web.gencross.mrprez.com/", "PersonnageServiceService");
    
	
	public static PersonnageService buildService(String url) throws MalformedURLException{
		Service service = Service.create(new URL(url), SERVICE);
		return service.getPort(PersonnageServicePort, PersonnageService.class);
	}
	
	
}
