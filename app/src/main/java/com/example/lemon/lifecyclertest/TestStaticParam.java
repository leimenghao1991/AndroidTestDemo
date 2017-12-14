package com.example.lemon.lifecyclertest;

/**
 * Created by lemon on 2017/11/18.
 */

public class TestStaticParam {
    private static StaticClass staticClass = new StaticClass();

    public StaticClass getParam() {
        return staticClass;
    }

    static class StaticClass {
        public int age = 20;
        public String name = "lemon";

        @Override
        public String toString() {
            return "age = " + age + "; name = " + name + ";  toString = " + super.toString();
        }
    }
}
