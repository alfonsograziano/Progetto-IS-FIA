package it.unisa.di.smartblog;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
     //Do something
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("email@email.com","password"));
        users.add(new User("email@email.com","password"));
        users.add(new User("email@email.com","password"));
        users.add(new User("email@email.com","password"));

        request.setAttribute("response", users);
    }


    public void destroy() {
    }
}