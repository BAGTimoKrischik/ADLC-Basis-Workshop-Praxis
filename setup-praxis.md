# Setup: Taschenrechner-Praxis

Was vor dem Workshop bereitstehen muss, damit die Praxis ohne Zeitverlust starten kann.

## Voraussetzungen

- **JDK 21 (LTS)** installiert, z. B. von [Oracle Java](https://www.oracle.com/java/technologies/downloads/). Prüfen mit:
  ```
  java -version
  ```
- Kein Build-Tool nötig (kein Maven/Gradle) — das Starter-Projekt läuft mit reinem `javac`/`java`. IntelliJ oder VS Code als Editor genügen.
- **[Git](https://git-scm.com/downloads) installiert** und das Haupt-Repo [`ADLC-Basis-Workshop`](https://brockhaus-ag@dev.azure.com/brockhaus-ag/KI-Team/_git/ADLC-Basis-Workshop) lokal ausgecheckt.
- **Dieses Repository ausgecheckt, richtiger Branch:** Die Übungsrunden liegen hier als Branches `runde-1` und `runde-2` (nicht auf `main`), getrennt vom Haupt-Repo `ADLC-Basis-Workshop`. Referenzlösung und Bug-Antwortschlüssel bleiben bewusst im Haupt-Repo (`praxis/loesung/`, `praxis/antwortschluessel.md`) und werden Teilnehmer:innen nicht zugänglich gemacht.
  ```
  git clone https://github.com/BAGTimoKrischik/ADLC-Basis-Workshop-Praxis.git
  cd ADLC-Basis-Workshop-Praxis
  git checkout runde-1
  ```
  `runde-2` (nach der Skill-Erstellung) ist ein weiterer Branch im selben Repo.
- **JUnit-Standalone-Jar einmalig herunterladen** (kein Maven/Gradle nötig, nur eine Jar-Datei) nach `lib/` **im Praxis-Repo** (Ordner ist gitignored, muss lokal angelegt werden):
  ```
  mkdir -p lib
  curl -sL -o lib/junit-platform-console-standalone-1.11.4.jar \
    https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
  ```
  Muss pro Branch (`runde-1`, `runde-2`) einmal angelegt werden, da es sich um unabhängige Arbeitsverzeichnisse handelt. Für `loesung/` (hier im Haupt-Repo) weiterhin nach `praxis/lib/`.
- **Ein Agent-Tool installiert und eingerichtet**, das Skills lesen und mehrere Agenten aus einem Skill heraus orchestrieren kann (z.B. Claude Code, Codex, oder ein anderes Tool mit vergleichbarer Unterstützung). Welches Tool genutzt wird, ist für den Workshop egal.
  - Falls das eigene Tool keine Multi-Agent-Orchestrierung unterstützt oder Unsicherheit besteht: Fallback ist **Claude von der BAG**. Das KI-Team hilft bei Integrationsfragen.
- **KI-Basiswissen** (Prompting, grundsätzliche Funktionsweise von LLMs) — wird laut Theorie-Teil vorausgesetzt.

## Am Tag des Workshops

1. Terminal öffnen, in den Checkout des Praxis-Repos wechseln, Branch `runde-1`.
2. Prüfen, dass das Starter-Projekt kompiliert und die Unittests laufen (sollten größtenteils fehlschlagen — das ist der Übungsstand: 5 von 38 Tests bestanden):
   ```
   javac -cp lib/junit-platform-console-standalone-1.11.4.jar *.java
   java -jar lib/junit-platform-console-standalone-1.11.4.jar execute -cp . --scan-classpath
   ```
   Unter Windows `-cp` mit `;` statt `:` trennen, falls mehrere Pfade nötig sind (hier reicht ein einzelner Pfad, also ohne Trenner).
3. Agent-Tool bereit haben (eingeloggt, im Projektordner nutzbar).

Bei Problemen mit einem der Schritte: vor Beginn der Praxis melden, nicht erst während der 60-Minuten-Übung.
