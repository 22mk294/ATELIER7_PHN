package org.biblio.model.livres;

public abstract class Livre {
    protected int id;
    protected String titre;
    protected String auteur;
    protected int anneePublication;
    protected String isbn;
    protected boolean disponible;

    public Livre(int id, String titre, String auteur, int anneePublication, String isbn, boolean disponible) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
        this.disponible = disponible;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public abstract void afficherDetails();

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneePublication=" + anneePublication +
                ", isbn='" + isbn + '\'' +
                ", disponible=" + disponible +
                '}';
    }

    public String getDetailsAsString() {
        String type = this.getClass().getSimpleName(); // Renvoie "Roman", "Biographie", etc.
        String statut = disponible ? "✅ Disponible" : "❌ Emprunté";
        return titre + " - " + auteur + " (" + anneePublication + ") [" + type + "] - " + statut;
    }



}


