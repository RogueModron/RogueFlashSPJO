/*
 * This file is generated by jOOQ.
 */
package app.db.tables.records;


import app.db.tables.Cards;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CardsRecord extends UpdatableRecordImpl<CardsRecord> implements Record7<Integer, Integer, Integer, String, String, String, String> {

    private static final long serialVersionUID = 1976480419;

    /**
     * Setter for <code>public.cards.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.cards.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.cards.version</code>.
     */
    public void setVersion(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.cards.version</code>.
     */
    public Integer getVersion() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.cards.deck_id</code>.
     */
    public void setDeckId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.cards.deck_id</code>.
     */
    public Integer getDeckId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.cards.side_a</code>.
     */
    public void setSideA(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.cards.side_a</code>.
     */
    public String getSideA() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.cards.side_b</code>.
     */
    public void setSideB(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.cards.side_b</code>.
     */
    public String getSideB() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.cards.notes</code>.
     */
    public void setNotes(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.cards.notes</code>.
     */
    public String getNotes() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.cards.tags</code>.
     */
    public void setTags(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.cards.tags</code>.
     */
    public String getTags() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, Integer, String, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, Integer, Integer, String, String, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Cards.CARDS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Cards.CARDS.VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Cards.CARDS.DECK_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Cards.CARDS.SIDE_A;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Cards.CARDS.SIDE_B;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Cards.CARDS.NOTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Cards.CARDS.TAGS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getDeckId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getSideA();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getSideB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getNotes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getTags();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getDeckId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSideA();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getSideB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getNotes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getTags();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value2(Integer value) {
        setVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value3(Integer value) {
        setDeckId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value4(String value) {
        setSideA(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value5(String value) {
        setSideB(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value6(String value) {
        setNotes(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord value7(String value) {
        setTags(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsRecord values(Integer value1, Integer value2, Integer value3, String value4, String value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CardsRecord
     */
    public CardsRecord() {
        super(Cards.CARDS);
    }

    /**
     * Create a detached, initialised CardsRecord
     */
    public CardsRecord(Integer id, Integer version, Integer deckId, String sideA, String sideB, String notes, String tags) {
        super(Cards.CARDS);

        set(0, id);
        set(1, version);
        set(2, deckId);
        set(3, sideA);
        set(4, sideB);
        set(5, notes);
        set(6, tags);
    }
}
