import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Alle Tests gehen ueber die oeffentliche API (berechne/berechneMit,
 * merkeErgebnis/durchschnittVerlauf/medianVerlauf, formatiere) und sind nach
 * Eingabeszenario benannt, nicht nach Datei/Subsystem -- ein Fehlschlag soll
 * nicht schon verraten, wo im Code zu suchen ist.
 */
class TaschenrechnerTest {

    private static final double DELTA = 1e-9;

    // --- Grundrechenarten & Prioritaet ---------------------------------

    @Test
    void addition() {
        assertEquals(5.0, Taschenrechner.berechne("2 + 3"), DELTA);
    }

    @Test
    void subtraktion() {
        assertEquals(30.0, Taschenrechner.berechne("50 - 20"), DELTA);
    }

    @Test
    void multiplikation() {
        assertEquals(144.0, Taschenrechner.berechne("12 * 12"), DELTA);
    }

    @Test
    void division() {
        assertEquals(2.0, Taschenrechner.berechne("20 / 10"), DELTA);
    }

    @Test
    void modulo() {
        assertEquals(2.0, Taschenrechner.berechne("17 % 15"), DELTA);
    }

    @Test
    void punktVorStrich() {
        assertEquals(122.0, Taschenrechner.berechne("2 + 3 * 40"), DELTA);
    }

    @Test
    void klammernHabenVorrang() {
        assertEquals(20.0, Taschenrechner.berechne("(2 + 3) * 4"), DELTA);
    }

    @Test
    void mehrfacheSubtraktionVonLinksNachRechts() {
        assertEquals(50.0, Taschenrechner.berechne("100 - 20 - 30"), DELTA);
    }

    @Test
    void potenzIstRechtsAssoziativ() {
        assertEquals(512.0, Taschenrechner.berechne("2 ^ 3 ^ 2"), DELTA);
    }

    @Test
    void potenzBindetStaerkerAlsMal() {
        assertEquals(18.0, Taschenrechner.berechne("2 * 3 ^ 2"), DELTA);
    }

    @Test
    void unaeresMinusVorKlammer() {
        assertEquals(-7.0, Taschenrechner.berechne("-(3 + 4)"), DELTA);
    }

    @Test
    void unaeresMinusBindetVorPotenz() {
        assertEquals(4.0, Taschenrechner.berechne("-2 ^ 2"), DELTA);
    }

    @Test
    void langeAdditionskette() {
        assertEquals(51.0, Taschenrechner.berechne("1 + 2 + 3 + 45"), DELTA);
    }

    @Test
    void tiefVerschachtelteKlammern() {
        assertEquals(9.0, Taschenrechner.berechne("(((1 + 2)) * ((3)))"), DELTA);
    }

    // --- Zahlenformate ---------------------------------------------------

    @Test
    void dezimalzahlen() {
        assertEquals(3.75, Taschenrechner.berechne("2.5 + 1.25"), DELTA);
    }

    @Test
    void tausendertrennzeichenInZahl() {
        assertEquals(12001.0, Taschenrechner.berechne("1 + 12_000"), DELTA);
    }

    @Test
    void exponentSchreibweisePositiv() {
        assertEquals(151.0, Taschenrechner.berechne("1 + 1.5e2"), DELTA);
    }

    @Test
    void exponentSchreibweiseNegativ() {
        assertEquals(2.0, Taschenrechner.berechne("2e-2 * 100"), DELTA);
    }

    // --- Variablen ---------------------------------------------------

    @Test
    void variableWirdAusUmgebungGelesen() {
        assertEquals(14.0, Taschenrechner.berechneMit("x + 10", Map.of("x", 4.0)), DELTA);
    }

    @Test
    void konstantePiIstVerfuegbar() {
        assertEquals(Math.PI, Taschenrechner.berechne("pi"), DELTA);
    }

    @Test
    void konstanteEIstVerfuegbar() {
        assertEquals(Math.E, Taschenrechner.berechne("e"), DELTA);
    }

    // --- Funktionen ---------------------------------------------------

    @Test
    void minVonZweiWerten() {
        assertEquals(3.0, Taschenrechner.berechne("min(3, 7)"), DELTA);
    }

    @Test
    void maxVonZweiWerten() {
        assertEquals(7.0, Taschenrechner.berechne("max(3, 7)"), DELTA);
    }

    @Test
    void mittelwertMehrererArgumente() {
        assertEquals(5.0, Taschenrechner.berechne("mittel(2, 4, 6, 8)"), DELTA);
    }

    @Test
    void wurzelEinesWertes() {
        assertEquals(3.0, Taschenrechner.berechne("wurzel(9)"), DELTA);
    }

    @Test
    void clampBegrenztNachOben() {
        assertEquals(10.0, Taschenrechner.berechne("clamp(15, 0, 10)"), DELTA);
    }

    @Test
    void clampBegrenztNachUnten() {
        assertEquals(0.0, Taschenrechner.berechne("clamp(-5, 0, 10)"), DELTA);
    }

    @Test
    void rundenAufZweiStellen() {
        assertEquals(3.14, Taschenrechner.berechne("runde(3.14159, 2)"), DELTA);
    }

    @Test
    void rundenAufMaximaleStellenzahl() {
        assertEquals(3.141593, Taschenrechner.berechne("runde(3.14159265, 6)"), DELTA);
    }

    @Test
    void verschachtelteFunktionsaufrufeFuenfEbenenTief() {
        String ausdruck = "wurzel(wurzel(wurzel(wurzel(wurzel(65536)))))";
        assertEquals(Math.sqrt(2), Taschenrechner.berechne(ausdruck), DELTA);
    }

    // --- Verlauf ---------------------------------------------------

    @Test
    void durchschnittNachDreiWerten() {
        Taschenrechner tr = new Taschenrechner();
        tr.merkeErgebnis(1.0);
        tr.merkeErgebnis(2.0);
        tr.merkeErgebnis(3.0);
        assertEquals(2.0, tr.durchschnittVerlauf(), DELTA);
    }

    @Test
    void durchschnittNachGenauFuenfWerten() {
        Taschenrechner tr = new Taschenrechner();
        for (double wert : new double[] {1.0, 2.0, 3.0, 4.0, 5.0}) {
            tr.merkeErgebnis(wert);
        }
        assertEquals(3.0, tr.durchschnittVerlauf(), DELTA);
    }

    @Test
    void durchschnittVerdraengtAeltesteNachSiebenWerten() {
        Taschenrechner tr = new Taschenrechner();
        for (double wert = 1.0; wert <= 7.0; wert++) {
            tr.merkeErgebnis(wert);
        }
        assertEquals(5.0, tr.durchschnittVerlauf(), DELTA);
    }

    @Test
    void medianVonDreiWerten() {
        Taschenrechner tr = new Taschenrechner();
        tr.merkeErgebnis(5.0);
        tr.merkeErgebnis(1.0);
        tr.merkeErgebnis(3.0);
        assertEquals(3.0, tr.medianVerlauf(), DELTA);
    }

    @Test
    void medianVonVierWerten() {
        Taschenrechner tr = new Taschenrechner();
        tr.merkeErgebnis(2.0);
        tr.merkeErgebnis(8.0);
        tr.merkeErgebnis(4.0);
        tr.merkeErgebnis(6.0);
        assertEquals(5.0, tr.medianVerlauf(), DELTA);
    }

    // --- Formatierung ---------------------------------------------------

    @Test
    void formatiertGrosseZahlMitTrennzeichen() {
        assertEquals("1'234'567.89", Taschenrechner.formatiere(1234567.891));
    }

    @Test
    void formatiertNegativeZahl() {
        assertEquals("-42.50", Taschenrechner.formatiere(-42.5));
    }

    @Test
    void formatiertUebertragInsNaechsteTausend() {
        assertEquals("1'000.00", Taschenrechner.formatiere(999.999));
    }
}
