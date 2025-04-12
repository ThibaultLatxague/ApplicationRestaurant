package com.example.tp1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {
    private String table_typePlat="create table typePlat ("
            + "idT INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nomT TEXT NOT NULL);";

    private String table_plat="create table plat ("
            + "idP INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "libelleP TEXT NOT NULL,"
            + "idT INTEGER,"
            + "foreign key (idT) references typePlat(idT));";

    public BdSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(table_plat);
        db.execSQL(table_typePlat);

        db.execSQL("insert into plat (libelleP, idT) values('Aucun',1);");
        db.execSQL("insert into plat (libelleP, idT) values('Foie gras',1);");
        db.execSQL("insert into plat (libelleP, idT) values('Aucun',2);");
        db.execSQL("insert into plat (libelleP, idT) values('Spaghetti',2);");
        db.execSQL("insert into plat (libelleP, idT) values('Aucun',3);");
        db.execSQL("insert into plat (libelleP, idT) values('Tiramisu',3);");

        db.execSQL("insert into typePlat (nomT,idT) values('Entrée',1);");
        db.execSQL("insert into typePlat (nomT,idT) values('Plat',2);");
        db.execSQL("insert into typePlat (nomT,idT) values('Dessert',3);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
