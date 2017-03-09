package com.mmt.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.mmt.model.bean.Flight;
import com.mmt.model.bean.Promotion;
import com.mmt.model.bean.Wallet;

public class WalletDaoImplMMT implements WalletDaoMMT {
	//Connection con = null;
	// Wallet wl = null;
	private Configuration cfg;
	private SessionFactory factory;
	private Transaction tx;
	
	
	public WalletDaoImplMMT() {
		cfg = new AnnotationConfiguration();
		cfg.configure();
		factory = cfg.buildSessionFactory();
		tx = null;
	}

	@Override
	public Wallet displayWallet(String userId) throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		Wallet newWallet = new Wallet();
//
//		ResultSet rs;
//		PreparedStatement pst = con.prepareStatement("select * from wallet where UserId=?");
//		pst.setString(1, userId);
//		rs = pst.executeQuery();
//		if (rs.next()) {
//			newWallet.setUserId(rs.getString(1));
//			newWallet.setWalletBalance(rs.getDouble(2));
//			con.close();
//			return newWallet;
//		}
//		con.close();
//		return null;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			System.out.println("0");
			Query query = session.createQuery("from Wallet where userId=?");
			query.setString(0,userId);
			System.out.println("1");
			List<Wallet> wallet= query.list();
			System.out.println(wallet);
			Wallet w=wallet.get(0);
			System.out.println(w);
			System.out.println("2");
				return w;
		} catch (Exception e) {
			System.out.println("Loop3");
			e.printStackTrace();
			System.out.println("Loop4");
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public int updateWallet(String userId, Wallet newWallet) throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		int row = 0;
//		PreparedStatement pst = con.prepareStatement("update wallet SET walletBalance=? where userId=?");
//
//		pst.setDouble(1, newWallet.getWalletBalance());
//		pst.setString(2, userId);
//		row = pst.executeUpdate();
//
//		con.close();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Wallet wallet = (Wallet) session.get(Wallet.class, userId);
			if (wallet != null) {
				wallet.setWalletBalance(newWallet.getWalletBalance());
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
	public ArrayList<Wallet> displayWalletAll() throws SQLException, ClassNotFoundException, IOException {
//		Wallet pro = new Wallet();
//		con = DbConnection.dbConnection();
//
//		ArrayList<Wallet> proList = new ArrayList<Wallet>();
//
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("select * from Wallet ");
//		while (rs.next()) {
//			pro.setUserId(rs.getString(1));
//			pro.setWalletBalance(rs.getDouble(2));
//			proList.add(pro);
//		}
//		con.close();
//		return proList;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Wallet");
			List<Wallet> walletList = query.list();

			return (ArrayList<Wallet>) walletList;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			session.close();
		}

		
	}

	@Override
	public int insertWallet(Wallet wallet) throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		int row = 0;
//
//		PreparedStatement pst = con.prepareStatement("insert into wallet values(?,?)");
//		pst.setString(1, wallet.getUserId());
//		pst.setDouble(2, wallet.getWalletBalance());
//		row = pst.executeUpdate();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(wallet);
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
	public int deleteWallet(Wallet w) throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		String uId = w.getUserId();
//		double userBalance = w.getWalletBalance();
//		Statement stmt = con.createStatement();
//		int rows = stmt.executeUpdate("delete from Wallet where USERID=+" + uId);
//		if (rows > 0) {
//			con.close();
//			return rows;
//		} else {
//			con.close();
//			return 0;
//		}
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Wallet wallet = (Wallet) session.get(Wallet.class, w.getUserId());
			if (wallet != null) {
				session.delete(wallet);
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
