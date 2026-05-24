package com.example.bibliothequeapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitre, tvAuteur, tvIsbn, tvDisponibilite;
    ImageButton btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Cacher la barre
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Bouton retour
        btnRetour = findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(v -> finish());

        // Récupération des vues
        tvTitre = findViewById(R.id.tvTitre);
        tvAuteur = findViewById(R.id.tvAuteur);
        tvIsbn = findViewById(R.id.tvIsbn);
        tvDisponibilite = findViewById(R.id.tvDisponibilite);

        // Récupération de l'objet Livre
        Livre livre = (Livre) getIntent().getSerializableExtra("livre");

        if (livre != null) {
            tvTitre.setText(livre.getTitre());
            tvAuteur.setText("Auteur : " + livre.getAuteur());
            tvIsbn.setText("ISBN : " + livre.getIsbn());

            if (livre.isDisponible()) {
                tvDisponibilite.setText("Disponible");
            } else {
                tvDisponibilite.setText("Indisponible");
            }
        }
    }
}