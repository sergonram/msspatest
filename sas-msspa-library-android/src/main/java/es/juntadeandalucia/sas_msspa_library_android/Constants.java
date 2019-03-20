package es.juntadeandalucia.sas_msspa_library_android;

/**
 * Created by BABEL Sistemas de Información S.L. on 18/08/2017.
 */

public class Constants {

    public static final String AES256_KEY = "mSSPAv1r0";
    public static final String PREF_DECRYPTED_DEVICE_ID = "pref_decrypted_device_id";
    public static final String DEVICE_TYPE = "0";  //Android

    public static final long INTERNET_TIMEOUT = 30; //30 seconds

    public static final boolean MSSPA_DEMO_MODE = false;
    public static final boolean MSSPA_PRO_MODE = false;

    //API Identifiers
    public static final String GENERATE_ID_DEVICE_MSSPA = "100";

    public static final int MSSPA_ERROR_NETWORK_CONNECTION = 1001;
    public static final int MSSPA_ERROR_TECHNICAL = 1002;

    public static final String MSSPA_HOST_1_DES = "https://www.juntadeandalucia.es/servicioandaluzdesalud/msspa_gateway_pre";
    public static final String MSSPA_HOST_1_PRO = "https://www.juntadeandalucia.es/servicioandaluzdesalud/msspa_gateway";
    public static final String MSSPA_HOST_2_DES = "https://ws237.juntadeandalucia.es";
    public static final String MSSPA_HOST_2_PRO = "https://ws238.juntadeandalucia.es:8442";

    public static final String AUTENTICATION_GET_TOKENS_CERTIFICATE_ID = "200";   //only for internal purpose
    public static final String AUTENTICATION_GET_TOKENS_DNIE_ID = "201";   //only for internal purpose
    public static final String AUTENTICATION_GET_TOKENS_CLAVE_ID = "202";   //only for internal purpose
    public static final String AUTENTICATION_GET_TOKENS_TWO_STEPS_ID = "203";   //only for internal purpose
    public static final String AUTENTICATION_REFRESH_TOKENS_ID = "204";   //only for internal purpose
    public static final String AUTENTICATION_VERIFY_TOKEN_ID = "205";   //only for internal purpose
    public static final String AUTENTICATION_REMOVE_TOKEN_ID = "206";   //only for internal purpose
    public static final String AUTENTICATION_GET_CONFIG_AUTHENTICATION_ID = "213";   //only for internal purpose
    public static final String OCTOPUSH_REGISTER_DEVICE_ID = "207";   //only for internal purpose
    public static final String OCTOPUSH_REMOVE_REGISTER_DEVICE_ID = "208";   //only for internal purpose
    public static final String OCTOPUSH_SEND_ACK_READ_PUSH_ID = "209";   //only for internal purpose
    public static final String OCTOPUSH_GET_MESSAGES_ID = "210";   //only for internal purpose
    public static final String OCTOPUSH_UPDATE_MESSAGE_ID = "211";   //only for internal purpose
    public static final String GENERATE_ID_MSSPA_DEVICE = "212";   //only for internal purpose
    public static final String OCTOPUSH_GET_BASIC_USERS_DATA_ID = "215";   //only for internal purpose
    public static final String USUARIOS_REGISTER_PIN_ID = "216";   //only for internal purpose
    public static final String USUARIOS_VERIFY_PIN_ID = "217";   //only for internal purpose
    public static final String USUARIOS_EDIT_PIN_ID = "218";   //only for internal purpose

    public static final String MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_CERTIFICATE = "/api/v1.0/autenticacion/token?";
    public static final String MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_DNIE = "/api/v1.0/autenticacion/token?";
    public static final String MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_CLAVE = "/api/v1.0/autenticacion/token?";
    public static final String MSSPA_URL_SW_AUTENTICATION_GET_TOKENS_TWO_STEPS = "/api/v1.0/autenticacion/token?";
    public static final String MSSPA_URL_SW_AUTENTICATION_REFRESH_TOKENS = "/api/v1.0/autenticacion/token?";
    public static final String MSSPA_URL_SW_AUTENTICATION_VERIFY_TOKEN = "/api/v1.0/autenticacion/token/estado?";
    public static final String MSSPA_URL_SW_AUTENTICATION_REMOVE_TOKEN = "/api/v1.0/autenticacion/token?";
    public static final String MSSPA_URL_SW_AUTENTICATION_GET_CONFIG_AUTHENTICATION = "/api/v1.0/autenticacion?";

    public static final String MSSPA_URL_SW_AUTENTICATION_GENERATE_ID_DEVICE_MSSPA = "/api/v1.0/dispositivos/identificadores?";

    public static final String MSSPA_URL_SW_OCTOPUSH_REGISTER_DEVICE = "/api/v1.0/dispositivos?";
    public static final String MSSPA_URL_SW_OCTOPUSH_REMOVE_REGISTER_DEVICE = "/api/v1.0/dispositivos/";
    public static final String MSSPA_URL_SW_OCTOPUSH_SEND_ACK_READ_PUSH = "/api/v1.0/dispositivos/";
    public static final String MSSPA_URL_SW_OCTOPUSH_SEND_ACK_READ_PUSH_NOTIF = "/notificaciones/";
    public static final String MSSPA_URL_SW_OCTOPUSH_SEND_ACK_READ_PUSH_RESPONSE = "/respuesta";
    public static final String MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES = "/api/v1.0/dispositivos/";
    public static final String MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES_NOTIF = "/notificaciones/";
    public static final String MSSPA_URL_SW_OCTOPUSH_GET_MESSAGES_RICH = "rich";
    public static final String MSSPA_URL_SW_OCTOPUSH_UPDATE_MESSAGE = "/api/v1.0/dispositivos/";

    public static final String MSSPA_URL_SW_GET_BASIC_USERS_DATA = "/api/v1.0/usuarios/demograficos";

    public static final String MSSPA_URL_SW_REGISTER_PIN = "/api/v1.0/usuarios/pin";
    public static final String MSSPA_URL_SW_VERIFY_PIN = "/api/v1.0/usuarios/pin$validar";
    public static final String MSSPA_URL_SW_EDIT_PIN = "/api/v1.0/usuarios/pin";

    public static final String MSSPA_URL_SW_PARAM_SEPARATOR = "&";
    public static final String MSSPA_URL_SW_PARAM_CLIENT_ID = "clientId";
    public static final String MSSPA_URL_SW_PARAM_CLIENT_ID_VALUE = "54f0c455-4d80-421f-82ca-9194df24859d";
    public static final String MSSPA_URL_SW_PARAM_REDIRECT_URI = "redirectURI=";
    public static final String MSSPA_URL_SW_PARAM_TYPE_AFIRMA = "tipo=afirma";
    public static final String MSSPA_URL_SW_PARAM_TYPE_CLAVE = "tipo=proxyClave";
    public static final String MSSPA_URL_SW_PARAM_OTP = "otp";
    public static final String MSSPA_URL_SW_PARAM_TYPE = "tipo";
    public static final String MSSPA_URL_SW_PARAM_DNIE = "dnie";
    public static final String MSSPA_URL_SW_PARAM_TOKEN_IDENTIFIER = "msspa%3A%2F%2Fautentication";
    public static final String MSSPA_URL_SW_PARAM_APPKEY = "appKey";
    public static final String MSSPA_URL_SW_PARAM_DNI = "dni";
    public static final String MSSPA_URL_SW_PARAM_TOKENOTP = "tokenOTP";
    public static final String MSSPA_URL_SW_PARAM_APIKEY = "apikey";
    public static final String MSSPA_URL_SW_PARAM_ID_SO = "idSo";
    public static final String MSSPA_URL_SW_PARAM_APP_KEY_OCTOPUSH = "appKeyOctopush";
    public static final String MSSPA_URL_SW_PARAM_ID_DEVICE = "idDevice";
    public static final String MSSPA_URL_SW_PARAM_ID_DEVICE_MSSPA = "idDeviceMsspa";
    public static final String MSSPA_URL_SW_PARAM_ID_MENSAJE = "idMensaje";
    public static final String MSSPA_URL_SW_PARAM_REFRESH_TOKEN = "refreshToken";
    public static final String MSSPA_URL_SW_PARAM_VALID = "valid";
    public static final String MSSPA_URL_SW_PARAM_LOCALE = "locale";
    public static final String MSSPA_URL_SW_PARAM_COMPLETE = "completa";
    public static final String MSSPA_URL_SW_PARAM_HASH = "hash";
    public static final String MSSPA_URL_SW_PARAM_FALSE = "false";

    public static final String KEY_APP_USER_AUTHORIZATION = "key_sr_user_access_token";
    public static final String KEY_APP_USER_HAS_PIN = "key_sr_user_has_pin";
    public static final String KEY_APP_EXPIRED_DATE = "key_sr_expired_date";
    public static final String KEY_APP_AUTHENTICATION_TYPE = "key_sr_authentication_type";
    public static final String KEY_OPEN_LOGIN_FROM = "key_open_loging_from";

    public static final String PACKAGE_SR_PRE = "citamovil.saludresponde.pre";
    public static final String PACKAGE_SR = "citamovil.saludresponde";


}
