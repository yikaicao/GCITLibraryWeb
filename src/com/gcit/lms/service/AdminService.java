package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

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

	public void addBook(Book book) throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			bkDAO.addBook(book);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void addBook(Book book, List<Author> authorList, List<Genre> genreList, Publisher pb) throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);

			// add a new book to tbl_book
			Integer newBookId = bkDAO.addBook(book);

			// add book author relation
			bkDAO.addBookAuthors(newBookId, authorList);

			// add book genre relation
			bkDAO.addBookGenres(newBookId, genreList);

			// add book publisher relation
			bkDAO.addBookPublisher(newBookId, pb);

			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void updateBook(Book book, List<Author> authorList, List<Genre> genreList, Publisher pb)
			throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			Integer bookId = book.getBookId();

			// update book author relation
			bkDAO.updateBookAuthors(bookId, authorList);

			// add book genre relation
			bkDAO.updateBookGenres(bookId, genreList);

			// add book publisher relation
			bkDAO.updateBookPublisher(bookId, pb);

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

	public List<Genre> getAllGenres() throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			GenreDAO gnDAO = new GenreDAO(conn);
			return gnDAO.readAllGenres();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;

	}

	public List<Publisher> getAllPublishers() throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			return pbDAO.readAllPublishers();

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

	public Book getBookByPk(Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.readBookByID(bookId);
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

	public void deleteBookById(int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			bkDAO.deleteBookById(bookId);
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
