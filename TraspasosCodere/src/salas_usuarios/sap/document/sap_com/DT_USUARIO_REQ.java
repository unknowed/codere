/**
 * DT_USUARIO_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package salas_usuarios.sap.document.sap_com;

public class DT_USUARIO_REQ  implements java.io.Serializable {
    private java.lang.String i_USUARIO;

    public DT_USUARIO_REQ() {
    }

    public DT_USUARIO_REQ(
           java.lang.String i_USUARIO) {
           this.i_USUARIO = i_USUARIO;
    }


    /**
     * Gets the i_USUARIO value for this DT_USUARIO_REQ.
     * 
     * @return i_USUARIO
     */
    public java.lang.String getI_USUARIO() {
        return i_USUARIO;
    }


    /**
     * Sets the i_USUARIO value for this DT_USUARIO_REQ.
     * 
     * @param i_USUARIO
     */
    public void setI_USUARIO(java.lang.String i_USUARIO) {
        this.i_USUARIO = i_USUARIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_USUARIO_REQ)) return false;
        DT_USUARIO_REQ other = (DT_USUARIO_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.i_USUARIO==null && other.getI_USUARIO()==null) || 
             (this.i_USUARIO!=null &&
              this.i_USUARIO.equals(other.getI_USUARIO())));
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
        if (getI_USUARIO() != null) {
            _hashCode += getI_USUARIO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_USUARIO_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:salas_usuarios", "DT_USUARIO_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_USUARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_USUARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
