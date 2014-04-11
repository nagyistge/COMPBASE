/**
 * MoodleEvidenceServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.evidence.service.client;

public class MoodleEvidenceServiceImplServiceLocator extends org.apache.axis.client.Service implements uzuzjmd.competence.evidence.service.client.MoodleEvidenceServiceImplService {

    public MoodleEvidenceServiceImplServiceLocator() {
    }


    public MoodleEvidenceServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MoodleEvidenceServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MoodleEvidenceServiceImplPort
    private java.lang.String MoodleEvidenceServiceImplPort_address = "http://localhost:8082/WS/Competence/Evidence";

    public java.lang.String getMoodleEvidenceServiceImplPortAddress() {
        return MoodleEvidenceServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MoodleEvidenceServiceImplPortWSDDServiceName = "MoodleEvidenceServiceImplPort";

    public java.lang.String getMoodleEvidenceServiceImplPortWSDDServiceName() {
        return MoodleEvidenceServiceImplPortWSDDServiceName;
    }

    public void setMoodleEvidenceServiceImplPortWSDDServiceName(java.lang.String name) {
        MoodleEvidenceServiceImplPortWSDDServiceName = name;
    }

    public uzuzjmd.competence.evidence.service.client.EvidenceService getMoodleEvidenceServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MoodleEvidenceServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMoodleEvidenceServiceImplPort(endpoint);
    }

    public uzuzjmd.competence.evidence.service.client.EvidenceService getMoodleEvidenceServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            uzuzjmd.competence.evidence.service.client.MoodleEvidenceServiceImplPortBindingStub _stub = new uzuzjmd.competence.evidence.service.client.MoodleEvidenceServiceImplPortBindingStub(portAddress, this);
            _stub.setPortName(getMoodleEvidenceServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMoodleEvidenceServiceImplPortEndpointAddress(java.lang.String address) {
        MoodleEvidenceServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (uzuzjmd.competence.evidence.service.client.EvidenceService.class.isAssignableFrom(serviceEndpointInterface)) {
                uzuzjmd.competence.evidence.service.client.MoodleEvidenceServiceImplPortBindingStub _stub = new uzuzjmd.competence.evidence.service.client.MoodleEvidenceServiceImplPortBindingStub(new java.net.URL(MoodleEvidenceServiceImplPort_address), this);
                _stub.setPortName(getMoodleEvidenceServiceImplPortWSDDServiceName());
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
        if ("MoodleEvidenceServiceImplPort".equals(inputPortName)) {
            return getMoodleEvidenceServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.evidence.competence.uzuzjmd/", "MoodleEvidenceServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.evidence.competence.uzuzjmd/", "MoodleEvidenceServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MoodleEvidenceServiceImplPort".equals(portName)) {
            setMoodleEvidenceServiceImplPortEndpointAddress(address);
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
