package com.silvio.listadisciplinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "disciplina2db";
    private static final String TABLE_Disciplinas = "detalhesdisciplina";

    private static final String KEY_ID = "id";
    private static final String KEY_DISCIPLINA = "disciplina";
    private static final String KEY_PROFESSOR = "professor";
    private static final String KEY_ALUNO = "aluno";

    public DbHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+ TABLE_Disciplinas + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_DISCIPLINA + " TEXT, "+ KEY_PROFESSOR + " TEXT, " + KEY_ALUNO + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drestroi tabela anterior se nao existir
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Disciplinas);

        // CRIA TABELA NOVAMENTE
         onCreate(db);
    }

    // adiciona novos itens a tabela
    void inserDisciplinaDetalhes(String disciplina, String professor, String aluno) {
        // pega os dados no modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        // cria um novo mapa de valores, onde os nomes das colunas sao as chaves
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DISCIPLINA, disciplina);
        contentValues.put(KEY_PROFESSOR, professor);
        contentValues.put(KEY_ALUNO, aluno);

        // insere a nova linha, retornando a primeira chave valor da nova linha
        long idNovaLinha = db.insert(TABLE_Disciplinas, null, contentValues);

        db.close();
    }

    // retorna detalhe da disciplina
    public ArrayList<HashMap<String, String>> getDisciplinas() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> lista = new ArrayList<>();
        String query = "SELECT professor, aluno FROM " + TABLE_Disciplinas;
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            HashMap<String, String> disciplina = new HashMap<>();
            disciplina.put("professor", cursor.getString(cursor.getColumnIndex(KEY_PROFESSOR)));
            disciplina.put("aluno", cursor.getString(cursor.getColumnIndex(KEY_ALUNO)));
            lista.add(disciplina);
        }
        return lista;
    }

    //retorna por disciplina
    public ArrayList<HashMap<String, String>> getListaPorDisciplina(String disciplina){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> lista = new ArrayList<>();

        String query = "SELECT professor, aluno FROM "+ TABLE_Disciplinas;

        Cursor cursor = db.query(TABLE_Disciplinas, new String[]{KEY_PROFESSOR, KEY_ALUNO},
                KEY_DISCIPLINA+ "=?",new String[]{disciplina},null, null, null, null);

        while(cursor.moveToNext()) {
            HashMap<String, String> mapaDisciplina = new HashMap<>();
            mapaDisciplina.put("professor", cursor.getString(cursor.getColumnIndex(KEY_PROFESSOR)));
            mapaDisciplina.put("aluno", cursor.getString(cursor.getColumnIndex(KEY_ALUNO)));
            lista.add(mapaDisciplina);
        }
        return lista;
    }


}
