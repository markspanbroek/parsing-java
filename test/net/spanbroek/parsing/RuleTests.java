package net.spanbroek.parsing;

import org.junit.Test;

import java.util.List;

import static net.spanbroek.parsing.Parsing.*;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;

public class RuleTests {

    @Test
    public void shouldBeAbbreviationForExpression() {
        Rule parser = rule(concat(literal("a"), literal("b")));
        assertEquals(result("a","b"), parser.parse("ab"));
    }

    @Test
    public void shouldHandleLeftRecursion() {
        Rule parser = rule();
        parser.is( choice(empty, concat(parser, literal("a"))) );

        assertEquals(result("a", "a", "a"), parser.parse("aaa"));
    }

    @Test
    public void shouldHandleRightRecursion() {
        Rule parser = rule();
        parser.is( choice(empty, concat(literal("a"), parser)) );

        assertEquals(result("a", "a", "a"), parser.parse("aaa"));
    }

    @Test
    public void shouldHandleRecursionWithoutProgress() {
        Rule parser = rule();
        parser.is( choice(parser, literal("a")) );

        assertEquals("a", parser.parse("a"));
    }

    @Test
    public void shouldEnableTransformations() {
        Rule parser = rule();
        parser.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result) {
                return "foo";
            }
        });

        assertEquals("foo", parser.parse(""));
    }

    @Test
    public void shouldConcatenateItsArguments() {
        Rule parser = rule(literal("a"), literal("b"));
        assertEquals(result("a", "b"), parser.parse("ab"));

        parser.is(literal("a"), literal("b"));
        assertEquals(result("a", "b"), parser.parse("ab"));
    }
}
