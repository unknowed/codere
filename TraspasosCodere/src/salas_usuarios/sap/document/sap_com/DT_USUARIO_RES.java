/**
 * DT_USUARIO_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package salas_usuarios.sap.document.sap_com;

public class DT_USUARIO_RES  implements java.io.Serializable {
    private salas_usuarios.sap.document.sap_com.DT_USUARIO_RESRETURNItem[] RETURN;

    private salas_usuarios.sap.document.sap_com.DT_USUARIO_RESSALASItem[] SALAS;

    public DT_USUARIO_RES() {
    }

    public DT_USUARIO_RES(
           salas_usuarios.sap.document.sap_com.DT_USUARIO_RESRETURNItem[] RETURN,
           salas_usuarios.sap.document.sap_com.DT_USUARIO_RESSALASItem[] SALAS) {
           this.RETURN = RETURN;
           this.SALAS = SALAS;
    }


    /**
     * Gets the RETURN value for this DT_USUARIO_RES.
     * 
     * @return RETURN
     */
    public salas_usuarios.sap.document.sap_com.DT_USUARIO_RESRETURNItem[] getRETURN() {
        return RETURN;
    }


    /**
     * Sets the RETURN value for this DT_USUARIO_RES.
     * 
     * @param RETURN
     */
    public void setRETURN(salas_usuarios.sap.document.sap_com.DT_USUARIO_RESRETURNItem[] RETURN) {
        this.RETURN = RETURN;
    }


    /**
     * Gets the SALAS value for this DT_USUARIO_RES.
     * 
     * @return SALAS
     */
    public salas_usuarios.sap.document.sap_com.DT_USUARIO_RESSALASItem[] getSALAS() {
        return SALAS;
    }


    /**
     * Sets the SALAS value for this DT_USUARIO_RES.
     * 
     * @param SALAS
     */
    public void setSALAS(salas_usuarios.sap.document.sap_com.DT_USUARIO_RESSALASItem[] SALAS) {
        this.SALAS = SALAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_USUARIO_RES)) return false;
        DT_USUARIO_RES other = (DT_USUARIO_RES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RETURN==null && other.getRETURN()==null) || 
             (this.RETURN!=null &&
              java.util.Arrays.equals(this.RETURN, other.getRETURN()))) &&
            ((this.SALAS==null && other.getSALAS()==null) || 
             (this.SALAS!=null &&
              java.util.Arrays.equals(this.SALAS, other.getSALAS())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRETURN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRETURN());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRETURN(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSALAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSALAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSALAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_USUARIO_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:salas_usuarios", "DT_USUARIO_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RETURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:salas_usuarios", ">>DT_USUARIO_RES>RETURN>item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SALAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:salas_usuarios", ">>DT_USUARIO_RES>SALAS>item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
