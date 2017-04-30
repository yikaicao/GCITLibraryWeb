package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/editAuthor", "/deleteAuthor", "/pageAuthors", "/searchAuthors", "/addBook" })
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
		switch (reqUrl) {
		case "/pageAuthors":
			pageAuthors(request);
			forwardPath = "/authors.jsp";
			break;
		case "/searchAuthors":
			String data = searchAuthors(request);
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
		}

		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	private void addAuthor(HttpServletRequest request) {
		Author author = new Author();
		author.setAuthorName(request.getParameter("authorName"));
		AdminService service = new AdminService();
		try {
			service.addAuthor(author);
		} catch (SQLException e) {
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
			request.setAttribute("message", "Deltion Failed");
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

		AdminService service = new AdminService();
		try {
			// service.addBook(book);
			service.addBookAuthor(book, authorList);
		} catch (SQLException e) {
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
						"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#editAuthorModal' href='editauthor.jsp?authorId="
								+ a.getAuthorId() + "' style='margin-right: 2%;'>Update</button>");

				strBuf.append(
						"<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#editAuthorModal' href='deleteauthor.jsp?authorId="
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
					+ ")' aria-label='Previous'> <span aria-hidden='true'>&raquo;</span></a></li>");

			strBuf.append("</ul");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

}
