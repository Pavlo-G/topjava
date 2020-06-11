package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDB {
    private List<Meal> meals = new CopyOnWriteArrayList<>();
    private AtomicInteger id = new AtomicInteger(0);

    {
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 15, 0), "Сур", 310, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 12, 20, 0), "Пельмени", 510, generateID()));
        addNew(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 24, 20, 0), "Ужин Торт", 750, generateID()));
    }


    public boolean addNew(Meal meal) {

        List<Meal> list = getAll();

        list.add(meal);
        setAll(list);
        return true;
    }

    public boolean delete(int mealId) {

        List<Meal> list = getAll();
       boolean rem =  list.removeIf(meal -> meal.getId() == mealId);
        setAll(list);
        return rem;
    }

    public Meal getByID(int mealId) {
        List<Meal> list = getAll();
        Meal meal = null;
        for (Meal meal1 : list) {
            if (meal1.getId() == mealId) {
                meal = meal1;
            }
        }
        return meal;
    }

    public void update(Meal meal) {
        List<Meal> list = getAll();
        Meal m = null;
        for (Meal e : list) {
            if (meal.getId() == e.getId()) {
                int index = list.indexOf(e);
                list.set(index, meal);

            }
        }

        setAll(list);
    }

    public List<Meal> getAll() {
        return meals;
    }

    public void setAll(List<Meal> meals) {
        this.meals = meals;
    }

    public int generateID() {
        return id.incrementAndGet();
    }

}
