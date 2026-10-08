package com.learnjava.m03.ex02;

import java.util.Locale;

public class BankAccount {

    private static final long CENTS_PER_EURO = 100;

    // static : un seul compteur partagé par tous les comptes
    private static int createdCount = 0;

    // final : le numéro et le titulaire ne changent jamais (le compilateur le garantit)
    private final int number;
    private final String owner;
    private long balanceInCents;

    public BankAccount(String owner) {
        // Toute la logique de création est dans l'autre constructeur : le numéro n'est attribué qu'à un endroit
        this(owner, 0);
    }

    public BankAccount(String owner, long initialBalanceInCents) {
        createdCount++;
        this.number = createdCount;
        this.owner = owner;
        this.balanceInCents = Math.max(0, initialBalanceInCents);
    }

    public int getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public long getBalanceInCents() {
        return balanceInCents;
    }

    public boolean deposit(long amountInCents) {
        if (amountInCents <= 0) {
            return false;
        }
        balanceInCents += amountInCents;
        return true;
    }

    public boolean withdraw(long amountInCents) {
        if (amountInCents <= 0 || amountInCents > balanceInCents) {
            return false;
        }
        balanceInCents -= amountInCents;
        return true;
    }

    public boolean transferTo(BankAccount target, long amountInCents) {
        // == compare les références : « target est-il le même objet que moi ? »
        if (target == null || target == this) {
            return false;
        }
        // Si le retrait échoue, rien n'a changé. S'il réussit, le dépôt réussit forcément (montant > 0).
        if (!withdraw(amountInCents)) {
            return false;
        }
        target.deposit(amountInCents);
        return true;
    }

    public String formatBalance() {
        // / et % découpent les centimes en euros et centimes restants (règle 1.3), un seul format (règle 8.2)
        return String.format(Locale.ROOT, "%d.%02d €",
                balanceInCents / CENTS_PER_EURO, balanceInCents % CENTS_PER_EURO);
    }

    public static int accountsCreated() {
        return createdCount;
    }

    @Override
    public String toString() {
        return "Compte n°" + number + " de " + owner + " : " + formatBalance();
    }
}
