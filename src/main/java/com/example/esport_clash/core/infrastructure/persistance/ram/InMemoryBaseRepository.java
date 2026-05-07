package com.example.esport_clash.core.infrastructure.persistance.ram;

import com.example.esport_clash.core.domain.model.BaseEntity;
import com.example.esport_clash.core.infrastructure.persistance.BaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    protected Map<String, T> entities = new HashMap<>();

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public void save(T entity) {
        this.entities.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        this.entities.remove(entity.getId());
    }

    public void clear() { this.entities.clear(); }
}
