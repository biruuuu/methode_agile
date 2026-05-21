package org;

import java.sql.Date;
import java.time.LocalDate;

public class Emprunt {
    private Client client;
    private Exemplaire exemplaire;
    private Date dateEmprunt;
    private Date dateRetourPrevue;

    public Emprunt() {
    }

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

    public void setClient(Client client) {
        this.client = client;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(Date dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }
}