package com.mmt.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.mmt.model.dao.DbConnection.*;
import com.mmt.model.bean.Flight;
import com.mmt.model.bean.HotelRoom;
import com.mmt.model.bean.Promotion;

public class PromotionDaoImplMMT implements PromotionDaoMMT {

	//Connection con = null;
	private Configuration cfg;
	private SessionFactory factory;
	private Transaction tx;
	
	
	public PromotionDaoImplMMT() {
		cfg = new AnnotationConfiguration();
		cfg.configure();
		factory = cfg.buildSessionFactory();
		tx = null;
	}
	//insert function to add the promo details
	@Override
	public int insertPromotion(Promotion p) throws SQLException, ClassNotFoundException, IOException {
//		int row;
//		con = DbConnection.dbConnection();
//
//		con = DbConnection.dbConnection();
//		PreparedStatement pst = con.prepareStatement("insert into promotion values(?,?,?,?,?,?)");
//
//		pst.setString(1, p.getPromotionId());
//		pst.setString(2, p.getPromotionName());
//		pst.setDouble(3, p.getPromotionDiscount());
//		pst.setString(4, p.getPromotionExpiryDate());
//		pst.setDouble(5, p.getPromotionMinRequiredAmount());
//		pst.setString(6, p.getPromotionType());
//		row = pst.executeUpdate();
//		return row;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(p);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}
	//delet function to delete the promo
	@Override
	public int deletePromotion(String promotionId) throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		// Statement stmt = con.createStatement();
//		// int rows = stmt.executeUpdate("delete from PROMOTION where
//		// PROMOTIONID =" + promotionId);
//		int row = 0;
//		PreparedStatement pst = con.prepareStatement("delete from promotion where promotionId=?");
//		pst.setString(1, promotionId);
//		row = pst.executeUpdate();
//
//		con.close();
//		return row;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Promotion promotion = (Promotion) session.get(Promotion.class, promotionId);
			if (promotion != null) {
				session.delete(promotion);
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
		//update function to update the promo details
	@Override
	public int updatePromotion(String promotionId, Promotion newp)
			throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		int row = 0;
//		PreparedStatement pst = con.prepareStatement(
//				"update promotion set promotionId=?,  promotionName=?, promotionDiscount=?, promotionExpiryDate=?,promotionMinRequiredAmount=?,promotionType=? where promotionId=?");
//		pst.setString(1, newp.getPromotionId());
//		pst.setString(2, newp.getPromotionName());
//		pst.setDouble(3, newp.getPromotionDiscount());
//		pst.setString(4, newp.getPromotionExpiryDate());
//		pst.setDouble(5, newp.getPromotionMinRequiredAmount());
//		pst.setString(6, newp.getPromotionType());
//		pst.setString(7, promotionId);
//		
//		row=pst.executeUpdate();
//		con.close();
//
//		return row;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Promotion promotion = (Promotion) session.get(Promotion.class, promotionId);
			if (promotion != null) {
				promotion.setPromotionId(newp.getPromotionId());
				promotion.setPromotionName(newp.getPromotionName());
				promotion.setPromotionDiscount(newp.getPromotionDiscount());
				promotion.setPromotionExpiryDate(newp.getPromotionExpiryDate());
				promotion.setPromotionMinRequiredAmount(newp.getPromotionMinRequiredAmount());
				promotion.setPromotionType(newp.getPromotionType());
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
	public ArrayList<Promotion> displayPromotion() throws SQLException, ClassNotFoundException, IOException {
//		Promotion pro = null;
//		con = DbConnection.dbConnection();
//
//		ArrayList<Promotion> proList = new ArrayList<Promotion>();
//
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("select * from Promotion ");
//		while (rs.next()) {
//			 pro = new Promotion();
//			pro.setPromotionId(rs.getString(1));
//			pro.setPromotionName(rs.getString(2));
//			pro.setPromotionDiscount(rs.getFloat(3));
//			pro.setPromotionExpiryDate(rs.getString(4));
//			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
//			pro.setPromotionType(rs.getString(6));
//			proList.add(pro);
//		}
//		con.close();
//		return proList;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Promotion");
			List<Promotion> pList = query.list();

			return (ArrayList<Promotion>) pList;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public Promotion searchPromotion(String promotionId) throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		Promotion pro;
//		PreparedStatement pst = con.prepareStatement("select * from promotion where promotionId=?");
//		pst.setString(1, promotionId);
//		ResultSet rs = pst.executeQuery();
//
//		while (rs.next()) {
//			pro = new Promotion();
//			pro.setPromotionId(rs.getString(1));
//			pro.setPromotionName(rs.getString(2));
//			pro.setPromotionDiscount(rs.getFloat(3));
//			pro.setPromotionExpiryDate(rs.getString(4));
//			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
//			pro.setPromotionType(rs.getString(6));
//			con.close();
//			return pro;
//		}
//		return null;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Promotion promotion = (Promotion) session.get(Promotion.class, promotionId);
			if (promotion != null) {
				// tx.commit(); Not Required but wont give any error if used
				return promotion;
			} else
				return null;

		} catch (Exception e) {
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public ArrayList<Promotion> displayPromotion(String promotionType)
			throws SQLException, ClassNotFoundException, IOException {
//		con = DbConnection.dbConnection();
//		Promotion pro = null;
//		ArrayList<Promotion> proList = new ArrayList<Promotion>();
//		PreparedStatement pst;
//		pst = con.prepareStatement("select * from  Promotion where promotionType=?");
//		pst.setString(1, promotionType);
//		ResultSet rs = pst.executeQuery();
//
//		while (rs.next()) {
//			pro = new Promotion();
//			pro.setPromotionId(rs.getString(1));
//			pro.setPromotionName(rs.getString(2));
//			pro.setPromotionDiscount(rs.getFloat(3));
//			pro.setPromotionExpiryDate(rs.getString(4));
//			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
//			pro.setPromotionType(rs.getString(6));
//			proList.add(pro);
//		}
//		con.close();
//		return proList;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			ArrayList<Promotion> promotion =  (ArrayList<Promotion>) session.get(Promotion.class, promotionType);
			if (promotion != null) {
				// tx.commit(); Not Required but wont give any error if used
				return promotion;
			} else
				return null;

		} catch (Exception e) {
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
		
	}

}
