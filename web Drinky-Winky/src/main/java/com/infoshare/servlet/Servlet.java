package com.infoshareacademy.servlet;


import com.infoshare.model.Drink;
import com.infoshare.service.FileDataHandler;
import com.infoshare.service.RecipeUploadedFileNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/json-upload")
public class Servlet extends HttpServlet {

    @Inject
    private FileDataHandler fileDataHandler;
    Logger logger = LoggerFactory.getLogger(Servlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part jsonFile = req.getPart("drinks");
        String fileUrl = "";
        try {
            fileUrl = "/drinks/" + fileDataHandler.dataUploadHandler(jsonFile);
        } catch (RecipeUploadedFileNotFound recipeUploadedFileNotFound) {
            logger.info(recipeUploadedFileNotFound.getMessage());
        }
        Drink drink = new Drink();
        drink.setImageUrl(fileUrl);
        resp.getWriter().println("File successful uploaded!");
    }

}



