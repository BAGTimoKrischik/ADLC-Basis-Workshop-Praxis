/**
 * Kapselt den rohen Eingabetext und die Positions-Arithmetik, die der Lexer
 * braucht. Eigener Typ statt nacktem String + int, damit Lexer, ZahlenLeser
 * und BezeichnerLeser dieselbe Grenz-Logik teilen statt sie zu duplizieren.
 */
public final class Quelltext {
    private final String text;

    public Quelltext(String text) {
        this.text = text;
    }

    public int laenge() {
        return text.length();
    }

    public char zeichenAt(int position) {
        return text.charAt(position);
    }

    /** Wie viele Zeichen ab (und inklusive) {@code position} noch folgen. */
    public int restLaenge(int position) {
        return laenge() - position;
    }

    public boolean hatZeichenAn(int position) {
        return position >= 0 && position < laenge();
    }

    public String ausschnitt(int start, int ende) {
        return text.substring(start, ende);
    }
}
