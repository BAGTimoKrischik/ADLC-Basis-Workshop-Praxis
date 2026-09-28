/** Ein einzelnes Token mit Art, Text (z.B. "3.14" oder "+") und Startposition im Quelltext. */
public record Token(TokenArt art, String text, int position) {
}
