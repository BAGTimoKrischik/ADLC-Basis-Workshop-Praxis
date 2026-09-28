import java.util.HashMap;
import java.util.Map;

/**
 * Bindet Variablennamen an Werte. Jeder Funktionsaufruf wertet seine
 * Argumente in einem eigenen Kind-Bereich aus (siehe Auswerter), damit
 * spaetere lokale Bindungen die aeussere Umgebung nicht veraendern koennen.
 * Die Tiefenpruefung schuetzt vor unbeabsichtigt tief verschachtelten
 * Aufrufen wie {@code wurzel(wurzel(wurzel(...)))}.
 */
public final class Umgebung {
    private static final int MAX_TIEFE = 5;

    private final Umgebung eltern;
    private final Map<String, Double> bindungen = new HashMap<>();
    private final int tiefe;

    public Umgebung() {
        this(null, 0);
    }

    private Umgebung(Umgebung eltern, int tiefe) {
        this.eltern = eltern;
        this.tiefe = tiefe;
    }

    public static Umgebung mitKonstanten() {
        Umgebung u = new Umgebung();
        u.setzen("pi", Math.PI);
        u.setzen("e", Math.E);
        return u;
    }

    public void setzen(String name, double wert) {
        bindungen.put(name, wert);
    }

    public double werteAus(String name) {
        Umgebung aktuelle = this;
        while (aktuelle != null) {
            Double wert = aktuelle.bindungen.get(name);
            if (wert != null) {
                return wert;
            }
            aktuelle = aktuelle.eltern;
        }
        throw new IllegalArgumentException("Unbekannte Variable: " + name);
    }

    /** Erstellt einen neuen, tiefer verschachtelten Bereich fuer einen Funktionsaufruf. */
    public Umgebung kindBereich() {
        Umgebung kind = new Umgebung(this, tiefe + 1);
        kind.pruefeTiefe();
        return kind;
    }

    private void pruefeTiefe() {
        if (tiefe > MAX_TIEFE) {
            throw new IllegalStateException(
                    "Ausdruck zu tief verschachtelt (mehr als " + MAX_TIEFE + " Ebenen an Funktionsaufrufen)");
        }
    }
}
