package com.studentRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationN extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String mob = req.getParameter("mob");
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");

		Connection cn = null;
		PreparedStatement pst = null;
		PrintWriter out = resp.getWriter();
		ServletContext context = getServletContext();
		ResultSet rs = null;

		try {
			Class.forName(context.getInitParameter("driver"));
			cn = DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("username"),
					context.getInitParameter("password"));

			pst = cn.prepareStatement("select * from studentnew.studentnew where username=?");
			pst.setString(1, user);
			rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(pass)) {
					context.setAttribute("username", user);
					context.setAttribute("cn", cn);

					out.println(
							"<html><body><h3 style='color:green-yellow;'>User is already present!!</h3></body></html>");
				} else {
					pst = cn.prepareStatement(
							"insert into studentnew.studentnew (sname,mobile,username,password) values(?,?,?,?)");
					pst.setString(1, name);
					pst.setInt(2, Integer.parseInt(mob));
					pst.setString(3, user);
					pst.setString(4, pass);

					pst.executeUpdate();

					out.println("<html><body><h3 style='color:green;'>signed up successfully!!</h3></body></html>");
					RequestDispatcher rd = req.getRequestDispatcher("Login.html");
					rd.include(req, resp);

					resp.sendRedirect("studentprof");
				}
			} else {
				pst = cn.prepareStatement("insert into studentnew.studentnew (sname,mobile,username,password) values(?,?,?,?)");
				pst.setString(1, name);
				pst.setInt(2, Integer.parseInt(mob));
				pst.setString(3, user);
				pst.setString(4, pass);

				pst.executeUpdate();

				out.println("<html><body><h3 style='color:green;'>signed up successfully!!</h3></body></html>");
				RequestDispatcher rd = req.getRequestDispatcher("Login.html");
				rd.include(req, resp);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
