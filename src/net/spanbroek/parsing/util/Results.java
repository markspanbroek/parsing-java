package net.spanbroek.parsing.util;

import net.spanbroek.parsing.Result;

import java.util.*;

public class Results {

    public static Result result(String... result) {
        return new ArrayResult(result);
    }

    public static Result combine(Result... results) {
        ArrayList<String> combination = new ArrayList<String>();
        for(Result result : results) {
            combination.addAll(result);
        }
        return new ListResult(combination);
    }

    private static class ArrayResult extends AbstractList<String> implements Result {

        private String[] result;

        private ArrayResult(String... result) {
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

    private static class ListResult extends AbstractList<String> implements Result {

        private List<String> result;

        private ListResult(List<String> result) {
            this.result = result;
        }


        @Override
        public String get(int i) {
            return result.get(i);
        }

        @Override
        public int size() {
            return result.size();
        }
    }

}
