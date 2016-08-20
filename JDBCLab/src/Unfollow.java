

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Functions;

/**
 * Servlet implementation class Unfollow
 */
@WebServlet("/Unfollow")
public class Unfollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unfollow() {
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
		String id1 = request.getParameter("id");		// imp 
		String id2 = request.getParameter("id1");		// imp 
		if(id1==null || id2==null || id1=="" || id2==""){
			response.sendRedirect("errors.jsp?num=4");
//			invalid credentials
		}
		else if(id1==id2){
			response.sendRedirect("errors.jsp?num=8");
			// following the same person!!, not possible
		}
		else{
			if(Functions.isfollow(id1, id2))
				Functions.unfollow(id1,id2);
				HttpSession session = request.getSession(false);
			//save message in session
				session.setAttribute("uid", id1);
				response.sendRedirect("profilepage.jsp?id1="+id2);
			// redirect to the home page of the user./newsfeed page
		}
	}

}
