
package eu.cise.ivef.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}Header"/&gt;
 *         &lt;element name="Body"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}LoginRequest"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "body"
})
@XmlRootElement(name = "MSG_LoginRequest")
public class MSGLoginRequest implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(name = "Body", required = true)
    protected MSGLoginRequest.Body body;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link MSGLoginRequest.Body }
     *     
     */
    public MSGLoginRequest.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSGLoginRequest.Body }
     *     
     */
    public void setBody(MSGLoginRequest.Body value) {
        this.body = value;
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
            Header theHeader;
            theHeader = this.getHeader();
            strategy.appendField(locator, this, "header", buffer, theHeader, (this.header!= null));
        }
        {
            MSGLoginRequest.Body theBody;
            theBody = this.getBody();
            strategy.appendField(locator, this, "body", buffer, theBody, (this.body!= null));
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
        final MSGLoginRequest that = ((MSGLoginRequest) object);
        {
            Header lhsHeader;
            lhsHeader = this.getHeader();
            Header rhsHeader;
            rhsHeader = that.getHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "header", lhsHeader), LocatorUtils.property(thatLocator, "header", rhsHeader), lhsHeader, rhsHeader, (this.header!= null), (that.header!= null))) {
                return false;
            }
        }
        {
            MSGLoginRequest.Body lhsBody;
            lhsBody = this.getBody();
            MSGLoginRequest.Body rhsBody;
            rhsBody = that.getBody();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "body", lhsBody), LocatorUtils.property(thatLocator, "body", rhsBody), lhsBody, rhsBody, (this.body!= null), (that.body!= null))) {
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
            Header theHeader;
            theHeader = this.getHeader();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "header", theHeader), currentHashCode, theHeader, (this.header!= null));
        }
        {
            MSGLoginRequest.Body theBody;
            theBody = this.getBody();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "body", theBody), currentHashCode, theBody, (this.body!= null));
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
        if (draftCopy instanceof MSGLoginRequest) {
            final MSGLoginRequest copy = ((MSGLoginRequest) draftCopy);
            {
                Boolean headerShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.header!= null));
                if (headerShouldBeCopiedAndSet == Boolean.TRUE) {
                    Header sourceHeader;
                    sourceHeader = this.getHeader();
                    Header copyHeader = ((Header) strategy.copy(LocatorUtils.property(locator, "header", sourceHeader), sourceHeader, (this.header!= null)));
                    copy.setHeader(copyHeader);
                } else {
                    if (headerShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.header = null;
                    }
                }
            }
            {
                Boolean bodyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.body!= null));
                if (bodyShouldBeCopiedAndSet == Boolean.TRUE) {
                    MSGLoginRequest.Body sourceBody;
                    sourceBody = this.getBody();
                    MSGLoginRequest.Body copyBody = ((MSGLoginRequest.Body) strategy.copy(LocatorUtils.property(locator, "body", sourceBody), sourceBody, (this.body!= null)));
                    copy.setBody(copyBody);
                } else {
                    if (bodyShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.body = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MSGLoginRequest();
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
     *       &lt;sequence&gt;
     *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}LoginRequest"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "loginRequest"
    })
    public static class Body implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlElement(name = "LoginRequest", required = true)
        protected LoginRequest loginRequest;

        /**
         * Gets the value of the loginRequest property.
         * 
         * @return
         *     possible object is
         *     {@link LoginRequest }
         *     
         */
        public LoginRequest getLoginRequest() {
            return loginRequest;
        }

        /**
         * Sets the value of the loginRequest property.
         * 
         * @param value
         *     allowed object is
         *     {@link LoginRequest }
         *     
         */
        public void setLoginRequest(LoginRequest value) {
            this.loginRequest = value;
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
                LoginRequest theLoginRequest;
                theLoginRequest = this.getLoginRequest();
                strategy.appendField(locator, this, "loginRequest", buffer, theLoginRequest, (this.loginRequest!= null));
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
            final MSGLoginRequest.Body that = ((MSGLoginRequest.Body) object);
            {
                LoginRequest lhsLoginRequest;
                lhsLoginRequest = this.getLoginRequest();
                LoginRequest rhsLoginRequest;
                rhsLoginRequest = that.getLoginRequest();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "loginRequest", lhsLoginRequest), LocatorUtils.property(thatLocator, "loginRequest", rhsLoginRequest), lhsLoginRequest, rhsLoginRequest, (this.loginRequest!= null), (that.loginRequest!= null))) {
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
                LoginRequest theLoginRequest;
                theLoginRequest = this.getLoginRequest();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "loginRequest", theLoginRequest), currentHashCode, theLoginRequest, (this.loginRequest!= null));
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
            if (draftCopy instanceof MSGLoginRequest.Body) {
                final MSGLoginRequest.Body copy = ((MSGLoginRequest.Body) draftCopy);
                {
                    Boolean loginRequestShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.loginRequest!= null));
                    if (loginRequestShouldBeCopiedAndSet == Boolean.TRUE) {
                        LoginRequest sourceLoginRequest;
                        sourceLoginRequest = this.getLoginRequest();
                        LoginRequest copyLoginRequest = ((LoginRequest) strategy.copy(LocatorUtils.property(locator, "loginRequest", sourceLoginRequest), sourceLoginRequest, (this.loginRequest!= null)));
                        copy.setLoginRequest(copyLoginRequest);
                    } else {
                        if (loginRequestShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.loginRequest = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new MSGLoginRequest.Body();
        }

    }

}
