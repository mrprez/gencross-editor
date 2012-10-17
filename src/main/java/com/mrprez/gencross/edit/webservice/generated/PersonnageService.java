package com.mrprez.gencross.edit.webservice.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.0
 * 2012-10-17T17:25:42.011+02:00
 * Generated source version: 2.7.0
 * 
 */
@WebService(targetNamespace = "http://service.web.gencross.mrprez.com/", name = "PersonnageService")
@XmlSeeAlso({ObjectFactory.class})
public interface PersonnageService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAvailablePersonnageList", targetNamespace = "http://service.web.gencross.mrprez.com/", className = "com.mrprez.gencross.edit.webservice.generated.GetAvailablePersonnageList")
    @WebMethod
    @ResponseWrapper(localName = "getAvailablePersonnageListResponse", targetNamespace = "http://service.web.gencross.mrprez.com/", className = "com.mrprez.gencross.edit.webservice.generated.GetAvailablePersonnageListResponse")
    public java.util.List<com.mrprez.gencross.edit.webservice.generated.PluginDescriptor> getAvailablePersonnageList();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getPersonnageList", targetNamespace = "http://service.web.gencross.mrprez.com/", className = "com.mrprez.gencross.edit.webservice.generated.GetPersonnageList")
    @WebMethod
    @ResponseWrapper(localName = "getPersonnageListResponse", targetNamespace = "http://service.web.gencross.mrprez.com/", className = "com.mrprez.gencross.edit.webservice.generated.GetPersonnageListResponse")
    public java.util.List<com.mrprez.gencross.edit.webservice.generated.PersonnageDescription> getPersonnageList(
        @WebParam(name = "type", targetNamespace = "")
        java.lang.String type
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getPersonnage", targetNamespace = "http://service.web.gencross.mrprez.com/", className = "com.mrprez.gencross.edit.webservice.generated.GetPersonnage")
    @WebMethod
    @ResponseWrapper(localName = "getPersonnageResponse", targetNamespace = "http://service.web.gencross.mrprez.com/", className = "com.mrprez.gencross.edit.webservice.generated.GetPersonnageResponse")
    public byte[] getPersonnage(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.Integer id,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );
}
