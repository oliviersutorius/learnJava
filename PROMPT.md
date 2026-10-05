# Prompt : projet d'apprentissage de Java

## Contexte

Je veux apprendre à coder en Java, de zéro jusqu'à un niveau où je peux développer seul une vraie application (API REST avec Spring Boot). Je travaille sous Linux (Ubuntu). Le projet est dans `~/Sources/IA/learnJava`, qui est vide pour l'instant.

Mon profil : [« je développe déjà en PHP/Laravel, je connais Git, la ligne de commande et les bases de la POO »].

Tu es mon formateur. Ton rôle est de me faire progresser par la pratique, pas d'écrire le code à ma place.

## Ce que je te demande de produire

### 1. Guide de démarrage (`docs/00-installation.md`)
- Les outils à installer sous Linux, avec les commandes exactes :
  - le JDK (dernière version LTS, installée via SDKMAN!) ;
  - un IDE (IntelliJ IDEA Community, ou VS Code avec l'Extension Pack for Java) : compare-les et recommande-en un ;
  - un outil de build (Maven ou Gradle) : explique la différence et choisis-en un pour le projet ;
  - Git.
- Comment vérifier que tout fonctionne (`java -version`, compiler et lancer un premier « Hello World » à la main avec `javac`/`java`, puis avec l'outil de build).
- Le framework le plus utilisé dans l'écosystème (Spring Boot) et les autres à connaître (Quarkus, Jakarta EE, Micronaut) : une phrase chacun, sans entrer dans les détails au début.

### 2. Présentation du langage (`docs/01-le-langage-java.md`)
- Ce qu'est Java : compilation en bytecode, JVM, typage statique, garbage collector, « write once, run anywhere », le rythme des versions et les LTS.
- Ce que l'on peut faire avec : backend et API, applications d'entreprise, Android, big data (Spark, Kafka), outils en ligne de commande, applications desktop.
- Les différences importantes avec les langages que je connais déjà (voir mon profil).

### 3. Bonnes pratiques et documentation (`docs/02-bonnes-pratiques.md`)
- Les conventions de nommage et l'organisation des packages.
- Les principes à respecter : lisibilité, SOLID, immuabilité, gestion des exceptions, `null` et `Optional`, tests unitaires.
- Les outils qualité : formatage, linters (Checkstyle, SpotBugs, SonarLint).
- Les sources de référence fiables : documentation officielle Oracle et OpenJDK (dev.java, Javadoc de l'API), Spring Guides, Baeldung, les livres de référence (*Effective Java*, etc.).

### 4. Parcours d'apprentissage progressif (`docs/03-parcours.md` + dossiers d'exercices)
Organise l'apprentissage en modules, du plus simple au plus avancé. Chaque module doit contenir :
- un objectif clair et la liste des notions abordées ;
- un cours court avec des exemples de code commentés ;
- 3 à 5 exercices pratiques de difficulté croissante, chacun avec un énoncé, du code de départ à compléter et des **tests JUnit** qui valident ma solution ;
- un mini-projet de synthèse en fin de module ;
- les solutions dans un dossier séparé (`solutions/`), à ne consulter qu'après avoir essayé.

Progression souhaitée (à ajuster si tu le juges utile) :

| Niveau | Modules |
|---|---|
| **Bases** | Syntaxe, variables et types primitifs, opérateurs, conditions, boucles, méthodes, `String`, tableaux, lecture d'entrées clavier |
| **POO** | Classes et objets, encapsulation, constructeurs, héritage, polymorphisme, classes abstraites, interfaces, `record`, `enum` |
| **Intermédiaire** | Exceptions, collections (`List`, `Set`, `Map`), génériques, `equals`/`hashCode`, lecture et écriture de fichiers, dates (`java.time`) |
| **Java moderne** | Lambdas, interfaces fonctionnelles, Streams, `Optional`, `var`, `switch` expressions, pattern matching, sealed classes, text blocks |
| **Outillage** | Maven/Gradle en profondeur, dépendances, JUnit 5 et AssertJ, Mockito, débogueur de l'IDE, logs (SLF4J) |
| **Avancé** | Concurrence (threads, `ExecutorService`, `CompletableFuture`, virtual threads), JDBC, notions de la JVM et de la mémoire |
| **Spring Boot** | Injection de dépendances, API REST, validation, Spring Data JPA avec une base de données, gestion des erreurs, tests d'intégration, sécurité de base |
| **Projet final** | Une application complète (ex. gestion de bibliothèque ou de tâches) : API REST, base de données, tests, README, lancement avec Docker |

### 5. Structure du projet
- Un projet Maven (ou Gradle) multi-modules, ou un dossier par module, où chaque exercice se lance et se teste avec une seule commande.
- Un `README.md` qui explique comment suivre le parcours.
- Un `CLAUDE.md` qui décrit les commandes (build, lancer tous les tests, lancer le test d'un seul exercice) et les règles pédagogiques ci-dessous, pour que les prochaines sessions les respectent.
- Un fichier `PROGRESSION.md` où je coche les exercices terminés.
- Initialise un dépôt Git.

## Règles pédagogiques (à inscrire dans CLAUDE.md)
- Ne me donne jamais directement la solution d'un exercice : commence par des indices, de plus en plus précis si je bloque.
- Quand je soumets du code, relis-le comme un développeur senior : ce qui fonctionne, ce qui n'est pas idiomatique en Java, et comment l'améliorer.
- Explique le « pourquoi », pas seulement le « comment ».
- Écris tout en français, mais garde le code (noms de classes, variables, méthodes) en anglais, comme dans la pratique professionnelle.
- Ne passe au module suivant que lorsque les tests du module en cours sont verts.

## Méthode
Commence par me proposer le plan détaillé (modules, exercices, arborescence) et attends ma validation. Crée ensuite le guide d'installation et le premier module complet. Les modules suivants seront générés au fur et à mesure de ma progression.
