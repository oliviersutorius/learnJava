package com.learnjava.m03.miniprojet;

/**
 * Mini-projet M03 (2/3) — Le carnet de contacts.
 *
 * Le carnet contient au plus « capacity » contacts, dans l'ordre d'ajout.
 * Deux contacts ne peuvent pas avoir le même nom complet (casse ignorée) : c'est ce nom qui identifie un contact.
 *
 * Compare avec le mini-projet du module 2 : un seul tableau d'objets remplace les tableaux parallèles.
 *
 * TODO : déclare les attributs.
 */
public class ContactBook {

    /**
     * Crée un carnet vide. La capacité reçue n'est jamais négative.
     */
    public ContactBook(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isFull() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Ajoute un contact. Renvoie false (et ne change rien) si contact est null, si le carnet est plein
     * ou si un contact de même nom complet (casse ignorée) existe déjà.
     */
    public boolean add(Contact contact) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le contact qui a ce nom complet (casse ignorée, espaces de début et de fin ignorés), ou null.
     * Exemple : findByName("  ada LOVELACE ") trouve Ada Lovelace.
     */
    public Contact findByName(String fullName) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Supprime le contact qui a ce nom complet (mêmes règles que findByName).
     * Les contacts suivants sont décalés d'une case : l'ordre d'ajout est conservé et il n'y a pas de « trou ».
     * Renvoie false si aucun contact ne porte ce nom.
     */
    public boolean remove(String fullName) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie les contacts qui correspondent à la recherche (voir Contact.matches), dans l'ordre d'ajout,
     * dans un tableau de la taille exacte (vide si aucun ne correspond).
     */
    public Contact[] search(String query) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie les contacts dans l'ordre d'ajout, dans un tableau de taille size().
     * Modifier le tableau renvoyé ne doit pas modifier le carnet.
     */
    public Contact[] getContacts() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la liste des contacts (leur toString), un par ligne, séparés par "\n" (pas de "\n" après le dernier).
     * Renvoie "" si le carnet est vide.
     */
    public String formatAll() {
        throw new UnsupportedOperationException("TODO");
    }
}
