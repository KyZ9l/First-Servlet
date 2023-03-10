package com.dmdev.http.servlet;

import com.dmdev.http.servise.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private final ImageService imageService =ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var uri = req.getRequestURI();

       var imagePath=uri.replace("/images", "");

       imageService.get(imagePath)
               .ifPresentOrElse(image -> {
                   resp.setContentType("application/octet-stream");
                   writeImage(image,resp);
               },  ()->resp.setStatus(404) );

    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image; var outputStream = resp.getOutputStream()) {
            int currentBy;
            while ((currentBy=image.read())!=-1)
            {
                 outputStream.write(currentBy);
            }
        }
    }
}
