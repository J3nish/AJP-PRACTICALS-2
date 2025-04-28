import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/examInfo"})
public class examInfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nm = request.getParameter("name");
        String id = request.getParameter("id");
        String department = request.getParameter("department");

        // Create cookies
        Cookie c1 = new Cookie("nm", nm);
        Cookie c2 = new Cookie("id", id);
        Cookie c3 = new Cookie("dept", department);

        // Add cookies to response
        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exam Info</title>");
            out.println("</head>");
            out.println("<body>");  // Fixed missing body tag
            out.println("<form action=\"examResult\">");
            out.println("<h1>Student: " + nm + "</h1><br><hr>");
            out.println("<center><b>Enter marks for six subjects [out of 100]</b></center><br>");
            out.println("Enter marks for subject 1: <input type=\"text\" name=\"subj1\"><br><br>");
            out.println("Enter marks for subject 2: <input type=\"text\" name=\"subj2\"><br><br>");
            out.println("Enter marks for subject 3: <input type=\"text\" name=\"subj3\"><br><br>");
            out.println("Enter marks for subject 4: <input type=\"text\" name=\"subj4\"><br><br>");
            out.println("Enter marks for subject 5: <input type=\"text\" name=\"subj5\"><br><br>");
            out.println("Enter marks for subject 6: <input type=\"text\" name=\"subj6\"><br><br>");
            out.println("<input type=\"submit\" value=\"Generate Result\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Exam Info Servlet";
    }
}