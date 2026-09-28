/** Liest Variablen- und Funktionsnamen: Buchstabe gefolgt von Buchstaben/Ziffern/'_'. */
public final class BezeichnerLeser {

    private BezeichnerLeser() {
    }

    public static boolean istBezeichnerStart(char c) {
        return Character.isLetter(c) || c == '_';
    }

    private static boolean istBezeichnerZeichen(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    /** Gibt die Endposition (exklusiv) des Bezeichners zurueck, der bei start beginnt. */
    public static int liesBezeichner(Quelltext quelle, int start) {
        int ende = start;
        while (ende < quelle.laenge() && istBezeichnerZeichen(quelle.zeichenAt(ende))) {
            ende++;
        }
        return ende;
    }
}
