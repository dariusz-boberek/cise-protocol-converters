
package eu.cise.ivef.generated;

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
 *       &lt;attribute name="MsgId" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;maxLength value="36"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Result" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;enumeration value="1"/&gt;
 *             &lt;enumeration value="2"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Reason"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;maxLength value="256"/&gt;
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
@XmlRootElement(name = "LoginResponse")
public class LoginResponse implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlAttribute(name = "MsgId", required = true)
    protected String msgId;
    @XmlAttribute(name = "Result", required = true)
    protected BigInteger result;
    @XmlAttribute(name = "Reason")
    protected String reason;

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
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResult(BigInteger value) {
        this.result = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
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
            String theMsgId;
            theMsgId = this.getMsgId();
            strategy.appendField(locator, this, "msgId", buffer, theMsgId, (this.msgId!= null));
        }
        {
            BigInteger theResult;
            theResult = this.getResult();
            strategy.appendField(locator, this, "result", buffer, theResult, (this.result!= null));
        }
        {
            String theReason;
            theReason = this.getReason();
            strategy.appendField(locator, this, "reason", buffer, theReason, (this.reason!= null));
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
        final LoginResponse that = ((LoginResponse) object);
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
            BigInteger lhsResult;
            lhsResult = this.getResult();
            BigInteger rhsResult;
            rhsResult = that.getResult();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "result", lhsResult), LocatorUtils.property(thatLocator, "result", rhsResult), lhsResult, rhsResult, (this.result!= null), (that.result!= null))) {
                return false;
            }
        }
        {
            String lhsReason;
            lhsReason = this.getReason();
            String rhsReason;
            rhsReason = that.getReason();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "reason", lhsReason), LocatorUtils.property(thatLocator, "reason", rhsReason), lhsReason, rhsReason, (this.reason!= null), (that.reason!= null))) {
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
            String theMsgId;
            theMsgId = this.getMsgId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "msgId", theMsgId), currentHashCode, theMsgId, (this.msgId!= null));
        }
        {
            BigInteger theResult;
            theResult = this.getResult();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "result", theResult), currentHashCode, theResult, (this.result!= null));
        }
        {
            String theReason;
            theReason = this.getReason();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reason", theReason), currentHashCode, theReason, (this.reason!= null));
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
        if (draftCopy instanceof LoginResponse) {
            final LoginResponse copy = ((LoginResponse) draftCopy);
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
                Boolean resultShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.result!= null));
                if (resultShouldBeCopiedAndSet == Boolean.TRUE) {
                    BigInteger sourceResult;
                    sourceResult = this.getResult();
                    BigInteger copyResult = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "result", sourceResult), sourceResult, (this.result!= null)));
                    copy.setResult(copyResult);
                } else {
                    if (resultShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.result = null;
                    }
                }
            }
            {
                Boolean reasonShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.reason!= null));
                if (reasonShouldBeCopiedAndSet == Boolean.TRUE) {
                    String sourceReason;
                    sourceReason = this.getReason();
                    String copyReason = ((String) strategy.copy(LocatorUtils.property(locator, "reason", sourceReason), sourceReason, (this.reason!= null)));
                    copy.setReason(copyReason);
                } else {
                    if (reasonShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.reason = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new LoginResponse();
    }

}
