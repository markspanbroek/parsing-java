package net.spanbroek.parsing;

import org.junit.Test;

import java.util.List;

import static net.spanbroek.parsing.Parsing.*;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        parser.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, String text, Position position) {
                return "foo";
            }
        });

        assertEquals("foo", parser.parse(""));
    }

    @Test
    public void shouldPassPositionToTransformation() {
        Transformation transformation = mock(Transformation.class);
        Rule parser = rule("foo");
        parser.transform(transformation);

        parser.parse("foo");

        verify(transformation).transform(anyListOf(Object.class), any(String.class), eq(new Position(1,1)));
    }

    @Test
    public void shouldPassOriginalTextToTransformation() {
        Transformation transformation = mock(Transformation.class);
        Rule parser = rule("foo", "bar");
        parser.transform(transformation);

        parser.parse("foobar");

        verify(transformation).transform(anyListOf(Object.class), eq("foobar"), any(Position.class));
    }

    @Test
    public void shouldConcatenateItsArguments() {
        Rule parser = rule("a", "b");
        assertEquals(result("a", "b"), parser.parse("ab"));

        parser.is("a", "b");
        assertEquals(result("a", "b"), parser.parse("ab"));
    }
}
