//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.25 at 01:58:00 AM CET 
//


package org.w3._2002._07.owl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubClassOf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubClassOf">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2002/07/owl#}ClassAxiom">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.w3.org/2002/07/owl#}ClassExpression"/>
 *         &lt;group ref="{http://www.w3.org/2002/07/owl#}ClassExpression"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubClassOf", propOrder = {
    "rest"
})
public class SubClassOf
    extends ClassAxiom
{

    @XmlElementRefs({
        @XmlElementRef(name = "ObjectUnionOf", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectHasSelf", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectExactCardinality", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DataSomeValuesFrom", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectHasValue", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectIntersectionOf", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Class", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectOneOf", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DataExactCardinality", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DataMaxCardinality", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DataMinCardinality", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectMaxCardinality", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DataAllValuesFrom", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectSomeValuesFrom", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectAllValuesFrom", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DataHasValue", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectMinCardinality", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ObjectComplementOf", namespace = "http://www.w3.org/2002/07/owl#", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<? extends ClassExpression>> rest;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "Clazz" is used by two different parts of a schema. See: 
     * line 319 of file:/I:/workspace/Wissensmodellierung/owlneo4j/owl.xsd
     * line 319 of file:/I:/workspace/Wissensmodellierung/owlneo4j/owl.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ObjectExactCardinality }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectHasSelf }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectUnionOf }{@code >}
     * {@link JAXBElement }{@code <}{@link DataSomeValuesFrom }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectHasValue }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectIntersectionOf }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectOneOf }{@code >}
     * {@link JAXBElement }{@code <}{@link Class }{@code >}
     * {@link JAXBElement }{@code <}{@link DataExactCardinality }{@code >}
     * {@link JAXBElement }{@code <}{@link DataMinCardinality }{@code >}
     * {@link JAXBElement }{@code <}{@link DataMaxCardinality }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectMaxCardinality }{@code >}
     * {@link JAXBElement }{@code <}{@link DataAllValuesFrom }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectSomeValuesFrom }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectAllValuesFrom }{@code >}
     * {@link JAXBElement }{@code <}{@link DataHasValue }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectMinCardinality }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectComplementOf }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends ClassExpression>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<? extends ClassExpression>>();
        }
        return this.rest;
    }

}