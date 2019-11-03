package com.silvio.listadisciplinas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private MeuAdapter meuAdapter;
    private ArrayList<ItemLista> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listaDisciplinas);
        listView.setOnItemClickListener(this);

        itens = new ArrayList<>();
        ItemLista item1 = new ItemLista("Poo");
        ItemLista item2 = new ItemLista("Algoritmo");
        ItemLista item3 = new ItemLista("DM 1");

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);

        meuAdapter = new MeuAdapter(this, itens);
        listView.setAdapter(meuAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemLista item = meuAdapter.getItem(position);
        Toast.makeText(this, "Voce clicou em: "+ item.getInfo(), Toast.LENGTH_SHORT).show();
    }
}
