package com.silvio.listadisciplinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaDetalhe extends AppCompatActivity {

    private Intent i;
    private Button btnEditar, btnExcluir;
    private TextView txtListaDetalhe, txtProfessor, txtAluno;

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
        txtProfessor = findViewById(R.id.professor);
        txtAluno = findViewById(R.id.aluno);
        btnEditar = findViewById(R.id.btnEditar);
        btnExcluir = findViewById(R.id.btnExcluir);
        System.out.println("PROFESSOR "+txtProfessor);
        System.out.println("Botao "+btnEditar.getId());
    }

    public void clickEditar(View v){
        System.out.println("PROFESSOR "+txtProfessor);
        System.out.println("Botao "+btnEditar.getId());
        Toast.makeText(getApplication(), "Click" + txtProfessor.getText().toString()+
                ", "+txtAluno.getText().toString(), Toast.LENGTH_SHORT).show();
    }

}
