/**
 * DT_ENTRADAS_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entrada_cendest.sap.document.sap_com;

public class DT_ENTRADAS_REQ  implements java.io.Serializable {
    private entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem[] i_ENTRADA;

    private java.lang.String i_TEXT;

    public DT_ENTRADAS_REQ() {
    }

    public DT_ENTRADAS_REQ(
           entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem[] i_ENTRADA,
           java.lang.String i_TEXT) {
           this.i_ENTRADA = i_ENTRADA;
           this.i_TEXT = i_TEXT;
    }


    /**
     * Gets the i_ENTRADA value for this DT_ENTRADAS_REQ.
     * 
     * @return i_ENTRADA
     */
    public entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem[] getI_ENTRADA() {
        return i_ENTRADA;
    }


    /**
     * Sets the i_ENTRADA value for this DT_ENTRADAS_REQ.
     * 
     * @param i_ENTRADA
     */
    public void setI_ENTRADA(entrada_cendest.sap.document.sap_com.DT_ENTRADAS_REQI_ENTRADAItem[] i_ENTRADA) {
        this.i_ENTRADA = i_ENTRADA;
    }


    /**
     * Gets the i_TEXT value for this DT_ENTRADAS_REQ.
     * 
     * @return i_TEXT
     */
    public java.lang.String getI_TEXT() {
        return i_TEXT;
    }


    /**
     * Sets the i_TEXT value for this DT_ENTRADAS_REQ.
     * 
     * @param i_TEXT
     */
    public void setI_TEXT(java.lang.String i_TEXT) {
        this.i_TEXT = i_TEXT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ENTRADAS_REQ)) return false;
        DT_ENTRADAS_REQ other = (DT_ENTRADAS_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.i_ENTRADA==null && other.getI_ENTRADA()==null) || 
             (this.i_ENTRADA!=null &&
              java.util.Arrays.equals(this.i_ENTRADA, other.getI_ENTRADA()))) &&
            ((this.i_TEXT==null && other.getI_TEXT()==null) || 
             (this.i_TEXT!=null &&
              this.i_TEXT.equals(other.getI_TEXT())));
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
        if (getI_ENTRADA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getI_ENTRADA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getI_ENTRADA(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getI_TEXT() != null) {
            _hashCode += getI_TEXT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ENTRADAS_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:entrada_cendest", "DT_ENTRADAS_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_ENTRADA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_ENTRADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:entrada_cendest", ">>DT_ENTRADAS_REQ>I_ENTRADA>item"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
