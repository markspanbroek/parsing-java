package net.spanbroek.parsing;

public class Context {
    private String originalText;
    private Position position;

    Context(String originalText, Position position) {
        this.originalText = originalText;
        this.position = position;
    }

    public String getOriginalText() {
        return originalText;
    }

    public Position getPosition() {
        return position;
    }
}
