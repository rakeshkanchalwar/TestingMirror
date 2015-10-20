//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:12:07 PM IST 
//


package com.bitwiseglobal.graph.commontypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.bitwiseglobal.graph.filter.TypeFilterOutSocket;
import com.bitwiseglobal.graph.reformat.TypeReformatOutSocket;


/**
 * <p>Java class for type-transform-out-socket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-transform-out-socket">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bitwiseglobal.com/graph/commontypes}type-base-outSocket">
 *       &lt;choice>
 *         &lt;element name="copyOfInsocket" type="{http://www.bitwiseglobal.com/graph/commontypes}type-outSocket-as-inSocket"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="passThroughfield" type="{http://www.bitwiseglobal.com/graph/commontypes}type-input-field"/>
 *           &lt;element name="operationField" type="{http://www.bitwiseglobal.com/graph/commontypes}type-operation-field"/>
 *           &lt;element name="mapField" type="{http://www.bitwiseglobal.com/graph/commontypes}type-map-field"/>
 *         &lt;/choice>
 *       &lt;/choice>
 *       &lt;anyAttribute/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-transform-out-socket", propOrder = {
    "copyOfInsocket",
    "passThroughfieldOrOperationFieldOrMapField"
})
@XmlSeeAlso({
    TypeReformatOutSocket.class,
    com.bitwiseglobal.graph.aggregate.TypeOutSocket.class,
    com.bitwiseglobal.graph.join.TypeOutSocket.class,
    TypeFilterOutSocket.class,
    com.bitwiseglobal.graph.uniquesequence.TypeOutSocket.class,
    com.bitwiseglobal.graph.scan.TypeOutSocket.class,
    com.bitwiseglobal.graph.lookup.TypeOutSocket.class,
    com.bitwiseglobal.graph.normalize.TypeOutSocket.class
})
public class TypeTransformOutSocket
    extends TypeBaseOutSocket
{

    protected TypeOutSocketAsInSocket copyOfInsocket;
    @XmlElements({
        @XmlElement(name = "passThroughfield", type = TypeInputField.class),
        @XmlElement(name = "operationField", type = TypeOperationField.class),
        @XmlElement(name = "mapField", type = TypeMapField.class)
    })
    protected List<Object> passThroughfieldOrOperationFieldOrMapField;

    /**
     * Gets the value of the copyOfInsocket property.
     * 
     * @return
     *     possible object is
     *     {@link TypeOutSocketAsInSocket }
     *     
     */
    public TypeOutSocketAsInSocket getCopyOfInsocket() {
        return copyOfInsocket;
    }

    /**
     * Sets the value of the copyOfInsocket property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOutSocketAsInSocket }
     *     
     */
    public void setCopyOfInsocket(TypeOutSocketAsInSocket value) {
        this.copyOfInsocket = value;
    }

    /**
     * Gets the value of the passThroughfieldOrOperationFieldOrMapField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passThroughfieldOrOperationFieldOrMapField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassThroughfieldOrOperationFieldOrMapField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeInputField }
     * {@link TypeOperationField }
     * {@link TypeMapField }
     * 
     * 
     */
    public List<Object> getPassThroughfieldOrOperationFieldOrMapField() {
        if (passThroughfieldOrOperationFieldOrMapField == null) {
            passThroughfieldOrOperationFieldOrMapField = new ArrayList<Object>();
        }
        return this.passThroughfieldOrOperationFieldOrMapField;
    }

}
