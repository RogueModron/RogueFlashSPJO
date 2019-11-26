/*
 * This file is generated by jOOQ.
 */
package app.db.tables;


import app.db.Indexes;
import app.db.Keys;
import app.db.Public;
import app.db.tables.records.CardsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Cards extends TableImpl<CardsRecord> {

    private static final long serialVersionUID = -898896900;

    /**
     * The reference instance of <code>public.cards</code>
     */
    public static final Cards CARDS = new Cards();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CardsRecord> getRecordType() {
        return CardsRecord.class;
    }

    /**
     * The column <code>public.cards.id</code>.
     */
    public final TableField<CardsRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('cards_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.cards.version</code>.
     */
    public final TableField<CardsRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.cards.deck_id</code>.
     */
    public final TableField<CardsRecord, Integer> DECK_ID = createField("deck_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.cards.side_a</code>.
     */
    public final TableField<CardsRecord, String> SIDE_A = createField("side_a", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.cards.side_b</code>.
     */
    public final TableField<CardsRecord, String> SIDE_B = createField("side_b", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.cards.notes</code>.
     */
    public final TableField<CardsRecord, String> NOTES = createField("notes", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.cards.tags</code>.
     */
    public final TableField<CardsRecord, String> TAGS = createField("tags", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>public.cards</code> table reference
     */
    public Cards() {
        this(DSL.name("cards"), null);
    }

    /**
     * Create an aliased <code>public.cards</code> table reference
     */
    public Cards(String alias) {
        this(DSL.name(alias), CARDS);
    }

    /**
     * Create an aliased <code>public.cards</code> table reference
     */
    public Cards(Name alias) {
        this(alias, CARDS);
    }

    private Cards(Name alias, Table<CardsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Cards(Name alias, Table<CardsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Cards(Table<O> child, ForeignKey<O, CardsRecord> key) {
        super(child, key, CARDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CARDS_PKEY, Indexes.FKI_CARDS_DECKS_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CardsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CARDS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CardsRecord> getPrimaryKey() {
        return Keys.CARDS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CardsRecord>> getKeys() {
        return Arrays.<UniqueKey<CardsRecord>>asList(Keys.CARDS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CardsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CardsRecord, ?>>asList(Keys.CARDS__CARDS_DECKS_FK);
    }

    public Decks decks() {
        return new Decks(this, Keys.CARDS__CARDS_DECKS_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cards as(String alias) {
        return new Cards(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cards as(Name alias) {
        return new Cards(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Cards rename(String name) {
        return new Cards(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Cards rename(Name name) {
        return new Cards(name, null);
    }
}