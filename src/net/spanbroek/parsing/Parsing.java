package net.spanbroek.parsing;

public class Parsing {

    public static Parser literal(String literal) {
        return new LiteralParser(literal);
    }

    public static Parser concat(Parser parser1, Parser parser2, Parser... rest) {
        Parser result = new ConcatenationParser(parser1, parser2);
        for (Parser parser : rest) {
            result = new ConcatenationParser(result, parser);
        }
        return result;
    }

    public static Parser choice(Parser parser1, Parser parser2, Parser... rest) {
        Parser result = new AlternativesParser(parser1, parser2);
        for (Parser parser : rest) {
            result = new AlternativesParser(result, parser);
        }
        return result;
    }
}
