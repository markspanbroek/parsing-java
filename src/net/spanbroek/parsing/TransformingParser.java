package net.spanbroek.parsing;

import static net.spanbroek.parsing.util.Results.result;

public class TransformingParser extends Parser {
    private final Parser parser;
    private final Transformation transformation;

    public TransformingParser(Parser parser, Transformation transformation) {
        this.parser = parser;
        this.transformation = transformation;
    }

    @Override
    protected void parse(final RemainingInput input, final ResultHandler handler, Session session) {
        parser.parse(input, (Result intermediate, RemainingInput remainder) -> {
                String text = remainder.difference(input);
                Position position = input.getPosition();
                Result result = result(transformation.transform(intermediate, new Context(text, position)));
            handler.handle(result, remainder);
        }, session);
    }
}
