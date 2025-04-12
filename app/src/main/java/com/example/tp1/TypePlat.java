package com.example.tp1;

public class TypePlat {
    private long idT;
    private String nomT;

    public TypePlat(long id, String nom){
        this.idT = id;
        this.nomT = nom;
    }

    public TypePlat(String nom) {
        this.idT = -1;
        this.nomT = nom;
    }

    public long getIdT() {
        return idT;
    }

    public void setIdT(long idT) {
        this.idT = idT;
    }

    public String getNomT() {
        return nomT;
    }

    public void setNomT(String nomT) {
        this.nomT = nomT;
    }
}
