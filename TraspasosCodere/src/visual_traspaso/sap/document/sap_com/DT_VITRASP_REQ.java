/**
 * DT_VITRASP_REQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visual_traspaso.sap.document.sap_com;

public class DT_VITRASP_REQ  implements java.io.Serializable {
    private java.lang.String TYBUS;

    private java.lang.String TYCON;

    private java.lang.String WERKS;

    private java.lang.String LGORT;

    private java.lang.String MATNRL;

    private java.lang.String MATNRH;

    private java.lang.String DOCMATL;

    private java.lang.String DOCMATH;

    public DT_VITRASP_REQ() {
    }

    public DT_VITRASP_REQ(
           java.lang.String TYBUS,
           java.lang.String TYCON,
           java.lang.String WERKS,
           java.lang.String LGORT,
           java.lang.String MATNRL,
           java.lang.String MATNRH,
           java.lang.String DOCMATL,
           java.lang.String DOCMATH) {
           this.TYBUS = TYBUS;
           this.TYCON = TYCON;
           this.WERKS = WERKS;
           this.LGORT = LGORT;
           this.MATNRL = MATNRL;
           this.MATNRH = MATNRH;
           this.DOCMATL = DOCMATL;
           this.DOCMATH = DOCMATH;
    }


    /**
     * Gets the TYBUS value for this DT_VITRASP_REQ.
     * 
     * @return TYBUS
     */
    public java.lang.String getTYBUS() {
        return TYBUS;
    }


    /**
     * Sets the TYBUS value for this DT_VITRASP_REQ.
     * 
     * @param TYBUS
     */
    public void setTYBUS(java.lang.String TYBUS) {
        this.TYBUS = TYBUS;
    }


    /**
     * Gets the TYCON value for this DT_VITRASP_REQ.
     * 
     * @return TYCON
     */
    public java.lang.String getTYCON() {
        return TYCON;
    }


    /**
     * Sets the TYCON value for this DT_VITRASP_REQ.
     * 
     * @param TYCON
     */
    public void setTYCON(java.lang.String TYCON) {
        this.TYCON = TYCON;
    }


    /**
     * Gets the WERKS value for this DT_VITRASP_REQ.
     * 
     * @return WERKS
     */
    public java.lang.String getWERKS() {
        return WERKS;
    }


    /**
     * Sets the WERKS value for this DT_VITRASP_REQ.
     * 
     * @param WERKS
     */
    public void setWERKS(java.lang.String WERKS) {
        this.WERKS = WERKS;
    }


    /**
     * Gets the LGORT value for this DT_VITRASP_REQ.
     * 
     * @return LGORT
     */
    public java.lang.String getLGORT() {
        return LGORT;
    }


    /**
     * Sets the LGORT value for this DT_VITRASP_REQ.
     * 
     * @param LGORT
     */
    public void setLGORT(java.lang.String LGORT) {
        this.LGORT = LGORT;
    }


    /**
     * Gets the MATNRL value for this DT_VITRASP_REQ.
     * 
     * @return MATNRL
     */
    public java.lang.String getMATNRL() {
        return MATNRL;
    }


    /**
     * Sets the MATNRL value for this DT_VITRASP_REQ.
     * 
     * @param MATNRL
     */
    public void setMATNRL(java.lang.String MATNRL) {
        this.MATNRL = MATNRL;
    }


    /**
     * Gets the MATNRH value for this DT_VITRASP_REQ.
     * 
     * @return MATNRH
     */
    public java.lang.String getMATNRH() {
        return MATNRH;
    }


    /**
     * Sets the MATNRH value for this DT_VITRASP_REQ.
     * 
     * @param MATNRH
     */
    public void setMATNRH(java.lang.String MATNRH) {
        this.MATNRH = MATNRH;
    }


    /**
     * Gets the DOCMATL value for this DT_VITRASP_REQ.
     * 
     * @return DOCMATL
     */
    public java.lang.String getDOCMATL() {
        return DOCMATL;
    }


    /**
     * Sets the DOCMATL value for this DT_VITRASP_REQ.
     * 
     * @param DOCMATL
     */
    public void setDOCMATL(java.lang.String DOCMATL) {
        this.DOCMATL = DOCMATL;
    }


    /**
     * Gets the DOCMATH value for this DT_VITRASP_REQ.
     * 
     * @return DOCMATH
     */
    public java.lang.String getDOCMATH() {
        return DOCMATH;
    }


    /**
     * Sets the DOCMATH value for this DT_VITRASP_REQ.
     * 
     * @param DOCMATH
     */
    public void setDOCMATH(java.lang.String DOCMATH) {
        this.DOCMATH = DOCMATH;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_VITRASP_REQ)) return false;
        DT_VITRASP_REQ other = (DT_VITRASP_REQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TYBUS==null && other.getTYBUS()==null) || 
             (this.TYBUS!=null &&
              this.TYBUS.equals(other.getTYBUS()))) &&
            ((this.TYCON==null && other.getTYCON()==null) || 
             (this.TYCON!=null &&
              this.TYCON.equals(other.getTYCON()))) &&
            ((this.WERKS==null && other.getWERKS()==null) || 
             (this.WERKS!=null &&
              this.WERKS.equals(other.getWERKS()))) &&
            ((this.LGORT==null && other.getLGORT()==null) || 
             (this.LGORT!=null &&
              this.LGORT.equals(other.getLGORT()))) &&
            ((this.MATNRL==null && other.getMATNRL()==null) || 
             (this.MATNRL!=null &&
              this.MATNRL.equals(other.getMATNRL()))) &&
            ((this.MATNRH==null && other.getMATNRH()==null) || 
             (this.MATNRH!=null &&
              this.MATNRH.equals(other.getMATNRH()))) &&
            ((this.DOCMATL==null && other.getDOCMATL()==null) || 
             (this.DOCMATL!=null &&
              this.DOCMATL.equals(other.getDOCMATL()))) &&
            ((this.DOCMATH==null && other.getDOCMATH()==null) || 
             (this.DOCMATH!=null &&
              this.DOCMATH.equals(other.getDOCMATH())));
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
        if (getTYBUS() != null) {
            _hashCode += getTYBUS().hashCode();
        }
        if (getTYCON() != null) {
            _hashCode += getTYCON().hashCode();
        }
        if (getWERKS() != null) {
            _hashCode += getWERKS().hashCode();
        }
        if (getLGORT() != null) {
            _hashCode += getLGORT().hashCode();
        }
        if (getMATNRL() != null) {
            _hashCode += getMATNRL().hashCode();
        }
        if (getMATNRH() != null) {
            _hashCode += getMATNRH().hashCode();
        }
        if (getDOCMATL() != null) {
            _hashCode += getDOCMATL().hashCode();
        }
        if (getDOCMATH() != null) {
            _hashCode += getDOCMATH().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_VITRASP_REQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:visual_traspaso", "DT_VITRASP_REQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TYBUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TYBUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TYCON");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TYCON"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WERKS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WERKS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LGORT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LGORT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATNRL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MATNRL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATNRH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MATNRH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCMATL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCMATL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCMATH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCMATH"));
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
