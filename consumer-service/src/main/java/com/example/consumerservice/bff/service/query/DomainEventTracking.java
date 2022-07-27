package com.example.consumerservice.bff.service.query;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * @author ebin
 */
public class DomainEventTracking {
    @JsonProperty("id")
    public Long id;

    @NotNull
    @JsonProperty("aggregate_root_class")
    public String aggregateRootClass;

    @NotNull
    @JsonProperty("aggregate_root_id")
    public Long aggregateRootId;

    @JsonProperty("payload")
    public String payload;

    @NotNull
    @JsonProperty("created_time")
    public ZonedDateTime createdTime;
}
