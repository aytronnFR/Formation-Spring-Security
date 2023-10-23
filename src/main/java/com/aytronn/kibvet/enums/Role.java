package com.aytronn.kibvet.enums;

public enum Role {
    ADMIN,
    EDITOR,
    USER;



    Role() {
    }

    /*
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
     */
}
