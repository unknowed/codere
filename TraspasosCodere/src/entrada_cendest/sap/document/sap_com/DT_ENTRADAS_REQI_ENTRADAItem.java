/**
 * DT_ENTRADAS_REQI_ENTRADAItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entrada_cendest.sap.document.sap_com;

public class DT_ENTRADAS_REQI_ENTRADAItem  implements java.io.Serializable {
    private java.lang.String MBLNR;

    private java.lang.String EBELN;

    private java.lang.String EBELP;

    private java.lang.String MATNR;

    private java.lang.String ZMENG;

    public DT_ENTRADAS_REQI_ENTRADAItem() {
    }

    public DT_ENTRADAS_REQI_ENTRADAItem(
           java.lang.String MBLNR,
           java.lang.String EBELN,
           java.lang.String EBELP,
           java.lang.String MATNR,
           java.lang.String ZMENG) {
           this.MBLNR = MBLNR;
           this.EBELN = EBELN;
           this.EBELP = EBELP;
           this.MATNR = MATNR;
           this.ZMENG = ZMENG;
    }


    /**
     * Gets the MBLNR value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @return MBLNR
     */
    public java.lang.String getMBLNR() {
        return MBLNR;
    }


    /**
     * Sets the MBLNR value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @param MBLNR
     */
    public void setMBLNR(java.lang.String MBLNR) {
        this.MBLNR = MBLNR;
    }


    /**
     * Gets the EBELN value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @return EBELN
     */
    public java.lang.String getEBELN() {
        return EBELN;
    }


    /**
     * Sets the EBELN value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @param EBELN
     */
    public void setEBELN(java.lang.String EBELN) {
        this.EBELN = EBELN;
    }


    /**
     * Gets the EBELP value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @return EBELP
     */
    public java.lang.String getEBELP() {
        return EBELP;
    }


    /**
     * Sets the EBELP value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @param EBELP
     */
    public void setEBELP(java.lang.String EBELP) {
        this.EBELP = EBELP;
    }


    /**
     * Gets the MATNR value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @return MATNR
     */
    public java.lang.String getMATNR() {
        return MATNR;
    }


    /**
     * Sets the MATNR value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @param MATNR
     */
    public void setMATNR(java.lang.String MATNR) {
        this.MATNR = MATNR;
    }


    /**
     * Gets the ZMENG value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @return ZMENG
     */
    public java.lang.String getZMENG() {
        return ZMENG;
    }


    /**
     * Sets the ZMENG value for this DT_ENTRADAS_REQI_ENTRADAItem.
     * 
     * @param ZMENG
     */
    public void setZMENG(java.lang.String ZMENG) {
        this.ZMENG = ZMENG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ENTRADAS_REQI_ENTRADAItem)) return false;
        DT_ENTRADAS_REQI_ENTRADAItem other = (DT_ENTRADAS_REQI_ENTRADAItem) obj;
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
            ((this.EBELN==null && other.getEBELN()==null) || 
             (this.EBELN!=null &&
              this.EBELN.equals(other.getEBELN()))) &&
            ((this.EBELP==null && other.getEBELP()==null) || 
             (this.EBELP!=null &&
              this.EBELP.equals(other.getEBELP()))) &&
            ((this.MATNR==null && other.getMATNR()==null) || 
             (this.MATNR!=null &&
              this.MATNR.equals(other.getMATNR()))) &&
            ((this.ZMENG==null && other.getZMENG()==null) || 
             (this.ZMENG!=null &&
              this.ZMENG.equals(other.getZMENG())));
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
        if (getEBELN() != null) {
            _hashCode += getEBELN().hashCode();
        }
        if (getEBELP() != null) {
            _hashCode += getEBELP().hashCode();
        }
        if (getMATNR() != null) {
            _hashCode += getMATNR().hashCode();
        }
        if (getZMENG() != null) {
            _hashCode += getZMENG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ENTRADAS_REQI_ENTRADAItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:entrada_cendest", ">>DT_ENTRADAS_REQ>I_ENTRADA>item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MBLNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MBLNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EBELN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EBELN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EBELP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EBELP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MATNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZMENG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZMENG"));
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
