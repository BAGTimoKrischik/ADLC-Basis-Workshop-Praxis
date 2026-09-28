/**
 * Liest ab einer Startposition eine vollstaendige Zahl: Ganzzahl- und
 * Nachkommateil (mit optionalem '_' als Tausendertrenner zwischen zwei
 * Ziffern, z.B. "12_000") sowie ein optionaler Exponent ("1.5e-3", "2E10").
 */
public final class ZahlenLeser {

    private ZahlenLeser() {
    }

    public static boolean istZahlStart(char c) {
        return Character.isDigit(c);
    }

    /** Gibt die Endposition (exklusiv) der Zahl zurueck, die bei start beginnt. */
    public static int liesZahl(Quelltext quelle, int start) {
        int i = liesZiffernfolge(quelle, start);
        if (quelle.hatZeichenAn(i) && quelle.zeichenAt(i) == '.') {
            int nachPunkt = liesZiffernfolge(quelle, i + 1);
            if (nachPunkt > i + 1) {
                i = nachPunkt;
            }
        }
        if (quelle.hatZeichenAn(i) && istExponentZeichen(quelle.zeichenAt(i))) {
            int nachE = enthaeltVorzeichen(quelle, i + 1) ? i + 2 : i + 1;
            int nachExponent = liesZiffernfolge(quelle, nachE);
            if (nachExponent > nachE) {
                i = nachExponent;
            }
        }
        return i;
    }

    private static boolean istExponentZeichen(char c) {
        return c == 'e' || c == 'E';
    }

    private static boolean enthaeltVorzeichen(Quelltext quelle, int position) {
        return quelle.hatZeichenAn(position)
                && (quelle.zeichenAt(position) == '+' || quelle.zeichenAt(position) == '-');
    }

    /** Liest eine Folge von Ziffern, erlaubt '_' zwischen zwei Ziffern als Trenner. */
    private static int liesZiffernfolge(Quelltext quelle, int start) {
        int i = start;
        while (quelle.hatZeichenAn(i) && Character.isDigit(quelle.zeichenAt(i))) {
            i++;
            boolean trennerZwischenZiffern = quelle.hatZeichenAn(i) && quelle.zeichenAt(i) == '_'
                    && quelle.hatZeichenAn(i + 1) && Character.isDigit(quelle.zeichenAt(i + 1));
            if (trennerZwischenZiffern) {
                i++;
            }
        }
        return i;
    }
}
