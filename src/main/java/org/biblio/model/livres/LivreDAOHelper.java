package org.biblio.model.livres;



public class LivreDAOHelper {

    public static Livre createLivre(String type, String titre, String auteur, int annee, String isbn,
                                    String genre, Integer pages, String sujet, Integer numero,
                                    String mois, String univers) {
        return switch (type) {
            case "Roman" -> new Roman(0, titre, auteur, annee, isbn, true, genre, pages != null ? pages : 0);
            case "Biographie" -> new Biographie(0, titre, auteur, annee, isbn, true, sujet);
            case "Magazine" -> new Magazine(0, titre, auteur, annee, isbn, true, numero != null ? numero : 0, mois);
            case "ScienceFiction" -> new ScienceFiction(0, titre, auteur, annee, isbn, true, univers);
            default -> throw new IllegalArgumentException("Type de livre inconnu : " + type);
        };
    }
}
