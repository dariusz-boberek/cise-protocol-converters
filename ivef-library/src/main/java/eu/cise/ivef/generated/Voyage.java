
package eu.cise.ivef.generated;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;attribute name="CargoType"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="0"/&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *             &lt;enumeration value="3"/&gt;
 *             &lt;enumeration value="4"/&gt;
 *             &lt;enumeration value="9"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Destination" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ETA" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="ATA" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="PersonsOnBoard"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="AirDraught"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Draught"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minExclusive value="0"/&gt;
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
@XmlRootElement(name = "Voyage")
public class Voyage implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlAttribute(name = "Id", required = true)
    protected String id;
    @XmlAttribute(name = "SourceName", required = true)
    protected String sourceName;
    @XmlAttribute(name = "Source", required = true)
    protected BigInteger source;
    @XmlAttribute(name = "CargoType")
    protected BigInteger cargoType;
    @XmlAttribute(name = "Destination")
    protected String destination;
    @XmlAttribute(name = "ETA")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eta;
    @XmlAttribute(name = "ATA")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ata;
    @XmlAttribute(name = "PersonsOnBoard")
    protected BigDecimal personsOnBoard;
    @XmlAttribute(name = "AirDraught")
    protected BigDecimal airDraught;
    @XmlAttribute(name = "Draught")
    protected BigDecimal draught;

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
     * Gets the value of the cargoType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCargoType() {
        return cargoType;
    }

    /**
     * Sets the value of the cargoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCargoType(BigInteger value) {
        this.cargoType = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the eta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getETA() {
        return eta;
    }

    /**
     * Sets the value of the eta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setETA(XMLGregorianCalendar value) {
        this.eta = value;
    }

    /**
     * Gets the value of the ata property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getATA() {
        return ata;
    }

    /**
     * Sets the value of the ata property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setATA(XMLGregorianCalendar value) {
        this.ata = value;
    }

    /**
     * Gets the value of the personsOnBoard property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPersonsOnBoard() {
        return personsOnBoard;
    }

    /**
     * Sets the value of the personsOnBoard property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPersonsOnBoard(BigDecimal value) {
        this.personsOnBoard = value;
    }

    /**
     * Gets the value of the airDraught property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAirDraught() {
        return airDraught;
    }

    /**
     * Sets the value of the airDraught property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAirDraught(BigDecimal value) {
        this.airDraught = value;
    }

    /**
     * Gets the value of the draught property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDraught() {
        return draught;
    }

    /**
     * Sets the value of the draught property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDraught(BigDecimal value) {
        this.draught = value;
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
            BigInteger theCargoType;
            theCargoType = this.getCargoType();
            strategy.appendField(locator, this, "cargoType", buffer, theCargoType, (this.cargoType!= null));
        }
        {
            String theDestination;
            theDestination = this.getDestination();
            strategy.appendField(locator, this, "destination", buffer, theDestination, (this.destination!= null));
        }
        {
            XMLGregorianCalendar theETA;
            theETA = this.getETA();
            strategy.appendField(locator, this, "eta", buffer, theETA, (this.eta!= null));
        }
        {
            XMLGregorianCalendar theATA;
            theATA = this.getATA();
            strategy.appendField(locator, this, "ata", buffer, theATA, (this.ata!= null));
        }
        {
            BigDecimal thePersonsOnBoard;
            thePersonsOnBoard = this.getPersonsOnBoard();
            strategy.appendField(locator, this, "personsOnBoard", buffer, thePersonsOnBoard, (this.personsOnBoard!= null));
        }
        {
            BigDecimal theAirDraught;
            theAirDraught = this.getAirDraught();
            strategy.appendField(locator, this, "airDraught", buffer, theAirDraught, (this.airDraught!= null));
        }
        {
            BigDecimal theDraught;
            theDraught = this.getDraught();
            strategy.appendField(locator, this, "draught", buffer, theDraught, (this.draught!= null));
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
        final Voyage that = ((Voyage) object);
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
            BigInteger lhsCargoType;
            lhsCargoType = this.getCargoType();
            BigInteger rhsCargoType;
            rhsCargoType = that.getCargoType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cargoType", lhsCargoType), LocatorUtils.property(thatLocator, "cargoType", rhsCargoType), lhsCargoType, rhsCargoType, (this.cargoType!= null), (that.cargoType!= null))) {
                return false;
            }
        }
        {
            String lhsDestination;
            lhsDestination = this.getDestination();
            String rhsDestination;
            rhsDestination = that.getDestination();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "destination", lhsDestination), LocatorUtils.property(thatLocator, "destination", rhsDestination), lhsDestination, rhsDestination, (this.destination!= null), (that.destination!= null))) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsETA;
            lhsETA = this.getETA();
            XMLGregorianCalendar rhsETA;
            rhsETA = that.getETA();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eta", lhsETA), LocatorUtils.property(thatLocator, "eta", rhsETA), lhsETA, rhsETA, (this.eta!= null), (that.eta!= null))) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsATA;
            lhsATA = this.getATA();
            XMLGregorianCalendar rhsATA;
            rhsATA = that.getATA();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ata", lhsATA), LocatorUtils.property(thatLocator, "ata", rhsATA), lhsATA, rhsATA, (this.ata!= null), (that.ata!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsPersonsOnBoard;
            lhsPersonsOnBoard = this.getPersonsOnBoard();
            BigDecimal rhsPersonsOnBoard;
            rhsPersonsOnBoard = that.getPersonsOnBoard();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "personsOnBoard", lhsPersonsOnBoard), LocatorUtils.property(thatLocator, "personsOnBoard", rhsPersonsOnBoard), lhsPersonsOnBoard, rhsPersonsOnBoard, (this.personsOnBoard!= null), (that.personsOnBoard!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsAirDraught;
            lhsAirDraught = this.getAirDraught();
            BigDecimal rhsAirDraught;
            rhsAirDraught = that.getAirDraught();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "airDraught", lhsAirDraught), LocatorUtils.property(thatLocator, "airDraught", rhsAirDraught), lhsAirDraught, rhsAirDraught, (this.airDraught!= null), (that.airDraught!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsDraught;
            lhsDraught = this.getDraught();
            BigDecimal rhsDraught;
            rhsDraught = that.getDraught();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "draught", lhsDraught), LocatorUtils.property(thatLocator, "draught", rhsDraught), lhsDraught, rhsDraught, (this.draught!= null), (that.draught!= null))) {
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
            BigInteger theCargoType;
            theCargoType = this.getCargoType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cargoType", theCargoType), currentHashCode, theCargoType, (this.cargoType!= null));
        }
        {
            String theDestination;
            theDestination = this.getDestination();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "destination", theDestination), currentHashCode, theDestination, (this.destination!= null));
        }
        {
            XMLGregorianCalendar theETA;
            theETA = this.getETA();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "eta", theETA), currentHashCode, theETA, (this.eta!= null));
        }
        {
            XMLGregorianCalendar theATA;
            theATA = this.getATA();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ata", theATA), currentHashCode, theATA, (this.ata!= null));
        }
        {
            BigDecimal thePersonsOnBoard;
            thePersonsOnBoard = this.getPersonsOnBoard();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "personsOnBoard", thePersonsOnBoard), currentHashCode, thePersonsOnBoard, (this.personsOnBoard!= null));
        }
        {
            BigDecimal theAirDraught;
            theAirDraught = this.getAirDraught();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "airDraught", theAirDraught), currentHashCode, theAirDraught, (this.airDraught!= null));
        }
        {
            BigDecimal theDraught;
            theDraught = this.getDraught();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "draught", theDraught), currentHashCode, theDraught, (this.draught!= null));
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
        if (draftCopy instanceof Voyage) {
            final Voyage copy = ((Voyage) draftCopy);
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
                Boolean cargoTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.cargoType!= null));
                if (cargoTypeShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceCargoType;
                    sourceCargoType = this.getCargoType();
                    BigInteger copyCargoType = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "cargoType", sourceCargoType), sourceCargoType, (this.cargoType!= null)));
                    copy.setCargoType(copyCargoType);
                } else {
                    if (cargoTypeShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.cargoType = null;
                    }
                }
            }
            {
                Boolean destinationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.destination!= null));
                if (destinationShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceDestination;
                    sourceDestination = this.getDestination();
                    String copyDestination = ((String) strategy.copy(LocatorUtils.property(locator, "destination", sourceDestination), sourceDestination, (this.destination!= null)));
                    copy.setDestination(copyDestination);
                } else {
                    if (destinationShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.destination = null;
                    }
                }
            }
            {
                Boolean etaShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.eta!= null));
                if (etaShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceETA;
                    sourceETA = this.getETA();
                    XMLGregorianCalendar copyETA = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "eta", sourceETA), sourceETA, (this.eta!= null)));
                    copy.setETA(copyETA);
                } else {
                    if (etaShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.eta = null;
                    }
                }
            }
            {
                Boolean ataShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.ata!= null));
                if (ataShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceATA;
                    sourceATA = this.getATA();
                    XMLGregorianCalendar copyATA = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "ata", sourceATA), sourceATA, (this.ata!= null)));
                    copy.setATA(copyATA);
                } else {
                    if (ataShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.ata = null;
                    }
                }
            }
            {
                Boolean personsOnBoardShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.personsOnBoard!= null));
                if (personsOnBoardShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourcePersonsOnBoard;
                    sourcePersonsOnBoard = this.getPersonsOnBoard();
                    BigDecimal copyPersonsOnBoard = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "personsOnBoard", sourcePersonsOnBoard), sourcePersonsOnBoard, (this.personsOnBoard!= null)));
                    copy.setPersonsOnBoard(copyPersonsOnBoard);
                } else {
                    if (personsOnBoardShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.personsOnBoard = null;
                    }
                }
            }
            {
                Boolean airDraughtShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.airDraught!= null));
                if (airDraughtShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceAirDraught;
                    sourceAirDraught = this.getAirDraught();
                    BigDecimal copyAirDraught = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "airDraught", sourceAirDraught), sourceAirDraught, (this.airDraught!= null)));
                    copy.setAirDraught(copyAirDraught);
                } else {
                    if (airDraughtShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.airDraught = null;
                    }
                }
            }
            {
                Boolean draughtShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.draught!= null));
                if (draughtShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceDraught;
                    sourceDraught = this.getDraught();
                    BigDecimal copyDraught = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "draught", sourceDraught), sourceDraught, (this.draught!= null)));
                    copy.setDraught(copyDraught);
                } else {
                    if (draughtShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.draught = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Voyage();
    }

}
