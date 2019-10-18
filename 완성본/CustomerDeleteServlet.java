
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet(name = "CustomerDelete", urlPatterns = { "/CustomerDelete" })
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int pno;
		pno = Integer.parseInt(request.getParameter("pno"));
		
	    Connection conn = null;
		Statement stmt = null;
		String sql = null;
				
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01");
		    stmt = conn.createStatement();
		    sql =  "DELETE FROM customermanage WHERE PNO=" + pno;
			stmt.executeUpdate(sql);

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원 삭제 결과</title></head>");
			out.println("<body><p>회원 삭제 성공입니다! 잠시 후 메인화면으로 이동합니다.</p></body></html>");
			
			response.setHeader("Refresh", "1;url=CustomerList");
			
	    } catch (Exception e) {
	      throw new ServletException(e);
	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (conn != null) conn.close();} catch(Exception e) {}
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
