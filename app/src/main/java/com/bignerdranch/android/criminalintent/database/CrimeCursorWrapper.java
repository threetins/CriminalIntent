package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.DATE;
import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.SOLVED;
import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.TITLE;
import static com.bignerdranch.android.criminalintent.database.CrimeDBSchema.CrimeTable.Cols.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(UUID));
        String title = getString(getColumnIndex(TITLE));
        long date = getLong(getColumnIndex(DATE));
        int isSolved = getInt(getColumnIndex(SOLVED));

        Crime crime = new Crime(java.util.UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date());
        crime.setSolved(isSolved != 0);

        return crime;
    }
}
