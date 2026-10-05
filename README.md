# learnJava

Un parcours progressif pour apprendre Java par la pratique : des bases du langage jusqu'à une API REST Spring Boot complète.

## Démarrer

1. Installe ton poste : [docs/00-installation.md](docs/00-installation.md) (JDK 25 via SDKMAN!, Maven, IntelliJ IDEA).
2. Découvre le langage : [docs/01-le-langage-java.md](docs/01-le-langage-java.md).
3. Lis les bonnes pratiques : [docs/02-bonnes-pratiques.md](docs/02-bonnes-pratiques.md).
4. Consulte le plan du parcours : [docs/03-parcours.md](docs/03-parcours.md).
5. Commence par le module 1 : [module-01-bases/COURS.md](module-01-bases/COURS.md).

## Comment travailler un module

1. Lis `COURS.md`, puis `EXERCICES.md`.
2. Ouvre le code de départ dans `src/main/java` et remplace chaque `throw new UnsupportedOperationException("TODO");`.
3. Lance les tests de l'exercice jusqu'à ce qu'ils soient verts :
   ```bash
   ./mvnw -q -pl module-01-bases test -Dtest=FizzBuzzTest
   ```
4. Quand tout le module est vert, coche-le dans [PROGRESSION.md](PROGRESSION.md) et demande une revue de code.
5. Seulement ensuite, compare avec `solutions/`.

## Commandes

```bash
./mvnw -q -pl module-01-bases test                         # tous les tests d'un module
./mvnw -q -pl module-01-bases test -Dtest=FizzBuzzTest     # un seul exercice
./mvnw -q -pl module-01-bases compile exec:java            # lancer le mini-projet
./mvnw -q -Psolutions -pl solutions/module-01-bases test   # vérifier les solutions
```

> `./mvnw test` à la racine échoue tant que tous les exercices ne sont pas faits : c'est normal, cible un module avec `-pl`.
