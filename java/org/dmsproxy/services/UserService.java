package org.dmsproxy.services;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.dmsproxy.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class UserService {
    @Inject
    EntityManager em;

    @Transactional
    public PanacheQuery<User> getAllUsers() {
        return User.findAll();
    }

    @Transactional
    public void createUser(String username, String role, Optional<String> password) {
        User user = new User();
        user.username = username;
        user.role = role;
        password.ifPresent(p -> user.password = p);
        em.persist(user);
    }
}
