package uzuzjmd.service.competence.client;

public class CompetenceServiceProxy implements uzuzjmd.service.competence.client.CompetenceService {
  private String _endpoint = null;
  private uzuzjmd.service.competence.client.CompetenceService competenceService = null;
  
  public CompetenceServiceProxy() {
    _initCompetenceServiceProxy();
  }
  
  public CompetenceServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCompetenceServiceProxy();
  }
  
  private void _initCompetenceServiceProxy() {
    try {
      competenceService = (new uzuzjmd.service.competence.client.CompetenceServiceImplServiceLocator()).getCompetenceServiceImplPort();
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
  
  public uzuzjmd.service.competence.client.CompetenceService getCompetenceService() {
    if (competenceService == null)
      _initCompetenceServiceProxy();
    return competenceService;
  }
  
  public uzuzjmd.service.competence.client.GetCompetencesResponseReturn[] getCompetences() throws java.rmi.RemoteException{
    if (competenceService == null)
      _initCompetenceServiceProxy();
    return competenceService.getCompetences();
  }
  
  public void insertCompetence(uzuzjmd.service.competence.client.InsertCompetenceArg0 arg0) throws java.rmi.RemoteException{
    if (competenceService == null)
      _initCompetenceServiceProxy();
    competenceService.insertCompetence(arg0);
  }
  
  
}