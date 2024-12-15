
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
 *         &lt;element name="Area" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}Pos" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Transmission"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Type" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                       &lt;enumeration value="1"/&gt;
 *                       &lt;enumeration value="2"/&gt;
 *                       &lt;enumeration value="3"/&gt;
 *                       &lt;enumeration value="4"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="Period" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Item" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Element" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                       &lt;enumeration value="1"/&gt;
 *                       &lt;enumeration value="2"/&gt;
 *                       &lt;enumeration value="3"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="Field" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Object" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="FileName" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
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
    "area",
    "transmission",
    "item",
    "object"
})
@XmlRootElement(name = "ServiceRequest")
public class ServiceRequest implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlElement(name = "Area")
    protected List<ServiceRequest.Area> area;
    @XmlElement(name = "Transmission", required = true)
    protected ServiceRequest.Transmission transmission;
    @XmlElement(name = "Item", required = true)
    protected List<ServiceRequest.Item> item;
    @XmlElement(name = "Object", required = true)
    protected List<ServiceRequest.Object> object;

    /**
     * Gets the value of the area property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the area property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequest.Area }
     * 
     * 
     */
    public List<ServiceRequest.Area> getArea() {
        if (area == null) {
            area = new ArrayList<ServiceRequest.Area>();
        }
        return this.area;
    }

    /**
     * Gets the value of the transmission property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequest.Transmission }
     *     
     */
    public ServiceRequest.Transmission getTransmission() {
        return transmission;
    }

    /**
     * Sets the value of the transmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequest.Transmission }
     *     
     */
    public void setTransmission(ServiceRequest.Transmission value) {
        this.transmission = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequest.Item }
     * 
     * 
     */
    public List<ServiceRequest.Item> getItem() {
        if (item == null) {
            item = new ArrayList<ServiceRequest.Item>();
        }
        return this.item;
    }

    /**
     * Gets the value of the object property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the object property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRequest.Object }
     * 
     * 
     */
    public List<ServiceRequest.Object> getObject() {
        if (object == null) {
            object = new ArrayList<ServiceRequest.Object>();
        }
        return this.object;
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
            List<ServiceRequest.Area> theArea;
            theArea = (((this.area!= null)&&(!this.area.isEmpty()))?this.getArea():null);
            strategy.appendField(locator, this, "area", buffer, theArea, ((this.area!= null)&&(!this.area.isEmpty())));
        }
        {
            ServiceRequest.Transmission theTransmission;
            theTransmission = this.getTransmission();
            strategy.appendField(locator, this, "transmission", buffer, theTransmission, (this.transmission!= null));
        }
        {
            List<ServiceRequest.Item> theItem;
            theItem = (((this.item!= null)&&(!this.item.isEmpty()))?this.getItem():null);
            strategy.appendField(locator, this, "item", buffer, theItem, ((this.item!= null)&&(!this.item.isEmpty())));
        }
        {
            List<ServiceRequest.Object> theObject;
            theObject = (((this.object!= null)&&(!this.object.isEmpty()))?this.getObject():null);
            strategy.appendField(locator, this, "object", buffer, theObject, ((this.object!= null)&&(!this.object.isEmpty())));
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceRequest that = ((ServiceRequest) object);
        {
            List<ServiceRequest.Area> lhsArea;
            lhsArea = (((this.area!= null)&&(!this.area.isEmpty()))?this.getArea():null);
            List<ServiceRequest.Area> rhsArea;
            rhsArea = (((that.area!= null)&&(!that.area.isEmpty()))?that.getArea():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "area", lhsArea), LocatorUtils.property(thatLocator, "area", rhsArea), lhsArea, rhsArea, ((this.area!= null)&&(!this.area.isEmpty())), ((that.area!= null)&&(!that.area.isEmpty())))) {
                return false;
            }
        }
        {
            ServiceRequest.Transmission lhsTransmission;
            lhsTransmission = this.getTransmission();
            ServiceRequest.Transmission rhsTransmission;
            rhsTransmission = that.getTransmission();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transmission", lhsTransmission), LocatorUtils.property(thatLocator, "transmission", rhsTransmission), lhsTransmission, rhsTransmission, (this.transmission!= null), (that.transmission!= null))) {
                return false;
            }
        }
        {
            List<ServiceRequest.Item> lhsItem;
            lhsItem = (((this.item!= null)&&(!this.item.isEmpty()))?this.getItem():null);
            List<ServiceRequest.Item> rhsItem;
            rhsItem = (((that.item!= null)&&(!that.item.isEmpty()))?that.getItem():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "item", lhsItem), LocatorUtils.property(thatLocator, "item", rhsItem), lhsItem, rhsItem, ((this.item!= null)&&(!this.item.isEmpty())), ((that.item!= null)&&(!that.item.isEmpty())))) {
                return false;
            }
        }
        {
            List<ServiceRequest.Object> lhsObject;
            lhsObject = (((this.object!= null)&&(!this.object.isEmpty()))?this.getObject():null);
            List<ServiceRequest.Object> rhsObject;
            rhsObject = (((that.object!= null)&&(!that.object.isEmpty()))?that.getObject():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "object", lhsObject), LocatorUtils.property(thatLocator, "object", rhsObject), lhsObject, rhsObject, ((this.object!= null)&&(!this.object.isEmpty())), ((that.object!= null)&&(!that.object.isEmpty())))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(java.lang.Object object) {
        final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
        int currentHashCode = 1;
        {
            List<ServiceRequest.Area> theArea;
            theArea = (((this.area!= null)&&(!this.area.isEmpty()))?this.getArea():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "area", theArea), currentHashCode, theArea, ((this.area!= null)&&(!this.area.isEmpty())));
        }
        {
            ServiceRequest.Transmission theTransmission;
            theTransmission = this.getTransmission();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "transmission", theTransmission), currentHashCode, theTransmission, (this.transmission!= null));
        }
        {
            List<ServiceRequest.Item> theItem;
            theItem = (((this.item!= null)&&(!this.item.isEmpty()))?this.getItem():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "item", theItem), currentHashCode, theItem, ((this.item!= null)&&(!this.item.isEmpty())));
        }
        {
            List<ServiceRequest.Object> theObject;
            theObject = (((this.object!= null)&&(!this.object.isEmpty()))?this.getObject():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "object", theObject), currentHashCode, theObject, ((this.object!= null)&&(!this.object.isEmpty())));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE2;
        return this.hashCode(null, strategy);
    }

    public java.lang.Object clone() {
        return copyTo(createNewInstance());
    }

    public java.lang.Object copyTo(java.lang.Object target) {
        final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE2;
        return copyTo(null, target, strategy);
    }

    public java.lang.Object copyTo(ObjectLocator locator, java.lang.Object target, CopyStrategy2 strategy) {
        final java.lang.Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof ServiceRequest) {
            final ServiceRequest copy = ((ServiceRequest) draftCopy);
            {
                Boolean areaShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.area!= null)&&(!this.area.isEmpty())));
                if (areaShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<ServiceRequest.Area> sourceArea;
                    sourceArea = (((this.area!= null)&&(!this.area.isEmpty()))?this.getArea():null);
                    @SuppressWarnings("unchecked")
                    List<ServiceRequest.Area> copyArea = ((List<ServiceRequest.Area> ) strategy.copy(LocatorUtils.property(locator, "area", sourceArea), sourceArea, ((this.area!= null)&&(!this.area.isEmpty()))));
                    copy.area = null;
                    if (copyArea!= null) {
                        List<ServiceRequest.Area> uniqueAreal = copy.getArea();
                        uniqueAreal.addAll(copyArea);
                    }
                } else {
                    if (areaShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.area = null;
                    }
                }
            }
            {
                Boolean transmissionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.transmission!= null));
                if (transmissionShouldBeCopiedAndSet == Boolean.TRUE) {
                    ServiceRequest.Transmission sourceTransmission;
                    sourceTransmission = this.getTransmission();
                    ServiceRequest.Transmission copyTransmission = ((ServiceRequest.Transmission) strategy.copy(LocatorUtils.property(locator, "transmission", sourceTransmission), sourceTransmission, (this.transmission!= null)));
                    copy.setTransmission(copyTransmission);
                } else {
                    if (transmissionShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.transmission = null;
                    }
                }
            }
            {
                Boolean itemShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.item!= null)&&(!this.item.isEmpty())));
                if (itemShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<ServiceRequest.Item> sourceItem;
                    sourceItem = (((this.item!= null)&&(!this.item.isEmpty()))?this.getItem():null);
                    @SuppressWarnings("unchecked")
                    List<ServiceRequest.Item> copyItem = ((List<ServiceRequest.Item> ) strategy.copy(LocatorUtils.property(locator, "item", sourceItem), sourceItem, ((this.item!= null)&&(!this.item.isEmpty()))));
                    copy.item = null;
                    if (copyItem!= null) {
                        List<ServiceRequest.Item> uniqueIteml = copy.getItem();
                        uniqueIteml.addAll(copyItem);
                    }
                } else {
                    if (itemShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.item = null;
                    }
                }
            }
            {
                Boolean objectShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.object!= null)&&(!this.object.isEmpty())));
                if (objectShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<ServiceRequest.Object> sourceObject;
                    sourceObject = (((this.object!= null)&&(!this.object.isEmpty()))?this.getObject():null);
                    @SuppressWarnings("unchecked")
                    List<ServiceRequest.Object> copyObject = ((List<ServiceRequest.Object> ) strategy.copy(LocatorUtils.property(locator, "object", sourceObject), sourceObject, ((this.object!= null)&&(!this.object.isEmpty()))));
                    copy.object = null;
                    if (copyObject!= null) {
                        List<ServiceRequest.Object> uniqueObjectl = copy.getObject();
                        uniqueObjectl.addAll(copyObject);
                    }
                } else {
                    if (objectShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.object = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public java.lang.Object createNewInstance() {
        return new ServiceRequest();
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
     *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}Pos" maxOccurs="unbounded"/&gt;
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
        "pos"
    })
    public static class Area implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlElement(name = "Pos", required = true)
        protected List<Pos> pos;

        /**
         * Gets the value of the pos property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pos property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPos().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Pos }
         * 
         * 
         */
        public List<Pos> getPos() {
            if (pos == null) {
                pos = new ArrayList<Pos>();
            }
            return this.pos;
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
                List<Pos> thePos;
                thePos = (((this.pos!= null)&&(!this.pos.isEmpty()))?this.getPos():null);
                strategy.appendField(locator, this, "pos", buffer, thePos, ((this.pos!= null)&&(!this.pos.isEmpty())));
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy2 strategy) {
            if ((object == null)||(this.getClass()!= object.getClass())) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ServiceRequest.Area that = ((ServiceRequest.Area) object);
            {
                List<Pos> lhsPos;
                lhsPos = (((this.pos!= null)&&(!this.pos.isEmpty()))?this.getPos():null);
                List<Pos> rhsPos;
                rhsPos = (((that.pos!= null)&&(!that.pos.isEmpty()))?that.getPos():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos, ((this.pos!= null)&&(!this.pos.isEmpty())), ((that.pos!= null)&&(!that.pos.isEmpty())))) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(java.lang.Object object) {
            final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
            return equals(null, null, object, strategy);
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
            int currentHashCode = 1;
            {
                List<Pos> thePos;
                thePos = (((this.pos!= null)&&(!this.pos.isEmpty()))?this.getPos():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos, ((this.pos!= null)&&(!this.pos.isEmpty())));
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE2;
            return this.hashCode(null, strategy);
        }

        public java.lang.Object clone() {
            return copyTo(createNewInstance());
        }

        public java.lang.Object copyTo(java.lang.Object target) {
            final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE2;
            return copyTo(null, target, strategy);
        }

        public java.lang.Object copyTo(ObjectLocator locator, java.lang.Object target, CopyStrategy2 strategy) {
            final java.lang.Object draftCopy = ((target == null)?createNewInstance():target);
            if (draftCopy instanceof ServiceRequest.Area) {
                final ServiceRequest.Area copy = ((ServiceRequest.Area) draftCopy);
                {
                    Boolean posShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.pos!= null)&&(!this.pos.isEmpty())));
                    if (posShouldBeCopiedAndSet == Boolean.TRUE) {
                        List<Pos> sourcePos;
                        sourcePos = (((this.pos!= null)&&(!this.pos.isEmpty()))?this.getPos():null);
                        @SuppressWarnings("unchecked")
                        List<Pos> copyPos = ((List<Pos> ) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos, ((this.pos!= null)&&(!this.pos.isEmpty()))));
                        copy.pos = null;
                        if (copyPos!= null) {
                            List<Pos> uniquePosl = copy.getPos();
                            uniquePosl.addAll(copyPos);
                        }
                    } else {
                        if (posShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.pos = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public java.lang.Object createNewInstance() {
            return new ServiceRequest.Area();
        }

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
     *       &lt;attribute name="Element" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *             &lt;enumeration value="1"/&gt;
     *             &lt;enumeration value="2"/&gt;
     *             &lt;enumeration value="3"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="Field" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Item implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlAttribute(name = "Element", required = true)
        protected BigInteger element;
        @XmlAttribute(name = "Field", required = true)
        protected String field;

        /**
         * Gets the value of the element property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getElement() {
            return element;
        }

        /**
         * Sets the value of the element property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setElement(BigInteger value) {
            this.element = value;
        }

        /**
         * Gets the value of the field property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getField() {
            return field;
        }

        /**
         * Sets the value of the field property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setField(String value) {
            this.field = value;
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
                BigInteger theElement;
                theElement = this.getElement();
                strategy.appendField(locator, this, "element", buffer, theElement, (this.element!= null));
            }
            {
                String theField;
                theField = this.getField();
                strategy.appendField(locator, this, "field", buffer, theField, (this.field!= null));
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy2 strategy) {
            if ((object == null)||(this.getClass()!= object.getClass())) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ServiceRequest.Item that = ((ServiceRequest.Item) object);
            {
                BigInteger lhsElement;
                lhsElement = this.getElement();
                BigInteger rhsElement;
                rhsElement = that.getElement();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "element", lhsElement), LocatorUtils.property(thatLocator, "element", rhsElement), lhsElement, rhsElement, (this.element!= null), (that.element!= null))) {
                    return false;
                }
            }
            {
                String lhsField;
                lhsField = this.getField();
                String rhsField;
                rhsField = that.getField();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "field", lhsField), LocatorUtils.property(thatLocator, "field", rhsField), lhsField, rhsField, (this.field!= null), (that.field!= null))) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(java.lang.Object object) {
            final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
            return equals(null, null, object, strategy);
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
            int currentHashCode = 1;
            {
                BigInteger theElement;
                theElement = this.getElement();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "element", theElement), currentHashCode, theElement, (this.element!= null));
            }
            {
                String theField;
                theField = this.getField();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "field", theField), currentHashCode, theField, (this.field!= null));
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE2;
            return this.hashCode(null, strategy);
        }

        public java.lang.Object clone() {
            return copyTo(createNewInstance());
        }

        public java.lang.Object copyTo(java.lang.Object target) {
            final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE2;
            return copyTo(null, target, strategy);
        }

        public java.lang.Object copyTo(ObjectLocator locator, java.lang.Object target, CopyStrategy2 strategy) {
            final java.lang.Object draftCopy = ((target == null)?createNewInstance():target);
            if (draftCopy instanceof ServiceRequest.Item) {
                final ServiceRequest.Item copy = ((ServiceRequest.Item) draftCopy);
                {
                    Boolean elementShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.element!= null));
                    if (elementShouldBeCopiedAndSet == Boolean.TRUE) {
                        BigInteger sourceElement;
                        sourceElement = this.getElement();
                        BigInteger copyElement = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "element", sourceElement), sourceElement, (this.element!= null)));
                        copy.setElement(copyElement);
                    } else {
                        if (elementShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.element = null;
                        }
                    }
                }
                {
                    Boolean fieldShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.field!= null));
                    if (fieldShouldBeCopiedAndSet == Boolean.TRUE) {
                        String sourceField;
                        sourceField = this.getField();
                        String copyField = ((String) strategy.copy(LocatorUtils.property(locator, "field", sourceField), sourceField, (this.field!= null)));
                        copy.setField(copyField);
                    } else {
                        if (fieldShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.field = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public java.lang.Object createNewInstance() {
            return new ServiceRequest.Item();
        }

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
     *       &lt;attribute name="FileName" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Object implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlAttribute(name = "FileName", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String fileName;

        /**
         * Gets the value of the fileName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Sets the value of the fileName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFileName(String value) {
            this.fileName = value;
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
                String theFileName;
                theFileName = this.getFileName();
                strategy.appendField(locator, this, "fileName", buffer, theFileName, (this.fileName!= null));
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy2 strategy) {
            if ((object == null)||(this.getClass()!= object.getClass())) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ServiceRequest.Object that = ((ServiceRequest.Object) object);
            {
                String lhsFileName;
                lhsFileName = this.getFileName();
                String rhsFileName;
                rhsFileName = that.getFileName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "fileName", lhsFileName), LocatorUtils.property(thatLocator, "fileName", rhsFileName), lhsFileName, rhsFileName, (this.fileName!= null), (that.fileName!= null))) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(java.lang.Object object) {
            final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
            return equals(null, null, object, strategy);
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
            int currentHashCode = 1;
            {
                String theFileName;
                theFileName = this.getFileName();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileName", theFileName), currentHashCode, theFileName, (this.fileName!= null));
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE2;
            return this.hashCode(null, strategy);
        }

        public java.lang.Object clone() {
            return copyTo(createNewInstance());
        }

        public java.lang.Object copyTo(java.lang.Object target) {
            final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE2;
            return copyTo(null, target, strategy);
        }

        public java.lang.Object copyTo(ObjectLocator locator, java.lang.Object target, CopyStrategy2 strategy) {
            final java.lang.Object draftCopy = ((target == null)?createNewInstance():target);
            if (draftCopy instanceof ServiceRequest.Object) {
                final ServiceRequest.Object copy = ((ServiceRequest.Object) draftCopy);
                {
                    Boolean fileNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.fileName!= null));
                    if (fileNameShouldBeCopiedAndSet == Boolean.TRUE) {
                        String sourceFileName;
                        sourceFileName = this.getFileName();
                        String copyFileName = ((String) strategy.copy(LocatorUtils.property(locator, "fileName", sourceFileName), sourceFileName, (this.fileName!= null)));
                        copy.setFileName(copyFileName);
                    } else {
                        if (fileNameShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.fileName = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public java.lang.Object createNewInstance() {
            return new ServiceRequest.Object();
        }

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
     *       &lt;attribute name="Type" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *             &lt;enumeration value="1"/&gt;
     *             &lt;enumeration value="2"/&gt;
     *             &lt;enumeration value="3"/&gt;
     *             &lt;enumeration value="4"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="Period" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Transmission implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
    {

        @XmlAttribute(name = "Type", required = true)
        protected BigInteger type;
        @XmlAttribute(name = "Period")
        protected BigDecimal period;

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setType(BigInteger value) {
            this.type = value;
        }

        /**
         * Gets the value of the period property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPeriod() {
            return period;
        }

        /**
         * Sets the value of the period property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPeriod(BigDecimal value) {
            this.period = value;
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
                BigInteger theType;
                theType = this.getType();
                strategy.appendField(locator, this, "type", buffer, theType, (this.type!= null));
            }
            {
                BigDecimal thePeriod;
                thePeriod = this.getPeriod();
                strategy.appendField(locator, this, "period", buffer, thePeriod, (this.period!= null));
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy2 strategy) {
            if ((object == null)||(this.getClass()!= object.getClass())) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ServiceRequest.Transmission that = ((ServiceRequest.Transmission) object);
            {
                BigInteger lhsType;
                lhsType = this.getType();
                BigInteger rhsType;
                rhsType = that.getType();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "type", lhsType), LocatorUtils.property(thatLocator, "type", rhsType), lhsType, rhsType, (this.type!= null), (that.type!= null))) {
                    return false;
                }
            }
            {
                BigDecimal lhsPeriod;
                lhsPeriod = this.getPeriod();
                BigDecimal rhsPeriod;
                rhsPeriod = that.getPeriod();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "period", lhsPeriod), LocatorUtils.property(thatLocator, "period", rhsPeriod), lhsPeriod, rhsPeriod, (this.period!= null), (that.period!= null))) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(java.lang.Object object) {
            final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE2;
            return equals(null, null, object, strategy);
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
            int currentHashCode = 1;
            {
                BigInteger theType;
                theType = this.getType();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "type", theType), currentHashCode, theType, (this.type!= null));
            }
            {
                BigDecimal thePeriod;
                thePeriod = this.getPeriod();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "period", thePeriod), currentHashCode, thePeriod, (this.period!= null));
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE2;
            return this.hashCode(null, strategy);
        }

        public java.lang.Object clone() {
            return copyTo(createNewInstance());
        }

        public java.lang.Object copyTo(java.lang.Object target) {
            final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE2;
            return copyTo(null, target, strategy);
        }

        public java.lang.Object copyTo(ObjectLocator locator, java.lang.Object target, CopyStrategy2 strategy) {
            final java.lang.Object draftCopy = ((target == null)?createNewInstance():target);
            if (draftCopy instanceof ServiceRequest.Transmission) {
                final ServiceRequest.Transmission copy = ((ServiceRequest.Transmission) draftCopy);
                {
                    Boolean typeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.type!= null));
                    if (typeShouldBeCopiedAndSet == Boolean.TRUE) {
                        BigInteger sourceType;
                        sourceType = this.getType();
                        BigInteger copyType = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "type", sourceType), sourceType, (this.type!= null)));
                        copy.setType(copyType);
                    } else {
                        if (typeShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.type = null;
                        }
                    }
                }
                {
                    Boolean periodShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.period!= null));
                    if (periodShouldBeCopiedAndSet == Boolean.TRUE) {
                        BigDecimal sourcePeriod;
                        sourcePeriod = this.getPeriod();
                        BigDecimal copyPeriod = ((BigDecimal) strategy.copy(LocatorUtils.property(locator, "period", sourcePeriod), sourcePeriod, (this.period!= null)));
                        copy.setPeriod(copyPeriod);
                    } else {
                        if (periodShouldBeCopiedAndSet == Boolean.FALSE) {
                            copy.period = null;
                        }
                    }
                }
            }
            return draftCopy;
        }

        public java.lang.Object createNewInstance() {
            return new ServiceRequest.Transmission();
        }

    }

}
