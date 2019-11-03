package com.silvio.listadisciplinas;

public class ItemLista {
    private String info;
    private int foto;

    public ItemLista(){}

    public ItemLista(String info, int foto){
        this.foto = foto;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
