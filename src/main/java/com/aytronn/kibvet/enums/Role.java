package com.aytronn.kibvet.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ADMIN(
        Set.of(
            "admin:get",
            "admin:post",
            "admin:delete",
            "admin:put"
      
        )
    ),
    EDITOR(
        Set.of(
            "user:post",
            "user:put",
            "user:get"
        )
    ),
    USER(
        Set.of(
            "user:get"
            // "auth:put"
        )
    );



  

    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        this.permissions.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission));
        });

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
