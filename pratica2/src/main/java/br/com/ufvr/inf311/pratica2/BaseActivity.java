package br.com.ufvr.inf311.pratica2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BaseActivity extends Activity {

    protected static final String TAG = "IMC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,getClassName()+".onCreate() chamado: "+savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,getClassName()+".onStart() chamado.");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,getClassName()+".onRestart() chamado.");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,getClassName()+".onResume() chamado.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,getClassName()+".onPause() chamado.");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,getClassName()+".onSaveInstanceState() chamado.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,getClassName()+".onStop() chamado.");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,getClassName()+".onDestroy() chamado.");
    }

    public String getClassName(){
        String s = getClass().getName();
        return s.substring(s.lastIndexOf("."));
    }

}