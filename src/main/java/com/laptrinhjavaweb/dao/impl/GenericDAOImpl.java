package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.common.util.concurrent.Service.State;
import com.laptrinhjavaweb.constant.Database;
import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class GenericDAOImpl<T> implements GenericDAO<T> {
	
	ResourceBundle bundle = ResourceBundle.getBundle("db");
	
	public static Connection conn = null;
	public static PreparedStatement statement = null;
	public static ResultSet rs = null;

	protected  Connection getConnection() {
		// url connect database
		String url = bundle.getString("url");
		// username for Mysql
		String user = bundle.getString("user");
		// password for Mysql
		String password = bundle.getString("password");

		try {
			// Load Driver Mysql
			Class.forName(bundle.getString("driverName"));
			// return connection
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// Connect falied
			return null;
		}

	}

	protected static void closeConnection(Connection conn, PreparedStatement statement, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> result = new ArrayList<>();
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			rs = statement.executeQuery();
			while (rs.next()) {
				result.add(rowMapper.mapRow(rs));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection(conn, statement, rs);
		}
	}

	private void setParameters(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String sql, Object... parameters) {
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			closeConnection(conn, statement, rs);
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		try {
			Long id = null;
			conn = getConnection();
			conn.setAutoCommit(false);
			// you must add this code here to return id the record after insert
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				//get first row of record after insert 
				id = rs.getLong(1);
			}
			conn.commit();
			return id;
		} catch (SQLException e) {
			if (conn != null)
				try {
					conn.rollback();
					e.printStackTrace();
					return null;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

		} finally {
			closeConnection(conn, statement, rs);
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Integer count = 0;
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			rs = statement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			closeConnection(conn, statement, rs);
		}
	}
}
