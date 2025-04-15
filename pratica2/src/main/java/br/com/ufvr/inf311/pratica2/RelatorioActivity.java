package br.com.ufvr.inf311.pratica2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RelatorioActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        TextView tNome = (TextView) findViewById(R.id.tNome);
        TextView tIdade = (TextView) findViewById(R.id.tIdade);
        TextView tPeso = (TextView) findViewById(R.id.tPeso);
        TextView tAltura = (TextView) findViewById(R.id.tAltura);
        TextView tIMC = (TextView) findViewById(R.id.tIMC);
        TextView tClassificacao = (TextView) findViewById(R.id.tClassificacao);

        tNome.setText(params.getString("nome"));
        tIdade.setText(String.valueOf(params.getInt("idade")));
        tPeso.setText(String.valueOf(params.getFloat("peso")));
        tAltura.setText(String.valueOf(params.getFloat("altura")));
        tIMC.setText(String.format("%.2f",params.getFloat("imc")));
        tClassificacao.setText(params.getString("categoria"));

    }
    public void voltar(View view){
        Intent it = new Intent(this,MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(it);
    }
}