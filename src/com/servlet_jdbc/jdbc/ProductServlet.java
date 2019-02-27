package com.servlet_jdbc.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "nisum");
		} catch (SQLException e) {

		}
	}

	@Override
	public void destroy() {
		try {
			stmt.close();
			connection.close();
		} catch (SQLException e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			stmt = connection.prepareStatement("insert into product values(?,?,?,?)");
		} catch (SQLException e) {

		}

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");

		try {
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, description);
			stmt.setInt(4, price);
			int res= stmt.executeUpdate();
			System.out.println(res);
			if(res > 0) {
				out.println("<h1>Yeiii...</h1>");
			} else {
				out.println("<h1>Tusss...</h1>");
			}
		} catch (SQLException e) {

		}
		out.println("</html></body>");
	}

}
