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

@WebServlet("/Authenticate")
public class AuthenticateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isAuthenticated = false;
        
        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/servlet"; // Check database name
        String dbUser = "root";
        String dbPassword = ""; // Update with your MySQL password if needed

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            
            // Prepare SQL query
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Execute query
            ResultSet rs = pstmt.executeQuery();
            isAuthenticated = rs.next();
            
            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error connecting to database!</h2>");
            return;
        }

        // Response based on authentication
        if (isAuthenticated) {
            out.println("<h1>Welcome, " + username + "!</h1>");
        } else {
            out.println("<h1>Invalid username or password!</h1>");
        }

        

        out.close();
    }
}
