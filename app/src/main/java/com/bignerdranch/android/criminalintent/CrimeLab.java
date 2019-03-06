package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes; // LinkedHashMap
//    private Map<UUID, Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab (Context context) {
//        mCrimes = new LinkedHashMap<>();
        mCrimes = new ArrayList<>(); // LinkedHashMap
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0); // every other
//            crime.setRequiresPolice(i % 3 == 0); // every third
//            mCrimes.put(crime.getId(), crime);
//        }
    }

    public void addCrime(Crime c) {
//        mCrimes.put(c.getId(), c);
        mCrimes.add(c); // LinkedHashMap
    }

    public List<Crime> getCrimes() {
        return mCrimes; // LinkedHashMap
//        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) { // to study // LinkedHashMap
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
//        return mCrimes.get(id);
    }
}
