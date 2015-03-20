package com.mrprez.gencross.editor.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.DOMException;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.ws.api.IAuthentificationService;

public class ServiceHandler implements Handler<SOAPMessageContext>, HandlerResolver {
	
	

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
			SOAPHeader header = envelope.getHeader();
			if(header==null){
				header = envelope.addHeader();
			}
			SOAPElement element = header.addChildElement(new QName("http://api.ws.web.gencross.mrprez.com/", IAuthentificationService.TOKEN_KEY));
			element.setTextContent(GencrossEditor.getInstance().getToken());
		} catch (DOMException | SOAPException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(this);
		return handlerChain;
	}

	

}
