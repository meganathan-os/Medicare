package com.example.admin.medicare.utilities;

/**
 * Created by Admin on 10-03-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    private final static String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "MEDICINE_DB.sqlite";
    //public final static String DATABASE_PATH ="/data/data/com.example,medicineproject/databases/";
    public static final int DATABASE_VERSION = 1;
    // private static final String TAG="MedicineProje";
    public static final String MY_CATE_DATABASE_TABLE = "MEDICINE_TABLE";
    public static final int MYDATABASE_VERSION = 1;

    private String path;

    public static final String CATEGORY = "CATEGORY";
    public static final String GENERIC_NAME = "GENERIC_NAME";
    public static final String BRAND_NAME = "BRAND_NAME";
    public static final String MOA = "MOA";
    public static final String PHARMACOKINETICS = "PHARMACOKINETICS";
    public static final String DAILY_DOSAGE = "DAILY_DOSAGE";
    public static final String PREGNANCY_EFFECTS = "PREGNANCY_EFFECTS";
    public static final String LACTATION_EFFECTS = "LACTATION_EFFECTS";
    public static final String SEDATION = "SEDATION";
    public static final String WEIGHT_GAIN = "WEIGHT_GAIN";
    public static final String SIDE_EFFECTS = "SIDE_EFFECTS";
    public static final String LIFE_THREATENING = "LIFE_THREATENING";
    public static final String DRUG_INTERACTIONS = "DRUG_INTERACTIONS";
    public static final String TESTS = "TESTS";
    public static final String OVERDOSE = "OVERDOSE";
    public static final String EXTRA_INFORMATION = "EXTRA_INFORMATION";
    public static final String REFERENCE = "REFERENCE";

    SQLiteDatabase db;

    public DatabaseHelper(Context context, String filePath) throws IOException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        path = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDataBase() throws SQLException {
        // Open the database
        String myPath = path + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {

            Log.d(TAG, "Database exists.");
            //SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            int currentDBVersion = db.getVersion();

            if (DATABASE_VERSION > currentDBVersion) {
                Log.d(TAG, "Database version is higher than old.");
                // deleteDb();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }


    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(path);
            checkDB = file.exists();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(path);
        InputStream is = myContext.getAssets().open("databases/" + DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }

    public void deleteDb() {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }

    public Cursor getEntireData(String itemGeneric) {
        //SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        Cursor res = db.rawQuery("select * from MEDICINE_TABLE where GENERIC_NAME LIKE '" + itemGeneric + "'", null);

        // db.close();

        return res;
    }

    public Cursor getEntireData1(String itemBrand) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        Cursor res = db.rawQuery("select * from MEDICINE_TABLE where BRAND_NAME LIKE '" + itemBrand + "'", null);
        // db.close();
        return res;
    }

    public ArrayList<String> getEntireGeneric() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        //Cursor res =  db.rawQuery( "select GENERIC_NAME from DRUG_TABLE", null );
        Cursor res = db.rawQuery("select * from MEDICINE_TABLE", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(GENERIC_NAME)));
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public ArrayList<String> getEntireBrand() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        //Cursor res =  db.rawQuery( "select GENERIC_NAME from DRUG_TABLE", null );
        Cursor res = db.rawQuery("select * from MEDICINE_TABLE", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(BRAND_NAME)));
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public ArrayList<String> getEntireCateg() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        //Cursor res =  db.rawQuery( "select GENERIC_NAME from DRUG_TABLE", null );
        //Cursor res =  db.rawQuery( "select DISTINCT CATEGORY from MEDI_TABLE", null );

        Cursor res = db.rawQuery("select DISTINCT CATEGORY from MEDICINE_TABLE", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CATEGORY)));
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public ArrayList<String> getAllDrugs(String categItem) {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        //Cursor res =  db.rawQuery( "select GENERIC_NAME from DRUG_TABLE", null );
        Cursor res = db.rawQuery("select * from MEDICINE_TABLE where CATEGORY LIKE '" + categItem + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(GENERIC_NAME)));
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public boolean insertCateg(String Item1, String Item2, String Item3, String Item4, String Item5,
                               String Item6, String Item7, String Item8, String Item9, String Item10, String Item11,
                               String Item12, String Item13, String Item14, String Item15, String Item16, String Item17) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        //SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(GENERIC_NAME, Item1);
        contentValues.put(BRAND_NAME, Item2);
        contentValues.put(CATEGORY, Item3);
        contentValues.put(MOA, Item4);
        contentValues.put(PHARMACOKINETICS, Item5);
        contentValues.put(DAILY_DOSAGE, Item6);
        contentValues.put(PREGNANCY_EFFECTS, Item7);
        contentValues.put(LACTATION_EFFECTS, Item8);
        contentValues.put(SEDATION, Item9);
        contentValues.put(WEIGHT_GAIN, Item10);
        contentValues.put(SIDE_EFFECTS, Item11);
        contentValues.put(LIFE_THREATENING, Item12);
        contentValues.put(DRUG_INTERACTIONS, Item13);
        contentValues.put(TESTS, Item14);
        contentValues.put(OVERDOSE, Item15);
        contentValues.put(EXTRA_INFORMATION, Item16);
        contentValues.put(REFERENCE, Item17);
        db.insert(MY_CATE_DATABASE_TABLE, null, contentValues);
        return true;
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE + SQLiteDatabase.CREATE_IF_NECESSARY);
            try {
                onCreate(db);
            } catch (Exception e) {
                Log.e("Log", e.getMessage(), e.fillInStackTrace());
            }
            return db;
        } catch (Exception e) {
            Log.e("Log", e.getMessage(), e.fillInStackTrace());
            if (db != null)
                db.close();
        }
        return db;
    }
}

