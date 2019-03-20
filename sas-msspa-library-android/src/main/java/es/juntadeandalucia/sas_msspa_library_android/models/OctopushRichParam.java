package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 05/09/2017.
 */
public class OctopushRichParam {

    @SerializedName("pushType")
    private String pushType;

    @SerializedName("pushAction")
    private String pushAction;

    @SerializedName("invisible")
    private boolean invisible;

    @SerializedName("silent")
    private boolean silent;


    public OctopushRichParam(String pushType, String pushAction, boolean invisible, boolean silent) {
        this.invisible = invisible;
        this.pushAction = pushAction;
        this.pushType = pushType;
        this.silent = silent;
    }
}
