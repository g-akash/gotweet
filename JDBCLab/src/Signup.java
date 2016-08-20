import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.InputStream; 

import database.Functions;				// name of file to be written...
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.*;
//import javax.servlet.http.Part;
/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		String pwd1 = request.getParameter("pwd1");		// imp
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		System.out.println(dob+" is the date1");
		//save message in session
		if(id==null || pwd==null || name==null || email==null || dob==null || id=="" || pwd=="" || name=="" || email=="" || dob==""){
			// redirect to invalid credentials.
			response.sendRedirect("errors.jsp?num=4");
		}
		else if(!pwd.equals(pwd1)){
			response.sendRedirect("errors.jsp?num=6");
		}
		else{
			boolean ispresent = Functions.userid_present(id);
			if(ispresent){
				response.sendRedirect("errors.jsp?num=2");
			}
			else{
				Functions.adduser(id, name, pwd, dob, email);
				response.sendRedirect("login.jsp");   // go to the login page.
			}
		}
		// assuming the same variables for username and password as is login		
	}
}