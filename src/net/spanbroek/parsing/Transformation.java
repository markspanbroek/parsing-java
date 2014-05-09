package net.spanbroek.parsing;

import java.util.List;

public interface Transformation {
    Object transform(List<Object> result, Position position);
}
