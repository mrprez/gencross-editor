
package com.mrprez.gencross.edit.webservice.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mrprez.gencross.edit.webservice.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPersonnageResponse_QNAME = new QName("http://service.web.gencross.mrprez.com/", "getPersonnageResponse");
    private final static QName _GetPersonnageListResponse_QNAME = new QName("http://service.web.gencross.mrprez.com/", "getPersonnageListResponse");
    private final static QName _GetPersonnageList_QNAME = new QName("http://service.web.gencross.mrprez.com/", "getPersonnageList");
    private final static QName _GetAvailablePersonnageListResponse_QNAME = new QName("http://service.web.gencross.mrprez.com/", "getAvailablePersonnageListResponse");
    private final static QName _GetPersonnage_QNAME = new QName("http://service.web.gencross.mrprez.com/", "getPersonnage");
    private final static QName _GetAvailablePersonnageList_QNAME = new QName("http://service.web.gencross.mrprez.com/", "getAvailablePersonnageList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mrprez.gencross.edit.webservice.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPersonnage }
     * 
     */
    public GetPersonnage createGetPersonnage() {
        return new GetPersonnage();
    }

    /**
     * Create an instance of {@link PluginDescriptor }
     * 
     */
    public PluginDescriptor createPluginDescriptor() {
        return new PluginDescriptor();
    }

    /**
     * Create an instance of {@link GetPersonnageListResponse }
     * 
     */
    public GetPersonnageListResponse createGetPersonnageListResponse() {
        return new GetPersonnageListResponse();
    }

    /**
     * Create an instance of {@link GetPersonnageList }
     * 
     */
    public GetPersonnageList createGetPersonnageList() {
        return new GetPersonnageList();
    }

    /**
     * Create an instance of {@link GetAvailablePersonnageListResponse }
     * 
     */
    public GetAvailablePersonnageListResponse createGetAvailablePersonnageListResponse() {
        return new GetAvailablePersonnageListResponse();
    }

    /**
     * Create an instance of {@link GetPersonnageResponse }
     * 
     */
    public GetPersonnageResponse createGetPersonnageResponse() {
        return new GetPersonnageResponse();
    }

    /**
     * Create an instance of {@link PersonnageDescription }
     * 
     */
    public PersonnageDescription createPersonnageDescription() {
        return new PersonnageDescription();
    }

    /**
     * Create an instance of {@link Version }
     * 
     */
    public Version createVersion() {
        return new Version();
    }

    /**
     * Create an instance of {@link GetAvailablePersonnageList }
     * 
     */
    public GetAvailablePersonnageList createGetAvailablePersonnageList() {
        return new GetAvailablePersonnageList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonnageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.gencross.mrprez.com/", name = "getPersonnageResponse")
    public JAXBElement<GetPersonnageResponse> createGetPersonnageResponse(GetPersonnageResponse value) {
        return new JAXBElement<GetPersonnageResponse>(_GetPersonnageResponse_QNAME, GetPersonnageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonnageListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.gencross.mrprez.com/", name = "getPersonnageListResponse")
    public JAXBElement<GetPersonnageListResponse> createGetPersonnageListResponse(GetPersonnageListResponse value) {
        return new JAXBElement<GetPersonnageListResponse>(_GetPersonnageListResponse_QNAME, GetPersonnageListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonnageList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.gencross.mrprez.com/", name = "getPersonnageList")
    public JAXBElement<GetPersonnageList> createGetPersonnageList(GetPersonnageList value) {
        return new JAXBElement<GetPersonnageList>(_GetPersonnageList_QNAME, GetPersonnageList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailablePersonnageListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.gencross.mrprez.com/", name = "getAvailablePersonnageListResponse")
    public JAXBElement<GetAvailablePersonnageListResponse> createGetAvailablePersonnageListResponse(GetAvailablePersonnageListResponse value) {
        return new JAXBElement<GetAvailablePersonnageListResponse>(_GetAvailablePersonnageListResponse_QNAME, GetAvailablePersonnageListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonnage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.gencross.mrprez.com/", name = "getPersonnage")
    public JAXBElement<GetPersonnage> createGetPersonnage(GetPersonnage value) {
        return new JAXBElement<GetPersonnage>(_GetPersonnage_QNAME, GetPersonnage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailablePersonnageList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.gencross.mrprez.com/", name = "getAvailablePersonnageList")
    public JAXBElement<GetAvailablePersonnageList> createGetAvailablePersonnageList(GetAvailablePersonnageList value) {
        return new JAXBElement<GetAvailablePersonnageList>(_GetAvailablePersonnageList_QNAME, GetAvailablePersonnageList.class, null, value);
    }

}
