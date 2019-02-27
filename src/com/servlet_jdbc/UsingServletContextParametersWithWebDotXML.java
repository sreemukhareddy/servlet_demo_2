package com.servlet_jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UsingServletContextParametersWithWebDotXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection= null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(connection);
		ServletContext context= config.getServletContext();
		try {
			Class.forName(context.getInitParameter("driver"));
			connection = DriverManager.getConnection(context.getInitParameter("dburl"), context.getInitParameter("user"), context.getInitParameter("password"));
		} catch (SQLException | ClassNotFoundException e) {
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
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		String sql= "insert into user values" + "('" + firstName + "','" + lastName + "','" + email + "','" + password + "')";
		System.out.println(sql);
		try {
			Statement statement= connection.createStatement();
			int update = statement.executeUpdate(sql);
			System.out.println(update);
			
			PrintWriter out= response.getWriter();
			if(update > 0) {
				out.println("<h1> The USER is CREATED </h1>");
			} else {
				out.println("<h1> The USER IS NOT UPDATED </h1>");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
