package com.infoshare.drinkywinky.utils;

import com.infoshare.drinkywinky.model.Drink;
import com.infoshare.drinkywinky.model.DrinkList;
import com.infoshare.drinkywinky.repositories.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public Utils() {
    }

    /**
     * @return only names of all drink
     */
    public static List<String> getNamesOfAllDrink(DrinkList drinkList) {
        return   drinkList.getAllDrink()
                .stream()
                .map(Drink::getName)
                .collect(Collectors.toList());
    }

    /**
     * @return only category of each drink
     */
    public static Set<String> getCategoryOfAllDrink(DrinkList drinkList) {
        return drinkList.getAllDrink()
                .stream().
                        map(Drink::getCategory)
                .collect(Collectors.toSet());
    }

    /**
     * @return only unique names included in each drink
     */
    public static Set<String> getNamesOfAllIngredients(DrinkList drinkList) {
        Set<String> ingredients = new HashSet<>();
        List<Drink> allDrink = drinkList.getAllDrink();
        for (Drink drink : allDrink) {
           drink.getIngredients().forEach(e ->ingredients.add(e.toLowerCase()));
        }
        return ingredients;
    }
}
