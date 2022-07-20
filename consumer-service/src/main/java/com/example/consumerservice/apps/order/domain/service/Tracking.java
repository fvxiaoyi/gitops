package com.example.consumerservice.apps.order.domain.service;

import com.example.consumerservice.core.domain.impl.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tracking")
//@DynamicUpdate
public class Tracking extends AbstractAggregateRoot<Tracking> {
}
