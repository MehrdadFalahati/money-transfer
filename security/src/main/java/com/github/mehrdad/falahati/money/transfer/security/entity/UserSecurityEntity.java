package com.github.mehrdad.falahati.money.transfer.security.entity;

import com.github.mehrdad.falahati.money.transfer.domain.entity.Role;
import com.github.mehrdad.falahati.money.transfer.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSecurityEntity implements UserDetails {

    private final String username;
    private final String password;
    private final boolean isEnabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSecurityEntity(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.getEnabled();
        this.authorities = getAuthorities(user.getRoles());
    }

    public Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().flatMap(r -> getAuthority(r).stream())
                .collect(Collectors.toSet());
    }

    public Set<SimpleGrantedAuthority> getAuthority(Role role) {
        Set<SimpleGrantedAuthority> authorities = role.getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermissionName()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
