import java.util.ArrayList;
import java.util.List;

/**
 * Zerlegt einen Ausdruck wie "12 + mittel(3, 4) * (x - 1)" in Tokens.
 * Delegiert die eigentliche Zeichenklassifikation an ZahlenLeser,
 * BezeichnerLeser und OperatorTabelle.
 */
public final class Lexer {

    private Lexer() {
    }

    public static List<Token> tokenisiere(String eingabe) {
        Quelltext quelle = new Quelltext(eingabe);
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        while (quelle.restLaenge(i) > 0) {
            char c = quelle.zeichenAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (ZahlenLeser.istZahlStart(c)) {
                int start = i;
                i = ZahlenLeser.liesZahl(quelle, start);
                tokens.add(new Token(TokenArt.ZAHL, quelle.ausschnitt(start, i), start));
                continue;
            }

            if (BezeichnerLeser.istBezeichnerStart(c)) {
                int start = i;
                i = BezeichnerLeser.liesBezeichner(quelle, start);
                tokens.add(new Token(TokenArt.BEZEICHNER, quelle.ausschnitt(start, i), start));
                continue;
            }

            if (OperatorTabelle.istOperatorZeichen(c)) {
                tokens.add(new Token(OperatorTabelle.artFuerZeichen(c), String.valueOf(c), i));
                i++;
                continue;
            }

            throw new IllegalArgumentException("Unerwartetes Zeichen '" + c + "' an Position " + i);
        }
        return tokens.subList(0, tokens.size() - 1);
    }
}
