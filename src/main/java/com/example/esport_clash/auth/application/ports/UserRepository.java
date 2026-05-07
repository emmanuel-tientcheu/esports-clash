package com.example.esport_clash.auth.application.ports;

import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.core.infrastructure.persistance.BaseRepository;

public interface UserRepository extends BaseRepository<User> {
    public boolean isEmailAddressAvailable(String email);
}
