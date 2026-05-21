package main.java.org;

import java.sql.Date;
import java.util.List;

public class Gestionnaire extends Compte {

    public Gestionnaire(String nom, String prenom, String mail, String telephone, Date date) {
        super(nom, prenom, mail, telephone, date);
    }

    public void inscrireNouveauClient(List<Client> annuaire, String nom, String prenom, String mail, String tel, Date date) {
        Client nouveau = new Client(nom, prenom, mail, tel, date);
        annuaire.add(nouveau);
        System.out.println("Le gestionnaire " + this.nom + " a créé le compte de " + prenom + " " + nom);
    }

    public void enregistrerPret(Exemplaire exemplaire) {
        if (exemplaire.estDisponible()) {
            exemplaire.setDisponibilite(false);
            System.out.println("Prêt enregistré par le gestionnaire : " + this.nom);
        }
    }

    public void enregistrerRetour(Exemplaire exemplaire) {
        exemplaire.setDisponibilite(true);
        System.out.println("Retour validé.");
    }
}