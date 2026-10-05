# M01 — Syntaxe, types, conditions, boucles

## Objectifs

À la fin de ce module, tu sais :
- lire et écrire la structure d'un programme Java (classe, `main`, méthodes statiques) ;
- choisir le bon type primitif et éviter les pièges de la division entière et du débordement ;
- écrire des conditions (`if`, `switch`) et des boucles (`for`, `while`) ;
- lire une saisie clavier avec `Scanner` ;
- lancer des tests JUnit et les faire passer au vert.

---

## 1. Anatomie d'un programme

```java
package com.learnjava.m01.demo;          // le package = le dossier où se trouve le fichier

import java.util.Scanner;                // importe une classe d'un autre package (comme « use » en PHP)

public class Demo {                      // le fichier DOIT s'appeler Demo.java

    public static void main(String[] args) {   // point d'entrée du programme
        System.out.println("Bonjour");         // affiche une ligne ; chaque instruction finit par ;
    }
}
```

- Tout le code vit **dans une classe**.
- Les blocs sont délimités par `{ }`. L'indentation n'a pas de sens pour le compilateur, mais elle est essentielle pour le lecteur (4 espaces).
- Commentaires : `// une ligne`, `/* plusieurs lignes */`, `/** Javadoc */` (documentation d'une classe ou d'une méthode).

## 2. Méthodes statiques : le strict minimum

Dans ce module, les exercices sont des **méthodes statiques** : l'équivalent d'une fonction PHP rangée dans une classe. Le module 2 les approfondit, le module 3 explique ce que `static` signifie vraiment.

```java
//      visibilité  static  type de retour  nom    paramètres typés
public  static      int     square         (int x) {
    return x * x;
}

// Appel depuis une autre classe : NomDeClasse.nomDeMethode(arguments)
int result = MathDemo.square(4);   // 16
```

`void` signifie « ne renvoie rien ». Une méthode qui déclare un type de retour **doit** renvoyer une valeur sur tous les chemins possibles, sinon le compilateur refuse.

## 3. Variables et types primitifs

Une variable se déclare avec son **type**, qui ne pourra plus changer :

```java
int age = 30;
double price = 19.99;
boolean active = true;
char initial = 'O';           // apostrophes simples : un seul caractère
String name = "Olivier";      // guillemets doubles : String est un objet, pas un primitif
final int MAX = 100;          // final : ne peut plus être réaffectée (constante)
var count = 10;               // var : le type (int) est déduit, mais il reste fixe
```

| Type | Taille | Plage / usage |
|---|---|---|
| `byte` | 8 bits | -128 à 127 |
| `short` | 16 bits | -32 768 à 32 767 |
| **`int`** | 32 bits | ±2,1 milliards : **l'entier par défaut** |
| `long` | 64 bits | ±9,2 × 10¹⁸ (littéral : `3_000_000_000L`) |
| `float` | 32 bits | décimal peu précis, rarement utilisé |
| **`double`** | 64 bits | **le décimal par défaut** |
| `boolean` | — | `true` / `false` |
| `char` | 16 bits | un caractère Unicode |

`1_000_000` : les underscores rendent les grands nombres lisibles.

> ⚠️ `double` n'est **jamais** exact pour l'argent : `0.1 + 0.2` vaut `0.30000000000000004`. Pour les montants, on utilise `BigDecimal` (module 5) ou des centimes en `long`.

### Conversions

```java
int i = 42;
double d = i;                // int -> double : automatique (élargissement, sans perte)
int back = (int) 3.99;       // double -> int : cast explicite, TRONQUE la partie décimale -> 3
long big = i;                // automatique
int parsed = Integer.parseInt("123");   // String -> int
String text = String.valueOf(42);      // int -> String (ou "" + 42)
```

## 4. Opérateurs

```java
int a = 7, b = 2;
a + b   // 9
a - b   // 5
a * b   // 14
a / b   // 3   ⚠️ division ENTIÈRE entre deux int
a % b   // 1   reste (modulo) : très utile pour « divisible par », « pair/impair »
7.0 / 2 // 3.5 : dès qu'un opérande est double, la division est décimale

a++;  a--;  a += 5;  a *= 2;   // incrément, décrément, opérateurs composés

// Comparaisons -> boolean
a == b   a != b   a < b   a <= b   a > b   a >= b

// Logiques
&&  // ET (court-circuit : si la gauche est false, la droite n'est pas évaluée)
||  // OU (court-circuit : si la gauche est true, la droite n'est pas évaluée)
!   // NON

// Ternaire
String parity = (a % 2 == 0) ? "pair" : "impair";
```

### ⚠️ Le débordement (overflow)

Un `int` est limité à `Integer.MAX_VALUE` = 2 147 483 647. Au-delà, **pas d'erreur** : le résultat « fait le tour » et devient négatif.

```java
int max = Integer.MAX_VALUE;
System.out.println(max + 1);          // -2147483648 !
System.out.println(50_000 * 50_000);  // -1794967296 !  (le vrai résultat 2,5 milliards ne tient pas dans un int)
long ok = 50_000L * 50_000;           // 2500000000 : calcul fait en long
```

Garde ce piège en tête pour l'exercice 5.

## 5. Conditions

```java
if (temperature < 0) {
    System.out.println("Gel");
} else if (temperature < 15) {
    System.out.println("Frais");
} else {
    System.out.println("Bon");
}
```

Mets toujours les accolades, même pour une seule ligne : ajouter une deuxième ligne plus tard sans elles est une source classique de bugs.

### `switch`

```java
switch (day) {
    case 6, 7:                     // plusieurs valeurs pour un même cas
        System.out.println("Week-end");
        break;                     // sans break, l'exécution « tombe » dans le cas suivant !
    case 1, 2, 3, 4, 5:
        System.out.println("Semaine");
        break;
    default:
        System.out.println("Jour invalide");
}
```

Dans une méthode, un `return` dans un `case` remplace le `break`. Le module 8 présente la forme moderne `switch` *expression* (`case 6, 7 -> "Week-end";`), plus concise et sans `break`.

### Comparer des `String`

```java
String answer = scanner.next();
if (answer.equals("oui")) { ... }   // ✅ compare le contenu
if (answer == "oui") { ... }        // ❌ compare les références (les adresses mémoire) : résultat imprévisible
```

## 6. Boucles

```java
// for : quand on connaît le nombre de tours
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}

// while : tant qu'une condition est vraie (on ne sait pas combien de tours à l'avance)
int n = 1;
while (n < 1000) {
    n *= 2;
}

// do-while : au moins un tour
do {
    input = scanner.nextInt();
} while (input < 0);

// break sort de la boucle ; continue passe directement au tour suivant
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) continue;   // ignore les pairs
    if (i > 7) break;           // arrête tout après 7
    System.out.println(i);      // 1 3 5 7
}
```

## 7. Formater et afficher

```java
System.out.println("Total : " + total);        // + concatène une String avec n'importe quoi
System.out.print("Sans retour à la ligne");
System.out.printf("%.2f €%n", 3.14159);        // 3.14 €   (%n = retour à la ligne)
String s = String.format("%02d:%02d", 5, 7);   // "05:07"  (%d entier, 02 = sur 2 chiffres, complété par des 0)
String t = "  texte  ".strip();                // "texte"
```

## 8. Lire le clavier avec `Scanner`

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("Ton âge : ");
int age = scanner.nextInt();          // lit un entier (lève une exception si on tape « abc »)
String word = scanner.next();         // lit un mot
String line = scanner.nextLine();     // lit une ligne entière
boolean ok = scanner.hasNextInt();    // le prochain élément est-il un entier ? (ne consomme rien)
```

Nombres aléatoires :

```java
import java.util.Random;

Random random = new Random();
int dice = random.nextInt(1, 7);      // entre 1 (inclus) et 7 (EXCLU) -> 1 à 6
```

## 9. Les tests JUnit : ton filet de sécurité

Chaque exercice est accompagné d'une classe de test dans `src/test/java`, dans le même package. Exemple :

```java
@Test
void isFreezing() {
    assertThat(TemperatureConverter.isFreezing(-5)).isTrue();
    //         ^ valeur calculée par TON code       ^ ce qu'on attend
}

@ParameterizedTest
@CsvSource({"0, 32", "100, 212"})       // le même test lancé avec plusieurs jeux de données
void celsiusToFahrenheit(double celsius, double expected) { ... }
```

Workflow pour chaque exercice :
1. Lis l'énoncé dans `EXERCICES.md` et la Javadoc de la méthode.
2. Lance le test : il est rouge (`UnsupportedOperationException: TODO`).
3. Remplace le `throw new UnsupportedOperationException("TODO");` par ton code.
4. Relance le test jusqu'à ce qu'il soit vert. Lis bien les messages d'échec : ils indiquent la valeur attendue et la valeur obtenue.

```bash
./mvnw -q -pl module-01-bases test -Dtest=TemperatureConverterTest
```

Dans IntelliJ : clic sur le ▶ vert à côté de la classe de test, ou `Ctrl+Shift+F10`.

> Ne modifie jamais les tests pour les faire passer. Si tu penses qu'un test est faux, parles-en d'abord.
