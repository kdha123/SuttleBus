

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
 * Servlet implementation class ProductListServelt
 */
@WebServlet(name = "CustomerList", urlPatterns = { "/CustomerList" })
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerListServlet() {
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
		String sql = null;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01");
			
			stmt = conn.createStatement();
			sql = "SELECT PNO,CUSID, CUSPASSWORD, CRE_DATE FROM customermanage";
			
			rs = stmt.executeQuery(sql);
			
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body>");
			out.println("<h2>회원 목록</h2>");
			out.println("<a href='test.html'>[회원가입]</a><br><hr>");
			while (rs.next()) {
				out.println(
						rs.getInt("PNO") + ", <a href='CustomerUpdate?pno=" +rs.getInt("PNO")+"'>" +
						rs.getString("CUSID") + "</a>," +
						rs.getString("CUSPASSWORD") + "," +
						rs.getDate("CRE_DATE") + "<br>");
			}
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try { if (rs != null) rs.close(); } catch(Exception e) {}
			try { if (stmt != null) stmt.close(); } catch(Exception e) {}
			try { if (conn != null) conn.close(); } catch(Exception e) {}
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
