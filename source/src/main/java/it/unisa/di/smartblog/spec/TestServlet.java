package  it.unisa.di.smartblog.spec;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	static {
		d = new SpecDao();
	}
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Spec s = (Spec) d.getById(2041);
	        System.out.println(s);
	        
	        boolean a = d.saveSpec(new Spec("antonio", "ciao", "si sono io", "corpo", "cervello", "neurone", "occhi", "poca", "poca", "mh", 1000, 1000));
	        System.out.println(a+" ");
	        
	        //boolean b = d.updateSpecScores(1, d.getByName("antonio").get(0).getId(), 5, 5, 5);
	        
	        //System.out.println(b+" ");
	        //boolean c = d.deleteSpec(d.getByName("antonio").get(0).getId());
	        
	        //System.out.println(a+" "+b+" "+c);
	        
	        int max = d.getMaxBattery();
	        int min = d.getMinBattery();
	        
	        System.out.println("max: " +max+", min: "+min);
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static SpecDao d;
}
