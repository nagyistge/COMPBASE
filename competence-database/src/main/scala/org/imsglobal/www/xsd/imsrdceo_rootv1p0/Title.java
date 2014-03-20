/**
 * Title.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imsglobal.www.xsd.imsrdceo_rootv1p0;

public class Title  implements java.io.Serializable, org.apache.axis.encoding.AnyContentType {
    private java.lang.String[] langstring;

    private org.apache.axis.message.MessageElement [] _any;

    public Title() {
    }

    public Title(
           java.lang.String[] langstring,
           org.apache.axis.message.MessageElement [] _any) {
           this.langstring = langstring;
           this._any = _any;
    }


    /**
     * Gets the langstring value for this Title.
     * 
     * @return langstring
     */
    public java.lang.String[] getLangstring() {
        return langstring;
    }


    /**
     * Sets the langstring value for this Title.
     * 
     * @param langstring
     */
    public void setLangstring(java.lang.String[] langstring) {
        this.langstring = langstring;
    }

    public java.lang.String getLangstring(int i) {
        return this.langstring[i];
    }

    public void setLangstring(int i, java.lang.String _value) {
        this.langstring[i] = _value;
    }


    /**
     * Gets the _any value for this Title.
     * 
     * @return _any
     */
    public org.apache.axis.message.MessageElement [] get_any() {
        return _any;
    }


    /**
     * Sets the _any value for this Title.
     * 
     * @param _any
     */
    public void set_any(org.apache.axis.message.MessageElement [] _any) {
        this._any = _any;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Title)) return false;
        Title other = (Title) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.langstring==null && other.getLangstring()==null) || 
             (this.langstring!=null &&
              java.util.Arrays.equals(this.langstring, other.getLangstring()))) &&
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
        if (getLangstring() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLangstring());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLangstring(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(Title.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">title"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("langstring");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "langstring"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "langstring"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
