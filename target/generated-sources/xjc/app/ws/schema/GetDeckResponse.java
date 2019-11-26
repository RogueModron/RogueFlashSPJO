
package app.ws.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="deck" type="{http://www.rogueflashspjo.foo/soap}deck"/&gt;
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
    "deck"
})
@XmlRootElement(name = "getDeckResponse")
public class GetDeckResponse {

    @XmlElement(required = true)
    protected Deck deck;

    /**
     * Gets the value of the deck property.
     * 
     * @return
     *     possible object is
     *     {@link Deck }
     *     
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Sets the value of the deck property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deck }
     *     
     */
    public void setDeck(Deck value) {
        this.deck = value;
    }

}
