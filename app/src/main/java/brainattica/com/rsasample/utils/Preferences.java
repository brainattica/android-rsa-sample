package brainattica.com.rsasample.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String RSA_GENERATED = "zuul.com.android.RSA_GENERATED";
    public static final String ACCESS_TOKEN = "zuul.com.android.ACCESS_TOKEN";
    public static final String RSA_PUBLIC_KEY = "zuul.com.android.RSA_PUBLIC_KEY";
    public static final String RSA_PRIVATE_KEY = "zuul.com.android.RSA_PRIVATE_KEY";
    public static final String RSA_SERVER_PUBLIC_KEY = "zuul.com.android.RSA_SERVER_PRIVATE_KEY";
    public static final String RSA_SERVER_PRIVATE_KEY = "zuul.com.android.RSA_SERVER_PRIVATE_KEY";
    public static final String EMAIL = "zuul.com.android.EMAIL";
    public static final String SMARTPHONE = "zuul.com.android.SMARTPHONE";
    public static final String FIRST_NAME = "zuul.com.android.FIRST_NAME";
    public static final String LAST_NAME = "zuul.com.android.LAST_NAME";
    public static final String UUID = "zuul.com.android.UUID";
    public static final String PROFILE_PHOTO_URL = "zuul.com.android.PROFILE_PHOTO";
    ;

    public static SharedPreferences mPreferences;

    public static void init(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCES, 0);
    }

    public static boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, boolean bool) {
        mPreferences.edit().putBoolean(key, bool).apply();
    }

    public static void putString(String key, String s) {
        mPreferences.edit().putString(key, s).apply();
    }

    public static void putInteger(String key, Integer integer) {
        mPreferences.edit().putInt(key, integer).apply();
    }

    public static void clear() {
        mPreferences.edit().clear().commit();
    }

    public static void remove(String key) {
        mPreferences.edit().remove(key).commit();
    }

    public static String getString(String key) {
        return mPreferences.getString(key, null);
    }

    public static int getInteger(String key) {
        return mPreferences.getInt(key, 0);
    }

}
