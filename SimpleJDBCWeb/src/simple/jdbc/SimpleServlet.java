package simple.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
                
    public SimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                System.out.println("simple.jdbc.SimpleServlet.doGet()>");
                String sql = request.getParameter("sql");
                String operation = request.getParameter("operation");
        
                DataSource ds = null;
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                PreparedStatement ps = null;
                try {
                        InitialContext initCtx = new InitialContext();
                        ds = (DataSource) initCtx.lookup("jdbc/SimpleDS");
                        con = ds.getConnection();
                        stmt = con.createStatement();
                        ps = con.prepareStatement("DELETE  FROM EMP WHERE ID=?");
                        if(operation.equals("select")) {
                                rs = stmt.executeQuery(sql);
                                while(rs.next()){
                                        int id = rs.getInt("ID");
                                        String name = rs.getString("NAME");
                                        System.out.println("ID: " + id + ". Name: " + name);
                                }
                        }else if(operation.equals("update")) {
                                int i = stmt.executeUpdate(sql);
                                System.out.println(i + " record was updated.");
                        }else if(operation.equals("insert")){
                                int i = stmt.executeUpdate(sql);
                                System.out.println(i + " record was inserted.");
                        }else if(operation.equals("delete")){
                                int i = stmt.executeUpdate(sql);
                                System.out.println(i + " record was deleted.");
                                System.out.println("---");
                                ps.setInt(1, 4);
                                System.out.println("---");
                                ps.execute();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        try {
                                if (rs != null) rs.close();
                                if (stmt != null) stmt.close();
                                if (con != null) con.close();
                        } catch (SQLException e) {
                                        e.printStackTrace();
                        }
                }
                getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
                System.out.println("simple.jdbc.SimpleServlet.doGet()<");
        }


        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}