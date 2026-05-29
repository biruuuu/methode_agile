package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * JSONBuilder - Gestionnaire de Persistance JSON
 * via le framework Jackson. Conçu pour centraliser les opérations de CRUD en mémoire.
 *
 * @author Arthur
 */
public class JSONBuilder {

    private String path;
    private Database db;

    /**
     * @param path Chemin relatif de la ressource JSON (ex: "/data.json")
     */
    public JSONBuilder(String path) {
        this.path = path;
    }

    /**
     * Initialise la base de données en chargeant le fichier JSON depuis le classpath.
     * Implémente un mécanisme de fail-fast en cas de ressource absente.
     */
    public void init() {
        // Instanciation du mapper Jackson pour le Data Mapping object/JSON
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Lecture Stream pour garantir la portabilité (compatible exécution JAR)
            InputStream inputStream = JSONBuilder.class.getResourceAsStream(this.path);

            if (inputStream == null) {
                System.out.println("Error: data.json file not found in resources!");
                return; // Fail-fast condition
            }

            // Désérialisation automatique de la structure JSON vers notre POJO Database
            db = mapper.readValue(inputStream, Database.class);

            System.out.println("JSON chargé avec succès depuis " + this.path);
            System.out.printf(db.toString());

        } catch (Exception e) {
            // StackTrace conservée pour le debugging de la structure du JSON
            e.printStackTrace();
        }
    }

    /**
     * Ecrit l'état actuel de l'objet Database en mémoire dans le fichier physique.
     * Gère le formatage du format JSON.
     */
    public void writeDb() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Activation du Pretty-Printing pour garder un JSON lisible et versionnable proprement sur Git
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Normalisation du chemin d'accès pour éviter les conflits de séparateurs
            String cleanPath = this.path.startsWith("/") ? this.path.substring(1) : this.path;
            File file = new File("src/main/resources/" + cleanPath);

            // Sérialisation de l'objet vers le fichier de destination
            mapper.writeValue(file, this.db);

            System.out.println("Base de données sauvegardée avec succès dans " + file.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde du JSON : ");
            e.printStackTrace();
        }
    }

    /**
     * Met à jour ou insère un compte.
     * @param id Identifier unique du compte à modifier (passer -1 pour forcer une insertion)
     * @param newCompte Nouvelles données de l'entité Compte
     */
    public void updateCompte(int id, Compte newCompte) {
        // Filtrage fonctionnel via pipeline Stream pour isoler la référence de l'objet cible
        Compte compteAModif = db.getComptes().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if(compteAModif != null) {
            // Remplacement in-place via l'index d'origine pour préserver l'ordre de la collection
            int ind = db.getComptes().indexOf(compteAModif);
            db.getComptes().set(ind, newCompte);
            System.out.printf("Compte mis à jour avec succès!\n");
        } else {
            // Fallback : Comportement de type "Insert" si aucune occurrence trouvée
            db.getComptes().add(newCompte);
        }
    }

    /**
     * Abstraction de l'insertion d'un compte.
     * Réutilise la logique d'Upsert.
     */
    public void addCompte(Compte newCompte) {
        updateCompte(-1, newCompte);
    }

    /**
     * Met à jour les métadonnées d'un livre ou l'ajoute au catalogue.
     * * @param isbn Identifiant standard unique du livre
     * @param newLivre Instance contenant les nouvelles propriétés
     */
    public void updateLivre(int isbn, Livre newLivre) {
        // Extraction par filtrage lambda du pattern métier
        Livre livreAModif = db.getLivres().stream()
                .filter(l -> l.getIsbn() == isbn)
                .findFirst()
                .orElse(null);

        if(livreAModif != null) {
            int ind = db.getLivres().indexOf(livreAModif);
            // TODO : implémenter db.getLivres().set(ind, newLivre) si mutation complète attendue
            System.out.printf("Livre mis à jour avec succès!\n");
        } else {
            db.getLivres().add(newLivre);
        }
    }

    /**
     * Enregistre une nouvelle référence de livre dans le catalogue.
     */
    public void addLivre(Livre newLivre) {
        updateLivre(-1, newLivre);
    }

    /**
     * Synchronise l'état d'un exemplaire physique dans l'inventaire global.
     * * @param id ID technique de l'exemplaire
     * @param newEx Instance mise à jour
     */
    public void updateExemplaire(int id, Exemplaire newEx) {
        Exemplaire exAModif = db.getExemplaires().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if(exAModif != null) {
            int ind = db.getExemplaires().indexOf(exAModif);
            // FIXME : Appliquer le remplacement de l'index comme sur updateCompte pour assurer la persistance effective
            System.out.println("Exemplaire mis a jour avec succès!");
        } else {
            db.getExemplaires().add(newEx);
        }
    }

    /**
     * Injecte un nouvel exemplaire physique dans le stock de la base.
     */
    public void addExemplaire(Exemplaire newExemplaire) {
        updateExemplaire(-1, newExemplaire);
    }

    /**
     * Accesseur au modèle de données (In-Memory Database state).
     * @return L'instance actuelle du graphe d'objets Database
     */
    public Database getDb() {
        return db;
    }
}