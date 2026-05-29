
# Bibliothèque - Application de Gestion

Application Java de gestion d'une bibliothèque pour le terminal gestionnaire et le terminal lecteur.

## Prérequis

- Java JDK 17
- Maven
- Jackson (inclus dans le pom.xml)

## Structure du projet

```
src/main/
├── java/org/
│   ├── MainGestionnaire.java
│   ├── MainClient.java
│   ├── Bibliotheque.java
│   ├── Gestionnaire.java
│   ├── Client.java
│   ├── Compte.java
│   ├── Livre.java
│   ├── Exemplaire.java
│   ├── Emprunt.java
│   ├── Database.java
│   └── JSONBuilder.java
└── resources/
    └── db.json
```

## Lancement

Terminal gestionnaire :
```
mvn exec:java -Dexec.mainClass="org.MainGestionnaire"
```

Terminal lecteur :
```
mvn exec:java -Dexec.mainClass="org.MainClient"
```

## Fonctionnalités

**Gestionnaire**
- Inscrire un nouveau client
- Enregistrer un prêt (recherche par nom de client et titre du livre)
- Enregistrer un retour d'exemplaire
- Afficher la liste des comptes

**Lecteur**
- Consulter le catalogue des livres
- Vérifier la disponibilité d'un ouvrage par titre

## Persistance

Les données sont stockées dans `src/main/resources/db.json`.
La base est chargée au démarrage et sauvegardée à la fermeture du terminal gestionnaire (option 0).

## Identifiants par défaut

Mot de passe par défaut à la création d'un compte : `1234`
