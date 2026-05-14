package com.example.esport_clash.auth.infrastructure.persistance.sql;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.core.infrastructure.persistance.sql.SQLBaseRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLUserRepository extends SQLBaseRepository<User> implements UserRepository {

    private final SQLUserAccessor userAccessor;

    public SQLUserRepository(EntityManager entity, SQLUserAccessor userAccessor) {
        super(entity);
        this.userAccessor = userAccessor;
    }

    @Override
    public Class<User> getEntityMangerClass() {
        return User.class;
    }

    @Override
    public boolean isEmailAddressAvailable(String email) {
        return !this.userAccessor.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userAccessor.findByEmail(email);
    }
}
