/**
 * CompetenceServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.service.soap.client;

public class CompetenceServiceImplServiceLocator extends org.apache.axis.client.Service implements uzuzjmd.competence.service.soap.client.CompetenceServiceImplService {

    public CompetenceServiceImplServiceLocator() {
    }


    public CompetenceServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CompetenceServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CompetenceServiceImplPort
    private java.lang.String CompetenceServiceImplPort_address = "http://localhost:8081/WS/Competence";

    public java.lang.String getCompetenceServiceImplPortAddress() {
        return CompetenceServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CompetenceServiceImplPortWSDDServiceName = "CompetenceServiceImplPort";

    public java.lang.String getCompetenceServiceImplPortWSDDServiceName() {
        return CompetenceServiceImplPortWSDDServiceName;
    }

    public void setCompetenceServiceImplPortWSDDServiceName(java.lang.String name) {
        CompetenceServiceImplPortWSDDServiceName = name;
    }

    public uzuzjmd.competence.service.soap.client.CompetenceService getCompetenceServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CompetenceServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCompetenceServiceImplPort(endpoint);
    }

    public uzuzjmd.competence.service.soap.client.CompetenceService getCompetenceServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uzuzjmd.competence.service.soap.client.CompetenceServiceImplPortBindingStub _stub = new uzuzjmd.competence.service.soap.client.CompetenceServiceImplPortBindingStub(portAddress, this);
            _stub.setPortName(getCompetenceServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCompetenceServiceImplPortEndpointAddress(java.lang.String address) {
        CompetenceServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uzuzjmd.competence.service.soap.client.CompetenceService.class.isAssignableFrom(serviceEndpointInterface)) {
                uzuzjmd.competence.service.soap.client.CompetenceServiceImplPortBindingStub _stub = new uzuzjmd.competence.service.soap.client.CompetenceServiceImplPortBindingStub(new java.net.URL(CompetenceServiceImplPort_address), this);
                _stub.setPortName(getCompetenceServiceImplPortWSDDServiceName());
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
        if ("CompetenceServiceImplPort".equals(inputPortName)) {
            return getCompetenceServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://competence.service.uzuzjmd/", "CompetenceServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://competence.service.uzuzjmd/", "CompetenceServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CompetenceServiceImplPort".equals(portName)) {
            setCompetenceServiceImplPortEndpointAddress(address);
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
