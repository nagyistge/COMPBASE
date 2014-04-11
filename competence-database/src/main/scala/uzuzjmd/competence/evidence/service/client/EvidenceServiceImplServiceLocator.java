/**
 * EvidenceServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.evidence.service.client;

public class EvidenceServiceImplServiceLocator extends org.apache.axis.client.Service implements uzuzjmd.competence.evidence.service.client.EvidenceServiceImplService {

    public EvidenceServiceImplServiceLocator() {
    }


    public EvidenceServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EvidenceServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EvidenceServiceImplPort
    private java.lang.String EvidenceServiceImplPort_address = "http://localhost:8082/WS/Competence/Evidence";

    public java.lang.String getEvidenceServiceImplPortAddress() {
        return EvidenceServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EvidenceServiceImplPortWSDDServiceName = "EvidenceServiceImplPort";

    public java.lang.String getEvidenceServiceImplPortWSDDServiceName() {
        return EvidenceServiceImplPortWSDDServiceName;
    }

    public void setEvidenceServiceImplPortWSDDServiceName(java.lang.String name) {
        EvidenceServiceImplPortWSDDServiceName = name;
    }

    public uzuzjmd.competence.evidence.service.client.EvidenceService getEvidenceServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EvidenceServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEvidenceServiceImplPort(endpoint);
    }

    public uzuzjmd.competence.evidence.service.client.EvidenceService getEvidenceServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uzuzjmd.competence.evidence.service.client.EvidenceServiceImplPortBindingStub _stub = new uzuzjmd.competence.evidence.service.client.EvidenceServiceImplPortBindingStub(portAddress, this);
            _stub.setPortName(getEvidenceServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEvidenceServiceImplPortEndpointAddress(java.lang.String address) {
        EvidenceServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uzuzjmd.competence.evidence.service.client.EvidenceService.class.isAssignableFrom(serviceEndpointInterface)) {
                uzuzjmd.competence.evidence.service.client.EvidenceServiceImplPortBindingStub _stub = new uzuzjmd.competence.evidence.service.client.EvidenceServiceImplPortBindingStub(new java.net.URL(EvidenceServiceImplPort_address), this);
                _stub.setPortName(getEvidenceServiceImplPortWSDDServiceName());
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
        if ("EvidenceServiceImplPort".equals(inputPortName)) {
            return getEvidenceServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.evidence.competence.uzuzjmd/", "EvidenceServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.evidence.competence.uzuzjmd/", "EvidenceServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EvidenceServiceImplPort".equals(portName)) {
            setEvidenceServiceImplPortEndpointAddress(address);
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
