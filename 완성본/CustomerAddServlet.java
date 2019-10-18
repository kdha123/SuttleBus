
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductAddServlet
 */
@WebServlet(name = "CustomerAdd", urlPatterns = { "/test" })
public class  CustomerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01");
		    stmt = conn.prepareStatement(
	    	        "INSERT INTO customermanage (CUSID,CUSPASSWORD,CRE_DATE)"
	    	        + " VALUES (?,?, NOW())");
			stmt.setString(1,  request.getParameter("CUSID"));
			stmt.setString(2,  request.getParameter("CUSPASSWORD"));
			stmt.executeUpdate();

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>상품등록결과</title></head>");
			
			
			response.setHeader("Refresh", "0.2;url=test.html");
			
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try { if (stmt != null) stmt.close(); } catch(Exception e) {}
			try { if (conn != null) conn.close(); } catch(Exception e) {}
		}
	}


}
