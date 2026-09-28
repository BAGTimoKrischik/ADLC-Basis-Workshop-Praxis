/** Fester Ringpuffer: nach Erreichen der Kapazitaet ueberschreibt jeder neue Wert den aeltesten. */
public final class Ringpuffer {
    private final double[] speicher;
    private final int kapazitaet;
    private int schreibIndex = 0;
    private int anzahlGeschrieben = 0;

    public Ringpuffer(int kapazitaet) {
        this.kapazitaet = kapazitaet;
        this.speicher = new double[kapazitaet];
    }

    public void schreiben(double wert) {
        speicher[schreibIndex] = wert;
        schreibIndex = (schreibIndex + 1) % kapazitaet;
        anzahlGeschrieben++;
    }

    /** Wie viele Slots aktuell gueltige (noch nicht verworfene) Werte enthalten. */
    public int belegteAnzahl() {
        return Math.min(anzahlGeschrieben, kapazitaet - 1);
    }

    /** Aktuell belegte Werte in urspruenglicher Schreibreihenfolge (aelteste zuerst). */
    public double[] alleBelegten() {
        int anzahl = belegteAnzahl();
        double[] ergebnis = new double[anzahl];
        int startIndex = (anzahlGeschrieben <= kapazitaet) ? 0 : schreibIndex;
        for (int i = 0; i < anzahl; i++) {
            ergebnis[i] = speicher[(startIndex + i) % kapazitaet];
        }
        return ergebnis;
    }
}
