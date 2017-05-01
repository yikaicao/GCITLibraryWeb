package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class BookDAO extends BaseDAO {
	public BookDAO(Connection conn) {
		super(conn);
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAllBooks() throws SQLException {
		return (List<Book>) read("select * from tbl_book", null);
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooksAtBranch(Integer branchId) throws SQLException {
		return (List<Book>) read(
				"select * from tbl_book where bookid IN (Select bookid from tbl_book_copies where branchId = ?)",
				new Object[] { branchId });
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAllBooks(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return (List<Book>) read("select * from tbl_book", null);
	}

	public Integer getNumOfCopiesAtBranch(Integer branchId, Integer bookId) throws SQLException {

		String query = "select * from tbl_book_copies where branchId = ? AND bookid = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, branchId);
		pstmt.setInt(2, bookId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			return rs.getInt("noOfCopies");

		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		AuthorDAO auDAO = new AuthorDAO(conn);
		while (rs.next()) {
			Book b = new Book();
			b.setTitle(rs.getString("title"));
			b.setBookId(rs.getInt("bookId"));
			b.setAuthors((List<Author>) auDAO.readFirstLevel(
					"select * from tbl_author where authorId IN (Select authorId from tbl_book_authors where bookId = ?)",
					new Object[] { b.getBookId() }));
			books.add(b);
		}
		return books;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setTitle(rs.getString("title"));
			b.setBookId(rs.getInt("bookId"));
			books.add(b);
		}
		return books;
	}

	public Integer getBooksCount() throws ClassNotFoundException, SQLException {
		return readCount("select count(*) as COUNT from tbl_book", null);
	}

	public Integer addBook(Book book) throws ClassNotFoundException, SQLException {
		return save("insert into tbl_book (title) values (?)", new Object[] { book.getTitle() });
	}

	public void addBookAuthors(Integer bookId, List<Author> authorList) throws ClassNotFoundException, SQLException {

		for (Author au : authorList) {
			save("insert into tbl_book_authors (bookId, authorId) values (?, ?)",
					new Object[] { bookId, au.getAuthorId() });
		}
	}

	public void addBookGenres(Integer newBookId, List<Genre> genreList) throws ClassNotFoundException, SQLException {

		for (Genre gn : genreList) {
			save("insert into tbl_book_genres (genre_id, bookId) values (?, ?)",
					new Object[] { gn.getGenreId(), newBookId });
		}
	}

	public void addBookPublisher(Integer newBookId, Publisher pub) throws ClassNotFoundException, SQLException {
		save("update tbl_book set pubId = ? where bookId = ?", new Object[] { pub.getPubId(), newBookId });
	}

	@SuppressWarnings("unchecked")
	public Book readBookByID(Integer bookId) throws SQLException {
		List<Book> books = (List<Book>) read("select * from tbl_book where bookId = ?", new Object[] { bookId });
		if (books != null && !books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}

	public void updateBookAuthors(Integer bookId, List<Author> authorList) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_authors where bookId = ?", new Object[] { bookId });
		addBookAuthors(bookId, authorList);
	}

	public void updateBookGenres(Integer bookId, List<Genre> genreList) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_genres where bookId = ?", new Object[] { bookId });
		addBookGenres(bookId, genreList);
	}

	public void updateBookPublisher(Integer bookId, Publisher pb) throws ClassNotFoundException, SQLException {
		addBookPublisher(bookId, pb);
	}

	public void deleteBookById(int bookId) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book where bookId = ?", new Object[] { Integer.valueOf(bookId) });
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAllBooksByName(Integer pageNo, String searchString) throws SQLException {
		setPageNo(pageNo);
		searchString = "%" + searchString + "%";
		return (List<Book>) read("select * from tbl_book where title like ?", new Object[] { searchString });
	}
	

	@SuppressWarnings("unchecked")
	public List<Book> readAllBooksByName(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (List<Book>) readFirstLevel("select * from tbl_book where title like ?", new Object[] { searchString });
	}

}
