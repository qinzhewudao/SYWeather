package com.weather.sy.syweather.Db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIEW_SPOT".
*/
public class ViewSpotDao extends AbstractDao<ViewSpot, Long> {

    public static final String TABLENAME = "VIEW_SPOT";

    /**
     * Properties of entity ViewSpot.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DirectName = new Property(1, String.class, "directName", false, "DIRECT_NAME");
        public final static Property ViewSpotName = new Property(2, String.class, "viewSpotName", false, "VIEW_SPOT_NAME");
        public final static Property ViewSpotID = new Property(3, String.class, "viewSpotID", false, "VIEW_SPOT_ID");
        public final static Property Love = new Property(4, String.class, "love", false, "LOVE");
    };


    public ViewSpotDao(DaoConfig config) {
        super(config);
    }
    
    public ViewSpotDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIEW_SPOT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DIRECT_NAME\" TEXT NOT NULL ," + // 1: directName
                "\"VIEW_SPOT_NAME\" TEXT NOT NULL ," + // 2: viewSpotName
                "\"VIEW_SPOT_ID\" TEXT NOT NULL ," + // 3: viewSpotID
                "\"LOVE\" TEXT);"); // 4: love
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIEW_SPOT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ViewSpot entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDirectName());
        stmt.bindString(3, entity.getViewSpotName());
        stmt.bindString(4, entity.getViewSpotID());
 
        String love = entity.getLove();
        if (love != null) {
            stmt.bindString(5, love);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ViewSpot readEntity(Cursor cursor, int offset) {
        ViewSpot entity = new ViewSpot( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // directName
            cursor.getString(offset + 2), // viewSpotName
            cursor.getString(offset + 3), // viewSpotID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // love
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ViewSpot entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDirectName(cursor.getString(offset + 1));
        entity.setViewSpotName(cursor.getString(offset + 2));
        entity.setViewSpotID(cursor.getString(offset + 3));
        entity.setLove(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ViewSpot entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ViewSpot entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
