package com.aytronn.kibvet.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum Role {
    ADMIN(
        Set.of(
                "user:get",
                "editor:get",
                "editor:post",
                "editor:delete",
                "editor:put",
                "admin:get",
                "admin:post",
                "admin:delete",
                "admin:put"
                
                
        )
),
    EDITOR(
        Set.of(
                "editor:get",
                "editor:post",
                "editor:delete",
                "editor:put"
        )
),
    USER(
        Set.of(
                "user:get"
        )
);



    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        this.permissions.forEach(permission -> {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        });

        return authorities;
    }
    
}
