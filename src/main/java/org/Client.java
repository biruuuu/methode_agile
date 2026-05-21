package org;

import java.sql.Date;
import java.util.ArrayList;

public class Client extends Compte {

    private ArrayList<Exemplaire> emprunts; 

    public Client(String nom, String prenom, String mail, String telephone, Date date ){
        super(nom, prenom, mail, telephone, date); 
        this.emprunts = new ArrayList<Exemplaire>(); 
    }

    public void ajoutExemplaire(Exemplaire ouvrage){
        this.emprunts.add(ouvrage); 
    }


    

}
