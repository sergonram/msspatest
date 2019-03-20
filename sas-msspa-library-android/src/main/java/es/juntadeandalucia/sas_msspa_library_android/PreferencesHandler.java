package es.juntadeandalucia.sas_msspa_library_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by BABEL Sistemas de Información S.L. on 17/08/2017.
 */

public class PreferencesHandler {


    public static boolean getPreferenceBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, defaultValue);
    }

    public static void setPreferenceBoolean(Context context, String key, boolean newValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(key, newValue).apply();
    }

    public static String getPreferenceString(Context context, String key, String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, defaultValue);
    }

    public static void setPreferenceString(Context context, String key, String newValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(key, newValue).apply();
    }

    public static double multiplyTwoTimesNumber(double number) {

        return number * 2;
    }

}
