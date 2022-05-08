package com.example.smartdiary;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Date;

public class Meal implements Serializable {
    @Exclude
    private String key;
    public String meal_name;
    public String meal_protien;
    public String meal_fat;
    public String meal_carbs;
    public String meal_calories;
    public String meal_description;
    public String meal_date;

    public Meal(){}

    public Meal( String meal_name, String meal_protien, String meal_fat, String meal_carbs, String meal_calories, String meal_description, String meal_date) {
        this.meal_name = meal_name;
        this.meal_protien = meal_protien;
        this.meal_fat = meal_fat;
        this.meal_carbs = meal_carbs;
        this.meal_calories = meal_calories;
        this.meal_description = meal_description;
        this.meal_date = meal_date;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getMeal_name() {
        return meal_name;
    }

    public String getMeal_protien() {
        return meal_protien;
    }

    public String getMeal_fat() {
        return meal_fat;
    }

    public String getMeal_carbs() {
        return meal_carbs;
    }

    public String getMeal_calories() {
        return meal_calories;
    }

    public String getMeal_description() {
        return meal_description;
    }

    public String getMeal_date() {
        return meal_date;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public void setMeal_protien(String meal_protien) {
        this.meal_protien = meal_protien;
    }

    public void setMeal_fat(String meal_fat) {
        this.meal_fat = meal_fat;
    }

    public void setMeal_carbs(String meal_carbs) {
        this.meal_carbs = meal_carbs;
    }

    public void setMeal_calories(String meal_calories) {
        this.meal_calories = meal_calories;
    }

    public void setMeal_description(String meal_description) {
        this.meal_description = meal_description;
    }

    public void setMeal_date(String meal_date) {
        this.meal_date = meal_date;
    }



}
