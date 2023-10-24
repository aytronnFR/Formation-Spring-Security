package com.aytronn.kibvet.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Role {
    ADMIN(
            Set.of(
                    Permission.DELETE,
                    Permission.POST,
                    Permission.PUT,
                    Permission.GET
            )
    ),
    EDITOR(
            Set.of(
                    Permission.POST,
                    Permission.PUT,
                    Permission.GET
            )
    ),
    USER(
            Set.of(
                    Permission.GET
            )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        this.permissions.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
        });

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
