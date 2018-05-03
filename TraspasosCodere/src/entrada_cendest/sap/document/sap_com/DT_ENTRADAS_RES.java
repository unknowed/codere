/**
 * DT_ENTRADAS_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entrada_cendest.sap.document.sap_com;

public class DT_ENTRADAS_RES  implements java.io.Serializable {
    private entrada_cendest.sap.document.sap_com.DT_ENTRADAS_RESRETURNItem[] RETURN;

    public DT_ENTRADAS_RES() {
    }

    public DT_ENTRADAS_RES(
           entrada_cendest.sap.document.sap_com.DT_ENTRADAS_RESRETURNItem[] RETURN) {
           this.RETURN = RETURN;
    }


    /**
     * Gets the RETURN value for this DT_ENTRADAS_RES.
     * 
     * @return RETURN
     */
    public entrada_cendest.sap.document.sap_com.DT_ENTRADAS_RESRETURNItem[] getRETURN() {
        return RETURN;
    }


    /**
     * Sets the RETURN value for this DT_ENTRADAS_RES.
     * 
     * @param RETURN
     */
    public void setRETURN(entrada_cendest.sap.document.sap_com.DT_ENTRADAS_RESRETURNItem[] RETURN) {
        this.RETURN = RETURN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ENTRADAS_RES)) return false;
        DT_ENTRADAS_RES other = (DT_ENTRADAS_RES) obj;
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
              java.util.Arrays.equals(this.RETURN, other.getRETURN())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ENTRADAS_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:entrada_cendest", "DT_ENTRADAS_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RETURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:entrada_cendest", ">>DT_ENTRADAS_RES>RETURN>item"));
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
