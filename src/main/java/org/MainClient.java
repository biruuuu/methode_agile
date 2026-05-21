package org;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClient {
    private static JSONBuilder jsonBuilder;
    private static Database db;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        jsonBuilder = new JSONBuilder("/db.json");
        jsonBuilder.init();
        db = jsonBuilder.getDb();

        if (db == null) {
            System.out.println("Erreur critique : Impossible de charger la base de données.");
            return;
        }

        if (db.getLivres() == null) db.setLivres(new ArrayList<>());
        if (db.getExemplaires() == null) db.setExemplaires(new ArrayList<>());

        int choix = -1;
        while (choix != 0) {
            System.out.println("\n============================================");
            System.out.println("         TERMINAL 2 : ESPACE LECTEUR        ");
            System.out.println("============================================");
            System.out.println("1. Consulter la liste complète des livres");
            System.out.println("2. Vérifier la disponibilité d'un livre");
            System.out.println("0. Quitter la borne");
            System.out.print("Votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine());
                switch (choix) {
                    case 1:
                        afficherCatalogue();
                        break;
                    case 2:
                        consulterExemplairesParTitre();
                        break;
                    case 0:
                        System.out.println("\nMerci de votre visite à la bibliothèque !");
                        break;
                    default:
                        System.out.println("Option inconnue.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un numéro valide.");
            }
        }
    }

    private static void afficherCatalogue() {
        System.out.println("\n--- CATALOGUE DES LIVRES PROPOSÉS ---");
        if (db.getLivres().isEmpty()) {
            System.out.println("La bibliothèque n'a aucun livre actuellement.");
        } else {
            for (Livre l : db.getLivres()) {
                System.out.println("- '" + l.getTitre() + "' par " + l.getAuteur() + " (ISBN: " + l.getIsbn() + ")");
            }
        }
    }

    private static void consulterExemplairesParTitre() {
        System.out.print("\nEntrez le titre du livre recherché : ");
        String titreRecherche = scanner.nextLine().trim();

        Livre livreTrouve = null;
        for (Livre l : db.getLivres()) {
            if (l.getTitre() != null && l.getTitre().toLowerCase().contains(titreRecherche.toLowerCase())) {
                livreTrouve = l;
                break;
            }
        }

        if (livreTrouve == null) {
            System.out.println("Aucun livre ne correspond à ce titre.");
            return;
        }

        int totalExemplaires = db.getNbExemplaires(livreTrouve);
        System.out.println("\nRésultat pour '" + livreTrouve.getTitre() + "' (" + totalExemplaires + " exemplaire(s) au total) :");

        ArrayList<Exemplaire> listeExs = db.getExemplaires(livreTrouve);
        if (listeExs.isEmpty()) {
            System.out.println("  Aucun exemplaire physique disponible pour ce livre.");
        } else {
            for (Exemplaire ex : listeExs) {
                String statut = ex.estDisponible() ? "Disponible en rayon" : "Déjà emprunté";
                System.out.println("  -> Exemplaire ID: " + ex.getId() + " | Statut : " + statut);
            }
        }
    }
}