

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Functions;

/**
 * Servlet implementation class Tweet
 */
@WebServlet("/Tweet")
public class Tweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tweet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");		// imp
		String body = request.getParameter("body");		// imp
		if(id==null || body==null){
			response.sendRedirect("invalid_credentials.html");
			// redirect to invalid credentials, 
			// invalid_credentials.html
		}
		else{
			if(body.length() > 140){
				response.sendRedirect("errors.jsp?num=7");
			}
			else{
				Functions.addtweet(id, body);
				response.sendRedirect("homepage.jsp");
				// redirect to the home page of the user/News feed
			}
		}
	}
}
