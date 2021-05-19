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
import javax.servlet.http.HttpSession;

public class LogInP extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String user=req.getParameter("user");
	String pass=req.getParameter("pass");
	
	Connection cn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	PrintWriter out=resp.getWriter();
	ServletContext context=getServletContext();
	
	try {
		Class.forName(context.getInitParameter("driver"));
		cn=DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("username"),context.getInitParameter("password"));
		pst=cn.prepareStatement("select * from studentnew.studentstatus where sname=?");
		pst.setString(1, user);
		rs=pst.executeQuery();
		if(rs.next()) 
		{
			if(rs.getString("sname").equals(user)) 
			{
				ServletContext con=getServletContext();
				con.setAttribute("username", user);
				out.println("<html><body>"
					+"<h1>welcome "
					+rs.getString("sname")+"...</h1>"+"<h2>"+"Your Subjects are </h2>"+rs.getString("subject1")+", "+rs.getString("subject2")+", "+rs.getString("subject3")
					+", "+rs.getString("subject4")+", "+rs.getString("subject5")+".</h4> <h2>You are going to read topics like: </h2>"+"<h3>In Physics: </h3>"+rs.getString("topic1")+", "
					+rs.getString("topic2")+", "+rs.getString("topic3")+"<br>"+"<h3>In Chemistry: </h3>"+rs.getString("topic4")+", "+rs.getString("topic5")+", "+rs.getString("topic6")
					+"<br>"+"<h3>In Mathematics: </h3>"+rs.getString("topic7")+", "+rs.getString("topic8")+", "+rs.getString("topic9")+"<br>"+"<h3>In IT: </h3>"+rs.getString("topic10")+", "+rs.getString("topic11")
					+", "+rs.getString("topic12")+"<br>"+"<h3>In Biology: </h3>"+rs.getString("topic13")+", "+rs.getString("topic14")+", "+rs.getString("topic15")
					+"</body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("TopicStatus.html");
			rd.include(req, resp);
			}
		}
		else 
		{
				pst=cn.prepareStatement("select * from studentnew.studentnew where username=?");
				pst.setString(1, user);
				rs=pst.executeQuery();
				if(rs.next()) 
				{
					if(rs.getString("password").equals(pass)) 
					{
						ServletContext con=getServletContext();
						con.setAttribute("username", user);
						con.setAttribute("cn", cn);
						resp.sendRedirect("studentprof");
					}
					else 
					{
						out.println("<html><body><h1>invalid password</h1></body></html>");
						RequestDispatcher rd=req.getRequestDispatcher("Login.html");
						rd.include(req, resp);
					}
				}
				else 
				{
					out.println("<html><body><h1>first register/signup to login</h1></body></html>");
				}
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}
	}
}
