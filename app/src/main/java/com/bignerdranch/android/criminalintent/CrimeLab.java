package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {

        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab (Context context) {

        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // every other
            mCrimes.add(crime);
        }
    }
}
