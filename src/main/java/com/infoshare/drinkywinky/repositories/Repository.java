package com.infoshare.drinkywinky.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshare.drinkywinky.model.Drink;
import com.infoshare.drinkywinky.model.DrinkList;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Repository {
    private static final String DATA_BASE_PATH_NAME = "search.json";
    private static final String USER_DATA_BASE_PATH_NAME = "drink list.json";
    private static final String MESSAGE = "File is saved";
    private static Repository INSTANCE = null;
    private static DrinkList drinkList;

    public Repository() {
        drinkList = readFile(USER_DATA_BASE_PATH_NAME);
    }

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    public Drink getDrinkByName(String drinkName) {
        return drinkList.getDrinkByName(drinkName);
    }

    public Drink getDrinkById(String drinkId) {
        return drinkList.getDrinkById(drinkId);
    }

    public List<Drink> getListOfDrinkByIngredient(String ingredient) {
        return drinkList.getDrinkByIngredients(ingredient);
    }

    public List<Drink> getListOfCategories(String category) {
        return drinkList.getDrinkByCategory(category);
    }

    public DrinkList getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(DrinkList drinkList) {
        this.drinkList = drinkList;
    }

    public static String saveToNewFile() {
        DrinkList drinkList = Repository.readFile(DATA_BASE_PATH_NAME);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(USER_DATA_BASE_PATH_NAME), drinkList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MESSAGE;
    }

    static DrinkList readFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        DrinkList drinkList = null;
        try {
            drinkList = mapper.readValue(file, DrinkList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drinkList;
    }
}
