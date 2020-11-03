package com.infoshare.service;

import com.infoshare.dao.DrinkDao;
import com.infoshare.dto.DrinkDTO;
import com.infoshare.model.Drink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class DrinkService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @EJB
    DrinkDao drinkDao;

    public void loadDrinks(List<Drink> drinks) {
        drinkDao.loadDrink(drinks);
    }

    public void addDrink(DrinkDTO drinkDTO) {
        Drink drink = DrinkDTO.DtoToDrink(drinkDTO);
        drinkDao.addDrink(drink);
    }

    public void editDrink(DrinkDTO drinkDTO) {
        Drink drink = DrinkDTO.DtoToDrink(drinkDTO);
        DrinkDTO.drinkToDTO(drink);
    }

    @Transactional
    public DrinkDTO getDrinkByName(String name) {
        Drink drinkByName = drinkDao.getDrinkByName(name);
        if (drinkByName != null) {
            return DrinkDTO.drinkToDTO(drinkByName);
        }
        return null;
    }

    public Drink getDrinkById(Long id) {
        return drinkDao.getDrinkById(id);
    }

    public void deleteRecipeById(Long id) {
        drinkDao.deleteRecipeById(id);
        logger.info("Drink has been deleted");
    }

    @Transactional
    public void deleteRecipeByName(String name) {
        drinkDao.deleteRecipeByName(name);
        logger.info("Drink has been deleted");
    }

    public List<Drink> getRecipesList() {
        return drinkDao.getDrinkList();
    }

    public List<Drink> findRecipeByCategoryId(List<Long> ids) {
        return drinkDao.findDrinkByCategoryId(ids);
    }

    public List<Drink> findDrinkByCategoryIdAndIngredient(List<Long> ids, List<String> names) {
        return drinkDao.findDrinkByCategoryIdAndIngredient(ids, names);
    }
}
