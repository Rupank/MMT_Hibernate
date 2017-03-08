package com.mmt.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.mmt.model.bean.Admin;
import com.mmt.model.bean.Flight;


public class AdminDaoImplMMT implements AdminDao{
	
	//insert function for the admin board
	private Configuration cfg;
	private SessionFactory factory;
	private Transaction tx;

	
	public AdminDaoImplMMT() {
		cfg = new AnnotationConfiguration();
		cfg.configure();
		factory = cfg.buildSessionFactory();
		tx = null;
	}

	@Override
	public int insert(Admin admin) throws SQLException, ClassNotFoundException, IOException {
//		int row;
//		Connection con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("insert into admin values(?,?,?,?,?,?)");
//		pst.setString(1, admin.getAdminId());
//		pst.setString(2,admin.getAdminName());
//		pst.setLong(3,admin.getAdminPhoneNo());
//		pst.setString(4,admin.getAdminEmailId());
//		pst.setString(5,admin.getAdminAddress());
//		pst.setString(6,admin.getAdminPassword());
//		
//		
//		row=pst.executeUpdate();
//		con.close();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(admin);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

		// search function for admin
	@Override
	public Admin search(String uid) throws SQLException, ClassNotFoundException, IOException {
//		Admin admin=new Admin();
//		ResultSet rs;
//		Connection con=DbConnection.dbConnection();;
//		PreparedStatement pst=con.prepareStatement("select * from admin where ADMINID=?");
//		pst.setString(1, uid);
//		rs=pst.executeQuery();
//		
//		if(rs.next()){
//		
//			admin.setAdminId((rs.getString("ADMINID")));
//			admin.setAdminName((rs.getString("ADMINNAME")));
//			admin.setAdminPhoneNo(rs.getLong("ADMINPHONENO"));
//			admin.setAdminEmailId(rs.getString("ADMINEMAILID"));
//			admin.setAdminAddress(rs.getString("ADMINADDRESS"));
//			admin.setAdminPassword(rs.getString("ADMINPASSWORD"));	
//			return admin;
//		}
//
//		con.close();
//		return null;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Admin admin = (Admin) session.get(Admin.class, uid);
			if (admin != null) {
				// tx.commit(); Not Required but wont give any error if used
				return admin;
			} else
				return null;

		} catch (Exception e) {
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	//delete function to delete Admin
	@Override
	public int delete(String uid) throws SQLException, ClassNotFoundException, IOException {
		
//		int row;
//		Connection con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("delete from admin where ADMINID=?");
//		pst.setString(1, uid);
//		row=pst.executeUpdate();
//		con.close();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Admin admin = (Admin) session.get(Admin.class, uid);
			if (admin != null) {
				session.delete(admin);
				tx.commit();
				return 1;
			} else
				return 0;

		} catch (Exception e) {
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	//update function to update the admin details
		
	@Override
	public int update(String uid, Admin newAdmin) throws SQLException, ClassNotFoundException, IOException {
		//int row;
//		Connection con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("update admin set ADMINNAME=?,  ADMINPHONENO=?, 	ADMINEMAILID=?, ADMINADDRESS=?,ADMINPASSWORD=? where ADMINID	=?");
//		pst.setString(1, admin.getAdminName());
//		pst.setLong(2, admin.getAdminPhoneNo());
//		pst.setString(3, admin.getAdminEmailId());
//		pst.setString(4, admin.getAdminAddress());
//		pst.setString(5, admin.getAdminPassword());
//		pst.setString(6, uid);
//		row=pst.executeUpdate();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Admin admin = (Admin) session.get(Admin.class, uid);
			if (admin != null) {
				admin.setAdminName(newAdmin.getAdminName());
				admin.setAdminPhoneNo(newAdmin.getAdminPhoneNo());
				admin.setAdminEmailId(newAdmin.getAdminEmailId());
				admin.setAdminAddress(newAdmin.getAdminAddress());
				admin.setAdminPassword(newAdmin.getAdminPassword());
				tx.commit();
				return 1;
			} else
				return 0;

		} catch (Exception e) {
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	
}
