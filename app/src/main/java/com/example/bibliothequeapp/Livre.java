package com.example.bibliothequeapp;

import java.io.Serializable;

public class Livre implements Serializable {

    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private boolean disponible;
    private int image;

    public Livre(int id, String titre, String auteur, String isbn, boolean disponible, int image) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.disponible = disponible;
        this.image = image;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getImage() { return image; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}