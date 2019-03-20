package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 05/09/2017.
 */

public class OctopushUpdateMessageRequest {


    @SerializedName("listIdMessage")
    private String[] listIdMessage;

    @SerializedName("readed")
    private boolean readed;

    public OctopushUpdateMessageRequest(String[] listIdMessage, boolean readed) {
        this.listIdMessage = listIdMessage;
        this.readed = readed;
    }


    public String[] getListIdMessage() {
        return listIdMessage;
    }

    public void setListIdMessage(String[] listIdMessage) {
        this.listIdMessage = listIdMessage;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
