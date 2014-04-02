/**
 * GetCompetencesResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.service.soap.client;

public class GetCompetencesResponseReturn  implements java.io.Serializable, org.apache.axis.encoding.AnyContentType {
    private java.lang.String identifier;

    private org.imsglobal.www.xsd.imsrdceo_rootv1p0.Title title;

    private org.imsglobal.www.xsd.imsrdceo_rootv1p0.Description description;

    private uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturnDefinition[] definition;

    private org.imsglobal.www.xsd.imsrdceo_rootv1p0.Metadata metadata;

    private org.apache.axis.message.MessageElement [] _any;

    public GetCompetencesResponseReturn() {
    }

    public GetCompetencesResponseReturn(
           java.lang.String identifier,
           org.imsglobal.www.xsd.imsrdceo_rootv1p0.Title title,
           org.imsglobal.www.xsd.imsrdceo_rootv1p0.Description description,
           uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturnDefinition[] definition,
           org.imsglobal.www.xsd.imsrdceo_rootv1p0.Metadata metadata,
           org.apache.axis.message.MessageElement [] _any) {
           this.identifier = identifier;
           this.title = title;
           this.description = description;
           this.definition = definition;
           this.metadata = metadata;
           this._any = _any;
    }


    /**
     * Gets the identifier value for this GetCompetencesResponseReturn.
     * 
     * @return identifier
     */
    public java.lang.String getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this GetCompetencesResponseReturn.
     * 
     * @param identifier
     */
    public void setIdentifier(java.lang.String identifier) {
        this.identifier = identifier;
    }


    /**
     * Gets the title value for this GetCompetencesResponseReturn.
     * 
     * @return title
     */
    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Title getTitle() {
        return title;
    }


    /**
     * Sets the title value for this GetCompetencesResponseReturn.
     * 
     * @param title
     */
    public void setTitle(org.imsglobal.www.xsd.imsrdceo_rootv1p0.Title title) {
        this.title = title;
    }


    /**
     * Gets the description value for this GetCompetencesResponseReturn.
     * 
     * @return description
     */
    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Description getDescription() {
        return description;
    }


    /**
     * Sets the description value for this GetCompetencesResponseReturn.
     * 
     * @param description
     */
    public void setDescription(org.imsglobal.www.xsd.imsrdceo_rootv1p0.Description description) {
        this.description = description;
    }


    /**
     * Gets the definition value for this GetCompetencesResponseReturn.
     * 
     * @return definition
     */
    public uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturnDefinition[] getDefinition() {
        return definition;
    }


    /**
     * Sets the definition value for this GetCompetencesResponseReturn.
     * 
     * @param definition
     */
    public void setDefinition(uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturnDefinition[] definition) {
        this.definition = definition;
    }

    public uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturnDefinition getDefinition(int i) {
        return this.definition[i];
    }

    public void setDefinition(int i, uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturnDefinition _value) {
        this.definition[i] = _value;
    }


    /**
     * Gets the metadata value for this GetCompetencesResponseReturn.
     * 
     * @return metadata
     */
    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Metadata getMetadata() {
        return metadata;
    }


    /**
     * Sets the metadata value for this GetCompetencesResponseReturn.
     * 
     * @param metadata
     */
    public void setMetadata(org.imsglobal.www.xsd.imsrdceo_rootv1p0.Metadata metadata) {
        this.metadata = metadata;
    }


    /**
     * Gets the _any value for this GetCompetencesResponseReturn.
     * 
     * @return _any
     */
    public org.apache.axis.message.MessageElement [] get_any() {
        return _any;
    }


    /**
     * Sets the _any value for this GetCompetencesResponseReturn.
     * 
     * @param _any
     */
    public void set_any(org.apache.axis.message.MessageElement [] _any) {
        this._any = _any;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCompetencesResponseReturn)) return false;
        GetCompetencesResponseReturn other = (GetCompetencesResponseReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              this.identifier.equals(other.getIdentifier()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.definition==null && other.getDefinition()==null) || 
             (this.definition!=null &&
              java.util.Arrays.equals(this.definition, other.getDefinition()))) &&
            ((this.metadata==null && other.getMetadata()==null) || 
             (this.metadata!=null &&
              this.metadata.equals(other.getMetadata()))) &&
            ((this._any==null && other.get_any()==null) || 
             (this._any!=null &&
              java.util.Arrays.equals(this._any, other.get_any())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIdentifier() != null) {
            _hashCode += getIdentifier().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDefinition() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDefinition());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDefinition(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMetadata() != null) {
            _hashCode += getMetadata().hashCode();
        }
        if (get_any() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(get_any());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(get_any(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCompetencesResponseReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://competence.service.uzuzjmd/", ">getCompetencesResponse>return"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">identifier"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">title"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">description"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://competence.service.uzuzjmd/", "definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://competence.service.uzuzjmd/", ">>getCompetencesResponse>return>definition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "metadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">metadata"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
