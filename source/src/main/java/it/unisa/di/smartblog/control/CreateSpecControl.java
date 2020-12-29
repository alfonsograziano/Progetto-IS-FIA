package it.unisa.di.smartblog.control;

import it.unisa.di.smartblog.filter.Error;
import it.unisa.di.smartblog.filter.Message;
import it.unisa.di.smartblog.spec.SpecMismatchException;
import it.unisa.di.smartblog.spec.SpecsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CreateSpecControl",
        value="/api/spec/add")
public class CreateSpecControl extends HttpServlet {
    static{
        sm = new SpecsManager();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int battery = -1;
        double price = -1;
        try{
            battery = Integer.parseInt(request.getParameter("battery"));
        }catch (NumberFormatException e){

        }

        try{
            price = Integer.parseInt(request.getParameter("price"));
        }catch (NumberFormatException e){

        }

        try {
            sm.createSpec(
                    request.getParameter("deviceName"),
                    request.getParameter("releaseDate"),
                    request.getParameter("image"),
                    request.getParameter("OS"),
                    request.getParameter("CPU"),
                    request.getParameter("chipset"),
                    request.getParameter("GPU"),
                    request.getParameter("RAM"),
                    request.getParameter("internalMemory"),
                    request.getParameter("displayInches"),
                    battery,
                    price
            );
            request.setAttribute("response", new Message("Done!"));
        } catch (SpecMismatchException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error(e.getMessage()));
        }  catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Wrong params"));
        }
    }

    private static SpecsManager sm;
}
