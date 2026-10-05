# 01 — Le langage Java

## Ce qu'est Java

Java est un langage **compilé, orienté objet, à typage statique et fort**, créé par Sun Microsystems en 1995 et aujourd'hui piloté par Oracle et la communauté OpenJDK.

### Du code source à l'exécution

```
HelloWorld.java  --javac-->  HelloWorld.class (bytecode)  --java-->  JVM  -->  code machine
```

1. `javac` compile le code source en **bytecode** : un format intermédiaire, indépendant du processeur et du système.
2. La **JVM** (Java Virtual Machine) exécute ce bytecode. Elle commence par l'interpréter, puis son compilateur **JIT** (*Just-In-Time*) compile en code machine natif les parties les plus utilisées, en optimisant à partir de ce qu'elle observe à l'exécution.

D'où la devise **« Write once, run anywhere »** : le même `.class` (ou `.jar`) tourne sur Linux, Windows ou macOS, du moment qu'une JVM est installée.

Conséquences pratiques :
- Le démarrage est plus lent qu'un script PHP, mais une fois « chaude » une application Java est très rapide.
- Une application Java est un **processus qui tourne en continu** (un serveur Spring Boot démarre une fois et traite des milliers de requêtes), alors que PHP-FPM repart de zéro à chaque requête. L'état en mémoire (caches, singletons) persiste donc entre les requêtes, ce qui demande d'être attentif à la concurrence.

### Typage statique et fort

Chaque variable a un type connu **à la compilation**, et le compilateur refuse le code incohérent avant même l'exécution :

```java
int age = 30;
age = "trente";        // erreur de compilation : incompatible types
String s = "42" + 1;   // "421" : concaténation, pas d'addition implicite
int n = Integer.parseInt("42") + 1;   // 43 : conversion explicite
```

Beaucoup d'erreurs que PHP ne révèle qu'à l'exécution (et parfois en production) sont détectées par le compilateur et l'IDE.

### Gestion automatique de la mémoire

On crée des objets avec `new`, on ne les libère jamais soi-même : le **garbage collector** (ramasse-miettes) récupère la mémoire des objets qui ne sont plus référencés. Contrairement à C/C++, pas de `free`, pas de fuite par oubli (mais des fuites restent possibles si on garde des références inutiles, par exemple dans un cache statique).

### Versions et LTS

- Une version tous les **6 mois** (mars et septembre).
- Une version **LTS** tous les 2 ans, supportée plusieurs années : 8, 11, 17, 21, **25**.
- Le langage est **très rétrocompatible** : du code écrit pour Java 8 compile toujours en Java 25. Tu rencontreras donc du code « ancien style » (boucles, classes anonymes) à côté du style moderne (lambdas, streams, records, pattern matching). Ce parcours enseigne les deux.

## Ce qu'on peut faire avec Java

| Domaine | Exemples d'outils |
|---|---|
| **Backend, API REST, microservices** | Spring Boot, Quarkus, Micronaut |
| **Applications d'entreprise** | Banques, assurances, ERP, e-commerce (souvent Spring / Jakarta EE) |
| **Android** | Java historiquement, Kotlin aujourd'hui (Kotlin tourne sur la même JVM et réutilise les bibliothèques Java) |
| **Big data, streaming** | Apache Kafka, Spark, Flink, Hadoop, Elasticsearch sont écrits en Java ou sur la JVM |
| **Outils en ligne de commande** | picocli, compilation native avec GraalVM pour un démarrage instantané |
| **Applications desktop** | JavaFX, Swing ; IntelliJ IDEA lui-même est écrit en Java |
| **Jeux** | Minecraft (édition Java), libGDX |

## Java vu par un développeur PHP / Laravel

| Sujet | PHP / Laravel | Java / Spring |
|---|---|---|
| Exécution | Interprété, une requête = un processus | Compilé en bytecode, application qui tourne en continu |
| Typage | Dynamique (types optionnels, `declare(strict_types=1)`) | Statique et obligatoire (`var` infère le type mais il reste fixe) |
| Variables | `$name` | `String name` (pas de `$`) |
| Tableaux | `array` : liste et dictionnaire à la fois | Séparés : tableau `int[]` de taille fixe, `List`, `Map`, `Set` |
| Chaînes | `'...'` ou `"..."`, concaténation `.` | `"..."` uniquement (`'a'` est un `char`), concaténation `+` |
| Comparaison | `==` / `===` | `==` compare les primitifs ou les **références** ; `equals()` compare le **contenu** des objets |
| Absence de valeur | `null` partout | `null` pour les objets uniquement, les primitifs (`int`, `boolean`) ne peuvent pas être `null` |
| Organisation | Namespaces + PSR-4 | Packages, qui doivent correspondre aux dossiers |
| Un fichier | Peut contenir plusieurs classes | Une classe publique par fichier, du même nom |
| Dépendances | Composer, `composer.json` | Maven, `pom.xml` |
| Injection de dépendances | Service Container | Spring IoC Container (`@Component`, `@Autowired`) |
| ORM | Eloquent (Active Record) | JPA / Hibernate (Data Mapper : entités + repositories) |
| Migrations | `php artisan migrate` | Flyway ou Liquibase |
| Tests | PHPUnit, Pest | JUnit, AssertJ, Mockito |
| Templates | Blade | Thymeleaf (mais Spring sert surtout d'API REST pour un front React) |
| Concurrence | Peu utilisée (une requête = un processus) | Centrale : threads, virtual threads |

Pièges classiques quand on vient de PHP :
- `"abc" == "abc"` peut être `true` ou `false` en Java selon la façon dont les chaînes ont été créées. **Toujours `equals()` pour comparer des objets.**
- `5 / 2` vaut `2` (division entière entre deux `int`), pas `2.5`.
- Les tableaux ont une taille fixe : `int[] t = new int[3]` ne peut pas grandir. Pour une liste dynamique, on utilise `ArrayList`.
- Les entiers ont une taille limitée et **débordent** silencieusement : `Integer.MAX_VALUE + 1` donne `-2147483648` (en PHP, le résultat bascule en `float`).
