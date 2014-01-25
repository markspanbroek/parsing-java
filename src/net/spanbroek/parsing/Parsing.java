package net.spanbroek.parsing;

public class Parsing {

    public static Parser literal(String literal) {
        return new LiteralParser(literal);
    }

    public static Parser concat(Parser... parsers) {
        if (parsers.length == 0) {
            return empty;
        }

        Parser result = parsers[0];
        for (int i=1; i<parsers.length; i++) {
            result = new ConcatenationParser(result, parsers[i]);
        }
        return result;
    }

    public static Parser choice(Parser... parsers) {
        if (parsers.length == 0) {
            return never;
        }

        Parser result = parsers[0];
        for (int i=1; i<parsers.length; i++) {
            result = new AlternativesParser(result, parsers[i]);
        }
        return result;
    }

    public static Parser empty = new EmptyParser();

    public static Parser never = new NeverParser();

    public static Rule rule() {
        return rule(empty);
    }

    public static Rule rule(Parser expression) {
        return new Rule(expression);
    }

    public static Parser repeat(Parser parser) {
        Rule repetition = rule();
        repetition.is(choice(empty, concat(repetition, parser)));
        return repetition;
    }

    public static Parser transform(Parser parser, Transformation transformation) {
        return new TransformingParser(parser, transformation);
    }

    public static Parser range(char begin, char end) {
        return new Range(begin, end);
    }

    public static Parser optional(Parser parser) {
        return choice(empty, parser);
    }
}
