package com.github.mehrdad.falahati.money.transfer.dataaccess.adapter;

import com.github.mehrdad.falahati.money.transfer.dataaccess.mapper.UserDataAccessMapper;
import com.github.mehrdad.falahati.money.transfer.dataaccess.repository.UserJpaRepository;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.UserRepository;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    @Override
    public User save(User user) {
        return userDataAccessMapper.entityToUser(userJpaRepository.save(userDataAccessMapper.userToEntity(user)));
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue()).map(userDataAccessMapper::entityToUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userDataAccessMapper::entityToUser);
    }
}
