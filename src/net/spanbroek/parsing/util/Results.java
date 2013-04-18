package net.spanbroek.parsing.util;

import net.spanbroek.parsing.Result;

import java.util.AbstractList;

public class Results {

    public static Result result(String... result) {
        return new ResultImpl(result);
    }

    private static class ResultImpl extends AbstractList<String> implements Result {

        private String[] result;

        private ResultImpl(String... result) {
            this.result = result;
        }

        @Override
        public String get(int i) {
            return result[i];
        }

        @Override
        public int size() {
            return result.length;
        }
    }
}
