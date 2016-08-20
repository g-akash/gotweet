

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.Functions;

/**
 * Servlet implementation class Changepassword
 */
@WebServlet("/Changepassword")
public class Changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Changepassword() {
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
		System.out.println("change pwd");
		String id = request.getParameter("id");
		String pwd  =request.getParameter("pwd");
		String pwd1 =request.getParameter("pwd1");
		String pwd2 =request.getParameter("pwd2");
		boolean match = Functions.matchpass(id,pwd);
		if(!match){
			response.sendRedirect("errors.jsp?num=9");
		}
		else if(pwd1.equals("") || pwd2.equals("")){
			response.sendRedirect("errors.jsp?num=4");
		}
		else if(!pwd1.equals(pwd2)){
			response.sendRedirect("errors.jsp?num=6");
		}
		else{
			Functions.changepass(id,pwd1);
			response.sendRedirect("login.jsp");   // go to the login page.
		}
	}

}
