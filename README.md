# Taschenrechner – Runde 2

Startgerüst für **Phase 3** des ADLC-Praxis-Teils: dieselbe Art von Bugs wie in
Runde 1, aber mit selbst destilliertem Skill und ≥2 spezialisierten Agenten
(Reviewer, Coder) fixen. Details zum Ablauf: `praxis-anleitung.md` im Haupt-Repo
`ADLC-Basis-Workshop`.

Gleicher Aufbau wie Runde 1 (Lexer → Parser → Auswerter, Variablen, eingebaute
Funktionen, Ergebnis-Verlauf), 4 neue Bugs an anderen Stellen — vergleichbar
schwer, damit der Runde-1/Runde-2-Vergleich in Phase 4 ehrlich ist.

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

**Startzustand: 8 von 38 Tests grün** — das ist der erwartete Übungsstand, kein
kaputtes Setup.

## Aufgabe (Phase 3, 15 Minuten)

Nutzt den in Phase 2 destillierten Skill und mindestens zwei darauf aufbauende
Agenten (Reviewer, Coder), kein Ad-hoc-Vibecoding. Bringt so viele JUnit-Tests
wie möglich zum Bestehen. Ergänzt den Skill währenddessen nicht nachträglich um
Runde-2-spezifische Bugs.
