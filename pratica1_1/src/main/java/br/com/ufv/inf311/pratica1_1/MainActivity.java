package br.com.ufv.inf311.pratica1_1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView valor1;
    TextView valor2;
    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void operacao(View view){

        valor1 = (TextView) findViewById(R.id.tValor1);
        valor2 = (TextView) findViewById(R.id.tValor2);
        msg = (TextView) findViewById(R.id.tRes);
        String str1 = valor1.getText().toString();
        String str2 = valor2.getText().toString();
        if(str1.isEmpty() || str2.isEmpty()){
            return;
        }
        double v1 = Double.parseDouble(str1);
        double v2 = Double.parseDouble(str2);

        String tag = view.getTag().toString();
        double res = 0;
        String alerta = "";
        switch (tag){
            case "1":
                res = v1 + v2;
                break;
            case "2":
                res = v1 - v2;
                break;
            case "3":
                res = v1 * v2;
                break;
            case "4":
                if(v2 != 0.0){
                    res = v1 /v2;
                }else{
                    alerta = "Impossível realizar divisão por zero!";
                }
                break;
        }
        if(alerta.isEmpty()){
            msg.setText(" "+res);
        }else{
            msg.setText(alerta);
        }
    }
}