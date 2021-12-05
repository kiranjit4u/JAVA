package com.jdbc.servletjdbcdemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletJdbcDemo
 */
@WebServlet("/Save")
public class ServletJdbcDemo extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName=req.getParameter("fname");
		String lastName=req.getParameter("lname");
		System.out.println("FirstName : " + firstName);
		System.out.println("LastName : " + lastName);
		try {
		//step1 load the driver class  
		Class.forName("com.mysql.jdbc.Driver");  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/devdb","root","");  
		//step3 create the statement object  
		//Statement stmt=con.createStatement();  
		
		
		  
		//Step6 Insert 
		PreparedStatement stmt=con.prepareStatement("insert into test values(?,?)");  
		stmt.setString(1,firstName);//1 specifies the first parameter in the query  
		stmt.setString(2,lastName);  
		  
		int i=stmt.executeUpdate();  
		System.out.println(i+" records inserted"); 
		
		//step4 execute query  
		ResultSet rs=stmt.executeQuery("select * from test");  
		while(rs.next())  
		System.out.println(rs.getString(1)+"  "+rs.getString(2));  
		
		//step5 close the connection object  
		con.close();  
		  
			}catch(Exception e){ System.out.println(e);}  
	    req.setAttribute("attrib", true);
	    req.getRequestDispatcher("/output.jsp").include(req, resp);
		
	}  
}

