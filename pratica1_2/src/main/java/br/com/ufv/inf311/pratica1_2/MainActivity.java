package br.com.ufv.inf311.pratica1_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et;
    private boolean podeAdicionarOperador =false;
    private boolean podeAdicionarDecimal = true;
    private boolean posAcaoIgual = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.lDisplay);
    }

    public void acaoNumero(View view){
        if(posAcaoIgual){
            posAcaoIgual = false;
            if(!et.getText().toString().isEmpty())
                et.setText("");
        }
        if(view instanceof Button){
            String value = ((Button) view).getText().toString();
            if(value.equals(".")) {
                if (podeAdicionarDecimal) {
                    et.append(value);
                    podeAdicionarDecimal = false;
                    podeAdicionarOperador = false;
                }
            }else {
                et.append(value);
                if(separaDigitosEOperandos().size() < 3)
                    podeAdicionarOperador = true;
            }
        }
    }
    public void acaoOperacao(View view){
        boolean hasError = et.getText().toString().equals("ERRO");
        if(view instanceof Button && podeAdicionarOperador && !hasError){
            et.append(((Button) view).getText().toString());
            podeAdicionarOperador = false;
            podeAdicionarDecimal = true;
            posAcaoIgual = false;
        }
    }

    public void acaoLimparTudo(View view){
        podeAdicionarDecimal = true;
        podeAdicionarOperador = false;
        et.setText("");
    }
    public void acaoBackSpace(View view){
        int tamanho = et.getText().length();
        if(tamanho > 0){
            String text = et.getText().toString();
            if(isOperando(text.charAt(text.length() - 1))){
                podeAdicionarOperador = true;
            }
            et.setText(text.substring(0,text.length() - 1));
        }
    }
    private boolean isOperando(char c){
        return c == '+' || c == '-' || c == 'X' || c == '/';
    }
    public void acaoEgual(View view){
        if(separaDigitosEOperandos().size() == 3) {
            et.setText(calculaResultado());
            posAcaoIgual = true;
            podeAdicionarOperador = true;
        }
    }
    private String calculaResultado(){
        List<String> digitosEOperandos = separaDigitosEOperandos();
        StringBuilder resultado = new StringBuilder();
        if(digitosEOperandos.isEmpty() || digitosEOperandos.size() < 3){
            int i=0;
            while(!digitosEOperandos.isEmpty()){
                resultado.append(digitosEOperandos.get(i));
                digitosEOperandos.remove(i);
            }
            return resultado.toString();
        }
        if(digitosEOperandos.contains("X") || digitosEOperandos.contains("/")){
            String operador = digitosEOperandos.get(1);
            float operandoEsquerda = Float.parseFloat(digitosEOperandos.get(0));
            float operandoDireita = Float.parseFloat(digitosEOperandos.get(2));
            float res =0;
            switch (operador){
                case "X":
                    res = operandoEsquerda * operandoDireita;
                    resultado.append(res);
                    break;
                case "/":
                    if(operandoDireita == 0){
                        resultado.append("ERRO");
                    }else{
                        res = operandoEsquerda / operandoDireita;
                        resultado.append(res);
                    }
                    break;
            }
        }
        if(digitosEOperandos.contains("+") || digitosEOperandos.contains("-")){
            String operador = digitosEOperandos.get(1);
            float operandoDireita = Float.parseFloat(digitosEOperandos.get(2));
            float res = Float.parseFloat(digitosEOperandos.get(0));
            if(operador.equals("+")){
                res += operandoDireita;
            }
            if(operador.equals("-")){
                res -= operandoDireita;
            }
            resultado = new StringBuilder(String.valueOf(res));
        }
        return resultado.toString();
    }


    public List<String> separaDigitosEOperandos(){
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(char c : et.getText().toString().toCharArray()){
            if(Character.isDigit(c) || c == '.'){
                sb.append(c);
            }else{
                list.add(sb.toString());
                sb = new StringBuilder();
                list.add(String.valueOf(c));
            }
        }
        if(!sb.toString().isEmpty()){
            list.add(sb.toString());
        }
        return list;
    }
}