

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
	    PreparedStatement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01", "webpro2upw01");
			
			
		    stmt = conn.prepareStatement(
		              "SELECT CUSID, CUSPASSWORD FROM customermanage"
		                  + " WHERE CUSID=? AND CUSPASSWORD=?");
		    stmt.setString(1, request.getParameter("CUSID"));
		    stmt.setString(2, request.getParameter("CUSPASSWORD"));
		    rs = stmt.executeQuery();
		    
			out.println("<html><head><title>로그인 결과</title>");
			out.println("</head>");
			out.println("<body>");
	    
		    if (rs.next()) {
		    	
		    	    // 로그인 성공 했을 때 코드를 작성하는 곳 
				out.println("<h2>로그인 성공!</h2>");
				out.println(				
		              rs.getString("CUSID") + "," +
		              rs.getString("CUSPASSWORD"));
				out.println("</body></html>");
				response.setHeader("Refresh", "1;url=logout.html");
				
		    } else {
		    	
	    	        // 로그인 실패 했을 때 코드를 작성하는 곳  
				out.println("<p>로그인 실패입니다. 아이디 또는 암호가 맞지 않습니다!<br>\n" + 
						"잠시 후에 다시 로그인 화면으로 이동합니다.</p>");
				out.println("</body></html>");
				response.setHeader("Refresh", "1;url=test.html");
				
				
		    }	
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try { if (rs != null) rs.close(); } catch(Exception e) {}
			try { if (stmt != null) stmt.close(); } catch(Exception e) {}
			try { if (conn != null) conn.close(); } catch(Exception e) {}
		}
	}
}
