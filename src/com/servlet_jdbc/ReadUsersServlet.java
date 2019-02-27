package com.servlet_jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/readServlet")
public class ReadUsersServlet extends HttpServlet {
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
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		try {
			Statement statement= connection.createStatement();
			ResultSet resultSet= statement.executeQuery("select * from user");
			while(resultSet.next()) {
				out.print("----------------------------");
				out.println("<h1>" + resultSet.getString(1) + "</h1>");
				out.println("<br>");
				out.println("<h1>" + resultSet.getString(2) + "</h1>");
				out.println("<br>");
				out.println("<h1>" + resultSet.getString(3) + "</h1>");
				out.println("<br>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
