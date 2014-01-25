package net.spanbroek.parsing;

class ImplicitLiterals {

    static Parser[] convert(Object... objects) {
        Parser[] results = new Parser[objects.length];
        for (int i=0; i<results.length; i++) {
            results[i] = convert(objects[i]);
        }
        return results;
    }

    static Parser convert(Object object) {
        if (object instanceof Parser) {
            return (Parser) object;
        }
        if (object instanceof String) {
            return new LiteralParser((String) object);
        }
        String message = "Only Parsers and Strings allowed as arguments";
        throw new IllegalArgumentException(message);
    }
}
