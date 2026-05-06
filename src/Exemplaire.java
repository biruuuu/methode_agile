public class Exemplaire {
    private static int incr = 1;
    private int id;
    private Livre livre;

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
}
