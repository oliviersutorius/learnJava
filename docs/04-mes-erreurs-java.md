# 04 — Mes erreurs Java à corriger

Ce fichier recense, revue après revue, les erreurs liées **au langage Java** relevées dans mon code : pièges du langage, API standard, conventions et idiomes Java. Les bugs de pure logique métier (ex. une limite de tentatives mal calculée) n'y figurent pas.

**Comment l'utiliser :** relis le tableau de synthèse avant chaque nouveau module. Une règle dont le compteur augmente est une habitude à travailler en priorité.

Légende des statuts : ✅ corrigé · ⏸️ pas encore corrigé · 🔁 erreur présente dans plusieurs revues (à travailler en priorité)

---

## Synthèse

| # | Catégorie | Règle | Nb de revues où l'erreur est présente | Dernière revue où elle est présente | Statut |
|---|---|---|---|---|---|
| 1.1 | Types et opérateurs | `char + char` est une addition d'entiers, pas une concaténation | 2 | M01 #2 | 🔁 ⏸️ |
| 1.2 | Types et opérateurs | Écrire un littéral décimal (`9.0`) plutôt qu'un cast (`(double) 9`) | 2 | M01 #2 | 🔁 ⏸️ |
| 1.3 | Types et opérateurs | Décomposer une valeur avec `/` et `%` | 2 | M01 #2 | 🔁 ⏸️ |
| 2.1 | Comparaisons | Comparer les primitifs avec `==`, pas leur représentation texte | 3 | M02 #1 | ✅ |
| 3.1 | Expressions booléennes | Renvoyer directement la condition au lieu de `if … return true/false` | 2 | M01 #2 | 🔁 ⏸️ |
| 3.2 | Expressions booléennes | Pas de drapeau booléen quand l'état est déjà déductible | 2 | M01 #2 | ✅ |
| 4.1 | Boucles | Préférer une condition de boucle explicite à `while (true)` + `break` | 1 | M01 #1 | ✅ |
| 4.2 | Boucles | `hasNextInt()` ne consomme pas l'entrée | 1 | M01 #1 | ✅ |
| 5.1 | Constantes | Pas de nombres magiques : `private static final` en `UPPER_SNAKE_CASE` | 4 | M02 #2 | ✅ |
| 6.1 | Nommage | Identifiants en anglais, sans fautes, cohérents | 5 | M02 #6 | ✅ |
| 6.2 | Nommage | Un nom doit dire ce que contient la variable | 2 | M02 #4 | ✅ |
| 6.3 | Nommage | Ne pas donner à une variable le nom d'une méthode | 1 | M01 #1 | ✅ |
| 7.1 | Conventions de style | Toujours des accolades, même sur une ligne | 2 | M01 #2 | ✅ |
| 7.2 | Conventions de style | `} else {` sur la même ligne | 2 | M01 #2 | 🔁 ⏸️ |
| 7.3 | Conventions de style | `i++` et `+=` plutôt que `i += 1` et `x = x + y` | 3 | M02 #1 | ✅ |
| 7.4 | Conventions de style | Formater le code et supprimer les imports inutilisés | 3 | M02 #1 | ✅ |
| 8.1 | API standard | `print` n'ajoute pas de retour à la ligne, `println` oui | 1 | M01 #1 | ✅ |
| 8.2 | API standard | Un seul `String.format` pour plusieurs valeurs | 2 | M01 #2 | 🔁 ⏸️ |
| 8.3 | API standard | Utiliser `String`, `StringBuilder` et `Character` plutôt que les réinventer | 1 | M02 #1 | ✅ |
| 8.4 | API standard | `toUpperCase`/`toLowerCase` : une seule conversion, toujours avec la même `Locale` | 1 | M02 #2 | ✅ |
| 9.1 | Méthodes | Ne pas réaffecter un paramètre | 1 | M02 #1 | ✅ |

---

## 1. Types et opérateurs

### 1.1 `char + char` est une addition d'entiers, pas une concaténation

Un `char` entre apostrophes (`'h'`) est un **nombre** (son code Unicode). `+` entre deux `char` les additionne. Le code ne fonctionne que si l'un des opérandes est déjà une `String`, ce qui le rend fragile.

```java
// ❌ ex03/FizzBuzz.java:45 — fonctionne parce que fizzBuzz(i) est une String
retour += ' ' + fizzBuzz(i);
// ❌ ex04/TimeFormatter.java:29, 48, 60
valeur = valeur + hour + 'h';

System.out.println(' ' + 'h');   // affiche 136, pas " h" !

// ✅ des guillemets doubles pour du texte
result += " " + fizzBuzz(i);
value += hours + "h";
```

**Pourquoi :** en PHP, `.` concatène toujours. En Java, `+` additionne ou concatène selon le type des opérandes, évalués de gauche à droite.

Revues : M01 #1, M01 #2 (pas encore corrigé)

### 1.2 Écrire un littéral décimal plutôt qu'un cast

```java
// ❌ ex01/TemperatureConverter.java:14, 24 — correct, mais il faut connaître la priorité du cast pour le vérifier
celsius * ((double) 9 / 5) + 32
(fahrenheit - 32) * (double)5/9

// ✅ immédiatement lisible
celsius * 9.0 / 5 + 32
```

**Pourquoi :** `9 / 5` vaut `1` (division entière entre deux `int`). Le littéral `9.0` est un `double` : la division devient décimale sans cast. Le cast `(double)` est utile pour une **variable** de type `int`, pas pour une constante qu'on écrit soi-même.

Revues : M01 #1, M01 #2 (pas encore corrigé)

### 1.3 Décomposer une valeur avec `/` et `%`

```java
// ❌ ex04/TimeFormatter.java:26-27, 43-44 — juste, mais répétitif
int minutes = (totalSeconds - (hour * 3600)) / 60;
int seconds = totalSeconds - (hour * 3600) - (minutes * 60);

// ✅ le reste de la division donne directement « ce qui reste »
int minutes = totalSeconds % SECONDS_PER_HOUR / SECONDS_PER_MINUTE;
int seconds = totalSeconds % SECONDS_PER_MINUTE;
```

**Pourquoi :** `/` et `%` vont toujours ensemble pour découper une quantité en unités (heures/minutes, euros/centimes, lignes/colonnes d'une grille).

Revues : M01 #1, M01 #2 (pas encore corrigé)

---

## 2. Comparaisons

### 2.1 Comparer les primitifs avec `==`, pas leur représentation texte

```java
// ❌ miniprojet/GuessTheNumber.java — la victoire dépend du texte affiché
if (Objects.equals(hint, WIN)) { ... }   // revue #1
found = message.equals(WIN);             // revue #2

// ❌ m02/ex01/StringTools.java:49-52 — chaque caractère devient une String d'un caractère
String[] compare = textCleaned.split("");
if (!Objects.equals(compare[start], compare[end])) { ... }

// ✅ la règle du jeu porte sur des nombres
found = guess == secret;
// ✅ un char est un primitif
if (cleaned.charAt(start) != cleaned.charAt(end)) { ... }
```

**Pourquoi :** `==` est la bonne comparaison pour les primitifs (`int`, `double`, `boolean`, `char`). Pour les objets, `==` compare les **références** : on utilise `equals()`, ou `Objects.equals()` si l'un des deux peut être `null`. Mais surtout, si l'on modifie un jour le message affiché, la logique ne doit pas changer.

Revues : M01 #1, M01 #2, M02 #1 · ✅ corrigé en M02 #2 (`charAt` et `equals(reverse(...))`)

---

## 3. Expressions booléennes

### 3.1 Renvoyer directement la condition

```java
// ❌ ex02/LeapYear.java:46-49
if (day <= 0 || day > maxDay || maxDay == -1) {
    return false;
}
return true;

// ✅ une comparaison est déjà un boolean
return day >= 1 && day <= maxDay;
```

**Pourquoi :** `if (cond) return true; else return false;` est un détour. Le test `maxDay == -1` était en plus redondant : si `maxDay` vaut -1, `day > maxDay` est déjà vrai pour tout jour positif.

Revues : M01 #1, M01 #2 (pas encore corrigé)

### 3.2 Pas de drapeau booléen quand l'état est déjà déductible

```java
// ❌ ex03/FizzBuzz.java:18-28
boolean threeOrFive = false;
...
if (!threeOrFive) fizz += n;

// ✅ la String elle-même dit si on a ajouté quelque chose
if (result.isEmpty()) {
    result += n;
}
```

**Pourquoi :** un drapeau doit être tenu à jour à la main à chaque modification. S'il est déductible d'une autre donnée, c'est une source d'incohérence en moins.

Revues : M01 #1, M01 #2 · ✅ corrigé en M02 #1 (`capitalize` sans drapeau)

---

## 4. Boucles

### 4.1 Préférer une condition de boucle explicite à `while (true)` + `break`

```java
// ❌ miniprojet/GuessTheNumber.java:61 et ex05/PrimeNumbers.java:55 (revue #1)
while (true) {
    ...
    if (...) break;
    ...
    if (...) break;
}

// ✅ la règle d'arrêt se lit dès la première ligne
while (!found && attempts < MAX_ATTEMPTS) { ... }
while (found < n) { ... }
```

**Pourquoi :** avec `while (true)`, il faut lire tout le corps de la boucle pour savoir quand elle s'arrête. Et si aucune des sorties n'est atteinte (par exemple `nthPrime(-1)`), la boucle devient infinie.

Revues : M01 #1 · ✅ corrigé en M01 #2

### 4.2 `hasNextInt()` ne consomme pas l'entrée

```java
// ✅ si le prochain mot n'est pas un entier, il faut le lire (et le jeter), sinon on boucle à l'infini
if (!scanner.hasNextInt()) {
    System.out.println(scanner.next() + " n'est pas un nombre.");
    continue;
}
```

**Pourquoi :** les méthodes `hasNextXxx()` de `Scanner` regardent la prochaine entrée sans la retirer ; seules les méthodes `next()`, `nextInt()`, `nextLine()` la consomment.

Revues : M01 #1 · ✅ corrigé en M01 #2

---

## 5. Constantes

### 5.1 Pas de nombres magiques

```java
// ❌ ex04/TimeFormatter.java — 3600 et 60 répétés 8 fois ; miniprojet : count > 7 (revue #1)
int hour = totalSeconds / 3600;

// ✅
private static final int SECONDS_PER_HOUR = 3600;
private static final int SECONDS_PER_MINUTE = 60;
int hours = totalSeconds / SECONDS_PER_HOUR;
```

**Pourquoi :** un nom explique ce que représente la valeur, et la modifier ne se fait qu'à un seul endroit. Convention Java : `static final`, nom en `UPPER_SNAKE_CASE`, `private` si la constante n'est utile qu'à la classe.

```java
// ❌ m02/ex01/StringTools.java:89 — une « chaîne magique » recréée à chaque appel
String vowels = "aeiouy";
// ✅
private static final String VOWELS = "aeiouy";
```

M02 #2 : la constante existe, mais elle est déclarée `public static final String VOWELS` (`m02/ex01/StringTools.java:12`). Elle n'est utile qu'à la classe, donc `private` : une constante `public` fait partie de l'API de la classe, et d'autres classes pourraient s'en servir.

Revues : M01 #1 (`7` ✅ corrigé avec `MAX_ATTEMPTS` en #2), M01 #2 (`3600`/`60` pas encore corrigés), M02 #1 (`"aeiouy"`), M02 #2 (`public` au lieu de `private`) · ✅ corrigé en M02 #3

---

## 6. Nommage

### 6.1 Identifiants en anglais, sans fautes, cohérents

```java
// ❌
String retour = "";        // ex03/FizzBuzz.java:39      -> result
String valeur = "";        // ex04/TimeFormatter.java:45 -> value
int limite = ...;          // ex05/PrimeNumbers.java:21  -> limit
double farenheit = ...;    // ex01/TemperatureConverter.java:14 -> fahrenheit
int hour; int minutes;     // ex04/TimeFormatter.java    -> hours / minutes (même forme)
String[] morceaux = ...;   // m02/ex01/StringTools.java:111 -> words
String textCleaned = ...;  // m02/ex01/StringTools.java:39  -> cleaned (en anglais, l'adjectif se place avant : cleanedText)
int nbAbove = 0;           // m02/ex02/ArrayStats.java:87  -> count (« nb » est une abréviation française)
String[] chains = ...;     // m02/ex03/CaesarCipher.java:53 -> candidates (faux ami : « chain » = chaîne métallique, pas chaîne de caractères)
```

**Pourquoi :** le code Java professionnel est écrit en anglais ; mélanger les langues ou les formes (singulier/pluriel) oblige le lecteur à deviner. Dans IntelliJ, `Shift+F6` renomme partout d'un coup.

Revues : M01 #1, M01 #2, M02 #1 · ✅ corrigé en M02 #2 · réapparue en M02 #4 · ✅ corrigé en M02 #5 · réapparue en M02 #6 · ✅ corrigé en M02 #7

### 6.2 Un nom doit dire ce que contient la variable

```java
// ❌ ex02/LeapYear.java:45 — « Ok » évoque un booléen, or c'est un nombre de jours
int dayOk = daysInMonth(month, year);
// ✅
int maxDay = daysInMonth(month, year);

// ❌ m02/ex02/ArrayStats.java:54 — « total » évoque une somme, or c'est le nombre d'éléments (et un long pour un int)
long total = values.length;
// ✅ values.length se lit très bien tel quel, ou bien :
int count = values.length;
```

Revues : M01 #1 · ✅ corrigé en M01 #2 · réapparue en M02 #4 · ✅ corrigé en M02 #5

### 6.3 Ne pas donner à une variable le nom d'une méthode

```java
// ❌ miniprojet/GuessTheNumber.java:78 — légal, mais on ne sait plus qui est qui
String hint = hint(secret, guess);
// ✅
String message = hint(secret, guess);
```

Revues : M01 #1 · ✅ corrigé en M01 #2

---

## 7. Conventions de style

### 7.1 Toujours des accolades

```java
// ❌ ex03/FizzBuzz.java:28, ex05/PrimeNumbers.java:17-19
if (!threeOrFive) fizz += n;
if (n < 2) return false;

// ✅
if (n < 2) {
    return false;
}
```

**Pourquoi :** sans accolades, une ligne ajoutée plus tard sous le `if` s'exécutera toujours, malgré l'indentation qui laisse croire le contraire.

Revues : M01 #1, M01 #2 · ✅ corrigé en M02 #1

### 7.2 `} else {` sur la même ligne

```java
// ❌ ex03/FizzBuzz.java:43-44
}
else {
// ✅ convention Java (Oracle, Google Java Style)
} else {
```

Revues : M01 #1, M01 #2 (pas encore corrigé)

### 7.3 `i++` et `+=`

```java
// ❌ ex05/PrimeNumbers.java:38-40, ex04/TimeFormatter.java:48-60
for (int i = 1; i <= limit; i += 1)
result += 1;
valeur = valeur + hour + "h";
start += 1; end -= 1;      // m02/ex01/StringTools.java:56-57

// ✅
for (int i = 2; i <= limit; i++)
count++;
value += hours + "h";
start++;
end--;
```

Revues : M01 #1, M01 #2, M02 #1 · ✅ corrigé en M02 #2

### 7.4 Formater le code et supprimer les imports inutilisés

- Espaces irréguliers : `MAX+1`, `(double)5/9`, `hour *3600`, `if (i == 1 )` → `Ctrl+Alt+L` dans IntelliJ.
- `import java.util.Objects;` n'est plus utilisé dans `GuessTheNumber.java` (revue #2) → `Ctrl+Alt+O`.
- Les commentaires `// TODO` et le code commenté sont à supprimer une fois le travail fait (revue #1, ✅ corrigé).
- M02 #1 : `import java.util.Locale;` inutilisé (`m02/ex01/StringTools.java:3`), espaces irréguliers `text.length()-1` (l. 24) et `+ 1 ;` (l. 45).

Revues : M01 #1, M01 #2, M02 #1 · ✅ corrigé en M02 #2

---

## 8. API standard

### 8.1 `print` n'ajoute pas de retour à la ligne

```java
// ❌ miniprojet/GuessTheNumber.java:79-81 — affiche « C'est plus !Tentative 1 »
System.out.print(hint);
System.out.println("Tentative " + count);
```

`print` écrit tel quel, `println` ajoute un retour à la ligne, `printf` formate (avec `%n` pour le retour à la ligne).

Revues : M01 #1 · ✅ corrigé en M01 #2

### 8.2 Un seul `String.format` pour plusieurs valeurs

```java
// ❌ ex04/TimeFormatter.java:29
String.format("%02d", hour) + ':' + String.format("%02d", minutes) + ':' + String.format("%02d", seconds);
// ✅ le gabarit montre le résultat attendu
String.format("%02d:%02d:%02d", hours, minutes, seconds);
```

Revues : M01 #1, M01 #2 (pas encore corrigé)

### 8.3 Utiliser `String`, `StringBuilder` et `Character` plutôt que les réinventer

```java
// ❌ m02/ex01/StringTools.java:22-27 — une boucle pour ce que fait déjà StringBuilder
for (int i = text.length() - 1; i >= 0; i--) { result.append(text.charAt(i)); }
// ✅
return new StringBuilder(text).reverse().toString();

// ❌ StringTools.java:49 — split("") pour accéder aux caractères : un tableau de String créé pour rien
String[] compare = textCleaned.split("");
// ✅ charAt(i) donne directement le char
cleaned.charAt(i)

// ❌ StringTools.java:71-73 — test inutile : un espace n'est ni une lettre ni un chiffre
if (Character.isWhitespace(c)) { continue; }
if (Character.isLetterOrDigit(c)) { result.append(c); }
// ✅
if (Character.isLetterOrDigit(c)) { result.append(Character.toLowerCase(c)); }
```

**Pourquoi :** l'API standard est testée, optimisée et connue de tous les développeurs Java : `reverse()` se lit d'un coup d'œil, une boucle doit être relue. C'est l'équivalent de préférer `strrev()` ou les helpers `Str::` de Laravel à une boucle maison.

Revues : M02 #1 · ✅ corrigé en M02 #2

### 8.4 `toUpperCase` / `toLowerCase` : une seule conversion, toujours avec la même `Locale`

```java
// ❌ m02/ex01/StringTools.java:43 et 47 — chaque caractère est mis en minuscule deux fois
result.append(Character.toLowerCase(c));
return result.toString().toLowerCase(Locale.ROOT);

// ❌ StringTools.java:81-82 — Locale.ROOT pour la minuscule, la langue du système pour la majuscule
words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase(Locale.ROOT);

// ✅ une conversion, et toujours la même Locale
words[i].substring(0, 1).toUpperCase(Locale.ROOT) + words[i].substring(1).toLowerCase(Locale.ROOT);
```

**Pourquoi :** sans argument, `String.toUpperCase()` et `toLowerCase()` suivent la langue du système (en turc, `"i".toUpperCase()` donne `"İ"`). Utiliser `Locale.ROOT` à un endroit et pas à l'autre ne protège qu'à moitié. `Character.toUpperCase(c)` et `Character.toLowerCase(c)` ne dépendent pas de la langue du système : c'est une bonne alternative caractère par caractère.

Revues : M02 #2 · ✅ corrigé en M02 #3

---

## 9. Méthodes

### 9.1 Ne pas réaffecter un paramètre

```java
// ❌ m02/ex01/StringTools.java:90 — après cette ligne, « text » n'est plus le texte reçu
text = text.toLowerCase();
// ✅ une nouvelle variable au nom explicite
String lowerText = text.toLowerCase();
```

**Pourquoi :** c'est légal (le paramètre est une copie locale, l'appelant n'est pas affecté), mais le lecteur qui voit `text` plus bas doit vérifier s'il s'agit de la valeur reçue ou d'une valeur transformée. Beaucoup d'équipes déclarent même leurs paramètres `final` pour l'interdire.

Revues : M02 #1 · ✅ corrigé en M02 #2

---

## Historique des revues

| Revue | Date | Module | Nouvelles erreurs | Corrigées depuis la revue précédente |
|---|---|---|---|---|
| M01 #1 | 2026-10-05 | M01 — Bases | 18 | — |
| M01 #2 | 2026-10-05 | M01 — Bases | 1 (`import` inutilisé) | 6 (4.1, 4.2, 6.2, 6.3, 8.1, le `7` de 5.1) |
| M02 #1 | 2026-10-06 | M02 — Ex01 `StringTools` | 2 (8.3, 9.1) | 2 (3.2, 7.1) |
| M02 #2 | 2026-10-06 | M02 — Ex01 `StringTools` | 1 (8.4) | 6 (2.1, 6.1, 7.3, 7.4, 8.3, 9.1) |
| M02 #3 | 2026-10-06 | M02 — Ex01 `StringTools` | 0 | 2 (5.1, 8.4) |
| M02 #4 | 2026-10-07 | M02 — Ex02 `ArrayStats` | 0 (6.1 et 6.2 réapparaissent) | — |
| M02 #5 | 2026-10-07 | M02 — Ex02 `ArrayStats` | 0 | 2 (6.1, 6.2) |
| M02 #6 | 2026-10-07 | M02 — Ex03 `CaesarCipher` | 0 (6.1 réapparaît) | — |
| M02 #7 | 2026-10-07 | M02 — Ex03 `CaesarCipher` | 0 | 1 (6.1) |
