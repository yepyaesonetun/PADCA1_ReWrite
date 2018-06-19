package com.primeyz.padca1_rewrite.data.db;

import android.annotation.SuppressLint;
import android.arch.persistence.room.TypeConverter;

import java.util.Arrays;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

public class ProgramLengthTypeConverter {
    @SuppressLint("NewApi")
    @TypeConverter
    public static int[] toIntArray(String length) {
        String[] lengths = length.split(",");
        return Arrays.stream(lengths).mapToInt(Integer::parseInt).toArray();
    }

    @TypeConverter
    public static String toString(int[] lengths) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int length : lengths) {
            stringBuilder.append(length).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
