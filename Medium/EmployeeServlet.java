package Medium;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;

public class EmployeeServlet extends HttpServlet {
    private Connection connection;

    public void init() {
        try {
            Properties props = new Properties();
            InputStream in = getServletContext().getResourceAsStream("/WEB-INF/db-config.properties");
            props.load(in);

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        
        try {
            String query = (id == null || id.isEmpty()) ? 
                "SELECT * FROM employees" : 
                "SELECT * FROM employees WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            if (id != null && !id.isEmpty()) {
                stmt.setInt(1, Integer.parseInt(id));
            }
            ResultSet rs = stmt.executeQuery();

            out.println("<h2>Employee List</h2>");
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + ", ");
                out.println("Name: " + rs.getString("name") + ", ");
                out.println("Department: " + rs.getString("department") + ", ");
                out.println("Email: " + rs.getString("email") + "<br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
