package com.example.demo.core;

import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

public class HibernateListener implements PreInsertEventListener, PreUpdateEventListener, PreDeleteEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        System.out.println("onPreInsert");
        return true;
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        return true;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        return true;
    }
}
