package de.unipotsdam.elis.service.http;

public class EvidenceServiceSoapProxy implements de.unipotsdam.elis.service.http.EvidenceServiceSoap {
  private String _endpoint = null;
  private de.unipotsdam.elis.service.http.EvidenceServiceSoap evidenceServiceSoap = null;
  
  public EvidenceServiceSoapProxy() {
    _initEvidenceServiceSoapProxy();
  }
  
  public EvidenceServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEvidenceServiceSoapProxy();
  }
  
  private void _initEvidenceServiceSoapProxy() {
    try {
      evidenceServiceSoap = (new de.unipotsdam.elis.service.http.EvidenceServiceSoapServiceLocator()).getPlugin_UPServices_EvidenceService();
      if (evidenceServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)evidenceServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)evidenceServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (evidenceServiceSoap != null)
      ((javax.xml.rpc.Stub)evidenceServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public de.unipotsdam.elis.service.http.EvidenceServiceSoap getEvidenceServiceSoap() {
    if (evidenceServiceSoap == null)
      _initEvidenceServiceSoapProxy();
    return evidenceServiceSoap;
  }
  
  public de.unipotsdam.elis.model.EvidenceSoap[] getGroupEvidences(long groupId) throws java.rmi.RemoteException{
    if (evidenceServiceSoap == null)
      _initEvidenceServiceSoapProxy();
    return evidenceServiceSoap.getGroupEvidences(groupId);
  }
  
  public void helloWorld() throws java.rmi.RemoteException{
    if (evidenceServiceSoap == null)
      _initEvidenceServiceSoapProxy();
    evidenceServiceSoap.helloWorld();
  }
  
  
}