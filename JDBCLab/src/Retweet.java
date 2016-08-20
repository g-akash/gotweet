import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import database.Functions;

/**
 * Servlet implementation class Retweet
 */
@WebServlet("/Retweet")
public class Retweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Retweet() {
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
		String tid_s = request.getParameter("tid");		// imp 
		if(id==null || tid_s == null){
			response.sendRedirect("errors.jsp?num=4");
			// redirect to invalid credentials page
		}
		else{
			int tid = Integer.parseInt(request.getParameter("tid"));		// imp 
			// redirect to the home page of the user /newsfeed page
			System.out.println("came in else");
			boolean isretweeted = Functions.isretweeted(tid, id);
			if(isretweeted == false){
				Functions.retweet(tid,id);
			}
//			HttpSession session = request.getSession(false);
			//save message in session
//			session.setAttribute("id", id);
			response.sendRedirect("homepage.jsp");			//redirect it to the incorrect_pwd.html page
		}
	}
}
