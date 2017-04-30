package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO {
	public PublisherDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> pubs = new ArrayList<>();

		while (rs.next()) {
			Publisher pb = new Publisher();
			pb.setPubId(rs.getInt("publisherId"));
			pb.setPubName(rs.getString("publisherName"));
			pb.setPubAddr(rs.getString("publisherAddress"));
			pb.setPubPhone(rs.getString("publisherPhone"));

			pubs.add(pb);
		}
		return pubs;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException {

		List<Publisher> pubs = new ArrayList<>();

		while (rs.next()) {
			Publisher pb = new Publisher();
			pb.setPubId(rs.getInt("publisherId"));
			pb.setPubName(rs.getString("publisherName"));
			pb.setPubAddr(rs.getString("publisherAddress"));
			pb.setPubPhone(rs.getString("publisherPhone"));

			pubs.add(pb);
		}
		return pubs;
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAllPublishers() throws SQLException {
		return (List<Publisher>) read("select * from tbl_publisher", null);

	}
}
