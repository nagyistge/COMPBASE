/**
 * Statement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imsglobal.www.xsd.imsrdceo_rootv1p0;

public class Statement  implements java.io.Serializable, org.apache.axis.encoding.AnyContentType {
    private org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtext statementtext;

    private org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtoken statementtoken;

    private org.apache.axis.message.MessageElement [] _any;

    private org.apache.axis.types.Id statementid;  // attribute

    private java.lang.String statementname;  // attribute

    public Statement() {
    }

    public Statement(
           org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtext statementtext,
           org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtoken statementtoken,
           org.apache.axis.message.MessageElement [] _any,
           org.apache.axis.types.Id statementid,
           java.lang.String statementname) {
           this.statementtext = statementtext;
           this.statementtoken = statementtoken;
           this._any = _any;
           this.statementid = statementid;
           this.statementname = statementname;
    }


    /**
     * Gets the statementtext value for this Statement.
     * 
     * @return statementtext
     */
    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtext getStatementtext() {
        return statementtext;
    }


    /**
     * Sets the statementtext value for this Statement.
     * 
     * @param statementtext
     */
    public void setStatementtext(org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtext statementtext) {
        this.statementtext = statementtext;
    }


    /**
     * Gets the statementtoken value for this Statement.
     * 
     * @return statementtoken
     */
    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtoken getStatementtoken() {
        return statementtoken;
    }


    /**
     * Sets the statementtoken value for this Statement.
     * 
     * @param statementtoken
     */
    public void setStatementtoken(org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statementtoken statementtoken) {
        this.statementtoken = statementtoken;
    }


    /**
     * Gets the _any value for this Statement.
     * 
     * @return _any
     */
    public org.apache.axis.message.MessageElement [] get_any() {
        return _any;
    }


    /**
     * Sets the _any value for this Statement.
     * 
     * @param _any
     */
    public void set_any(org.apache.axis.message.MessageElement [] _any) {
        this._any = _any;
    }


    /**
     * Gets the statementid value for this Statement.
     * 
     * @return statementid
     */
    public org.apache.axis.types.Id getStatementid() {
        return statementid;
    }


    /**
     * Sets the statementid value for this Statement.
     * 
     * @param statementid
     */
    public void setStatementid(org.apache.axis.types.Id statementid) {
        this.statementid = statementid;
    }


    /**
     * Gets the statementname value for this Statement.
     * 
     * @return statementname
     */
    public java.lang.String getStatementname() {
        return statementname;
    }


    /**
     * Sets the statementname value for this Statement.
     * 
     * @param statementname
     */
    public void setStatementname(java.lang.String statementname) {
        this.statementname = statementname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Statement)) return false;
        Statement other = (Statement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statementtext==null && other.getStatementtext()==null) || 
             (this.statementtext!=null &&
              this.statementtext.equals(other.getStatementtext()))) &&
            ((this.statementtoken==null && other.getStatementtoken()==null) || 
             (this.statementtoken!=null &&
              this.statementtoken.equals(other.getStatementtoken()))) &&
            ((this._any==null && other.get_any()==null) || 
             (this._any!=null &&
              java.util.Arrays.equals(this._any, other.get_any()))) &&
            ((this.statementid==null && other.getStatementid()==null) || 
             (this.statementid!=null &&
              this.statementid.equals(other.getStatementid()))) &&
            ((this.statementname==null && other.getStatementname()==null) || 
             (this.statementname!=null &&
              this.statementname.equals(other.getStatementname())));
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
        if (getStatementtext() != null) {
            _hashCode += getStatementtext().hashCode();
        }
        if (getStatementtoken() != null) {
            _hashCode += getStatementtoken().hashCode();
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
        if (getStatementid() != null) {
            _hashCode += getStatementid().hashCode();
        }
        if (getStatementname() != null) {
            _hashCode += getStatementname().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Statement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">statement"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("statementid");
        attrField.setXmlName(new javax.xml.namespace.QName("", "statementid"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "ID"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("statementname");
        attrField.setXmlName(new javax.xml.namespace.QName("", "statementname"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statementtext");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "statementtext"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">statementtext"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statementtoken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "statementtoken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">statementtoken"));
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
