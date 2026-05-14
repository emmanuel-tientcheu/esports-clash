package com.example.esport_clash.auth.infrastructure.persistance.sql;

import com.example.esport_clash.auth.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SQLUserAccessor extends CrudRepository<User, String> {
    public boolean existsByEmail(String email);
    public Optional<User> findByEmail(String email);
}
