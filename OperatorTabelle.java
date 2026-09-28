/**
 * Kennt Operatorzeichen, ihre Prioritaet (fuer Punkt-vor-Strich) und ihre
 * Assoziativitaet. Ausgelagert, damit Lexer und Parser dieselbe Quelle der
 * Wahrheit fuer "was ist ein Operator und wie stark bindet er" nutzen.
 */
public final class OperatorTabelle {

    // Rang 1: + - ; Rang 2: * / % ; Rang 3: ^ (staerker bindend = hoehere Zahl)
    private static final int[] STUFEN = {1, 2, 3};

    private OperatorTabelle() {
    }

    public static TokenArt artFuerZeichen(char c) {
        return switch (c) {
            case '+' -> TokenArt.PLUS;
            case '-' -> TokenArt.MINUS;
            case '*' -> TokenArt.MAL;
            case '/' -> TokenArt.GETEILT;
            case '%' -> TokenArt.MODULO;
            case '^' -> TokenArt.HOCH;
            case '(' -> TokenArt.KLAMMER_AUF;
            case ')' -> TokenArt.KLAMMER_ZU;
            case ',' -> TokenArt.KOMMA;
            default -> null;
        };
    }

    public static boolean istOperatorZeichen(char c) {
        return artFuerZeichen(c) != null;
    }

    public static boolean istBinaerOperator(TokenArt art) {
        return rangVon(art) > 0;
    }

    public static boolean istRechtsAssoziativ(TokenArt art) {
        return art == TokenArt.HOCH;
    }

    /** Je hoeher der Rueckgabewert, desto staerker bindet der Operator. */
    public static int prioritaetVon(TokenArt art) {
        int rang = rangVon(art);
        return STUFEN[rang];
    }

    private static int rangVon(TokenArt art) {
        return switch (art) {
            case PLUS, MINUS -> 1;
            case MAL, GETEILT, MODULO -> 2;
            case HOCH -> 3;
            default -> 0;
        };
    }
}
