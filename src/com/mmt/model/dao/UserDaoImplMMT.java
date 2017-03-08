package com.mmt.model.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.mmt.model.bean.Flight;
import com.mmt.model.bean.User;

public class UserDaoImplMMT implements UserDaoMMT {
	//Connection con;
	private Configuration cfg;
	private SessionFactory factory;
	private Transaction tx;
	
	
	
	public UserDaoImplMMT() {
		cfg = new AnnotationConfiguration();
		cfg.configure();
		factory = cfg.buildSessionFactory();
		tx = null;
	}

	@Override
	public int insert(User user) throws SQLException, ClassNotFoundException, IOException {
//		int row;
//		con=DbConnection.dbConnection();
//		PreparedStatement pst = con.prepareStatement("insert into mmt_user values(?,?,?,?,?,?)");
//		pst.setString(1, user.getUserId());
//		pst.setString(2,user.getUserName());
//		pst.setLong(3,user.getUserPhoneNo());
//		pst.setString(4,user.getUserEmailId());
//		pst.setString(5,user.getUserAddress());
//		pst.setString(6, user.getUserPassword());
//		
//		
//		row=pst.executeUpdate();
//		
//		con.close();
//		return row;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	@Override
	public User search(String uid) throws SQLException, ClassNotFoundException, IOException {
		
//		User user=new User();
//		ResultSet rs;
//		con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("select * from mmt_user where USERID=?");
//		pst.setString(1, uid);
//		rs=pst.executeQuery();
//		if(rs.next()){
//			user.setUserId((rs.getString("USERID")));
//			user.setUserName((rs.getString("USERNAME")));
//			user.setUserPhoneNo(rs.getLong("USERPHONENO"));
//			user.setUserEmailId(rs.getString("USEREMAILID"));
//			user.setUserAddress(rs.getString("USERADDRESS"));
//			user.setUserPassword(rs.getString("userpassword"));	
//			return user;
//		}
//
//		con.close();
//		return null;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, uid);
			if (user != null) {
				// tx.commit(); Not Required but wont give any error if used
				return user;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public int delete(String uid) throws SQLException, ClassNotFoundException, IOException {
//		int row;
//		 con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("delete from mmt_user where USERID=?");
//		pst.setString(1, uid);
//		row=pst.executeUpdate();
//		con.close();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, uid);
			if (user != null) {
				session.delete(user);
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

	@Override
	public int update(String uid, User newUser) throws SQLException, ClassNotFoundException, IOException {
//		int row;
//		 con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("update mmt_user set USERNAME=?,  USERPHONENO=?, USEREMAILID=?, USERADDRESS=?,USERPASSWORD=? where userid=?");
//		pst.setString(1, user.getUserName());
//		pst.setLong(2, user.getUserPhoneNo());
//		pst.setString(3, user.getUserEmailId());
//		pst.setString(4, user.getUserAddress());
//		pst.setString(5, user.getUserPassword());
//		pst.setString(6, uid);
//		row=pst.executeUpdate();
//		return row;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, uid);
			if (user != null) {
				user.setUserName(newUser.getUserName());
				user.setUserPhoneNo(newUser.getUserPhoneNo());
				user.setUserEmailId(newUser.getUserEmailId());
				user.setUserAddress(newUser.getUserAddress());
				user.setUserPassword(newUser.getUserPassword());
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

	@Override
	public List<User> displayAll() throws SQLException, ClassNotFoundException, IOException {
//		User user=new User();
//		List<User> list=new ArrayList<User>();
//		ResultSet rs;
//		con=DbConnection.dbConnection();
//		PreparedStatement pst=con.prepareStatement("select * from mmt_user");
//		rs=pst.executeQuery();
//		while(rs.next()){
//			user.setUserId(rs.getString("USERID"));
//			user.setUserName((rs.getString("USERNAME")));
//			user.setUserPhoneNo((rs.getLong("USERPHONENO")));
//			user.setUserEmailId((rs.getString("USEREMAILID")));
//			user.setUserAddress((rs.getString("USERADDRESS")));
//			user.setUserPassword((rs.getString("USERPASSWORD")));
//			
//			list.add(user);
//		}
//		
//		con.close();
//		
//		return list;
		
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Mmt_user");
			List<User> userList = query.list();

			return  userList;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}
}
