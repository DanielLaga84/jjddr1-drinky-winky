package com.infoshare.servlet;

import com.infoshare.dto.DrinkDTO;
import com.infoshare.dto.UserDTO;
import com.infoshare.model.Drink;
import com.infoshare.model.User;
import com.infoshare.service.DrinkService;
import com.infoshare.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Add-favourite")
public class AddFavouriteServlet extends HttpServlet {

    @Inject
    DrinkService drinkService;
    @Inject
    UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");

        DrinkDTO drinkByName = drinkService.getDrinkByName(name);

        UserDTO userById = userService.getUserById(1L);
//        userById.addFavouriteDrink(drinkByName);
        userById.setName("Siara2");

        userService.updateUser(userById, 1L);

    }
}
