package net.spanbroek.parsing;

import java.util.List;

public interface Transformation {
    Object transform(List<Object> result, String text, Position position);
}
