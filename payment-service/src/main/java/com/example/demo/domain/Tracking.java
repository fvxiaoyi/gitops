package com.example.demo.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tracking")
//@DynamicUpdate
public class Tracking {
    @Id
    public String id;
    @NotNull(message = "qty 不能为空")
    public Integer qty;
    public String name;
    public String address;
}
