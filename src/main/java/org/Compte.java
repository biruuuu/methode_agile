package org;

import java.sql.Date;

public class Compte {
    protected String nom; 
    protected String prenom; 
    protected String mail; 
    protected String telephone;
    protected int id; 
    protected Date date; 
    protected String mdp; 
    protected static int incr = 1; 

    public Compte(String nom, String prenom, String mail, String telephone, Date date){
        this.nom = nom; 
        this.prenom = prenom; 
        this.mail = mail; 
        this.telephone = telephone; 
        this.id = incr++; 
        this.date = date; 
        this.mdp = "1234"; 


    }

    public void setNom(String nom){
        this.nom = nom; 
    }

    public void setPrenom(String prenom){
        this.prenom = prenom; 
    }

    public void setAdresseMail(String mail){
        this.mail = mail; 
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public void setMdp(){
        this.mdp = mdp; 
    }

    public String getNom(){
        return nom + prenom; 
    }

    public String getMail(){
        return mail; 
    }

    public String getTelephone(){
        return telephone; 
    }

    public int getId(){
        return id; 
    }

    public String getMdp(){
        return mdp; 
    }


}
