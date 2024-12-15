
package eu.cise.ivef.generated;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}Pos"/&gt;
 *         &lt;element name="Sensor" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="SenId" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;maxInclusive value="65536"/&gt;
 *                       &lt;minInclusive value="0"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="TrkId" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;maxInclusive value="65536"/&gt;
 *                       &lt;minInclusive value="0"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="SourceId" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="UpdateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="UpdateTimeRadar" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="UpdateTimeAIS" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="UpdateTimeDR" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="SOG" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minInclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="COG" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;fractionDigits value="1"/&gt;
 *             &lt;minInclusive value="0"/&gt;
 *             &lt;maxInclusive value="360"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Lost" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="no"/&gt;
 *             &lt;enumeration value="yes"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="RateOfTurn" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="Orientation"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minInclusive value="0.0"/&gt;
 *             &lt;maxInclusive value="360.0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Length"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minInclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Breadth"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minInclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Altitude" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="NavStatus"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="0"/&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *             &lt;enumeration value="3"/&gt;
 *             &lt;enumeration value="4"/&gt;
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
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="UpdSensorType"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *             &lt;enumeration value="3"/&gt;
 *             &lt;enumeration value="4"/&gt;
 *             &lt;enumeration value="5"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="ATONOffPos" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pos",
    "sensor"
})
@XmlRootElement(name = "PosReport")
public class PosReport implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlElement(name = "Pos", required = true)
    protected Pos pos;
    @XmlElement(name = "Sensor")
    protected List<PosReport.Sensor> sensor;
    @XmlAttribute(name = "Id", required = true)
    protected BigInteger id;
    @XmlAttribute(name = "SourceId", required = true)
    protected BigInteger sourceId;
    @XmlAttribute(name = "UpdateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    @XmlAttribute(name = "UpdateTimeRadar")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimeRadar;
    @XmlAttribute(name = "UpdateTimeAIS")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimeAIS;
    @XmlAttribute(name = "UpdateTimeDR")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTimeDR;
    @XmlAttribute(name = "SOG", required = true)
    protected BigDecimal sog;
    @XmlAttribute(name = "COG", required = true)
    protected BigDecimal cog;
    @XmlAttribute(name = "Lost", required = true)
    protected String lost;
    @XmlAttribute(name = "RateOfTurn")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "decimal")
    protected Double rateOfTurn;
    @XmlAttribute(name = "Orientation")
    protected BigDecimal orientation;
    @XmlAttribute(name = "Length")
    protected BigDecimal length;
    @XmlAttribute(name = "Breadth")
    protected BigDecimal breadth;
    @XmlAttribute(name = "Altitude")
    protected BigDecimal altitude;
    @XmlAttribute(name = "NavStatus")
    protected BigInteger navStatus;
    @XmlAttribute(name = "UpdSensorType")
    protected BigInteger updSensorType;
    @XmlAttribute(name = "ATONOffPos")
    protected Boolean atonOffPos;

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link Pos }
     *     
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pos }
     *     
     */
    public void setPos(Pos value) {
        this.pos = value;
    }

    /**
     * Gets the value of the sensor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PosReport.Sensor }
     * 
     * 
     */
    public List<PosReport.Sensor> getSensor() {
        if (sensor == null) {
            sensor = new ArrayList<PosReport.Sensor>();
        }
        return this.sensor;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the sourceId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSourceId() {
        return sourceId;
    }

    /**
     * Sets the value of the sourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSourceId(BigInteger value) {
        this.sourceId = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
    }

    /**
     * Gets the value of the updateTimeRadar property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTimeRadar() {
        return updateTimeRadar;
    }

    /**
     * Sets the value of the updateTimeRadar property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTimeRadar(XMLGregorianCalendar value) {
        this.updateTimeRadar = value;
    }

    /**
     * Gets the value of the updateTimeAIS property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTimeAIS() {
        return updateTimeAIS;
    }

    /**
     * Sets the value of the updateTimeAIS property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTimeAIS(XMLGregorianCalendar value) {
        this.updateTimeAIS = value;
    }

    /**
     * Gets the value of the updateTimeDR property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTimeDR() {
        return updateTimeDR;
    }

    /**
     * Sets the value of the updateTimeDR property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTimeDR(XMLGregorianCalendar value) {
        this.updateTimeDR = value;
    }

    /**
     * Gets the value of the sog property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSOG() {
        return sog;
    }

    /**
     * Sets the value of the sog property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSOG(BigDecimal value) {
        this.sog = value;
    }

    /**
     * Gets the value of the cog property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCOG() {
        return cog;
    }

    /**
     * Sets the value of the cog property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCOG(BigDecimal value) {
        this.cog = value;
    }

    /**
     * Gets the value of the lost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLost() {
        return lost;
    }

    /**
     * Sets the value of the lost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLost(String value) {
        this.lost = value;
    }

    /**
     * Gets the value of the rateOfTurn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getRateOfTurn() {
        return rateOfTurn;
    }

    /**
     * Sets the value of the rateOfTurn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateOfTurn(Double value) {
        this.rateOfTurn = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrientation() {
        return orientation;
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrientation(BigDecimal value) {
        this.orientation = value;
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
     * Gets the value of the altitude property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAltitude() {
        return altitude;
    }

    /**
     * Sets the value of the altitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAltitude(BigDecimal value) {
        this.altitude = value;
    }

    /**
     * Gets the value of the navStatus property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNavStatus() {
        return navStatus;
    }

    /**
     * Sets the value of the navStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNavStatus(BigInteger value) {
        this.navStatus = value;
    }

    /**
     * Gets the value of the updSensorType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUpdSensorType() {
        return updSensorType;
    }

    /**
     * Sets the value of the updSensorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUpdSensorType(BigInteger value) {
        this.updSensorType = value;
    }

    /**
     * Gets the value of the atonOffPos property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isATONOffPos() {
        return atonOffPos;
    }

    /**
     * Sets the value of the atonOffPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setATONOffPos(Boolean value) {
        this.atonOffPos = value;
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
            Pos thePos;
            thePos = this.getPos();
            strategy.appendField(locator, this, "pos", buffer, thePos, (this.pos!= null));
        }
        {
            List<PosReport.Sensor> theSensor;
            theSensor = (((this.sensor!= null)&&(!this.sensor.isEmpty()))?this.getSensor():null);
            strategy.appendField(locator, this, "sensor", buffer, theSensor, ((this.sensor!= null)&&(!this.sensor.isEmpty())));
        }
        {
            BigInteger theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId, (this.id!= null));
        }
        {
            BigInteger theSourceId;
            theSourceId = this.getSourceId();
            strategy.appendField(locator, this, "sourceId", buffer, theSourceId, (this.sourceId!= null));
        }
        {
            XMLGregorianCalendar theUpdateTime;
            theUpdateTime = this.getUpdateTime();
            strategy.appendField(locator, this, "updateTime", buffer, theUpdateTime, (this.updateTime!= null));
        }
        {
            XMLGregorianCalendar theUpdateTimeRadar;
            theUpdateTimeRadar = this.getUpdateTimeRadar();
            strategy.appendField(locator, this, "updateTimeRadar", buffer, theUpdateTimeRadar, (this.updateTimeRadar!= null));
        }
        {
            XMLGregorianCalendar theUpdateTimeAIS;
            theUpdateTimeAIS = this.getUpdateTimeAIS();
            strategy.appendField(locator, this, "updateTimeAIS", buffer, theUpdateTimeAIS, (this.updateTimeAIS!= null));
        }
        {
            XMLGregorianCalendar theUpdateTimeDR;
            theUpdateTimeDR = this.getUpdateTimeDR();
            strategy.appendField(locator, this, "updateTimeDR", buffer, theUpdateTimeDR, (this.updateTimeDR!= null));
        }
        {
            BigDecimal theSOG;
            theSOG = this.getSOG();
            strategy.appendField(locator, this, "sog", buffer, theSOG, (this.sog!= null));
        }
        {
            BigDecimal theCOG;
            theCOG = this.getCOG();
            strategy.appendField(locator, this, "cog", buffer, theCOG, (this.cog!= null));
        }
        {
            String theLost;
            theLost = this.getLost();
            strategy.appendField(locator, this, "lost", buffer, theLost, (this.lost!= null));
        }
        {
            Double theRateOfTurn;
            theRateOfTurn = this.getRateOfTurn();
            strategy.appendField(locator, this, "rateOfTurn", buffer, theRateOfTurn, (this.rateOfTurn!= null));
        }
        {
            BigDecimal theOrientation;
            theOrientation = this.getOrientation();
            strategy.appendField(locator, this, "orientation", buffer, theOrientation, (this.orientation!= null));
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
            BigDecimal theAltitude;
            theAltitude = this.getAltitude();
            strategy.appendField(locator, this, "altitude", buffer, theAltitude, (this.altitude!= null));
        }
        {
            BigInteger theNavStatus;
            theNavStatus = this.getNavStatus();
            strategy.appendField(locator, this, "navStatus", buffer, theNavStatus, (this.navStatus!= null));
        }
        {
            BigInteger theUpdSensorType;
            theUpdSensorType = this.getUpdSensorType();
            strategy.appendField(locator, this, "updSensorType", buffer, theUpdSensorType, (this.updSensorType!= null));
        }
        {
            Boolean theATONOffPos;
            theATONOffPos = this.isATONOffPos();
            strategy.appendField(locator, this, "atonOffPos", buffer, theATONOffPos, (this.atonOffPos!= null));
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
        final PosReport that = ((PosReport) object);
        {
            Pos lhsPos;
            lhsPos = this.getPos();
            Pos rhsPos;
            rhsPos = that.getPos();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos, (this.pos!= null), (that.pos!= null))) {
                return false;
            }
        }
        {
            List<PosReport.Sensor> lhsSensor;
            lhsSensor = (((this.sensor!= null)&&(!this.sensor.isEmpty()))?this.getSensor():null);
            List<PosReport.Sensor> rhsSensor;
            rhsSensor = (((that.sensor!= null)&&(!that.sensor.isEmpty()))?that.getSensor():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sensor", lhsSensor), LocatorUtils.property(thatLocator, "sensor", rhsSensor), lhsSensor, rhsSensor, ((this.sensor!= null)&&(!this.sensor.isEmpty())), ((that.sensor!= null)&&(!that.sensor.isEmpty())))) {
                return false;
            }
        }
        {
            BigInteger lhsId;
            lhsId = this.getId();
            BigInteger rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId, (this.id!= null), (that.id!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsSourceId;
            lhsSourceId = this.getSourceId();
            BigInteger rhsSourceId;
            rhsSourceId = that.getSourceId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceId", lhsSourceId), LocatorUtils.property(thatLocator, "sourceId", rhsSourceId), lhsSourceId, rhsSourceId, (this.sourceId!= null), (that.sourceId!= null))) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsUpdateTime;
            lhsUpdateTime = this.getUpdateTime();
            XMLGregorianCalendar rhsUpdateTime;
            rhsUpdateTime = that.getUpdateTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "updateTime", lhsUpdateTime), LocatorUtils.property(thatLocator, "updateTime", rhsUpdateTime), lhsUpdateTime, rhsUpdateTime, (this.updateTime!= null), (that.updateTime!= null))) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsUpdateTimeRadar;
            lhsUpdateTimeRadar = this.getUpdateTimeRadar();
            XMLGregorianCalendar rhsUpdateTimeRadar;
            rhsUpdateTimeRadar = that.getUpdateTimeRadar();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "updateTimeRadar", lhsUpdateTimeRadar), LocatorUtils.property(thatLocator, "updateTimeRadar", rhsUpdateTimeRadar), lhsUpdateTimeRadar, rhsUpdateTimeRadar, (this.updateTimeRadar!= null), (that.updateTimeRadar!= null))) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsUpdateTimeAIS;
            lhsUpdateTimeAIS = this.getUpdateTimeAIS();
            XMLGregorianCalendar rhsUpdateTimeAIS;
            rhsUpdateTimeAIS = that.getUpdateTimeAIS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "updateTimeAIS", lhsUpdateTimeAIS), LocatorUtils.property(thatLocator, "updateTimeAIS", rhsUpdateTimeAIS), lhsUpdateTimeAIS, rhsUpdateTimeAIS, (this.updateTimeAIS!= null), (that.updateTimeAIS!= null))) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsUpdateTimeDR;
            lhsUpdateTimeDR = this.getUpdateTimeDR();
            XMLGregorianCalendar rhsUpdateTimeDR;
            rhsUpdateTimeDR = that.getUpdateTimeDR();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "updateTimeDR", lhsUpdateTimeDR), LocatorUtils.property(thatLocator, "updateTimeDR", rhsUpdateTimeDR), lhsUpdateTimeDR, rhsUpdateTimeDR, (this.updateTimeDR!= null), (that.updateTimeDR!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsSOG;
            lhsSOG = this.getSOG();
            BigDecimal rhsSOG;
            rhsSOG = that.getSOG();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sog", lhsSOG), LocatorUtils.property(thatLocator, "sog", rhsSOG), lhsSOG, rhsSOG, (this.sog!= null), (that.sog!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsCOG;
            lhsCOG = this.getCOG();
            BigDecimal rhsCOG;
            rhsCOG = that.getCOG();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cog", lhsCOG), LocatorUtils.property(thatLocator, "cog", rhsCOG), lhsCOG, rhsCOG, (this.cog!= null), (that.cog!= null))) {
                return false;
            }
        }
        {
            String lhsLost;
            lhsLost = this.getLost();
            String rhsLost;
            rhsLost = that.getLost();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lost", lhsLost), LocatorUtils.property(thatLocator, "lost", rhsLost), lhsLost, rhsLost, (this.lost!= null), (that.lost!= null))) {
                return false;
            }
        }
        {
            Double lhsRateOfTurn;
            lhsRateOfTurn = this.getRateOfTurn();
            Double rhsRateOfTurn;
            rhsRateOfTurn = that.getRateOfTurn();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rateOfTurn", lhsRateOfTurn), LocatorUtils.property(thatLocator, "rateOfTurn", rhsRateOfTurn), lhsRateOfTurn, rhsRateOfTurn, (this.rateOfTurn!= null), (that.rateOfTurn!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsOrientation;
            lhsOrientation = this.getOrientation();
            BigDecimal rhsOrientation;
            rhsOrientation = that.getOrientation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orientation", lhsOrientation), LocatorUtils.property(thatLocator, "orientation", rhsOrientation), lhsOrientation, rhsOrientation, (this.orientation!= null), (that.orientation!= null))) {
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
            BigDecimal lhsAltitude;
            lhsAltitude = this.getAltitude();
            BigDecimal rhsAltitude;
            rhsAltitude = that.getAltitude();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "altitude", lhsAltitude), LocatorUtils.property(thatLocator, "altitude", rhsAltitude), lhsAltitude, rhsAltitude, (this.altitude!= null), (that.altitude!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsNavStatus;
            lhsNavStatus = this.getNavStatus();
            BigInteger rhsNavStatus;
            rhsNavStatus = that.getNavStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "navStatus", lhsNavStatus), LocatorUtils.property(thatLocator, "navStatus", rhsNavStatus), lhsNavStatus, rhsNavStatus, (this.navStatus!= null), (that.navStatus!= null))) {
                return false;
            }
        }
        {
            BigInteger lhsUpdSensorType;
            lhsUpdSensorType = this.getUpdSensorType();
            BigInteger rhsUpdSensorType;
            rhsUpdSensorType = that.getUpdSensorType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "updSensorType", lhsUpdSensorType), LocatorUtils.property(thatLocator, "updSensorType", rhsUpdSensorType), lhsUpdSensorType, rhsUpdSensorType, (this.updSensorType!= null), (that.updSensorType!= null))) {
                return false;
            }
        }
        {
            Boolean lhsATONOffPos;
            lhsATONOffPos = this.isATONOffPos();
            Boolean rhsATONOffPos;
            rhsATONOffPos = that.isATONOffPos();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "atonOffPos", lhsATONOffPos), LocatorUtils.property(thatLocator, "atonOffPos", rhsATONOffPos), lhsATONOffPos, rhsATONOffPos, (this.atonOffPos!= null), (that.atonOffPos!= null))) {
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
            Pos thePos;
            thePos = this.getPos();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos, (this.pos!= null));
        }
        {
            List<PosReport.Sensor> theSensor;
            theSensor = (((this.sensor!= null)&&(!this.sensor.isEmpty()))?this.getSensor():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sensor", theSensor), currentHashCode, theSensor, ((this.sensor!= null)&&(!this.sensor.isEmpty())));
        }
        {
            BigInteger theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId, (this.id!= null));
        }
        {
            BigInteger theSourceId;
            theSourceId = this.getSourceId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceId", theSourceId), currentHashCode, theSourceId, (this.sourceId!= null));
        }
        {
            XMLGregorianCalendar theUpdateTime;
            theUpdateTime = this.getUpdateTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "updateTime", theUpdateTime), currentHashCode, theUpdateTime, (this.updateTime!= null));
        }
        {
            XMLGregorianCalendar theUpdateTimeRadar;
            theUpdateTimeRadar = this.getUpdateTimeRadar();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "updateTimeRadar", theUpdateTimeRadar), currentHashCode, theUpdateTimeRadar, (this.updateTimeRadar!= null));
        }
        {
            XMLGregorianCalendar theUpdateTimeAIS;
            theUpdateTimeAIS = this.getUpdateTimeAIS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "updateTimeAIS", theUpdateTimeAIS), currentHashCode, theUpdateTimeAIS, (this.updateTimeAIS!= null));
        }
        {
            XMLGregorianCalendar theUpdateTimeDR;
            theUpdateTimeDR = this.getUpdateTimeDR();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "updateTimeDR", theUpdateTimeDR), currentHashCode, theUpdateTimeDR, (this.updateTimeDR!= null));
        }
        {
            BigDecimal theSOG;
            theSOG = this.getSOG();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sog", theSOG), currentHashCode, theSOG, (this.sog!= null));
        }
        {
            BigDecimal theCOG;
            theCOG = this.getCOG();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cog", theCOG), currentHashCode, theCOG, (this.cog!= null));
        }
        {
            String theLost;
            theLost = this.getLost();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lost", theLost), currentHashCode, theLost, (this.lost!= null));
        }
        {
            Double theRateOfTurn;
            theRateOfTurn = this.getRateOfTurn();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rateOfTurn", theRateOfTurn), currentHashCode, theRateOfTurn, (this.rateOfTurn!= null));
        }
        {
            BigDecimal theOrientation;
            theOrientation = this.getOrientation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "orientation", theOrientation), currentHashCode, theOrientation, (this.orientation!= null));
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
            BigDecimal theAltitude;
            theAltitude = this.getAltitude();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "altitude", theAltitude), currentHashCode, theAltitude, (this.altitude!= null));
        }
        {
            BigInteger theNavStatus;
            theNavStatus = this.getNavStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "navStatus", theNavStatus), currentHashCode, theNavStatus, (this.navStatus!= null));
        }
        {
            BigInteger theUpdSensorType;
            theUpdSensorType = this.getUpdSensorType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "updSensorType", theUpdSensorType), currentHashCode, theUpdSensorType, (this.updSensorType!= null));
        }
        {
            Boolean theATONOffPos;
            theATONOffPos = this.isATONOffPos();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "atonOffPos", theATONOffPos), currentHashCode, theATONOffPos, (this.atonOffPos!= null));
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
        if (draftCopy instanceof PosReport) {
            final PosReport copy = ((PosReport) draftCopy);
            {
                Boolean posShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.pos!= null));
                if (posShouldBeCopiedAndSet == Boolean.TRUE) {
                    Pos sourcePos;
                    sourcePos = this.getPos();
                    Pos copyPos = ((Pos) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos, (this.pos!= null)));
                    copy.setPos(copyPos);
                } else {
                    if (posShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.pos = null;
                    }
                }
            }
            {
                Boolean sensorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.sensor!= null)&&(!this.sensor.isEmpty())));
                if (sensorShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<PosReport.Sensor> sourceSensor;
                    sourceSensor = (((this.sensor!= null)&&(!this.sensor.isEmpty()))?this.getSensor():null);
                    @SuppressWarnings("unchecked")
                    List<PosReport.Sensor> copySensor = ((List<PosReport.Sensor> ) strategy.copy(LocatorUtils.property(locator, "sensor", sourceSensor), sourceSensor, ((this.sensor!= null)&&(!this.sensor.isEmpty()))));
                    copy.sensor = null;
                    if (copySensor!= null) {
                        List<PosReport.Sensor> uniqueSensorl = copy.getSensor();
                        uniqueSensorl.addAll(copySensor);
                    }
                } else {
                    if (sensorShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.sensor = null;
                    }
                }
            }
            {
                Boolean idShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.id!= null));
                if (idShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceId;
                    sourceId = this.getId();
                    BigInteger copyId = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "id", sourceId), sourceId, (this.id!= null)));
                    copy.setId(copyId);
                } else {
                    if (idShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.id = null;
                    }
                }
            }
            {
                Boolean sourceIdShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.sourceId!= null));
                if (sourceIdShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceSourceId;
                    sourceSourceId = this.getSourceId();
                    BigInteger copySourceId = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "sourceId", sourceSourceId), sourceSourceId, (this.sourceId!= null)));
                    copy.setSourceId(copySourceId);
                } else {
                    if (sourceIdShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.sourceId = null;
                    }
                }
            }
            {
                Boolean updateTimeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.updateTime!= null));
                if (updateTimeShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceUpdateTime;
                    sourceUpdateTime = this.getUpdateTime();
                    XMLGregorianCalendar copyUpdateTime = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "updateTime", sourceUpdateTime), sourceUpdateTime, (this.updateTime!= null)));
                    copy.setUpdateTime(copyUpdateTime);
                } else {
                    if (updateTimeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.updateTime = null;
                    }
                }
            }
            {
                Boolean updateTimeRadarShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.updateTimeRadar!= null));
                if (updateTimeRadarShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceUpdateTimeRadar;
                    sourceUpdateTimeRadar = this.getUpdateTimeRadar();
                    XMLGregorianCalendar copyUpdateTimeRadar = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "updateTimeRadar", sourceUpdateTimeRadar), sourceUpdateTimeRadar, (this.updateTimeRadar!= null)));
                    copy.setUpdateTimeRadar(copyUpdateTimeRadar);
                } else {
                    if (updateTimeRadarShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.updateTimeRadar = null;
                    }
                }
            }
            {
                Boolean updateTimeAISShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.updateTimeAIS!= null));
                if (updateTimeAISShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceUpdateTimeAIS;
                    sourceUpdateTimeAIS = this.getUpdateTimeAIS();
                    XMLGregorianCalendar copyUpdateTimeAIS = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "updateTimeAIS", sourceUpdateTimeAIS), sourceUpdateTimeAIS, (this.updateTimeAIS!= null)));
                    copy.setUpdateTimeAIS(copyUpdateTimeAIS);
                } else {
                    if (updateTimeAISShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.updateTimeAIS = null;
                    }
                }
            }
            {
                Boolean updateTimeDRShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.updateTimeDR!= null));
                if (updateTimeDRShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceUpdateTimeDR;
                    sourceUpdateTimeDR = this.getUpdateTimeDR();
                    XMLGregorianCalendar copyUpdateTimeDR = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "updateTimeDR", sourceUpdateTimeDR), sourceUpdateTimeDR, (this.updateTimeDR!= null)));
                    copy.setUpdateTimeDR(copyUpdateTimeDR);
                } else {
                    if (updateTimeDRShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.updateTimeDR = null;
                    }
                }
            }
            {
                Boolean sogShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.sog!= null));
                if (sogShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceSOG;
                    sourceSOG = this.getSOG();
                    BigDecimal copySOG = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "sog", sourceSOG), sourceSOG, (this.sog!= null)));
                    copy.setSOG(copySOG);
                } else {
                    if (sogShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.sog = null;
                    }
                }
            }
            {
                Boolean cogShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.cog!= null));
                if (cogShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceCOG;
                    sourceCOG = this.getCOG();
                    BigDecimal copyCOG = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "cog", sourceCOG), sourceCOG, (this.cog!= null)));
                    copy.setCOG(copyCOG);
                } else {
                    if (cogShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.cog = null;
                    }
                }
            }
            {
                Boolean lostShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.lost!= null));
                if (lostShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceLost;
                    sourceLost = this.getLost();
                    String copyLost = ((String) strategy.copy(LocatorUtils.property(locator, "lost", sourceLost), sourceLost, (this.lost!= null)));
                    copy.setLost(copyLost);
                } else {
                    if (lostShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.lost = null;
                    }
                }
            }
            {
                Boolean rateOfTurnShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.rateOfTurn!= null));
                if (rateOfTurnShouldBeCopiedAndSet == Boolean.TRUE) {
                    Double sourceRateOfTurn;
                    sourceRateOfTurn = this.getRateOfTurn();
                    Double copyRateOfTurn = ((Double) strategy.copy(LocatorUtils.property(locator, "rateOfTurn", sourceRateOfTurn), sourceRateOfTurn, (this.rateOfTurn!= null)));
                    copy.setRateOfTurn(copyRateOfTurn);
                } else {
                    if (rateOfTurnShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.rateOfTurn = null;
                    }
                }
            }
            {
                Boolean orientationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.orientation!= null));
                if (orientationShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceOrientation;
                    sourceOrientation = this.getOrientation();
                    BigDecimal copyOrientation = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "orientation", sourceOrientation), sourceOrientation, (this.orientation!= null)));
                    copy.setOrientation(copyOrientation);
                } else {
                    if (orientationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.orientation = null;
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
                Boolean altitudeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.altitude!= null));
                if (altitudeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceAltitude;
                    sourceAltitude = this.getAltitude();
                    BigDecimal copyAltitude = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "altitude", sourceAltitude), sourceAltitude, (this.altitude!= null)));
                    copy.setAltitude(copyAltitude);
                } else {
                    if (altitudeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.altitude = null;
                    }
                }
            }
            {
                Boolean navStatusShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.navStatus!= null));
                if (navStatusShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceNavStatus;
                    sourceNavStatus = this.getNavStatus();
                    BigInteger copyNavStatus = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "navStatus", sourceNavStatus), sourceNavStatus, (this.navStatus!= null)));
                    copy.setNavStatus(copyNavStatus);
                } else {
                    if (navStatusShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.navStatus = null;
                    }
                }
            }
            {
                Boolean updSensorTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.updSensorType!= null));
                if (updSensorTypeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceUpdSensorType;
                    sourceUpdSensorType = this.getUpdSensorType();
                    BigInteger copyUpdSensorType = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "updSensorType", sourceUpdSensorType), sourceUpdSensorType, (this.updSensorType!= null)));
                    copy.setUpdSensorType(copyUpdSensorType);
                } else {
                    if (updSensorTypeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.updSensorType = null;
                    }
                }
            }
            {
                Boolean atonOffPosShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.atonOffPos!= null));
                if (atonOffPosShouldBeCopiedAndSet == Boolean.TRUE) {
                    Boolean sourceATONOffPos;
                    sourceATONOffPos = this.isATONOffPos();
                    Boolean copyATONOffPos = ((Boolean) strategy.copy(LocatorUtils.property(locator, "atonOffPos", sourceATONOffPos), sourceATONOffPos, (this.atonOffPos!= null)));
                    copy.setATONOffPos(copyATONOffPos);
                } else {
                    if (atonOffPosShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.atonOffPos = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PosReport();
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="SenId" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;maxInclusive value="65536"/&gt;
     *             &lt;minInclusive value="0"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="TrkId" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;maxInclusive value="65536"/&gt;
     *             &lt;minInclusive value="0"/&gt;
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
    public static class Sensor implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlAttribute(name = "SenId", required = true)
        protected BigDecimal senId;
        @XmlAttribute(name = "TrkId", required = true)
        protected BigDecimal trkId;

        /**
         * Gets the value of the senId property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSenId() {
            return senId;
        }

        /**
         * Sets the value of the senId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSenId(BigDecimal value) {
            this.senId = value;
        }

        /**
         * Gets the value of the trkId property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTrkId() {
            return trkId;
        }

        /**
         * Sets the value of the trkId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTrkId(BigDecimal value) {
            this.trkId = value;
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
                BigDecimal theSenId;
                theSenId = this.getSenId();
                strategy.appendField(locator, this, "senId", buffer, theSenId, (this.senId!= null));
            }
            {
                BigDecimal theTrkId;
                theTrkId = this.getTrkId();
                strategy.appendField(locator, this, "trkId", buffer, theTrkId, (this.trkId!= null));
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
            final PosReport.Sensor that = ((PosReport.Sensor) object);
            {
                BigDecimal lhsSenId;
                lhsSenId = this.getSenId();
                BigDecimal rhsSenId;
                rhsSenId = that.getSenId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "senId", lhsSenId), LocatorUtils.property(thatLocator, "senId", rhsSenId), lhsSenId, rhsSenId, (this.senId!= null), (that.senId!= null))) {
                    return false;
                }
            }
            {
                BigDecimal lhsTrkId;
                lhsTrkId = this.getTrkId();
                BigDecimal rhsTrkId;
                rhsTrkId = that.getTrkId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "trkId", lhsTrkId), LocatorUtils.property(thatLocator, "trkId", rhsTrkId), lhsTrkId, rhsTrkId, (this.trkId!= null), (that.trkId!= null))) {
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
                BigDecimal theSenId;
                theSenId = this.getSenId();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "senId", theSenId), currentHashCode, theSenId, (this.senId!= null));
            }
            {
                BigDecimal theTrkId;
                theTrkId = this.getTrkId();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trkId", theTrkId), currentHashCode, theTrkId, (this.trkId!= null));
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
            if (draftCopy instanceof PosReport.Sensor) {
                final PosReport.Sensor copy = ((PosReport.Sensor) draftCopy);
                {
                    Boolean senIdShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.senId!= null));
                    if (senIdShouldBeCopiedAndSet == Boolean.TRUE) {
                        BigDecimal sourceSenId;
                        sourceSenId = this.getSenId();
                        BigDecimal copySenId = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "senId", sourceSenId), sourceSenId, (this.senId!= null)));
                        copy.setSenId(copySenId);
                    } else {
                        if (senIdShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.senId = null;
                        }
                    }
                }
                {
                    Boolean trkIdShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.trkId!= null));
                    if (trkIdShouldBeCopiedAndSet == Boolean.TRUE) {
                        BigDecimal sourceTrkId;
                        sourceTrkId = this.getTrkId();
                        BigDecimal copyTrkId = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "trkId", sourceTrkId), sourceTrkId, (this.trkId!= null)));
                        copy.setTrkId(copyTrkId);
                    } else {
                        if (trkIdShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.trkId = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new PosReport.Sensor();
        }

    }

}
