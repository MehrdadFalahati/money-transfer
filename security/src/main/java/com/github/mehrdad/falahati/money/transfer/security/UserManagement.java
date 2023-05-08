package com.github.mehrdad.falahati.money.transfer.security;

import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.UserApplicationService;
import com.github.mehrdad.falahati.money.transfer.security.entity.UserSecurityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagement implements UserDetailsService {

    private final UserApplicationService userApplicationService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userApplicationService.getUserByUsername(username);
        return new UserSecurityEntity(user);
    }
}
