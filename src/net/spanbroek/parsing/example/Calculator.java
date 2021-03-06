package net.spanbroek.parsing.example;

import net.spanbroek.parsing.*;

import java.util.List;

import static net.spanbroek.parsing.Parsing.*;

public class Calculator {

    Rule expression = rule();
    Rule expression1 = rule();
    Rule addition = rule();
    Rule subtraction = rule();
    Rule braces = rule();
    Rule integer = rule();

    public Calculator() {
        expression.is(
                choice(expression1, addition, subtraction)
        );
        expression1.is(
                choice(braces, integer)
        );
        addition.is(
                expression, "+", expression1
        );
        subtraction.is(
                expression, "-", expression1
        );
        braces.is(
                "(", expression, ")"
        );
        integer.is(
                range('0','9')
        );

        integer.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                return Integer.parseInt((String) result.get(0));
            }
        });
        addition.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                int left = (Integer)result.get(0);
                int right = (Integer)result.get(2);
                return left + right;
            }
        });
        subtraction.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                int left = (Integer)result.get(0);
                int right = (Integer)result.get(2);
                return left - right;
            }
        });
        braces.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                return result.get(1);
            }
        });
    }

    public int evaluate(String expression) {
        Parser parser = this.expression;
        return (Integer) parser.parse(expression);
    }
}
