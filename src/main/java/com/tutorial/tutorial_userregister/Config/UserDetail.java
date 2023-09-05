package com.tutorial.tutorial_userregister.Config;

import com.tutorial.tutorial_userregister.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetail implements UserDetails {

    private String id;
    private String password;
    private String auth;

    public UserDetail(User user) {
        this.id = user.getUserid();
        this.password = user.getPw();
        this.auth = "ROLE_" + user.getUserAuth();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {//계정 만료 여부 (false == 만료)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//계정 잠김 여부 (false == 만료)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//계정 비밀번호 만료 여부 (false == 만료)
        return true;
    }

    @Override
    public boolean isEnabled() {//계정 활성화 여부
        return true;
    }
}