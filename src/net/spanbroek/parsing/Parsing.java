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

    public static Parser empty = new EmptyParser();

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
