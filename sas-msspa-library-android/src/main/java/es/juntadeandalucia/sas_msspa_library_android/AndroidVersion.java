package es.juntadeandalucia.sas_msspa_library_android;

import android.os.Build;

/**
 * Created by BABEL Sistemas de Información S.L. on 18/08/2017.
 */

public class AndroidVersion {


    private static final int VERSION_CODES_FROYO = 8;
    private static final int VERSION_CODES_GINGERBREAD = 9;
    private static final int VERSION_CODES_GINGERBREADM_MR1 = 10;
    private static final int VERSION_CODES_HONEYCOMB = 11;
    private static final int VERSION_CODES_HONEYCOMB_MR1 = 12;
    private static final int VERSION_CODES_HONEYCOMB_MR2 = 13;
    private static final int VERSION_CODES_JELLY_BEAN = 16;
    private static final int VERSION_CODES_JELLY_BEAN_MR1 = 17;
    private static final int VERSION_CODES_LOLLIPOP = 21;
    private static final int VERSION_CODES_MARSHMALLOW = 23;
    private static final int VERSION_CODES_NOUGAT = 24;


    public static boolean isFroyoOrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_FROYO);
    }

    public static boolean isGingerbreadOrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_GINGERBREAD);
    }

    public static boolean isGingerbreadOrDown () {
        return (Build.VERSION.SDK_INT <= VERSION_CODES_GINGERBREAD);
    }

    public static boolean isGingerbreadMR1OrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_GINGERBREADM_MR1);
    }

    public static boolean isGingerbreadMR1OrDown () {
        return (Build.VERSION.SDK_INT <= VERSION_CODES_GINGERBREADM_MR1);
    }

    public static boolean isHoneycombOrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_HONEYCOMB);
    }

    public static boolean isHoneycombMR1OrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_HONEYCOMB_MR1);
    }

    public static boolean isHoneycombMR2OrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_HONEYCOMB_MR2);
    }

    public static boolean isJellyBeanOrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_JELLY_BEAN);
    }

    public static boolean isJellyBeanMr1OrUp () {
        return (Build.VERSION.SDK_INT >= VERSION_CODES_JELLY_BEAN_MR1);
    }

    public static boolean isLollipopOrUp(){
        return (Build.VERSION.SDK_INT >= VERSION_CODES_LOLLIPOP);
    }

    public static boolean isMarshmallowOrUp(){
        return (Build.VERSION.SDK_INT >= VERSION_CODES_MARSHMALLOW);
    }
}
