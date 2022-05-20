package com.example.comprasdesconto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.comprasdesconto.R;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_pastel,cb_pizza,cb_lazanha,cb_refri,cb_pudim;
    EditText et_valorPago;
    RadioGroup rg_grupo;
    TextView tv_valorP;
    Button bt_valor,bt_efetuar;
    double valorC =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_pastel = findViewById(R.id.cb_pastel);
        cb_pizza = findViewById(R.id.cb_pizza);
        cb_lazanha = findViewById(R.id.cb_lazanha);
        cb_refri = findViewById(R.id.cb_refri);
        cb_pudim = findViewById(R.id.cb_pudim);
        bt_valor = findViewById(R.id.bt_valor);
        tv_valorP = findViewById(R.id.tv_valorT);
        rg_grupo = findViewById(R.id.radioGroup2);
        et_valorPago = findViewById(R.id.et_valorPago);
        bt_efetuar = findViewById(R.id.bt_efetuar);

        bt_valor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorC=0.0;

                if(cb_pastel.isChecked()){
                    valorC += 5;
                }
                if(cb_pizza.isChecked()){
                    valorC += 20;
                }
                if(cb_lazanha.isChecked()){
                    valorC += 15;
                }
                if(cb_refri.isChecked()){
                    valorC += 6.50;
                }
                if(cb_pudim.isChecked()){
                    valorC += 4.75;
                }
                tv_valorP.setText("Valor: "+valorC);


            }
        });
        bt_efetuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int op = rg_grupo.getCheckedRadioButtonId();
                String desconto="";
                double valorTotal=0.0,troco=0.0;
                double valorP = 0.0;
                valorP = Double.parseDouble(et_valorPago.getText().toString());

                if(op == R.id.rb_sem){
                    valorTotal = valorP;
                    desconto="Sem desconto";
                    troco = 0.0;
                }
                else if(op == R.id.rb_5){
                    valorTotal = valorC - (valorC*0.05);
                    desconto= "5%";
                    troco = valorC - valorTotal;
                }
                else if(op == R.id.rb_10){
                    valorTotal = valorC - (valorC*0.1);
                    desconto = "10%";
                    troco = valorP-valorTotal;
                }
                else if(op == R.id.rb_15){
                    valorTotal = valorC - (valorC*0.15);
                    desconto = "15%";
                    troco = valorP-valorTotal;
                }
                else{
                    valorTotal = valorP;
                    desconto="Sem desconto";
                    troco = 0.0;
                }
                if(valorP<valorC){
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("Alerta");
                    janela.setMessage("Valor Pago incompativel com o valor do pedido");
                    janela.show();

                }else{
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("Aviso");
                    janela.setMessage("Valor total da compra: "+valorTotal+"\n"+"Desconto: "+desconto+"\n"+"Valor Pago: "+valorP+"\n"+"Troco: "+troco);
                    janela.show();

                }

            }
        });

    }
}