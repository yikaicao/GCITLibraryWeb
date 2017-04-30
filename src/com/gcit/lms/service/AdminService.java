package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AdminService {

	/**
	 * Add given passed by
	 * 
	 * @param author
	 * @throws ClassNotFoundException
	 */
	public void addAuthor(Author author) throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO auDAO = new AuthorDAO(conn);
			auDAO.addAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 
	 * @return List of all authors stored in library database
	 * @throws SQLException
	 */
	public List<Author> getAllAtuhors() throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO auDAO = new AuthorDAO(conn);
			return (List<Author>) auDAO.readAllAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}

	public List<Author> getAllAuthorsOnPage(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.readAllAuthors(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public List<Book> getAllBooksOnPage(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.readAllBooks(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * @return List of all books stored in library database
	 * @throws SQLException
	 */
	public List<Book> getAllBooks() throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return (List<Book>) bkDAO.readAllBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}

	public Integer getAuthorsCount() throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.getAuthorsCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getBooksCount() throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.getBooksCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Author getAuthorByPk(Integer authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO auDAO = new AuthorDAO(conn);
			return auDAO.readAuthorByID(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void editAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Author> getAuthorsByName(Integer pageNo, String authorName) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO auDAO = new AuthorDAO(conn);
			return auDAO.readAuthorsByName(pageNo, authorName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void deleteAuthorById(int authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO auDAO = new AuthorDAO(conn);
			auDAO.deleteAuthorById(authorId);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Author> getAuthorsByName(String authorName) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO auDAO = new AuthorDAO(conn);
			return auDAO.readAllAuthorsByName(authorName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

}
