package es.juntadeandalucia.sas_msspa_library_android.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

public interface GCallback {

    void onError(GResponse response);

    void onSuccess(GResponse response);
}
