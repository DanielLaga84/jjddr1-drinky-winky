package com.infoshare.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NamedQueries(value = {
        @NamedQuery(
                name = "Drink.getDrinkList",
                query = "SELECT r FROM Drink r"),
        @NamedQuery(
                name = Drink.GET_DRINK_BY_CATEGORY_AND_INGREDIENT,
                query = "SELECT r.name FROM Drink r  JOIN r.ingredientList i WHERE r.category IN :categories AND  (i.name IN (:names))")
})


@Entity
@Table
public class Drink {

    public static final String GET_DRINK_BY_CATEGORY_AND_INGREDIENT = "Drink.findDrinkByCategoryIdAndIngredientName";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    @NotNull
    private String name;

    @Column(name = "is_custom")
    @NotNull
    private Boolean isCustom;

    @Column(name = "is_approved")
    @NotNull
    private Boolean isApproved;

    @Column(name = "recipe", length = 5000)
    @NotNull
    private String recipe;

    @Column(name = "drink_type")
    @NotNull
    private String drinkType;

    @Column(name = "glass_type")
    @NotNull
    private String glassType;

    @Column(name = "date_of_modification")
    @NotNull
    private String modificationDate;

    @Column(name = "image_url", length = 1024)
    @NotNull
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable( name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "drink_to_ingredient",
            joinColumns = {@JoinColumn(name = "drink_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id",referencedColumnName = "id")}
    )
    private List<Ingredient> ingredientList = new ArrayList<>();

    @ManyToMany(mappedBy = "drinkList")
    private List<User> users = new ArrayList<>();

    public Drink() {
    }

    public Drink(@NotNull String name, @NotNull String recipe, @NotNull String drinkType, @NotNull String glassType) {
        this.name = name;
        this.recipe = recipe;
        this.drinkType = drinkType;
        this.glassType = glassType;

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

    public Boolean getCustom() {
        return isCustom;
    }

    public void setCustom(Boolean custom) {
        isCustom = custom;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getGlassType() {
        return glassType;
    }

    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
