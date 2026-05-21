package main.java.org;

import java.util.ArrayList;

public class Database {
    public ArrayList<Livre> livres;
    public ArrayList<Exemplaire> exemplaires;
    public ArrayList<Compte> comptes;
    public ArrayList<Emprunt> emprunts;

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

    @Override
    public String toString() {
        return "Database{" +
                "livres=" + livres.toString() +
                ", exemplaires=" + exemplaires.toString() +
                ", comptes=" + comptes.toString() +
                ", emprunts=" + emprunts.toString() +
                '}';
    }
}
