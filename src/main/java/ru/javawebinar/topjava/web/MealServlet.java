package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealsDB;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealsDB mealsDB = new MealsDB();
    private MealsDAOImpl mealsDAOImpl = new MealsDAOImpl(mealsDB);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("buttonDelete") != null) {
            log.debug("delete position from meals");
           mealsDAOImpl.delete (Integer.parseInt(request.getParameter("id")));
          doGet(request, response);}

        if (request.getParameter("buttonFindById") != null) {
            log.debug("findById servlet ");
            Object ob = mealsDAOImpl.getEntityById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("elementById",ob);
            request.getRequestDispatcher("byid.jsp").forward(request, response);}

        if (request.getParameter("buttonAdd") != null) {
            log.debug("Add position to meals");
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            mealsDAOImpl.create(new Meal(dateTime,description,calories,mealsDB.generateID()));
            doGet(request, response);}

        if (request.getParameter("buttonUpdate") != null) {
            log.debug("Update position to meals");
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            int id =  Integer.parseInt(request.getParameter("id"));
            mealsDAOImpl.update(new Meal(dateTime,description,calories,id));
            doGet(request, response);}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        // response.sendRedirect("meals.jsp");


        List<MealTo> mealsTo = MealsUtil.filteredByStreams(mealsDAOImpl.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealsList", mealsTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
