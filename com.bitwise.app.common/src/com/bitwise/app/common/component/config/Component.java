//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.23 at 12:41:24 PM IST 
//


package com.bitwise.app.common.component.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Component complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Component">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.bitwise.com/ComponentConfig}category_type" minOccurs="0"/>
 *         &lt;element name="inputPort" type="{http://www.bitwise.com/ComponentConfig}IOPort" minOccurs="0"/>
 *         &lt;element name="outputPort" type="{http://www.bitwise.com/ComponentConfig}IOPort" minOccurs="0"/>
 *         &lt;element name="property" type="{http://www.bitwise.com/ComponentConfig}Property" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="policy" type="{http://www.bitwise.com/ComponentConfig}Policy" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="iconPath" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="shape" use="required" type="{http://www.bitwise.com/ComponentConfig}shape" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Component", propOrder = {
    "category",
    "inputPort",
    "outputPort",
    "property",
    "policy"
})
public class Component {

    @XmlSchemaType(name = "string")
    protected CategoryType category;
    protected IOPort inputPort;
    protected IOPort outputPort;
    protected List<Property> property;
    protected List<Policy> policy;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "iconPath", required = true)
    protected String iconPath;
    @XmlAttribute(name = "shape", required = true)
    protected Shape shape;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryType }
     *     
     */
    public CategoryType getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryType }
     *     
     */
    public void setCategory(CategoryType value) {
        this.category = value;
    }

    /**
     * Gets the value of the inputPort property.
     * 
     * @return
     *     possible object is
     *     {@link IOPort }
     *     
     */
    public IOPort getInputPort() {
        return inputPort;
    }

    /**
     * Sets the value of the inputPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link IOPort }
     *     
     */
    public void setInputPort(IOPort value) {
        this.inputPort = value;
    }

    /**
     * Gets the value of the outputPort property.
     * 
     * @return
     *     possible object is
     *     {@link IOPort }
     *     
     */
    public IOPort getOutputPort() {
        return outputPort;
    }

    /**
     * Sets the value of the outputPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link IOPort }
     *     
     */
    public void setOutputPort(IOPort value) {
        this.outputPort = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getProperty() {
        if (property == null) {
            property = new ArrayList<Property>();
        }
        return this.property;
    }

    /**
     * Gets the value of the policy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Policy }
     * 
     * 
     */
    public List<Policy> getPolicy() {
        if (policy == null) {
            policy = new ArrayList<Policy>();
        }
        return this.policy;
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
     * Gets the value of the iconPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Sets the value of the iconPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconPath(String value) {
        this.iconPath = value;
    }

    /**
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link Shape }
     *     
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shape }
     *     
     */
    public void setShape(Shape value) {
        this.shape = value;
    }

}
