package com.example.thewitsapp;

import com.bumptech.glide.Glide;

public class ModelFood {

    private int image;
    private String image0;
    private String ingredients,name,place,price,recipe;

//    public ModelFood(int image, String name, String place, String price, String ingredients, String recipe) {
//
//        this.image = image;
//        this.recipe = recipe;
//        this.name = name;
//        this.place = place;
//        this.price = price;
//        this.ingredients = ingredients;
//    }

    public ModelFood(String image, String name, String place, String price, String ingredients, String recipe) {

        this.image0 = image;
        this.recipe = recipe;
        this.name = name;
        this.place = place;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getRecipe() { return recipe; }

    public void setRecipe(String recipe) { this.recipe = recipe; }

    public String getImage() {
        return image0;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
