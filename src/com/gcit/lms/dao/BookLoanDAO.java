package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookLoan;

public class BookLoanDAO extends BaseDAO {
	public BookLoanDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> bkList = new ArrayList<>();

		while (rs.next()) {
			BookLoan bl = new BookLoan();
			bl.setBookId(rs.getInt("bookId"));
			bl.setBranchId(rs.getInt("branchId"));
			bl.setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));

			bkList.add(bl);
		}
		return bkList;
	}

	@Override
	public List<BookLoan> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookLoan> bkList = new ArrayList<>();

		while (rs.next()) {
			BookLoan bl = new BookLoan();
			bl.setBookId(rs.getInt("bookId"));
			bl.setBranchId(rs.getInt("branchId"));
			bl.setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));

			bkList.add(bl);
		}
		return bkList;
	}

	public Integer getBorrowersCount() throws ClassNotFoundException, SQLException {
		return readCount("select count(*) as COUNT from tbl_book_loans", null);
	}

	@SuppressWarnings("unchecked")
	public List<BookLoan> readAllBookLoans(Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		return (List<BookLoan>) read("select * from tbl_book_loans", null);
	}

	public void updateDueDate(BookLoan bl) throws ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bl.getDueDate(), bl.getBookId(), bl.getBranchId(), bl.getCardNo() });
	}

	public void returnBookLoan(BookLoan bl) throws ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dateIn = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bl.getDateIn(), bl.getBookId(), bl.getBranchId(), bl.getCardNo() });

	}

	@SuppressWarnings("unchecked")
	public void addBookLoan(Integer bookId, Integer branchId, Integer cardNo)
			throws ClassNotFoundException, SQLException {

		List<BookLoan> blList = (List<BookLoan>) read(
				"select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookId, branchId, cardNo });
		for (BookLoan bl : blList) {
			if (bl.getDateIn() == null)
				throw new SQLException();
		}

		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookId, branchId, cardNo });
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, CURDATE(), DATE_ADD(CURDATE(),INTERVAL 7 DAY))",
				new Object[] { bookId, branchId, cardNo });
	}
}
