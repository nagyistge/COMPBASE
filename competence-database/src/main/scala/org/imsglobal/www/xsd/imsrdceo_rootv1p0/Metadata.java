/**
 * Metadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imsglobal.www.xsd.imsrdceo_rootv1p0;

public class Metadata  implements java.io.Serializable, org.apache.axis.encoding.AnyContentType {
    private java.lang.String rdceoschema;

    private java.lang.String rdceoschemaversion;

    private org.apache.axis.message.MessageElement [] _any;

    public Metadata() {
    }

    public Metadata(
           java.lang.String rdceoschema,
           java.lang.String rdceoschemaversion,
           org.apache.axis.message.MessageElement [] _any) {
           this.rdceoschema = rdceoschema;
           this.rdceoschemaversion = rdceoschemaversion;
           this._any = _any;
    }


    /**
     * Gets the rdceoschema value for this Metadata.
     * 
     * @return rdceoschema
     */
    public java.lang.String getRdceoschema() {
        return rdceoschema;
    }


    /**
     * Sets the rdceoschema value for this Metadata.
     * 
     * @param rdceoschema
     */
    public void setRdceoschema(java.lang.String rdceoschema) {
        this.rdceoschema = rdceoschema;
    }


    /**
     * Gets the rdceoschemaversion value for this Metadata.
     * 
     * @return rdceoschemaversion
     */
    public java.lang.String getRdceoschemaversion() {
        return rdceoschemaversion;
    }


    /**
     * Sets the rdceoschemaversion value for this Metadata.
     * 
     * @param rdceoschemaversion
     */
    public void setRdceoschemaversion(java.lang.String rdceoschemaversion) {
        this.rdceoschemaversion = rdceoschemaversion;
    }


    /**
     * Gets the _any value for this Metadata.
     * 
     * @return _any
     */
    public org.apache.axis.message.MessageElement [] get_any() {
        return _any;
    }


    /**
     * Sets the _any value for this Metadata.
     * 
     * @param _any
     */
    public void set_any(org.apache.axis.message.MessageElement [] _any) {
        this._any = _any;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Metadata)) return false;
        Metadata other = (Metadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rdceoschema==null && other.getRdceoschema()==null) || 
             (this.rdceoschema!=null &&
              this.rdceoschema.equals(other.getRdceoschema()))) &&
            ((this.rdceoschemaversion==null && other.getRdceoschemaversion()==null) || 
             (this.rdceoschemaversion!=null &&
              this.rdceoschemaversion.equals(other.getRdceoschemaversion()))) &&
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
        if (getRdceoschema() != null) {
            _hashCode += getRdceoschema().hashCode();
        }
        if (getRdceoschemaversion() != null) {
            _hashCode += getRdceoschemaversion().hashCode();
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
        new org.apache.axis.description.TypeDesc(Metadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">metadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rdceoschema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "rdceoschema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">rdceoschema"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rdceoschemaversion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "rdceoschemaversion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">rdceoschemaversion"));
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
