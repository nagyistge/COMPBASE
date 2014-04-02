package uzuzjmd.competence.service.soap.client;

public class CompetenceServiceProxy implements uzuzjmd.competence.service.soap.client.CompetenceService {
  private String _endpoint = null;
  private uzuzjmd.competence.service.soap.client.CompetenceService competenceService = null;
  
  public CompetenceServiceProxy() {
    _initCompetenceServiceProxy();
  }
  
  public CompetenceServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCompetenceServiceProxy();
  }
  
  private void _initCompetenceServiceProxy() {
    try {
      competenceService = (new uzuzjmd.competence.service.soap.client.CompetenceServiceImplServiceLocator()).getCompetenceServiceImplPort();
      if (competenceService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)competenceService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)competenceService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (competenceService != null)
      ((javax.xml.rpc.Stub)competenceService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public uzuzjmd.competence.service.soap.client.CompetenceService getCompetenceService() {
    if (competenceService == null)
      _initCompetenceServiceProxy();
    return competenceService;
  }
  
  public uzuzjmd.competence.service.soap.client.GetCompetencesResponseReturn[] getCompetences() throws java.rmi.RemoteException{
    if (competenceService == null)
      _initCompetenceServiceProxy();
    return competenceService.getCompetences();
  }
  
  public void insertCompetence(uzuzjmd.competence.service.soap.client.InsertCompetenceArg0 arg0) throws java.rmi.RemoteException{
    if (competenceService == null)
      _initCompetenceServiceProxy();
    competenceService.insertCompetence(arg0);
  }
  
  
}