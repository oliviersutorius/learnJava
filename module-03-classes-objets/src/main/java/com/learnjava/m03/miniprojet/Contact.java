package com.learnjava.m03.miniprojet;

/**
 * Mini-projet M03 (1/3) — Un contact.
 *
 * Le prénom et le nom ne changent jamais ; le téléphone et l'e-mail peuvent être modifiés.
 * Les valeurs sont « nettoyées » à l'enregistrement, dans le constructeur comme dans les setters :
 * - prénom et nom : espaces de début et de fin retirés ; ils ne sont jamais vides ;
 * - téléphone : TOUS les espaces retirés ("06 12 34 56 78" -> "0612345678") ;
 * - e-mail : espaces de début et de fin retirés, et mis en minuscules ; "" signifie « pas d'e-mail ».
 * Aucune valeur reçue n'est null.
 *
 * TODO : déclare les attributs. Évite d'écrire deux fois le même nettoyage (méthodes privées d'aide).
 */
public class Contact {

    public Contact(String firstName, String lastName, String phone, String email) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Crée un contact sans e-mail.
     */
    public Contact(String firstName, String lastName, String phone) {
        throw new UnsupportedOperationException("TODO");
    }

    public String getFirstName() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getLastName() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getPhone() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("TODO");
    }

    public void setPhone(String phone) {
        throw new UnsupportedOperationException("TODO");
    }

    public void setEmail(String email) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Exemple : "Ada Lovelace".
     */
    public String getFullName() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Les initiales en majuscules. Exemple : Contact("ada", "lovelace", ...) -> "AL".
     * Attention à la règle 1.1 de ton journal.
     */
    public String getInitials() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si le contact a un e-mail.
     */
    public boolean hasEmail() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si le contact correspond à une recherche : query (sans ses espaces de début et de fin) est contenu
     * dans le nom complet (casse ignorée) ou dans le téléphone.
     * Exemples pour Ada Lovelace, 0612345678 : "love" -> vrai, "ADA L" -> vrai, "1234" -> vrai, "bob" -> faux.
     * Une recherche vide correspond à tous les contacts.
     */
    public boolean matches(String query) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Exemples : "Ada Lovelace | 0612345678 | ada@example.com", et sans e-mail : "Alan Turing | 0698765432 | -".
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO");
    }
}
