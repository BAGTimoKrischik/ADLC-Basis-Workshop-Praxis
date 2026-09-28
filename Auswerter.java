import java.util.List;

/** Wertet einen Syntaxbaum (Knoten) unter einer gegebenen Umgebung zu einem double aus. */
public final class Auswerter {

    private Auswerter() {
    }

    public static double auswerten(Knoten knoten, Umgebung umgebung) {
        return switch (knoten) {
            case Zahl z -> z.wert();
            case VariablenRef v -> umgebung.werteAus(v.name());
            case UnaerOp u -> -auswerten(u.operand(), umgebung);
            case BinaerOp b ->
                    verrechne(b.operator(), auswerten(b.links(), umgebung), auswerten(b.rechts(), umgebung));
            case Aufruf a -> werteAufrufAus(a, umgebung);
        };
    }

    private static double verrechne(TokenArt operator, double links, double rechts) {
        return switch (operator) {
            case PLUS -> links + rechts;
            case MINUS -> links - rechts;
            case MAL -> links * rechts;
            case GETEILT -> links / rechts;
            case MODULO -> links % rechts;
            case HOCH -> Math.pow(links, rechts);
            default -> throw new IllegalStateException("Kein binaerer Operator: " + operator);
        };
    }

    private static double werteAufrufAus(Aufruf aufruf, Umgebung umgebung) {
        Umgebung lokal = umgebung.kindBereich();
        List<Knoten> argumente = aufruf.argumente();
        double[] werte = new double[argumente.size()];
        for (int i = 0; i < werte.length; i++) {
            werte[i] = auswerten(argumente.get(i), lokal);
        }
        return Funktionen.aufrufen(aufruf.funktionsname(), werte);
    }
}
