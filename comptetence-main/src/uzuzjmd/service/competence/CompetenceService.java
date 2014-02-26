package uzuzjmd.service.competence;

import java.rmi.RemoteException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uzuzjmd.rcd.generated.Rdceo;

@WebService(name = "CompetenceService", targetNamespace = "http://competence.service.uzuzjmd/")
public interface CompetenceService {
	@WebMethod(operationName = "getCompetences", action = "urn:GetCompetences")
	public Rdceo[] getCompetences() throws RemoteException;
	@WebMethod(operationName = "insertCompetence", action = "urn:InsertCompetence")
	public void insertCompetence(@WebParam(name = "arg0") Rdceo rcdeo);
}
