package com.infoshare.dto;

import com.infoshare.model.Ingredient;
import java.util.ArrayList;
import java.util.List;


public class IngredientDTO {

    private Long id;
    private String name;
    private String measure;
    private List<DrinkDTO> drinkDTOS = new ArrayList<>();

    public IngredientDTO ingredientToDto(Ingredient ingredient){
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setMeasure(ingredient.getMeasure());
        return ingredientDTO;
    }

    public Ingredient dtoToIngredient (IngredientDTO ingredientDTO){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setMeasure(ingredientDTO.getMeasure());
        return ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
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

    public List<DrinkDTO> getDrinkDTOS() {
        return drinkDTOS;
    }

    public void setDrinkDTOS(List<DrinkDTO> drinkDTOS) {
        this.drinkDTOS = drinkDTOS;
    }
}
