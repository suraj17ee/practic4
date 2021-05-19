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

public class SubjectStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext con = getServletContext();
		Connection cn = (Connection) con.getAttribute("cn");
		String user = (String) con.getAttribute("username");

		String opt = req.getParameter("optics");
		String mot = req.getParameter("motion");
		String gra = req.getParameter("gravity");

		String aci = req.getParameter("acid");
		String bas = req.getParameter("base");
		String elem = req.getParameter("elements");

		String numb = req.getParameter("numbersystem");
		String pro = req.getParameter("profitloss");
		String spe = req.getParameter("speeddistance");

		String c = req.getParameter("c");
		String cpp = req.getParameter("cpp");
		String jav = req.getParameter("java");

		String cel = req.getParameter("cells");
		String res = req.getParameter("respiratorysystem");
		String dia = req.getParameter("digestivesystem");

		PreparedStatement pst = null;
		PrintWriter out = resp.getWriter();

		try {
			Class.forName(con.getInitParameter("driver"));
			cn = DriverManager.getConnection(con.getInitParameter("url"), con.getInitParameter("username"),
					con.getInitParameter("password"));
			pst = cn.prepareStatement(
					"insert into studentnew.topicstatus (sname,optics,motion,gravity,acid,base,elements,numbersystem,profitloss,speeddistance,c,cpp,java,cells,respiratorysystem,digestivesystem"
							+ ") values(?,?,?,?,?,? ,?,?,?,?,?,? ,?,?,?,?)");
			pst.setString(1, user);
			pst.setString(2, opt);
			pst.setString(3, mot);
			pst.setString(4, gra);
			pst.setString(5, aci);
			pst.setString(6, bas);
			pst.setString(7, elem);

			pst.setString(8, numb);
			pst.setString(9, pro);
			pst.setString(10, spe);
			pst.setString(11, c);
			pst.setString(12, cpp);
			pst.setString(13, jav);
			pst.setString(14, cel);
			pst.setString(15, res);
			pst.setString(16, dia);

			pst.executeUpdate();

			out.println("<html><body><h3 style='color:green;'>topic status updated successfully!!</h3></body></html>");
//			RequestDispatcher rd = req.getRequestDispatcher("Login.html");
//			rd.include(req, resp);
			resp.sendRedirect("topicproceedings");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
