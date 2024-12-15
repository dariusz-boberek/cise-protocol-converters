
package eu.cise.ivef.generated;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}PosReport" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}StaticData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}Voyage" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:http://www.ivef.org/XMLSchema/IVEF/0.1.5}TaggedItem" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "posReport",
    "staticData",
    "voyage",
    "taggedItem"
})
@XmlRootElement(name = "VesselData")
public class VesselData implements Cloneable, CopyTo2, Equals2, HashCode2, ToString2
{

    @XmlElement(name = "PosReport")
    protected PosReport posReport;
    @XmlElement(name = "StaticData")
    protected List<StaticData> staticData;
    @XmlElement(name = "Voyage")
    protected List<Voyage> voyage;
    @XmlElement(name = "TaggedItem")
    protected List<TaggedItem> taggedItem;

    /**
     * Gets the value of the posReport property.
     * 
     * @return
     *     possible object is
     *     {@link PosReport }
     *     
     */
    public PosReport getPosReport() {
        return posReport;
    }

    /**
     * Sets the value of the posReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link PosReport }
     *     
     */
    public void setPosReport(PosReport value) {
        this.posReport = value;
    }

    /**
     * Gets the value of the staticData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staticData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaticData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaticData }
     * 
     * 
     */
    public List<StaticData> getStaticData() {
        if (staticData == null) {
            staticData = new ArrayList<StaticData>();
        }
        return this.staticData;
    }

    /**
     * Gets the value of the voyage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voyage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVoyage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Voyage }
     * 
     * 
     */
    public List<Voyage> getVoyage() {
        if (voyage == null) {
            voyage = new ArrayList<Voyage>();
        }
        return this.voyage;
    }

    /**
     * Gets the value of the taggedItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taggedItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaggedItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaggedItem }
     * 
     * 
     */
    public List<TaggedItem> getTaggedItem() {
        if (taggedItem == null) {
            taggedItem = new ArrayList<TaggedItem>();
        }
        return this.taggedItem;
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
            PosReport thePosReport;
            thePosReport = this.getPosReport();
            strategy.appendField(locator, this, "posReport", buffer, thePosReport, (this.posReport!= null));
        }
        {
            List<StaticData> theStaticData;
            theStaticData = (((this.staticData!= null)&&(!this.staticData.isEmpty()))?this.getStaticData():null);
            strategy.appendField(locator, this, "staticData", buffer, theStaticData, ((this.staticData!= null)&&(!this.staticData.isEmpty())));
        }
        {
            List<Voyage> theVoyage;
            theVoyage = (((this.voyage!= null)&&(!this.voyage.isEmpty()))?this.getVoyage():null);
            strategy.appendField(locator, this, "voyage", buffer, theVoyage, ((this.voyage!= null)&&(!this.voyage.isEmpty())));
        }
        {
            List<TaggedItem> theTaggedItem;
            theTaggedItem = (((this.taggedItem!= null)&&(!this.taggedItem.isEmpty()))?this.getTaggedItem():null);
            strategy.appendField(locator, this, "taggedItem", buffer, theTaggedItem, ((this.taggedItem!= null)&&(!this.taggedItem.isEmpty())));
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
        final VesselData that = ((VesselData) object);
        {
            PosReport lhsPosReport;
            lhsPosReport = this.getPosReport();
            PosReport rhsPosReport;
            rhsPosReport = that.getPosReport();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "posReport", lhsPosReport), LocatorUtils.property(thatLocator, "posReport", rhsPosReport), lhsPosReport, rhsPosReport, (this.posReport!= null), (that.posReport!= null))) {
                return false;
            }
        }
        {
            List<StaticData> lhsStaticData;
            lhsStaticData = (((this.staticData!= null)&&(!this.staticData.isEmpty()))?this.getStaticData():null);
            List<StaticData> rhsStaticData;
            rhsStaticData = (((that.staticData!= null)&&(!that.staticData.isEmpty()))?that.getStaticData():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "staticData", lhsStaticData), LocatorUtils.property(thatLocator, "staticData", rhsStaticData), lhsStaticData, rhsStaticData, ((this.staticData!= null)&&(!this.staticData.isEmpty())), ((that.staticData!= null)&&(!that.staticData.isEmpty())))) {
                return false;
            }
        }
        {
            List<Voyage> lhsVoyage;
            lhsVoyage = (((this.voyage!= null)&&(!this.voyage.isEmpty()))?this.getVoyage():null);
            List<Voyage> rhsVoyage;
            rhsVoyage = (((that.voyage!= null)&&(!that.voyage.isEmpty()))?that.getVoyage():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "voyage", lhsVoyage), LocatorUtils.property(thatLocator, "voyage", rhsVoyage), lhsVoyage, rhsVoyage, ((this.voyage!= null)&&(!this.voyage.isEmpty())), ((that.voyage!= null)&&(!that.voyage.isEmpty())))) {
                return false;
            }
        }
        {
            List<TaggedItem> lhsTaggedItem;
            lhsTaggedItem = (((this.taggedItem!= null)&&(!this.taggedItem.isEmpty()))?this.getTaggedItem():null);
            List<TaggedItem> rhsTaggedItem;
            rhsTaggedItem = (((that.taggedItem!= null)&&(!that.taggedItem.isEmpty()))?that.getTaggedItem():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taggedItem", lhsTaggedItem), LocatorUtils.property(thatLocator, "taggedItem", rhsTaggedItem), lhsTaggedItem, rhsTaggedItem, ((this.taggedItem!= null)&&(!this.taggedItem.isEmpty())), ((that.taggedItem!= null)&&(!that.taggedItem.isEmpty())))) {
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
            PosReport thePosReport;
            thePosReport = this.getPosReport();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posReport", thePosReport), currentHashCode, thePosReport, (this.posReport!= null));
        }
        {
            List<StaticData> theStaticData;
            theStaticData = (((this.staticData!= null)&&(!this.staticData.isEmpty()))?this.getStaticData():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "staticData", theStaticData), currentHashCode, theStaticData, ((this.staticData!= null)&&(!this.staticData.isEmpty())));
        }
        {
            List<Voyage> theVoyage;
            theVoyage = (((this.voyage!= null)&&(!this.voyage.isEmpty()))?this.getVoyage():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "voyage", theVoyage), currentHashCode, theVoyage, ((this.voyage!= null)&&(!this.voyage.isEmpty())));
        }
        {
            List<TaggedItem> theTaggedItem;
            theTaggedItem = (((this.taggedItem!= null)&&(!this.taggedItem.isEmpty()))?this.getTaggedItem():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taggedItem", theTaggedItem), currentHashCode, theTaggedItem, ((this.taggedItem!= null)&&(!this.taggedItem.isEmpty())));
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
        if (draftCopy instanceof VesselData) {
            final VesselData copy = ((VesselData) draftCopy);
            {
                Boolean posReportShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.posReport!= null));
                if (posReportShouldBeCopiedAndSet == Boolean.TRUE) {
                    PosReport sourcePosReport;
                    sourcePosReport = this.getPosReport();
                    PosReport copyPosReport = ((PosReport) strategy.copy(LocatorUtils.property(locator, "posReport", sourcePosReport), sourcePosReport, (this.posReport!= null)));
                    copy.setPosReport(copyPosReport);
                } else {
                    if (posReportShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.posReport = null;
                    }
                }
            }
            {
                Boolean staticDataShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.staticData!= null)&&(!this.staticData.isEmpty())));
                if (staticDataShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<StaticData> sourceStaticData;
                    sourceStaticData = (((this.staticData!= null)&&(!this.staticData.isEmpty()))?this.getStaticData():null);
                    @SuppressWarnings("unchecked")
                    List<StaticData> copyStaticData = ((List<StaticData> ) strategy.copy(LocatorUtils.property(locator, "staticData", sourceStaticData), sourceStaticData, ((this.staticData!= null)&&(!this.staticData.isEmpty()))));
                    copy.staticData = null;
                    if (copyStaticData!= null) {
                        List<StaticData> uniqueStaticDatal = copy.getStaticData();
                        uniqueStaticDatal.addAll(copyStaticData);
                    }
                } else {
                    if (staticDataShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.staticData = null;
                    }
                }
            }
            {
                Boolean voyageShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.voyage!= null)&&(!this.voyage.isEmpty())));
                if (voyageShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<Voyage> sourceVoyage;
                    sourceVoyage = (((this.voyage!= null)&&(!this.voyage.isEmpty()))?this.getVoyage():null);
                    @SuppressWarnings("unchecked")
                    List<Voyage> copyVoyage = ((List<Voyage> ) strategy.copy(LocatorUtils.property(locator, "voyage", sourceVoyage), sourceVoyage, ((this.voyage!= null)&&(!this.voyage.isEmpty()))));
                    copy.voyage = null;
                    if (copyVoyage!= null) {
                        List<Voyage> uniqueVoyagel = copy.getVoyage();
                        uniqueVoyagel.addAll(copyVoyage);
                    }
                } else {
                    if (voyageShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.voyage = null;
                    }
                }
            }
            {
                Boolean taggedItemShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, ((this.taggedItem!= null)&&(!this.taggedItem.isEmpty())));
                if (taggedItemShouldBeCopiedAndSet == Boolean.TRUE) {
                    List<TaggedItem> sourceTaggedItem;
                    sourceTaggedItem = (((this.taggedItem!= null)&&(!this.taggedItem.isEmpty()))?this.getTaggedItem():null);
                    @SuppressWarnings("unchecked")
                    List<TaggedItem> copyTaggedItem = ((List<TaggedItem> ) strategy.copy(LocatorUtils.property(locator, "taggedItem", sourceTaggedItem), sourceTaggedItem, ((this.taggedItem!= null)&&(!this.taggedItem.isEmpty()))));
                    copy.taggedItem = null;
                    if (copyTaggedItem!= null) {
                        List<TaggedItem> uniqueTaggedIteml = copy.getTaggedItem();
                        uniqueTaggedIteml.addAll(copyTaggedItem);
                    }
                } else {
                    if (taggedItemShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.taggedItem = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new VesselData();
    }

}
