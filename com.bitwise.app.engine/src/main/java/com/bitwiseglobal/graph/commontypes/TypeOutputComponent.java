//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.17 at 04:03:17 PM IST 
//


package com.bitwiseglobal.graph.commontypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.bitwiseglobal.graph.ofmixedscheme.TypeMixedBase;
import com.bitwiseglobal.graph.ofsubgraph.TypeSubgraphBase;
import com.bitwiseglobal.graph.otffw.TypeFixedWidthBase;
import com.bitwiseglobal.graph.outputtypes.Discard;


/**
 * <p>Java class for type-output-component complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-output-component">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bitwiseglobal.com/graph/commontypes}type-base-component">
 *       &lt;sequence>
 *         &lt;element name="inSocket" type="{http://www.bitwiseglobal.com/graph/commontypes}type-output-inSocket" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-output-component", propOrder = {
    "inSocket"
})
@XmlSeeAlso({
    Discard.class,
    TypeFixedWidthBase.class,
    com.bitwiseglobal.graph.otfd.TypeOutputFileDelimitedBase.class,
    com.bitwiseglobal.graph.ofparquet.TypeOutputFileDelimitedBase.class,
    com.bitwiseglobal.graph.ohiveparquet.TypeOutputFileDelimitedBase.class,
    TypeMixedBase.class,
    TypeSubgraphBase.class
})
public abstract class TypeOutputComponent
    extends TypeBaseComponent
{

    @XmlElement(required = true)
    protected List<TypeOutputInSocket> inSocket;

    /**
     * Gets the value of the inSocket property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inSocket property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInSocket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeOutputInSocket }
     * 
     * 
     */
    public List<TypeOutputInSocket> getInSocket() {
        if (inSocket == null) {
            inSocket = new ArrayList<TypeOutputInSocket>();
        }
        return this.inSocket;
    }

}
