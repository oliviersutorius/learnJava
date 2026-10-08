package com.learnjava.m03.ex02;

/**
 * Exercice 2 — Un compte bancaire.
 * Notions : encapsulation et invariant, attribut final, attribut et méthode static, this et ==,
 * un objet qui agit sur un autre objet de la même classe.
 *
 * Règles du compte :
 * - les sommes sont exprimées en CENTIMES dans des long (jamais de double pour de l'argent : relis le cours) ;
 * - le solde n'est jamais négatif, et il ne change que par deposit, withdraw et transferTo (pas de setter !) ;
 * - chaque compte reçoit à sa création un numéro unique : 1 pour le premier compte créé, 2 pour le suivant, etc. ;
 * - le titulaire et le numéro ne changent jamais.
 *
 * TODO : déclare les attributs. Lesquels doivent être final ? Lequel doit être static ?
 */
public class BankAccount {

    /**
     * Crée un compte vide pour ce titulaire.
     */
    public BankAccount(String owner) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Crée un compte avec un solde initial. Un solde initial négatif est remplacé par 0.
     * Attention : le numéro ne doit être attribué qu'à un seul endroit du code.
     */
    public BankAccount(String owner, long initialBalanceInCents) {
        throw new UnsupportedOperationException("TODO");
    }

    public int getNumber() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getOwner() {
        throw new UnsupportedOperationException("TODO");
    }

    public long getBalanceInCents() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Dépose de l'argent. Renvoie false (et ne change rien) si le montant n'est pas strictement positif.
     */
    public boolean deposit(long amountInCents) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Retire de l'argent. Renvoie false (et ne change rien) si le montant n'est pas strictement positif
     * ou si le solde est insuffisant.
     */
    public boolean withdraw(long amountInCents) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Vire de l'argent de ce compte vers target. Renvoie false (et ne change AUCUN des deux comptes) si :
     * target est null, target est ce compte lui-même, le montant n'est pas strictement positif
     * ou le solde est insuffisant.
     * Astuce : réutilise withdraw et deposit.
     */
    public boolean transferTo(BankAccount target, long amountInCents) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le solde en euros, avec toujours deux chiffres après le point, suivi d'un espace et de « € ».
     * Exemples : 123456 -> "1234.56 €", 5 -> "0.05 €", 0 -> "0.00 €", 1200 -> "12.00 €".
     * Pas de double ici : découpe les centimes avec / et % (règle 1.3 de ton journal).
     */
    public String formatBalance() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le nombre total de comptes créés depuis le lancement du programme.
     */
    public static int accountsCreated() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Exemple : "Compte n°3 de Alice : 1234.56 €".
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO");
    }
}
