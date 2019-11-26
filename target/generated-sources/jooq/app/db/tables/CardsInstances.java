/*
 * This file is generated by jOOQ.
 */
package app.db.tables;


import app.db.Indexes;
import app.db.Keys;
import app.db.Public;
import app.db.tables.records.CardsInstancesRecord;

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
public class CardsInstances extends TableImpl<CardsInstancesRecord> {

    private static final long serialVersionUID = 1836342163;

    /**
     * The reference instance of <code>public.cards_instances</code>
     */
    public static final CardsInstances CARDS_INSTANCES = new CardsInstances();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CardsInstancesRecord> getRecordType() {
        return CardsInstancesRecord.class;
    }

    /**
     * The column <code>public.cards_instances.id</code>.
     */
    public final TableField<CardsInstancesRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('cards_instances_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.cards_instances.card_id</code>.
     */
    public final TableField<CardsInstancesRecord, Integer> CARD_ID = createField("card_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.cards_instances.side_a_to_b</code>.
     */
    public final TableField<CardsInstancesRecord, Boolean> SIDE_A_TO_B = createField("side_a_to_b", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.cards_instances.disabled</code>.
     */
    public final TableField<CardsInstancesRecord, Boolean> DISABLED = createField("disabled", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * Create a <code>public.cards_instances</code> table reference
     */
    public CardsInstances() {
        this(DSL.name("cards_instances"), null);
    }

    /**
     * Create an aliased <code>public.cards_instances</code> table reference
     */
    public CardsInstances(String alias) {
        this(DSL.name(alias), CARDS_INSTANCES);
    }

    /**
     * Create an aliased <code>public.cards_instances</code> table reference
     */
    public CardsInstances(Name alias) {
        this(alias, CARDS_INSTANCES);
    }

    private CardsInstances(Name alias, Table<CardsInstancesRecord> aliased) {
        this(alias, aliased, null);
    }

    private CardsInstances(Name alias, Table<CardsInstancesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> CardsInstances(Table<O> child, ForeignKey<O, CardsInstancesRecord> key) {
        super(child, key, CARDS_INSTANCES);
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
        return Arrays.<Index>asList(Indexes.CARD_INSTANCES_CARD_SIDE_U, Indexes.CARDS_INSTANCES_PKEY, Indexes.FKI_CARDS_INSTANCES_CARDS_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CardsInstancesRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CARDS_INSTANCES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CardsInstancesRecord> getPrimaryKey() {
        return Keys.CARDS_INSTANCES_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CardsInstancesRecord>> getKeys() {
        return Arrays.<UniqueKey<CardsInstancesRecord>>asList(Keys.CARDS_INSTANCES_PKEY, Keys.CARD_INSTANCES_CARD_SIDE_U);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CardsInstancesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CardsInstancesRecord, ?>>asList(Keys.CARDS_INSTANCES__CARDS_INSTANCES_CARDS_FK);
    }

    public Cards cards() {
        return new Cards(this, Keys.CARDS_INSTANCES__CARDS_INSTANCES_CARDS_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsInstances as(String alias) {
        return new CardsInstances(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsInstances as(Name alias) {
        return new CardsInstances(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CardsInstances rename(String name) {
        return new CardsInstances(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CardsInstances rename(Name name) {
        return new CardsInstances(name, null);
    }
}