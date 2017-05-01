package com.gcit.lms.web;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/editAuthor", "/deleteAuthor", "/pageAuthors", "/searchAuthors", "/addBook", "/editBook",
		"/deleteBook", "/pageBooks", "/searchBooks", "/addPublisher", "/editPublisher", "/deletePublisher",
		"/pagePublishers", "/searchPublishers", "/addBranch", "/editBranch", "/deleteBranch", "/pageBranches",
		"/searchBranches", "/addBorrower", "/editBorrower", "/deleteBorrower", "/pageBorrowers", "/searchBorrowers",
		"/editDueDate", "/returnBookLoan" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());

		String forwardPath = "/welcome.jsp";
		Boolean isAjax = Boolean.FALSE;
		String data = null;
		switch (reqUrl) {
		case "/pageAuthors":
			pageAuthors(request);
			forwardPath = "/authors.jsp";
			break;
		case "/searchAuthors":
			data = searchAuthors(request);
			response.getWriter().write(data);
			isAjax = Boolean.TRUE;
			break;
		case "/pageBooks":
			pageBooks(request);
			forwardPath = "/books.jsp";
			break;
		case "/searchBooks":
			data = searchBooks(request);
			response.getWriter().write(data);
			isAjax = Boolean.TRUE;
			break;
		case "/pagePublishers":
			pagePublishers(request);
			forwardPath = "/publishers.jsp";
			break;
		case "/searchPublishers":
			data = searchPublishers(request);
			response.getWriter().write(data);
			isAjax = Boolean.TRUE;
			break;
		case "/pageBranches":
			pageBranches(request);
			forwardPath = "/branches.jsp";
			break;
		case "/searchBranches":
			data = searchBranches(request);
			response.getWriter().write(data);
			isAjax = Boolean.TRUE;
			break;
		case "/pageBorrowers":
			pageBorrowers(request);
			forwardPath = "/borrowers.jsp";
			break;
		case "/searchBorrowers":
			data = searchBorrowers(request);
			response.getWriter().write(data);
			isAjax = Boolean.TRUE;
			break;
		}

		if (!isAjax) {
			RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
			rd.forward(request, response);
		} else {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPath = "/welcome.jsp";
		switch (reqUrl) {
		case "/addAuthor":
			addAuthor(request);
			forwardPath = "/authors.jsp";
			break;
		case "/editAuthor":
			editAuthor(request);
			forwardPath = "/authors.jsp";
			break;
		case "/deleteAuthor":
			deleteAuthor(request);
			forwardPath = "/authors.jsp";
			break;
		case "/addBook":
			addBook(request);
			forwardPath = "/books.jsp";
			break;
		case "/editBook":
			editBook(request);
			forwardPath = "/books.jsp";
			break;
		case "/deleteBook":
			deleteBook(request);
			forwardPath = "/books.jsp";
			break;
		case "/addPublisher":
			addPublisher(request);
			forwardPath = "/publishers.jsp";
			break;
		case "/editPublisher":
			editPublisher(request);
			forwardPath = "/publishers.jsp";
			break;
		case "/deletePublisher":
			deletePublisher(request);
			forwardPath = "/publishers.jsp";
			break;
		case "/addBranch":
			addBranch(request);
			forwardPath = "/branches.jsp";
			break;
		case "/editBranch":
			editBranch(request);
			forwardPath = "/branches.jsp";
			if (request.getParameter("redirect") == "librarian")
				System.out.println("YOOOOOOOO");
			break;
		case "/deleteBranch":
			deleteBranch(request);
			forwardPath = "/branches.jsp";
			break;
		case "/addBorrower":
			addBorrower(request);
			forwardPath = "/borrowers.jsp";
			break;
		case "/editBorrower":
			editBorrower(request);
			forwardPath = "/borrowers.jsp";
			break;
		case "/deleteBorrower":
			deleteBorrower(request);
			forwardPath = "/borrowers.jsp";
			break;
		case "/editDueDate":
			editDueDate(request);
			forwardPath = "/bookloans.jsp";
			break;
		case "/returnBookLoan":
			returnBookLoan(request);
			forwardPath = "/bookloans.jsp";
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private void returnBookLoan(HttpServletRequest request) {
		BookLoan bl = new BookLoan();
		bl.setBookId(Integer.valueOf(request.getParameter("bookId")));
		bl.setBranchId(Integer.valueOf(request.getParameter("branchId")));
		bl.setCardNo(Integer.valueOf(request.getParameter("cardNo")));
		Calendar calendar = Calendar.getInstance();
		bl.setDateIn(calendar.getTime());
		
		AdminService service = new AdminService();
		
		try {
			service.returnBookLoan(bl);
			request.setAttribute("message", "Book Returned");
		} catch (SQLException e) {
			request.setAttribute("message", "Book Return Failed");
			e.printStackTrace();
		}
	}

	private void editDueDate(HttpServletRequest request) {
		BookLoan bl = new BookLoan();
		bl.setBookId(Integer.valueOf(request.getParameter("bookId")));
		bl.setBranchId(Integer.valueOf(request.getParameter("branchId")));
		bl.setCardNo(Integer.valueOf(request.getParameter("cardNo")));
		bl.setDueDate(Date.valueOf(request.getParameter("newDueDate")));
		
		AdminService service = new AdminService();

		try {
			service.updateDueDate(bl);
			request.setAttribute("message", "Due Date Changed");
		} catch (SQLException e) {
			request.setAttribute("message", "Change Due Date Failed");
			e.printStackTrace();
		}
	}

	private void addAuthor(HttpServletRequest request) {
		Author author = new Author();
		author.setAuthorName(request.getParameter("authorName"));
		AdminService service = new AdminService();
		try {
			service.addAuthor(author);
			request.setAttribute("message", "Add Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Add Failed");
			e.printStackTrace();
		}
	}

	private void editAuthor(HttpServletRequest request) {
		Author author = new Author();
		author.setAuthorName(request.getParameter("authorName"));
		author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
		AdminService service = new AdminService();
		try {
			service.editAuthor(author);
			request.setAttribute("message", "Edit Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Edit Failed");
			e.printStackTrace();
		}
	}

	private void deleteAuthor(HttpServletRequest request) {
		AdminService service = new AdminService();
		try {
			service.deleteAuthorById(Integer.parseInt(request.getParameter("authorId")));
			request.setAttribute("message", "Delete Success");
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("message", "Delete Failed");
			e.printStackTrace();
		}
	}

	private void addBook(HttpServletRequest request) {
		// prepare new book's title
		Book book = new Book();
		book.setTitle(request.getParameter("bookTitle"));

		// prepare new book's author(s)
		List<Author> authorList = new ArrayList<>();
		for (String anAuthorId : request.getParameterValues("authors")) {
			Author au = new Author();
			au.setAuthorId(Integer.valueOf(anAuthorId));
			authorList.add(au);
		}

		// prepare new book's genre(s)
		List<Genre> genreList = new ArrayList<>();
		for (String aGenreId : request.getParameterValues("genres")) {
			Genre gn = new Genre();
			gn.setGenreId(Integer.valueOf(aGenreId));
			genreList.add(gn);
		}

		// prepare new book's publisher
		Publisher pb = new Publisher();
		pb.setPubId(Integer.valueOf(request.getParameter("publisher")));

		AdminService service = new AdminService();
		try {
			service.addBook(book, authorList, genreList, pb);
			request.setAttribute("message", "Add Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Add Failed");
			e.printStackTrace();
		}
	}

	private void editBook(HttpServletRequest request) {
		// prepare new book's title
		Book book = new Book();
		book.setTitle(request.getParameter("bookTitle"));
		book.setBookId(Integer.valueOf(request.getParameter("bookId")));

		// prepare new book's author(s)
		List<Author> authorList = new ArrayList<>();
		for (String anAuthorId : request.getParameterValues("authors")) {
			Author au = new Author();
			au.setAuthorId(Integer.valueOf(anAuthorId));
			authorList.add(au);
		}

		// prepare new book's genre(s)
		List<Genre> genreList = new ArrayList<>();
		for (String aGenreId : request.getParameterValues("genres")) {
			Genre gn = new Genre();
			gn.setGenreId(Integer.valueOf(aGenreId));
			genreList.add(gn);
		}

		// prepare new book's publisher
		Publisher pb = new Publisher();
		pb.setPubId(Integer.valueOf(request.getParameter("publisher")));

		AdminService service = new AdminService();
		try {
			service.updateBook(book, authorList, genreList, pb);
			request.setAttribute("message", "Edit Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Edit Failed");
			e.printStackTrace();
		}
	}

	private void deleteBook(HttpServletRequest request) {
		AdminService service = new AdminService();
		try {
			service.deleteBookById(Integer.parseInt(request.getParameter("bookId")));
			request.setAttribute("message", "Delete Success");
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("message", "Delete Failed");
			e.printStackTrace();
		}

	}

	private void addPublisher(HttpServletRequest request) {
		Publisher pb = new Publisher();

		pb.setPubName(request.getParameter("publisherName"));
		pb.setPubAddr(request.getParameter("publisherAddress"));
		pb.setPubPhone(request.getParameter("publisherPhone"));

		AdminService service = new AdminService();
		try {
			service.addPublisher(pb);
			request.setAttribute("message", "Add Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Add Failed");
			e.printStackTrace();
		}
	}

	private void editPublisher(HttpServletRequest request) {
		Publisher pb = new Publisher();
		pb.setPubId(Integer.valueOf(request.getParameter("publisherId")));
		pb.setPubName(request.getParameter("publisherName"));
		pb.setPubAddr(request.getParameter("publisherAddress"));
		pb.setPubPhone(request.getParameter("publisherPhone"));

		AdminService service = new AdminService();
		try {
			service.editPublisher(pb);
			request.setAttribute("message", "Edit Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Edit Failed");
			e.printStackTrace();
		}
	}

	private void deletePublisher(HttpServletRequest request) {
		AdminService service = new AdminService();
		try {
			service.deletePublisherById(Integer.parseInt(request.getParameter("publisherId")));
			request.setAttribute("message", "Delete Success");
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("message", "Delete Failed");
			e.printStackTrace();
		}
	}

	private void addBranch(HttpServletRequest request) {
		Branch br = new Branch();

		br.setBranchName(request.getParameter("branchName"));
		br.setBranchAddress(request.getParameter("branchAddress"));

		AdminService service = new AdminService();

		try {
			service.addBranch(br);
			request.setAttribute("message", "Add Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Add Failed");
			e.printStackTrace();
		}
	}

	private void editBranch(HttpServletRequest request) {
		Branch br = new Branch();

		br.setBranchId(Integer.valueOf(request.getParameter("branchId")));
		br.setBranchName(request.getParameter("branchName"));
		br.setBranchAddress(request.getParameter("branchAddress"));

		AdminService service = new AdminService();
		try {
			service.editBranch(br);
			request.setAttribute("message", "Edit Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Edit Failed");
			e.printStackTrace();
		}
	}

	private void deleteBranch(HttpServletRequest request) {
		AdminService service = new AdminService();
		try {
			service.deleteBranchById(Integer.parseInt(request.getParameter("branchId")));
			request.setAttribute("message", "Delete Success");
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("message", "Delete Failed");
			e.printStackTrace();
		}
	}

	private void addBorrower(HttpServletRequest request) {
		Borrower br = new Borrower();

		br.setName(request.getParameter("name"));
		br.setAddress(request.getParameter("address"));
		br.setPhone(request.getParameter("phone"));

		AdminService service = new AdminService();

		try {
			service.addBorrower(br);
			request.setAttribute("message", "Add Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Add Failed");
			e.printStackTrace();
		}
	}

	private void editBorrower(HttpServletRequest request) {
		Borrower bo = new Borrower();

		bo.setCardNo(Integer.valueOf(request.getParameter("borrowerId")));
		bo.setName(request.getParameter("borrowerName"));
		bo.setAddress(request.getParameter("borrowerAddress"));
		bo.setPhone(request.getParameter("borrowerPhone"));

		AdminService service = new AdminService();
		try {
			service.editBorrower(bo);
			request.setAttribute("message", "Edit Success");
		} catch (SQLException e) {
			request.setAttribute("message", "Edit Failed");
			e.printStackTrace();
		}
	}

	private void deleteBorrower(HttpServletRequest request) {
		AdminService service = new AdminService();
		try {
			service.deleteBorrowerById(Integer.parseInt(request.getParameter("borrowerId")));
			request.setAttribute("message", "Delete Success");
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("message", "Delete Failed");
			e.printStackTrace();
		}
	}

	private void pageAuthors(HttpServletRequest request) {
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdminService service = new AdminService();

		try {
			request.setAttribute("authors", service.getAllAuthorsOnPage(pageNo));
			request.setAttribute("pageNo", pageNo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void pageBooks(HttpServletRequest request) {
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdminService service = new AdminService();

		try {
			request.setAttribute("books", service.getAllBooksOnPage(pageNo));
			request.setAttribute("pageNo", pageNo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void pagePublishers(HttpServletRequest request) {
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdminService service = new AdminService();

		try {
			request.setAttribute("publishers", service.getAllPublishersOnPage(pageNo));
			request.setAttribute("pageNo", pageNo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void pageBranches(HttpServletRequest request) {
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdminService service = new AdminService();

		try {
			request.setAttribute("branches", service.getAllBranchesOnPage(pageNo));
			request.setAttribute("pageNo", pageNo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void pageBorrowers(HttpServletRequest request) {
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdminService service = new AdminService();

		try {
			request.setAttribute("borrowers", service.getAllBooksOnPage(pageNo));
			request.setAttribute("pageNo", pageNo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String searchAuthors(HttpServletRequest request) {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		AdminService service = new AdminService();
		StringBuffer strBuf = new StringBuffer();

		try {
			List<Author> authors = service.getAuthorsByName(pageNo, searchString);
			strBuf.append("<thead><tr><th>#</th><th>Author Name</th><th>Operation</th></tr></thead><tbody>");

			// prepare searched authors
			for (Author a : authors) {
				strBuf.append("<tr>");

				strBuf.append("<td>");
				strBuf.append(authors.indexOf(a) + 1);
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(a.getAuthorName());
				strBuf.append("</td>");

				strBuf.append(
						"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#editAuthorModal' href='modal/editauthor.jsp?authorId="
								+ a.getAuthorId() + "' style='margin-right: 2%;'>Update</button>");

				strBuf.append(
						"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#editAuthorModal' href='modal/deleteauthor.jsp?authorId="
								+ a.getAuthorId() + "'>Delete</button></td>");

				strBuf.append("</tr>");
			}
			strBuf.append("</tbody>");

			// special separator
			strBuf.append("\n");

			strBuf.append("<ul class='pagination' id='paginationList'>");

			Integer previousPageNo = Integer.valueOf(request.getParameter("pageNo")) - 1;
			Integer nextPageNo = Integer.valueOf(request.getParameter("pageNo")) + 1;
			strBuf.append("<li><a onclick='searchAuthorsPage(" + previousPageNo
					+ ")' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");

			// add new pagination for search result
			authors = service.getAuthorsByName(searchString);
			int pageCount = authors.size() / 10;
			if (authors.size() % 10 != 0)
				pageCount += 1;
			for (int i = 1; i <= pageCount; i++) {
				strBuf.append("<li><a onclick='searchAuthorsPage(" + i + ")' id='pageNav'>" + i + "</a></li>");
			}
			strBuf.append("<li><a onclick='searchAuthorsPage(" + nextPageNo
					+ ")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");

			strBuf.append("</ul");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	private String searchBooks(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		AdminService service = new AdminService();
		StringBuffer strBuf = new StringBuffer();

		try {
			List<Book> books = service.getBooksByName(pageNo, searchString);
			strBuf.append("<thead><tr><th>#</th><th>Title</th><th>Author</th><th>Operation</th></tr></thead><tbody>");

			// prepare searched books
			for (Book bk : books) {
				strBuf.append("<tr>");

				strBuf.append("<td>");
				strBuf.append(books.indexOf(bk) + 1);
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(bk.getTitle());
				strBuf.append("</td>");

				strBuf.append("<td>");

				int commaFlag = 0;
				for (Author au : bk.getAuthors()) {
					if (commaFlag == 0)
						strBuf.append(au.getAuthorName());
					else
						strBuf.append(", " + au.getAuthorName());
					commaFlag = 1;
				}

				strBuf.append("</td>");

				strBuf.append(
						"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#modalConnector' href='modal/editbook.jsp?bookId="
								+ bk.getBookId() + "' style='margin-right: 2%;'>Update</button>");

				strBuf.append(
						"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalConnector' href='modal/deletebook.jsp?bookId="
								+ bk.getBookId() + "'>Delete</button></td>");

				strBuf.append("</tr>");
			}
			strBuf.append("</tbody>");

			// special separator
			strBuf.append("\n");

			// add new pagination for search result
			strBuf.append("<ul class='pagination' id='paginationList'>");

			Integer previousPageNo = Integer.valueOf(request.getParameter("pageNo")) - 1;
			Integer nextPageNo = Integer.valueOf(request.getParameter("pageNo")) + 1;

			strBuf.append("<li><a onclick='searchBooksPage(" + previousPageNo
					+ ")' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");

			books = service.getBooksByName(searchString);
			int pageCount = books.size() / 10;
			if (books.size() % 10 != 0)
				pageCount += 1;
			for (int i = 1; i <= pageCount; i++) {
				strBuf.append("<li><a onclick='searchBooksPage(" + i + ")' id='pageNav'>" + i + "</a></li>");
			}

			strBuf.append("<li><a onclick='searchBooksPage(" + nextPageNo
					+ ")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");

			strBuf.append("</ul"); // end pagination

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	private String searchPublishers(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		AdminService service = new AdminService();
		StringBuffer strBuf = new StringBuffer();

		try {
			List<Publisher> pubs = service.getPublisherByName(pageNo, searchString);
			strBuf.append(
					"<thead><tr><th>#</th><th>Publisher Name</th><th>Address</th><th>Phone</th><th>Operation</th></tr></thead><tbody>");

			// prepare searched books
			for (Publisher pb : pubs) {
				strBuf.append("<tr>");

				strBuf.append("<td>");
				strBuf.append(pubs.indexOf(pb) + 1);
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(pb.getPubName());
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(pb.getPubAddr());
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(pb.getPubPhone());
				strBuf.append("</td>");

				strBuf.append(
						"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#modalConnector' href='modal/editpublisher.jsp?publisherId="
								+ pb.getPubId() + "' style='margin-right: 2%;'>Update</button>");

				strBuf.append(
						"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalConnector' href='modal/deletepublisher.jsp?publisherId="
								+ pb.getPubId() + "'>Delete</button></td>");

				strBuf.append("</tr>");
			}
			strBuf.append("</tbody>");

			// special separator
			strBuf.append("\n");

			// add new pagination for search result
			strBuf.append("<ul class='pagination' id='paginationList'>");

			Integer previousPageNo = Integer.valueOf(request.getParameter("pageNo")) - 1;
			Integer nextPageNo = Integer.valueOf(request.getParameter("pageNo")) + 1;

			strBuf.append("<li><a onclick='searchItemsPage(" + previousPageNo
					+ ")' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");

			pubs = service.getPublisherByName(searchString);
			int pageCount = pubs.size() / 10;
			if (pubs.size() % 10 != 0)
				pageCount += 1;
			for (int i = 1; i <= pageCount; i++) {
				strBuf.append("<li><a onclick='searchItemsPage(" + i + ")' id='pageNav'>" + i + "</a></li>");
			}

			strBuf.append("<li><a onclick='searchItemsPage(" + nextPageNo
					+ ")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");

			strBuf.append("</ul"); // end pagination

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	private String searchBranches(HttpServletRequest request) {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		AdminService service = new AdminService();
		StringBuffer strBuf = new StringBuffer();

		try {
			List<Branch> branches = service.getBranchesByName(pageNo, searchString);
			strBuf.append(
					"<thead><tr><th>#</th><th>Branch Name</th><th>Address</th><th>Operation</th></tr></thead><tbody>");

			// prepare searched books
			for (Branch br : branches) {
				strBuf.append("<tr>");

				strBuf.append("<td>");
				strBuf.append(branches.indexOf(br) + 1);
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(br.getBranchName());
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(br.getBranchAddress());
				strBuf.append("</td>");

				strBuf.append(
						"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#modalConnector' href='modal/editbranch.jsp?branchId="
								+ br.getBranchId() + "' style='margin-right: 2%;'>Update</button>");

				strBuf.append(
						"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalConnector' href='modal/deletebranch.jsp?branchId="
								+ br.getBranchId() + "'>Delete</button></td>");

				strBuf.append("</tr>");
			}
			strBuf.append("</tbody>");

			// special separator
			strBuf.append("\n");

			// add new pagination for search result
			strBuf.append("<ul class='pagination' id='paginationList'>");

			Integer previousPageNo = Integer.valueOf(request.getParameter("pageNo")) - 1;
			Integer nextPageNo = Integer.valueOf(request.getParameter("pageNo")) + 1;

			strBuf.append("<li><a onclick='searchItemsPage(" + previousPageNo
					+ ")' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");

			branches = service.getBranchesByName(searchString);
			int pageCount = branches.size() / 10;
			if (branches.size() % 10 != 0)
				pageCount += 1;
			for (int i = 1; i <= pageCount; i++) {
				strBuf.append("<li><a onclick='searchItemsPage(" + i + ")' id='pageNav'>" + i + "</a></li>");
			}

			strBuf.append("<li><a onclick='searchItemsPage(" + nextPageNo
					+ ")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");

			strBuf.append("</ul"); // end pagination

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	private String searchBorrowers(HttpServletRequest request) {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		AdminService service = new AdminService();
		StringBuffer strBuf = new StringBuffer();

		try {
			List<Borrower> borrowers = service.getBorrowersByName(pageNo, searchString);
			strBuf.append(
					"<thead><tr><th>#</th><th>Name</th><th>Address</th><th>Phone</th><th>Operation</th></tr></thead><tbody>");

			// prepare searched books
			for (Borrower bo : borrowers) {
				strBuf.append("<tr>");

				strBuf.append("<td>");
				strBuf.append(borrowers.indexOf(bo) + 1);
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(bo.getName());
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(bo.getAddress());
				strBuf.append("</td>");

				strBuf.append("<td>");
				strBuf.append(bo.getPhone());
				strBuf.append("</td>");

				strBuf.append(
						"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#modalConnector' href='modal/editborrower.jsp?borrowerId="
								+ bo.getCardNo() + "' style='margin-right: 2%;'>Update</button>");

				strBuf.append(
						"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#modalConnector' href='modal/deleteborrower.jsp?borrowerId="
								+ bo.getCardNo() + "'>Delete</button></td>");

				strBuf.append("</tr>");
			}
			strBuf.append("</tbody>");

			// special separator
			strBuf.append("\n");

			// add new pagination for search result
			strBuf.append("<ul class='pagination' id='paginationList'>");

			Integer previousPageNo = Integer.valueOf(request.getParameter("pageNo")) - 1;
			Integer nextPageNo = Integer.valueOf(request.getParameter("pageNo")) + 1;

			strBuf.append("<li><a onclick='searchItemsPage(" + previousPageNo
					+ ")' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");

			borrowers = service.getBorrowersByName(searchString);
			int pageCount = borrowers.size() / 10;
			if (borrowers.size() % 10 != 0)
				pageCount += 1;
			for (int i = 1; i <= pageCount; i++) {
				strBuf.append("<li><a onclick='searchItemsPage(" + i + ")' id='pageNav'>" + i + "</a></li>");
			}

			strBuf.append("<li><a onclick='searchItemsPage(" + nextPageNo
					+ ")' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");

			strBuf.append("</ul"); // end pagination

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}
}
