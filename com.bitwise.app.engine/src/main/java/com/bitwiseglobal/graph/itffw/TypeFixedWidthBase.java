//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.17 at 04:03:17 PM IST 
//


package com.bitwiseglobal.graph.itffw;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.bitwiseglobal.graph.commontypes.TypeInputComponent;
import com.bitwiseglobal.graph.inputtypes.TextFileFixedWidth;


/**
 * <p>Java class for type-fixed-width-base complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-fixed-width-base">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.bitwiseglobal.com/graph/commontypes}type-input-component">
 *       &lt;sequence>
 *         &lt;element name="outSocket" type="{http://www.bitwiseglobal.com/graph/itffw}type-input-fixedwidth-out-socket"/>
 *         &lt;element name="dependsOn" type="{http://www.bitwiseglobal.com/graph/commontypes}type-depends-on" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-fixed-width-base")
@XmlSeeAlso({
    TextFileFixedWidth.class
})
public class TypeFixedWidthBase
    extends TypeInputComponent
{


}
