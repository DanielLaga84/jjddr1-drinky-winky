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

    public void addFav(Long drinkId, Long userId) {
        User userById = getUserById(userId);

        List<Drink> favouriteDrinkList = userById.getFavouriteDrinkList();
        Drink drink = entityManager.find(Drink.class, drinkId);

        if(favouriteDrinkList.stream().anyMatch(e -> drinkId.equals(e.getId()))){
            favouriteDrinkList.remove(drink);
        } else {
            favouriteDrinkList.add(drink);
        }
        userById.setFavouriteDrinkList(favouriteDrinkList);
    }

    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }


    public void updateUser(User user, Long id) {
        User userToUpdate = entityManager.find(User.class, id);
        if (userToUpdate != null) {
            userToUpdate.setName(user.getName());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setUserType(user.getUserType());
            userToUpdate.setLogin(user.getLogin());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setFavouriteDrinkList(user.getFavouriteDrinkList());
            entityManager.merge(userToUpdate);
        }
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

    public User getLogin(String login){
        TypedQuery<User> query = entityManager.createNamedQuery(User.GET_USER_BY_LOGIN, User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    public User getPassword(String password){
        TypedQuery<User> query = entityManager.createNamedQuery(User.GET_USER_BY_PASSWORD, User.class);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public User getUserByLoginAndPass(String login, String password){
        TypedQuery<User> query = entityManager.createNamedQuery(User.GET_LOGIN_AND_PASSWORD, User.class);
        query.setParameter("login",login);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

}
