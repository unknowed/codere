package salas_usuarios.sap.document.sap_com;

public class SI_USUARIOSALA_SOProxy implements salas_usuarios.sap.document.sap_com.SI_USUARIOSALA_SO {
  private String _endpoint = null;
  private salas_usuarios.sap.document.sap_com.SI_USUARIOSALA_SO sI_USUARIOSALA_SO = null;
  
  public SI_USUARIOSALA_SOProxy() {
    _initSI_USUARIOSALA_SOProxy();
  }
  
  public SI_USUARIOSALA_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_USUARIOSALA_SOProxy();
  }
  
  private void _initSI_USUARIOSALA_SOProxy() {
    try {
      sI_USUARIOSALA_SO = (new salas_usuarios.sap.document.sap_com.BC_APPTRASP_SI_USUARIOSALA_SOLocator()).getHTTPS_Port();
      if (sI_USUARIOSALA_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_USUARIOSALA_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_USUARIOSALA_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_USUARIOSALA_SO != null)
      ((javax.xml.rpc.Stub)sI_USUARIOSALA_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public salas_usuarios.sap.document.sap_com.SI_USUARIOSALA_SO getSI_USUARIOSALA_SO() {
    if (sI_USUARIOSALA_SO == null)
      _initSI_USUARIOSALA_SOProxy();
    return sI_USUARIOSALA_SO;
  }
  
  public salas_usuarios.sap.document.sap_com.DT_USUARIO_RES SI_USUARIOSALA_SO(salas_usuarios.sap.document.sap_com.DT_USUARIO_REQ MT_USUARIO_REQ) throws java.rmi.RemoteException, solpeds.sap.document.sap_com.MT_FAULT{
    if (sI_USUARIOSALA_SO == null)
      _initSI_USUARIOSALA_SOProxy();
    return sI_USUARIOSALA_SO.SI_USUARIOSALA_SO(MT_USUARIO_REQ);
  }
  
  
}