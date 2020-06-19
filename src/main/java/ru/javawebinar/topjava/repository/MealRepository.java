package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal, int userId);

    // false if not found
    boolean delete(int id,int UsedId);

    // null if not found
    Meal get(int id,int UsedId);

    Collection<Meal> getAll(int UsedId);
}
