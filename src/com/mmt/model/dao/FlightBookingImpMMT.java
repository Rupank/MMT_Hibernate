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

import com.mmt.model.bean.Flight;
import com.mmt.model.bean.FlightBooking;
import com.mmt.model.bean.Promotion;

public class FlightBookingImpMMT implements FlightBookingDaoMMT {
	 Connection con;

	private Configuration cfg;
	private SessionFactory factory;
	private Transaction tx;

	public FlightBookingImpMMT() {
		cfg = new AnnotationConfiguration();
		cfg.configure();
		factory = cfg.buildSessionFactory();
		
	}

	// display the details of flightbooking
	@Override
	public ArrayList<FlightBooking> displayFlightBooking() throws ClassNotFoundException, SQLException, IOException {
		// ArrayList<FlightBooking> FB = new ArrayList<FlightBooking>();
		// FlightBooking fb = new FlightBooking();
		// con = DbConnection.dbConnection();
		// // Query
		// Statement stmt = con.createStatement();
		// ResultSet rs = stmt.executeQuery("select * from FLIGHTBOOKING");
		//
		// while (rs.next()) {
		// fb.setFlightBookingId(rs.getString("flightBookingId"));
		// fb.setFlightId(rs.getString("flightId"));
		// fb.setUserId(rs.getString("userId"));
		// fb.setFlightBookingId(rs.getString("flightBookingDate"));
		//
		// String status = "false";
		// if (rs.getString("flag").equals("true")){
		// status = "true";
		//
		// fb.setFlag(true);
		// }
		//
		//
		//
		// }
		// con.close();
		//
		// return FB;
		// }

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from Flightbooking");
			
			List<FlightBooking> fList = query.list();
			
			return (ArrayList<FlightBooking>) fList;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public ArrayList<FlightBooking> searchFlightBooking(String userId)
			throws ClassNotFoundException, SQLException, IOException {

//		FlightBooking fb = null;
//		ArrayList<FlightBooking> fList = new ArrayList<FlightBooking>();
//		ResultSet rs;
//		con = DbConnection.dbConnection();
//		// Query
//		PreparedStatement pst = con.prepareStatement("select * from FLIGHTBOOKING where userId=?");
//		pst.setString(1, userId);
//		rs = pst.executeQuery();
//		while (rs.next()) {
//			fb = new FlightBooking();
//			fb.setFlightBookingId(rs.getString("flightBookingId"));
//			fb.setFlightId(rs.getString("flightId"));
//			fb.setUserId(rs.getString("userId"));
//			Date d = rs.getDate(4);
//			// java.sql.Date sqlDate1 = new java.sql.Date(d.getTime());
//			fb.setFlightBookingDate(d);
//
//			String status = "false";
//			if (rs.getString("flag").equalsIgnoreCase("true")) {
//				status = "true";
//
//				fb.setFlag(true);
//			}
//
//			fList.add(fb);
//		}
//		con.close();
//
//		return fList;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			System.out.println("1");
			Query query = session.createQuery("from FlightBooking where userId=?");
			query.setString(0, userId);
			List<FlightBooking> flightbooking =  query.list();
			System.out.println("2");
			if (flightbooking != null) {
				// tx.commit(); Not Required but wont give any error if used
				System.out.println("3");
				System.out.println(flightbooking);
				return (ArrayList<FlightBooking>) flightbooking;
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

	// insert function for the flight booking
	@Override
	public int insertFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException, IOException {

//		con = DbConnection.dbConnection();
//		int row = 0;
//		// Statement stmt = con.createStatement();
//		// System.out.println("DAOPrint:"+fb);
//		PreparedStatement pst = con.prepareStatement("insert into FLIGHTBOOKING values(?,?,?,?,?)");
//		pst.setString(1, fb.getFlightBookingId());
//		pst.setString(2, fb.getUserId());
//		pst.setString(3, fb.getFlightId());
//		Date d = fb.getFlightBookingDate();
//		java.sql.Date sqlDate1 = new java.sql.Date(d.getTime());
//		pst.setDate(4, sqlDate1);
//		String status = "false";
//		if (fb.isFlag()) {
//			status = "true";
//			pst.setString(5, status);
//		}
//
//		row = pst.executeUpdate();
//
//		con.close();
//		return row;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			System.out.println(fb);
			session.save(fb);
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println("Erros IS----------");
			e.printStackTrace();
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	// delete function for the flight booking
	@Override
	public int deleteFlightBooking(String flightBookingId) throws ClassNotFoundException, SQLException, IOException {

//		con = DbConnection.dbConnection();
//
//		Statement stmt = con.createStatement();
//		int rows = stmt.executeUpdate("delete from FLIGHTBOOKING where flightBookingId =" + flightBookingId);
//		// Process Results
//
//		con.close();
//		return rows;
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			FlightBooking flightbooking = (FlightBooking) session.get(FlightBooking.class, flightBookingId);
			if (flightbooking != null) {
				session.delete(flightbooking);
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
