Parsing
=======

[![Build Status](https://travis-ci.org/markspanbroek/parsing-java.svg?branch=master)](https://travis-ci.org/markspanbroek/parsing-java)

This is a Java library for parsing context free grammars. It is inspired by a
paper by Mark Johnson: [Memoization of Top-down Parsing][1].

A calculator has been included as an example of how to use the library. It can
parse and evaluate simple expressions like "2+(1-1)":

        // Declare grammar rules:
        Rule expression = rule();
        Rule expression1 = rule();
        Rule addition = rule();
        ...

        // Set right hand side of grammar rules:
        ...
        addition.is(
                expression, "+", expression1
        );
        ...

        // Define transformation of parsing results:
        ...
        addition.transform(new Transformation() {
            @Override
            public Object transform(List<Object> result, Context context) {
                int left = (Integer)result.get(0);
                int right = (Integer)result.get(2);
                return left + right;
            }
        });
        ...

        // Parse a string
        expression.parse("2+(1-1)");

The complete example can be found in the
`src/net/spanbroek/parsing/example/Calculator.java` file.

Share and enjoy!

[1]: http://arxiv.org/pdf/cmp-lg/9504016.pdf
