package com.silvio.listadisciplinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView, preview;
    private MeuAdapter meuAdapter;
    private ArrayList<ItemLista> itens;
    private EditText disciplina, professor, aluno;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listaDisciplinas);
        disciplina = findViewById(R.id.txtDisciplina);
        professor = findViewById(R.id.txtProfessor);
        aluno = findViewById(R.id.txtAluno);
        btnAdd = findViewById(R.id.btnAdd);

        listView.setOnItemClickListener(this);

        itens = new ArrayList<>();
        final ItemLista item1 = new ItemLista("Poo");
        ItemLista item2 = new ItemLista("Algoritmo");
        ItemLista item3 = new ItemLista("DM 1");

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);

        meuAdapter = new MeuAdapter(this, itens);
        listView.setAdapter(meuAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdisciplina = disciplina.getText().toString();
                String sprofessor = professor.getText().toString();
                String saluno = aluno.getText().toString();
                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.inserDisciplinaDetalhes(sdisciplina, sprofessor, saluno);
                disciplina.setText("");
                professor.setText("");
                aluno.setText("");
                Toast.makeText(getApplicationContext(), "Nova entrada adicionada! ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemLista item = meuAdapter.getItem(position);
        //preencher os itens com nomes dos alunos dessa disciplina

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmento, new MainFragment());
        fragmentTransaction.commit();
        */
        Intent i = new Intent(MainActivity.this, ListaDetalhe.class);
        i.putExtra("info", item.getInfo());
        startActivity(i);
        Toast.makeText(this, "Voce clicou em: "+ item.getInfo(), Toast.LENGTH_SHORT).show();
    }


}
