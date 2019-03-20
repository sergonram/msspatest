package es.juntadeandalucia.sas_msspa_library_android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BABEL Sistemas de Información S.L. on 05/09/2017.
 */
public class OctopushConMessageRichDTO {

    @SerializedName("description")
    private String description;

    @SerializedName("htmlContentURL")
    private String htmlContentUrl;

    @SerializedName("locale")
    private String locale;

    @SerializedName("title")
    private String title;

    @SerializedName("readed")
    private boolean read;


    public OctopushConMessageRichDTO(String description, String htmlContentUrl, String locale, String title, boolean read) {
        this.description = description;
        this.htmlContentUrl = htmlContentUrl;
        this.locale = locale;
        this.read = read;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlContentUrl() {
        return htmlContentUrl;
    }

    public void setHtmlContentUrl(String htmlContentUrl) {
        this.htmlContentUrl = htmlContentUrl;
    }


    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
