

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Functions;

/**
 * Servlet implementation class Like1
 */
@WebServlet("/Like1")
public class Like1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Like1() {
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
		String id = request.getParameter("id");		// imp 
		String id1 = request.getParameter("id1");		// jiska page hai
		String tid_s = request.getParameter("tid");		// imp 
		if(id==null || tid_s == null){
			response.sendRedirect("errors.jsp?num=4");
			// redirect to invalid credentials page
		}
		else{
			int tid = Integer.parseInt(request.getParameter("tid"));		// imp 
			boolean isliked = Functions.isliked(tid, id);
			if(isliked == false){
				System.out.println("never been like before");
				Functions.like(tid,id);         // the first person is only liking the page.
			}
				response.sendRedirect("profilepage.jsp?id1="+id1);
			// redirect to the home page of the user /newsFeed page
		}
	}

}
