# M03 — Exercices

Le code de départ se trouve dans `src/main/java/com/learnjava/m03/`. Nouveauté de ce module : **c'est à toi de déclarer les attributs** de chaque classe (un `TODO` en tête de classe te le rappelle). Ensuite, comme d'habitude, remplace chaque `throw new UnsupportedOperationException("TODO");` en suivant la Javadoc.

Pour raccourcir les commandes : `M=module-03-classes-objets`.

| # | Exercice | Difficulté | Commande de test |
|---|---|---|---|
| 1 | `ex01/Rectangle` | ⭐ | `./mvnw -q -pl $M test -Dtest=RectangleTest` |
| 2 | `ex02/BankAccount` | ⭐⭐ | `./mvnw -q -pl $M test -Dtest=BankAccountTest` |
| 3 | `ex03/Temperature` | ⭐⭐ | `./mvnw -q -pl $M test -Dtest=TemperatureTest` |
| 4 | `ex04/Book` puis `ex04/Library` | ⭐⭐⭐ | `./mvnw -q -pl $M test -Dtest='BookTest,LibraryTest'` |
| 🎯 | `miniprojet/Contact`, `ContactBook`, `ContactBookApp` | ⭐⭐⭐ | `./mvnw -q -pl $M test -Dtest='ContactTest,ContactBookTest'` |

Chaque classe a un test **« Les attributs sont privés »** : il vérifie, par réflexion, que tous tes attributs (hors constantes `static final`) sont `private`.

---

## Ex 1 — `Rectangle` ⭐

Ta première classe : deux attributs, deux constructeurs, des getters, quelques calculs et un `toString`.

**Ce que tu pratiques :** déclarer des attributs `private`, `this.width = width`, appeler un constructeur depuis un autre avec `this(...)`, lire les attributs d'un autre objet de la même classe.

**Questions à te poser :**
- Le constructeur à un paramètre a-t-il besoin de répéter la règle sur les dimensions négatives ?
- `isSquare` et `canContain` renvoient un `boolean` : as-tu besoin d'un seul `if` (règle **3.1** de ton journal) ?
- Dans `canContain`, que se passe-t-il si tu lis `other.width` alors que `other` vaut `null` ? Comment l'ordre des conditions dans un `&&` peut-il te protéger ?

## Ex 2 — `BankAccount` ⭐⭐

Un compte bancaire dont le solde ne peut jamais devenir négatif, ni être modifié sans passer par les méthodes prévues.

**Ce que tu pratiques :** l'encapsulation (pas de setter : un test le vérifie), les attributs `final`, un compteur `static`, `this` et `==` entre objets, un objet qui agit sur un autre.

**Indices :**
- Le numéro de compte : un attribut `static` compte les comptes créés, un attribut d'instance garde le numéro de *ce* compte.
- Les deux constructeurs doivent attribuer un numéro, mais ce code ne doit exister qu'une fois. Lequel des deux peut appeler l'autre ?
- `transferTo` : quand peut-on être sûr que le virement va réussir ? Dans quel ordre appeler `withdraw` et `deposit` pour qu'un échec ne laisse aucun compte modifié ?
- `formatBalance` : pas de `double` ! Les euros et les centimes s'obtiennent avec `/` et `%` (règle **1.3**), et un seul `String.format` suffit (règle **8.2**). Comment afficher `5` centimes sous la forme `05` ?

**Piège :** un virement vers soi-même. Relis la section « `==` compare les références » du cours.

## Ex 3 — `Temperature` ⭐⭐

Une température **immuable**, créée uniquement par des méthodes de fabrique statiques, comme `LocalDate.of(...)`.

**Ce que tu pratiques :** attributs `private final`, constructeur `private`, méthodes `static` qui créent des objets, méthode qui renvoie un nouvel objet au lieu de modifier `this`.

**Indices :**
- Stocke la température dans **une seule** unité : les autres se calculent. Pourquoi stocker les trois serait-il risqué ?
- Le zéro absolu doit être appliqué partout : `ofCelsius`, `ofFahrenheit`, `ofKelvin` et `plus`. Quel est le seul endroit par lequel passent toutes ces méthodes ?
- `9 / 5` vaut `1` en Java. Relis la règle **1.2** de ton journal : littéral décimal ou cast ?
- `-273.15`, `273.15`, `32` : lesquels méritent une constante (règle **5.1**) ?

**Pièges (les tests les vérifient) :** la classe ne doit avoir **aucun constructeur public**. Sans constructeur écrit, Java en ajoute un public automatiquement.

## Ex 4 — `Book` et `Library` ⭐⭐⭐

Commence par `Book` (`-Dtest=BookTest`), puis écris `Library`, qui range des livres dans un tableau de capacité fixe.

**Ce que tu pratiques :** un tableau d'objets avec un compteur, `null` (dans le tableau et comme valeur de retour), les références partagées, une copie défensive, la délégation (la bibliothèque demande au livre de s'emprunter).

**Indices :**
- `new Book[capacity]` ne crée **aucun** livre : les cases valent `null`. Ne parcours que les cases réellement remplies.
- `addBook` peut réutiliser `isFull` et `findByIsbn`. `borrow` et `giveBack` peuvent réutiliser `findByIsbn` et les méthodes de `Book`.
- `findByAuthor` : même problème que `words` dans le `WordCounter` du module 2 (un tableau de la taille exacte).
- `getBooks` : relis « Ne pas laisser fuir l'intérieur » dans le cours.

**Pièges :**
- Comparer deux ISBN (des `String`) avec `==` compile, et fonctionne parfois… jusqu'au jour où ça ne fonctionne plus.
- Le test `borrowThroughLibrary` vérifie qu'emprunter par la bibliothèque modifie **l'objet** `Book` que le test a créé : la bibliothèque ne doit pas en faire de copie.

## 🎯 Mini-projet — Carnet de contacts ⭐⭐⭐

Un carnet de contacts en console : ajouter, afficher, rechercher, modifier, supprimer. Compare avec le `GradeBook` du module 2 : ici, chaque contact est **un objet**, et toute la logique est dans des classes testées. La classe `ContactBookApp` ne s'occupe que de lire et d'afficher.

1. Écris `Contact` (`-Dtest=ContactTest`).
2. Écris `ContactBook` (`-Dtest=ContactBookTest`).
3. Écris `main` dans `ContactBookApp` en suivant les étapes indiquées en commentaire.
4. Lance-le :
   ```bash
   ./mvnw -q -pl module-03-classes-objets compile exec:java
   ```

**Points d'attention :**
- `Contact` : le même nettoyage s'applique dans le constructeur et dans les setters. Écris-le une seule fois, dans une méthode privée d'aide.
- `getInitials` : relis la règle **1.1** de ton journal avant d'écrire la ligne.
- `matches` : faut-il traiter la recherche vide à part ? Que vaut `"Ada".contains("")` ?
- `ContactBook.remove` : c'est la question bonus du module 2. Que faut-il faire des cases qui suivent le contact supprimé, et de la dernière case ?
- `findByName` et `remove` cherchent tous les deux un contact par son nom : une méthode privée d'aide qui renvoie un **indice** peut servir aux deux.
- Choix 4 du menu : `findByName` peut renvoyer `null`.
- **Nommage** (règles **6.1** à **6.3**) : beaucoup de variables se ressemblent ici (`contact`, `contacts`, `count`, `index`, `fullName`…). Choisis chaque nom avec soin.

**Bonus :**
- Refuser un téléphone qui ne contient pas exactement 10 chiffres après nettoyage. Comment le signaler sans exception ? (Indice : le type de retour d'un setter n'est pas obligatoirement `void`, mais est-ce une bonne idée ?)
- Ajouter un choix « 6. Trier par nom ». Sans `Comparator` (module 5), un tri à bulles sur le tableau suffit : `a.compareToIgnoreCase(b)` renvoie un nombre négatif si `a` vient avant `b` dans l'ordre alphabétique.

---

✅ Quand tout est vert (`./mvnw -q -pl module-03-classes-objets test`), coche le module dans `PROGRESSION.md` et demande une revue de code.
