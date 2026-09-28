import java.util.Arrays;

/** Merkt sich die letzten Ergebnisse (Ringpuffer) und bietet Durchschnitt/Median darueber. */
public final class Verlauf {
    private static final int KAPAZITAET = 5;

    private final Ringpuffer puffer = new Ringpuffer(KAPAZITAET);

    public void merke(double ergebnis) {
        puffer.schreiben(ergebnis);
    }

    public double durchschnitt() {
        double[] werte = pruefeNichtLeer();
        double summe = 0;
        for (double w : werte) {
            summe += w;
        }
        return summe / werte.length;
    }

    public double median() {
        double[] sortiert = pruefeNichtLeer().clone();
        Arrays.sort(sortiert);
        int mitte = sortiert.length / 2;
        if (sortiert.length % 2 == 1) {
            return sortiert[mitte];
        }
        return (sortiert[mitte - 1] + sortiert[mitte]) / 2.0;
    }

    private double[] pruefeNichtLeer() {
        double[] werte = puffer.alleBelegten();
        if (werte.length == 0) {
            throw new IllegalStateException("Verlauf ist leer");
        }
        return werte;
    }
}
