package com.silvio.listadisciplinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaDetalhe extends AppCompatActivity {

    private Intent i;
    private TextView txtListaDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_detalhe);
        txtListaDetalhe = findViewById(R.id.txtListaDetalhe);
        i = getIntent();

        txtListaDetalhe.setText(i.getStringExtra("info"));

        DbHandler dbHandler = new DbHandler(this);
        ArrayList<HashMap<String, String>> lista = dbHandler.getListaPorDisciplina(i.getStringExtra("info"));
        ListView listView = findViewById(R.id.lista_detalhe);
        ListAdapter listAdapter = new SimpleAdapter(ListaDetalhe.this, lista, R.layout.item_lista,
                new String[]{ "professor", "aluno" }, new int[]{ R.id.professor, R.id.aluno }
                );
        listView.setAdapter(listAdapter);
    }
}
