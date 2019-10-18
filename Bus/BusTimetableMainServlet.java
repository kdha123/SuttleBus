package Bus;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BusTimetableMainServlet
 */
@WebServlet(name = "BusTimetableMain", urlPatterns = { "/BusTimetableMain" })
public class BusTimetableMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusTimetableMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>버스시간표</title>");
		out.println("<style>h1 {color:blue}");
		out.println("</style></head><body>");
		out.println("<h1>버스시간표</h1>");
		out.println("<form action='BusTimetable' method = 'post'>");
		out.println("<a href='MemberCA'>로그인/회원가입</a><br>");
		out.println("<form action= 'BusTimetable' method='post'>");
		out.println("<a href='RealTerm'><input type='button' value='시외버스'</a>");
		out.println("<a href='Terminal'><input type='button'' value='천안역'</a><br>");
		out.println("<select name='op'>");
		out.println("<table border='1'>");
		out.println	("<tr><th>시간</th>");
		out.println("<a href='Ansan'>안산</a>");
		out.println("수원");
		out.println("서울");
		out.println("<option value='안산'>안산 </option>");
		out.println("<option value='수원'>수원 </option>");
		out.println("<option value='서울'>서울 </option>");
		out.println("</select><br>");
		out.println("<input type='submit' value='조회'>");
		out.println("</form>");
		out.println("</body></html>");


	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
}
	
}