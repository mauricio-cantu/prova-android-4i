package com.example.mauriciocantu.prova4i;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dao.TelevisaoDAO;
import pojo.Televisao;

public class ListaActivity extends ListActivity{

    private ArrayAdapter listaTvAdapter;
    private ArrayList<Televisao> listaTvs;
    private TelevisaoDAO tvDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializaComps();
    }

    private void inicializaComps(){
        tvDAO = new TelevisaoDAO(getApplicationContext());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent itEditar = new Intent(getApplicationContext(), AcaoActivity.class);
        itEditar.putExtra("tv",listaTvs.get(position));
        itEditar.putExtra("acao", "editar");
        startActivity(itEditar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaTvs = tvDAO.listar();
        listaTvAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listaTvs);
        setListAdapter(listaTvAdapter);
    }
}
