package es.juntadeandalucia.sas_msspa_library_android.guasapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GHeader {
    Map<String, String> map;

    public GHeader() {
        this.map = new HashMap<>();
    }

    public GHeader add(String key, String value) {
        this.map.put(key, value);
        return this;
    }

    Map<String, String> getHeader() {
        return map;
    }
}
