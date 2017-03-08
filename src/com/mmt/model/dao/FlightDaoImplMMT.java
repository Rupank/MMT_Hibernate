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

public class FlightDaoImplMMT implements FlightDaoMMT {
	//Connection con;
	private Configuration cfg;
	private SessionFactory factory;
	private Transaction tx;

	public FlightDaoImplMMT() {
		cfg = new AnnotationConfiguration();
		cfg.configure();
		factory = cfg.buildSessionFactory();
		tx = null;
	}

	// insert function for the flight details
	@Override
	public int insertFlight(Flight flight) throws ClassNotFoundException, SQLException, IOException {

		// int row=0;
		// con=DbConnection.dbConnection();
		// PreparedStatement pst=con.prepareStatement("insert into flight
		// values(?,?,?,?,?,?,?,?)");
		//
		// pst.setString(1, flight.getFlightCompanyName());
		// pst.setString(2, flight.getFlightId());
		// pst.setString(3, flight.getFlightSource());
		// pst.setString(4, flight.getFlightDestination());
		// pst.setString(5, flight.getFlightDepartureTime());
		// pst.setString(6, flight.getFlightArrivalTime());
		// pst.setDouble(7, flight.getFlightTicketPrice());
		// pst.setInt(8, flight.getAvailableSeats());
		// row=pst.executeUpdate();
		// con.close();
		// return row;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(flight);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}

	}

	// delete function to delete flight
	@Override
	public int deleteFlight(String flightId) throws ClassNotFoundException, SQLException, IOException {
		// con = DbConnection.dbConnection();
		// // Query
		// int row = 0;
		//
		// PreparedStatement pst = con.prepareStatement("delete from flight
		// where flightId=?");
		//
		// pst.setString(1, flightId);
		//
		// row = pst.executeUpdate();
		// con.close();
		//
		// return row;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightId);
			if (flight != null) {
				session.delete(flight);
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

	// function to update flight
	@Override
	public int updateFlight(String flightId, Flight newflight)
			throws ClassNotFoundException, SQLException, IOException {
		// Connection con = DbConnection.dbConnection();
		// int row = 0;
		// // Query
		// // Statement stmt=con.createStatement();
		//
		// PreparedStatement pst = con.prepareStatement(
		// "update flight set FlightCompanyName=?, flightId=?, flightSource=?,
		// flightDestination=?,flightDepartureTime=?,flightArrivalTime=?,flightTicketPrice=?
		// ,availableSeats=? where flightId=?");
		//
		// pst.setString(1, newflight.getFlightCompanyName());
		// pst.setString(2, newflight.getFlightId());
		// pst.setString(3, newflight.getFlightSource());
		// pst.setString(4, newflight.getFlightDestination());
		// pst.setString(5, newflight.getFlightDepartureTime());
		// pst.setString(6, newflight.getFlightArrivalTime());
		// pst.setDouble(7, newflight.getFlightTicketPrice());
		// pst.setInt(8, newflight.getAvailableSeats());
		// pst.setString(9, flightId);
		// row = pst.executeUpdate();
		//
		// con.close();
		// return row;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightId);
			if (flight != null) {
				flight.setAvailableSeats(newflight.getAvailableSeats());
				flight.setFlightCompanyName(newflight.getFlightCompanyName());
				flight.setFlightId(newflight.getFlightId());
				flight.setFlightSource(newflight.getFlightSource());
				flight.setFlightDestination(newflight.getFlightDestination());
				flight.setFlightDepartureTime(newflight.getFlightDepartureTime());
				flight.setFlightArrivalTime(newflight.getFlightArrivalTime());
				flight.setFlightTicketPrice(newflight.getFlightTicketPrice());
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

	// search function to search flight
	@Override
	public Flight searchFlight(String flightId) throws ClassNotFoundException, SQLException, IOException {
		// Flight flight = new Flight();
		// Connection con = DbConnection.dbConnection();
		//
		// PreparedStatement pst = con.prepareStatement("select * from Flight
		// where flightId=?");
		//
		// pst.setString(1, flightId);
		// ResultSet rs = pst.executeQuery();
		// if (rs.next()) {
		// flight.setFlightCompanyName(rs.getString("flightCompanyName"));
		// flight.setFlightId(rs.getString("flightId"));
		// flight.setFlightSource(rs.getString("flightSource"));
		// flight.setFlightDestination(rs.getString("flightDestination"));
		// flight.setFlightDepartureTime(rs.getString("flightDepartureTime"));
		// flight.setFlightArrivalTime(rs.getString("flightArrivalTime"));
		// flight.setFlightTicketPrice(rs.getDouble("flightTicketPrice"));
		// flight.setAvailableSeats(rs.getInt("availableSeats"));
		//
		// }
		// con.close();
		//
		// return flight;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightId);
			if (flight != null) {
				// tx.commit(); Not Required but wont give any error if used
				return flight;
			} else
				return null;

		} catch (Exception e) {
			tx.rollback();
			return null;
		} finally {
			session.close();
		}

	}

	// display function to display all details of flight

	@Override
	public ArrayList<Flight> displayFlight() throws ClassNotFoundException, SQLException, IOException {
		// ArrayList<Flight> F = new ArrayList<Flight>();
		// Flight f;
		// con = DbConnection.dbConnection();
		// // Query
		// Statement stmt = con.createStatement();
		// ResultSet rs = stmt.executeQuery("select * from Flight");
		// // Process Results
		// while (rs.next()) {
		// f = new Flight();
		// f.setFlightCompanyName(rs.getString("flightCompanyName"));
		// f.setFlightId(rs.getString("flightId"));
		// f.setFlightSource(rs.getString("flightSource"));
		// f.setFlightDestination(rs.getString("flightDestination"));
		// f.setFlightDepartureTime(rs.getString("flightDepartureTime"));
		// f.setFlightArrivalTime(rs.getString("flightArrivalTime"));
		// f.setFlightTicketPrice(rs.getDouble("flightTicketPrice"));
		// f.setAvailableSeats(rs.getInt("availableSeats"));
		// F.add(f);
		// }
		// con.close();
		//
		// return F;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Flight");
			List<Flight> fList = query.list();

			return (ArrayList<Flight>) fList;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			session.close();
		}

	}

	// search flight on the basis of source and destination

	@Override
	public ArrayList<Flight> searchFlight(String flightSource, String flightDestination)
			throws ClassNotFoundException, SQLException, IOException {
		// Flight f = new Flight();
		// ArrayList<Flight> F = new ArrayList<Flight>();
		// con = DbConnection.dbConnection();
		// // Query
		// PreparedStatement pst;
		// pst = con.prepareStatement("select * from Flight where flightSource=?
		// AND flightDestination=?");
		// pst.setString(1, flightSource);
		// pst.setString(2, flightDestination);
		// ResultSet rs = pst.executeQuery();
		// // Process Results
		// while (rs.next()) {
		// f.setFlightCompanyName(rs.getString("flightCompanyName"));
		// f.setFlightId(rs.getString("flightId"));
		// f.setFlightSource(rs.getString("flightSource"));
		// f.setFlightDestination(rs.getString("flightDestination"));
		// f.setFlightDepartureTime(rs.getString("flightDepartureTime"));
		// f.setFlightArrivalTime(rs.getString("flightArrivalTime"));
		// f.setFlightTicketPrice(rs.getDouble("flightTicketPrice"));
		// f.setAvailableSeats(rs.getInt("availableSeats"));
		// F.add(f);
		//
		// }
		// con.close();
		//
		// return F;

		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			//System.out.println("1");
			Query query = session.createQuery("from Flight where Flightsource= ? AND Flightdestination= ? ");
			//System.out.println("2");
			query.setString(0, flightSource);
			query.setString(1, flightDestination);
			List<Flight> flightList = query.list();
			System.out.println(flightList);
			return (ArrayList<Flight>) flightList;

		} catch (Exception e) {
			tx.rollback();
			return null;
		} finally {
			session.close();
		}

	}
}
