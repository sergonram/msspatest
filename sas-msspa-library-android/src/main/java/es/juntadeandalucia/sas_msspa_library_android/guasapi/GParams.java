package es.juntadeandalucia.sas_msspa_library_android.guasapi;

import java.io.File;

import okhttp3.MediaType;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GParams {

    Guasapi guasapi;
    GParamsInternalSecurity securityParams;

    Long timeOut = GConstants.DEFAULT_SECONDS_TIMEOUT;
    String id = null;
    MediaType mediaType = null;
    GConstants.Type type = null;
    String url = null;
    GUrlParams gUrlParams = null;
    GHeader header = null;
    String body = null;
    GCallback callback;
    GFormBody formBody = null;
    GParamsSecurity securityInputParams;
    GMultipartForm multipartForm = null;
    GCache cache = null;
    File file;
    boolean responseInBackground = false;
    boolean debug = false;
    boolean bodyResponse = false;

    public GParams() {
    }

    public GParams(Guasapi guasapi) {
        this.guasapi = guasapi;
    }

    public void doCall() {
        guasapi.setParams(new GParamsInternal(this));
        guasapi.doCall();
    }

    public GParams setId(String id) {
        this.id = id;
        return this;
    }

    public GParams setMediaType(GConstants.GMediaType mediaType) {
        this.mediaType = GUtils.getMediaType(mediaType);
        return this;
    }

    public GParams setMediaType(String customMediaType) {
        this.mediaType = MediaType.parse(customMediaType);
        return this;
    }

    public GParams setType(GConstants.Type type) {
        this.type = type;
        return this;
    }

    public GParams setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public GParams setUrl(String url) {
        this.url = url;
        return this;
    }

    public GParams setBody(String body) {
        this.body = body;
        return this;
    }

    public GParams setUrlParams(GUrlParams gUrlParams) {
        this.gUrlParams = gUrlParams;
        return this;
    }

    public GParams setHeader(GHeader header) {
        this.header = header;
        return this;
    }

    public GParams setCallback(GCallback callback) {
        this.callback = callback;
        return this;
    }

    public GParams setFormBody(GFormBody formBody) {
        this.formBody = formBody;
        return this;
    }

    public GParams setSecurity(GParamsSecurity securityInputParams) {
        this.securityInputParams = securityInputParams;
        return this;
    }

    public GParams setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public GParams setMultipartForm(GMultipartForm multipartForm) {
        this.multipartForm = multipartForm;
        return this;
    }

    public GParams setFile (File file) {
        this.file = file;
        return this;
    }

    public GParams setCache (GCache cache) {
        this.cache = cache;
        return this;
    }

    public GParams setResponseInBackground(boolean responseInBackground) {
        this.responseInBackground = responseInBackground;
        return this;
    }

    public GParams setBodyResponse(boolean sourceResponse) {
        this.bodyResponse = sourceResponse;
        return this;
    }

    public boolean isBodyResponse() {
        return bodyResponse;
    }
}
