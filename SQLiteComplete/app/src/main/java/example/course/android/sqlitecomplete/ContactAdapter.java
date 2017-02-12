package example.course.android.sqlitecomplete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactAdapter {

    private Context context;
    private SQLiteHelper helper;
    private SQLiteDatabase db;
    // CTor
public ContactAdapter(Context context) {
    // on récupère le context: le CTor de la classe SQLiteHelper le réclame!
    this.context=context;
    helper=new SQLiteHelper(context);
    db=helper.getWritableDatabase();
}// CTor

    public long insert(String name, String address){
        ContentValues cv=new ContentValues();
        cv.put(helper.NAME, name);
        cv.put(helper.ADDRESS, address);
        long id=db.insert(helper.TABLE_NAME,null, cv);
        return id;
    }

    public Cursor getContacts_rawQuery() {

        String sql="SELECT * FROM "+helper.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql, null);
        return cursor;
    }

    public String getContacts_query(){

        String[] columns={helper.UID, helper.NAME, helper.ADDRESS};
        StringBuffer buffer=new StringBuffer();
        Cursor c=db.query(helper.TABLE_NAME, columns, null, null, null, null, null, null);
        if(c.moveToFirst()){
            do{
                int uid=c.getInt(c.getColumnIndex(helper.UID));
                String name=c.getString(c.getColumnIndex(helper.NAME));
                String address=c.getString(c.getColumnIndex(helper.ADDRESS));
                buffer.append("uid: "+uid+" name: "+name+" address: "+address+"\n");
            }while(c.moveToNext());
        }

        return buffer.toString();
    }

    public void closeDB(){
        db.close();
    }

public String[] getContactByName(String name) {
    String[] columns={helper.UID, helper.NAME, helper.ADDRESS};
    String selection="name=?";
    String[] selectionArgs={name};
    Cursor c=db.query(helper.TABLE_NAME,columns,
            selection, selectionArgs, null, null, null);
    String[] contactData={"",""};
    if(c.moveToFirst()){
        int uid=c.getInt(c.getColumnIndex(helper.UID));
        String address=c.getString(c.getColumnIndex(helper.ADDRESS));
        contactData[0]=uid+"";contactData[1]=address;
        return contactData;
    }
    return contactData;
}

    public int updateContact(String name, String address) {
        // update de nom avec address
        ContentValues values=new ContentValues();
        values.put(helper.ADDRESS, address);
        // sélection de la ligne à modifier
        String whereClause=helper.NAME+"=?";
        String[] whereArgs={name};
        // exécution
        int nbLigne=db.update(helper.TABLE_NAME, values, whereClause,
                whereArgs);
        return nbLigne;
    }

    public int deleteContact(String name) {
        String whereClause=helper.NAME+"=?";
        String[] whereArgs={name};
        int nbLigne=db.delete(helper.TABLE_NAME, whereClause, whereArgs);
        return nbLigne;
    }


static class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteHelper";
    private static final String DB_NAME = "contacts.db";
    private static final String TABLE_NAME = "contact";
    private static final String UID = "_id";
    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";

    private static final int DB_VERSION = 4;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "  (" +
            UID + " integer primary key autoincrement, " +
            NAME + " varchar(255), " +
            ADDRESS + " varchar(255));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "CTor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Log.d(TAG, "onCreate called!");
        } catch (SQLException e) {
            Log.d(TAG, "onCreate Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Log.d(TAG, "onUpgrade called!");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            Log.d(TAG, "onUpgrade Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}//SQLiteHelper

}// contactAdapter
