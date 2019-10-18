package Bus;

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
 * Servlet implementation class MemberCAServlet
 */
@WebServlet(name = "MemberCA", urlPatterns = { "/MemberCA" })
public class MemberCAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<meta charset=\"utf-8\"><title>webpro12</title>");
		out.println("<link rel='stylesheet' type='text/css' href='regist.css'></head>");
        out.println("<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\"></head>");
        out.println("<body>");
		out.println("<form action='MemberCA' method = 'post'>");
        out.println("<header>");
        out.println("</header>");
        out.println("<main>");
        out.println("<div id=\"main_content\">");
        out.println("<div id=\"regist\">");
        out.println("회원가입<hr style=\"width:500px\">");
        out.println("</div>");
		out.println("이름 <input type = 'text' name='CUSRNAME'><br>");
		out.println("아이디 <input type = 'id' name='IDENTIFY'><br>");
		out.println("비밀번호 <input type = 'password' name='CUSPASSWORD'><br>");
		out.println("핸드폰번호 <input type = 'text' name='CUSPHONE'><br>");
		out.println("<input type = 'submit' value='회원가입'>");
		out.println("<a href='BusTimetableMain'><input type='button' value='가입취소'</a><br>");
        out.println("</div>");
        out.println("</div>");
        out.println("</main>");
        out.println("</body></html>");
     
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
	    	        "INSERT INTO CustomerManagement (CUSRNAME, IDENTIFY, CUSPASSWORD, CUSPHONE)"
	    	        + " VALUES (?,?,?,?)");
			stmt.setString(1,  request.getParameter("CUSRNAME"));
			stmt.setString(2,  request.getParameter("IDENTIFY"));
			stmt.setString(3,  request.getParameter("CUSPASSWORD"));
	        stmt.setInt(4, Integer.parseInt(request.getParameter("CUSPHONE")));
			stmt.executeUpdate();

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원등록결과</title></head>");
			out.println("<body><p>등록 성공입니다!</p></body></html>");
			
			response.setHeader("Refresh", "1;url=BusTimetableMain2");
			
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try { if (stmt != null) stmt.close(); } catch(Exception e) {}
			try { if (conn != null) conn.close(); } catch(Exception e) {}
		}
	}

}
