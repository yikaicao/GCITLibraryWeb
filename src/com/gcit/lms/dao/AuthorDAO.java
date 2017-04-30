package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AuthorDAO extends BaseDAO {
	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthorById(int authorId) throws ClassNotFoundException, SQLException {
		save("delete from tbl_author where authorid = ?", new Object[] { Integer.valueOf(authorId) });
	}

	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException {
		return readCount("select count(*) as COUNT from tbl_author", null);
	}

	public List<Author> readAllAuthors(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return (List<Author>) read("select * from tbl_author", null);
	}

	public List<Author> readAllAuthors() throws SQLException {
		return (List<Author>) readFirstLevel("select * from tbl_author", null);
	}

	public List<Author> readAllAuthorsByName(String authorName) throws SQLException {
		authorName = "%" + authorName + "%";
		return (List<Author>) readFirstLevel("select * from tbl_author where authorName like ?",
				new Object[] { authorName });
	}

	public List<Author> readAuthorsByName(Integer pageNo, String authorName) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		authorName = "%" + authorName + "%";
		return (List<Author>) read("select * from tbl_author where authorName like ?", new Object[] { authorName });
	}

	@SuppressWarnings("unchecked")
	public Author readAuthorByID(Integer authorID) throws ClassNotFoundException, SQLException {
		List<Author> authors = (List<Author>) read("select * from tbl_author where authorId = ?",
				new Object[] { authorID });
		if (authors != null && !authors.isEmpty()) {
			return authors.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		BookDAO bDAO = new BookDAO(conn);
		while (rs.next()) {
			Author au = new Author();
			au.setAuthorId(rs.getInt("authorId"));
			au.setAuthorName(rs.getString("authorName"));
			au.setBooks((List<Book>) bDAO.readFirstLevel(
					"select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)",
					new Object[] { au.getAuthorId() }));
			authors.add(au);
		}
		return authors;
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author au = new Author();
			au.setAuthorId(rs.getInt("authorId"));
			au.setAuthorName(rs.getString("authorName"));
			authors.add(au);
		}
		return authors;
	}

}
