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
//    private List<Crime> mCrimes;
    private Map<UUID, Crime> mCrimes; // LinkedHashMap

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab (Context context) {
        mCrimes = new LinkedHashMap<>(); // LinkedHashMap
//        mCrimes = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0); // every other
//            crime.setRequiresPolice(i % 3 == 0); // every third
//            mCrimes.put(crime.getId(), crime);
//        }
    }

    public void addCrime(Crime c) {
        mCrimes.put(c.getId(), c); // LinkedHashMap
//        mCrimes.add(c);
    }

    public Map<UUID, Crime> getCrimes() {
//    public List<Crime> getCrimes() {
        return mCrimes;
//        return new ArrayList<>(mCrimes.values()); // LinkedHashMap
    }

    public Crime getCrime(UUID id) {
//        for (Crime crime : mCrimes) { // to study
//            if (crime.getId().equals(id)) {
//                return crime;
//            }
//        }
//        return null;
        return mCrimes.get(id); // LinkedHashMap
    }
}
