package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.*;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;

public class RuleTests {

    @Test
    public void shouldBeAbbreviationForExpression() {
        Rule parser = rule("a", "b");
        assertEquals(result("a","b"), parser.parse("ab"));
    }

    @Test
    public void shouldHandleLeftRecursion() {
        Rule parser = rule();
        parser.is( choice(empty, concat(parser, "a")) );

        assertEquals(result("a", "a", "a"), parser.parse("aaa"));
    }

    @Test
    public void shouldHandleRightRecursion() {
        Rule parser = rule();
        parser.is( choice(empty, concat("a", parser)) );

        assertEquals(result("a", "a", "a"), parser.parse("aaa"));
    }

    @Test
    public void shouldHandleRecursionWithoutProgress() {
        Rule parser = rule();
        parser.is( choice(parser, "a") );

        assertEquals("a", parser.parse("a"));
    }

    @Test
    public void shouldEnableTransformations() {
        Rule parser = rule();
        parser.transform((result, context) -> "foo");

        assertEquals("foo", parser.parse(""));
    }

    @Test
    public void shouldPassPositionToTransformation() {
        Rule parser = rule("foo", "bar");
        parser.transform((result, context) -> {
            assertEquals(new Position(1, 1), context.getPosition());
            return null;
        });

        parser.parse("foobar");
    }

    @Test
    public void shouldPassOriginalTextToTransformation() {
        Rule parser = rule("foo", "bar");
        parser.transform((result, context) -> {
            assertEquals("foobar", context.getOriginalText());
            return null;
        });

        parser.parse("foobar");
    }

    @Test
    public void shouldConcatenateItsArguments() {
        Rule parser = rule("a", "b");
        assertEquals(result("a", "b"), parser.parse("ab"));

        parser.is("a", "b");
        assertEquals(result("a", "b"), parser.parse("ab"));
    }
}
