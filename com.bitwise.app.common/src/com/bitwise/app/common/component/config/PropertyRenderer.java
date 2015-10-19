//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.16 at 03:30:47 PM IST 
//


package com.bitwise.app.common.component.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for property_renderer.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="property_renderer">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ELTComponentNameWidget"/>
 *     &lt;enumeration value="ELTSchemaWidget"/>
 *     &lt;enumeration value="ELTRuntimePropertiesWidget"/>
 *     &lt;enumeration value="ELTFilePathWidget"/>
 *     &lt;enumeration value="ELTCharacterSetWidget"/>
 *     &lt;enumeration value="ELTDelimeterWidget"/>
 *     &lt;enumeration value="ELTPhaseWidget"/>
 *     &lt;enumeration value="ELTHasHeaderWidget"/>
 *     &lt;enumeration value="ELTSafePropertyWidget"/>
 *     &lt;enumeration value="ELTFieldSequenceWidget"/>
 *     &lt;enumeration value="ELTFilterPropertyWidget"/>
 *     &lt;enumeration value="ELTOperationalClassWidget"/>
 *     &lt;enumeration value="ELTStrictClassWidget"/>
 *     &lt;enumeration value="ELTFixedWidget"/>
 *     &lt;enumeration value="ELTRetentionlogicWidget"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "property_renderer")
@XmlEnum
public enum PropertyRenderer {

    @XmlEnumValue("ELTComponentNameWidget")
    ELT_COMPONENT_NAME_WIDGET("ELTComponentNameWidget"),
    @XmlEnumValue("ELTSchemaWidget")
    ELT_SCHEMA_WIDGET("ELTSchemaWidget"),
    @XmlEnumValue("ELTRuntimePropertiesWidget")
    ELT_RUNTIME_PROPERTIES_WIDGET("ELTRuntimePropertiesWidget"),
    @XmlEnumValue("ELTFilePathWidget")
    ELT_FILE_PATH_WIDGET("ELTFilePathWidget"),
    @XmlEnumValue("ELTCharacterSetWidget")
    ELT_CHARACTER_SET_WIDGET("ELTCharacterSetWidget"),
    @XmlEnumValue("ELTDelimeterWidget")
    ELT_DELIMETER_WIDGET("ELTDelimeterWidget"),
    @XmlEnumValue("ELTPhaseWidget")
    ELT_PHASE_WIDGET("ELTPhaseWidget"),
    @XmlEnumValue("ELTHasHeaderWidget")
    ELT_HAS_HEADER_WIDGET("ELTHasHeaderWidget"),
    @XmlEnumValue("ELTSafePropertyWidget")
    ELT_SAFE_PROPERTY_WIDGET("ELTSafePropertyWidget"),
    @XmlEnumValue("ELTFieldSequenceWidget")
    ELT_FIELD_SEQUENCE_WIDGET("ELTFieldSequenceWidget"),
    @XmlEnumValue("ELTFilterPropertyWidget")
    ELT_FILTER_PROPERTY_WIDGET("ELTFilterPropertyWidget"),
    @XmlEnumValue("ELTOperationalClassWidget")
    ELT_OPERATIONAL_CLASS_WIDGET("ELTOperationalClassWidget"),
    @XmlEnumValue("ELTStrictClassWidget")
    ELT_STRICT_CLASS_WIDGET("ELTStrictClassWidget"),
    @XmlEnumValue("ELTFixedWidget")
    ELT_FIXED_WIDGET("ELTFixedWidget"),
    @XmlEnumValue("ELTRetentionlogicWidget")
    ELT_RETENTIONLOGIC_WIDGET("ELTRetentionlogicWidget");
    private final String value;

    PropertyRenderer(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PropertyRenderer fromValue(String v) {
        for (PropertyRenderer c: PropertyRenderer.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
