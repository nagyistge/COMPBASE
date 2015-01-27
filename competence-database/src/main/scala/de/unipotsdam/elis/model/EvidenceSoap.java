/**
 * EvidenceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.unipotsdam.elis.model;

public class EvidenceSoap  implements java.io.Serializable {
    private java.lang.String activityTyp;

    private long companyId;

    private java.util.Calendar createDate;

    private long evidenceId;

    private long groupId;

    private java.lang.String link;

    private java.util.Calendar modifiedDate;

    private long primaryKey;

    private java.lang.String summary;

    private java.lang.String title;

    private long userId;

    private java.lang.String userName;

    public EvidenceSoap() {
    }

    public EvidenceSoap(
           java.lang.String activityTyp,
           long companyId,
           java.util.Calendar createDate,
           long evidenceId,
           long groupId,
           java.lang.String link,
           java.util.Calendar modifiedDate,
           long primaryKey,
           java.lang.String summary,
           java.lang.String title,
           long userId,
           java.lang.String userName) {
           this.activityTyp = activityTyp;
           this.companyId = companyId;
           this.createDate = createDate;
           this.evidenceId = evidenceId;
           this.groupId = groupId;
           this.link = link;
           this.modifiedDate = modifiedDate;
           this.primaryKey = primaryKey;
           this.summary = summary;
           this.title = title;
           this.userId = userId;
           this.userName = userName;
    }


    /**
     * Gets the activityTyp value for this EvidenceSoap.
     * 
     * @return activityTyp
     */
    public java.lang.String getActivityTyp() {
        return activityTyp;
    }


    /**
     * Sets the activityTyp value for this EvidenceSoap.
     * 
     * @param activityTyp
     */
    public void setActivityTyp(java.lang.String activityTyp) {
        this.activityTyp = activityTyp;
    }


    /**
     * Gets the companyId value for this EvidenceSoap.
     * 
     * @return companyId
     */
    public long getCompanyId() {
        return companyId;
    }


    /**
     * Sets the companyId value for this EvidenceSoap.
     * 
     * @param companyId
     */
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }


    /**
     * Gets the createDate value for this EvidenceSoap.
     * 
     * @return createDate
     */
    public java.util.Calendar getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this EvidenceSoap.
     * 
     * @param createDate
     */
    public void setCreateDate(java.util.Calendar createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the evidenceId value for this EvidenceSoap.
     * 
     * @return evidenceId
     */
    public long getEvidenceId() {
        return evidenceId;
    }


    /**
     * Sets the evidenceId value for this EvidenceSoap.
     * 
     * @param evidenceId
     */
    public void setEvidenceId(long evidenceId) {
        this.evidenceId = evidenceId;
    }


    /**
     * Gets the groupId value for this EvidenceSoap.
     * 
     * @return groupId
     */
    public long getGroupId() {
        return groupId;
    }


    /**
     * Sets the groupId value for this EvidenceSoap.
     * 
     * @param groupId
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    /**
     * Gets the link value for this EvidenceSoap.
     * 
     * @return link
     */
    public java.lang.String getLink() {
        return link;
    }


    /**
     * Sets the link value for this EvidenceSoap.
     * 
     * @param link
     */
    public void setLink(java.lang.String link) {
        this.link = link;
    }


    /**
     * Gets the modifiedDate value for this EvidenceSoap.
     * 
     * @return modifiedDate
     */
    public java.util.Calendar getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this EvidenceSoap.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(java.util.Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the primaryKey value for this EvidenceSoap.
     * 
     * @return primaryKey
     */
    public long getPrimaryKey() {
        return primaryKey;
    }


    /**
     * Sets the primaryKey value for this EvidenceSoap.
     * 
     * @param primaryKey
     */
    public void setPrimaryKey(long primaryKey) {
        this.primaryKey = primaryKey;
    }


    /**
     * Gets the summary value for this EvidenceSoap.
     * 
     * @return summary
     */
    public java.lang.String getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this EvidenceSoap.
     * 
     * @param summary
     */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }


    /**
     * Gets the title value for this EvidenceSoap.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this EvidenceSoap.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the userId value for this EvidenceSoap.
     * 
     * @return userId
     */
    public long getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this EvidenceSoap.
     * 
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }


    /**
     * Gets the userName value for this EvidenceSoap.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this EvidenceSoap.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EvidenceSoap)) return false;
        EvidenceSoap other = (EvidenceSoap) obj;
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
            this.companyId == other.getCompanyId() &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            this.evidenceId == other.getEvidenceId() &&
            this.groupId == other.getGroupId() &&
            ((this.link==null && other.getLink()==null) || 
             (this.link!=null &&
              this.link.equals(other.getLink()))) &&
            ((this.modifiedDate==null && other.getModifiedDate()==null) || 
             (this.modifiedDate!=null &&
              this.modifiedDate.equals(other.getModifiedDate()))) &&
            this.primaryKey == other.getPrimaryKey() &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            this.userId == other.getUserId() &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName())));
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
        _hashCode += new Long(getCompanyId()).hashCode();
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        _hashCode += new Long(getEvidenceId()).hashCode();
        _hashCode += new Long(getGroupId()).hashCode();
        if (getLink() != null) {
            _hashCode += getLink().hashCode();
        }
        if (getModifiedDate() != null) {
            _hashCode += getModifiedDate().hashCode();
        }
        _hashCode += new Long(getPrimaryKey()).hashCode();
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        _hashCode += new Long(getUserId()).hashCode();
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EvidenceSoap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.elis.unipotsdam.de", "EvidenceSoap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityTyp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityTyp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "companyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("evidenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "evidenceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link");
        elemField.setXmlName(new javax.xml.namespace.QName("", "link"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "primaryKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("", "summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
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
