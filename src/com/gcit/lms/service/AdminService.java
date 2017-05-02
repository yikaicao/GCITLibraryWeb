package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
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

	public List<Branch> getAllBranchesOnPage(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			return brDAO.readAllBranches(pageNo);
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

	public List<Publisher> getAllPublishersOnPage(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			return pbDAO.readAllPublishers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Borrower> getAllBorrowersOnPage(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			return boDAO.readAllBorrowers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<BookLoan> getAllBookLoansOnPage(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			return blDAO.readAllBookLoans(pageNo);
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

	public Publisher getPublisherByPk(Integer pubId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			return pbDAO.readPublisherByID(pubId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Branch getBranchByPk(Integer pk) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			return brDAO.readBranchByID(pk);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Borrower getBorrowerByPk(Integer pk) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			return boDAO.readBorrowerByID(pk);
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

	public List<Book> getBooksByName(Integer pageNo, String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.readAllBooksByName(pageNo, searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Book> getBooksByName(String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.readAllBooksByName(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Integer getCount(String category) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();

			switch (category) {
			case "publisher":
				PublisherDAO pbDAO = new PublisherDAO(conn);
				return pbDAO.getPublishersCount();
			case "book":
				BookDAO bkDAO = new BookDAO(conn);
				return bkDAO.getBooksCount();
			case "author":
				AuthorDAO auDAO = new AuthorDAO(conn);
				return auDAO.getAuthorsCount();
			case "branch":
				BranchDAO brDAO = new BranchDAO(conn);
				return brDAO.getBranchesCount();
			case "borrower":
				BorrowerDAO boDAO = new BorrowerDAO(conn);
				return boDAO.getBorrowersCount();
			case "bookloan":
				BookLoanDAO blDAO = new BookLoanDAO(conn);
				return blDAO.getBorrowersCount();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		System.out.println("AdminService.getCount(): invalid input");
		return null;
	}

	public void editPublisher(Publisher pb) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			pbDAO.updatePublisher(pb);
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

	public void addPublisher(Publisher pb) throws SQLException {

		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			pbDAO.addPublisher(pb);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void deletePublisherById(int pubId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			pbDAO.deletePublisherById(pubId);
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

	public List<Publisher> getPublisherByName(Integer pageNo, String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			return pbDAO.readAllPublisherByName(pageNo, searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Publisher> getPublisherByName(String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO pbDAO = new PublisherDAO(conn);
			return pbDAO.readAllPublisherByName(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void addBranch(Branch br) throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			brDAO.addBranch(br);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void editBranch(Branch br) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			brDAO.updateBranch(br);
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

	public void deleteBranchById(int pk) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			brDAO.deleteBranchById(pk);
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

	public List<Branch> getBranchesByName(Integer pageNo, String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			return brDAO.readAllBranchesByName(pageNo, searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Branch> getBranchesByName(String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			return brDAO.readAllBranchesByName(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void addBorrower(Borrower br) throws SQLException {

		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			boDAO.addBorrower(br);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void editBorrower(Borrower bo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			boDAO.updateBorrower(bo);
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

	public void deleteBorrowerById(int pk) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			boDAO.deleteBorrowerById(pk);
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

	public List<Borrower> getBorrowersByName(Integer pageNo, String searchString) throws SQLException {

		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			return boDAO.readAllBorrowersByName(pageNo, searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Borrower> getBorrowersByName(String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			return boDAO.readAllBorrowersByName(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void updateDueDate(BookLoan bl) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			blDAO.updateDueDate(bl);
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

	public void returnBookLoan(BookLoan bl) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			blDAO.returnBookLoan(bl);
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

	public List<Book> getAllBooksAtBranch(Integer branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.getBooksAtBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public Integer getAllNumOfCopies(Integer bookId, Integer branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bkDAO = new BookDAO(conn);
			return bkDAO.getNumOfCopiesAtBranch(branchId, bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void updateBookCopies(Integer branchId, Integer bookId, Integer quantity) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			brDAO.updateBookCopies(branchId, bookId, quantity);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean validateBorrower(Integer cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO boDAO = new BorrowerDAO(conn);
			return boDAO.validateCardNo(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return false;
	}

	public void checkoutBook(Integer cardNo, Integer bookId, Integer branchId) throws SQLException {
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO brDAO = new BranchDAO(conn);
			BookLoanDAO blDAO = new BookLoanDAO(conn);
			brDAO.decCopies(bookId, branchId);
			blDAO.addBookLoan(bookId, branchId, cardNo);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
