package main.java.org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.InputStream;
import java.net.Socket;

public class JSONBuilder {

    private String path;
    private Database db;

    public JSONBuilder(String path) {
        this.path = path;
    }

    public void init() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream inputStream = JSONBuilder.class.getResourceAsStream(this.path);

            if (inputStream == null) {
                System.out.println("Error: data.json file not found in resources!");
                return;
            }

            // 3. Automatically map the JSON structure into our Java Object
            db = mapper.readValue(inputStream, Database.class);

            System.out.println("JSON chargé avec succès depuis " + this.path);
            System.out.printf(db.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeDb() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // indent du json
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            String cleanPath = this.path.startsWith("/") ? this.path.substring(1) : this.path;
            File file = new File("src/main/resources/" + cleanPath);

            mapper.writeValue(file, this.db);

            System.out.println("Base de données sauvegardée avec succès dans " + file.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde du JSON : ");
            e.printStackTrace();
        }
    }

    public void updateCompte(int id, Compte newCompte) {
        Compte compteAModif = db.comptes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if(compteAModif != null) {
            int ind = db.comptes.indexOf(compteAModif);
            db.comptes.set(ind, newCompte);
            System.out.printf("Compte mis à jour avec succès!\n");
        } else {
            db.comptes.add(newCompte);
        }
    }

    public void addCompte(Compte newCompte) {
        updateCompte(-1, newCompte);
    }

    public void updateLivre(int isbn, Livre newLivre) {
        Livre livreAModif = db.livres.stream()
                .filter(l -> l.getIsbn() == isbn)
                .findFirst()
                .orElse(null);

        if(livreAModif != null) {
            int ind = db.livres.indexOf(livreAModif);
            System.out.printf("Livre mis à jour avec succès!\n");
        } else {
            db.livres.add(newLivre);
        }
    }

    public void addLivre(Livre newLivre) {
       updateLivre(-1, newLivre); 
    }

    public void updateExemplaire(int id, Exemplaire newEx) {
      Exemplaire exAModif = db.exemplaires.stream()
        .filter(e -> e.getId() == id)
        .findFirst()
        .orElse(null);

      if(exAModif != null) {
        int ind = db.exemplaires.indexOf(exAModif);
        System.out.println("Exemplaire mis a jour avec succès!");
      } else {
        db.exemplaires.add(newEx);
      }
    } 

    public void addExemplaire(Exemplaire newExemplaire) {
      updateExemplaire(-1, newExemplaire);
    }
}
