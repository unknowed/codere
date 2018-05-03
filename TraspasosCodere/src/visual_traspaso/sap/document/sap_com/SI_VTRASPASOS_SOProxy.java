package visual_traspaso.sap.document.sap_com;

public class SI_VTRASPASOS_SOProxy implements visual_traspaso.sap.document.sap_com.SI_VTRASPASOS_SO {
  private String _endpoint = null;
  private visual_traspaso.sap.document.sap_com.SI_VTRASPASOS_SO sI_VTRASPASOS_SO = null;
  
  public SI_VTRASPASOS_SOProxy() {
    _initSI_VTRASPASOS_SOProxy();
  }
  
  public SI_VTRASPASOS_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_VTRASPASOS_SOProxy();
  }
  
  private void _initSI_VTRASPASOS_SOProxy() {
    try {
      sI_VTRASPASOS_SO = (new visual_traspaso.sap.document.sap_com.BC_APPTRASP_SI_VTRASPASOS_SOLocator()).getHTTPS_Port();
      if (sI_VTRASPASOS_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_VTRASPASOS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_VTRASPASOS_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_VTRASPASOS_SO != null)
      ((javax.xml.rpc.Stub)sI_VTRASPASOS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public visual_traspaso.sap.document.sap_com.SI_VTRASPASOS_SO getSI_VTRASPASOS_SO() {
    if (sI_VTRASPASOS_SO == null)
      _initSI_VTRASPASOS_SOProxy();
    return sI_VTRASPASOS_SO;
  }
  
  public visual_traspaso.sap.document.sap_com.DT_VITRASP_RES SI_VTRASPASOS_SO(visual_traspaso.sap.document.sap_com.DT_VITRASP_REQ MT_VITRASP_REQ) throws java.rmi.RemoteException, solpeds.sap.document.sap_com.MT_FAULT{
    if (sI_VTRASPASOS_SO == null)
      _initSI_VTRASPASOS_SOProxy();
    return sI_VTRASPASOS_SO.SI_VTRASPASOS_SO(MT_VITRASP_REQ);
  }
  
  
}