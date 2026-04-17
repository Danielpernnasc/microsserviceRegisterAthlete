package com.trainday.bodybuilder.infra.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.trainday.bodybuilder.domain.model.Login;

public class UserDetailsImpl implements UserDetails {
    private final Login login;

    public UserDetailsImpl(Login login){
        this.login = login;
    }

     @Override
    public String getUsername() {
        return login.getEmail(); // Spring usa isso como identificador
    }

    @Override
    public String getPassword() {
        return login.getPassword(); // senha já deve estar com BCrypt
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // sem roles por enquanto
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }



}
