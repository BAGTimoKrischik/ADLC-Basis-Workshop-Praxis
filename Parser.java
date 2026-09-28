import java.util.ArrayList;
import java.util.List;

/**
 * Precedence-Climbing-Parser: baut aus den Tokens einen Syntaxbaum (Knoten).
 * expression := unary (BinOp expression)*   -- Prioritaet steuert die Klammerung
 * unary      := '-' unary | atom
 * atom       := zahl | bezeichner ['(' argListe ')'] | '(' expression ')'
 */
public final class Parser {
    private final List<Token> tokens;
    private final int anzahl;
    private int pos;

    private Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.anzahl = tokens.size();
        this.pos = 0;
    }

    public static Knoten parsen(List<Token> tokens) {
        Parser parser = new Parser(tokens);
        Knoten wurzel = parser.parseExpression(0);
        if (!parser.istVollstaendig()) {
            throw new IllegalArgumentException("Unerwartetes Token nach Ausdruck: " + parser.peek().text());
        }
        return wurzel;
    }

    private boolean hatNaechstes() {
        return pos < anzahl;
    }

    /** Ob der gesamte Token-Strom konsumiert wurde. */
    private boolean istVollstaendig() {
        return pos == anzahl - 1;
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private Token weiter() {
        return tokens.get(pos++);
    }

    private void erwarte(TokenArt art, String fehlermeldung) {
        if (!hatNaechstes() || peek().art() != art) {
            throw new IllegalArgumentException(fehlermeldung);
        }
        pos++;
    }

    private Knoten parseExpression(int minPrioritaet) {
        Knoten links = parseUnary();
        while (hatNaechstes() && OperatorTabelle.istBinaerOperator(peek().art())
                && OperatorTabelle.prioritaetVon(peek().art()) >= minPrioritaet) {
            Token operator = weiter();
            int operatorPrioritaet = OperatorTabelle.prioritaetVon(operator.art());
            int naechstesMin = OperatorTabelle.istRechtsAssoziativ(operator.art())
                    ? operatorPrioritaet
                    : operatorPrioritaet + 1;
            Knoten rechts = parseExpression(naechstesMin);
            links = new BinaerOp(operator.art(), links, rechts);
        }
        return links;
    }

    private Knoten parseUnary() {
        if (hatNaechstes() && peek().art() == TokenArt.MINUS) {
            weiter();
            return new UnaerOp(parseUnary());
        }
        return parseAtom();
    }

    private Knoten parseAtom() {
        if (!hatNaechstes()) {
            throw new IllegalArgumentException("Unerwartetes Ende des Ausdrucks");
        }
        Token token = weiter();
        return switch (token.art()) {
            case ZAHL -> new Zahl(Double.parseDouble(token.text().replace("_", "")));
            case BEZEICHNER -> parseBezeichnerOderAufruf(token.text());
            case KLAMMER_AUF -> {
                Knoten innen = parseExpression(0);
                erwarte(TokenArt.KLAMMER_ZU, "Schliessende Klammer erwartet");
                yield innen;
            }
            default -> throw new IllegalArgumentException("Unerwartetes Token: " + token.text());
        };
    }

    private Knoten parseBezeichnerOderAufruf(String name) {
        if (!hatNaechstes() || peek().art() != TokenArt.KLAMMER_AUF) {
            return new VariablenRef(name);
        }
        weiter();
        List<Knoten> argumente = new ArrayList<>();
        if (!hatNaechstes() || peek().art() != TokenArt.KLAMMER_ZU) {
            argumente.add(parseExpression(0));
            while (hatNaechstes() && peek().art() == TokenArt.KOMMA) {
                weiter();
                argumente.add(parseExpression(0));
            }
        }
        erwarte(TokenArt.KLAMMER_ZU, "Schliessende Klammer nach Argumenten erwartet");
        return new Aufruf(name, argumente);
    }
}
