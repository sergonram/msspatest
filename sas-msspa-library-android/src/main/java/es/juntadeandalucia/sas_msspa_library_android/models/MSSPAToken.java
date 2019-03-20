package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 25/08/2017.
 */

public class MSSPAToken {

    @SerializedName("accessToken")
    String accessToken;
    @SerializedName("tokenType")
    String tokenType;
    @SerializedName("expiresIn")
    String expiresIn;
    @SerializedName("scope")
    String scope;
    @SerializedName("pin")
    boolean pin;

    public MSSPAToken(String accessToken, String tokenType, String expiresIn, String scope, boolean pin) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.pin = pin;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public boolean isPin() {
        return pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
