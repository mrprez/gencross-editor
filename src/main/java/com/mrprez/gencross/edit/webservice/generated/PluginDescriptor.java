
package com.mrprez.gencross.edit.webservice.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pluginDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pluginDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appendixFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gcrFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="helpFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="helpFileSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://service.web.gencross.mrprez.com/}version" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pluginDescriptor", propOrder = {
    "appendixFileName",
    "className",
    "gcrFileName",
    "helpFileName",
    "helpFileSize",
    "name",
    "version"
})
public class PluginDescriptor {

    protected String appendixFileName;
    protected String className;
    protected String gcrFileName;
    protected String helpFileName;
    protected Long helpFileSize;
    protected String name;
    protected Version version;

    /**
     * Gets the value of the appendixFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppendixFileName() {
        return appendixFileName;
    }

    /**
     * Sets the value of the appendixFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppendixFileName(String value) {
        this.appendixFileName = value;
    }

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the gcrFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGcrFileName() {
        return gcrFileName;
    }

    /**
     * Sets the value of the gcrFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGcrFileName(String value) {
        this.gcrFileName = value;
    }

    /**
     * Gets the value of the helpFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHelpFileName() {
        return helpFileName;
    }

    /**
     * Sets the value of the helpFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHelpFileName(String value) {
        this.helpFileName = value;
    }

    /**
     * Gets the value of the helpFileSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHelpFileSize() {
        return helpFileSize;
    }

    /**
     * Sets the value of the helpFileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHelpFileSize(Long value) {
        this.helpFileSize = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Version }
     *     
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Version }
     *     
     */
    public void setVersion(Version value) {
        this.version = value;
    }

}
