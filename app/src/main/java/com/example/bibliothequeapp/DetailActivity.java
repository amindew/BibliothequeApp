package com.example.bibliothequeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitre, tvAuteur, tvIsbn, tvDisponibilite;
    ImageButton btnRetour;
    Button btnModifier;
    private Livre livreActuel;
    private static final int REQUEST_EDIT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnRetour = findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(v -> finish());

        tvTitre = findViewById(R.id.tvTitre);
        tvAuteur = findViewById(R.id.tvAuteur);
        tvIsbn = findViewById(R.id.tvIsbn);
        tvDisponibilite = findViewById(R.id.tvDisponibilite);
        btnModifier = findViewById(R.id.btnModifier);

        livreActuel = (Livre) getIntent().getSerializableExtra("livre");

        afficherLivre(livreActuel);

        btnModifier.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, AddEditActivity.class);
            intent.putExtra(AddEditActivity.EXTRA_MODE, AddEditActivity.MODE_EDIT);
            intent.putExtra(AddEditActivity.EXTRA_LIVRE, livreActuel);
            startActivityForResult(intent, REQUEST_EDIT);
        });
    }

    private void afficherLivre(Livre livre) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null) {
            Livre livreMod = (Livre) data.getSerializableExtra(AddEditActivity.EXTRA_LIVRE);
            if (livreMod != null) {
                livreActuel = livreMod;
                afficherLivre(livreActuel);
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra(AddEditActivity.EXTRA_MODE, AddEditActivity.MODE_EDIT);
            resultIntent.putExtra(AddEditActivity.EXTRA_LIVRE, livreActuel);
            setResult(RESULT_OK, resultIntent);
        }
    }
}