/**
 * EvidenceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.evidence.service.client;

public interface EvidenceService extends java.rmi.Remote {
	public uzuzjmd.competence.evidence.service.client.MoodleEvidence[] getMoodleEvidences(
			java.lang.String course, java.lang.String user)
			throws java.rmi.RemoteException;

	public uzuzjmd.competence.evidence.service.client.Evidence[] getEvidences(
			java.lang.String user) throws java.rmi.RemoteException;
}
