import java.util.List;
import java.util.Map;

/** Fassade: Ausdruck auswerten (Lexer -> Parser -> Auswerter) plus Ergebnis-Verlauf. */
public final class Taschenrechner {
    private final Verlauf verlauf = new Verlauf();

    public static double berechne(String ausdruck) {
        return berechneMit(ausdruck, Map.of());
    }

    /** Wie berechne, aber mit zusaetzlichen Variablenbindungen (ueberschreibt pi/e nicht). */
    public static double berechneMit(String ausdruck, Map<String, Double> variablen) {
        Umgebung umgebung = Umgebung.mitKonstanten();
        variablen.forEach(umgebung::setzen);
        List<Token> tokens = Lexer.tokenisiere(ausdruck);
        Knoten baum = Parser.parsen(tokens);
        return Auswerter.auswerten(baum, umgebung);
    }

    public static String formatiere(double wert) {
        return Formatierer.formatiere(wert, 2);
    }

    public void merkeErgebnis(double wert) {
        verlauf.merke(wert);
    }

    public double durchschnittVerlauf() {
        return verlauf.durchschnitt();
    }

    public double medianVerlauf() {
        return verlauf.median();
    }
}
