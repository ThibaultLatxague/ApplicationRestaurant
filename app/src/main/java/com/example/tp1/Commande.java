package com.example.tp1;

import java.util.Hashtable;

public class Commande {
    private Hashtable<String,Integer> lesPlats;
    private Hashtable<String,Integer> lesEntrees ;
    private Hashtable<String,Integer> lesDesserts ;
    private String remarques ;
    public Commande() {
        lesPlats = new Hashtable<String,Integer>();
        lesEntrees = new Hashtable<String,Integer>();
        lesDesserts = new Hashtable<String,Integer>();
        remarques="";
    }
    public Hashtable<String, Integer> getLesPlats() {
        return lesPlats;
    }
    public Hashtable<String, Integer> getLesEntrees() {
        return lesEntrees;
    }
    public Hashtable<String, Integer> getLesDesserts() {
        return lesDesserts;
    }
    public String getRemarques() {
        return remarques;
    }
    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }
}