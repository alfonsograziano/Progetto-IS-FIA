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
                    Integer.parseInt(request.getParameter("battery")),
                    Double.parseDouble(request.getParameter("price"))
            );
            request.setAttribute("response", new Message("Done!"));
        } catch (SpecMismatchException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Error, wrong params!"));
        }  catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("response", new Error("Error, something went wrong!"));
        }
    }

    private static SpecsManager sm;
}
