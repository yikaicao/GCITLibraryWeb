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

	public Integer getPublishersCount() throws ClassNotFoundException, SQLException {
		return readCount("select count(*) as COUNT from tbl_publisher", null);
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAllPublishers(Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		return (List<Publisher>) read("select * from tbl_publisher", null);
	}

	@SuppressWarnings("unchecked")
	public Publisher readPublisherByID(Integer pubId) throws SQLException {
		List<Publisher> pubs = (List<Publisher>) read("select * from tbl_publisher where publisherId = ?",
				new Object[] { pubId });
		if (pubs != null && !pubs.isEmpty())
			return pubs.get(0);
		return null;
	}

	public void updatePublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?",
				new Object[] { pb.getPubName(), pb.getPubAddr(), pb.getPubPhone(), pb.getPubId() });
	}

	public void addPublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)",
				new Object[] { pb.getPubName(), pb.getPubAddr(), pb.getPubPhone() });
	}

	public void deletePublisherById(int pubId) throws ClassNotFoundException, SQLException {
		save("delete from tbl_publisher where publisherId = ?", new Object[] { Integer.valueOf(pubId) });
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAllPublisherByName(Integer pageNo, String searchString) throws SQLException {
		setPageNo(pageNo);
		searchString = "%" + searchString + "%";
		return (List<Publisher>) read("select * from tbl_publisher where publisherName like ?",
				new Object[] { searchString });
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAllPublisherByName(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (List<Publisher>) readFirstLevel("select * from tbl_publisher where publisherName like ?",
				new Object[] { searchString });
	}
}
