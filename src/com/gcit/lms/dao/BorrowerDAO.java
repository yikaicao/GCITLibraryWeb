package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO {
	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();

		while (rs.next()) {
			Borrower bo = new Borrower();
			bo.setCardNo(rs.getInt("cardNo"));
			bo.setName(rs.getString("name"));
			bo.setAddress(rs.getString("address"));
			bo.setPhone(rs.getString("phone"));

			borrowers.add(bo);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();

		while (rs.next()) {
			Borrower bo = new Borrower();
			bo.setCardNo(rs.getInt("cardNo"));
			bo.setName(rs.getString("name"));
			bo.setAddress(rs.getString("address"));
			bo.setPhone(rs.getString("phone"));

			borrowers.add(bo);
		}
		return borrowers;
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAllBorrowers(Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		return (List<Borrower>) read("select * from tbl_borrower", null);
	}

	public void addBorrower(Borrower br) throws ClassNotFoundException, SQLException {
		save("insert into tbl_borrower (name, address, phone) values (?, ?, ?)",
				new Object[] { br.getName(), br.getAddress(), br.getPhone() });
	}

	@SuppressWarnings("unchecked")
	public Borrower readBorrowerByID(Integer pk) throws SQLException {
		List<Borrower> borrowers = (List<Borrower>) read("select * from tbl_borrower where cardNo = ?",
				new Object[] { pk });
		if (borrowers != null && !borrowers.isEmpty())
			return borrowers.get(0);
		return null;
	}

	public void updateBorrower(Borrower bo) throws ClassNotFoundException, SQLException {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { bo.getName(), bo.getAddress(), bo.getPhone(), bo.getCardNo() });
	}

	public void deleteBorrowerById(int pk) throws ClassNotFoundException, SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] { Integer.valueOf(pk) });
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAllBorrowersByName(Integer pageNo, String searchString) throws SQLException {
		setPageNo(pageNo);
		searchString = "%" + searchString + "%";
		return (List<Borrower>) read("select * from tbl_borrower where name like ?", new Object[] { searchString });
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAllBorrowersByName(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (List<Borrower>) readFirstLevel("select * from tbl_borrower where name like ?",
				new Object[] { searchString });
	}

	public Integer getBorrowersCount() throws ClassNotFoundException, SQLException {
		return readCount("select count(*) as COUNT from tbl_borrower", null);
	}

	@SuppressWarnings("unchecked")
	public boolean validateCardNo(Integer cardNo) throws SQLException {
		List<Borrower> borrowerList = (List<Borrower>) read("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		if (borrowerList == null ||borrowerList.isEmpty()){
			return false;
		}
		return true;
	}

}
