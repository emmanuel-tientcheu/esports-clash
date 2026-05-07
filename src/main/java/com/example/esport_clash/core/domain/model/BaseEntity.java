package com.example.esport_clash.core.domain.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    public String id;

    public BaseEntity() {}

    public BaseEntity(String id) { this.id = id; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id;}
}
