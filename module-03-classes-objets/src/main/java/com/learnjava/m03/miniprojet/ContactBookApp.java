package com.learnjava.m03.miniprojet;

/**
 * Mini-projet M03 (3/3) — L'application console du carnet de contacts.
 *
 * Menu :
 *   1. Ajouter un contact
 *   2. Afficher tous les contacts
 *   3. Rechercher
 *   4. Modifier le téléphone ou l'e-mail d'un contact
 *   5. Supprimer un contact
 *   0. Quitter
 *
 * Cette classe ne s'occupe QUE du dialogue avec l'utilisateur (lire, afficher) : toute la logique est dans
 * Contact et ContactBook, qui sont testés. C'est la même séparation qu'entre un contrôleur et un modèle en Laravel.
 *
 * Lancer le programme : ./mvnw -q -pl module-03-classes-objets compile exec:java
 */
public class ContactBookApp {

    public static final int MAX_CONTACTS = 50;

    public static void main(String[] args) {
        // TODO — étapes suggérées :
        //  1. Créer le Scanner et un ContactBook de capacité MAX_CONTACTS.
        //  2. Boucler tant que l'utilisateur ne choisit pas 0 : afficher le menu, lire le choix avec nextLine().
        //  3. Choix 1 : lire prénom, nom, téléphone, e-mail (vide = pas d'e-mail), puis add(...) ;
        //     afficher un message différent si l'ajout est refusé (carnet plein ou doublon).
        //  4. Choix 2 : afficher formatAll(), ou un message si le carnet est vide.
        //  5. Choix 3 : lire la recherche et afficher les résultats de search(...), ou « Aucun résultat ».
        //  6. Choix 4 : lire le nom complet, findByName(...) ; si le contact existe, lire le nouveau téléphone
        //     et le nouvel e-mail (vide = ne pas changer). Attention au null !
        //  7. Choix 5 : lire le nom complet et remove(...) ; afficher si la suppression a réussi.
        //  8. Choix inconnu : afficher un message d'erreur.
        //  Les messages affichés sont en français ; les noms de variables et de méthodes en anglais.
        System.out.println("TODO : implémente le carnet de contacts !");
    }
}
