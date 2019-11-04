package com.silvio.listadisciplinas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MeuAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<ItemLista> itens;

        public MeuAdapter(Context context, ArrayList<ItemLista> itens){
            this.itens = itens;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return itens.size();
        }

        @Override
        public ItemLista getItem(int i) {
            return itens.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            ItemLista item = itens.get(i);
            view = inflater.inflate(R.layout.lista, null);

            TextView texto = view.findViewById(R.id.texto_lista);
            texto.setText(item.getInfo());

            return view;
        }
    }

