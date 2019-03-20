package es.juntadeandalucia.sas_msspa_library_android;

import android.content.Context;
import android.os.Build;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.juntadeandalucia.sas_msspa_library_android.guasapi.GCallback;
import es.juntadeandalucia.sas_msspa_library_android.guasapi.GConstants;
import es.juntadeandalucia.sas_msspa_library_android.guasapi.GHeader;
import es.juntadeandalucia.sas_msspa_library_android.guasapi.GResponse;
import es.juntadeandalucia.sas_msspa_library_android.guasapi.GUrlParams;
import es.juntadeandalucia.sas_msspa_library_android.guasapi.Guasapi;
import es.juntadeandalucia.sas_msspa_library_android.models.MSSPAError;
import es.juntadeandalucia.sas_msspa_library_android.models.MSSPAMessage;
import es.juntadeandalucia.sas_msspa_library_android.models.MSSPAToken;
import es.juntadeandalucia.sas_msspa_library_android.models.OctopushConMessageRichDTO;
import es.juntadeandalucia.sas_msspa_library_android.models.OctopushRegisterDeviceRequest;
import es.juntadeandalucia.sas_msspa_library_android.models.OctopushRichParam;
import es.juntadeandalucia.sas_msspa_library_android.models.OctopushUpdateMessageRequest;

/**
 * Created by BABEL Sistemas de Información S.L. on 22/08/2017.
 * <p>
 * Autenticacion v00r25
 * IdDeviceMsspa v00r010
 * Octopush v00r32
 * Usuarios v00r35
 * Gestion PIN v01r01
 */

public class MSSPAWebServicesManager implements GCallback {


    // Listeners
    private OnGenerateIdDeviceMsspaReceivedListener listenerGenerateIdDeviceMsspa;
    private OnGetTokensDNIeReceivedListener listenerGetTokensDNIe;
    private OnGetTokensTwoStepsReceivedListener listenerGetTokensTwoSteps;
    private OnRefreshTokensReceivedListener listenerRefreshTokens;
    private OnVerifyTokenListener listenerVerifyToken;
    private OnRemoveTokenListener listenerRemoveToken;
    private OnGetConfigAuthenticationListener listenerGetConfigAuthentication;
    private OnOctopushRegisterDeviceListener listenerOctopushRegisterDevice;
    private OnOctopushRemoveRegisterDevice listenerOctopushRemoveRegisterDevice;
    private OnOctopushSendAckReadPush listenerOctopushSendAckReadPush;
    private OnOctopushGetMessages listenerOctopushGetMessages;
    private OnOctopushUpdateMessage listenerOctopushUpdateMessage;
    private OnGetUsersBasicUserData listenerGetUsersBasicUserData;
    private OnRegisterPINListener listenerRegisterPIN;
    private OnVerifyPINlistener listenerVerifyPIN;
    private OnEditsPINListener listenerEditPIN;


    // Singleton instance
    private static MSSPAWebServicesManager mInstance = null;
    // Context
    private static Context mContext;

    // Constructor
    private MSSPAWebServicesManager(Context context) {
        mContext = context;
    }

    // Get instance
    public static MSSPAWebServicesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MSSPAWebServicesManager(context);
            mContext = context;
        }
        return mInstance;
    }

    // Métodos para asignación de listeners.
    public void setOnOctopushReisterDeviceReceiveListener(OnOctopushRegisterDeviceListener l) {
        listenerOctopushRegisterDevice = l;
    }

    public void setOnGetTokensDNIeReceivedListener(OnGetTokensDNIeReceivedListener l) {
        listenerGetTokensDNIe = l;
    }

    public void setOnGetTokensTwoStepsReceivedListener(OnGetTokensTwoStepsReceivedListener l) {
        listenerGetTokensTwoSteps = l;
    }

    public void setOnRefreshTokensReceivedListener(OnRefreshTokensReceivedListener l) {
        listenerRefreshTokens = l;
    }

    public void setOnVerifyTokenListener(OnVerifyTokenListener l) {
        listenerVerifyToken = l;
    }

    public void setOnRemoveTokenListener(OnRemoveTokenListener l) {
        listenerRemoveToken = l;
    }

    public void setOnGenerateIdDeviceMsspaListener(OnGenerateIdDeviceMsspaReceivedListener l) {
        listenerGenerateIdDeviceMsspa = l;
    }

    public void setOnOctopushRegisterDeviceReceiveListener(OnOctopushRegisterDeviceListener l) {
        listenerOctopushRegisterDevice = l;
    }

    public void setOnOctopushRemoveRegisterDeviceListener(OnOctopushRemoveRegisterDevice l) {
        listenerOctopushRemoveRegisterDevice = l;
    }

    public void setOnOctopushSenAckReadPushListener(OnOctopushSendAckReadPush l) {
        listenerOctopushSendAckReadPush = l;
    }

    public void setOnOctopushGetMessagesListener(OnOctopushGetMessages l) {
        listenerOctopushGetMessages = l;
    }

    public void setOnOctopushUpdateMessageListener(OnOctopushUpdateMessage l) {
        listenerOctopushUpdateMessage = l;
    }

    public void setOnGetAuthenticacionConfigAuthentication(OnGetConfigAuthenticationListener l) {
        listenerGetConfigAuthentication = l;
    }

    public void setOnGetUsersBasicUserData(OnGetUsersBasicUserData l) {
        listenerGetUsersBasicUserData = l;
    }

    public void setOnRegisterPINListener(OnRegisterPINListener l) {
        listenerRegisterPIN = l;
    }

    public void setOnVerifyPINListener(OnVerifyPINlistener l) {
        listenerVerifyPIN = l;
    }

    public void setOnEditsPINListener(OnEditsPINListener l) {
        listenerEditPIN = l;
    }

    //Autenticación v00r25

    /**
     * This method opens an url to authenticate through digital certificate, the app will be waiting to get the response from a defined url
     *
     * @param apiKey
     * @param scheme
     * @return
     */
    public String getAuthenticationUrlCertificate(String apiKey, String scheme, String idDevice, String idDeviceMsspa, String appKey) {
//        En la autenticación con certificado electrónico y clave, añadir en el path de la url a la que se llama 3 nuevos parámetros: idDevice, idDeviceMsspa y appKey
        String url = getHost(2) +
                Constants.MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_CERTIFICATE +
                Constants.MSSPA_URL_SW_PARAM_TYPE_AFIRMA +
                "&" + Constants.MSSPA_URL_SW_PARAM_CLIENT_ID + "=" + Constants.MSSPA_URL_SW_PARAM_CLIENT_ID_VALUE +
                "&" + Constants.MSSPA_URL_SW_PARAM_REDIRECT_URI + scheme + "%3A%2F%2Fautentication" +
                "&" + Constants.MSSPA_URL_SW_PARAM_APIKEY + "=" + apiKey +
                "&" + Constants.MSSPA_URL_SW_PARAM_ID_DEVICE + "=" + idDevice+
                "&" + Constants.MSSPA_URL_SW_PARAM_ID_DEVICE_MSSPA + "=" + idDeviceMsspa+
                "&" + Constants.MSSPA_URL_SW_PARAM_APPKEY + "=" + appKey;

        return url;
    }

    //Autenticación v00r25

    /**
     * This service gets the authentication response when an user tries logged through DNIe
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param certificate
     */
    public void serviceAuthenticationGetTokenDNIe(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String certificate) {

        if (Constants.MSSPA_DEMO_MODE) {

            MSSPAToken tokenMSSPA = new MSSPAToken("c7d82733-1827-465c-a278-01f83919e463", "Bearer", "3600", "ciudadano", true);
            listenerGetTokensDNIe.onGetTokensDNIeResponseReceived(tokenMSSPA, null);

        } else if (!hasNetworkConnection(mContext)) {

            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerGetTokensDNIe.onFailGetTokensDNIeReceived(null, errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            if (certificate == null) certificate = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("certificate", certificate);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_TYPE, Constants.MSSPA_URL_SW_PARAM_DNIE)
                    .add(Constants.MSSPA_URL_SW_PARAM_CLIENT_ID, Constants.MSSPA_URL_SW_PARAM_CLIENT_ID_VALUE)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.AUTENTICATION_GET_TOKENS_DNIE_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_DNIE)
                    .setType(GConstants.Type.GET) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }

    }

    //Autenticación v00r25

    /**
     * This method opens an url to authenticate through Cl@ve, the app will be waiting to get the response from a defined url
     *
     * @param apiKey
     * @param scheme
     * @return
     */
    public String getAuthenticationUrlClave(String apiKey, String scheme,String idDevice, String idDeviceMsspa, String appKey) {
        String url = getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_CLAVE + Constants.MSSPA_URL_SW_PARAM_TYPE_CLAVE +
                "&" + Constants.MSSPA_URL_SW_PARAM_REDIRECT_URI + scheme + "%3A%2F%2Fautentication"
                + "&apiKey=" + apiKey+
                "&" + Constants.MSSPA_URL_SW_PARAM_ID_DEVICE + "=" + idDevice+
                "&" + Constants.MSSPA_URL_SW_PARAM_ID_DEVICE_MSSPA + "=" + idDeviceMsspa+
                "&" + Constants.MSSPA_URL_SW_PARAM_APPKEY + "=" + appKey;


        return url;
    }

    //Autenticación v00r25

    /**
     * This service gets the authentication response when an user tries logged through web/desktop
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param tokenOTP
     * @param hashDNI
     */
    public void serviceAuthenticationGetTokenWebDesktop(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String tokenOTP, String hashDNI) {
        if (Constants.MSSPA_DEMO_MODE) {

            MSSPAToken tokenMSSPA = new MSSPAToken("c7d82733-1827-465c-a278-01f83919e467", "Bearer", "3600", "ciudadano", true);
            listenerGetTokensTwoSteps.onGetTokensTwoStepsResponseReceived(tokenMSSPA, null);

        } else if (!hasNetworkConnection(mContext)) {

            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerGetTokensTwoSteps.onFailGetTokensTwoStepsReceived(null, errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";
            if (tokenOTP == null) tokenOTP = "";
            if (hashDNI == null) hashDNI = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_TYPE, Constants.MSSPA_URL_SW_PARAM_OTP)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_ID_DEVICE, idDevice)
                    .add(Constants.MSSPA_URL_SW_PARAM_DNI, hashDNI)
                    .add(Constants.MSSPA_URL_SW_PARAM_TOKENOTP, tokenOTP)
                    .add(Constants.MSSPA_URL_SW_PARAM_APPKEY, appKey);

            new Guasapi().builder().setId(Constants.AUTENTICATION_GET_TOKENS_TWO_STEPS_ID)
                    .setUrl(getHost(2) + Constants.MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_TWO_STEPS)
                    .setType(GConstants.Type.GET) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    /**
     * Autenticación v00r19
     * This service refreshes the tokens (access and refresh)
     */
    public void serviceAutenticationRefreshTokens(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String refreshToken) {
        if (Constants.MSSPA_DEMO_MODE) {

            MSSPAToken tokenMSSPA = new MSSPAToken("c7d82733-1827-465c-a278-01f83919e469", "Bearer", "3600", "ciudadano", true);
            listenerRefreshTokens.onRefreshTokensResponseReceived(tokenMSSPA, null);

        } else if (!hasNetworkConnection(mContext)) {

            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerRefreshTokens.onFailRefreshTokensReceived(null, errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_REFRESH_TOKEN, refreshToken)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.AUTENTICATION_REFRESH_TOKENS_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_REFRESH_TOKENS)
                    .setType(GConstants.Type.GET) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //Autenticación v00r25

    /**
     * This service verifies the expiration date of the access token
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param authorization
     */
    public void serviceAuthenticationVerifyToken(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String authorization) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerVerifyToken.onVerifyTokenResponseReceived(true);

        } else if (!hasNetworkConnection(mContext)) {

            listenerVerifyToken.onFailVerifyTokenReceived(false);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";
            if (authorization == null) authorization = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.AUTENTICATION_VERIFY_TOKEN_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_VERIFY_TOKEN)
                    .setType(GConstants.Type.GET) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //IdDeviceMSSPA v00r10

    /**
     * This service generates a MSSPA device identifier
     *
     * @param apiKey
     * @param idDevice
     * @param appKey
     */
    public void serviceIdDeviceMsspaGenerateIdentifier(String apiKey, String idDevice, String appKey) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerGenerateIdDeviceMsspa.onGenerateIdDeviceMsspaResponseReceived("0123456789", null);

        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerGenerateIdDeviceMsspa.onFailGenerateIdDeviceMsspaReceived("", errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("appKey", appKey);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.GENERATE_ID_DEVICE_MSSPA)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_GENERATE_ID_DEVICE_MSSPA)
                    .setType(GConstants.Type.GET) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //Autenticación v00r25

    /**
     * This service removes the access token
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param authorization
     */
    public void serviceAuthenticationRemoveToken(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String authorization) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerRemoveToken.onRemoveTokenResponseReceived(true);

        } else if (!hasNetworkConnection(mContext)) {
            listenerRemoveToken.onFailRemoveTokenReceived(false);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";
            if (authorization == null) authorization = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.AUTENTICATION_REMOVE_TOKEN_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_REMOVE_TOKEN)
                    .setType(GConstants.Type.DELETE) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }

    //Autenticación v00r25

    /**
     * This service gets the configuration of the methods of authentication
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     */
    public void serviceAuthenticationGetConfigAuthentication(String apiKey, String idDevice, String idDeviceMSSPA, String appKey) {

        if (Constants.MSSPA_DEMO_MODE) {
            listenerGetConfigAuthentication.onGetConfigAuthenticationResponseReceived(true, true, true, true, true);

        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerGetConfigAuthentication.onFailGetConfigAuthenticationReceived(errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_APPKEY, appKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_ID_SO, Constants.DEVICE_TYPE);

            new Guasapi().builder().setId(Constants.AUTENTICATION_GET_CONFIG_AUTHENTICATION_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_AUTENTICATION_GET_CONFIG_AUTHENTICATION)
                    .setType(GConstants.Type.GET)
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }


    //API Octopush V32

    //Octopush v00r32

    /**
     * This service registers the device in Octopush
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param appKeyOctopush
     * @param authorization
     * @param idNotification
     * @param migratedFCM
     * @param migratedApple
     * @param versionApp
     * @param locale
     */
    public void serviceOctopushRegisterDevice(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String appKeyOctopush, String authorization, String idNotification, boolean migratedFCM, boolean migratedApple, String versionApp, String locale) {
        if (Constants.MSSPA_DEMO_MODE) {

            listenerOctopushRegisterDevice.onOctopushRegisterDeviceResponseReceived(null);

        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerOctopushRegisterDevice.onFailOctopushRegisterDeviceReceived(errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";
            if (authorization == null) authorization = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("appKeyOctopush", appKeyOctopush)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            OctopushRegisterDeviceRequest requestBody = new OctopushRegisterDeviceRequest(Constants.DEVICE_TYPE, idNotification, Build.VERSION.RELEASE, Build.MODEL, migratedFCM, false, versionApp, appKeyOctopush, idDevice, locale);

            Gson mGson = new Gson();

            new Guasapi().builder().setId(Constants.OCTOPUSH_REGISTER_DEVICE_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_OCTOPUSH_REGISTER_DEVICE)
                    .setType(GConstants.Type.POST) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setBody(mGson.toJson(requestBody))
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //Octopush v00r32

    /**
     * This service removes one device registered in Octopush
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param appKeyOctopush
     */
    public void serviceOctopushRemoveRegisterDevice(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String appKeyOctopush) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerOctopushRemoveRegisterDevice.onOctopushRemoveRegisterDeviceResponseReceived(null);


        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerOctopushRemoveRegisterDevice.onFailOctopushRemoveRegisterDeviceReceived(errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("appKeyOctopush", appKeyOctopush);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_APP_KEY_OCTOPUSH, appKeyOctopush);


            new Guasapi().builder().setId(Constants.OCTOPUSH_REMOVE_REGISTER_DEVICE_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_OCTOPUSH_REMOVE_REGISTER_DEVICE + idDevice)
                    .setType(GConstants.Type.DELETE)
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //Octopush v00r32

    /**
     * This service sends a push notification ACK to Octopush
     *
     * @param apiKey
     * @param idDevice       Device id
     * @param idDeviceMSSPA  MSSPA id for the device
     * @param appKey         App key
     * @param appKeyOctopush
     * @param idMessage
     */
    public void serviceOctopushSendAckReadPush(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String appKeyOctopush, String idMessage) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerOctopushSendAckReadPush.onOctopushSendAckReadPushResponseReceived(null);


        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerOctopushSendAckReadPush.onFailOctopushSendAckReadPushReceived(errorMSSPA);

        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("appKeyOctopush", appKeyOctopush);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_APP_KEY_OCTOPUSH, appKeyOctopush);

            new Guasapi().builder().setId(Constants.OCTOPUSH_SEND_ACK_READ_PUSH_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_OCTOPUSH_SEND_ACK_READ_PUSH + idDevice + Constants.MSSPA_URL_SW_OCTOPUSH_SEND_ACK_READ_PUSH_NOTIF + idMessage + Constants.MSSPA_URL_SW_OCTOPUSH_SEND_ACK_READ_PUSH_RESPONSE)
                    .setType(GConstants.Type.PUT) //change to POST when real
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //Octopush v00r32

    /**
     * This service gets messages from Octopush
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param appKeyOctopush
     * @param locale
     * @param authorization
     */
    public void serviceOctopushGetMessages(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String appKeyOctopush, String locale, String authorization) {
        if (Constants.MSSPA_DEMO_MODE) {
            MSSPAMessage[] messageList = new MSSPAMessage[2];
            messageList[0] = new MSSPAMessage(new OctopushRichParam("", "", false, false), false, "20170901000000", "2", "20180101000000", new OctopushConMessageRichDTO("Description message 1", "http://www.google.es", locale, "Message 1", false));
            messageList[1] = new MSSPAMessage(new OctopushRichParam("", "", false, false), false, "20170901000000", "2", "20180101000000", new OctopushConMessageRichDTO("Description message 2", "http://www.google.es", locale, "Message 2", true));

            listenerOctopushGetMessages.onOctopushGetMessagesResponseReceived(messageList, null);
        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerOctopushGetMessages.onFailOctopushGetMessagesReceived(null, errorMSSPA);
        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("appKeyOctopush", appKeyOctopush)
                    .add("authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_APP_KEY_OCTOPUSH, appKeyOctopush)
                    .add(Constants.MSSPA_URL_SW_PARAM_LOCALE, locale)
                    .add(Constants.MSSPA_URL_SW_PARAM_VALID, "true");

            new Guasapi().builder().setId(Constants.OCTOPUSH_GET_MESSAGES_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES + idDevice + Constants.MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES_NOTIF + Constants.MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES_RICH)
                    .setType(GConstants.Type.GET)
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();

        }
    }

    //Octopush v00r32

    /**
     * This service updates the "read" param in an Octopush message
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param appKeyOctopush
     * @param locale
     * @param authorization
     * @param idMessages
     */
    public void serviceOctopushUpdateMessage(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String appKeyOctopush, String locale, String authorization, String[] idMessages) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerOctopushUpdateMessage.onOctopushUpdateMessageResponseReceived(null);
        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerOctopushUpdateMessage.onFailOctopushUpdateMessageReceived(errorMSSPA);
        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("appKeyOctopush", appKeyOctopush)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey)
                    .add(Constants.MSSPA_URL_SW_PARAM_APP_KEY_OCTOPUSH, appKeyOctopush)
                    .add(Constants.MSSPA_URL_SW_PARAM_LOCALE, locale);

            OctopushUpdateMessageRequest requestBody = new OctopushUpdateMessageRequest(idMessages, true);

            Gson mGson = new Gson();

            new Guasapi().builder().setId(Constants.OCTOPUSH_UPDATE_MESSAGE_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_OCTOPUSH_UPDATE_MESSAGE + idDevice + Constants.MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES_NOTIF + Constants.MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES_RICH)
                    .setType(GConstants.Type.PUT)
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setBody(mGson.toJson(requestBody))
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }

    //Usuarios v00r35

    /**
     * This service gets the basic data of the user
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param authorization
     */
    public void serviceUsersGetBasicUserData(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String authorization) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerGetUsersBasicUserData.onGetUsersBasicUserDataResponseReceived(0, "AN0000000000", "19850209", "Usuario", "Modo", "Demo");
        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerGetUsersBasicUserData.onFailGetUsersBasicUserDataReceived(errorMSSPA);
        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_COMPLETE, Constants.MSSPA_URL_SW_PARAM_FALSE)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            Gson mGson = new Gson();

            new Guasapi().builder().setId(Constants.OCTOPUSH_GET_BASIC_USERS_DATA_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_GET_BASIC_USERS_DATA)
                    .setType(GConstants.Type.GET)
                    .setHeader(gHeader)
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }


    //Gestión PIN v01r01

    /**
     * This service registers a PIN in the mSSPA
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param authorization
     * @param hashPIN
     */
    public void serviceManagePINRegisterPIN(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String authorization, String hashPIN) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerRegisterPIN.onRegisterPINResponseReceived(null);
        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerRegisterPIN.onFailRegisterPINReceived(errorMSSPA);
        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_HASH, hashPIN)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.USUARIOS_REGISTER_PIN_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_REGISTER_PIN)
                    .setType(GConstants.Type.POST)
                    .setHeader(gHeader)
                    .setBody("{}")
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }

    /**
     * This service verifies the mSSPA PIN
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param authorization
     * @param hashPIN
     */
    public void serviceManagePINVerifyPIN(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String authorization, String hashPIN) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerVerifyPIN.onVerifyPINResponseReceived(true, null);
        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerVerifyPIN.onFailVerifyPINReceived(errorMSSPA);
        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_HASH, hashPIN)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.USUARIOS_VERIFY_PIN_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_VERIFY_PIN)
                    .setType(GConstants.Type.POST)
                    .setHeader(gHeader)
                    .setBody("{}")
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }

    /**
     * This service edits the mSSPA PIN
     *
     * @param apiKey
     * @param idDevice
     * @param idDeviceMSSPA
     * @param appKey
     * @param authorization
     * @param hashPIN
     */
    public void serviceManagePINEditPIN(String apiKey, String idDevice, String idDeviceMSSPA, String appKey, String authorization, String hashPIN) {
        if (Constants.MSSPA_DEMO_MODE) {
            listenerEditPIN.onEditPINResponseReceived(null);
        } else if (!hasNetworkConnection(mContext)) {
            MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_NETWORK_CONNECTION, "MSSPA Library", "NC01", "No hay conexión con el servidor");
            listenerEditPIN.onFailEditsPINReceived(errorMSSPA);
        } else {

            if (idDevice == null) idDevice = "";
            if (idDeviceMSSPA == null) idDeviceMSSPA = "";
            if (appKey == null) appKey = "";

            GHeader gHeader = new GHeader()
                    .add("Content-Type", "application/json")
                    .add("Accept", "application/json")
                    .add("idDevice", idDevice)
                    .add("idDeviceMsspa", idDeviceMSSPA)
                    .add("appKey", appKey)
                    .add("Authorization", authorization);

            GUrlParams gUrlParams = new GUrlParams()
                    .add(Constants.MSSPA_URL_SW_PARAM_HASH, hashPIN)
                    .add(Constants.MSSPA_URL_SW_PARAM_APIKEY, apiKey);

            new Guasapi().builder().setId(Constants.USUARIOS_EDIT_PIN_ID)
                    .setUrl(getHost(1) + Constants.MSSPA_URL_SW_EDIT_PIN)
                    .setType(GConstants.Type.PUT)
                    .setHeader(gHeader)
                    .setBody("{}")
                    .setUrlParams(gUrlParams)
                    .setMediaType(GConstants.GMediaType.JSON)
                    .setTimeOut(Constants.INTERNET_TIMEOUT)
                    .setCallback(this).doCall();
        }
    }


    @Override
    public void onSuccess(GResponse response) {
        switch (response.getId()) {
            case Constants.GENERATE_ID_DEVICE_MSSPA:
                if (listenerGenerateIdDeviceMsspa != null) {
                    String idDeviceMSSPA = getSuccesResponseFromGenerateIdDeviceMSSPA(response);
                    listenerGenerateIdDeviceMsspa.onGenerateIdDeviceMsspaResponseReceived(idDeviceMSSPA, null);
                }
                break;

            //AUTHENTICATION
            case Constants.AUTENTICATION_GET_TOKENS_DNIE_ID:
                if (listenerGetTokensDNIe != null) {
                    MSSPAToken tokenMsspa = getTokenMsspaFromResponse(response);
                    listenerGetTokensDNIe.onGetTokensDNIeResponseReceived(tokenMsspa, null);
                }
                break;
            case Constants.AUTENTICATION_GET_TOKENS_TWO_STEPS_ID:
                if (listenerGetTokensTwoSteps != null) {
                    MSSPAToken tokenMsspa = getTokenMsspaFromResponse(response);
                    listenerGetTokensTwoSteps.onGetTokensTwoStepsResponseReceived(tokenMsspa, null);
                }
                break;
            case Constants.AUTENTICATION_REFRESH_TOKENS_ID:
                if (listenerRefreshTokens != null) {
                    MSSPAToken tokenMsspa = getTokenMsspaFromResponse(response);
                    listenerRefreshTokens.onRefreshTokensResponseReceived(tokenMsspa, null);
                }
                break;
            case Constants.AUTENTICATION_VERIFY_TOKEN_ID:
                if (listenerVerifyToken != null) {
                    listenerVerifyToken.onVerifyTokenResponseReceived(true);
                }
                break;
            case Constants.AUTENTICATION_REMOVE_TOKEN_ID:
                if (listenerRemoveToken != null) {
                    listenerRemoveToken.onRemoveTokenResponseReceived(true);
                }
                break;
            case Constants.AUTENTICATION_GET_CONFIG_AUTHENTICATION_ID:
                if (listenerGetConfigAuthentication != null) {
                    boolean lanzadora = getSuccesResponseFromGetConfigAuthentication(response, "lanzadora");
                    boolean otp = getSuccesResponseFromGetConfigAuthentication(response, "otp");
                    boolean clave = getSuccesResponseFromGetConfigAuthentication(response, "clave");
                    boolean certificado = getSuccesResponseFromGetConfigAuthentication(response, "certificado");
                    boolean dnie = getSuccesResponseFromGetConfigAuthentication(response, "dnie");
                    listenerGetConfigAuthentication.onGetConfigAuthenticationResponseReceived(lanzadora, otp, clave, certificado, dnie);
                }
                break;

            //USUARIOS
            case Constants.OCTOPUSH_GET_BASIC_USERS_DATA_ID:
                if (listenerGetUsersBasicUserData != null) {
                    int idSexo = getSuccesResponseIntFromResponse(response, "idSexo");
                    String nuhsa = getSuccesResponseStringFromResponse(response, "nuhsa");
                    String fechaNacimiento = getSuccesResponseStringFromResponse(response, "fechaNacimiento");
                    String nombre = getSuccesResponseStringFromResponse(response, "nombre");
                    String apellido1 = getSuccesResponseStringFromResponse(response, "apellido1");
                    String apellido2 = getSuccesResponseStringFromResponse(response, "apellido2");
                    listenerGetUsersBasicUserData.onGetUsersBasicUserDataResponseReceived(idSexo, nuhsa, fechaNacimiento, nombre, apellido1, apellido2);
                }
                break;

            case Constants.USUARIOS_REGISTER_PIN_ID:
                if (listenerRegisterPIN != null) {
                    listenerRegisterPIN.onRegisterPINResponseReceived(null);
                }
                break;
            case Constants.USUARIOS_VERIFY_PIN_ID:
                if (listenerVerifyPIN != null) {
                    boolean verifyPinResponse = getVerifyPinResponse(response);
                    listenerVerifyPIN.onVerifyPINResponseReceived(verifyPinResponse, null);
                }
                break;
            case Constants.USUARIOS_EDIT_PIN_ID:
                if (listenerEditPIN != null) {
                    listenerEditPIN.onEditPINResponseReceived(null);
                }
                break;

            //API Octopush
            case Constants.OCTOPUSH_REGISTER_DEVICE_ID:
                if (listenerOctopushRegisterDevice != null) {
                    listenerOctopushRegisterDevice.onOctopushRegisterDeviceResponseReceived(null);
                }
                break;

            case Constants.OCTOPUSH_REMOVE_REGISTER_DEVICE_ID:
                if (listenerOctopushRemoveRegisterDevice != null) {
                    listenerOctopushRemoveRegisterDevice.onOctopushRemoveRegisterDeviceResponseReceived(null);
                }
                break;
            case Constants.OCTOPUSH_SEND_ACK_READ_PUSH_ID:
                if (listenerOctopushSendAckReadPush != null) {
                    listenerOctopushSendAckReadPush.onOctopushSendAckReadPushResponseReceived(null);
                }
                break;
            case Constants.OCTOPUSH_GET_MESSAGES_ID:
                if (listenerOctopushGetMessages != null) {
                    MSSPAMessage[] listMessages = getListMessagesFromResponse(response);
                    listenerOctopushGetMessages.onOctopushGetMessagesResponseReceived(listMessages, null);
                }
                break;
            case Constants.OCTOPUSH_UPDATE_MESSAGE_ID:
                if (listenerOctopushUpdateMessage != null) {
                    listenerOctopushUpdateMessage.onOctopushUpdateMessageResponseReceived(null);
                }
                break;

        }
    }

    @Override
    public void onError(GResponse response) {
        MSSPAError errorMSSPA = new MSSPAError(Constants.MSSPA_ERROR_TECHNICAL, "MSSPA Library", "TE01", "Ha ocurrido un error técnico");

        switch (response.getId()) {
            case Constants.GENERATE_ID_DEVICE_MSSPA:
                if (listenerGenerateIdDeviceMsspa != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerGenerateIdDeviceMsspa.onFailGenerateIdDeviceMsspaReceived("", errorMSSPA);
                }
                break;
            case Constants.USUARIOS_REGISTER_PIN_ID:
                if (listenerRegisterPIN != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerRegisterPIN.onFailRegisterPINReceived(errorMSSPA);
                }
                break;
            case Constants.USUARIOS_VERIFY_PIN_ID:
                if (listenerVerifyPIN != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerVerifyPIN.onFailVerifyPINReceived(errorMSSPA);
                }
                break;
            case Constants.USUARIOS_EDIT_PIN_ID:
                if (listenerEditPIN != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerEditPIN.onFailEditsPINReceived(errorMSSPA);
                }
                break;

            //OCT
            case Constants.OCTOPUSH_REGISTER_DEVICE_ID:
                if (listenerOctopushRegisterDevice != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerOctopushRegisterDevice.onFailOctopushRegisterDeviceReceived(errorMSSPA);
                }
                break;
            case Constants.OCTOPUSH_REMOVE_REGISTER_DEVICE_ID:
                if (listenerOctopushRemoveRegisterDevice != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerOctopushRemoveRegisterDevice.onFailOctopushRemoveRegisterDeviceReceived(errorMSSPA);
                }
                break;
            case Constants.OCTOPUSH_SEND_ACK_READ_PUSH_ID:
                if (listenerOctopushSendAckReadPush != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerOctopushSendAckReadPush.onFailOctopushSendAckReadPushReceived(errorMSSPA);
                }
                break;
            case Constants.OCTOPUSH_GET_MESSAGES_ID:
                if (listenerOctopushGetMessages != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerOctopushGetMessages.onFailOctopushGetMessagesReceived(null, errorMSSPA);
                }
                break;
            case Constants.OCTOPUSH_UPDATE_MESSAGE_ID:
                if (listenerOctopushUpdateMessage != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerOctopushUpdateMessage.onFailOctopushUpdateMessageReceived(errorMSSPA);
                }
                break;

            //AUTHENTICATION
            case Constants.AUTENTICATION_GET_TOKENS_DNIE_ID:
                if (listenerGetTokensDNIe != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerGetTokensDNIe.onFailGetTokensDNIeReceived(null, errorMSSPA);
                }
                break;
            case Constants.AUTENTICATION_GET_TOKENS_TWO_STEPS_ID:
                if (listenerGetTokensTwoSteps != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerGetTokensTwoSteps.onFailGetTokensTwoStepsReceived(null, errorMSSPA);
                }
                break;
            case Constants.AUTENTICATION_REFRESH_TOKENS_ID:
                if (listenerRefreshTokens != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerRefreshTokens.onFailRefreshTokensReceived(null, errorMSSPA);
                }
                break;
            case Constants.AUTENTICATION_VERIFY_TOKEN_ID:
                if (listenerVerifyToken != null) {
                    listenerVerifyToken.onFailVerifyTokenReceived(false);
                }
                break;
            case Constants.AUTENTICATION_REMOVE_TOKEN_ID:
                if (listenerRemoveToken != null) {
                    listenerRemoveToken.onFailRemoveTokenReceived(false);
                }
                break;
            case Constants.AUTENTICATION_GET_CONFIG_AUTHENTICATION_ID:
                if (listenerGetConfigAuthentication != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerGetConfigAuthentication.onFailGetConfigAuthenticationReceived(errorMSSPA);
                }
                break;
            case Constants.OCTOPUSH_GET_BASIC_USERS_DATA_ID:
                if (listenerGetUsersBasicUserData != null) {
                    errorMSSPA = proccesErrorMsspa(response);
                    listenerGetUsersBasicUserData.onFailGetUsersBasicUserDataReceived(errorMSSPA);
                }
                break;

        }
    }

    private boolean getSuccesResponseFromGetConfigAuthentication(GResponse response, String field) {

        boolean responseValue = false;
        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null) {
                if (jsonObject.has(field)) {
                    responseValue = jsonObject.getBoolean(field);
                }else if(jsonObject.has("operaciones")){
                    responseValue = jsonObject.getJSONObject("operaciones").getBoolean(field);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseValue;
    }

    private String getSuccesResponseStringFromResponse(GResponse response, String field) {

        String responseValue = "";
        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null && jsonObject.has("administrativos")) {
                jsonObject = jsonObject.getJSONObject("administrativos");
                if (jsonObject.has(field)) {
                    responseValue = jsonObject.getString(field);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseValue;
    }

    private int getSuccesResponseIntFromResponse(GResponse response, String field) {

        int responseValue = -1;
        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null && jsonObject.has("administrativos")) {
                jsonObject = jsonObject.getJSONObject("administrativos");
                if (jsonObject.has(field)) {
                    responseValue = jsonObject.getInt(field);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseValue;
    }

    private String getSuccesResponseFromGenerateIdDeviceMSSPA(GResponse response) {

        String idDeviceMsspa = "";
        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null) {
                if (jsonObject.has("idDeviceMsspa")) {
                    idDeviceMsspa = jsonObject.getString("idDeviceMsspa");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return idDeviceMsspa;
    }

    private MSSPAMessage[] getListMessagesFromResponse(GResponse response) {

        try {
            final Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(response.getResult());
            MSSPAMessage[] result = new MSSPAMessage[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                MSSPAMessage msspaMessage = gson.fromJson(jsonObject.toString(), MSSPAMessage.class);
                result[i] = msspaMessage;
            }

            if (jsonArray.length() == 0) {
                result = null;
            }

            return result;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private MSSPAToken getTokenMsspaFromResponse(GResponse response) {

        MSSPAToken tokenMSSPA = new MSSPAToken("", "", "", "", true);

        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null) {
                if (jsonObject.has("accessToken")) {
                    tokenMSSPA.setAccessToken(jsonObject.getString("accessToken"));
                    tokenMSSPA.setTokenType(jsonObject.getString("tokenType"));
                    tokenMSSPA.setExpiresIn(jsonObject.getString("expiresIn"));
                    tokenMSSPA.setScope(jsonObject.getString("scope"));
                    tokenMSSPA.setPin(jsonObject.getBoolean("pin"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tokenMSSPA;
    }

    private MSSPAError proccesErrorMsspa(GResponse response) {

        MSSPAError errorMSSPA = new MSSPAError(0, "", "", "");//new MSSPAError(Constants.MSSPA_ERROR_TECHNICAL, "MSSPA Library", "TE01", "Ha ocurrido un error técnico");
        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null) {
                if (jsonObject.has("error")) {
                    jsonObject=jsonObject.getJSONObject("error");
                    errorMSSPA.setStatus(jsonObject.getInt("status"));
                    errorMSSPA.setType(jsonObject.getString("type"));
                    errorMSSPA.setCode(jsonObject.getString("code"));
                    errorMSSPA.setMessage(jsonObject.getString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return errorMSSPA;

    }

    private boolean getVerifyPinResponse(GResponse response) {

        boolean validPIN = false;

        try {
            JSONObject jsonObject = new JSONObject(response.getResult());
            if (jsonObject != null) {
                if (jsonObject.has("valido")) {
                    validPIN = jsonObject.getBoolean("valido");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return validPIN;
    }


    /**
     * Interface to receive the response from service GenerateIdDeviceMsspa
     */
    public interface OnGenerateIdDeviceMsspaReceivedListener {

        void onGenerateIdDeviceMsspaResponseReceived(String idDeviceMsspa, MSSPAError errorMSSPA);

        void onFailGenerateIdDeviceMsspaReceived(String idDeviceMsspa, MSSPAError errorMSSPA);
    }

    /**
     * Interface to receive the response from service GetTokensDNI
     */
    public interface OnGetTokensDNIeReceivedListener {

        void onGetTokensDNIeResponseReceived(MSSPAToken msspaToken, MSSPAError errorMSSPA);

        void onFailGetTokensDNIeReceived(MSSPAToken msspaToken, MSSPAError errorMSSPA);
    }

    /**
     * Interface to receive the response from service GetTokenTwoSteps
     */
    public interface OnGetTokensTwoStepsReceivedListener {

        void onGetTokensTwoStepsResponseReceived(MSSPAToken msspaToken, MSSPAError errorMSSPA);

        void onFailGetTokensTwoStepsReceived(MSSPAToken msspaToken, MSSPAError errorMSSPA);
    }

    /**
     * Interface to receive the response from service RefreshTokens
     */
    public interface OnRefreshTokensReceivedListener {

        void onRefreshTokensResponseReceived(MSSPAToken msspaToken, MSSPAError errorMSSPA);

        void onFailRefreshTokensReceived(MSSPAToken msspaToken, MSSPAError errorMSSPA);
    }

    /**
     * Interface to receive the response from service VerifyToken
     */
    public interface OnVerifyTokenListener {

        void onVerifyTokenResponseReceived(boolean response);

        void onFailVerifyTokenReceived(boolean response);
    }

    /**
     * Interface to receive the response from service RemoveToken
     */
    public interface OnRemoveTokenListener {

        void onRemoveTokenResponseReceived(boolean response);

        void onFailRemoveTokenReceived(boolean response);

    }

    /**
     * Interface to receive the response from service GetConfigAuthentication
     */
    public interface OnGetConfigAuthenticationListener {

        void onGetConfigAuthenticationResponseReceived(boolean lanzadora, boolean certificado, boolean dnie, boolean clave, boolean webdesktop);

        void onFailGetConfigAuthenticationReceived(MSSPAError errorMSSPA);
    }

    /**
     * Interface to receive the response from service OctopushRegisterDevice
     */
    public interface OnOctopushRegisterDeviceListener {

        void onOctopushRegisterDeviceResponseReceived(MSSPAError errorMSSPA);

        void onFailOctopushRegisterDeviceReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service OctopushRemoveRegister
     */
    public interface OnOctopushRemoveRegisterDevice {

        void onOctopushRemoveRegisterDeviceResponseReceived(MSSPAError errorMSSPA);

        void onFailOctopushRemoveRegisterDeviceReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service OctopushSendAckReadPush
     */
    public interface OnOctopushSendAckReadPush {

        void onOctopushSendAckReadPushResponseReceived(MSSPAError errorMSSPA);

        void onFailOctopushSendAckReadPushReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service OctopushGetMessages
     */
    public interface OnOctopushGetMessages {

        void onOctopushGetMessagesResponseReceived(MSSPAMessage[] listMessagesMSSPA, MSSPAError errorMSSPA);

        void onFailOctopushGetMessagesReceived(MSSPAMessage[] listMessagesMSSPA, MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service OctopushUpdateMessage
     */
    public interface OnOctopushUpdateMessage {

        void onOctopushUpdateMessageResponseReceived(MSSPAError errorMSSPA);

        void onFailOctopushUpdateMessageReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service GetUserBasicUserData
     */
    public interface OnGetUsersBasicUserData {

        void onGetUsersBasicUserDataResponseReceived(int idSexo, String nuhsa, String fechaNaciminto, String nombre, String apellido1, String apellido2);

        void onFailGetUsersBasicUserDataReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service RegisterPIN
     */
    public interface OnRegisterPINListener {

        void onRegisterPINResponseReceived(MSSPAError errorMSSPA);

        void onFailRegisterPINReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service VerifyPIN
     */
    public interface OnVerifyPINlistener {

        void onVerifyPINResponseReceived(boolean valido, MSSPAError errorMSSPA);

        void onFailVerifyPINReceived(MSSPAError errorMSSPA);

    }

    /**
     * Interface to receive the response from service EditsPIN
     */
    public interface OnEditsPINListener {

        void onEditPINResponseReceived(MSSPAError errorMSSPA);

        void onFailEditsPINReceived(MSSPAError errorMSSPA);

    }

    public String getHost(int num) {
        String result = "";

        if (Constants.MSSPA_PRO_MODE) {
            if (num == 1) {
                result = Constants.MSSPA_HOST_1_PRO;
            } else {
                result = Constants.MSSPA_HOST_2_PRO;
            }
        } else {
            if (num == 1) {
                result = Constants.MSSPA_HOST_1_DES;
            } else {
                result = Constants.MSSPA_HOST_2_DES;
            }
        }
        return result;
    }

    //This method checks if the device has network connection with data
    public static boolean hasNetworkConnection(Context context) {
        return Network.hasNetworkConnection(context);
    }

}
