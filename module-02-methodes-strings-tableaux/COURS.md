# M02 — Méthodes, `String`, tableaux

## Objectifs

À la fin de ce module, tu sais :
- découper un programme en méthodes, dont des méthodes privées d'aide, et utiliser la surcharge ;
- expliquer pourquoi une méthode peut modifier un tableau reçu, mais pas un `int` ;
- manipuler des `String` et des `char` sans tomber dans leurs pièges, et construire du texte avec `StringBuilder` ;
- créer, parcourir, copier et trier des tableaux à une et deux dimensions ;
- formater des nombres indépendamment de la langue du système (`Locale`).

> 📓 **Ton journal d'erreurs** (`docs/04-mes-erreurs-java.md`) : ce module revient sur les règles **1.1** (`char` + `char`), **1.2** (littéral décimal ou cast), **3.1** (renvoyer directement un booléen), **3.2** (drapeau booléen inutile) et **7.3** (`+=`). Relis-les avant de commencer.

---

## 1. Méthodes

### Anatomie

```java
/** Javadoc : ce que fait la méthode, pas comment. */
public static double average(int[] values) {   // visibilité, static, type de retour, nom, paramètres
    if (values.length == 0) {
        return 0.0;                              // retour anticipé pour le cas particulier
    }
    return (double) sum(values) / values.length; // une méthode en appelle une autre
}
```

Une bonne méthode **fait une seule chose**, porte un nom qui commence par un verbe (`compute`, `is`, `count`, `parse`…) et tient sur un écran.

### `public` ou `private`

```java
public static boolean isPalindrome(String text) {       // utilisable depuis les autres classes (et les tests)
    String cleaned = keepLetters(text).toLowerCase();
    return cleaned.equals(reverse(cleaned));
}

private static String keepLetters(String text) { ... }  // détail interne : invisible de l'extérieur
```

Les méthodes `private` d'aide permettent de nommer une étape et de la réutiliser, sans l'exposer. Ce que tu rends `public` est un engagement envers les classes qui l'utilisent ; ce qui est `private` peut changer librement.

### Surcharge

Plusieurs méthodes peuvent porter **le même nom** si leurs **paramètres diffèrent** (en nombre ou en type). Java choisit la bonne à la compilation.

```java
public static String encrypt(String text, int shift) { ... }

public static String encrypt(String text) {
    return encrypt(text, 3);   // la version courte délègue à la version complète
}
```

PHP n'a pas de surcharge : on y utilise des paramètres par défaut (`function encrypt($text, $shift = 3)`). Java n'a pas de paramètres par défaut : on utilise la surcharge.

### Passage des paramètres : toujours par valeur… de la référence

Java copie **toujours** la valeur de l'argument dans le paramètre. Mais pour un tableau (ou tout objet), cette valeur est une **référence** (l'adresse de l'objet) : la méthode reçoit une copie de l'adresse, qui pointe vers **le même tableau**.

```java
static void reset(int n) { n = 0; }
static void reset(int[] t) { t[0] = 0; }

int x = 5;
reset(x);         // x vaut toujours 5 : la méthode a modifié sa copie

int[] values = {5, 6};
reset(values);    // values[0] vaut 0 ! la méthode a modifié le tableau partagé
```

Conséquence : **une méthode ne doit pas modifier un tableau reçu sans que ce soit son rôle annoncé.** Pour trier sans abîmer l'original, on travaille sur une copie :

```java
int[] sorted = Arrays.copyOf(values, values.length);
Arrays.sort(sorted);   // values reste intact
```

---

## 2. `char` et `String`

### Deux types différents

| | `char` | `String` |
|---|---|---|
| Littéral | `'a'` (apostrophes) | `"a"` (guillemets) |
| Nature | Un nombre (code Unicode sur 16 bits), type primitif | Un objet, suite de caractères |
| Comparaison | `==` | `equals()` |

Comme un `char` est un nombre, on peut faire de l'arithmétique dessus :

```java
char c = 'c';
int position = c - 'a';          // 2 : la distance entre les deux codes
char next = (char) (c + 1);      // 'd' : c + 1 est un int, le cast (char) est obligatoire
boolean lower = c >= 'a' && c <= 'z';

System.out.println('a' + 'b');   // 195 ! (97 + 98) — rappel de la règle 1.1 de ton journal
System.out.println("a" + 'b');   // "ab" : dès qu'une String est à gauche, + concatène
```

La classe `Character` fournit les tests courants : `Character.isLetter(c)`, `isDigit`, `isLetterOrDigit`, `isWhitespace`, `isUpperCase`, `toUpperCase(c)`, `toLowerCase(c)`.

### Méthodes utiles de `String`

| Méthode | Exemple | Résultat |
|---|---|---|
| `length()` | `"Java".length()` | `4` |
| `charAt(i)` | `"Java".charAt(0)` | `'J'` |
| `substring(début, fin)` | `"Bonjour".substring(0, 3)` | `"Bon"` (fin exclue) |
| `indexOf(x)` | `"Bonjour".indexOf('j')` | `3` (`-1` si absent) |
| `contains(s)` | `"Bonjour".contains("jour")` | `true` |
| `startsWith` / `endsWith` | `"Main.java".endsWith(".java")` | `true` |
| `toUpperCase()` / `toLowerCase()` | `"Java".toUpperCase()` | `"JAVA"` |
| `strip()` | `"  hi  ".strip()` | `"hi"` |
| `isEmpty()` / `isBlank()` | `"   ".isBlank()` | `true` (vide ou que des espaces) |
| `equals` / `equalsIgnoreCase` | `"Java".equalsIgnoreCase("JAVA")` | `true` |
| `replace(a, b)` | `"a-b-c".replace("-", "/")` | `"a/b/c"` |
| `repeat(n)` | `"ab".repeat(3)` | `"ababab"` |
| `toCharArray()` | `"abc".toCharArray()` | `{'a', 'b', 'c'}` |
| `String.join(sép, ...)` | `String.join(", ", "a", "b")` | `"a, b"` |

Parcourir les caractères d'une chaîne :

```java
for (char c : text.toCharArray()) { ... }          // quand seule la valeur compte

for (int i = 0; i < text.length(); i++) {          // quand on a besoin de la position
    char c = text.charAt(i);
    char previous = i > 0 ? text.charAt(i - 1) : ' ';
}
```

### Les `String` sont immuables

Aucune méthode ne modifie une `String` : elles renvoient toutes une **nouvelle** chaîne.

```java
String name = "java";
name.toUpperCase();            // ❌ résultat perdu : name vaut toujours "java"
name = name.toUpperCase();     // ✅
```

### `split` et les expressions régulières

`split` découpe selon une **expression régulière** (regex) :

```java
"a,b,c".split(",")                 // {"a", "b", "c"}
"un   deux".split("\\s+")          // {"un", "deux"} : \s = un espace blanc, + = un ou plusieurs
"a, b; c".split("[,;]\\s*")        // {"a", "b", "c"} : [...] = un caractère parmi ceux-là
```

En Java, `\` doit être doublé dans une chaîne : la regex `\s+` s'écrit `"\\s+"`. Le point `.` signifie « n'importe quel caractère » en regex : pour découper sur un vrai point, écris `"\\."` ou mets-le entre crochets `"[.]"`.

⚠️ Pièges de `split` :

```java
"".split(",")          // {""} : UN élément vide, pas un tableau vide !
",a".split(",")        // {"", "a"} : un séparateur en tête produit une chaîne vide en tête
"a,".split(",")        // {"a"} : les chaînes vides en FIN sont supprimées
```

### `StringBuilder` : construire du texte efficacement

Comme une `String` est immuable, `result += x` dans une boucle **crée une nouvelle chaîne à chaque tour** et recopie tout le texte. Pour 10 000 tours, cela fait des millions de copies. `StringBuilder` est une chaîne **modifiable** :

```java
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 3; i++) {
    if (i > 1) {
        sb.append(", ");
    }
    sb.append(i);                   // append accepte String, char, int, double…
}
String result = sb.toString();      // "1, 2, 3"

new StringBuilder("abc").reverse().toString();   // "cba"
```

Règle pratique : `+` pour quelques concaténations sur une ligne, `StringBuilder` dans une boucle.

### Formater des nombres : attention à la langue

```java
String.format("%.2f", 3.14159)                // "3,14" sur un poste en français, "3.14" ailleurs !
String.format(Locale.ROOT, "%.2f", 3.14159)   // "3.14" partout
String.format("%-10s|", "Bob")                // "Bob       |" : aligné à gauche sur 10 caractères
String.format("%5.2f|", 9.5)                  // " 9.50|" : 5 caractères au total, 2 décimales
```

Sans `Locale`, `String.format`, `printf` et `Scanner.nextDouble()` utilisent la langue du système : ton programme se comporterait différemment selon la machine. `Locale.ROOT` désigne un format neutre (point décimal). En revanche, `Double.parseDouble("15.5")` attend **toujours** un point.

---

## 3. Tableaux

### Créer un tableau

```java
int[] scores = new int[5];                 // 5 cases, toutes à 0 (false pour boolean, null pour les objets)
int[] primes = {2, 3, 5, 7};               // initialisation directe
String[] names = new String[3];            // {null, null, null}
double[] empty = new double[0];            // tableau vide (≠ null !)

scores.length                              // 5 : un attribut, sans parenthèses (contrairement à String.length())
scores[0] = 42;                            // indices de 0 à length - 1
scores[5] = 1;                             // ❌ ArrayIndexOutOfBoundsException à l'exécution
```

**Un tableau a une taille fixe**, définie à sa création. Pour remplir un tableau dont on ne connaît pas la taille finale, il faut soit la calculer avant, soit prévoir une capacité maximale et tenir un compteur des cases utilisées. C'est la principale limite des tableaux, et la raison d'être de `ArrayList` (module 5).

### Parcourir

```java
for (int score : scores) {            // for-each : lecture seule, sans indice
    total += score;
}

for (int i = 0; i < scores.length; i++) {   // for classique : quand on a besoin de l'indice ou qu'on écrit
    scores[i] = scores[i] * 2;
}
```

### La classe `Arrays`

```java
import java.util.Arrays;

Arrays.toString(primes)            // "[2, 3, 5, 7]" (println(primes) affiche une adresse illisible : [I@1b6d3586)
Arrays.sort(scores)                // trie EN PLACE (modifie le tableau)
Arrays.copyOf(scores, 3)           // nouveau tableau avec les 3 premières cases
Arrays.equals(a, b)                // compare le contenu (a == b compare les références)
Arrays.fill(scores, -1)            // remplit toutes les cases
```

### Tableaux à deux dimensions

Un `int[][]` est un **tableau de tableaux** : chaque case contient une ligne.

```java
int[][] grid = new int[2][3];          // 2 lignes, 3 colonnes, remplies de 0
int[][] m = {{1, 2, 3},
             {4, 5, 6}};

m.length          // 2 : nombre de lignes
m[0].length       // 3 : nombre de colonnes (longueur de la ligne 0)
m[1][2]           // 6 : ligne 1, colonne 2

for (int i = 0; i < m.length; i++) {
    for (int j = 0; j < m[i].length; j++) {
        System.out.print(m[i][j] + " ");
    }
    System.out.println();
}

double[][] grades = new double[30][];  // 30 lignes non encore créées (null) : chacune aura sa propre taille
grades[0] = new double[]{12, 15.5};
grades[1] = new double[]{9};
```

`Arrays.toString` n'affiche qu'un niveau : pour un tableau 2D, utilise `Arrays.deepToString(m)`.

---

## 4. Lire des lignes avec `Scanner`

```java
Scanner scanner = new Scanner(System.in);
String line = scanner.nextLine();           // lit toute la ligne, retour à la ligne exclu
```

⚠️ Piège classique : `nextInt()` lit le nombre mais **laisse le retour à la ligne** dans l'entrée. Le `nextLine()` suivant renvoie alors une chaîne vide. Dans un programme à menus, le plus simple est de **tout lire avec `nextLine()`** et de convertir ensuite (`Integer.parseInt`, `Double.parseDouble`) ou de comparer des `String`.

Un `switch` fonctionne aussi sur les `String` :

```java
switch (choice) {
    case "1":
        ...
        break;
    case "0":
        running = false;
        break;
    default:
        System.out.println("Choix inconnu");
}
```
