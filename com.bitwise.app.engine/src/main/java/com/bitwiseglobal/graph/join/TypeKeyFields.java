//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:12:07 PM IST 
//


package com.bitwiseglobal.graph.join;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.bitwiseglobal.graph.commontypes.TypeFieldName;


/**
 * <p>Java class for type-key-fields complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-key-fields">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="field" type="{http://www.bitwiseglobal.com/graph/commontypes}type-field-name" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="inSocketId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="joinType" use="required" type="{http://www.bitwiseglobal.com/graph/join}join_type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-key-fields", propOrder = {
    "field"
})
public class TypeKeyFields {

    @XmlElement(required = true)
    protected List<TypeFieldName> field;
    @XmlAttribute(name = "inSocketId", required = true)
    protected String inSocketId;
    @XmlAttribute(name = "joinType", required = true)
    protected JoinType joinType;

    /**
     * Gets the value of the field property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the field property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeFieldName }
     * 
     * 
     */
    public List<TypeFieldName> getField() {
        if (field == null) {
            field = new ArrayList<TypeFieldName>();
        }
        return this.field;
    }

    /**
     * Gets the value of the inSocketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInSocketId() {
        return inSocketId;
    }

    /**
     * Sets the value of the inSocketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInSocketId(String value) {
        this.inSocketId = value;
    }

    /**
     * Gets the value of the joinType property.
     * 
     * @return
     *     possible object is
     *     {@link JoinType }
     *     
     */
    public JoinType getJoinType() {
        return joinType;
    }

    /**
     * Sets the value of the joinType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JoinType }
     *     
     */
    public void setJoinType(JoinType value) {
        this.joinType = value;
    }

}
