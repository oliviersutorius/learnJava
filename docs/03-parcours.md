# 03 — Le parcours d'apprentissage

8 niveaux, 13 modules. Chaque module contient :
- `COURS.md` : un cours court avec des exemples commentés ;
- `EXERCICES.md` : les énoncés ;
- `src/main/java` : le code de départ à compléter (les méthodes lèvent `UnsupportedOperationException("TODO")`) ;
- `src/test/java` : les tests JUnit, rouges au départ, que tu dois faire passer au vert ;
- un mini-projet de synthèse ;
- les solutions dans `solutions/<module>/`, **à n'ouvrir qu'après avoir vraiment essayé**.

Règle du jeu : on ne passe au module suivant que lorsque **tous les tests du module sont verts**.

Les modules sont générés au fur et à mesure de ta progression ; seuls ceux marqués ✅ existent déjà.

---

## Niveau 1 — Bases

### ✅ M01. Syntaxe, types, conditions, boucles — `module-01-bases`
Structure d'un programme, types primitifs, opérateurs, débordement, `if`/`switch`, `for`/`while`, méthodes statiques (premiers pas), `Scanner`.
- Ex 1 `TemperatureConverter` : `double`, opérateurs
- Ex 2 `LeapYear` : modulo, opérateurs logiques, `switch`
- Ex 3 `FizzBuzz` : boucles, ordre des conditions
- Ex 4 `TimeFormatter` : division entière, `String.format`
- Ex 5 `PrimeNumbers` : boucles imbriquées, optimisation, overflow
- 🎯 Mini-projet : jeu « Devine le nombre » en console

### ✅ M02. Méthodes, `String`, tableaux — `module-02-methodes-strings-tableaux`
Méthodes `public`/`private`, surcharge, passage par valeur de la référence, `char` et `Character`, méthodes de `String`, immuabilité, `split` et regex simples, `StringBuilder`, `Locale`, tableaux 1D et 2D, `Arrays`.
- Ex 1 `StringTools` : inverser, palindrome, voyelles, majuscules
- Ex 2 `ArrayStats` : somme, min, max, moyenne, médiane sans modifier l'entrée
- Ex 3 `CaesarCipher` : arithmétique sur les `char`, modulo négatif, surcharge
- Ex 4 `WordCounter` : `split`, chaînes vides, mot le plus fréquent
- Ex 5 `MatrixOps` : identité, transposée, addition, produit, symétrie
- 🎯 Mini-projet : carnet de notes en console (`GradeBook`)

## Niveau 2 — Programmation orientée objet

### M03. Classes, objets, encapsulation, constructeurs
Attributs et méthodes d'instance, `this`, constructeurs, `private` et getters, `static` vs instance, `toString`, références et `null`, objets immuables.
- `BankAccount`, `Rectangle`, `Temperature` immuable, `Library`
- 🎯 Mini-projet : carnet de contacts

### M04. Héritage, polymorphisme, interfaces, `record`, `enum`
`extends`, `super`, redéfinition et `@Override`, classes abstraites, interfaces et méthodes `default`, composition vs héritage, `record`, `enum` avec attributs et méthodes.
- Hiérarchie `Shape`, paie d'`Employee`, `enum Planet`, `record Point`
- 🎯 Mini-projet : gestion d'un parc de véhicules

## Niveau 3 — Intermédiaire

### M05. Exceptions, collections, génériques, `equals`/`hashCode`
Exceptions vérifiées ou non, `try`/`catch`/`finally`, exceptions métier, `List`/`Set`/`Map` et leurs implémentations, itération, classes et méthodes génériques, contrat `equals`/`hashCode`, `Comparable` et `Comparator`.
- `Stack<T>` générique, comptage de fréquences avec `Map`, dédoublonnage avec `Set`, tri personnalisé
- 🎯 Mini-projet : gestion de stock

### M06. Fichiers et dates
`java.nio.file` (`Path`, `Files`), `try-with-resources`, lecture et écriture de CSV, `java.time` (`LocalDate`, `LocalDateTime`, `Duration`, `Period`, formatage).
- Lecteur/écrivain CSV, calculs d'âge et de durées, planning
- 🎯 Mini-projet : journal de dépenses persisté en CSV

## Niveau 4 — Java moderne

### M07. Lambdas, interfaces fonctionnelles, Streams, `Optional`
`Function`, `Predicate`, `Supplier`, `Consumer`, références de méthodes, `map`/`filter`/`reduce`, `collect`, `groupingBy`, `Optional`.
- Réécrire des boucles en streams, statistiques groupées, recherches avec `Optional`
- 🎯 Mini-projet : analyse d'un jeu de données de films

### M08. `var`, `switch` expressions, pattern matching, sealed classes, text blocks
- 🎯 Mini-projet : évaluateur d'expressions arithmétiques (`sealed interface Expr` + `record`)

## Niveau 5 — Outillage

### M09. Maven en profondeur, JUnit 5, AssertJ, Mockito, SLF4J, débogueur
Cycle de vie Maven, scopes de dépendances, plugins (Checkstyle, JaCoCo). **C'est toi qui écris les tests.**
- 🎯 Mini-projet : service de réservation avec un repository mocké

## Niveau 6 — Avancé

### M10. Concurrence
`Thread`, `ExecutorService`, `Future`, `CompletableFuture`, virtual threads, conditions de course, `synchronized`, `Atomic*`, collections concurrentes.
- 🎯 Mini-projet : traitement parallèle de tâches et mesure des performances

### M11. JDBC et la JVM
JDBC avec la base H2, `PreparedStatement`, transactions ; heap et stack, garbage collector, outils (`jcmd`, VisualVM).
- 🎯 Mini-projet : DAO de bibliothèque

## Niveau 7 — Spring Boot

### M12. Spring Boot : injection de dépendances, API REST, validation
Spring Initializr, `@RestController`, DTO, Bean Validation, `application.yml`, profils.
- 🎯 Mini-projet : API de tâches en mémoire

### M13. Spring Data JPA, gestion des erreurs, tests, sécurité
Entités, relations, repositories, Flyway, `@ControllerAdvice`, `@WebMvcTest` et `@SpringBootTest`, Testcontainers, Spring Security de base.
- 🎯 Mini-projet : API de tâches persistée en base

## Niveau 8 — Projet final

### Gestion de bibliothèque
Livres, auteurs, membres, emprunts (règles métier : pas plus de 3 emprunts, pénalités de retard). API REST documentée (OpenAPI), PostgreSQL, Flyway, tests unitaires et d'intégration, README, `docker compose up` pour tout lancer.
