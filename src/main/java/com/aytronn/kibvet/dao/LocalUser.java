package com.aytronn.kibvet.dao;

import com.aytronn.kibvet.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Setter
@ToString
public class LocalUser {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private boolean enabled;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
