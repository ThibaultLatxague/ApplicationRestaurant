package com.example.tp1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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
    private Spinner s_plats;

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
        s_plats = findViewById(R.id.s_plats);
        rg_categorie = findViewById(R.id.rg_plats);

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
                int positionItem = s_plats.getSelectedItemPosition();
                Object nomItem = s_plats.getSelectedItem();
                Log.d("ITEM POSITION", "" + positionItem);
                Log.d("ITEM OBJET", "" + nomItem);
                Log.d("ITEM CATEGORIE", buttonSelected.getText().toString());
            }
        });
    }
}