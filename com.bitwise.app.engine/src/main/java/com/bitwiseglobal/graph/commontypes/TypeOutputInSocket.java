//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.17 at 04:03:17 PM IST 
//


package com.bitwiseglobal.graph.commontypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.bitwiseglobal.graph.ofmixedscheme.TypeOutputMixedInSocket;
import com.bitwiseglobal.graph.otdiscard.TypeOutputInSocketIno;
import com.bitwiseglobal.graph.otffw.TypeOutputFixedwidthInSocket;


/**
 * <p>Java class for type-output-inSocket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-output-inSocket">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bitwiseglobal.com/graph/commontypes}type-base-inSocket">
 *       &lt;sequence>
 *         &lt;element name="schema" type="{http://www.bitwiseglobal.com/graph/commontypes}type-base-record" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-output-inSocket", propOrder = {
    "schema"
})
@XmlSeeAlso({
    TypeOutputFixedwidthInSocket.class,
    com.bitwiseglobal.graph.otfd.TypeOutputDelimitedInSocket.class,
    TypeOutputInSocketIno.class,
    com.bitwiseglobal.graph.ofparquet.TypeOutputDelimitedInSocket.class,
    com.bitwiseglobal.graph.ohiveparquet.TypeOutputDelimitedInSocket.class,
    TypeOutputMixedInSocket.class,
    com.bitwiseglobal.graph.ofsubgraph.TypeOutputDelimitedInSocket.class
})
public class TypeOutputInSocket
    extends TypeBaseInSocket
{

    protected TypeBaseRecord schema;

    /**
     * Gets the value of the schema property.
     * 
     * @return
     *     possible object is
     *     {@link TypeBaseRecord }
     *     
     */
    public TypeBaseRecord getSchema() {
        return schema;
    }

    /**
     * Sets the value of the schema property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeBaseRecord }
     *     
     */
    public void setSchema(TypeBaseRecord value) {
        this.schema = value;
    }

}
