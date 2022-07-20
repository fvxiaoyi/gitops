package com.example.consumerservice.core.util.json;

import com.example.consumerservice.core.domain.impl.AbstractAggregateRoot;
import com.example.consumerservice.core.utils.json.JSON;

import java.time.Instant;

public class JSONTest {
    public static void main(String[] args) {
        System.out.println(JSON.toJSON(new Demo()));
        String s = "{\"id\":null,\"createdTime\":\"2022-07-18T12:41:46.3556274+08:00\",\"time2\":\"2022-07-18T04:41:46.356628500Z\"}";
        System.out.println(JSON.fromJSON(Demo.class, s));
    }

    public static class Demo extends AbstractAggregateRoot {
        Instant time2 = Instant.now();

        @Override
        public String toString() {
            return "Demo{" +
                    "time2=" + time2 +
                    '}';
        }
    }
}
