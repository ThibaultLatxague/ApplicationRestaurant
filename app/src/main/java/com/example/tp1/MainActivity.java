package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private int ind;
    private ImageView logo;
    private Spinner s_entrees;
    private Spinner s_plats;
    private Spinner s_desserts;
    private Spinner s_quantite;
    private EditText i_quantite;
    private TextInputEditText e_remarques;
    private Button b_ajouter;
    private Button b_enregistrer;
    private Button b_annuler;
    private Button b_parametre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ind = Modele.newCommande();

        logo = findViewById(R.id.imageView);
        s_entrees = findViewById(R.id.s_entree);
        s_plats = findViewById(R.id.s_plat);
        s_desserts = findViewById(R.id.s_dessert);
        s_quantite = findViewById(R.id.s_quantite);
        i_quantite = findViewById(R.id.i_quantite);
        e_remarques = findViewById(R.id.e_remarques);
        b_ajouter = findViewById(R.id.b_ajouter);
        b_enregistrer = findViewById(R.id.b_enregistrer);
        b_annuler = findViewById(R.id.b_annuler);
        b_parametre = findViewById(R.id.b_parametre);

        s_quantite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i_quantite.setText(s_quantite.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        i_quantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                s_quantite.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s_quantite.setEnabled(false);
                if(s.toString().isEmpty()) {
                    s_quantite.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        b_parametre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(getApplicationContext(), ParametrageActivity.class);
                startActivity(unIntent);
            }
        });

        b_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commande maCommande = Modele.lesCommandes.get(ind);
                maCommande.getLesEntrees().put(s_entrees.getSelectedItem().toString(), Integer.parseInt(i_quantite.getText().toString()));
                maCommande.getLesPlats().put(s_plats.getSelectedItem().toString(), Integer.parseInt(i_quantite.getText().toString()));
                maCommande.getLesDesserts().put(s_desserts.getSelectedItem().toString(), Integer.parseInt(i_quantite.getText().toString()));
            }
        });
    }
}