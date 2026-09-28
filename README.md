# ADLC Praxis: Taschenrechner-Debugging

Übungsmaterial für den Praxis-Teil des **ADLC-Basis-Workshops**. Konzept, Ablauf und
Teilnehmer-Anleitung stehen im Haupt-Repo `ADLC-Basis-Workshop`:

- `praxis-konzept.md` – didaktisches Konzept, Bug-Design-Prinzipien, Zeitplan
- `praxis-anleitung.md` – Phasen-Anleitung für Teilnehmer:innen
- `setup-praxis.md` – Setup-Checkliste vor dem Workshop

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
git clone <diese-repo-url>
cd ADLC-Basis-Workshop-Praxis
git checkout runde-1   # Phase 1
# ... später, für Phase 3:
git checkout runde-2
```

Jeder Branch bringt sein eigenes `README.md` mit Kompilier- und Testbefehlen mit.
