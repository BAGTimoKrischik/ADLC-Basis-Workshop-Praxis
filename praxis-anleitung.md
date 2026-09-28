# Praxis-Anleitung für Teilnehmer:innen

Setup vorab: `setup-praxis.md`.

## Vor der Praxis

- Setup abgeschlossen, Praxis-Repo (`ADLC-Basis-Workshop-Praxis`) ausgecheckt.
- Branch `runde-1` kompiliert, Start: 5 von 38 Tests grün.
- Ziel: möglichst viele Tests bestehen (grün), nicht zwingend alle 38.

## Phase 1 (15 Min): Bugs finden & fixen

- Arbeitet im Praxis-Repo auf Branch `runde-1`, per Vibecoding, ohne Skill oder Agenten.

## Phase 2 (20 Min): Skill + Agenten erstellen

- Destilliert euer Vorgehen aus Phase 1 zu einem Skill: eine generelle Checkliste, nicht die konkreten Bugs von eben.
- Legt mindestens zwei Agenten an, die den Skill nutzen: Reviewer und Coder.

## Phase 3 (15 Min): Runde 2 mit Skill + Agenten

- Wechselt im Praxis-Repo zu Branch `runde-2` (`git checkout runde-2`), Start: 8 von 38 Tests grün.
- Nutzt Skill und Agenten aus Phase 2, kein Ad-hoc-Vibecoding.
- Ergänzt den Skill nicht nachträglich um Runde-2-Bugs.

## Phase 4 (10 Min): Vergleich

- Vergleicht Testzahlen und Vorgehen aus Runde 1 und 2.
- Startet keinen neuen Agent-Auftrag.

## Bei Problemen

- Setup-Probleme sofort melden.
- Kein Multi-Agent-Tool: Fallback Claude von der BAG.
- Nach 9 Minuten ohne einen neuen grünen Test: meldet euch.
