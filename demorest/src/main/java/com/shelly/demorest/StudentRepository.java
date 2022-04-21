package com.shelly.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	Connection con;

	public StudentRepository() {
		try {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restdb", "root", "default");
			System.out.println("Connection establish successfull!!" + con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getStudentList() {
		System.out.println("Inside StudentRepository.getStudentList");
		List<Student> studentlist = new ArrayList<>();
		String sql = "select * from student";
		Statement stmt;
		ResultSet rs;
		Student s;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setPoints(rs.getInt(3));
				studentlist.add(s);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("the list is " + studentlist);
		return studentlist;
	}

	public Student getStudent(int id) {
		String sql = "select * from student where id=" + id;
		Statement stmt;
		ResultSet rs;
		Student s = new Student();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setPoints(rs.getInt(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	public Student create(Student s) {
		String sql = "insert into Student values(?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, s.getId());
			stmt.setString(2, s.getName());
			stmt.setInt(3, s.getPoints());
			stmt.executeUpdate();
            System.out.println("inserted in the database!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public Student update(Student s) {
		String sql = "update Student set name=? , points=? where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(3, s.getId());
			stmt.setString(1, s.getName());
			stmt.setInt(2, s.getPoints());
			int result = stmt.executeUpdate();
			if(result==0) // no result found or no row updated
				create(s);
            System.out.println("updated in the database!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public void delete(int id) {
		String sql = "delete from Student where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
		    stmt.executeUpdate();
            System.out.println("deleted from the database!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
