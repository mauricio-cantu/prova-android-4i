package com.example.mauriciocantu.prova4i;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpcoesActivity extends AppCompatActivity {

    private Button btCadastrar, btListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcoes_layout);

        inicializaComps();

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itCadastro = new Intent(getApplicationContext(), AcaoActivity.class);
                itCadastro.putExtra("acao", "cadastrar");
                startActivity(itCadastro);
            }
        });

        this.btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itLista = new Intent(getApplicationContext(), ListaActivity.class);
                startActivity(itLista);
            }
        });

    }

    private void inicializaComps(){
        this.btCadastrar = (Button) findViewById(R.id.bt_cadastrar);
        this.btListar = (Button) findViewById(R.id.bt_listar);
    }

}
