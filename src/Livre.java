import java.util.Objects;

public class Livre {
    private String titre;
    private String genre;
    private String auteur;
    private String editeur;
    private int isbn;
    private int nbPages;

    public Livre(String titre, String genre, String auteur, String editeur, int isbn, int nbPages) {
        this.titre = titre;
        this.genre = genre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.isbn = isbn;
        this.nbPages = nbPages;
    }

    public Livre(String titre, String genre, String editeur, int isbn, int nbPages) {
        this(titre, genre, "Anonyme", editeur, isbn, nbPages);
    }

    public Livre(String titre, String editeur, int isbn, int nbPages) {
        this(titre, "Sans genre", "Anonyme", editeur, isbn, nbPages);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return isbn == livre.isbn && nbPages == livre.nbPages && Objects.equals(titre, livre.titre) && Objects.equals(genre, livre.genre) && Objects.equals(auteur, livre.auteur) && Objects.equals(editeur, livre.editeur);
    }

    public String getGenre() {
        return genre;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getNbPages() {
        return nbPages;
    }
}
