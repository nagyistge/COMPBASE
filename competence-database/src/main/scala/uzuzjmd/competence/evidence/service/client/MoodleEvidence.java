/**
 * MoodleEvidence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.evidence.service.client;

public class MoodleEvidence  implements java.io.Serializable {
    private java.lang.String activityTyp;

    private java.lang.String course;

    public MoodleEvidence() {
    }

    public MoodleEvidence(
           java.lang.String activityTyp,
           java.lang.String course) {
           this.activityTyp = activityTyp;
           this.course = course;
    }


    /**
     * Gets the activityTyp value for this MoodleEvidence.
     * 
     * @return activityTyp
     */
    public java.lang.String getActivityTyp() {
        return activityTyp;
    }


    /**
     * Sets the activityTyp value for this MoodleEvidence.
     * 
     * @param activityTyp
     */
    public void setActivityTyp(java.lang.String activityTyp) {
        this.activityTyp = activityTyp;
    }


    /**
     * Gets the course value for this MoodleEvidence.
     * 
     * @return course
     */
    public java.lang.String getCourse() {
        return course;
    }


    /**
     * Sets the course value for this MoodleEvidence.
     * 
     * @param course
     */
    public void setCourse(java.lang.String course) {
        this.course = course;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MoodleEvidence)) return false;
        MoodleEvidence other = (MoodleEvidence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activityTyp==null && other.getActivityTyp()==null) || 
             (this.activityTyp!=null &&
              this.activityTyp.equals(other.getActivityTyp()))) &&
            ((this.course==null && other.getCourse()==null) || 
             (this.course!=null &&
              this.course.equals(other.getCourse())));
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
        if (getActivityTyp() != null) {
            _hashCode += getActivityTyp().hashCode();
        }
        if (getCourse() != null) {
            _hashCode += getCourse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MoodleEvidence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.evidence.competence.uzuzjmd/", "moodleEvidence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityTyp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityTyp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("course");
        elemField.setXmlName(new javax.xml.namespace.QName("", "course"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
