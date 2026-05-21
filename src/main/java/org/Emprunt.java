package org;

import java.sql.Date;
import java.time.LocalDate;

public class Emprunt {
    private Client client;
    private Exemplaire exemplaire;
    private Date dateEmprunt;
    private Date dateRetourPrevue;

    public Emprunt(Client client, Exemplaire exemplaire) {
        this.client = client;
        this.exemplaire = exemplaire;
        this.dateEmprunt = Date.valueOf(LocalDate.now());
        this.dateRetourPrevue = Date.valueOf(LocalDate.now().plusDays(14));
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "org.Emprunt : " + exemplaire.getLivre().getTitre() +
               " par " + client.getNom() + 
               " (Retour prévu le : " + dateRetourPrevue + ")";
    }
}