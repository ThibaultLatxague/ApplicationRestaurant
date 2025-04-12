package com.example.tp1;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
public class TypePlatDAO {
    private static String base = "BDPlatList"; // Change the database name to match your project context
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public TypePlatDAO(Context ct) {
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public TypePlat getTypePlat(long idT) {
        TypePlat leTypePlat = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from typeplat where idT=" + idT + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leTypePlat = new TypePlat(idT, curseur.getString(1)); // Updated to reflect TypePlat
        }
        return leTypePlat;
    }

    public ArrayList<TypePlat> getTypePlats() {
        Cursor curseur;
        String req = "select * from typeplat;";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        return cursorToTypePlatArrayList(curseur);
    }

    private ArrayList<TypePlat> cursorToTypePlatArrayList(Cursor curseur) {
        ArrayList<TypePlat> listeTypePlats = new ArrayList<TypePlat>();
        long idT;
        String nomT;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            idT = curseur.getLong(0);
            nomT = curseur.getString(1);
            listeTypePlats.add(new TypePlat(idT, nomT)); // Creating TypePlat object instead of Categorie
            curseur.moveToNext();
        }

        return listeTypePlats;
    }

}
