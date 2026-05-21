package org;

import java.util.ArrayList;

public class Database {
    private ArrayList<Livre> livres;
    private ArrayList<Exemplaire> exemplaires;
    private ArrayList<Compte> comptes;
    private ArrayList<Emprunt> emprunts;

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

    public ArrayList<Exemplaire> getExemplaires(Livre livre){
        ArrayList<Exemplaire> exemplairesLivre = new ArrayList<>();
        for(Exemplaire exemplaire : this.exemplaires){
            if(exemplaire.getLivre().equals(livre)){
                exemplairesLivre.add(exemplaire);
            }
        }
        return exemplairesLivre;
    }

    public int getNbExemplaires(Livre livre){
        return getExemplaires(livre).size();
    }

    public ArrayList<Emprunt> getEmpruntsClient(Client client){
        ArrayList<Emprunt> empruntsClient = new ArrayList<>();
        for(Emprunt emprunt : this.emprunts){
            if(emprunt.getClient().equals(client)){
                empruntsClient.add(emprunt);
            }
        }
        return empruntsClient;
    }
}
