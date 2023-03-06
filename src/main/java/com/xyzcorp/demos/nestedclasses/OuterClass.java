package com.xyzcorp.demos.nestedclasses;

public class OuterClass {
    private int x = 4;

    private int m = 30;

    private static int a = 19;

    public class InnerClass {
        private int y = 2;

        public int sum() {

            return x + y;
        }

        public int sumCond(int aa) {
            if (aa == 30) {
                m = 50;
            }
            return m + x + y;
        }
    }

    public static class StaticInnerClass {
        private int z = 10;

        public int bar() {
            return z + 19 + a;
        }
    }

    public int foo() {
        return x + 1;
    }

    public InnerClass bar() {
        return new InnerClass();
    }

    public static int baz() {
        return a + 10;
    }

    public int qux(int mz) {
        final int g = 10;

        /*
          This local class cannot be returned
          It is meant for the purposes to be used within the
          method. If was even attempted to be returned it wouldn't
          know how to find it. It would end up being an inner class.
         */
        class MyLocalClass {
            private int art(int n) {
                return mz + n + g + a + x;
            }
        }

        MyLocalClass myLocalClass = new MyLocalClass();
        return myLocalClass.art(20);
    }
}
