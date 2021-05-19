package com.studentRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectReg extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext con = getServletContext();
		Connection cn = (Connection) con.getAttribute("cn");
		String user = (String) con.getAttribute("username");

		String phy = req.getParameter("phy");
		String che = req.getParameter("che");
		String mat = req.getParameter("mat");
		String inf = req.getParameter("inf");
		String bio = req.getParameter("bio");

		String opt = req.getParameter("opt");
		String mot = req.getParameter("mot");
		String gra = req.getParameter("gra");

		String aci = req.getParameter("aci");
		String bas = req.getParameter("bas");
		String elem = req.getParameter("elem");

		String numb = req.getParameter("numb");
		String pro = req.getParameter("pro");
		String spe = req.getParameter("spe");

		String c = req.getParameter("c");
		String cpp = req.getParameter("cpp");
		String jav = req.getParameter("jav");

		String cel = req.getParameter("cel");
		String res = req.getParameter("res");
		String dia = req.getParameter("dia");

		PreparedStatement pst = null;
		PrintWriter out = resp.getWriter();

		try {
			Class.forName(con.getInitParameter("driver"));
			cn = DriverManager.getConnection(con.getInitParameter("url"), con.getInitParameter("username"),
					con.getInitParameter("password"));
			pst = cn.prepareStatement(
					"insert into studentnew.studentstatus (sname,subject1,subject2,subject3,subject4,subject5,topic1,"
							+ "topic2,topic3,topic4,topic5,topic6,topic7,topic8,topic9,topic10,topic11,topic12,topic13,topic14,topic15) values(?,?,?,?,?,? ,?,?,?,?,?,? ,?,?,?,?,?,? ,?,?,?)");
			pst.setString(1, user);
			pst.setString(2, phy);
			pst.setString(3, che);
			pst.setString(4, mat);
			pst.setString(5, inf);
			pst.setString(6, bio);

			pst.setString(7, opt);
			pst.setString(8, mot);
			pst.setString(9, gra);
			pst.setString(10, aci);
			pst.setString(11, bas);
			pst.setString(12, elem);

			pst.setString(13, numb);
			pst.setString(14, pro);
			pst.setString(15, spe);
			pst.setString(16, c);
			pst.setString(17, cpp);
			pst.setString(18, jav);
			pst.setString(19, cel);
			pst.setString(20, res);
			pst.setString(21, dia);

			pst.executeUpdate();

			out.println("<html><body><h3 style='color:green;'>subjects added successfully!!</h3></body></html>");
			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
			rd.include(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
