package com.github.mehrdad.falahati.money.transfer.domain.port.output.repository;

import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.UserId;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(UserId userId);

    Optional<User> findByUsername(String username);
}
