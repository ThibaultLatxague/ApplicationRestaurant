package com.example.tp1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

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
    private Button b_gestionEntrees;
    private Button b_gestionPlats;
    private Button b_gestionDesserts;
    private PlatDAO unPlatDAO;
    private TypePlatDAO unTypePlatDAO;
    private ArrayList<Plat> lstPlats;
    private ArrayList<TypePlat> lstTypesPlats;
    private ArrayAdapter<String> contenuPlats;

    @SuppressLint("MissingInflatedId")
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

        Modele.initEntrées();
        Modele.initPlats();
        Modele.initDesserts();

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
        b_gestionEntrees = findViewById(R.id.b_gestion_entrees);
        b_gestionPlats = findViewById(R.id.b_gestion_plats);
        b_gestionDesserts = findViewById(R.id.b_gestion_desserts);

        unPlatDAO = new PlatDAO(this);
        unTypePlatDAO = new TypePlatDAO(this);

        lstPlats = unPlatDAO.getPlats();
        lstTypesPlats = unTypePlatDAO.getTypePlats();

        // Remplir le spinner des plats
        affSpiderEntrees();
        affSpiderPlats();
        affSpiderDesserts();

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

        b_gestionEntrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(getApplicationContext(), GestionEntreesActivity.class);
                startActivity(unIntent);
            }
        });

        b_gestionPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(getApplicationContext(), GestionPlatsActivity.class);
                startActivity(unIntent);
            }
        });

        b_gestionDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(getApplicationContext(), GestionDessertsActivity.class);
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

    public void affSpiderEntrees() {
        ArrayAdapter<String> contenu = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < unPlatDAO.getPlatsByType("Entrée").size(); i++) {
            contenuPlats.add(unPlatDAO.getPlatsByType("Entrée").get(i).getLibelleP());
        }
        s_entrees.setAdapter(contenu);
    }

    public void affSpiderPlats() {
        contenuPlats = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < unPlatDAO.getPlatsByType("Plat").size(); i++) {
            contenuPlats.add(unPlatDAO.getPlatsByType("Plat").get(i).getLibelleP());
        }
        s_plats.setAdapter(contenuPlats);
    }

    public void affSpiderDesserts() {
        ArrayAdapter<String> contenu = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < unPlatDAO.getPlatsByType("Dessert").size(); i++) {
            contenuPlats.add(unPlatDAO.getPlatsByType("Dessert").get(i).getLibelleP());
        }
        s_desserts.setAdapter(contenu);
    }
}