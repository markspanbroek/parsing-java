package net.spanbroek.parsing;

import static net.spanbroek.parsing.ImplicitLiterals.convert;

public class Parsing {

    public static Parser literal(String literal) {
        return new LiteralParser(literal);
    }

    public static Parser concat(Object... concatenation) {
        Parser[] parsers = convert(concatenation);
        if (parsers.length == 0) {
            return empty;
        }

        Parser result = parsers[0];
        for (int i=1; i<parsers.length; i++) {
            result = new ConcatenationParser(result, parsers[i]);
        }
        return result;
    }

    public static Parser choice(Object... choices) {
        Parser[] parsers = convert(choices);
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

    public static Rule rule(Object... concatenation) {
        return new Rule(concat(concatenation));
    }

    public static Parser repeat(Object... concatenation) {
        Rule repetition = rule();
        repetition.is(choice(empty, concat(repetition, concat(concatenation))));
        return repetition;
    }

    public static Parser range(char begin, char end) {
        return new Range(begin, end);
    }

    public static Parser optional(Object... concatenation) {
        return choice(empty, concat(concatenation));
    }
}
