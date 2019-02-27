package com.servlet_jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection;
    
    @Override
    public void init() throws ServletException {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "nisum");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public void destroy() {
    	try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		String sql= "update user set password = '"+ password + "' where email='" +email+ "'";
		
		try {
			Statement statement= connection.createStatement();
			int update = statement.executeUpdate(sql);
			System.out.println(update);
			
			PrintWriter out= response.getWriter();
			if(update > 0) {
				out.println("<h1> Updated the USER </h1>");
			} else {
				out.println("<h1> The USER IS NOT UPDATED </h1>");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
