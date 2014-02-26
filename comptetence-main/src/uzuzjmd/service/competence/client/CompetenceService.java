/**
 * CompetenceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.service.competence.client;

public interface CompetenceService extends java.rmi.Remote {
    public uzuzjmd.service.competence.client.GetCompetencesResponseReturn[] getCompetences() throws java.rmi.RemoteException;
    public void insertCompetence(uzuzjmd.service.competence.client.InsertCompetenceArg0 arg0) throws java.rmi.RemoteException;
}
