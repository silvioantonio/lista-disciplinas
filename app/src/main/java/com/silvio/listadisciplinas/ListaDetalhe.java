package com.silvio.listadisciplinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaDetalhe extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_detalhe);

        DbHandler dbHandler = new DbHandler(this);
        ArrayList<HashMap<String, String>> lista = dbHandler.GetDisciplinas();
        ListView listView = findViewById(R.id.lista_detalhe);
        ListAdapter listAdapter = new SimpleAdapter(
                ListaDetalhe.this,
                lista,
                R.layout.item_lista,
                new String[]{ "professor", "aluno" },
                new int[]{ R.id.professor, R.id.aluno }
                );
        listView.setAdapter(listAdapter);
    }
}
