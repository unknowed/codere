package entrada_cendest.sap.document.sap_com;

public class SI_ENTRADAS_SOProxy implements entrada_cendest.sap.document.sap_com.SI_ENTRADAS_SO {
  private String _endpoint = null;
  private entrada_cendest.sap.document.sap_com.SI_ENTRADAS_SO sI_ENTRADAS_SO = null;
  
  public SI_ENTRADAS_SOProxy() {
    _initSI_ENTRADAS_SOProxy();
  }
  
  public SI_ENTRADAS_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_ENTRADAS_SOProxy();
  }
  
  private void _initSI_ENTRADAS_SOProxy() {
    try {
      sI_ENTRADAS_SO = (new entrada_cendest.sap.document.sap_com.BC_APPTRASP_SI_ENTRADAS_SOLocator()).getHTTPS_Port();
      if (sI_ENTRADAS_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_ENTRADAS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_ENTRADAS_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_ENTRADAS_SO != null)
      ((javax.xml.rpc.Stub)sI_ENTRADAS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public entrada_cendest.sap.document.sap_com.SI_ENTRADAS_SO getSI_ENTRADAS_SO() {
    if (sI_ENTRADAS_SO == null)
      _initSI_ENTRADAS_SOProxy();
    return sI_ENTRADAS_SO;
  }
  
  public entrada_cendest.sap.document.sap_com.DT_ENTRADAS_RES SI_ENTRADAS_SO(entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQ MT_ENTRADAS_REQ) throws java.rmi.RemoteException, solpeds.sap.document.sap_com.MT_FAULT{
    if (sI_ENTRADAS_SO == null)
      _initSI_ENTRADAS_SOProxy();
    return sI_ENTRADAS_SO.SI_ENTRADAS_SO(MT_ENTRADAS_REQ);
  }
  
  
}