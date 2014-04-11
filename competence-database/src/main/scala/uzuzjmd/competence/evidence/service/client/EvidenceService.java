/**
 * EvidenceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package uzuzjmd.competence.evidence.service.client;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;

public interface EvidenceService extends java.rmi.Remote {
	public MoodleEvidence[] getMoodleEvidences(java.lang.String course,
			java.lang.String user) throws java.rmi.RemoteException;

	public Evidence[] getEvidences(java.lang.String user)
			throws java.rmi.RemoteException;
}
