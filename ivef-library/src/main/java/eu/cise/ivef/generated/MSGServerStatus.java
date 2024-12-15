
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
 *                   &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}ServerStatus"/&gt;
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
@XmlRootElement(name = "MSG_ServerStatus")
public class MSGServerStatus implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(name = "Body", required = true)
    protected MSGServerStatus.Body body;

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
     *     {@link MSGServerStatus.Body }
     *     
     */
    public MSGServerStatus.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSGServerStatus.Body }
     *     
     */
    public void setBody(MSGServerStatus.Body value) {
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
            MSGServerStatus.Body theBody;
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
        final MSGServerStatus that = ((MSGServerStatus) object);
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
            MSGServerStatus.Body lhsBody;
            lhsBody = this.getBody();
            MSGServerStatus.Body rhsBody;
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
            MSGServerStatus.Body theBody;
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
        if (draftCopy instanceof MSGServerStatus) {
            final MSGServerStatus copy = ((MSGServerStatus) draftCopy);
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
                    MSGServerStatus.Body sourceBody;
                    sourceBody = this.getBody();
                    MSGServerStatus.Body copyBody = ((MSGServerStatus.Body) strategy.copy(LocatorUtils.property(locator, "body", sourceBody), sourceBody, (this.body!= null)));
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
        return new MSGServerStatus();
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
     *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}ServerStatus"/&gt;
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
        "serverStatus"
    })
    public static class Body implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlElement(name = "ServerStatus", required = true)
        protected ServerStatus serverStatus;

        /**
         * Gets the value of the serverStatus property.
         * 
         * @return
         *     possible object is
         *     {@link ServerStatus }
         *     
         */
        public ServerStatus getServerStatus() {
            return serverStatus;
        }

        /**
         * Sets the value of the serverStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link ServerStatus }
         *     
         */
        public void setServerStatus(ServerStatus value) {
            this.serverStatus = value;
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
                ServerStatus theServerStatus;
                theServerStatus = this.getServerStatus();
                strategy.appendField(locator, this, "serverStatus", buffer, theServerStatus, (this.serverStatus!= null));
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
            final MSGServerStatus.Body that = ((MSGServerStatus.Body) object);
            {
                ServerStatus lhsServerStatus;
                lhsServerStatus = this.getServerStatus();
                ServerStatus rhsServerStatus;
                rhsServerStatus = that.getServerStatus();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "serverStatus", lhsServerStatus), LocatorUtils.property(thatLocator, "serverStatus", rhsServerStatus), lhsServerStatus, rhsServerStatus, (this.serverStatus!= null), (that.serverStatus!= null))) {
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
                ServerStatus theServerStatus;
                theServerStatus = this.getServerStatus();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serverStatus", theServerStatus), currentHashCode, theServerStatus, (this.serverStatus!= null));
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
            if (draftCopy instanceof MSGServerStatus.Body) {
                final MSGServerStatus.Body copy = ((MSGServerStatus.Body) draftCopy);
                {
                    Boolean serverStatusShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.serverStatus!= null));
                    if (serverStatusShouldBeCopiedAndSet == Boolean.TRUE) {
                        ServerStatus sourceServerStatus;
                        sourceServerStatus = this.getServerStatus();
                        ServerStatus copyServerStatus = ((ServerStatus) strategy.copy(LocatorUtils.property(locator, "serverStatus", sourceServerStatus), sourceServerStatus, (this.serverStatus!= null)));
                        copy.setServerStatus(copyServerStatus);
                    } else {
                        if (serverStatusShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.serverStatus = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new MSGServerStatus.Body();
        }

    }

}
