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
        parser.parse(input, new ResultHandler() {
            @Override
            public void handle(Result intermediate, RemainingInput remainder) {
                Result result = result(transformation.transform(intermediate, input.getPosition()));
                handler.handle(result, remainder);
            }
        }, session);
    }
}
