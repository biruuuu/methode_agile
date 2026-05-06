public class Exemplaire {
    private static int incr = 1;
    private int id;
    private Livre livre;
    private boolean disponibilite;

    public Exemplaire(Livre livre) {
        this.livre = livre;
        id = incr++;
    }

    public int getId() {
        return id;
    }

    public Livre getLivre() {
        return livre;
    }

    public boolean estDisponible() {
        return disponibilite;
    }

    public void setDisponibilite(boolean estDisponible) {
        this.disponibilite = estDisponible;
    }
}
