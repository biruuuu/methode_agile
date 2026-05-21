package org;

import java.util.ArrayList;

public class Bibliotheque {
    private static Bibliotheque bibliotheque;
    private ArrayList<Client> clients;
    private Gestionnaire gestionnaire;

    private Bibliotheque() {
        clients = new ArrayList<>();
    }

    public static Bibliotheque getBibliotheque() {
        if (bibliotheque == null) {
            bibliotheque = new Bibliotheque();
        }
        return bibliotheque;
    }

    public void ajouterClient(Client client) {
        clients.add(client);
    }

    public void supprimerClient(Client client) {
        clients.remove(client);
    }
}
