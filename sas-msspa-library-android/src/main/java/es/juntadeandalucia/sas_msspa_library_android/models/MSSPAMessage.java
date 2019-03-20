package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 05/09/2017.
 */

public class MSSPAMessage {

    @SerializedName("richParams")
    private OctopushRichParam richParams;

    @SerializedName("sendPush")
    private boolean sendPush;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("idMessage")
    private String idMessage;

    @SerializedName("endDate")
    private String endDate;

    @SerializedName("contMessageRichDTO")
    private OctopushConMessageRichDTO contentMessageRichDTO;


    public MSSPAMessage(OctopushRichParam richParams, boolean sendPush, String startDate, String idMessage, String endDate, OctopushConMessageRichDTO contentMessageRichDTO) {
        this.contentMessageRichDTO = contentMessageRichDTO;
        this.endDate = endDate;
        this.idMessage = idMessage;
        this.richParams = richParams;
        this.sendPush = sendPush;
        this.startDate = startDate;
    }

    public OctopushConMessageRichDTO getContentMessageRichDTO() {
        return contentMessageRichDTO;
    }

    public void setContentMessageRichDTO(OctopushConMessageRichDTO contentMessageRichDTO) {
        this.contentMessageRichDTO = contentMessageRichDTO;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public OctopushRichParam getRichParams() {
        return richParams;
    }

    public void setRichParams(OctopushRichParam richParams) {
        this.richParams = richParams;
    }

    public boolean isSendPush() {
        return sendPush;
    }

    public void setSendPush(boolean sendPush) {
        this.sendPush = sendPush;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
