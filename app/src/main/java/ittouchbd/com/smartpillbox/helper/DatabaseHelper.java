package ittouchbd.com.smartpillbox.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String  DATABASE_NAME = "prescription.db";
    private static final int DATABASE_VERSION = 1;

    private static final String  PRESCRIPTION_TABLE = "prescription_table.db";
    private static final String  DOCTORINFO_TABLE = "doctor_table.db";
    private static final String  APPOINTMENT_TABLE = "appointment_table.db";
    private static final String  MEDICINEINFO_TABLE = "medicine_table.db";
    private static final String  TIME_TABLE = "medicine_time_table.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
