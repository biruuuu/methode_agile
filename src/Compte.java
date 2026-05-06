import java.sql.Date;
import java.time.LocalDate;

public class Compte {
    private String nom; 
    private String prenom; 
    private String mail; 
    private String telephone;
    private int id; 
    private Date date; 
    private static int incr = 1; 

    public Compte(String nom, String prenom, String mail, String telephone, Date date){
        this.nom = nom; 
        this.prenom = prenom; 
        this.mail = mail; 
        this.telephone = telephone; 
        this.id = incr++; 
        this.date = date; 


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


}
