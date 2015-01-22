/**
 * SocialActivityServiceSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.liferay.portlet.social.service.http;

public class SocialActivityServiceSoapServiceLocator extends org.apache.axis.client.Service implements com.liferay.portlet.social.service.http.SocialActivityServiceSoapService {

    public SocialActivityServiceSoapServiceLocator() {
    }


    public SocialActivityServiceSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SocialActivityServiceSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Portlet_Social_SocialActivityService
    private java.lang.String Portlet_Social_SocialActivityService_address = "http://localhost:8080/api/axis/Portlet_Social_SocialActivityService";

    public java.lang.String getPortlet_Social_SocialActivityServiceAddress() {
        return Portlet_Social_SocialActivityService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Portlet_Social_SocialActivityServiceWSDDServiceName = "Portlet_Social_SocialActivityService";

    public java.lang.String getPortlet_Social_SocialActivityServiceWSDDServiceName() {
        return Portlet_Social_SocialActivityServiceWSDDServiceName;
    }

    public void setPortlet_Social_SocialActivityServiceWSDDServiceName(java.lang.String name) {
        Portlet_Social_SocialActivityServiceWSDDServiceName = name;
    }

    public com.liferay.portlet.social.service.http.SocialActivityServiceSoap getPortlet_Social_SocialActivityService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Portlet_Social_SocialActivityService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPortlet_Social_SocialActivityService(endpoint);
    }

    public com.liferay.portlet.social.service.http.SocialActivityServiceSoap getPortlet_Social_SocialActivityService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.liferay.portlet.social.service.http.Portlet_Social_SocialActivityServiceSoapBindingStub _stub = new com.liferay.portlet.social.service.http.Portlet_Social_SocialActivityServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getPortlet_Social_SocialActivityServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPortlet_Social_SocialActivityServiceEndpointAddress(java.lang.String address) {
        Portlet_Social_SocialActivityService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.liferay.portlet.social.service.http.SocialActivityServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.liferay.portlet.social.service.http.Portlet_Social_SocialActivityServiceSoapBindingStub _stub = new com.liferay.portlet.social.service.http.Portlet_Social_SocialActivityServiceSoapBindingStub(new java.net.URL(Portlet_Social_SocialActivityService_address), this);
                _stub.setPortName(getPortlet_Social_SocialActivityServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Portlet_Social_SocialActivityService".equals(inputPortName)) {
            return getPortlet_Social_SocialActivityService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:http.service.social.portlet.liferay.com", "SocialActivityServiceSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:http.service.social.portlet.liferay.com", "Portlet_Social_SocialActivityService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Portlet_Social_SocialActivityService".equals(portName)) {
            setPortlet_Social_SocialActivityServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
