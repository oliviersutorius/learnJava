# 00 — Installer son poste de développement Java (Ubuntu)

## Vue d'ensemble

| Outil | Rôle | Équivalent PHP |
|---|---|---|
| **JDK** (Java Development Kit) | Compilateur `javac` + machine virtuelle `java` + bibliothèque standard | L'interpréteur `php` |
| **SDKMAN!** | Installer et basculer entre plusieurs versions du JDK et des outils | `phpbrew`, `nvm` côté Node |
| **Maven** | Build, dépendances, tests, packaging | Composer + scripts |
| **IntelliJ IDEA** | IDE | PhpStorm (même éditeur, JetBrains) |
| **Git** | Versionnement | Git |

## 1. SDKMAN! et le JDK

Pourquoi SDKMAN! plutôt que `apt install openjdk-xx-jdk` ? Les dépôts Ubuntu ont souvent une version de retard, et SDKMAN! permet d'installer plusieurs JDK côte à côte et de changer de version par projet.

```bash
# Prérequis
sudo apt install -y curl zip unzip

# Installer SDKMAN! (ajoute automatiquement son initialisation à ~/.bashrc et ~/.zshrc)
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Lister les JDK disponibles, puis installer Java 25 (LTS) distribué par Eclipse Temurin
sdk list java
sdk install java 25.0.4-tem
sdk default java 25.0.4-tem
```

**Quelle distribution ?** Le code de Java est open source (OpenJDK). Plusieurs fournisseurs le compilent et le distribuent : Eclipse Temurin, Amazon Corretto, Azul Zulu, Oracle… Elles sont équivalentes pour apprendre. Temurin est le choix neutre et gratuit le plus courant.

**Quelle version ?** Toujours la dernière **LTS** (*Long-Term Support*) pour un projet : Java 25 (sept. 2025). Une nouvelle version sort tous les 6 mois, une LTS tous les 2 ans (21 → 25 → 29).

> Ton poste avait déjà OpenJDK 21 installé via `apt` (`/usr/bin/java`). Il reste en place, mais SDKMAN! passe en priorité dans le `PATH`. Commandes utiles :
> `sdk current` (versions actives), `sdk use java 21.0.12-tem` (changer pour le terminal courant), `sdk env init` (fichier `.sdkmanrc` par projet).

## 2. Maven

```bash
sdk install maven
mvn -version     # doit afficher « Java version: 25... »
```

### Maven ou Gradle ?

| | Maven | Gradle |
|---|---|---|
| Configuration | `pom.xml`, déclaratif (XML) | `build.gradle.kts`, script (Kotlin/Groovy) |
| Courbe d'apprentissage | Simple, très conventionnel | Plus souple, plus complexe |
| Performance | Correcte | Plus rapide (cache, build incrémental) |
| Usage | Majoritaire en entreprise Java backend | Standard Android, fréquent dans les gros projets |

**Choix pour ce projet : Maven.** Il est plus lisible pour débuter, majoritaire dans l'écosystème Spring, et son modèle déclaratif ressemble à `composer.json`. Gradle reste à connaître ; tu le croiseras forcément.

Le projet contient un **wrapper** (`./mvnw`) : il télécharge la bonne version de Maven automatiquement. Utilise toujours `./mvnw` plutôt que `mvn` dans le projet.

## 3. IDE

| | IntelliJ IDEA | VS Code + Extension Pack for Java |
|---|---|---|
| Analyse du code, refactoring | Excellent, la référence | Bon |
| Débogueur, JUnit, Maven | Intégrés nativement | Via extensions |
| Spring Boot | Très bon (support avancé avec abonnement) | Correct (extension Spring Boot Tools) |
| Poids | Lourd (~2 Go RAM) | Léger |

**Recommandation : IntelliJ IDEA.** Tu connais probablement déjà PhpStorm : même éditeur, mêmes raccourcis. Depuis 2025.3, JetBrains distribue une seule édition d'IntelliJ IDEA, dont les fonctionnalités Java, Maven, Git et débogage sont gratuites.

```bash
sudo snap install intellij-idea --classic
```

Puis : *File → Open* → choisir le dossier `learnJava` (IntelliJ détecte le `pom.xml`). Dans *File → Project Structure → SDK*, sélectionne le JDK 25 de `~/.sdkman/candidates/java/current`.

Raccourcis à connaître dès le début : `Alt+Entrée` (corrections rapides), `Ctrl+Shift+F10` (lancer le test / la classe sous le curseur), `Ctrl+Alt+L` (formater), `Shift+F6` (renommer), `Ctrl+B` (aller à la définition), `Shift Shift` (chercher partout).

Alternative VS Code : installe l'extension *Extension Pack for Java* (Microsoft).

## 4. Git

```bash
sudo apt install -y git
git config --global user.name "Ton Nom"
git config --global user.email "ton@email"
```

## 5. Vérifier que tout fonctionne

```bash
java -version       # openjdk version "25.0.4" ... Temurin
javac -version      # javac 25.0.4
./mvnw -version     # Apache Maven ... Java version: 25.0.4
```

### Hello World « à la main »

Crée `HelloWorld.java` dans un dossier temporaire :

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

```bash
javac HelloWorld.java   # compile : produit HelloWorld.class (du bytecode)
java HelloWorld         # exécute la classe sur la JVM (sans l'extension .class)
```

Ce qu'il faut retenir :
- Le fichier doit porter **le même nom que la classe publique** (`HelloWorld.java` ↔ `public class HelloWorld`).
- `main` est le point d'entrée du programme.
- Il y a **deux étapes** : compilation puis exécution (contrairement à PHP).

Raccourcis modernes :
- `java HelloWorld.java` compile en mémoire et exécute directement (pratique pour un script).
- Depuis Java 25, un fichier peut même s'écrire sans classe, pour débuter ou écrire des scripts :
  ```java
  void main() {
      String name = IO.readln("Ton prénom ? ");
      IO.println("Bonjour " + name + " !");
  }
  ```
  Dans ce parcours, on utilise la forme classique, celle de tout le code professionnel existant.

### Hello World avec Maven (dans ce projet)

```bash
./mvnw -q -Psolutions -pl solutions/module-01-bases compile exec:java
```

lance la solution du mini-projet du module 1 (jeu « Devine le nombre »). Si le jeu démarre, ton poste est prêt.

## 6. L'écosystème des frameworks (pour situer)

- **Spring Boot** : le framework Java le plus utilisé, de loin. C'est l'équivalent de Laravel : injection de dépendances, web/REST, accès aux données, sécurité, tout est intégré. C'est l'objectif des niveaux 7 et 8.
- **Quarkus** : framework moderne (Red Hat), démarrage très rapide et faible mémoire, pensé pour Kubernetes et la compilation native.
- **Jakarta EE** : les spécifications standard de Java « entreprise » (ex-Java EE), implémentées par des serveurs d'applications (WildFly, Payara…). Spring et Quarkus en réutilisent une partie (JPA, Bean Validation…).
- **Micronaut** : proche de Quarkus, injection de dépendances résolue à la compilation, adapté aux microservices et au serverless.
