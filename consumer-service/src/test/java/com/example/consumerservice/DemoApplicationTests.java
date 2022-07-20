package com.example.consumerservice;

import com.example.consumerservice.apps.order.domain.service.Tracking;
import com.example.consumerservice.apps.order.domain.service.TrackingEvent;
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
    @Rollback(false)
    void contextLoads() {
        Tracking tracking = new Tracking();
//        tracking.name = "1";
//        tracking.address = "1";
        tracking.registerEvent(new TrackingEvent(tracking));
        entityManager.persist(tracking);
    }

    @Test
    @Transactional
    @Rollback(false)
    void update() {
        Tracking tracking = entityManager.find(Tracking.class, "1");
//        tracking.qty = 6;
    }

}
