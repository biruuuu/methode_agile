package org;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainGestionnaire {
    private static JSONBuilder jsonBuilder;
    private static Database db;
    private static Scanner scanner = new Scanner(System.in);
    private static Gestionnaire gestionnaireConnecte;

    public static void main(String[] args) {
        jsonBuilder = new JSONBuilder("/db.json");
        jsonBuilder.init();
        db = jsonBuilder.getDb();

        if (db == null) {
            System.out.println("Erreur critique : Impossible de charger la base de données.");
            return;
        }

        if (db.getComptes() == null) db.setComptes(new ArrayList<>());
        if (db.getLivres() == null) db.setLivres(new ArrayList<>());
        if (db.getExemplaires() == null) db.setExemplaires(new ArrayList<>());
        if (db.getEmprunts() == null) db.setEmprunts(new ArrayList<>());

        gestionnaireConnecte = new Gestionnaire("Dupond", "Jean", "jean.dupond@village.fr", "0470123456", Date.valueOf(LocalDate.now()));

        int choix = -1;
        while (choix != 0) {
            System.out.println("\n============================================");
            System.out.println("       TERMINAL 1 : ESPACE GESTIONNAIRE     ");
            System.out.println("============================================");
            System.out.println("1. Inscrire un nouveau client");
            System.out.println("2. Enregistrer un prêt");
            System.out.println("3. Enregistrer un retour d'exemplaire");
            System.out.println("4. Afficher tous les comptes enregistrés");
            System.out.println("0. Quitter et sauvegarder");
            System.out.print("Votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine());
                switch (choix) {
                    case 1:
                        actionInscrireClient();
                        break;
                    case 2:
                        actionEnregistrerPretIntelligent();
                        break;
                    case 3:
                        actionEnregistrerRetour();
                        break;
                    case 4:
                        actionAfficherComptes();
                        break;
                    case 0:
                        System.out.println("\nSauvegarde dans db.json...");
                        jsonBuilder.writeDb();
                        System.out.println("Fin de session Gestionnaire.");
                        break;
                    default:
                        System.out.println("Option invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }

    private static void actionInscrireClient() {
        System.out.println("\n[Inscription Nouveau Client]");
        System.out.print("Nom de famille : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String mail = scanner.nextLine();
        System.out.print("Téléphone : ");
        String tel = scanner.nextLine();

        ArrayList<Client> listeTemporaire = new ArrayList<>();
        gestionnaireConnecte.inscrireNouveauClient(listeTemporaire, nom, prenom, mail, tel, Date.valueOf(LocalDate.now()));

        Client nouveauClient = listeTemporaire.get(0);
        jsonBuilder.addCompte(nouveauClient);
    }

    private static void actionEnregistrerPretIntelligent() {
        System.out.println("\n[Enregistrement d'un Prêt]");

        // 1. RECHERCHE DU CLIENT PAR SON NOM
        System.out.print("Entrez le nom du client : ");
        String nomRecherche = scanner.nextLine().trim();

        Client clientTrouve = null;
        for (Compte c : db.getComptes()) {
            if (c instanceof Client) {
                // On vérifie si la chaîne brute (contenant nom et prénom) contient le nom recherché
                if (c.getNom() != null && c.getNom().toLowerCase().contains(nomRecherche.toLowerCase())) {
                    clientTrouve = (Client) c;
                    break;
                }
            }
        }

        if (clientTrouve == null) {
            System.out.println("Aucun client trouvé pour le nom : " + nomRecherche);
            return;
        }
        System.out.println("-> Client sélectionné : " + clientTrouve.getMail() + " (ID: " + clientTrouve.getId() + ")");

        // 2. RECHERCHE DU LIVRE PAR SON TITRE
        System.out.print("Entrez le titre du livre à prêter : ");
        String titreRecherche = scanner.nextLine().trim();

        Livre livreTrouve = null;
        for (Livre l : db.getLivres()) {
            if (l.getTitre() != null && l.getTitre().toLowerCase().contains(titreRecherche.toLowerCase())) {
                livreTrouve = l;
                break;
            }
        }

        if (livreTrouve == null) {
            System.out.println("Aucun livre trouvé avec le titre : " + titreRecherche);
            return;
        }
        System.out.println("-> Livre trouvé : '" + livreTrouve.getTitre() + "' (ISBN: " + livreTrouve.getIsbn() + ")");

        // 3. TROUVER UN EXEMPLAIRE DISPONIBLE POUR CE LIVRE
        ArrayList<Exemplaire> exemplairesDuLivre = db.getExemplaires(livreTrouve);
        Exemplaire exemplaireDisponible = null;

        for (Exemplaire ex : exemplairesDuLivre) {
            if (ex.estDisponible()) {
                exemplaireDisponible = ex;
                break; // On prend le premier disponible trouvé
            }
        }

        if (exemplaireDisponible == null) {
            System.out.println("Désolé, aucun exemplaire n'est disponible actuellement pour ce livre.");
            return;
        }
        System.out.println("-> Exemplaire sélectionné automatiquement (ID de l'exemplaire : " + exemplaireDisponible.getId() + ")");

        // 4. VALIDATION ET ENREGISTREMENT DU PRÊT
        gestionnaireConnecte.enregistrerPret(exemplaireDisponible);
        jsonBuilder.updateExemplaire(exemplaireDisponible.getId(), exemplaireDisponible);

        Emprunt emprunt = new Emprunt(clientTrouve, exemplaireDisponible);
        db.getEmprunts().add(emprunt);

        clientTrouve.ajoutExemplaire(exemplaireDisponible);
        jsonBuilder.updateCompte(clientTrouve.getId(), clientTrouve);

        System.out.println("Prêt enregistré avec succès !");
    }

    private static void actionEnregistrerRetour() {
        System.out.println("\n[Enregistrement d'un Retour]");
        System.out.print("ID de l'exemplaire retourné : ");
        int idEx = Integer.parseInt(scanner.nextLine());

        Exemplaire exemplaireTrouve = null;
        for (Exemplaire e : db.getExemplaires()) {
            if (e.getId() == idEx) {
                exemplaireTrouve = e;
                break;
            }
        }

        if (exemplaireTrouve == null) {
            System.out.println("Exemplaire introuvable.");
            return;
        }

        gestionnaireConnecte.enregistrerRetour(exemplaireTrouve);
        jsonBuilder.updateExemplaire(exemplaireTrouve.getId(), exemplaireTrouve);

        db.getEmprunts().removeIf(emprunt -> emprunt.getExemplaire().getId() == idEx);
        System.out.println("Le retour a bien été traité.");
    }

    private static void actionAfficherComptes() {
        System.out.println("\n--- LISTE DES COMPTES ENREGISTRÉS ---");
        if (db.getComptes().isEmpty()) {
            System.out.println("Aucun compte présent.");
        } else {
            for (Compte c : db.getComptes()) {
                System.out.println("[ID: " + c.getId() + "] Type: " + c.getClass().getSimpleName() + " | Mail: " + c.getMail());
            }
        }
    }
}