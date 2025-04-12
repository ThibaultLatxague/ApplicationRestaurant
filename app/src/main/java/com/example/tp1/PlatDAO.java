package com.example.tp1;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class PlatDAO {
    private static String base = "BDRestaurant"; // Update to PlatList
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public PlatDAO(Context ct) {
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Plat getEntree(long idP) {
        Plat lePlat = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from plat where idP=" + idP + "and idT =" + 1 + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            lePlat = new Plat(idP, curseur.getString(1), curseur.getLong(2)); // Changed Tache -> Plat
        }
        return lePlat;
    }

    public Plat getPlat(long idP) {
        Plat lePlat = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from plat where idP=" + idP + "and idT =" + 2 + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            lePlat = new Plat(idP, curseur.getString(1), curseur.getLong(2)); // Changed Tache -> Plat
        }
        return lePlat;
    }

    public Plat getDessert(long idP) {
        Plat lePlat = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from plat where idP=" + idP + "and idT =" + 3 + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            lePlat = new Plat(idP, curseur.getString(1), curseur.getLong(2)); // Changed Tache -> Plat
        }
        return lePlat;
    }

    public void addPlat(Plat unPlat) {
        Log.d("testLog", "insert into plat(libelleP, idT) values('" + unPlat.getLibelleP() + "'," + unPlat.getIdT() + ");");
        accesBD.getWritableDatabase().execSQL("insert into plat(libelleP, idT) values('" + unPlat.getLibelleP() + "'," + unPlat.getIdT() + ");");
        accesBD.close();
    }

    public void delPlat(Plat unPlat) {
        Log.d("testLog", "delete from plat where idP=" + unPlat.getIdP() + ";");
        accesBD.getWritableDatabase().execSQL("delete from plat where idP=" + unPlat.getIdP() + ";");
        accesBD.close();
    }

    public ArrayList<Plat> getPlats() {
        Cursor curseur;
        String req = "select * from plat;";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        return cursorToPlatArrayList(curseur);
    }

    public ArrayList<Plat> getPlatsByType(String nomT) {
        Cursor curseur;
        String req = "select * from plat, typeplat where plat.idT = typeplat.idT and nomT='" + nomT + "';";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        return cursorToPlatArrayList(curseur);
    }

    private ArrayList<Plat> cursorToPlatArrayList(Cursor curseur) {
        ArrayList<Plat> listePlats = new ArrayList<Plat>();
        long idP;
        String libelleP;
        long idT;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            idP = curseur.getLong(0);
            libelleP = curseur.getString(1);
            idT = curseur.getLong(2);
            listePlats.add(new Plat(idP, libelleP, idT));
            curseur.moveToNext();
        }

        return listePlats;
    }
}
