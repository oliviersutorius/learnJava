package com.learnjava.m03.ex02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex02 - BankAccount")
class BankAccountTest {

    @Test
    @DisplayName("Les attributs sont privés")
    void fieldsArePrivate() {
        assertThat(BankAccount.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field))
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers()))
                        .as("l'attribut %s doit être private", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Il n'y a aucun setter : le solde ne change que par dépôt, retrait ou virement")
    void noSetter() {
        assertThat(BankAccount.class.getMethods())
                .extracting(Method::getName)
                .noneMatch(name -> name.startsWith("set"));
    }

    @Test
    @DisplayName("Un nouveau compte est vide")
    void newAccountIsEmpty() {
        BankAccount account = new BankAccount("Alice");
        assertThat(account.getOwner()).isEqualTo("Alice");
        assertThat(account.getBalanceInCents()).isZero();
    }

    @Test
    void initialBalance() {
        assertThat(new BankAccount("Bob", 1050).getBalanceInCents()).isEqualTo(1050);
        assertThat(new BankAccount("Bob", -500).getBalanceInCents()).as("solde initial négatif").isZero();
    }

    @Test
    @DisplayName("Chaque compte reçoit le numéro suivant, quel que soit le constructeur utilisé")
    void numbersAreConsecutive() {
        BankAccount first = new BankAccount("Alice");
        BankAccount second = new BankAccount("Bob", 1000);
        BankAccount third = new BankAccount("Chloé");
        assertThat(first.getNumber()).isPositive();
        assertThat(second.getNumber()).isEqualTo(first.getNumber() + 1);
        assertThat(third.getNumber()).isEqualTo(second.getNumber() + 1);
    }

    @Test
    @DisplayName("accountsCreated compte tous les comptes créés")
    void accountsCreated() {
        int before = BankAccount.accountsCreated();
        new BankAccount("Alice");
        new BankAccount("Bob", 100);
        assertThat(BankAccount.accountsCreated()).isEqualTo(before + 2);
    }

    @Test
    void deposit() {
        BankAccount account = new BankAccount("Alice", 1000);
        assertThat(account.deposit(250)).isTrue();
        assertThat(account.getBalanceInCents()).isEqualTo(1250);
    }

    @ParameterizedTest(name = "deposit({0}) est refusé")
    @CsvSource({"0", "-1", "-1000"})
    void depositRejectsNonPositiveAmount(long amount) {
        BankAccount account = new BankAccount("Alice", 1000);
        assertThat(account.deposit(amount)).isFalse();
        assertThat(account.getBalanceInCents()).isEqualTo(1000);
    }

    @Test
    void withdraw() {
        BankAccount account = new BankAccount("Alice", 1000);
        assertThat(account.withdraw(300)).isTrue();
        assertThat(account.getBalanceInCents()).isEqualTo(700);
        assertThat(account.withdraw(700)).as("retirer tout le solde").isTrue();
        assertThat(account.getBalanceInCents()).isZero();
    }

    @ParameterizedTest(name = "withdraw({0}) est refusé avec 10 € sur le compte")
    @CsvSource({"0", "-5", "1001", "999999"})
    void withdrawRejectsInvalidAmount(long amount) {
        BankAccount account = new BankAccount("Alice", 1000);
        assertThat(account.withdraw(amount)).isFalse();
        assertThat(account.getBalanceInCents()).isEqualTo(1000);
    }

    @Test
    void transferTo() {
        BankAccount alice = new BankAccount("Alice", 1000);
        BankAccount bob = new BankAccount("Bob", 200);
        assertThat(alice.transferTo(bob, 300)).isTrue();
        assertThat(alice.getBalanceInCents()).isEqualTo(700);
        assertThat(bob.getBalanceInCents()).isEqualTo(500);
    }

    @Test
    @DisplayName("Un virement refusé ne modifie aucun des deux comptes")
    void rejectedTransfer() {
        BankAccount alice = new BankAccount("Alice", 1000);
        BankAccount bob = new BankAccount("Bob", 200);
        assertThat(alice.transferTo(bob, 1001)).as("solde insuffisant").isFalse();
        assertThat(alice.transferTo(bob, 0)).as("montant nul").isFalse();
        assertThat(alice.transferTo(bob, -50)).as("montant négatif").isFalse();
        assertThat(alice.transferTo(null, 100)).as("compte cible null").isFalse();
        assertThat(alice.getBalanceInCents()).isEqualTo(1000);
        assertThat(bob.getBalanceInCents()).isEqualTo(200);
    }

    @Test
    @DisplayName("On ne peut pas se faire un virement à soi-même")
    void transferToItself() {
        BankAccount alice = new BankAccount("Alice", 1000);
        assertThat(alice.transferTo(alice, 100)).isFalse();
        assertThat(alice.getBalanceInCents()).isEqualTo(1000);
    }

    @ParameterizedTest(name = "{0} centimes -> \"{1}\"")
    @CsvSource(value = {"123456|1234.56 €", "5|0.05 €", "0|0.00 €", "1200|12.00 €", "99|0.99 €", "100|1.00 €",
            "1000000007|10000000.07 €"}, delimiter = '|')
    void formatBalance(long cents, String expected) {
        assertThat(new BankAccount("Alice", cents).formatBalance()).isEqualTo(expected);
    }

    @Test
    void toStringFormat() {
        BankAccount account = new BankAccount("Alice", 123456);
        assertThat(account).hasToString("Compte n°" + account.getNumber() + " de Alice : 1234.56 €");
    }

    /** Une constante (static final) peut être publique : seuls les autres attributs doivent être privés. */
    private static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }
}
