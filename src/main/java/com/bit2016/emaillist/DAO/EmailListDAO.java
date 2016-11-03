package com.bit2016.emaillist.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bit2016.emaillist.VO.EmailList;

@Repository
public class EmailListDAO {

	public static void insert(EmailList emaillist) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DAOConnection.connection();

			String sql = "insert into emaillist values(EMAIL_SEQ.nextval, ?,?,?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emaillist.getFirst_name());
			stmt.setString(2, emaillist.getLast_name());
			stmt.setString(3, emaillist.getEmail());
			stmt.executeUpdate();//
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<EmailList> selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<EmailList> list = null;
		try {
			conn = DAOConnection.connection();
			String sql = "select no,last_name, first_name,email from emaillist order by no desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = new ArrayList<EmailList>();
			while (rs.next()) {
				EmailList el = new EmailList();
				el.setNo(rs.getLong(1));
				el.setLast_name(rs.getString(2));
				el.setFirst_name(rs.getString(3));
				el.setEmail(rs.getString(4));
				list.add(el);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
