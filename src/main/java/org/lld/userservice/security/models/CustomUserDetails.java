package org.lld.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.lld.userservice.models.Role;
import org.lld.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {

//    private User user;
    private String username;
    private String password;
    private List<CustomGrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CustomUserDetails() {}

    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getHashedPassword();
        this.authorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                this.authorities.add(new CustomGrantedAuthority(role));
            }
        }
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<CustomGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        if (user.getRoles() != null) {
//            for (Role role : user.getRoles()) {
//                grantedAuthorities.add(new CustomGrantedAuthority(role));
//            }
//        }
//        return grantedAuthorities;
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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
