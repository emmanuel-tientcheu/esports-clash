package com.example.esport_clash.auth.infrastructure.persistance.sql;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.core.infrastructure.persistance.sql.SQLBaseRepository;
import jakarta.persistence.EntityManager;

public class SQLUserRepository extends SQLBaseRepository<User> implements UserRepository {

    public SQLUserRepository(EntityManager entity) {
        super(entity);
    }

    @Override
    public Class<User> getEntityMangerClass() {
        return User.class;
    }
}
