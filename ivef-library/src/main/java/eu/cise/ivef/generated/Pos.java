
package eu.cise.ivef.generated;

import java.math.BigDecimal;
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
 *       &lt;attribute name="Lat" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;minInclusive value="-90.00000"/&gt;
 *             &lt;maxInclusive value="+90.00000"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Long" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;maxInclusive value="+180.00000"/&gt;
 *             &lt;minExclusive value="-180.00000"/&gt;
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
@XmlRootElement(name = "Pos")
public class Pos implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlAttribute(name = "Lat", required = true)
    protected BigDecimal lat;
    @XmlAttribute(name = "Long", required = true)
    protected BigDecimal _long;

    /**
     * Gets the value of the lat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * Sets the value of the lat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLat(BigDecimal value) {
        this.lat = value;
    }

    /**
     * Gets the value of the long property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLong() {
        return _long;
    }

    /**
     * Sets the value of the long property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLong(BigDecimal value) {
        this._long = value;
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
            BigDecimal theLat;
            theLat = this.getLat();
            strategy.appendField(locator, this, "lat", buffer, theLat, (this.lat!= null));
        }
        {
            BigDecimal theLong;
            theLong = this.getLong();
            strategy.appendField(locator, this, "_long", buffer, theLong, (this._long!= null));
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
        final Pos that = ((Pos) object);
        {
            BigDecimal lhsLat;
            lhsLat = this.getLat();
            BigDecimal rhsLat;
            rhsLat = that.getLat();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lat", lhsLat), LocatorUtils.property(thatLocator, "lat", rhsLat), lhsLat, rhsLat, (this.lat!= null), (that.lat!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsLong;
            lhsLong = this.getLong();
            BigDecimal rhsLong;
            rhsLong = that.getLong();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "_long", lhsLong), LocatorUtils.property(thatLocator, "_long", rhsLong), lhsLong, rhsLong, (this._long!= null), (that._long!= null))) {
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
            BigDecimal theLat;
            theLat = this.getLat();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lat", theLat), currentHashCode, theLat, (this.lat!= null));
        }
        {
            BigDecimal theLong;
            theLong = this.getLong();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "_long", theLong), currentHashCode, theLong, (this._long!= null));
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
        if (draftCopy instanceof Pos) {
            final Pos copy = ((Pos) draftCopy);
            {
                Boolean latShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.lat!= null));
                if (latShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceLat;
                    sourceLat = this.getLat();
                    BigDecimal copyLat = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "lat", sourceLat), sourceLat, (this.lat!= null)));
                    copy.setLat(copyLat);
                } else {
                    if (latShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.lat = null;
                    }
                }
            }
            {
                Boolean _longShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this._long!= null));
                if (_longShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigDecimal sourceLong;
                    sourceLong = this.getLong();
                    BigDecimal copyLong = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "_long", sourceLong), sourceLong, (this._long!= null)));
                    copy.setLong(copyLong);
                } else {
                    if (_longShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy._long = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Pos();
    }

}
