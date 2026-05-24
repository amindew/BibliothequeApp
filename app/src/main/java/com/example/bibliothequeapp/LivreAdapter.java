package com.example.bibliothequeapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.LivreViewHolder> {

    private ArrayList<Livre> listeLivres;

    public LivreAdapter(ArrayList<Livre> listeLivres) {
        this.listeLivres = listeLivres;
    }

    @NonNull
    @Override
    public LivreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_livre, parent, false);
        return new LivreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivreViewHolder holder, int position) {
        Livre livre = listeLivres.get(position);

        holder.tvTitreLivre.setText(livre.getTitre());
        holder.tvAuteurLivre.setText("Auteur : " + livre.getAuteur());
        holder.tvIsbnLivre.setText("ISBN : " + livre.getIsbn());
        holder.imgCouverture.setImageResource(livre.getImage());

        if (livre.isDisponible()) {
            holder.tvDisponibilite.setText("Disponible");
            holder.tvDisponibilite.setBackgroundResource(R.drawable.badge_arrondi);
        } else {
            holder.tvDisponibilite.setText("Indisponible");
            holder.tvDisponibilite.setBackgroundResource(R.drawable.badge_rouge);
        }

        // Clic simple : ouvrir le détail
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("livre", livre);
            v.getContext().startActivity(intent);
        });

        // Clic long : ouvrir le formulaire en mode modification
        holder.itemView.setOnLongClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddEditActivity.class);
            intent.putExtra(AddEditActivity.EXTRA_MODE, AddEditActivity.MODE_EDIT);
            intent.putExtra(AddEditActivity.EXTRA_LIVRE, livre);
            intent.putExtra(AddEditActivity.EXTRA_POSITION, holder.getAdapterPosition());
            v.getContext().startActivity(intent);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listeLivres.size();
    }

    public static class LivreViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitreLivre;
        TextView tvAuteurLivre;
        TextView tvIsbnLivre;
        TextView tvDisponibilite;
        ImageView imgCouverture;

        public LivreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitreLivre = itemView.findViewById(R.id.tvTitreLivre);
            tvAuteurLivre = itemView.findViewById(R.id.tvAuteurLivre);
            tvIsbnLivre = itemView.findViewById(R.id.tvIsbnLivre);
            tvDisponibilite = itemView.findViewById(R.id.tvDisponibilite);
            imgCouverture = itemView.findViewById(R.id.imgCouverture);
        }
    }
}