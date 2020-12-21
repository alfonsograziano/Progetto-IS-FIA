package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.dora.DoraManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/dora")
public class DoraControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoraManager dm = new DoraManager();
        System.out.println("Battery => "+request.getParameter("battery"));
        try {
            double battery = Double.parseDouble(request.getParameter("battery"));
            double performance = Double.parseDouble(request.getParameter("performance"));
            double camera = Double.parseDouble(request.getParameter("camera"));
            double display = Double.parseDouble(request.getParameter("display"));
            int maxBudget = Integer.parseInt(request.getParameter("maxBudget"));

            System.out.println("Battery => "+battery);

            request.setAttribute("response",dm.findSpectByParams(battery,performance,camera,display,maxBudget));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
