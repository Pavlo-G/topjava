package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;


import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println("--------------------");
        System.out.println(filteredByStreamsOp2(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(
            List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExcess> resultList = new ArrayList<>();
        Map<LocalDate, Integer> map = new HashMap<>();

        for (UserMeal userMeal : meals) {
            LocalDate currentDay = userMeal.getDateTime().toLocalDate();
            map.put(currentDay, map.get(currentDay) == null ? userMeal.getCalories() : map.get(currentDay) + userMeal.getCalories());
        }

        for (UserMeal userMeal : meals) {
            LocalDate currentDay = userMeal.getDateTime().toLocalDate();
            boolean excess = map.get(currentDay) > caloriesPerDay;
            if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                resultList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), excess));

            }
        }
        return resultList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime
            startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapCaloriesPerDay =
                meals.stream()
                        .collect(Collectors.toMap(userMeal -> userMeal.getDateTime().toLocalDate(),
                                UserMeal::getCalories, Integer::sum));

        return meals.stream()
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        mapCaloriesPerDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExcess> filteredByStreamsOp2(List<UserMeal> meals, LocalTime
            startTime, LocalTime endTime, int caloriesPerDay) {
        class Holder<A, B> {
            ArrayList<UserMeal> list;
            HashMap<LocalDate, Integer> map;

            public Holder(ArrayList<UserMeal> list, HashMap<LocalDate, Integer> map) {
                this.list = list;
                this.map = map;
            }
        }

        return meals.stream().collect(Collector.of(
                //supplier
                () ->
                    new Holder<ArrayList<UserMeal>, HashMap<LocalDate, Integer>>(
                            new ArrayList<>(),
                            new HashMap<>())
                ,
                //biConsumer
                (holder,userMeal) -> {
                    if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                       holder.list.add(userMeal);
                    }
                   holder.map.merge(userMeal.getDate(), userMeal.getCalories(), Integer::sum);
                },
                //BinaryOperator
                (t1, t2) -> {
                    t1.list.addAll(t2.list);
                    t1.map.putAll(t2.map);
                    return t1;
                },
                //Function
                t -> {
                    List<UserMealWithExcess> result = new ArrayList<>();
                    for (UserMeal meal : t.list) {
                        result.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                                t.map.get(meal.getDate()) > caloriesPerDay));
                    }
                    return result;
                }));

    }

}

