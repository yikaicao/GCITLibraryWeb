package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO {
	public Connection conn = null;

	// for pagination
	private Integer pageNo;
	private Integer pageSize = 10;

	public BaseDAO(Connection conn) {
		this.conn = conn;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * Prepare a general saving statement based on
	 * 
	 * @param query
	 *            and
	 * @param vals,
	 *            then execute the update.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Integer save(String query, Object[] vals) throws ClassNotFoundException, SQLException {

		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		if (vals != null) {
			int count = 1;
			for (Object obj : vals) {
				pstmt.setObject(count, obj);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();

		Integer generatedKey = null;
		if (rs.next())
			generatedKey = Integer.valueOf(rs.getInt(1));

		return generatedKey;
	}

	/**
	 * Prepare a general reading statement based on
	 * 
	 * @param query
	 *            and
	 * @param vals,
	 *            then execute the update.
	 * @return
	 * @throws SQLException
	 */
	public List<?> read(String query, Object[] vals) throws SQLException {
		Integer index = 0;
		if (getPageNo() != null) {
			index = (getPageNo() - 1) * 10;
		}
		query = query + " LIMIT " + index + ", " + pageSize;
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object obj : vals) {
				pstmt.setObject(count, obj);
				count++;
			}
		}

		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}

	/**
	 * Prepare a general reading statement based on
	 * 
	 * @param query
	 *            and
	 * @param vals,
	 *            then execute the update.
	 * @return
	 * @throws SQLException
	 * 
	 * @note This is used for first level of reading.
	 */
	public List<?> readFirstLevel(String query, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object obj : vals) {
				pstmt.setObject(count, obj);
				count++;
			}
		}

		ResultSet rs = pstmt.executeQuery();
		return extractDataFirstLevel(rs);
	}

	public Integer readCount(String query, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (vals != null) {
			int count = 1;
			for (Object obj : vals) {
				pstmt.setObject(count, obj);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("COUNT");
		}
		return null;
	}

	public abstract List<?> extractData(ResultSet rs) throws SQLException;

	public abstract List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
}
