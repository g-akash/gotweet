

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Functions;


//import javax.servlet.http.*;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String pwd = request.getParameter("pwd");		// imp
		if(id == null || pwd==null || id.equals("") || pwd.equals(""))
			response.sendRedirect("errors.jsp?num=4");
		boolean isvalid = Functions.checkUser(id, pwd);
		if(!isvalid){
			response.sendRedirect("errors.jsp?num=1");
		}
		else{
			HttpSession session = request.getSession(false);
			//save message in session
			session.setAttribute("id", id);
			response.sendRedirect("homepage.jsp");			//redirect it to the incorrect_pwd.html page
			// pass the username parameter to the user
			// redirect to the home page of the user.
		}		
	}

}
