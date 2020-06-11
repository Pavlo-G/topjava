package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealsDB;

import java.util.List;

public class MealsDAOImpl implements ElementDAO<Meal,Integer>{
    private MealsDB mealsDB;


    public MealsDAOImpl(MealsDB mealsDB) {
        this.mealsDB = mealsDB;
    }


    @Override
    public Meal getEntityById(Integer id) {
        return  mealsDB.getByID(id);
    }

    @Override
    public void update(Meal entity) {
        mealsDB.update(entity);
    }

    @Override
    public boolean delete(Integer id) {

        return  mealsDB.delete(id);
    }

    @Override
    public boolean create(Meal entity) {
        return  mealsDB.addNew(entity);
    }

    @Override
    public List<Meal> getAll() {
        return mealsDB.getAll();
    }








}

