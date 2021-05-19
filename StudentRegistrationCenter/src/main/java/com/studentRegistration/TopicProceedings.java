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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/topicproceedings")
public class TopicProceedings extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PrintWriter out = resp.getWriter();
		ServletContext con = getServletContext();

		cn = (Connection) con.getAttribute("cn");
		String user = (String) con.getAttribute("username");

		try {
			Class.forName(con.getInitParameter("driver"));
			cn = DriverManager.getConnection(con.getInitParameter("url"), con.getInitParameter("username"),
					con.getInitParameter("password"));
			pst = cn.prepareStatement("select * from studentnew.topicstatus where sname=?");
			pst.setString(1, user);
			rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getString("sname").equals(user)) {
					out.println("<html><body>" + "<h1>welcome " + rs.getString("sname") + "...</h1>" + "<h2>"
							+ "Your topics status are </h2>" +"<h3>In Physics: </h3>"+"Optics: "+ rs.getString("optics") + ", "
							+" Motion: "+ rs.getString("motion") + ", " +" Gravity: "+ rs.getString("gravity") +"<br>"+ "<h3>In Chemistry: </h3>" +" Acid: "+ rs.getString("acid")
							+ ", " +" Base: "+ rs.getString("base") + ", " +" Elements: "+ rs.getString("elements") + "<br>"
							+"<h3>In Mathematics: </h3>"+ " Number System: "+rs.getString("numbersystem") + ", " +" Profit Loss: "+ rs.getString("profitloss") + ", "
							+" Speed Distance: "+ rs.getString("speeddistance") + "<br>" +"<h3>In IT: </h3>"+" C: "+ rs.getString("c") + ", " +" Cpp: "+ rs.getString("cpp")
							+ ", " +" JAVA: "+ rs.getString("java") + "<br>" +"<h3>In Biology: </h3>"+" Cells: "+ rs.getString("cells") + ", "
							+" Respiratory System: "+ rs.getString("respiratorysystem") + ", " +" Digestive System: "+ rs.getString("digestivesystem") + "<br>"
							+ "<a href='Login.html'>Back to login page</a>" + "</body></html>");
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
