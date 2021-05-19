package com.studentRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentProfile extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext con = getServletContext();
		Connection cn = (Connection) con.getAttribute("cn");
		String user = (String) con.getAttribute("username");
		PreparedStatement pst;
		try {
			String qu = "select * from studentnew.studentnew where username=?";
			pst = cn.prepareStatement(qu);
			pst.setString(1, user);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				PrintWriter out = resp.getWriter();
				out.println("<html><body>" + "<h1>welcome </h1> <h3>" + rs.getString("sname")
						+ " your mobile number is " + rs.getInt("mobile") + "</h3>" + "</body></html>");

				RequestDispatcher rd = req.getRequestDispatcher("subject.html");
				rd.include(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
