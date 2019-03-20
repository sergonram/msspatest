package es.juntadeandalucia.sas_msspa_library_android_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import es.juntadeandalucia.sas_msspa_library_android.Constants;
import es.juntadeandalucia.sas_msspa_library_android.MSSPAUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Environment PRO: ", Boolean.toString(Constants.MSSPA_PRO_MODE));
    }
}
