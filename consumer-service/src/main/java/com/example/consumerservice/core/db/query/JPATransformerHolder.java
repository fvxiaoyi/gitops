package com.example.consumerservice.core.db.query;

import org.hibernate.transform.ResultTransformer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ebin
 */
public class JPATransformerHolder {
    public static final JPATransformerHolder INSTANCE = new JPATransformerHolder();

    private final Map<Class<?>, ResultTransformer> resultBeanTransformers = new HashMap<>();

    private JPATransformerHolder() {
    }

    public ResultTransformer get(Class<?> clazz) {
        return resultBeanTransformers.get(clazz);
    }

    public void add(Class<?> clazz, ResultTransformer transformer) {
        this.resultBeanTransformers.put(clazz, transformer);
    }
}
