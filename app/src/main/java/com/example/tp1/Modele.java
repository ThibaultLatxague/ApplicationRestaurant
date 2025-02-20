package com.example.tp1;

import java.util.ArrayList;

public class Modele {
    public static ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
    public static int newCommande(){
        Commande uneCommande = new Commande();
        lesCommandes.add(uneCommande);
        return lesCommandes.indexOf(uneCommande);
    }
}
