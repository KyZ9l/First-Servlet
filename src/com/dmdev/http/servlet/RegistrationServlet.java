package com.dmdev.http.servlet;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.exaption.ValidationException;
import com.dmdev.http.servise.UserService;
import com.dmdev.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("roles", List.of("USER", "ADMIN"));
        req.setAttribute("genders", List.of("MALE", "FEMALE"));
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .image(req.getPart("image"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();


        try {

            userService.create(userDto);
            resp.sendRedirect("/login");
        }catch (ValidationException e)
        {
            req.setAttribute("errors", e.getErrors());
            doGet(req,resp);
        }


    }
}
