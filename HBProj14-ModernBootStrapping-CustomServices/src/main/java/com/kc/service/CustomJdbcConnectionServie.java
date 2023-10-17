package com.kc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class CustomJdbcConnectionServie implements ConnectionProvider {

	@Override
	public boolean isUnwrappableAs(Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeConnection(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("CustomJdbcConnectionServie.closeConnection()");
		con.close();

	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("CustomJdbcConnectionServie.getConnection()");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Arunkc9900");
		return con;
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}
