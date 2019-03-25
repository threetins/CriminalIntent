package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.database.CrimeDBSchema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.DATE;
import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.SOLVED;
import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.TITLE;
import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.NAME;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
//    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

//    private Map<UUID, Crime> mCrimes; // LinkedHashMap

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
//        mCrimes = new LinkedHashMap<>(); // LinkedHashMap
//        mCrimes = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0); // every other
//            crime.setRequiresPolice(i % 3 == 0); // every third
//            mCrimes.put(crime.getId(), crime);
//        }
    }

    public void addCrime(Crime c) {
//        mCrimes.put(c.getId(), c); // LinkedHashMap
//        mCrimes.add(c);
        ContentValues values = getContentValues(c);

        mDatabase.insert(NAME, null, values);
    }

    public void deleteCrime(Crime c) {
//        mCrimes.remove(c);
        String uuidString = c.getId().toString();

        mDatabase.delete(NAME, "uuid=?", new String[]{ uuidString });
    }

//    public Map<UUID, Crime> getCrimes() {
    public List<Crime> getCrimes() {

        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
//        return mCrimes;
//        return new ArrayList<>(); // to study
//        return new ArrayList<>(mCrimes.values()); // LinkedHashMap
    }

    public Crime getCrime(UUID id) {

        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDBSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]{ id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(
                NAME,
                values,
                CrimeDBSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]{ uuidString }
                );
    }

//    private Cursor queryCrimes(String whereClause, String[] whereArgs) {
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();

        values.put(CrimeDBSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(TITLE, crime.getTitle());
        values.put(DATE, crime.getDate().getTime());
        values.put(SOLVED, crime.isSolved());

        return values;

    }
}
