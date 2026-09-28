# Taschenrechner – Runde 1

Startgerüst für **Phase 1** des ADLC-Praxis-Teils: Bugs per Ad-hoc-Vibecoding finden &
fixen, noch ohne Skill oder Agenten. Details zum Ablauf: `praxis-anleitung.md` im
Haupt-Repo `ADLC-Basis-Workshop`.

Eine Taschenrechner-Ausdrucks-Engine (Lexer → Parser → Auswerter, mit Variablen,
eingebauten Funktionen und Ergebnis-Verlauf) mit 4 eingebauten Bugs.

## Voraussetzungen

- **JDK 21 (LTS)** installiert. Prüfen mit:
  ```
  java -version
  ```
- Kein Build-Tool nötig (kein Maven/Gradle) — reines `javac`/`java`.
- **JUnit-Standalone-Jar** einmalig herunterladen (Ordner `lib/` ist gitignored):
  ```
  mkdir -p lib
  curl -sL -o lib/junit-platform-console-standalone-1.11.4.jar \
    https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
  ```

## Kompilieren & Testen

```
javac -cp lib/junit-platform-console-standalone-1.11.4.jar *.java
java -jar lib/junit-platform-console-standalone-1.11.4.jar execute -cp . --scan-classpath
```

Unter Windows `-cp` mit `;` statt `:` trennen, falls mehrere Pfade nötig sind (hier
reicht ein einzelner Pfad, also ohne Trenner).

**Startzustand: 5 von 38 Tests grün** — das ist der erwartete Übungsstand, kein
kaputtes Setup.

## Aufgabe (Phase 1, 15 Minuten)

Bringt per Vibecoding — ohne Skill, ohne Agenten — so viele JUnit-Tests wie möglich
zum Bestehen. Alle 38 zu schaffen ist nicht garantiert und kein Muss; das ist die
Baseline-Messung für den späteren Vergleich mit Runde 2.
