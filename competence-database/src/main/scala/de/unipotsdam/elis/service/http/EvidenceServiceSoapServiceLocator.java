/**
 * EvidenceServiceSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.unipotsdam.elis.service.http;

public class EvidenceServiceSoapServiceLocator extends org.apache.axis.client.Service implements de.unipotsdam.elis.service.http.EvidenceServiceSoapService {

    public EvidenceServiceSoapServiceLocator() {
    }


    public EvidenceServiceSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EvidenceServiceSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Plugin_UPServices_EvidenceService
    private java.lang.String Plugin_UPServices_EvidenceService_address = "http://127.0.0.1:8080/competence-portlet/api/axis/Plugin_UPServices_EvidenceService";

    public java.lang.String getPlugin_UPServices_EvidenceServiceAddress() {
        return Plugin_UPServices_EvidenceService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Plugin_UPServices_EvidenceServiceWSDDServiceName = "Plugin_UPServices_EvidenceService";

    public java.lang.String getPlugin_UPServices_EvidenceServiceWSDDServiceName() {
        return Plugin_UPServices_EvidenceServiceWSDDServiceName;
    }

    public void setPlugin_UPServices_EvidenceServiceWSDDServiceName(java.lang.String name) {
        Plugin_UPServices_EvidenceServiceWSDDServiceName = name;
    }

    public de.unipotsdam.elis.service.http.EvidenceServiceSoap getPlugin_UPServices_EvidenceService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Plugin_UPServices_EvidenceService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPlugin_UPServices_EvidenceService(endpoint);
    }

    public de.unipotsdam.elis.service.http.EvidenceServiceSoap getPlugin_UPServices_EvidenceService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.unipotsdam.elis.service.http.Plugin_UPServices_EvidenceServiceSoapBindingStub _stub = new de.unipotsdam.elis.service.http.Plugin_UPServices_EvidenceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getPlugin_UPServices_EvidenceServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPlugin_UPServices_EvidenceServiceEndpointAddress(java.lang.String address) {
        Plugin_UPServices_EvidenceService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.unipotsdam.elis.service.http.EvidenceServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                de.unipotsdam.elis.service.http.Plugin_UPServices_EvidenceServiceSoapBindingStub _stub = new de.unipotsdam.elis.service.http.Plugin_UPServices_EvidenceServiceSoapBindingStub(new java.net.URL(Plugin_UPServices_EvidenceService_address), this);
                _stub.setPortName(getPlugin_UPServices_EvidenceServiceWSDDServiceName());
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
        if ("Plugin_UPServices_EvidenceService".equals(inputPortName)) {
            return getPlugin_UPServices_EvidenceService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:http.service.elis.unipotsdam.de", "EvidenceServiceSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:http.service.elis.unipotsdam.de", "Plugin_UPServices_EvidenceService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Plugin_UPServices_EvidenceService".equals(portName)) {
            setPlugin_UPServices_EvidenceServiceEndpointAddress(address);
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
