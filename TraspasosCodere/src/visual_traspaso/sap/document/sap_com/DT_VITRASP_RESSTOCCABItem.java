/**
 * DT_VITRASP_RESSTOCCABItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visual_traspaso.sap.document.sap_com;

public class DT_VITRASP_RESSTOCCABItem  implements java.io.Serializable {
    private java.lang.String MBLNR;

    private java.lang.String EINDT;

    public DT_VITRASP_RESSTOCCABItem() {
    }

    public DT_VITRASP_RESSTOCCABItem(
           java.lang.String MBLNR,
           java.lang.String EINDT) {
           this.MBLNR = MBLNR;
           this.EINDT = EINDT;
    }


    /**
     * Gets the MBLNR value for this DT_VITRASP_RESSTOCCABItem.
     * 
     * @return MBLNR
     */
    public java.lang.String getMBLNR() {
        return MBLNR;
    }


    /**
     * Sets the MBLNR value for this DT_VITRASP_RESSTOCCABItem.
     * 
     * @param MBLNR
     */
    public void setMBLNR(java.lang.String MBLNR) {
        this.MBLNR = MBLNR;
    }


    /**
     * Gets the EINDT value for this DT_VITRASP_RESSTOCCABItem.
     * 
     * @return EINDT
     */
    public java.lang.String getEINDT() {
        return EINDT;
    }


    /**
     * Sets the EINDT value for this DT_VITRASP_RESSTOCCABItem.
     * 
     * @param EINDT
     */
    public void setEINDT(java.lang.String EINDT) {
        this.EINDT = EINDT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_VITRASP_RESSTOCCABItem)) return false;
        DT_VITRASP_RESSTOCCABItem other = (DT_VITRASP_RESSTOCCABItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MBLNR==null && other.getMBLNR()==null) || 
             (this.MBLNR!=null &&
              this.MBLNR.equals(other.getMBLNR()))) &&
            ((this.EINDT==null && other.getEINDT()==null) || 
             (this.EINDT!=null &&
              this.EINDT.equals(other.getEINDT())));
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
        if (getMBLNR() != null) {
            _hashCode += getMBLNR().hashCode();
        }
        if (getEINDT() != null) {
            _hashCode += getEINDT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_VITRASP_RESSTOCCABItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:visual_traspaso", ">>DT_VITRASP_RES>STOCCAB>item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MBLNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MBLNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EINDT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EINDT"));
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
