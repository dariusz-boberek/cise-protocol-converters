
package eu.cise.ivef.generated;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Double>
{


    public Double unmarshal(String value) {
        return (eu.cise.ivef.DoubleNaNAdapter.parse(value));
    }

    public String marshal(Double value) {
        return (eu.cise.ivef.DoubleNaNAdapter.print(value));
    }

}
