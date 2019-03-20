package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 05/09/2017.
 */

public class OctopushRegisterDeviceRequest {


    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("idNotificacion")
    private String idNotificacion;

    @SerializedName("versionSO")
    private String versionSO;

    @SerializedName("model")
    private String model;

    @SerializedName("migratedFCM")
    private boolean migratedFCM;

    @SerializedName("migratedApple")
    private boolean migratedApple;

    @SerializedName("versionApp")
    private String versionApp;

    @SerializedName("appKeyOctopush")
    private String appKeyOctopush;

    @SerializedName("idDevice")
    private String idDevice;

    @SerializedName("locale")
    private String locale;

    public OctopushRegisterDeviceRequest(String deviceType, String idNotificacion, String versionSO, String model, boolean migratedFCM, boolean migratedApple, String versionApp, String appKeyOctopush, String idDevice, String locale) {
        this.appKeyOctopush = appKeyOctopush;
        this.deviceType = deviceType;
        this.idDevice = idDevice;
        this.idNotificacion = idNotificacion;
        this.migratedApple = migratedApple;
        this.migratedFCM = migratedFCM;
        this.model = model;
        this.versionApp = versionApp;
        this.versionSO = versionSO;
        this.locale = locale;
    }

    public String getAppKeyOctopush() {
        return appKeyOctopush;
    }

    public void setAppKeyOctopush(String appKeyOctopush) {
        this.appKeyOctopush = appKeyOctopush;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public boolean isMigratedApple() {
        return migratedApple;
    }

    public void setMigratedApple(boolean migratedApple) {
        this.migratedApple = migratedApple;
    }

    public boolean isMigratedFCM() {
        return migratedFCM;
    }

    public void setMigratedFCM(boolean migratedFCM) {
        this.migratedFCM = migratedFCM;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }

    public String getVersionSO() {
        return versionSO;
    }

    public void setVersionSO(String versionSO) {
        this.versionSO = versionSO;
    }
}
