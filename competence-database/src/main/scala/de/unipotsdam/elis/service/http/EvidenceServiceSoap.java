/**
 * EvidenceServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.unipotsdam.elis.service.http;

public interface EvidenceServiceSoap extends java.rmi.Remote {
    public de.unipotsdam.elis.model.EvidenceSoap[] getGroupEvidences(long groupId) throws java.rmi.RemoteException;
    public void helloWorld() throws java.rmi.RemoteException;
}
