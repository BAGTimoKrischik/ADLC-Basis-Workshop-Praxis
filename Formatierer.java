import java.util.Locale;

/** Formatiert Zahlen mit fester Nachkommastellenzahl und Tausendertrennzeichen ('). */
public final class Formatierer {
    private static final char TRENNER = '\'';

    private Formatierer() {
    }

    public static String formatiere(double wert, int nachkommastellen) {
        boolean negativ = wert < 0;
        double betrag = Math.abs(wert);
        double faktor = Math.pow(10, nachkommastellen);
        double gerundet = Math.round(betrag * faktor) / faktor;

        // Locale.ROOT: sonst liefert '.'-als-Trenner auf Systemen mit deutscher
        // Standard-Locale ein Komma und der spaetere split("\\.") schlaegt fehl.
        String[] teile = String.format(Locale.ROOT, "%." + nachkommastellen + "f", gerundet).split("\\.", -1);
        String ganzzahlteil = gruppiere(teile[0]);

        StringBuilder ergebnis = new StringBuilder();
        if (negativ && gerundet != 0.0) {
            ergebnis.append('-');
        }
        ergebnis.append(ganzzahlteil);
        if (teile.length > 1 && !teile[1].isEmpty()) {
            ergebnis.append('.').append(teile[1]);
        }
        return ergebnis.toString();
    }

    /** Gruppiert Ziffern von rechts in Dreiergruppen, getrennt durch TRENNER. */
    private static String gruppiere(String ziffern) {
        int laenge = ziffern.length();
        StringBuilder ergebnis = new StringBuilder();
        for (int i = 0; i < laenge; i++) {
            if (i > 0 && (laenge - i) % 3 == 0) {
                ergebnis.append(TRENNER);
            }
            ergebnis.append(ziffern.charAt(i));
        }
        return ergebnis.toString();
    }
}
