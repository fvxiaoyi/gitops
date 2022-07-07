package com.example.demo;

import com.example.demo.domain.Tracking;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class DemoApplicationTests {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    void contextLoads() {
        Tracking tracking = new Tracking();
        tracking.id = "1";
        tracking.name = "1";
        tracking.address = "1";
        entityManager.persist(tracking);
    }

    @Test
    @Transactional
    @Rollback(false)
    void update() {
        Tracking tracking = entityManager.find(Tracking.class, "1");
        tracking.qty = 6;
    }
}
