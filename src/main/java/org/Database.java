package org;

import java.util.ArrayList;

public class Database {
    ArrayList<Livre> livres;
    ArrayList<Exemplaire> exemplaires;
    ArrayList<Compte> comptes;
    ArrayList<Emprunt> emprunts;

    public ArrayList<Livre> getLivres() {
        return livres;
    }

    public void setLivres(ArrayList<Livre> livres) {
        this.livres = livres;
    }

    public ArrayList<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(ArrayList<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(ArrayList<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
