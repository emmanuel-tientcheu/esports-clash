package com.example.esport_clash.core.infrastructure.persistance;

import com.example.esport_clash.core.domain.model.BaseEntity;

import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
    Optional<T> findById(String id);
    void save(T entity);
    void delete(T entity);

}
