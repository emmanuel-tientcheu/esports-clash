package com.example.esport_clash.auth.infrastructure.persistance.ram;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.core.infrastructure.persistance.ram.InMemoryBaseRepository;

public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    @Override
    public boolean isEmailAddressAvailable(String email) {
        return this.entities.values().stream().noneMatch(user -> user.getEmail().equals(email));
    }
}
