/**
 * DT_VITRASP_RES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visual_traspaso.sap.document.sap_com;

public class DT_VITRASP_RES  implements java.io.Serializable {
    private visual_traspaso.sap.document.sap_com.DT_VITRASP_RESRETURNItem[] RETURN;

    private visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCCABItem[] STOCCAB;

    private visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCDETItem[] STOCDET;

    public DT_VITRASP_RES() {
    }

    public DT_VITRASP_RES(
           visual_traspaso.sap.document.sap_com.DT_VITRASP_RESRETURNItem[] RETURN,
           visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCCABItem[] STOCCAB,
           visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCDETItem[] STOCDET) {
           this.RETURN = RETURN;
           this.STOCCAB = STOCCAB;
           this.STOCDET = STOCDET;
    }


    /**
     * Gets the RETURN value for this DT_VITRASP_RES.
     * 
     * @return RETURN
     */
    public visual_traspaso.sap.document.sap_com.DT_VITRASP_RESRETURNItem[] getRETURN() {
        return RETURN;
    }


    /**
     * Sets the RETURN value for this DT_VITRASP_RES.
     * 
     * @param RETURN
     */
    public void setRETURN(visual_traspaso.sap.document.sap_com.DT_VITRASP_RESRETURNItem[] RETURN) {
        this.RETURN = RETURN;
    }


    /**
     * Gets the STOCCAB value for this DT_VITRASP_RES.
     * 
     * @return STOCCAB
     */
    public visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCCABItem[] getSTOCCAB() {
        return STOCCAB;
    }


    /**
     * Sets the STOCCAB value for this DT_VITRASP_RES.
     * 
     * @param STOCCAB
     */
    public void setSTOCCAB(visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCCABItem[] STOCCAB) {
        this.STOCCAB = STOCCAB;
    }


    /**
     * Gets the STOCDET value for this DT_VITRASP_RES.
     * 
     * @return STOCDET
     */
    public visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCDETItem[] getSTOCDET() {
        return STOCDET;
    }


    /**
     * Sets the STOCDET value for this DT_VITRASP_RES.
     * 
     * @param STOCDET
     */
    public void setSTOCDET(visual_traspaso.sap.document.sap_com.DT_VITRASP_RESSTOCDETItem[] STOCDET) {
        this.STOCDET = STOCDET;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_VITRASP_RES)) return false;
        DT_VITRASP_RES other = (DT_VITRASP_RES) obj;
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
            ((this.STOCCAB==null && other.getSTOCCAB()==null) || 
             (this.STOCCAB!=null &&
              java.util.Arrays.equals(this.STOCCAB, other.getSTOCCAB()))) &&
            ((this.STOCDET==null && other.getSTOCDET()==null) || 
             (this.STOCDET!=null &&
              java.util.Arrays.equals(this.STOCDET, other.getSTOCDET())));
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
        if (getSTOCCAB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSTOCCAB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSTOCCAB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSTOCDET() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSTOCDET());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSTOCDET(), i);
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
        new org.apache.axis.description.TypeDesc(DT_VITRASP_RES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:visual_traspaso", "DT_VITRASP_RES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RETURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:visual_traspaso", ">>DT_VITRASP_RES>RETURN>item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STOCCAB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STOCCAB"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:visual_traspaso", ">>DT_VITRASP_RES>STOCCAB>item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STOCDET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STOCDET"));
        elemField.setXmlType(new javax.xml.namespace.QName("unr:sap-com:document:sap:visual_traspaso", ">>DT_VITRASP_RES>STOCDET>item"));
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
