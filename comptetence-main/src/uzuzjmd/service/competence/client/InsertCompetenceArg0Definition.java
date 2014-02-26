/**
 * InsertCompetenceArg0Definition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.service.competence.client;

public class InsertCompetenceArg0Definition  implements java.io.Serializable, org.apache.axis.encoding.AnyContentType {
    private java.lang.String model;

    private org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statement[] statement;

    private org.apache.axis.message.MessageElement [] _any;

    public InsertCompetenceArg0Definition() {
    }

    public InsertCompetenceArg0Definition(
           java.lang.String model,
           org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statement[] statement,
           org.apache.axis.message.MessageElement [] _any) {
           this.model = model;
           this.statement = statement;
           this._any = _any;
    }


    /**
     * Gets the model value for this InsertCompetenceArg0Definition.
     * 
     * @return model
     */
    public java.lang.String getModel() {
        return model;
    }


    /**
     * Sets the model value for this InsertCompetenceArg0Definition.
     * 
     * @param model
     */
    public void setModel(java.lang.String model) {
        this.model = model;
    }


    /**
     * Gets the statement value for this InsertCompetenceArg0Definition.
     * 
     * @return statement
     */
    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statement[] getStatement() {
        return statement;
    }


    /**
     * Sets the statement value for this InsertCompetenceArg0Definition.
     * 
     * @param statement
     */
    public void setStatement(org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statement[] statement) {
        this.statement = statement;
    }

    public org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statement getStatement(int i) {
        return this.statement[i];
    }

    public void setStatement(int i, org.imsglobal.www.xsd.imsrdceo_rootv1p0.Statement _value) {
        this.statement[i] = _value;
    }


    /**
     * Gets the _any value for this InsertCompetenceArg0Definition.
     * 
     * @return _any
     */
    public org.apache.axis.message.MessageElement [] get_any() {
        return _any;
    }


    /**
     * Sets the _any value for this InsertCompetenceArg0Definition.
     * 
     * @param _any
     */
    public void set_any(org.apache.axis.message.MessageElement [] _any) {
        this._any = _any;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertCompetenceArg0Definition)) return false;
        InsertCompetenceArg0Definition other = (InsertCompetenceArg0Definition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.model==null && other.getModel()==null) || 
             (this.model!=null &&
              this.model.equals(other.getModel()))) &&
            ((this.statement==null && other.getStatement()==null) || 
             (this.statement!=null &&
              java.util.Arrays.equals(this.statement, other.getStatement()))) &&
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
        if (getModel() != null) {
            _hashCode += getModel().hashCode();
        }
        if (getStatement() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatement());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatement(), i);
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
        new org.apache.axis.description.TypeDesc(InsertCompetenceArg0Definition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://competence.service.uzuzjmd/", ">>insertCompetence>arg0>definition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("model");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "model"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", ">model"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "statement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.imsglobal.org/xsd/imsrdceo_rootv1p0", "statement"));
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
