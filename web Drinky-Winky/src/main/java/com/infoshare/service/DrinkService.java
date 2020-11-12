package com.infoshare.service;

import com.infoshare.dao.DrinkDao;
import com.infoshare.dto.DrinkDTO;
import com.infoshare.model.Drink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@RequestScoped
public class DrinkService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @EJB
    DrinkDao drinkDao;

    public String addDrink(DrinkDTO drinkDTO) {
        if (drinkDao.getDrinkList().stream().noneMatch(drink -> drink.getName().equals(drinkDTO.getName()))) {
            Drink drink = DrinkDTO.DtoToDrink(drinkDTO);
            drinkDao.addDrink(drink);
            return "SUCCESS! THE DRINK HAS BEEN ADDED";
        }
        return "SOMETHING WENT WRONG! THE DRINK WITH THAT NAME ALREADY EXIST";
    }

    public void editDrink(DrinkDTO drinkDTO) {
        Drink drink = DrinkDTO.DtoToDrink(drinkDTO);
        drinkDao.editDrink(drink);
    }


    public DrinkDTO getDrinkByName(String name) {
        Drink drinkByName = drinkDao.getDrinkByName(name);
        if (drinkByName != null) {
            return DrinkDTO.drinkToDTO(drinkByName);
        }
        return null;
    }


    public DrinkDTO getDrinkById(Long id) {
        Drink drinkById = drinkDao.getDrinkById(id);
        if (drinkById != null) {
            return DrinkDTO.drinkToDTO(drinkById);
        }
        return null;
    }


    public void deleteDrinkById(Long id) {
        if (drinkDao.getDrinkList().stream().anyMatch(drink -> drink.getId().equals(id))) {
            drinkDao.deleteDrinkById(id);
            logger.info("Drink has been deleted");
        }
    }


    public void deleteDrinkByName(String name) {
        if (drinkDao.getDrinkList().stream().anyMatch(drink -> drink.getName().equals(name))) {
            drinkDao.deleteDrinkByName(name);
            logger.info("Drink has been deleted");
        }
    }


    public List<DrinkDTO> getDrinkList() {
        return drinkDao.getDrinkList()
                .stream()
                .map(DrinkDTO::drinkToDTO)
                .collect(Collectors.toList());
    }


    public List<DrinkDTO> getRequestDrinkList(int request, int size) {

        int fromIndex = (request - 1) * size;
        int toIndex = request * size;

        if (toIndex > drinkDao.getDrinkList().size()) {
            toIndex = drinkDao.getDrinkList().size();
        }

        return drinkDao.getDrinkList()
                .stream()
                .map(DrinkDTO::drinkToDTO)
                .collect(Collectors.toList())
                .subList(fromIndex, toIndex);
    }


    public Set<String> getUniqueGlassesNameList() {
        return drinkDao.getDrinkList()
                .stream()
                .map(Drink::getGlassType)
                .collect(Collectors.toSet());
    }

    public List<Drink> findRecipeByCategoryId(List<Long> ids) {
        return drinkDao.findDrinkByCategoryId(ids);
    }

    public List<Drink> findDrinkByCategoryIdAndIngredient(List<Long> ids, List<String> names) {
        return drinkDao.findDrinkByCategoryIdAndIngredient(ids, names);
    }

    public List<DrinkDTO> findDrinkListByName(String name) {
        if (name != null || !name.isBlank()) {
            List<DrinkDTO> found = new ArrayList<>();
            List<DrinkDTO> drinkList = getDrinkList();
            for (DrinkDTO foundDrink : drinkList) {
                if (foundDrink.getName().toLowerCase().contains(name.toLowerCase())) {
                    found.add(foundDrink);
                }
            }
            return found;
        }
        return null;
    }

    public List<DrinkDTO> findDrinkListByGlass(String glass) {
        if (glass != null || !glass.isBlank()) {
            List<DrinkDTO> found = new ArrayList<>();
            List<DrinkDTO> drinkList = getDrinkList();
            for (DrinkDTO foundDrink : drinkList) {
                if (foundDrink.getGlassType().toLowerCase().contains(glass.toLowerCase())) {
                    found.add(foundDrink);
                }
            }
            return found;
        }
        return null;
    }

    public List<DrinkDTO> findDrinkListByCategory(String category) {

        if (category != null || !category.isBlank()) {
            List<DrinkDTO> found = new ArrayList<>();
            List<DrinkDTO> drinkList = getDrinkList();
            for (DrinkDTO foundDrink : drinkList) {
                if (foundDrink.getCategory().toString().toLowerCase().contains(category.toLowerCase())) {
                    found.add(foundDrink);
                }
            }
            return found;
        }
        return null;
    }

    public List<DrinkDTO> findDrinkListByIngredient(String ingredient) {
        if (ingredient != null || !ingredient.isBlank()) {
            List<DrinkDTO> found = new ArrayList<>();
            List<DrinkDTO> drinkList = getDrinkList();
            for (DrinkDTO foundDrink : drinkList) {
                if (foundDrink.getIngredientList().stream().anyMatch(d -> d.getName().toLowerCase().contains(ingredient.toLowerCase()))) {
                    found.add(foundDrink);
                }
            }
            return found;
        }
        return null;
    }

    public List<DrinkDTO> findDrinkListByType(String type) {
        if (type != null || !type.isBlank()) {
            List<DrinkDTO> found = new ArrayList<>();
            List<DrinkDTO> drinkList = getDrinkList();
            for (DrinkDTO foundDrink : drinkList) {
                if (foundDrink.getDrinkType().toLowerCase().contains(type.toLowerCase())) {
                    found.add(foundDrink);
                }
            }
            return found;
        }
        return null;
    }

    public List<DrinkDTO> findDrinkListBasicOnAllValues(String input) {

        if (input != null || !input.isBlank()) {
            List<DrinkDTO> found = new ArrayList<>();
            List<DrinkDTO> drinkList = getDrinkList();
            for (DrinkDTO foundDrink : drinkList) {

                //Name check
                if (foundDrink.getName().toLowerCase().contains(input.toLowerCase())) {
                    found.add(foundDrink);
                }
                //glass type check
                if (foundDrink.getGlassType().toLowerCase().contains(input.toLowerCase())) {
                    if (!found.contains(foundDrink)) {
                        found.add(foundDrink);
                    }
                }
                //drink type check, if user want to find alcoholic or non alcoholic
                if (foundDrink.getDrinkType().toLowerCase().contains(input.toLowerCase())) {
                    if (!found.contains(foundDrink)) {
                        found.add(foundDrink);
                    }
                }
                // category check
                if (foundDrink.getCategory().toString().toLowerCase().contains(input.toLowerCase())) {
                    if (!found.contains(foundDrink)) {
                        found.add(foundDrink);
                    }
                }
                // trying to find an ingredient
                if (foundDrink.getIngredientList().stream().anyMatch(d -> d.getName().toLowerCase().contains(input.toLowerCase()))) {
                    if (!found.contains(foundDrink)) {
                        found.add(foundDrink);
                    }
                }
            }
            return found;
        }
        return null;
    }

}