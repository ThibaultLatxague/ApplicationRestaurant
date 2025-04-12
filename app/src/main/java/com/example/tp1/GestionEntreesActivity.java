package com.example.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GestionEntreesActivity extends AppCompatActivity {
    public Button b_supprimer;
    public Button b_ajouter;
    public Button b_modifier;
    public TextView t_ajouter;
    public TextView t_modifier;
    public Spinner s_modifier;
    public LinearLayout l_checkbox;
    public ArrayList<CheckBox> lstCheck;
    private ArrayAdapter<String> contenuPlats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gest_entrees);

        b_supprimer = findViewById(R.id.b_supprimerGE);
        b_ajouter = findViewById(R.id.b_ajouterGE);
        b_modifier = findViewById(R.id.b_modifierGE);
        t_ajouter = findViewById(R.id.t_estNouveauGE);
        t_modifier = findViewById(R.id.t_modifierGE);
        l_checkbox = findViewById(R.id.l_checkboxGE);
        s_modifier = findViewById(R.id.s_modifierGE);
        lstCheck = new ArrayList<CheckBox>();

        afficherChk();
        affSpiderPlats();

        b_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = lstCheck.size()-1; i >= 0; i--) {
                    CheckBox check = lstCheck.get(i);
                    if (lstCheck.get(i).isChecked()) {
                        lstCheck.remove(i);
                        Modele.lesEntrees.remove(i);
                    }
                }
                afficherChk();
            }
        });

        b_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!t_ajouter.getText().toString().isEmpty()){
                    String texte = t_ajouter.getText().toString();
                    Modele.lesEntrees.add(texte);
                    t_ajouter.setText("");
                    afficherChk();
                }
            }
        });

        s_modifier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    t_modifier.setText(s_modifier.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!t_modifier.getText().toString().isEmpty() && s_modifier.getSelectedItem()!=null){
                    int itemSelectedPosition = s_modifier.getSelectedItemPosition();
                    Modele.lesEntrees.set(itemSelectedPosition, t_modifier.getText().toString());
                    s_modifier.setSelection(0);
                    t_modifier.setText("");
                    afficherChk();
                }
            }
        });
    }

    public void afficherChk() {
        l_checkbox.removeAllViews();
        lstCheck.clear();
        for(int i=0;i<Modele.lesEntrees.size();i++) {
            LinearLayout unlayout = new LinearLayout(this);
            unlayout.setOrientation(LinearLayout.HORIZONTAL);
            CheckBox unchk = new CheckBox(this);
            unchk.setText(Modele.lesEntrees.get(i));
            lstCheck.add(unchk);
            unlayout.addView(unchk);
            l_checkbox.addView(unlayout);
        }
    }

    public void affSpiderPlats() {
        contenuPlats = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < Modele.lesEntrees.size(); i++) {
            contenuPlats.add(Modele.lesEntrees.get(i));
        }
        s_modifier.setAdapter(contenuPlats);
    }
}