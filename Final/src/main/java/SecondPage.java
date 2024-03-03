
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
@WebServlet(urlPatterns={"/SecondPage", "/Final/SecondPage"})
public class SecondPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondPage() {
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
    
    private Users getEntryString(String name) {
    	@SuppressWarnings("unchecked")
		List<Users> entries = (List<Users>) getServletContext().getAttribute("userentries");
		for(Users entry: entries)
			if((entry.getName()).equals(name)) {
				return entry;
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
        	System.out.println(bloodpressureentries.get(i).getSystolic());
        }
        getServletContext().setAttribute( "bloodpressureentries", bloodpressureentries );
        request.getRequestDispatcher( "/WEB-INF/SecondPage.jsp" )
            .forward( request, response );
        dbService.close();
    }

    protected void doPost( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
    	Users entry = getEntryString(request.getParameter("name"));
		int userid = entry.getId();
		int systolic = Integer.parseInt(request.getParameter("systolic"));
		int diastolic = Integer.parseInt(request.getParameter("diastolic"));
		
        DbService dbService = new DbService();
        dbService.addBloodPressure(userid,systolic,diastolic);
        dbService.close();
		response.sendRedirect("SecondPage?name=" + request.getParameter("name"));
        }

}
