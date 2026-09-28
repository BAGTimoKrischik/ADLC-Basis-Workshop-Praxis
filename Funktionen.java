/** Eingebaute Funktionen: min, max, mittel, runde, potenz, wurzel, clamp. */
public final class Funktionen {
    private static final int MAX_STELLEN = 6;

    private Funktionen() {
    }

    public static double aufrufen(String name, double[] argumente) {
        return switch (name) {
            case "min" -> {
                pruefeArity(name, argumente, 2, 2);
                yield Math.min(argumente[0], argumente[1]);
            }
            case "max" -> {
                pruefeArity(name, argumente, 2, 2);
                yield Math.max(argumente[0], argumente[1]);
            }
            case "mittel" -> {
                pruefeArity(name, argumente, 1, Integer.MAX_VALUE);
                yield mittelwert(argumente);
            }
            case "runde" -> {
                pruefeArity(name, argumente, 2, 2);
                yield runde(argumente[0], (int) argumente[1]);
            }
            case "potenz" -> {
                pruefeArity(name, argumente, 2, 2);
                yield Math.pow(argumente[0], argumente[1]);
            }
            case "wurzel" -> {
                pruefeArity(name, argumente, 1, 1);
                yield Math.sqrt(argumente[0]);
            }
            case "clamp" -> {
                pruefeArity(name, argumente, 3, 3);
                yield clamp(argumente[0], argumente[1], argumente[2]);
            }
            default -> throw new IllegalArgumentException("Unbekannte Funktion: " + name);
        };
    }

    private static void pruefeArity(String name, double[] argumente, int min, int max) {
        if (argumente.length < min || argumente.length > max) {
            throw new IllegalArgumentException("Funktion " + name + " erwartet " + min
                    + (min == max ? "" : ".." + max) + " Argument(e), bekam " + argumente.length);
        }
    }

    private static double mittelwert(double[] werte) {
        double summe = 0;
        for (double w : werte) {
            summe += w;
        }
        return summe / werte.length;
    }

    /** Rundet auf hoechstens MAX_STELLEN Nachkommastellen (mehr macht bei double ohnehin keinen Sinn). */
    private static double runde(double wert, int stellen) {
        int begrenzteStellen = Math.min(Math.max(stellen, 0), MAX_STELLEN - 1);
        double faktor = Math.pow(10, begrenzteStellen);
        return Math.round(wert * faktor) / faktor;
    }

    private static double clamp(double wert, double min, double max) {
        return Math.max(min, Math.min(max, wert));
    }
}
