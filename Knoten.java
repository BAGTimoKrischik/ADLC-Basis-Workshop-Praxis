import java.util.List;

/** Ein Knoten im Syntaxbaum (AST), den der Parser aus den Tokens aufbaut. */
public sealed interface Knoten permits Zahl, BinaerOp, UnaerOp, Aufruf, VariablenRef {
}

record Zahl(double wert) implements Knoten {
}

record BinaerOp(TokenArt operator, Knoten links, Knoten rechts) implements Knoten {
}

/** Unaeres Minus, z.B. das "-" in "-5" oder "3 * -x". */
record UnaerOp(Knoten operand) implements Knoten {
}

record Aufruf(String funktionsname, List<Knoten> argumente) implements Knoten {
}

record VariablenRef(String name) implements Knoten {
}
