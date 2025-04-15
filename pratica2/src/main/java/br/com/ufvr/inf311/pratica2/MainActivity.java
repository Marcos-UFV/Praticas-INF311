package br.com.ufvr.inf311.pratica2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calcular(View view){
        EditText tNome = (EditText) findViewById(R.id.tNome);
        EditText tIdade = (EditText) findViewById(R.id.tIdade);
        EditText tPeso = (EditText) findViewById(R.id.tPeso);
        EditText tAltura = (EditText) findViewById(R.id.tAltura);
        String nome = tNome.getText().toString();
        int idade = Integer.parseInt(tIdade.getText().toString());
        float peso = Float.parseFloat(tPeso.getText().toString());
        float altura = Float.parseFloat(tAltura.getText().toString());
        Pair<String,Float> p = calculaIMC(peso,altura);
        Intent it = new Intent(this, RelatorioActivity.class);
        Bundle params = new Bundle();
        params.putString("nome",nome);
        params.putInt("idade",idade);
        params.putFloat("peso",peso);
        params.putFloat("altura",altura);
        params.putString("categoria",p.first);
        params.putFloat("imc",p.second);
        it.putExtras(params);
        startActivity(it);
    }
    public Pair<String,Float> calculaIMC(float peso, float altura){
        float imc = (float) peso/(altura*altura);
        String classificacao = "";
        if(imc < 18.5){
            classificacao = "Abaixo do Peso";
        } else if (imc >= 18.5 && imc < 25) {
            classificacao = "Saudável";
        }else if(imc >= 25 && imc < 30){
            classificacao = "Sobrepeso";
        }else if(imc >= 30 && imc < 35){
            classificacao = "Obesidade Grau I";
        }else if(imc >= 35 && imc < 40){
            classificacao = "Obesidade Grau II (severa)";
        }else{
            classificacao = "Obesidade Grau III (mórbida)";
        }
        return new Pair<>(classificacao,imc);
    }
}