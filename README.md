# ADLC Praxis: Taschenrechner-Debugging

Übungsmaterial für den Praxis-Teil des **ADLC-Basis-Workshops**.

- `setup-praxis.md` – Setup-Checkliste vor dem Workshop
- `praxis-anleitung.md` – Phasen-Anleitung für Teilnehmer:innen

Das didaktische Konzept (`praxis-konzept.md`) bleibt im Haupt-Repo
`ADLC-Basis-Workshop`, da es Vortragenden-Hintergrund (Bug-Design-Prinzipien,
Timing-Begründung) enthält, der für Teilnehmer:innen nicht nötig ist.

## Branches statt Ordner

Dieses Repo hat keinen Code auf `main` — jede Übungsrunde ist ein eigener Branch mit
den Taschenrechner-Quelldateien direkt im Repo-Root:

- **`runde-1`** – Startgerüst für Phase 1 (Baseline-Vibecoding, ohne Skill/Agenten). Start: 5 von 38 Tests grün.
- **`runde-2`** – Startgerüst für Phase 3 (mit Skill + Agenten). Start: 8 von 38 Tests grün.

Referenzlösung (`loesung/`) und Bug-Antwortschlüssel (`antwortschluessel.md`) bleiben bewusst
im Haupt-Repo `ADLC-Basis-Workshop`, da sie nur für die Vorbereitung durch Vortragende gedacht
sind und die Bugs für Teilnehmer:innen nicht vorab sichtbar sein dürfen.

## Nutzung

```
git clone https://github.com/BAGTimoKrischik/ADLC-Basis-Workshop-Praxis.git
cd ADLC-Basis-Workshop-Praxis
git checkout runde-1   # Phase 1
# ... später, für Phase 3:
git checkout runde-2
```

Jeder Branch bringt sein eigenes `README.md` mit Kompilier- und Testbefehlen mit.
