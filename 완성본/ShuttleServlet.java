

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShuttleServlet
 */
@WebServlet(name = "Shuttle", urlPatterns = { "/Shuttle" })
public class ShuttleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShuttleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01" );
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
					"SELECT * FROM HOSEOSHUTTLE where CHEONANSTART >= now() order by CHEONANSTART asc limit 10 ");
		
		
		out.println("<html>	<head>	<meta charset='utf-8'>");
		out.println("<title>셔틀정보</title>");
		out.println("<link rel='stylesheet' type='text/css' href='shuttle.css'>");
		out.println("</head><body>");
		out.println("<table cellspacing='0'>");
		out.println("<thead><tr><th>학교출발</th><th>천안터미널도착</th><th>천안역도착</th></tr>");
	    out.println("</thead>");
	    out.println("<tbody>");
		out.println("</tbody>");

		while(rs.next()) {
			out.println(
					"<tr><td>" + rs.getTime("CHEONANSTART")+ "</td>" +
					"<td>" + rs.getTime("CHEONANTERMINAL")+ "</td>" +
					"<td>" + rs.getTime("CHEONANSTATION")+ "</td>" 
					);
			
			
		}
		
		out.println("</table></body></html>");
		
		
	} catch (Exception e) {
		throw new ServletException(e);
} finally {
	try {if (rs !=null) rs.close();} catch(Exception e) {}
	try {if (stmt !=null) stmt.close();} catch(Exception e) {}
	try {if (conn !=null) conn.close();} catch(Exception e) {}
	}

	}

	
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
