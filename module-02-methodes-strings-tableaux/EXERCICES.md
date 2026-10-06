# M02 — Exercices

Le code de départ se trouve dans `src/main/java/com/learnjava/m02/`. Comme au module 1, remplace chaque `throw new UnsupportedOperationException("TODO");` en suivant la Javadoc.

Pour raccourcir les commandes, place-toi dans une variable : `M=module-02-methodes-strings-tableaux`.

| # | Exercice | Difficulté | Commande de test |
|---|---|---|---|
| 1 | `ex01/StringTools` | ⭐ | `./mvnw -q -pl $M test -Dtest=StringToolsTest` |
| 2 | `ex02/ArrayStats` | ⭐⭐ | `./mvnw -q -pl $M test -Dtest=ArrayStatsTest` |
| 3 | `ex03/CaesarCipher` | ⭐⭐ | `./mvnw -q -pl $M test -Dtest=CaesarCipherTest` |
| 4 | `ex04/WordCounter` | ⭐⭐⭐ | `./mvnw -q -pl $M test -Dtest=WordCounterTest` |
| 5 | `ex05/MatrixOps` | ⭐⭐⭐ | `./mvnw -q -pl $M test -Dtest=MatrixOpsTest` |
| 🎯 | `miniprojet/GradeBook` | ⭐⭐⭐ | `./mvnw -q -pl $M test -Dtest=GradeBookTest` |

---

## Ex 1 — `StringTools` ⭐

Inverser un texte, détecter un palindrome, compter les voyelles, mettre en majuscule la première lettre de chaque mot.

**Ce que tu pratiques :** `char` et la classe `Character`, `StringBuilder`, une méthode privée d'aide.

**Questions à te poser :**
- Comment savoir si un `char` fait partie d'une liste de voyelles sans écrire six `||` ? Regarde `indexOf` dans le cours.
- Pour `capitalize`, comment savoir qu'un caractère commence un mot ? As-tu vraiment besoin d'un booléen à tenir à jour (règle **3.2** de ton journal) ?

## Ex 2 — `ArrayStats` ⭐⭐

Somme, minimum, maximum, moyenne, médiane, comptage au-dessus d'un seuil.

**Ce que tu pratiques :** la boucle for-each, le débordement d'`int`, la division décimale, la copie et le tri d'un tableau.

**Pièges (les tests les vérifient) :**
- La somme de `{Integer.MAX_VALUE, 1}` doit valoir 2 147 483 648.
- La moyenne de `{1, 2}` vaut `1.5`, pas `1.0`.
- `median` ne doit **pas** modifier le tableau reçu. Relis la section « passage des paramètres » du cours.
- La médiane de `{Integer.MAX_VALUE, Integer.MAX_VALUE}` : que donne l'addition des deux valeurs du milieu ?

## Ex 3 — `CaesarCipher` ⭐⭐

Le chiffre de César : décaler chaque lettre dans l'alphabet. Puis une attaque par force brute qui essaie les 26 décalages.

**Ce que tu pratiques :** l'arithmétique sur les `char` et le cast `(char)`, le modulo, la surcharge, une méthode qui en réutilise une autre.

**Indices :**
- Ramène la lettre à une position de 0 à 25 (`c - 'a'`), décale, applique le modulo, puis reviens à un `char`.
- En Java, `-1 % 26` vaut **`-1`**, pas 25. Comment obtenir un décalage toujours compris entre 0 et 25, même pour -27 ?
- `encrypt(text)` et `decrypt(text, shift)` tiennent chacune en une ligne.

## Ex 4 — `WordCounter` ⭐⭐⭐

Découper un texte en mots, puis compter les mots, trouver le plus long, compter les occurrences d'un mot et le mot le plus fréquent.

**Ce que tu pratiques :** `split` avec une regex, les pièges des chaînes vides, remplir un tableau dont on doit d'abord calculer la taille, `equalsIgnoreCase`, les boucles imbriquées.

**Indices :**
- Commence par `words` : toutes les autres méthodes devraient s'appuyer dessus.
- Teste à la main (avec `Arrays.toString`) ce que renvoie `"!Salut".split(SEPARATORS)`.
- Pour `mostFrequentWord`, sans dictionnaire (`Map`, module 5), une double boucle suffit.

## Ex 5 — `MatrixOps` ⭐⭐⭐

Matrice identité, transposée, addition, produit matriciel, symétrie.

**Ce que tu pratiques :** les tableaux 2D, créer un résultat aux bonnes dimensions, trois boucles imbriquées.

**Indices :**
- Une transposée de 2 × 3 est une matrice 3 × 2 : crée-la avec les bonnes dimensions avant de la remplir.
- Le produit `a × b` a autant de lignes que `a` et autant de colonnes que `b`.
- Pour `isSymmetric`, a-t-on besoin de comparer `m[0][1]` avec `m[1][0]` **et** `m[1][0]` avec `m[0][1]` ?

## 🎯 Mini-projet — `GradeBook` ⭐⭐⭐

Un carnet de notes en console avec un menu : ajouter un élève et ses notes, afficher le bulletin de la classe, afficher le meilleur élève.

1. Écris `parseGrades`, `average`, `mention`, `bestStudentIndex` et `formatReport` en t'aidant des tests.
2. Écris `main` en suivant les étapes indiquées en commentaire.
3. Lance-le :
   ```bash
   ./mvnw -q -pl module-02-methodes-strings-tableaux compile exec:java
   ```

**Points d'attention :**
- Le test `formatReportIgnoresDefaultLocale` passe ta machine en français : relis la section « Formater des nombres » du cours.
- Les seuils des mentions (10, 12, 14, 16) sont des nombres magiques (règle **5.1**).
- `bestStudentIndex` : que se passe-t-il si le seul élève a 0 de moyenne ?

**Bonus :**
- Refuser une note hors de l'intervalle 0..20.
- Ajouter un choix « 4. Supprimer un élève ». Avec des tableaux, que faut-il faire des cases suivantes ? Tu verras au module 5 pourquoi une `List` simplifie tout cela.

---

✅ Quand tout est vert (`./mvnw -q -pl module-02-methodes-strings-tableaux test`), coche le module dans `PROGRESSION.md` et demande une revue de code.
