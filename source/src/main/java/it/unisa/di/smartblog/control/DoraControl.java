package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.dora.DoraManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name="DoraControl",
        value="/api/dora")
public class DoraControl extends HttpServlet {
    static{
        dm = new DoraManager();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Battery => "+request.getParameter("battery"));
        try {
            double battery = Double.parseDouble(request.getParameter("battery"))*2;
            double performance = Double.parseDouble(request.getParameter("performance"))*2;
            double camera = Double.parseDouble(request.getParameter("camera"))*2;
            double display = Double.parseDouble(request.getParameter("display"))*2;
            int maxBudget = Integer.parseInt(request.getParameter("maxBudget"));

            System.out.println("Battery => "+battery);

            request.setAttribute("response",dm.findSpectByParams(battery,performance,camera,display,maxBudget));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static DoraManager dm;
}
