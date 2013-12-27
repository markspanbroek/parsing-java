package net.spanbroek.parsing.util;

import net.spanbroek.parsing.Result;

import java.util.*;

public class Results {

    public static Result result(Object... result) {
        return new ArrayResult(result);
    }

    public static Result combine(Result... results) {
        ArrayList<Object> combination = new ArrayList<Object>();
        for(Result result : results) {
            combination.addAll(result);
        }
        return new ListResult(combination);
    }

    private static class ArrayResult extends AbstractList<Object> implements Result {

        private Object[] result;

        private ArrayResult(Object... result) {
            this.result = result;
        }

        @Override
        public Object get(int i) {
            return result[i];
        }

        @Override
        public int size() {
            return result.length;
        }
    }

    private static class ListResult extends AbstractList<Object> implements Result {

        private List result;

        private ListResult(List result) {
            this.result = result;
        }


        @Override
        public Object get(int i) {
            return result.get(i);
        }

        @Override
        public int size() {
            return result.size();
        }
    }

}
