package es.juntadeandalucia.sas_msspa_library_android;

import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by BABEL Sistemas de Información S.L. on 18/08/2017.
 */

public class MSSPAUtils {

    /**
     * This method opens the store of one app if it's possible
     *
     * @param packageName
     * @param context
     */
    public static void openAppInGooglePlay(String packageName, Context context) {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            Log.d("Library UtilsMSSPA", "openAppInPlayStore failed : " + packageName);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    /**
     * This method opens the Salud Responde app in the device, if the app isn't installed, the method opens the Google Play page of Salud Responde
     *
     * @param sourcePackage The applicationId (package name) of the application which needs to open Salud Responde.
     * @param context
     */
    public static void openSaludResponde(String sourcePackage, Context context) {

        String srPackage = Constants.MSSPA_PRO_MODE ? Constants.PACKAGE_SR : Constants.PACKAGE_SR_PRE;

        if (isInstalledAppWithPackage(srPackage, context)) {
            Intent launchApp = context.getPackageManager().getLaunchIntentForPackage(srPackage);
            //launchApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //launchApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            launchApp.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            launchApp.putExtra(Constants.KEY_OPEN_LOGIN_FROM, sourcePackage);
            context.startActivity(launchApp);
        } else {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + srPackage)));
            } catch (android.content.ActivityNotFoundException anfe) {
                Log.d("Library UtilsMSSPA", "openAppInPlayStore failed : " + srPackage);
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + srPackage)));
            }
        }
    }

    /**
     * This method checks if an app is installed in the device
     *
     * @param packageName
     * @param mContext
     * @return
     */
    public static boolean isInstalledAppWithPackage(String packageName, Context mContext) {
        return isInstalledApp(packageName, mContext);
    }

    private static boolean isInstalledApp(String packageName, Context mContext) {
        String version = getVersionAppInstalled(packageName, mContext.getPackageManager());

        if (version != null) {
            return (!version.equals(""));
        } else {
            return false;
        }
    }

    /**
     * Get version of an app
     *
     * @param packageName
     * @param packageManager
     * @return A string containing the installed version of the app identified with @packageName name . Returns empty string if not installed
     */
    private static String getVersionAppInstalled(String packageName, PackageManager packageManager) {
        String version = "";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    //This method checks if the device has network connection with data
    public static boolean hasNetworkConnection(Context context) {
        return Network.hasNetworkConnection(context);
    }


    //This method gets an unique idDevice
    public static String getIdDevice(Context context, boolean telephonyPermissionAccepted) {
        String deviceid = "";

        if(telephonyPermissionAccepted){
            TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null && telephonyPermissionAccepted) {
                deviceid = mTelephony.getDeviceId();
            } else {
                deviceid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }else {
            deviceid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }


        return deviceid;
    }


    //This method saves the idDeviceMSSPA in a file
    public static void saveIdDeviceMSSPAInFile(String deviceId, Context context) {
        //PreferencesHandler.setPreferenceString(context, Constants.PREF_DECRYPTED_DEVICE_ID, deviceId);
        String encryptedDeviceId = encryptAES256(deviceId, Constants.AES256_KEY);
        saveFile(encryptedDeviceId);
        Log.d("Library UtilsMSSPA", "It wasn't ENCRYPTED in the device. So we generate to file this encrypted id: " + encryptedDeviceId + ".Generated and DESENCRYPTED id: " + deviceId);
    }

    //This method gets the idDeviceMSSPA from a file
    public static String readIdDeviceMSSPAFromFile() {

        File path = new File(Environment.getExternalStorageDirectory(), "/mSSPA");
        File file = new File(path, "/msspa_temp");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();

        } catch (IOException e) {
            return "Error get idDevice";
        }

        String result = "";
        if (!text.toString().isEmpty() && !text.toString().equals("Error get idDevice")) {
            result = decryptAES256(text.toString(), Constants.AES256_KEY);
            //PreferencesHandler.setPreferenceString(this.getApplicationContext(), Constants.PREF_DECRYPTED_DEVICE_ID, result);
            //Log.d("Library UtilsMSSPA", "ID was encrypted in file: " + text.toString() + ". DESENCRYPTED NOW from file: " + result + " and saved in pref decrypted");
        }

        return result;
    }

    //This method saves the Hash PIN in a file
    public static void saveHashPINInFile(String hashPin) {
        saveHashFile(hashPin);
    }

    //    	hashPIN (String)
    //This method gets the Hash PIN from a file
    public static String readHashPINFromFile() {
        String result = "";
        File path = new File(Environment.getExternalStorageDirectory(), "/mSSPA");
        File file = new File(path, "/user");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            result = text.toString();
            br.close();

        } catch (IOException e) {
            return "Error getting file info.";
        }

        return result;
    }


    //This method generates the OTP Token for authentication through two steps method
    public static String generateTokenOTP(String tokenWeb) {
        String result = "";

        long miliseconds = System.currentTimeMillis();

        int temp = (int) (miliseconds / 30000);

        String stringTemp = String.valueOf(temp);

        result = tokenWeb + stringTemp;
        //result = "d211ccfdd39d414bb9b1501ed502a27550113076";

        result = md5(result);

        //Log.d("Library UtilsMSSPA", "Tokenweb: " + tokenWeb + "\nMiliseconds: " + miliseconds + "\n. Divided: " + temp + "\nConcat:" + result);

        return result;

    }

    private static final String md5(final String password) {
        try {

            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * This method get the hash with algorithm SHA2-512
     *
     * @return Model o
     */
    public static String getHashSHA2_512(String text) {
        String textHash = "";
        byte[] data = new byte[0];
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            data = md.digest(text.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return bytesToHex(data);

    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes)
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    /**
     * This method gets the Authorization sent by Salud Responde
     *
     * @param intent El intent obtenido de la app Salud Responde
     * @return the authorization string sent by Salud Responde app.
     */
    public static String getAuthorizationBySaludResponde(Intent intent) {
        String authorization = "";

        Bundle bundle = intent.getExtras();
        if (bundle != null)
            authorization = bundle.getString(Constants.KEY_APP_USER_AUTHORIZATION);

        return authorization;
    }

    /**
     * This method gets the PIN sent by Salud Responde
     *
     * @param intent
     * @return
     */
    public static boolean getPINBySaludResponde(Intent intent) {
        boolean hasPin = false;

        Bundle bundle = intent.getExtras();
        if (bundle != null)
            hasPin = bundle.getBoolean(Constants.KEY_APP_USER_HAS_PIN);


        return hasPin;
    }


    /**
     * This method gets the Expired Date sent by Salud Responde
     *
     * @param intent
     * @return
     */
    public static long getExpiredDateBySaludResponde(Intent intent) {
        long expiredDate = 0;

        Bundle bundle = intent.getExtras();
        if (bundle != null)
            expiredDate = bundle.getLong(Constants.KEY_APP_EXPIRED_DATE);

        return expiredDate;
    }


    /**
     * This method gets the Authentication Type sent by Salud Responde
     * 0:nothing, 1: WebDesktop, 2: Certificate, 3: DNIe, 4:Cl@ve
     *
     * @param intent
     * @return
     */
    public static int getAuthenticationTypeBySaludResponde(Intent intent) {
        int authenticationType = 0;

        Bundle bundle = intent.getExtras();
        if (bundle != null)
            authenticationType = bundle.getInt(Constants.KEY_APP_AUTHENTICATION_TYPE, 0);

        return authenticationType;
    }


    //******************* DEVICE ID METHODS ****************
    public static void saveFile(String data) {

        File root = null;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            root = new File(Environment.getExternalStorageDirectory(), "/mSSPA");
            if (!root.exists()) {
                root.mkdirs();
            }
            File filepath = new File(root, "/msspa_temp");
            FileWriter writer = null;
            try {
                writer = new FileWriter(filepath);
                writer.append(data);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Library UtilsMSSPA", "TODO ID savefile exc: " + e.getMessage());
            }
        }
    }

    public static void saveHashFile(String data) {

        File root = null;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            root = new File(Environment.getExternalStorageDirectory(), "/mSSPA");
            if (!root.exists()) {
                root.mkdirs();
            }
            File filepath = new File(root, "/user");
            FileWriter writer = null;
            try {
                writer = new FileWriter(filepath);
                writer.append(data);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method encrypts a text with an algorithm AES256 with key
     *
     * @param text
     * @param key
     * @return
     */
    public static String encryptAES256(String text, String key) {

        JNCryptor cryptor = new AES256JNCryptor();
        byte[] ciphertext = new byte[0];
        try {
            ciphertext = cryptor.encryptData(text.getBytes(), key.toCharArray());
        } catch (CryptorException e) {
            e.printStackTrace();
            Log.d("Library UtilsMSSPA", "TODO ID encryp exc: " + e.getMessage());
        }
        return Base64.encodeToString(ciphertext, Base64.NO_WRAP);
    }

    /**
     * This method decrypts a text with an algorithm AES256 with key
     *
     * @param textEnc Encrypted text coded (Base64)
     * @param key
     * @return
     */
    public static String decryptAES256(String textEnc, String key) {

        JNCryptor cryptor = new AES256JNCryptor();
        byte[] text = new byte[0];
        try {
            text = cryptor.decryptData(Base64.decode(textEnc, Base64.NO_WRAP), key.toCharArray());
        } catch (CryptorException e) {
            e.printStackTrace();
            Log.d("Library UtilsMSSPA", "TODO ID decrypt exc: " + e.getMessage());
        }
        return new String(text);
    }


    /**
     * Checks if the lock screen is set up with a PIN, PASS or PATTERN
     * <p>
     * For Api 16+
     *
     * @return true if PIN, PASS or PATTERN set, false otherwise.
     */
    public static boolean hasDeviceLockPin(Context context) {
        return isPatternSet(context) || isPassOrPinSet(context);
    }

    /**
     * @param context
     * @return true if pattern set, false if not (or if an issue when checking)
     */
    private static boolean isPatternSet(Context context) {
        ContentResolver cr = context.getContentResolver();
        try {
            int lockPatternEnable = Settings.Secure.getInt(cr, Settings.Secure.LOCK_PATTERN_ENABLED);

            return lockPatternEnable == 1;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }

    /**
     * @param context
     * @return true if pass or pin set
     */
    private static boolean isPassOrPinSet(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE); //api 16+
        return keyguardManager.isKeyguardSecure();
    }

    /**
     * This method gets the model of the device
     *
     * @return the model of the device
     */
    public static String getModelDevice() {
        String model = "";

        model = Build.MANUFACTURER + "-" + Build.MODEL;

        return model;


    }


    /**
     * This method check if the device has a security passcode
     *
     * @return
     */
    public static boolean hasSecurityDevice(Context context) {

        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return keyguardManager.isDeviceSecure();
        } else {
            return (isPatternSet(context) || isPassOrPinSet(context));
        }
    }


}
