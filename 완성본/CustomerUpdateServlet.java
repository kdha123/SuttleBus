

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
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet(name = "CustomerUpdate", urlPatterns = { "/CustomerUpdate" })
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateServlet() {
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
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01");
		    stmt = conn.createStatement();
			sql =  "SELECT PNO,CUSID,CUSPASSWORD,CRE_DATE"
			        + " FROM customermanage WHERE PNO=" + pno ;
			rs = stmt.executeQuery(sql);
			
		    if (rs.next()) {
				out.println("<html><head><title>상품정보수정</title></head>");
				out.println("<body><h1>회원정보수정</h1>");
				out.println("<form action='Customer Update' method = 'post'>");
				out.println("회원번호: <input type = 'text' name='pno' value='"+pno+"' readonly><br>");
				out.println("아이디: <input type = 'text' name='cusid' value='"+rs.getString("CUSID")+"'><br>");
				out.println("비밀번호: <input type = 'text' name='cuspassword' value='"+rs.getString("CUSPASSWORD")+"'><br>");	
				out.println("<input type = 'submit' value='수정'>");
				out.println("<input type = 'reset' value='취소'>");
				out.println("<input type = 'button' value='삭제' onclick='location.href=\"CustomerDelete?pno="+pno+"\"'>");
				out.println("</form>");
				out.println("</body></html>");			
	        } else {
	           throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
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
			conn = DriverManager.getConnection("jdbc:mysql://modt.thehoseo.com:9136/javaweb2018team01db", "javaweb2018team01","webpro2upw01");
		    stmt = conn.prepareStatement(
		        "UPDATE customermanage SET "
		        + " CUSID=?,"
		        + " CUSPASSWORD=?,"
		        + " CRE_DATE=now()"
		        + " WHERE PNO=?");
			stmt.setString(1,  request.getParameter("cusid"));
			stmt.setString(2,  request.getParameter("cuspassword"));
	        stmt.setInt(3, Integer.parseInt(request.getParameter("pno")));
			stmt.executeUpdate();

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원정보 수정결과</title></head>");
			out.println("<body><p>회원수정 성공입니다! 잠시 후 회원 목록 화면으로 이동합니다.</p></body></html>");
			
			response.setHeader("Refresh", "1;url=CustomerList");
			
	    } catch (Exception e) {
	      throw new ServletException(e);
	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (conn != null) conn.close();} catch(Exception e) {}
	    }
	
	}

}
