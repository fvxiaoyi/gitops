package com.example.consumerservice.bff.controller;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;

public class Demo extends DemoSuper {
    @NotNull
    private ZonedDateTime t1 = ZonedDateTime.now();
    private ZonedDateTime t3;
    public static final Instant t2 = Instant.now();
    transient String abc = "abc";
    private int i = 100;

    public Demo() {
//        super();
    }

   /* public boolean equals(Demo obj) {
        return super.equals(obj);
    }*/

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public class Test {
        private int a;
        private int b;
        private int c;

        public Test(int a) {
            // overlapping by constructor argument
            this.a = a;       // OK, this keyword used
            b = 0;            // OK, no overlap
            foo(5);
            a = a;// OK
        }

        public void foo(int c) {
            // overlapping by method argument
            c = c;            // violation, reference to instance variable "c" requires "this"
        }
    }


    public class Boolean {
        boolean a;

        public Boolean(boolean a) {
            this.a = a;
        }
    }

    class Foo2 { // violation, second top-level class
        // methods
        public void method() {
            String text = (abc == null)
                    ? ""
                    : abc;
        }
    }

    class Foo3 { // violation, second top-level class
        // methods
        public void method() throws Exception {
            int i = 0;
            while (i >= 0) {
                switch (i) {
                    case 1:
                        i++;
                    case 2: // violation, previous case contains code but lacks
                        // break, return, yield, throw or continue statement
                        i++;
                        break;
                    case 3: // OK
                        i++;
                        return;
                    case 4: // OK
                        i++;
                        throw new Exception();
                    case 5: // OK
                        i++;
                        continue;
                    case 6: // OK
                    case 7: // Previous case: OK, case does not contain code
                        // This case: OK, by default the last case might not have statement
                        // that transfer control
                        i++;

                }
            }
        }
    }
}
