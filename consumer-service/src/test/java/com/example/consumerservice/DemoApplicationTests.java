package com.example.consumerservice;

import com.example.consumerservice.apps.order.domain.service.Tracking;
import com.example.consumerservice.apps.order.domain.service.TrackingEvent;
import com.example.consumerservice.bff.service.query.DomainEventTracking;
import com.example.consumerservice.core.db.query.JPAQueryCommand;
import com.example.consumerservice.core.db.query.JPAQueryService;
import com.example.consumerservice.core.db.query.PagingResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

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

    @Autowired
    JPAQueryService jpaSqlQueryService;

    @Test
    public void testQuery() {
        JPAQueryCommand<DomainEventTracking> domainEventTrackingJPAQueryCommand = new JPAQueryCommand<>("article.list", DomainEventTracking.class);
        domainEventTrackingJPAQueryCommand.addQueryParam("aggregateRootId", "13");
        PagingResult<DomainEventTracking> select = jpaSqlQueryService.select(domainEventTrackingJPAQueryCommand, 0, 10);
        System.out.println(select);
    }

}
