package com.infoshare.dao;

import com.infoshare.model.Drink;
import com.infoshare.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUserById(Long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public List<User> getUserList() {
        return entityManager.createNamedQuery(User.FIND_USER_LIST, User.class).getResultList();
    }

    //Todo query to make
    public List<Drink> getFavouriteDrinkList() {
        return entityManager.createNamedQuery(User.GET_FAVOURITE_LIST, Drink.class).getResultList();
    }

    public User findUserByName(String name) {
        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_USER_BY_NAME, User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public User findUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_USER_BY_LOGIN, User.class);
        query.setParameter("login",login);
        return query.getSingleResult();
    }
}

// if (userToUpdate != null) {
//         userToUpdate.setName(userDTO.getName());
//         userToUpdate.setSurname(userDTO.getSurname());
//         userToUpdate.setUserType(userDTO.getUserType());
//         userToUpdate.setLogin(userDTO.getLogin());
//         userToUpdate.setPassword(userDTO.getPassword());
//         userToUpdate.setEmail(userDTO.getEmail());