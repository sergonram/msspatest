package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 25/08/2017.
 */

public class MSSPAError {


        @SerializedName("status")
        int status;
        @SerializedName("type")
        String type;
        @SerializedName("code")
        String code;
        @SerializedName("message")
        String message;

        public MSSPAError(int status, String type,String code, String message ) {
            this.code = code;
            this.message = message;
            this.status = status;
            this.type = type;
        }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
