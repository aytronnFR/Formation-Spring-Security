package com.aytronn.kibvet.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ADMIN(
            Set.of(
                "test:get",
                "test:post",
                "test:delete",
                "test:put",
                "admin:get",
                "admin:post",
                "admin:delete",
                "admin:put"

            )
    ),
    EDITOR(
            Set.of(
                "test:post",
                "test:put",
                "test:get"
            )
    ),
    USER(
            Set.of(
                "test:get"
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
