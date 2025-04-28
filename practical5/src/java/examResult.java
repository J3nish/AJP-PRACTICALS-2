import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/examResult"})
public class examResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve marks from request parameters
        int m1 = Integer.parseInt(request.getParameter("subj1"));
        int m2 = Integer.parseInt(request.getParameter("subj2"));
        int m3 = Integer.parseInt(request.getParameter("subj3"));
        int m4 = Integer.parseInt(request.getParameter("subj4"));
        int m5 = Integer.parseInt(request.getParameter("subj5"));
        int m6 = Integer.parseInt(request.getParameter("subj6"));

        // Calculate average and grade
        float avg = (m1 + m2 + m3 + m4 + m5 + m6) / 6.0f;
        char grade = (avg >= 90) ? 'A' : (avg >= 70) ? 'B' : (avg >= 60) ? 'C' : 'F';

        // Retrieve cookies
        String name = "Unknown", id = "Unknown", dept = "Unknown";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                switch (c.getName()) {
                    case "nm":
                        name = c.getValue();
                        break;
                    case "id":
                        id = c.getValue();
                        break;
                    case "dept":
                        dept = c.getValue();
                        break;
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exam Result</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Student Details</h1>");
            out.println("<p><strong>Name:</strong> " + name + "</p>");
            out.println("<p><strong>ID:</strong> " + id + "</p>");
            out.println("<p><strong>Department:</strong> " + dept + "</p>");
            out.println("<h2>Exam Result: " + grade + "</h2>");
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
        return "Exam Result Servlet";
    }
}
