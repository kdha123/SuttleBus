package Bus;

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


@WebServlet("/Terminal")
public class TerminalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/javawebdb", "javaweb" , "javaweb" );
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
					"SELECT * FROM CHEONANSTATION where STARTTIME >= now() order by STARTTIME asc limit 3 ");
				
				out.println("<html><head><title>천안역시간표</title></head>");
				out.println("<body><h1>천안역시간표</h1>");
				out.println("<a href='ProductAdd'>[시간표추가]");
				out.println("<br>");
				out.println("<table border='1'>");
				out.println
				
			("<tr><th>시간</th>");
			
				
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


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}

}

