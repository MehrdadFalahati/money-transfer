package com.github.mehrdad.falahati.money.transfer.dataaccess.repository;

import com.github.mehrdad.falahati.money.transfer.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
