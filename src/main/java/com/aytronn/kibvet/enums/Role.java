package com.aytronn.kibvet.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ADMIN(
        Set.of(
            Permission.POST,
            Permission.DELETE,
            Permission.GET,
            Permission.PUT
        )
    ),
    EDITOR(
        Set.of(
            Permission.POST,
            Permission.DELETE,
            Permission.PUT
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
