
package eu.cise.ivef.generated;

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
 *       &lt;attribute name="TimeStamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="MsgId" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;maxLength value="36"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="SourceId" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Pong")
public class Pong implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlAttribute(name = "TimeStamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlAttribute(name = "MsgId", required = true)
    protected String msgId;
    @XmlAttribute(name = "SourceId", required = true)
    protected BigInteger sourceId;

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the msgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Sets the value of the msgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgId(String value) {
        this.msgId = value;
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
            XMLGregorianCalendar theTimeStamp;
            theTimeStamp = this.getTimeStamp();
            strategy.appendField(locator, this, "timeStamp", buffer, theTimeStamp, (this.timeStamp!= null));
        }
        {
            String theMsgId;
            theMsgId = this.getMsgId();
            strategy.appendField(locator, this, "msgId", buffer, theMsgId, (this.msgId!= null));
        }
        {
            BigInteger theSourceId;
            theSourceId = this.getSourceId();
            strategy.appendField(locator, this, "sourceId", buffer, theSourceId, (this.sourceId!= null));
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
        final Pong that = ((Pong) object);
        {
            XMLGregorianCalendar lhsTimeStamp;
            lhsTimeStamp = this.getTimeStamp();
            XMLGregorianCalendar rhsTimeStamp;
            rhsTimeStamp = that.getTimeStamp();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeStamp", lhsTimeStamp), LocatorUtils.property(thatLocator, "timeStamp", rhsTimeStamp), lhsTimeStamp, rhsTimeStamp, (this.timeStamp!= null), (that.timeStamp!= null))) {
                return false;
            }
        }
        {
            String lhsMsgId;
            lhsMsgId = this.getMsgId();
            String rhsMsgId;
            rhsMsgId = that.getMsgId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "msgId", lhsMsgId), LocatorUtils.property(thatLocator, "msgId", rhsMsgId), lhsMsgId, rhsMsgId, (this.msgId!= null), (that.msgId!= null))) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
        int currentHashCode = 1;
        {
            XMLGregorianCalendar theTimeStamp;
            theTimeStamp = this.getTimeStamp();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeStamp", theTimeStamp), currentHashCode, theTimeStamp, (this.timeStamp!= null));
        }
        {
            String theMsgId;
            theMsgId = this.getMsgId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "msgId", theMsgId), currentHashCode, theMsgId, (this.msgId!= null));
        }
        {
            BigInteger theSourceId;
            theSourceId = this.getSourceId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceId", theSourceId), currentHashCode, theSourceId, (this.sourceId!= null));
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
        if (draftCopy instanceof Pong) {
            final Pong copy = ((Pong) draftCopy);
            {
                Boolean timeStampShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.timeStamp!= null));
                if (timeStampShouldBeCopiedAndSet == Boolean.TRUE) {
                    XMLGregorianCalendar sourceTimeStamp;
                    sourceTimeStamp = this.getTimeStamp();
                    XMLGregorianCalendar copyTimeStamp = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "timeStamp", sourceTimeStamp), sourceTimeStamp, (this.timeStamp!= null)));
                    copy.setTimeStamp(copyTimeStamp);
                } else {
                    if (timeStampShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.timeStamp = null;
                    }
                }
            }
            {
                Boolean msgIdShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.msgId!= null));
                if (msgIdShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceMsgId;
                    sourceMsgId = this.getMsgId();
                    String copyMsgId = ((String) strategy.copy(LocatorUtils.property(locator, "msgId", sourceMsgId), sourceMsgId, (this.msgId!= null)));
                    copy.setMsgId(copyMsgId);
                } else {
                    if (msgIdShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.msgId = null;
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
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Pong();
    }

}
