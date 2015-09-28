//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 12:24:55 PM IST 
//


package com.bitwise.app.common.component.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PortSpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeOfPort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numberOfPorts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sequenceOfPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="allowMultipleLinks" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="linkMandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortSpecification", propOrder = {
    "typeOfPort",
    "numberOfPorts",
    "sequenceOfPort",
    "allowMultipleLinks",
    "linkMandatory"
})
public class PortSpecification {

    @XmlElement(required = true)
    protected String typeOfPort;
    protected int numberOfPorts;
    protected int sequenceOfPort;
    protected Boolean allowMultipleLinks;
    protected Boolean linkMandatory;

    /**
     * Gets the value of the typeOfPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfPort() {
        return typeOfPort;
    }

    /**
     * Sets the value of the typeOfPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfPort(String value) {
        this.typeOfPort = value;
    }

    /**
     * Gets the value of the numberOfPorts property.
     * 
     */
    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    /**
     * Sets the value of the numberOfPorts property.
     * 
     */
    public void setNumberOfPorts(int value) {
        this.numberOfPorts = value;
    }

    /**
     * Gets the value of the sequenceOfPort property.
     * 
     */
    public int getSequenceOfPort() {
        return sequenceOfPort;
    }

    /**
     * Sets the value of the sequenceOfPort property.
     * 
     */
    public void setSequenceOfPort(int value) {
        this.sequenceOfPort = value;
    }

    /**
     * Gets the value of the allowMultipleLinks property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowMultipleLinks() {
        return allowMultipleLinks;
    }

    /**
     * Sets the value of the allowMultipleLinks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowMultipleLinks(Boolean value) {
        this.allowMultipleLinks = value;
    }

    /**
     * Gets the value of the linkMandatory property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLinkMandatory() {
        return linkMandatory;
    }

    /**
     * Sets the value of the linkMandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLinkMandatory(Boolean value) {
        this.linkMandatory = value;
    }

}
