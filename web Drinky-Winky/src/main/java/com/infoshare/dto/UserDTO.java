package com.infoshare.dto;

import com.infoshare.model.Drink;
import com.infoshare.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String userType;
    private String login;
    private String password;
    private List<DrinkDTO> favouriteDrinkList;

    public List<DrinkDTO> getFavouriteDrinkList() {
        return favouriteDrinkList;
    }

    public void setFavouriteDrinkList(List<DrinkDTO> favouriteDrinkList) {
        this.favouriteDrinkList = favouriteDrinkList;
    }

    public static UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserType(user.getUserType());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        List<DrinkDTO> drinkDTOList = new ArrayList<>();
        user.getFavouriteDrinkList().forEach(drink -> {
            DrinkDTO drinkDTO = DrinkDTO.drinkToDTO(drink);
            drinkDTOList.add(drinkDTO);
        });
        userDTO.setFavouriteDrinkList(drinkDTOList);
        return userDTO;
    }

    public static User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUserType(userDTO.getUserType());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        List<Drink> drinkList = new ArrayList<>();
        userDTO.getFavouriteDrinkList().forEach(drinkDTO -> {
            Drink drink = DrinkDTO.DtoToDrink(drinkDTO);
            drinkList.add(drink);
        });
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
