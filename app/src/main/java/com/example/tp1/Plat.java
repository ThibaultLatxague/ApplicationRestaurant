package com.example.tp1;

public class Plat {
    private long idP;
    private String libelleP;
    private long idT; //ID type plat

    public Plat(long idP, String libelleP, long idT){
        this.idP = idP;
        this.libelleP = libelleP;
        this.idT = idT;
    }

    public Plat(String libelle, long idT){
        this.idP = -1;
        this.libelleP = libelle;
        this.idT = idT;
    }

    public long getIdP() {
        return idP;
    }

    public long getIdT() {
        return idT;
    }

    public String getLibelleP() {
        return libelleP;
    }

    public void setIdP(long idP) {
        this.idP = idP;
    }

    public void setIdT(long idT) {
        this.idT = idT;
    }

    public void setLibelleP(String libelleP) {
        this.libelleP = libelleP;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "idP=" + idP +
                ", libelleP='" + libelleP + '\'' +
                ", idT=" + idT +
                '}';
    }
}
