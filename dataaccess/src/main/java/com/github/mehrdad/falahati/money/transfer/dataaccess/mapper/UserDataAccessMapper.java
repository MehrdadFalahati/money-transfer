package com.github.mehrdad.falahati.money.transfer.dataaccess.mapper;

import com.github.mehrdad.falahati.money.transfer.dataaccess.entity.UserEntity;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDataAccessMapper {

    private final AccountDataAccessMapper accountDataAccessMapper;

    public UserEntity userToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId().getValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .accounts(user.getAccounts()
                        .stream()
                        .map(accountDataAccessMapper::accountToEntity)
                        .collect(Collectors.toSet()))
                .roles(user.getRoles())
                .isEnabled(user.getEnabled())
                .build();
    }

    public User entityToUser(UserEntity userEntity) {
        User user = User.builder()
                .id(new UserId(userEntity.getId()))
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
        userEntity.getAccounts().stream()
                .map(accountDataAccessMapper::entityToAccount)
                .forEach(user::addAccounts);
        return user;
    }
}
