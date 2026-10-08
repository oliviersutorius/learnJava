# M03 — Classes, objets, encapsulation

## Objectifs

À la fin de ce module, tu sais :
- écrire une classe avec des attributs, des constructeurs et des méthodes d'instance, et utiliser `this` ;
- protéger l'état d'un objet : attributs `private`, getters, et pas de setter quand il n'en faut pas ;
- choisir entre `static` et instance ;
- redéfinir `toString` ;
- expliquer ce que contient vraiment une variable de type objet (une référence), ce que compare `==`, et ce qu'est `null` ;
- écrire un objet immuable.

> 📓 **Ton journal d'erreurs** (`docs/04-mes-erreurs-java.md`) : ce module retravaille les quatre règles encore ouvertes du M01 : **1.1** (`char + char`), **1.2** (littéral décimal plutôt que cast), **1.3** (`/` et `%`) et **3.1** (renvoyer directement la condition). Il sollicite aussi beaucoup le **nommage** (règles **6.1** à **6.3**) : une classe, c'est d'abord des noms d'attributs et de méthodes bien choisis. Relis ces règles avant de commencer.

---

## 1. Classe et objet

Une **classe** est un plan ; un **objet** (ou instance) est une construction réalisée d'après ce plan. Chaque objet a **ses propres valeurs** d'attributs.

```java
public class Rectangle {

    private double width;      // attributs (ou « champs ») : l'état de CHAQUE rectangle
    private double height;

    public Rectangle(double width, double height) {   // constructeur : même nom que la classe, pas de type de retour
        this.width = width;    // this.width = l'attribut ; width = le paramètre
        this.height = height;
    }

    public double area() {     // méthode d'instance : pas de static, elle travaille sur CET objet
        return width * height; // pas d'ambiguïté ici : width désigne l'attribut
    }
}
```

```java
Rectangle small = new Rectangle(2, 3);
Rectangle big = new Rectangle(10, 20);
small.area();   // 6.0
big.area();     // 200.0 : même méthode, données différentes
```

| | PHP | Java |
|---|---|---|
| Créer un objet | `new Rectangle(2, 3)` | `new Rectangle(2, 3)` |
| Appeler une méthode | `$r->area()` | `r.area()` |
| L'objet courant | `$this->width` | `this.width` (et `this.` est facultatif s'il n'y a pas d'ambiguïté) |
| Déclarer un attribut | `private float $width;` | `private double width;` |
| Constructeur | `__construct(...)` | une méthode qui porte le nom de la classe |
| Promotion de propriétés | `__construct(private float $width)` | n'existe pas pour les classes (les `record` du module 4 font quelque chose d'approchant) |

Un attribut non initialisé prend une valeur par défaut : `0`, `0.0`, `false`, et **`null` pour les objets** (dont `String`). Une variable locale, elle, doit être initialisée avant d'être lue, sinon le code ne compile pas.

### Conventions de nommage

- Classe : `PascalCase`, un **nom** (`BankAccount`, `Book`).
- Méthode : `camelCase`, un **verbe** (`deposit`, `findByIsbn`), ou une question pour un `boolean` (`isSquare`, `hasEmail`, `canContain`).
- Getters : `getXxx()` pour lire un attribut, **`isXxx()` pour un `boolean`** (`isAvailable()`, pas `getAvailable()`). Ce sont les conventions « JavaBeans », sur lesquelles s'appuient beaucoup de bibliothèques (Jackson, Spring…).
- Le nom d'un attribut dit ce qu'il contient, avec son unité si elle n'est pas évidente : `balanceInCents`, pas `balance` ni `bal` (règle **6.2**).

---

## 2. Constructeurs

### Plusieurs constructeurs, une seule logique

Java n'a pas de paramètres par défaut : on **surcharge** le constructeur, comme une méthode (module 2). Pour ne pas dupliquer la logique, un constructeur en appelle un autre avec **`this(...)`**, obligatoirement en première instruction :

```java
public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
}

public Rectangle(double side) {
    this(side, side);       // un carré est un rectangle dont les deux côtés sont égaux
}
```

Si tu n'écris **aucun** constructeur, Java en fournit un sans paramètre (le « constructeur par défaut »). Dès que tu en écris un, celui-là disparaît.

### Garantir un état valide

Le constructeur est l'endroit où l'on garantit que l'objet naît **dans un état valide** (on parle d'**invariant** : une propriété toujours vraie, comme « un solde n'est jamais négatif »). Les exceptions, qui permettent de refuser une valeur invalide, arrivent au module 5. En attendant, ce module corrige la valeur (`Math.max(0, width)`) ou, pour une opération, renvoie `false` sans rien modifier :

```java
public class Stock {
    private int quantity;

    public Stock(int initialQuantity) {
        quantity = Math.max(0, initialQuantity);   // une quantité négative n'a pas de sens : ramenée à 0
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean remove(int amount) {
        if (amount <= 0 || amount > quantity) {
            return false;                          // opération refusée, l'état ne change pas
        }
        quantity -= amount;
        return true;
    }
}
```

---

## 3. Encapsulation

### `private` par défaut

```java
public class BankAccount {
    public long balanceInCents;        // ❌ n'importe qui peut écrire account.balanceInCents = -1_000_000;
}
```

```java
public class BankAccount {
    private long balanceInCents;       // ✅ seul le code de la classe peut le modifier

    public long getBalanceInCents() {  // lecture autorisée
        return balanceInCents;
    }

    public boolean deposit(long amountInCents) { ... }   // modification contrôlée, qui respecte l'invariant
}
```

**Tous les attributs sont `private`.** On ouvre ensuite ce qui est nécessaire, méthode par méthode. Les tests de ce module vérifient que tes attributs sont privés.

### Pas de setter automatique

En Laravel, un modèle Eloquent accepte n'importe quelle affectation (`$account->balance = -5`). En Java, on n'écrit **pas** un getter et un setter par attribut par réflexe : un `setBalanceInCents` permettrait de contourner `deposit` et `withdraw`. Un setter n'existe que si modifier directement l'attribut a un sens métier (un numéro de téléphone, par exemple).

### `private` concerne la classe, pas l'objet

Une méthode d'un `Rectangle` peut lire les attributs privés **d'un autre** `Rectangle` :

```java
public boolean hasSameWidthAs(Rectangle other) {
    return width == other.width;   // other.width est accessible ici, bien qu'il soit private
}
```

C'est la même règle qu'en PHP.

### Ne pas laisser fuir l'intérieur

Un getter qui renvoie un **tableau** interne donne à l'appelant la **référence** de ce tableau, qu'il peut alors modifier (module 2, « passage des paramètres »). On renvoie une copie :

```java
public int[] getScores() {
    return Arrays.copyOf(scores, scores.length);   // l'appelant reçoit SA copie
}
```

---

## 4. `static` ou instance ?

| | Instance (sans `static`) | Classe (`static`) |
|---|---|---|
| Appartient à | chaque objet | la classe, partagée par tous les objets |
| Appel | `ticket.getNumber()` | `Ticket.issuedCount()` |
| Accès à `this` | oui | **non** : il n'y a pas d'objet courant |
| PHP | `$this->x` | `self::$x`, `static::method()` |

```java
public class Ticket {
    private static int issuedCount = 0;    // UN SEUL compteur pour toute la classe
    private final int number;              // un numéro PAR ticket

    public Ticket() {
        issuedCount++;
        number = issuedCount;              // chaque ticket reçoit le numéro suivant
    }

    public int getNumber() {
        return number;
    }

    public static int issuedCount() {
        return issuedCount;                // pas de this ici : on ne peut pas lire number
    }
}
```

### Les méthodes de fabrique statiques

Une méthode `static` peut créer et renvoyer un objet. Elle a un **nom**, ce qu'un constructeur n'a pas :

```java
Temperature t = Temperature.ofCelsius(21.5);       // plus clair que new Temperature(21.5) : Celsius ou Kelvin ?
Temperature u = Temperature.ofFahrenheit(70);
```

Le constructeur devient alors `private` : on ne peut plus créer l'objet qu'en passant par ces méthodes. Tu connais déjà ce style : `LocalDate.of(2026, 10, 8)`, `List.of(...)`, `Carbon::create(...)` en Laravel.

---

## 5. `toString`

`System.out.println(objet)` et `"texte" + objet` appellent automatiquement la méthode `toString()` de l'objet. Par défaut, elle renvoie un texte peu utile (`com.learnjava.m03.Point@1b6d3586`). On la **redéfinit** :

```java
public class Point {
    private final double x;
    private final double y;
    ...

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "(%.2f ; %.2f)", x, y);   // Locale.ROOT : toujours un point décimal
    }
}
```

L'annotation `@Override` demande au compilateur de vérifier que tu redéfinis bien une méthode existante : une faute de frappe (`tostring`) devient une erreur de compilation au lieu d'un bug silencieux. Le module 4 explique d'où vient cette méthode (la classe `Object`). C'est l'équivalent de `__toString()` en PHP.

---

## 6. Références et `null`

### Une variable contient une référence, pas l'objet

```java
Stock a = new Stock(10);
Stock b = a;               // b reçoit une COPIE DE LA RÉFÉRENCE : a et b désignent le MÊME objet
b.remove(3);
a.getQuantity();           // 7 ! l'objet a été modifié « à travers » b
```

C'est le comportement des objets en PHP aussi (`$b = $a;` partage l'objet ; il faut `clone` pour copier).

### `==` compare les références

```java
Stock c = new Stock(10);
Stock d = new Stock(10);
c == d;            // false : deux objets distincts, même s'ils ont les mêmes valeurs
c == c;            // true

if (target == this) { ... }    // « est-ce le même objet que moi ? » : un usage légitime de == sur des objets
```

Pour comparer le **contenu**, on utilise `equals()` : c'est ce que tu fais déjà avec les `String`. Écrire son propre `equals` pour ses classes demande quelques précautions : ce sera au module 5. Dans ce module, compare des attributs qui identifient l'objet (un ISBN, un nom), avec `equals` pour les `String`.

### `null`

`null` signifie « aucun objet ». Appeler une méthode sur `null` lève une `NullPointerException` :

```java
Book book = library.findByIsbn("inconnu");   // renvoie null : aucun livre trouvé
book.getTitle();                             // ❌ NullPointerException

if (book != null) {                          // ✅ vérifier avant d'utiliser
    System.out.println(book.getTitle());
}
```

Renvoyer `null` pour « pas trouvé » est courant, mais oblige l'appelant à y penser. Le module 7 présentera `Optional`, qui rend cette absence explicite.

### Un tableau d'objets contient des références

```java
Book[] books = new Book[10];   // 10 cases, toutes à null : AUCUN livre n'a été créé
int count = 0;                 // nombre de cases réellement utilisées

books[count] = new Book(...);  // on remplit les cases dans l'ordre
count++;

for (int i = 0; i < count; i++) {   // on ne parcourt que les cases remplies, pas books.length !
    ...
}
```

C'est le « tableau + compteur » du mini-projet du module 2, mais avec **un seul** tableau d'objets au lieu de tableaux parallèles.

---

## 7. Objets immuables

Un objet **immuable** ne change plus après sa création. Tu en utilises déjà : `String`, `Integer`, `LocalDate`.

Recette :
1. tous les attributs `private final` (une fois affecté dans le constructeur, un attribut `final` ne peut plus changer : le compilateur le vérifie) ;
2. aucun setter ;
3. les « modifications » renvoient un **nouvel** objet.

```java
public class Money {
    private final long cents;

    private Money(long cents) {
        this.cents = cents;
    }

    public static Money ofCents(long cents) {
        return new Money(cents);
    }

    public Money plus(Money other) {
        return new Money(cents + other.cents);   // this n'est pas modifié
    }
}

Money price = Money.ofCents(1000);
price.plus(Money.ofCents(500));            // ❌ résultat perdu, comme avec name.toUpperCase()
Money total = price.plus(Money.ofCents(500));   // ✅ price vaut toujours 10 €
```

**Pourquoi ?** Un objet immuable peut être partagé sans risque : personne ne peut le modifier « dans ton dos » (souviens-toi de `b.remove(3)` plus haut), et il est automatiquement sûr quand plusieurs threads l'utilisent (module 10). C'est exactement la différence entre `Carbon` et `CarbonImmutable` en Laravel : avec `Carbon`, `$date->addDay()` modifie l'objet partagé, ce qui est une source de bugs classique.

---

## 8. Un mot sur l'argent

Ne stocke jamais une somme d'argent dans un `double` : `0.1 + 0.2` vaut `0.30000000000000004`. On compte en **centimes** dans un `long` (ou on utilise `BigDecimal`, qu'on verra plus tard), et on ne convertit en euros qu'à l'affichage, avec `/` et `%` (règle **1.3** de ton journal) :

```java
long cents = 1234;
cents / 100    // 12 : les euros
cents % 100    // 34 : les centimes restants
```
