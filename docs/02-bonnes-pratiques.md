# 02 — Bonnes pratiques et sources de documentation

## Conventions de nommage

| Élément | Convention | Exemple |
|---|---|---|
| Classe, interface, record, enum | `PascalCase`, nom | `BankAccount`, `PaymentService` |
| Méthode | `camelCase`, verbe | `calculateTotal()`, `isValid()`, `getName()` |
| Variable, paramètre | `camelCase` | `totalPrice`, `userCount` |
| Constante (`static final`) | `UPPER_SNAKE_CASE` | `MAX_RETRY`, `DEFAULT_TIMEOUT` |
| Package | minuscules, nom de domaine inversé | `com.learnjava.m01.ex01` |
| Booléens | préfixe `is`, `has`, `can` | `isEmpty()`, `hasChildren` |

Le code (noms, identifiants) s'écrit **en anglais**, comme dans la quasi-totalité des projets professionnels.

## Organisation des packages

- Le package doit correspondre au chemin du fichier : `com/learnjava/m01/ex01/TemperatureConverter.java` déclare `package com.learnjava.m01.ex01;`.
- Structure Maven standard, que tous les outils reconnaissent :
  ```
  src/main/java       code de production
  src/main/resources  fichiers de configuration (application.yml…)
  src/test/java       tests (même package que la classe testée)
  ```
- Dans une application, on préfère **regrouper par fonctionnalité** (`com.app.order`, `com.app.customer`) plutôt que par couche technique (`com.app.controllers`, `com.app.services`) : le code qui change ensemble reste ensemble.

## Principes de code

**Lisibilité d'abord.** Le code est lu bien plus souvent qu'il n'est écrit : des noms explicites, des méthodes courtes qui font une seule chose, pas de « nombres magiques » (on les nomme avec une constante).

**SOLID** (vus en détail aux niveaux POO et Spring) :
- **S**ingle responsibility : une classe a une seule raison de changer.
- **O**pen/closed : ouverte à l'extension, fermée à la modification.
- **L**iskov substitution : une sous-classe doit pouvoir remplacer sa classe mère sans surprise.
- **I**nterface segregation : plusieurs petites interfaces plutôt qu'une grosse.
- **D**ependency inversion : dépendre d'abstractions (interfaces), pas d'implémentations.

**Immuabilité.** Un objet qui ne change pas après sa création est plus simple à comprendre, à tester et à partager entre threads. Champs `private final`, pas de setters, `record` pour les objets de données, `List.of(...)` pour les listes non modifiables.

**Exceptions.**
- Ne jamais avaler une exception (`catch (Exception e) {}` vide).
- Attraper l'exception la plus précise possible, au niveau où l'on sait quoi en faire.
- Lever des exceptions explicites pour les entrées invalides (`IllegalArgumentException`), avec un message utile.
- `try-with-resources` pour fermer automatiquement fichiers et connexions.

**`null` et `Optional`.**
- `null` est la première source de bugs Java (`NullPointerException`).
- Ne pas renvoyer `null` pour une collection : renvoyer une collection vide.
- Pour une valeur de retour qui peut être absente, renvoyer un `Optional<T>`. Ne pas utiliser `Optional` pour les champs ou les paramètres.
- Valider tôt : `Objects.requireNonNull(param, "param must not be null")`.

**Tests unitaires.**
- Un test vérifie un comportement, avec un nom qui le décrit.
- Structure *Arrange / Act / Assert* (ou *Given / When / Then*).
- Tests rapides, indépendants les uns des autres et répétables.
- Tester les cas limites : 0, négatif, vide, `null`, valeurs maximales.

**Préférer la bibliothèque standard.** `java.time` pour les dates, `java.nio.file.Files` pour les fichiers, `java.util` pour les collections : elles sont éprouvées et connues de tous.

## Outils qualité

| Outil | Rôle |
|---|---|
| Formatage de l'IDE (`Ctrl+Alt+L`) ou **Spotless** / google-java-format | Style de code uniforme, appliqué automatiquement |
| **Checkstyle** | Vérifie les conventions (nommage, longueur des méthodes, imports…) |
| **SpotBugs** | Détecte les bugs probables dans le bytecode (NPE potentielles, ressources non fermées…) |
| **SonarQube for IDE** (ex-SonarLint) | Plugin IntelliJ/VS Code : bugs, code smells et failles en direct pendant l'écriture |
| **JaCoCo** | Couverture de code par les tests |

Ces outils seront branchés sur Maven au niveau 5 (Outillage).

## Sources de référence

**Officielles :**
- [dev.java](https://dev.java/learn/) : le site d'apprentissage officiel d'Oracle, avec des tutoriels à jour pour le Java moderne. **C'est le meilleur point de départ.**
- [Javadoc de l'API Java 25](https://docs.oracle.com/en/java/javase/25/docs/api/index.html) : la documentation de chaque classe de la bibliothèque standard. Dans IntelliJ, `Ctrl+Q` l'affiche sous le curseur.
- [JEPs (OpenJDK)](https://openjdk.org/jeps/0) : les propositions qui décrivent chaque nouvelle fonctionnalité du langage, avec leurs motivations.
- [Spring Guides](https://spring.io/guides) et la [doc de référence Spring Boot](https://docs.spring.io/spring-boot/) : tutoriels courts et documentation officielle.
- [Guide de l'utilisateur JUnit](https://docs.junit.org/current/user-guide/) et [AssertJ](https://assertj.github.io/doc/).

**Tutoriels :**
- [Baeldung](https://www.baeldung.com/) : des milliers d'articles pratiques sur Java et Spring. On y tombe à chaque recherche Google, et c'est fiable.
- [Inside Java](https://inside.java/) : articles et podcasts de l'équipe Java d'Oracle.

**Livres de référence :**
- *Effective Java*, Joshua Bloch : LE livre des bonnes pratiques Java. À lire après le niveau 3.
- *Modern Java in Action*, Urma, Fusco et Mycroft : lambdas, streams et programmation fonctionnelle.
- *Java Concurrency in Practice*, Brian Goetz : la référence sur la concurrence (niveau 6).
- *Clean Code*, Robert C. Martin : la lisibilité, à lire avec un regard critique.

**Communauté :** Stack Overflow (tag `java`), r/java.
