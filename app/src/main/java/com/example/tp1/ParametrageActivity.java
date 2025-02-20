package com.example.tp1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ParametrageActivity extends AppCompatActivity {
    private Button b_ajouter;
    private Button b_supprimer;
    private Button b_modifier;
    private RadioGroup rg_categorie;
    private RadioButton rb_entrees;
    private RadioButton rb_plats;
    private RadioButton rb_desserts;
    private EditText t_nomPlat;
    private EditText t_nomPlatModif;
    private Spinner s_entrees;
    private Spinner s_plats;
    private Spinner s_desserts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_parametrage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b_ajouter = findViewById(R.id.b_ajouter);
        b_supprimer = findViewById(R.id.b_supprimer);
        b_modifier = findViewById(R.id.b_modifier);
        rb_entrees = findViewById(R.id.rb_plats);
        rb_plats = findViewById(R.id.rb_plats);
        rb_desserts = findViewById(R.id.rb_desserts);
        t_nomPlat = findViewById(R.id.t_nomPlat);
        s_entrees = findViewById(R.id.s_entrees);
        s_plats = findViewById(R.id.s_plats);
        s_desserts = findViewById(R.id.s_desserts);
        rg_categorie = findViewById(R.id.rg_plats);
        t_nomPlatModif = findViewById(R.id.t_nomPlatModif);

        b_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typePlat;

                if(rb_entrees.isChecked()) {
                    typePlat = rb_entrees.getText().toString();
                } else if(rb_plats.isChecked()) {
                    typePlat = rb_plats.getText().toString();
                } else if(rb_desserts.isChecked()){
                    typePlat = rb_desserts.getText().toString();
                } else {
                    Log.d("ERREUR", "Aucun bouton radio pressé");
                    typePlat = "ERROR";
                }

                Log.d("nomNvPlat", t_nomPlat.getText().toString() + " " + typePlat);
            }
        });

        b_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton buttonSelected = findViewById(rg_categorie.getCheckedRadioButtonId());
                Log.d("TEST1", "" + s_entrees.getSelectedItemPosition());
                Log.d("TEST1", "" + s_plats.getSelectedItemPosition());
                Log.d("TEST1", "" + s_desserts.getSelectedItemPosition());

                if(s_entrees.getSelectedItemPosition() != 0) {
                    String item = s_entrees.getSelectedItem().toString();
                    item += " " + s_entrees.getSelectedItemPosition();
                    item += " Entree";
                    Log.d("ITEM SUPPRIME", item);
                }

                if(s_plats.getSelectedItemPosition() != 0) {
                    String item = s_plats.getSelectedItem().toString();
                    item += " " + s_plats.getSelectedItemPosition();
                    item += " Plat";
                    Log.d("ITEM SUPPRIME", item);
                }

                if(s_desserts.getSelectedItemPosition() != 0) {
                    String item = s_desserts.getSelectedItem().toString();
                    item += " " + s_desserts.getSelectedItemPosition();
                    item += " Dessert";
                    Log.d("ITEM SUPPRIME", item);
                }
            }
        });

        b_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entree = s_entrees.getSelectedItem().toString();
                String plat = s_plats.getSelectedItem().toString();
                String dessert = s_desserts.getSelectedItem().toString();
                String nomModifie = t_nomPlatModif.getText().toString();

                if(nomModifie.equals(entree) || nomModifie.equals(plat) || nomModifie.equals(dessert)) {
                    Log.d("MODIFICATION", "Aucune modification enregistrée, les noms sont identiques");
                } else {
                    Log.d("MODIFICATION", "Nom différents : modification enregistrée");
                }
            }
        });

        s_entrees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    s_plats.setSelection(0);
                    s_desserts.setSelection(0);
                    t_nomPlatModif.setText(s_entrees.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s_plats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    s_entrees.setSelection(0);
                    s_desserts.setSelection(0);
                    t_nomPlatModif.setText(s_plats.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s_desserts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    s_entrees.setSelection(0);
                    s_plats.setSelection(0);
                    t_nomPlatModif.setText(s_desserts.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}