# M01 — Exercices

Le code de départ se trouve dans `src/main/java/com/learnjava/m01/`. Chaque méthode à écrire contient `throw new UnsupportedOperationException("TODO");`, que tu dois remplacer par ton implémentation. La Javadoc au-dessus de chaque méthode précise le comportement attendu.

| # | Exercice | Difficulté | Commande de test |
|---|---|---|---|
| 1 | `ex01/TemperatureConverter` | ⭐ | `./mvnw -q -pl module-01-bases test -Dtest=TemperatureConverterTest` |
| 2 | `ex02/LeapYear` | ⭐⭐ | `./mvnw -q -pl module-01-bases test -Dtest=LeapYearTest` |
| 3 | `ex03/FizzBuzz` | ⭐⭐ | `./mvnw -q -pl module-01-bases test -Dtest=FizzBuzzTest` |
| 4 | `ex04/TimeFormatter` | ⭐⭐⭐ | `./mvnw -q -pl module-01-bases test -Dtest=TimeFormatterTest` |
| 5 | `ex05/PrimeNumbers` | ⭐⭐⭐ | `./mvnw -q -pl module-01-bases test -Dtest=PrimeNumbersTest` |
| 🎯 | `miniprojet/GuessTheNumber` | ⭐⭐⭐ | `./mvnw -q -pl module-01-bases test -Dtest=GuessTheNumberTest` |

---

## Ex 1 — `TemperatureConverter` ⭐

Écris les conversions Celsius ↔ Fahrenheit et une méthode qui indique si l'eau gèle.

**Ce que tu pratiques :** les opérations sur des `double`, la priorité des opérateurs, renvoyer un `boolean`.

**Piège :** `9 / 5` vaut `1` en Java. Pourquoi ? Comment l'éviter ?

## Ex 2 — `LeapYear` ⭐⭐

1. `isLeapYear` : la règle complète des années bissextiles (divisible par 4, sauf par 100, sauf par 400).
2. `daysInMonth` : le nombre de jours d'un mois, avec février qui dépend de l'année.
3. `isValidDate` : la date existe-t-elle ?

**Ce que tu pratiques :** le modulo, la combinaison de `&&` et `||`, `switch`, et la réutilisation d'une méthode dans une autre.

**Pour aller plus loin :** est-il possible d'écrire `isLeapYear` en une seule expression `return ...;` ?

## Ex 3 — `FizzBuzz` ⭐⭐

Le grand classique des entretiens. Les multiples de 3 donnent « Fizz », ceux de 5 « Buzz », ceux des deux « FizzBuzz ». Puis construis toute la séquence de 1 à n.

**Ce que tu pratiques :** l'ordre des conditions, la boucle `for`, construire une `String` dans une boucle sans espace en trop.

## Ex 4 — `TimeFormatter` ⭐⭐⭐

Convertis des durées : `3723` secondes → `"01:02:03"` → `"1h 2min 3s"`.

**Ce que tu pratiques :** la division entière et le modulo pour découper une valeur, `String.format`, la construction conditionnelle d'un texte.

**Indice :** combien de secondes dans une heure ? Une fois les heures retirées (`%`), combien reste-t-il ?

## Ex 5 — `PrimeNumbers` ⭐⭐⭐

1. `isPrime` : n est-il premier ?
2. `countPrimesUpTo` : combien de premiers jusqu'à `limit` ?
3. `nthPrime` : le n-ième nombre premier.

**Ce que tu pratiques :** les boucles imbriquées, le `return` anticipé, `while` quand on ne connaît pas le nombre de tours, l'efficacité d'un algorithme.

**Attention :** le test `isPrimeIsFast` a une limite de 2 secondes, et les tests utilisent `Integer.MAX_VALUE`. Une solution naïve risque d'être trop lente… ou fausse. Relis la section « débordement » du cours.

## 🎯 Mini-projet — `GuessTheNumber` ⭐⭐⭐

Un jeu console complet : l'ordinateur choisit un nombre entre 1 et 100, le joueur propose des nombres et le programme lui répond « C'est plus ! » ou « C'est moins ! » jusqu'à ce qu'il trouve.

1. Écris `hint` et `isInRange` en t'aidant des tests.
2. Écris `main` en suivant les étapes indiquées en commentaire.
3. Joue :
   ```bash
   ./mvnw -q -pl module-01-bases compile exec:java
   ```

**Bonus :**
- Que se passe-t-il si le joueur tape `abc` ? Rends le programme robuste.
- Limite le jeu à 7 tentatives. Pourquoi 7 suffisent-elles toujours si on joue bien ?
- Propose de rejouer à la fin.

---

✅ Quand tout est vert (`./mvnw -q -pl module-01-bases test`), coche le module dans `PROGRESSION.md` et demande une revue de ton code avant de passer au module 2.
