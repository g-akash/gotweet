import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Functions;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
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
		String body = request.getParameter("body");		// imp 
		
		if(id==null || tid_s == null || body==null){
			response.sendRedirect("invalid_credentials.html");
			// incorrect credentials
		}
		else{
			int tid = Integer.parseInt(tid_s);
			Functions.addcomment(id,tid,body);
			HttpSession session = request.getSession(false);
			//save message in session
			session.setAttribute("uid", id);
			response.sendRedirect("comment.jsp?twid="+tid_s);
			// redirect to the home page of the user /newsfeed page
		}
	}
}
