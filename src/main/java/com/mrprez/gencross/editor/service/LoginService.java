package com.mrprez.gencross.editor.service;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.ws.api.IAuthentificationService;

public class LoginService {
	
	
	public boolean authenticate(String username, String password) throws NoSuchAlgorithmException, Exception{
		URL wsdlUrl = new URL(GencrossEditor.getInstance().getGencrossWebUrl()+"/authentificationService?wsdl");
		QName qname = new QName("http://service.web.gencross.mrprez.com/", "AuthentificationServiceService");
		Service service = Service.create(wsdlUrl, qname);
		QName qnamePort = new QName("http://service.web.gencross.mrprez.com/", "AuthentificationServicePort");
		IAuthentificationService authentificationService = service.getPort(qnamePort, IAuthentificationService.class);
		String token = authentificationService.authentificate(username, buildMD5Digest(password));
		if(token.length()!=0){
			GencrossEditor.getInstance().setUsernameToken(username, token);
			return true;
		}
		
		return false;
	}
	
	
	private static String buildMD5Digest(String string) throws NoSuchAlgorithmException{
		MessageDigest msgDigest = MessageDigest.getInstance("MD5");
		msgDigest.update(string.getBytes());
		byte digest[] = msgDigest.digest();
		StringBuilder digestStringBuffer = new StringBuilder(digest.length*2);
		for(int i=0; i<digest.length; i++){
			String element = String.format("%x", digest[i]);
			if(element.length()<2){
				digestStringBuffer.append("0");
			}
			digestStringBuffer.append(element);
		}
		return digestStringBuffer.toString();
	}

}
