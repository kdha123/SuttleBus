


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
 * Servlet implementation class ProductListServlet
 */
@WebServlet(name = "Intercitybus", urlPatterns = { "/Intercitybus" })
public class IntercitybusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn=null;
		Statement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM intercitybus where STARTTIME >= now() order by STARTTIME asc limit 6");


		out.println("<html><head><title>시외버스시간표</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"inter.css\">\r\n" + 
				"<link href=\"https://fonts.googleapis.com/css?family=Nanum+Gothic\" rel=\"stylesheet\">\r\n" + 
				"<link href=\"https://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\">\r\n" + 
				"</head>");
		out.println("<body>\r\n" + 
				"    <div class=\"header\">\r\n" + 
				"        <div class=\"header_content\">\r\n" + 
				"                <span style=\"color: white; float: right; padding:10px; font-size: 0.8em; margin-right: 50px;\">\r\n" + 
				"                    <span style=\"font-size: 1px;\" > > </span><a href=\"animation.html\"> Home</a>\r\n" + 
				"                    <span style=\"font-size: 1px;\" > ></span> <a href=\"CustomerList\">Regsist</a> </span>\r\n" + 
				"            <h2 style=\"color: white; font-family: 'Raleway', sans-serif;\">Bus</h2>\r\n" + 
				"        </div>\r\n" + 
				"    <div class=\"main\">\r\n" + 
				"        <div class=\"main_content\">\r\n" + 
				"                <h2>배차조회</h2>\r\n" + 
				"                <div class=\"tabs\">\r\n" + 
				"                        <ul>\r\n" + 
				"                          <li class=\"active\"><a href=\"#\">조회</a></li>\r\n" + 
				"                        </ul>\r\n" + 
				"                </div><br><br>\r\n" + 
				"                      <table cellspacing='0'> <!-- cellspacing='0' is important, must stay -->\r\n" );
				out.println("<thead>");
				out.println("<tr>"
						+ "<th>버스출발시낙</th>"
						+ "<th>차편정보</th>"
						+ "<th>노선명</th>"
						+ "<th>어른요금</th>"
						+ "<th>아동요금</th>"
						+ "<th>중고생요금</th></tr>");
				
				out.println("</thead><!-- Table Header -->");
		  
				
				
				                    
				while(rs.next()) {
					out.println(
							"<tr><td>" + rs.getTime("STARTTIME")+ 
							"<td>" + rs.getString("BUSINFORMATION")+
							"<td>" + rs.getString("ROUTENAME")+
							"<td>" + rs.getInt("ADULTFEE")+
							"<td>" + rs.getInt("CHILDFEE")+
							"<td>" + rs.getInt("STUDENTFEE")+"</td>" 
							);
						
				}
				out.println("</table></body></html>");
				
	
		}catch(Exception e){
			throw new ServletException(e);
			
		}
		finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

}
