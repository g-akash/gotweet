

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Functions;				// name of file to be written ....

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
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
		String id1 = request.getParameter("id");		// the sender
		String id2 = request.getParameter("id1");		// the receiver
		String body = request.getParameter("body");		
		if(id1==null || id2==null || body==null){
			// redirect to invalid credentials, 
			// invalid_credentials.html
		}
		else{
			if(body.length() > 160){
				response.sendRedirect("invalid_credentials.html");
				// body length should be lesser :(
			}
			else{
				Functions.sendmessage(id1, id2, body);
				response.sendRedirect("message.jsp");
				// redirect to the home page of the message page!!!
			}
		}
	}

}
