package Bus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TTUpdateServlet
 */
@WebServlet(name = "TTUpdate", urlPatterns = { "/TTUpdate" })
public class TTUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TTUpdateServlet() {
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
		int pno;
		 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		pno = Integer.parseInt(request.getParameter("pno"));
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javawebdb", "javaweb", "javaweb");
		    stmt = conn.createStatement();
			sql =  "SELECT PNO,STARTTIME"
			        + " FROM INTERCITYBUS WHERE PNO=" + pno ;
			rs = stmt.executeQuery(sql);
			
		    if (rs.next()) {
				out.println("<html><head><title>상품정보수정</title></head>");
				out.println("<body><h1>상품정보수정</h1>");
				out.println("<form action='TTUpdate' method = 'post'>");
				out.println("번호: <input type = 'text' name='pno' value='"+pno+"' readonly><br>");
				out.println("상품명: <input type = 'text' name='STARTTIME' value='"+rs.getTime("STARTTIME")+"'><br>");
				out.println("<input type = 'submit' value='수정'>");
				out.println("<input type = 'reset' value='취소'>");
				out.println("<input type = 'button' value='삭제' onclick='location.href=\"ProductDelete?pno="+pno+"\"'>");
				out.println("</form>");
				out.println("</body></html>");		
				
				
	        } else {
	           throw new Exception("해당 번호의 상품을 찾을 수 없습니다.");
	        }
			
		} catch(Exception e) {
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
		request.setCharacterEncoding("UTF-8");
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javawebdb", "javaweb", "javaweb");
		    stmt = conn.prepareStatement(
		        "UPDATE INTERCITYBUS SET "
		        + " STARTTIME=?,"
		    		);
	        stmt.setInt(1, Integer.parseInt(request.getParameter("STARTTIME")));
			stmt.executeUpdate();

			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>상품정보 수정결과</title></head>");
			out.println("<body><p>정보수정 성공입니다! 잠시 후 상품 목록 화면으로 이동합니다.</p></body></html>");
			
			response.setHeader("Refresh", "1;url=ProductList");
			
	    } catch (Exception e) {
	      throw new ServletException(e);
	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (conn != null) conn.close();} catch(Exception e) {}
	    }
	
	}

}
