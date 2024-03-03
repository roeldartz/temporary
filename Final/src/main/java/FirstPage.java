
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class EditVaccine
 */
@WebServlet(urlPatterns={"/FirstPage", "/Final/FirstPage"})
public class FirstPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstPage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private Users getEntry(int userid) {
    	@SuppressWarnings("unchecked")
		List<Users> entries = (List<Users>) getServletContext().getAttribute("userentries");
		for(Users entry: entries)
			if(entry.getId() == userid) { return entry;
			}
		return null;
    }
    
    protected void doGet( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
        DbService dbService = new DbService();
        List<Users> userentries = dbService.getUsers();
        getServletContext().setAttribute( "userentries", userentries );
        List<BloodPressure> bloodpressureentries = dbService.getBloodpressure();
        for (int i = 0; i < bloodpressureentries.size(); i++) {
        	bloodpressureentries.get(i).setUsername(getEntry(bloodpressureentries.get(i).getId()));
        }
        getServletContext().setAttribute( "bloodpressureentries", bloodpressureentries );
        request.getRequestDispatcher( "/WEB-INF/FirstPage.jsp" )
            .forward( request, response );
        dbService.close();
    }

    protected void doPost( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
    	String name = request.getParameter("name");

		response.sendRedirect("SecondPage?name=" + name);
        }

}
