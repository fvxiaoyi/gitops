package com.example.consumerservice.apps.order.application;

import com.example.consumerservice.apps.order.domain.service.TrackingEvent;
import core.framework.domain.event.DomainPreEventListener;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventLinstiner2 implements DomainPreEventListener<TrackingEvent> {
    @Override
    public void onEvent(TrackingEvent event) {
        System.out.println(2);
    }

    class CyclomaticComplexity {
        // Cyclomatic Complexity = 11
        int a, b, c, d, e, n;

        public void fun1() {
        }

        public void fun2() {
        }

        public void fun3() {
        }

        public void fun4() {
        }

        public void foo() { // 1, function declaration
            if (a == b) { // 2, if
                fun1();
            } else if (a == 0 // 3, if
                    && b == c) { // 4, && operator
                if (c == -1) { // 5, if
                    fun2();
                }
            } else if (a == c // 6, if
                    || a == d) { // 7, || operator
                fun3();
            } else if (d == e) { // 8, if
                try {
                    fun4();
                } catch (Exception e) { // 9, catch
                }
            } else {
                switch (n) { // 10, switch
                    case 1:
                        fun1();
                        break;
                    case 2:
                        fun2();
                        break;
                    default:
                        break;
                }
            }
            a = a > 0 ? b : c; // 11, ternary operator
        }
    }
}
