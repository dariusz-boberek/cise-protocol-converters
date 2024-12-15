
package eu.cise.ivef.generated;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy2;
import org.jvnet.jaxb2_commons.lang.CopyTo2;
import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.HashCode2;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Id" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;maxLength value="40"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="SourceName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Source" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *             &lt;enumeration value="3"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Length"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Breadth"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Callsign" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ShipName"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="ObjectType"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *             &lt;enumeration value="3"/&gt;
 *             &lt;enumeration value="4"/&gt;
 *             &lt;enumeration value="5"/&gt;
 *             &lt;enumeration value="6"/&gt;
 *             &lt;enumeration value="7"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="ShipType"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="20"/&gt;
 *             &lt;enumeration value="30"/&gt;
 *             &lt;enumeration value="31"/&gt;
 *             &lt;enumeration value="32"/&gt;
 *             &lt;enumeration value="33"/&gt;
 *             &lt;enumeration value="34"/&gt;
 *             &lt;enumeration value="35"/&gt;
 *             &lt;enumeration value="36"/&gt;
 *             &lt;enumeration value="37"/&gt;
 *             &lt;enumeration value="40"/&gt;
 *             &lt;enumeration value="50"/&gt;
 *             &lt;enumeration value="51"/&gt;
 *             &lt;enumeration value="52"/&gt;
 *             &lt;enumeration value="53"/&gt;
 *             &lt;enumeration value="54"/&gt;
 *             &lt;enumeration value="55"/&gt;
 *             &lt;enumeration value="58"/&gt;
 *             &lt;enumeration value="59"/&gt;
 *             &lt;enumeration value="60"/&gt;
 *             &lt;enumeration value="70"/&gt;
 *             &lt;enumeration value="80"/&gt;
 *             &lt;enumeration value="90"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="IMO" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="MMSI" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="ATONType"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="0"/&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *             &lt;enumeration value="3"/&gt;
 *             &lt;enumeration value="5"/&gt;
 *             &lt;enumeration value="6"/&gt;
 *             &lt;enumeration value="7"/&gt;
 *             &lt;enumeration value="8"/&gt;
 *             &lt;enumeration value="9"/&gt;
 *             &lt;enumeration value="10"/&gt;
 *             &lt;enumeration value="11"/&gt;
 *             &lt;enumeration value="12"/&gt;
 *             &lt;enumeration value="13"/&gt;
 *             &lt;enumeration value="14"/&gt;
 *             &lt;enumeration value="15"/&gt;
 *             &lt;enumeration value="16"/&gt;
 *             &lt;enumeration value="17"/&gt;
 *             &lt;enumeration value="18"/&gt;
 *             &lt;enumeration value="19"/&gt;
 *             &lt;enumeration value="20"/&gt;
 *             &lt;enumeration value="21"/&gt;
 *             &lt;enumeration value="22"/&gt;
 *             &lt;enumeration value="23"/&gt;
 *             &lt;enumeration value="24"/&gt;
 *             &lt;enumeration value="25"/&gt;
 *             &lt;enumeration value="26"/&gt;
 *             &lt;enumeration value="27"/&gt;
 *             &lt;enumeration value="28"/&gt;
 *             &lt;enumeration value="29"/&gt;
 *             &lt;enumeration value="30"/&gt;
 *             &lt;enumeration value="31"/&gt;
 *             &lt;enumeration value="32"/&gt;
 *             &lt;enumeration value="33"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="ATONName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="AntPosDistFromFront" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="AntPosDistFromLeft" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="NatLangShipName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="PortOfRegistry" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="CountryFlag" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MaxAirDraught"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="MaxDraught"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="DeepWaterVesselind"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="yes"/&gt;
 *             &lt;enumeration value="no"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StaticData")
public class StaticData implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlAttribute(name = "Id", required = true)
    protected String id;
    @XmlAttribute(name = "SourceName", required = true)
    protected String sourceName;
    @XmlAttribute(name = "Source", required = true)
    protected BigInteger source;
    @XmlAttribute(name = "Length")
    protected BigDecimal length;
    @XmlAttribute(name = "Breadth")
    protected BigDecimal breadth;
    @XmlAttribute(name = "Callsign")
    protected String callsign;
    @XmlAttribute(name = "ShipName")
    protected String shipName;
    @XmlAttribute(name = "ObjectType")
    protected BigInteger objectType;
    @XmlAttribute(name = "ShipType")
    protected BigInteger shipType;
    @XmlAttribute(name = "IMO")
    protected BigInteger imo;
    @XmlAttribute(name = "MMSI")
    protected BigInteger mmsi;
    @XmlAttribute(name = "ATONType")
    protected BigInteger atonType;
    @XmlAttribute(name = "ATONName")
    protected String atonName;
    @XmlAttribute(name = "AntPosDistFromFront")
    protected BigDecimal antPosDistFromFront;
    @XmlAttribute(name = "AntPosDistFromLeft")
    protected BigDecimal antPosDistFromLeft;
    @XmlAttribute(name = "NatLangShipName")
    protected String natLangShipName;
    @XmlAttribute(name = "PortOfRegistry")
    protected String portOfRegistry;
    @XmlAttribute(name = "CountryFlag")
    protected String countryFlag;
    @XmlAttribute(name = "MaxAirDraught")
    protected BigDecimal maxAirDraught;
    @XmlAttribute(name = "MaxDraught")
    protected BigDecimal maxDraught;
    @XmlAttribute(name = "DeepWaterVesselind")
    protected String deepWaterVesselind;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the sourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * Sets the value of the sourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceName(String value) {
        this.sourceName = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSource(BigInteger value) {
        this.source = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLength(BigDecimal value) {
        this.length = value;
    }

    /**
     * Gets the value of the breadth property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBreadth() {
        return breadth;
    }

    /**
     * Sets the value of the breadth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBreadth(BigDecimal value) {
        this.breadth = value;
    }

    /**
     * Gets the value of the callsign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallsign() {
        return callsign;
    }

    /**
     * Sets the value of the callsign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallsign(String value) {
        this.callsign = value;
    }

    /**
     * Gets the value of the shipName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Sets the value of the shipName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipName(String value) {
        this.shipName = value;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getObjectType() {
        return objectType;
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setObjectType(BigInteger value) {
        this.objectType = value;
    }

    /**
     * Gets the value of the shipType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getShipType() {
        return shipType;
    }

    /**
     * Sets the value of the shipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setShipType(BigInteger value) {
        this.shipType = value;
    }

    /**
     * Gets the value of the imo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIMO() {
        return imo;
    }

    /**
     * Sets the value of the imo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIMO(BigInteger value) {
        this.imo = value;
    }

    /**
     * Gets the value of the mmsi property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMMSI() {
        return mmsi;
    }

    /**
     * Sets the value of the mmsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMMSI(BigInteger value) {
        this.mmsi = value;
    }

    /**
     * Gets the value of the atonType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getATONType() {
        return atonType;
    }

    /**
     * Sets the value of the atonType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setATONType(BigInteger value) {
        this.atonType = value;
    }

    /**
     * Gets the value of the atonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getATONName() {
        return atonName;
    }

    /**
     * Sets the value of the atonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setATONName(String value) {
        this.atonName = value;
    }

    /**
     * Gets the value of the antPosDistFromFront property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAntPosDistFromFront() {
        return antPosDistFromFront;
    }

    /**
     * Sets the value of the antPosDistFromFront property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAntPosDistFromFront(BigDecimal value) {
        this.antPosDistFromFront = value;
    }

    /**
     * Gets the value of the antPosDistFromLeft property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAntPosDistFromLeft() {
        return antPosDistFromLeft;
    }

    /**
     * Sets the value of the antPosDistFromLeft property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAntPosDistFromLeft(BigDecimal value) {
        this.antPosDistFromLeft = value;
    }

    /**
     * Gets the value of the natLangShipName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNatLangShipName() {
        return natLangShipName;
    }

    /**
     * Sets the value of the natLangShipName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNatLangShipName(String value) {
        this.natLangShipName = value;
    }

    /**
     * Gets the value of the portOfRegistry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortOfRegistry() {
        return portOfRegistry;
    }

    /**
     * Sets the value of the portOfRegistry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortOfRegistry(String value) {
        this.portOfRegistry = value;
    }

    /**
     * Gets the value of the countryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryFlag() {
        return countryFlag;
    }

    /**
     * Sets the value of the countryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryFlag(String value) {
        this.countryFlag = value;
    }

    /**
     * Gets the value of the maxAirDraught property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxAirDraught() {
        return maxAirDraught;
    }

    /**
     * Sets the value of the maxAirDraught property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxAirDraught(BigDecimal value) {
        this.maxAirDraught = value;
    }

    /**
     * Gets the value of the maxDraught property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxDraught() {
        return maxDraught;
    }

    /**
     * Sets the value of the maxDraught property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxDraught(BigDecimal value) {
        this.maxDraught = value;
    }

    /**
     * Gets the value of the deepWaterVesselind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeepWaterVesselind() {
        return deepWaterVesselind;
    }

    /**
     * Sets the value of the deepWaterVesselind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeepWaterVesselind(String value) {
        this.deepWaterVesselind = value;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId, (this.id!= null));
        }
        {
            String theSourceName;
            theSourceName = this.getSourceName();
            strategy.appendField(locator, this, "sourceName", buffer, theSourceName, (this.sourceName!= null));
        }
        {
            BigInteger theSource;
            theSource = this.getSource();
            strategy.appendField(locator, this, "source", buffer, theSource, (this.source!= null));
        }
        {
            BigDecimal theLength;
            theLength = this.getLength();
            strategy.appendField(locator, this, "length", buffer, theLength, (this.length!= null));
        }
        {
            BigDecimal theBreadth;
            theBreadth = this.getBreadth();
            strategy.appendField(locator, this, "breadth", buffer, theBreadth, (this.breadth!= null));
        }
        {
            String theCallsign;
            theCallsign = this.getCallsign();
            strategy.appendField(locator, this, "callsign", buffer, theCallsign, (this.callsign!= null));
        }
        {
            String theShipName;
            theShipName = this.getShipName();
            strategy.appendField(locator, this, "shipName", buffer, theShipName, (this.shipName!= null));
        }
        {
            BigInteger theObjectType;
            theObjectType = this.getObjectType();
            strategy.appendField(locator, this, "objectType", buffer, theObjectType, (this.objectType!= null));
        }
        {
            BigInteger theShipType;
            theShipType = this.getShipType();
            strategy.appendField(locator, this, "shipType", buffer, theShipType, (this.shipType!= null));
        }
        {
            BigInteger theIMO;
            theIMO = this.getIMO();
            strategy.appendField(locator, this, "imo", buffer, theIMO, (this.imo!= null));
        }
        {
            BigInteger theMMSI;
            theMMSI = this.getMMSI();
            strategy.appendField(locator, this, "mmsi", buffer, theMMSI, (this.mmsi!= null));
        }
        {
            BigInteger theATONType;
            theATONType = this.getATONType();
            strategy.appendField(locator, this, "atonType", buffer, theATONType, (this.atonType!= null));
        }
        {
            String theATONName;
            theATONName = this.getATONName();
            strategy.appendField(locator, this, "atonName", buffer, theATONName, (this.atonName!= null));
        }
        {
            BigDecimal theAntPosDistFromFront;
            theAntPosDistFromFront = this.getAntPosDistFromFront();
            strategy.appendField(locator, this, "antPosDistFromFront", buffer, theAntPosDistFromFront, (this.antPosDistFromFront!= null));
        }
        {
            BigDecimal theAntPosDistFromLeft;
            theAntPosDistFromLeft = this.getAntPosDistFromLeft();
            strategy.appendField(locator, this, "antPosDistFromLeft", buffer, theAntPosDistFromLeft, (this.antPosDistFromLeft!= null));
        }
        {
            String theNatLangShipName;
            theNatLangShipName = this.getNatLangShipName();
            strategy.appendField(locator, this, "natLangShipName", buffer, theNatLangShipName, (this.natLangShipName!= null));
        }
        {
            String thePortOfRegistry;
            thePortOfRegistry = this.getPortOfRegistry();
            strategy.appendField(locator, this, "portOfRegistry", buffer, thePortOfRegistry, (this.portOfRegistry!= null));
        }
        {
            String theCountryFlag;
            theCountryFlag = this.getCountryFlag();
            strategy.appendField(locator, this, "countryFlag", buffer, theCountryFlag, (this.countryFlag!= null));
        }
        {
            BigDecimal theMaxAirDraught;
            theMaxAirDraught = this.getMaxAirDraught();
            strategy.appendField(locator, this, "maxAirDraught", buffer, theMaxAirDraught, (this.maxAirDraught!= null));
        }
        {
            BigDecimal theMaxDraught;
            theMaxDraught = this.getMaxDraught();
            strategy.appendField(locator, this, "maxDraught", buffer, theMaxDraught, (this.maxDraught!= null));
        }
        {
            String theDeepWaterVesselind;
            theDeepWaterVesselind = this.getDeepWaterVesselind();
            strategy.appendField(locator, this, "deepWaterVesselind", buffer, theDeepWaterVesselind, (this.deepWaterVesselind!= null));
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final StaticData that = ((StaticData) object);
        {
            String lhsId;
            lhsId = this.getId();
            String rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId, (this.id!= null), (that.id!= null))) {
                return false;
            }
        }
        {
            String lhsSourceName;
            lhsSourceName = this.getSourceName();
            String rhsSourceName;
            rhsSourceName = that.getSourceName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceName", lhsSourceName), LocatorUtils.property(thatLocator, "sourceName", rhsSourceName), lhsSourceName, rhsSourceName, (this.sourceName!= null), (that.sourceName!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsSource;
            lhsSource = this.getSource();
            BigInteger rhsSource;
            rhsSource = that.getSource();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "source", lhsSource), LocatorUtils.property(thatLocator, "source", rhsSource), lhsSource, rhsSource, (this.source!= null), (that.source!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsLength;
            lhsLength = this.getLength();
            BigDecimal rhsLength;
            rhsLength = that.getLength();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "length", lhsLength), LocatorUtils.property(thatLocator, "length", rhsLength), lhsLength, rhsLength, (this.length!= null), (that.length!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsBreadth;
            lhsBreadth = this.getBreadth();
            BigDecimal rhsBreadth;
            rhsBreadth = that.getBreadth();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "breadth", lhsBreadth), LocatorUtils.property(thatLocator, "breadth", rhsBreadth), lhsBreadth, rhsBreadth, (this.breadth!= null), (that.breadth!= null))) {
                return false;
            }
        }
        {
            String lhsCallsign;
            lhsCallsign = this.getCallsign();
            String rhsCallsign;
            rhsCallsign = that.getCallsign();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "callsign", lhsCallsign), LocatorUtils.property(thatLocator, "callsign", rhsCallsign), lhsCallsign, rhsCallsign, (this.callsign!= null), (that.callsign!= null))) {
                return false;
            }
        }
        {
            String lhsShipName;
            lhsShipName = this.getShipName();
            String rhsShipName;
            rhsShipName = that.getShipName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "shipName", lhsShipName), LocatorUtils.property(thatLocator, "shipName", rhsShipName), lhsShipName, rhsShipName, (this.shipName!= null), (that.shipName!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsObjectType;
            lhsObjectType = this.getObjectType();
            BigInteger rhsObjectType;
            rhsObjectType = that.getObjectType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "objectType", lhsObjectType), LocatorUtils.property(thatLocator, "objectType", rhsObjectType), lhsObjectType, rhsObjectType, (this.objectType!= null), (that.objectType!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsShipType;
            lhsShipType = this.getShipType();
            BigInteger rhsShipType;
            rhsShipType = that.getShipType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "shipType", lhsShipType), LocatorUtils.property(thatLocator, "shipType", rhsShipType), lhsShipType, rhsShipType, (this.shipType!= null), (that.shipType!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsIMO;
            lhsIMO = this.getIMO();
            BigInteger rhsIMO;
            rhsIMO = that.getIMO();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "imo", lhsIMO), LocatorUtils.property(thatLocator, "imo", rhsIMO), lhsIMO, rhsIMO, (this.imo!= null), (that.imo!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsMMSI;
            lhsMMSI = this.getMMSI();
            BigInteger rhsMMSI;
            rhsMMSI = that.getMMSI();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mmsi", lhsMMSI), LocatorUtils.property(thatLocator, "mmsi", rhsMMSI), lhsMMSI, rhsMMSI, (this.mmsi!= null), (that.mmsi!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsATONType;
            lhsATONType = this.getATONType();
            BigInteger rhsATONType;
            rhsATONType = that.getATONType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "atonType", lhsATONType), LocatorUtils.property(thatLocator, "atonType", rhsATONType), lhsATONType, rhsATONType, (this.atonType!= null), (that.atonType!= null))) {
                return false;
            }
        }
        {
            String lhsATONName;
            lhsATONName = this.getATONName();
            String rhsATONName;
            rhsATONName = that.getATONName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "atonName", lhsATONName), LocatorUtils.property(thatLocator, "atonName", rhsATONName), lhsATONName, rhsATONName, (this.atonName!= null), (that.atonName!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsAntPosDistFromFront;
            lhsAntPosDistFromFront = this.getAntPosDistFromFront();
            BigDecimal rhsAntPosDistFromFront;
            rhsAntPosDistFromFront = that.getAntPosDistFromFront();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "antPosDistFromFront", lhsAntPosDistFromFront), LocatorUtils.property(thatLocator, "antPosDistFromFront", rhsAntPosDistFromFront), lhsAntPosDistFromFront, rhsAntPosDistFromFront, (this.antPosDistFromFront!= null), (that.antPosDistFromFront!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsAntPosDistFromLeft;
            lhsAntPosDistFromLeft = this.getAntPosDistFromLeft();
            BigDecimal rhsAntPosDistFromLeft;
            rhsAntPosDistFromLeft = that.getAntPosDistFromLeft();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "antPosDistFromLeft", lhsAntPosDistFromLeft), LocatorUtils.property(thatLocator, "antPosDistFromLeft", rhsAntPosDistFromLeft), lhsAntPosDistFromLeft, rhsAntPosDistFromLeft, (this.antPosDistFromLeft!= null), (that.antPosDistFromLeft!= null))) {
                return false;
            }
        }
        {
            String lhsNatLangShipName;
            lhsNatLangShipName = this.getNatLangShipName();
            String rhsNatLangShipName;
            rhsNatLangShipName = that.getNatLangShipName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "natLangShipName", lhsNatLangShipName), LocatorUtils.property(thatLocator, "natLangShipName", rhsNatLangShipName), lhsNatLangShipName, rhsNatLangShipName, (this.natLangShipName!= null), (that.natLangShipName!= null))) {
                return false;
            }
        }
        {
            String lhsPortOfRegistry;
            lhsPortOfRegistry = this.getPortOfRegistry();
            String rhsPortOfRegistry;
            rhsPortOfRegistry = that.getPortOfRegistry();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portOfRegistry", lhsPortOfRegistry), LocatorUtils.property(thatLocator, "portOfRegistry", rhsPortOfRegistry), lhsPortOfRegistry, rhsPortOfRegistry, (this.portOfRegistry!= null), (that.portOfRegistry!= null))) {
                return false;
            }
        }
        {
            String lhsCountryFlag;
            lhsCountryFlag = this.getCountryFlag();
            String rhsCountryFlag;
            rhsCountryFlag = that.getCountryFlag();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "countryFlag", lhsCountryFlag), LocatorUtils.property(thatLocator, "countryFlag", rhsCountryFlag), lhsCountryFlag, rhsCountryFlag, (this.countryFlag!= null), (that.countryFlag!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsMaxAirDraught;
            lhsMaxAirDraught = this.getMaxAirDraught();
            BigDecimal rhsMaxAirDraught;
            rhsMaxAirDraught = that.getMaxAirDraught();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxAirDraught", lhsMaxAirDraught), LocatorUtils.property(thatLocator, "maxAirDraught", rhsMaxAirDraught), lhsMaxAirDraught, rhsMaxAirDraught, (this.maxAirDraught!= null), (that.maxAirDraught!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsMaxDraught;
            lhsMaxDraught = this.getMaxDraught();
            BigDecimal rhsMaxDraught;
            rhsMaxDraught = that.getMaxDraught();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxDraught", lhsMaxDraught), LocatorUtils.property(thatLocator, "maxDraught", rhsMaxDraught), lhsMaxDraught, rhsMaxDraught, (this.maxDraught!= null), (that.maxDraught!= null))) {
                return false;
            }
        }
        {
            String lhsDeepWaterVesselind;
            lhsDeepWaterVesselind = this.getDeepWaterVesselind();
            String rhsDeepWaterVesselind;
            rhsDeepWaterVesselind = that.getDeepWaterVesselind();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deepWaterVesselind", lhsDeepWaterVesselind), LocatorUtils.property(thatLocator, "deepWaterVesselind", rhsDeepWaterVesselind), lhsDeepWaterVesselind, rhsDeepWaterVesselind, (this.deepWaterVesselind!= null), (that.deepWaterVesselind!= null))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
        int currentHashCode = 1;
        {
            String theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId, (this.id!= null));
        }
        {
            String theSourceName;
            theSourceName = this.getSourceName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceName", theSourceName), currentHashCode, theSourceName, (this.sourceName!= null));
        }
        {
            BigInteger theSource;
            theSource = this.getSource();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "source", theSource), currentHashCode, theSource, (this.source!= null));
        }
        {
            BigDecimal theLength;
            theLength = this.getLength();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "length", theLength), currentHashCode, theLength, (this.length!= null));
        }
        {
            BigDecimal theBreadth;
            theBreadth = this.getBreadth();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "breadth", theBreadth), currentHashCode, theBreadth, (this.breadth!= null));
        }
        {
            String theCallsign;
            theCallsign = this.getCallsign();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "callsign", theCallsign), currentHashCode, theCallsign, (this.callsign!= null));
        }
        {
            String theShipName;
            theShipName = this.getShipName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "shipName", theShipName), currentHashCode, theShipName, (this.shipName!= null));
        }
        {
            BigInteger theObjectType;
            theObjectType = this.getObjectType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "objectType", theObjectType), currentHashCode, theObjectType, (this.objectType!= null));
        }
        {
            BigInteger theShipType;
            theShipType = this.getShipType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "shipType", theShipType), currentHashCode, theShipType, (this.shipType!= null));
        }
        {
            BigInteger theIMO;
            theIMO = this.getIMO();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "imo", theIMO), currentHashCode, theIMO, (this.imo!= null));
        }
        {
            BigInteger theMMSI;
            theMMSI = this.getMMSI();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mmsi", theMMSI), currentHashCode, theMMSI, (this.mmsi!= null));
        }
        {
            BigInteger theATONType;
            theATONType = this.getATONType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "atonType", theATONType), currentHashCode, theATONType, (this.atonType!= null));
        }
        {
            String theATONName;
            theATONName = this.getATONName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "atonName", theATONName), currentHashCode, theATONName, (this.atonName!= null));
        }
        {
            BigDecimal theAntPosDistFromFront;
            theAntPosDistFromFront = this.getAntPosDistFromFront();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "antPosDistFromFront", theAntPosDistFromFront), currentHashCode, theAntPosDistFromFront, (this.antPosDistFromFront!= null));
        }
        {
            BigDecimal theAntPosDistFromLeft;
            theAntPosDistFromLeft = this.getAntPosDistFromLeft();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "antPosDistFromLeft", theAntPosDistFromLeft), currentHashCode, theAntPosDistFromLeft, (this.antPosDistFromLeft!= null));
        }
        {
            String theNatLangShipName;
            theNatLangShipName = this.getNatLangShipName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "natLangShipName", theNatLangShipName), currentHashCode, theNatLangShipName, (this.natLangShipName!= null));
        }
        {
            String thePortOfRegistry;
            thePortOfRegistry = this.getPortOfRegistry();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "portOfRegistry", thePortOfRegistry), currentHashCode, thePortOfRegistry, (this.portOfRegistry!= null));
        }
        {
            String theCountryFlag;
            theCountryFlag = this.getCountryFlag();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "countryFlag", theCountryFlag), currentHashCode, theCountryFlag, (this.countryFlag!= null));
        }
        {
            BigDecimal theMaxAirDraught;
            theMaxAirDraught = this.getMaxAirDraught();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxAirDraught", theMaxAirDraught), currentHashCode, theMaxAirDraught, (this.maxAirDraught!= null));
        }
        {
            BigDecimal theMaxDraught;
            theMaxDraught = this.getMaxDraught();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxDraught", theMaxDraught), currentHashCode, theMaxDraught, (this.maxDraught!= null));
        }
        {
            String theDeepWaterVesselind;
            theDeepWaterVesselind = this.getDeepWaterVesselind();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "deepWaterVesselind", theDeepWaterVesselind), currentHashCode, theDeepWaterVesselind, (this.deepWaterVesselind!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE2;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE2;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy2 strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof StaticData) {
            final StaticData copy = ((StaticData) draftCopy);
            {
                Boolean idShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.id!= null));
                if (idShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceId;
                    sourceId = this.getId();
                    String copyId = ((String) strategy.copy(LocatorUtils.property(locator, "id", sourceId), sourceId, (this.id!= null)));
                    copy.setId(copyId);
                } else {
                    if (idShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.id = null;
                    }
                }
            }
            {
                Boolean sourceNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.sourceName!= null));
                if (sourceNameShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceSourceName;
                    sourceSourceName = this.getSourceName();
                    String copySourceName = ((String) strategy.copy(LocatorUtils.property(locator, "sourceName", sourceSourceName), sourceSourceName, (this.sourceName!= null)));
                    copy.setSourceName(copySourceName);
                } else {
                    if (sourceNameShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.sourceName = null;
                    }
                }
            }
            {
                Boolean sourceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.source!= null));
                if (sourceShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceSource;
                    sourceSource = this.getSource();
                    BigInteger copySource = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "source", sourceSource), sourceSource, (this.source!= null)));
                    copy.setSource(copySource);
                } else {
                    if (sourceShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.source = null;
                    }
                }
            }
            {
                Boolean lengthShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.length!= null));
                if (lengthShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceLength;
                    sourceLength = this.getLength();
                    BigDecimal copyLength = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "length", sourceLength), sourceLength, (this.length!= null)));
                    copy.setLength(copyLength);
                } else {
                    if (lengthShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.length = null;
                    }
                }
            }
            {
                Boolean breadthShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.breadth!= null));
                if (breadthShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceBreadth;
                    sourceBreadth = this.getBreadth();
                    BigDecimal copyBreadth = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "breadth", sourceBreadth), sourceBreadth, (this.breadth!= null)));
                    copy.setBreadth(copyBreadth);
                } else {
                    if (breadthShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.breadth = null;
                    }
                }
            }
            {
                Boolean callsignShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.callsign!= null));
                if (callsignShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceCallsign;
                    sourceCallsign = this.getCallsign();
                    String copyCallsign = ((String) strategy.copy(LocatorUtils.property(locator, "callsign", sourceCallsign), sourceCallsign, (this.callsign!= null)));
                    copy.setCallsign(copyCallsign);
                } else {
                    if (callsignShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.callsign = null;
                    }
                }
            }
            {
                Boolean shipNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.shipName!= null));
                if (shipNameShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceShipName;
                    sourceShipName = this.getShipName();
                    String copyShipName = ((String) strategy.copy(LocatorUtils.property(locator, "shipName", sourceShipName), sourceShipName, (this.shipName!= null)));
                    copy.setShipName(copyShipName);
                } else {
                    if (shipNameShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.shipName = null;
                    }
                }
            }
            {
                Boolean objectTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.objectType!= null));
                if (objectTypeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceObjectType;
                    sourceObjectType = this.getObjectType();
                    BigInteger copyObjectType = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "objectType", sourceObjectType), sourceObjectType, (this.objectType!= null)));
                    copy.setObjectType(copyObjectType);
                } else {
                    if (objectTypeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.objectType = null;
                    }
                }
            }
            {
                Boolean shipTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.shipType!= null));
                if (shipTypeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceShipType;
                    sourceShipType = this.getShipType();
                    BigInteger copyShipType = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "shipType", sourceShipType), sourceShipType, (this.shipType!= null)));
                    copy.setShipType(copyShipType);
                } else {
                    if (shipTypeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.shipType = null;
                    }
                }
            }
            {
                Boolean imoShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.imo!= null));
                if (imoShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceIMO;
                    sourceIMO = this.getIMO();
                    BigInteger copyIMO = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "imo", sourceIMO), sourceIMO, (this.imo!= null)));
                    copy.setIMO(copyIMO);
                } else {
                    if (imoShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.imo = null;
                    }
                }
            }
            {
                Boolean mmsiShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.mmsi!= null));
                if (mmsiShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceMMSI;
                    sourceMMSI = this.getMMSI();
                    BigInteger copyMMSI = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "mmsi", sourceMMSI), sourceMMSI, (this.mmsi!= null)));
                    copy.setMMSI(copyMMSI);
                } else {
                    if (mmsiShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.mmsi = null;
                    }
                }
            }
            {
                Boolean atonTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.atonType!= null));
                if (atonTypeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceATONType;
                    sourceATONType = this.getATONType();
                    BigInteger copyATONType = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "atonType", sourceATONType), sourceATONType, (this.atonType!= null)));
                    copy.setATONType(copyATONType);
                } else {
                    if (atonTypeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.atonType = null;
                    }
                }
            }
            {
                Boolean atonNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.atonName!= null));
                if (atonNameShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceATONName;
                    sourceATONName = this.getATONName();
                    String copyATONName = ((String) strategy.copy(LocatorUtils.property(locator, "atonName", sourceATONName), sourceATONName, (this.atonName!= null)));
                    copy.setATONName(copyATONName);
                } else {
                    if (atonNameShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.atonName = null;
                    }
                }
            }
            {
                Boolean antPosDistFromFrontShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.antPosDistFromFront!= null));
                if (antPosDistFromFrontShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceAntPosDistFromFront;
                    sourceAntPosDistFromFront = this.getAntPosDistFromFront();
                    BigDecimal copyAntPosDistFromFront = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "antPosDistFromFront", sourceAntPosDistFromFront), sourceAntPosDistFromFront, (this.antPosDistFromFront!= null)));
                    copy.setAntPosDistFromFront(copyAntPosDistFromFront);
                } else {
                    if (antPosDistFromFrontShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.antPosDistFromFront = null;
                    }
                }
            }
            {
                Boolean antPosDistFromLeftShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.antPosDistFromLeft!= null));
                if (antPosDistFromLeftShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceAntPosDistFromLeft;
                    sourceAntPosDistFromLeft = this.getAntPosDistFromLeft();
                    BigDecimal copyAntPosDistFromLeft = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "antPosDistFromLeft", sourceAntPosDistFromLeft), sourceAntPosDistFromLeft, (this.antPosDistFromLeft!= null)));
                    copy.setAntPosDistFromLeft(copyAntPosDistFromLeft);
                } else {
                    if (antPosDistFromLeftShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.antPosDistFromLeft = null;
                    }
                }
            }
            {
                Boolean natLangShipNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.natLangShipName!= null));
                if (natLangShipNameShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceNatLangShipName;
                    sourceNatLangShipName = this.getNatLangShipName();
                    String copyNatLangShipName = ((String) strategy.copy(LocatorUtils.property(locator, "natLangShipName", sourceNatLangShipName), sourceNatLangShipName, (this.natLangShipName!= null)));
                    copy.setNatLangShipName(copyNatLangShipName);
                } else {
                    if (natLangShipNameShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.natLangShipName = null;
                    }
                }
            }
            {
                Boolean portOfRegistryShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.portOfRegistry!= null));
                if (portOfRegistryShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourcePortOfRegistry;
                    sourcePortOfRegistry = this.getPortOfRegistry();
                    String copyPortOfRegistry = ((String) strategy.copy(LocatorUtils.property(locator, "portOfRegistry", sourcePortOfRegistry), sourcePortOfRegistry, (this.portOfRegistry!= null)));
                    copy.setPortOfRegistry(copyPortOfRegistry);
                } else {
                    if (portOfRegistryShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.portOfRegistry = null;
                    }
                }
            }
            {
                Boolean countryFlagShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.countryFlag!= null));
                if (countryFlagShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceCountryFlag;
                    sourceCountryFlag = this.getCountryFlag();
                    String copyCountryFlag = ((String) strategy.copy(LocatorUtils.property(locator, "countryFlag", sourceCountryFlag), sourceCountryFlag, (this.countryFlag!= null)));
                    copy.setCountryFlag(copyCountryFlag);
                } else {
                    if (countryFlagShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.countryFlag = null;
                    }
                }
            }
            {
                Boolean maxAirDraughtShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.maxAirDraught!= null));
                if (maxAirDraughtShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceMaxAirDraught;
                    sourceMaxAirDraught = this.getMaxAirDraught();
                    BigDecimal copyMaxAirDraught = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "maxAirDraught", sourceMaxAirDraught), sourceMaxAirDraught, (this.maxAirDraught!= null)));
                    copy.setMaxAirDraught(copyMaxAirDraught);
                } else {
                    if (maxAirDraughtShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.maxAirDraught = null;
                    }
                }
            }
            {
                Boolean maxDraughtShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.maxDraught!= null));
                if (maxDraughtShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceMaxDraught;
                    sourceMaxDraught = this.getMaxDraught();
                    BigDecimal copyMaxDraught = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "maxDraught", sourceMaxDraught), sourceMaxDraught, (this.maxDraught!= null)));
                    copy.setMaxDraught(copyMaxDraught);
                } else {
                    if (maxDraughtShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.maxDraught = null;
                    }
                }
            }
            {
                Boolean deepWaterVesselindShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.deepWaterVesselind!= null));
                if (deepWaterVesselindShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceDeepWaterVesselind;
                    sourceDeepWaterVesselind = this.getDeepWaterVesselind();
                    String copyDeepWaterVesselind = ((String) strategy.copy(LocatorUtils.property(locator, "deepWaterVesselind", sourceDeepWaterVesselind), sourceDeepWaterVesselind, (this.deepWaterVesselind!= null)));
                    copy.setDeepWaterVesselind(copyDeepWaterVesselind);
                } else {
                    if (deepWaterVesselindShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.deepWaterVesselind = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new StaticData();
    }

}
