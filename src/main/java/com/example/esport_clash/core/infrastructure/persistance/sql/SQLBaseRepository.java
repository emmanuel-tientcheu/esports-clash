package com.example.esport_clash.core.infrastructure.persistance.sql;

import com.example.esport_clash.core.domain.model.BaseEntity;
import com.example.esport_clash.core.infrastructure.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public abstract class SQLBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private final EntityManager entityManager;

    public SQLBaseRepository(EntityManager entity) { this.entityManager = entity; }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(this.entityManager.find(getEntityMangerClass(), id));
    }

    @Override
    public void save(T entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
        this.entityManager.remove(entity);
    }

    @Override
    public void clear() {
        this.entityManager.createQuery("DELETE * FROM " + getEntityMangerClass().getSimpleName()).executeUpdate();
    }

    public abstract Class<T> getEntityMangerClass();
}
