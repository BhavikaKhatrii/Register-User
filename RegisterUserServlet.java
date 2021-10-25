package com.codyk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","Bhavika","bhavika");
			PreparedStatement ps = con.prepareStatement("insert into REGISTERUSER values(?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2,pass);
			ps.setString(3,email);
			ps.setString(4,country);

			int i = ps.executeUpdate();
			if(i>0)
				pw.println("<h2>You Are Successfully Registered...</h2>");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
