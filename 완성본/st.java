

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
 * Servlet implementation class st
 */
@WebServlet("/st")
public class st extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public st() {
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
					"SELECT * FROM CHEONANSTATION where STARTTIME >= now() order by STARTTIME asc limit 3 ");
				
		
		out.println("<html>	<head>	<meta charset='utf-8'>");
		out.println("<title>천안역시간표</title>");

		out.println("<link rel='stylesheet' type='text/css' href='station.css'>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Nanum+Gothic' rel='stylesheet'>");
		out.println("<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>");
		out.println("</head><body>");
		out.println("<div class='header'>");
		out.println("<div class='header_content'>");
		out.println("<span style='color: white; float: right; padding:10px; font-size: 0.8em; margin-right: 50px;'>");
		out.println("<span style='font-size: 1px;' > > </span><a href='animation.html'> Home</a></span>");
		out.println("<h2 style='color: white; font-family: 'Raleway', sans-serif;'>Station</h2>");
		out.println("</div>");

		out.println("<div class='main'><div class='main_content'>");
		out.println("<hr style='width: 200px;'>");
		out.println("<h2>배차조회</h2>");
		out.println("<div class='tabs'>");
		out.println("<ul><li class='active'><a href='#'>조회</a></li></ul>");
		out.println("</div><br><br><table cellspacing='0'> <!-- cellspacing='0' is important, must stay --><thead>");

		out.println("<thead>");
		out.println("<tr>"
				+ "<th>목적지</th>"
				+ "<th>출발시간</th>"
				+ "<th>도착시간</th></tr>");
		
		out.println("</thead><!-- Table Header -->");
  
		
		
		                    
		while(rs.next()) {
			out.println(
					"<tr><td>" + rs.getString("ROUTENAME")+ 
					"<td>" + rs.getTime("STARTTIME")+ 
					"<td>" + rs.getTime("ARRIVALTIME")+ "</td>" 
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
