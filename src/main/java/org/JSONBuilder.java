package org;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;
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

    public void updateCompte(int id, Compte newCompte) {
        Compte compteAModif = db.comptes.stream()
                .filter(c -> c.getId() == id) // Remplace getId() par le bon nom de ton getter d'ID
                .findFirst()
                .orElse(null);

        if(compteAModif != null) {
            int ind = db.comptes.indexOf(compteAModif);
            db.comptes.set(ind, newCompte);
            System.out.printf("Compte mis à jour avec succès!");
        } else {
            System.out.printf("Erreur lors de la mise à jour");
        }
    }
}
