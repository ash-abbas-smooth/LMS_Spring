package com.ss.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.dao.GenreDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;

public class AdminService 
{
	@Autowired
	AuthorDAO adao;
	
	@Autowired
	BookDAO bdao;
	
	@Autowired
	BookCopiesDAO bcdao;
	
	@Autowired
	BookLoansDAO bldao;
	
	@Autowired
	BorrowerDAO bordao;
	
	@Autowired
	BranchDAO brdao;
	
	@Autowired
	GenreDAO gdao;
	/*
	 * READ METHODS:
	 */
	public List<Author> readAuthors() throws SQLException 
	{
		List<Author> authors = new ArrayList<>();
		try {
			authors = adao.readAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		}
		return authors;
	}
	
	public List<Book> readBooks() throws SQLException 
	{
		List<Book> books = new ArrayList<>();
		try {

			books = bdao.readBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		}
		return books;
	}
	
	public List<BookCopies> readBookCopies() throws SQLException 
	{
		List<BookCopies> bcs = new ArrayList<>();
		try {
			bcs = bcdao.readBookCopies();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return bcs;
	}
	
	public List<BookLoans> readBookLoans() throws SQLException 
	{
		List<BookLoans> bls = new ArrayList<>();
		try {

			bls = bldao.readBookLoans();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return bls;
	}
	
	public List<Borrower> readBorrowers() throws SQLException
	{
		List<Borrower> borrowers = new ArrayList<>();
		try {
			borrowers = bordao.readBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		}
		return borrowers;
	}
	
	public List<Branch> readBranches() throws SQLException 
	{
		List<Branch> branches = new ArrayList<>();
		try {
			branches = brdao.readBranches();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} 
		return branches;
	}
	
	public List<Genre> readGenres() throws SQLException
	{
		
		List<Genre> genres = new ArrayList<>();
		try {
			genres = gdao.readGenres();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading genres faiiled");
		}
		return genres;
	}
	
	/*
	 * ADD METHODS:
	 */
	
	public String addAuthor(Author author) throws SQLException
	{
		try
		{
			Integer authorId = adao.saveAuthorWithId(author);
			if(author.getBooks()!=null)
			{
				for(Book b: author.getBooks())
					adao.insertBookAuthors(b.getBookId(), authorId);
			}
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Author Failed");
		}
		return "Author Added Successfully";
	}
	
	public String addBook(Book book) throws SQLException
	{
		try
		{
			Integer bookId = bdao.saveBookWithId(book);
			if(book.getAuthors()!=null)
			{
				for(Author a: book.getAuthors())
					bdao.insertBookAuthors(bookId, a.getAuthorId());
			}
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Book Failed");
		}
		return "Book Added Successfully";
	}
	
	public String addBookCopies(BookCopies bc) throws SQLException
	{
		try
		{
			Integer bookId = bcdao.saveBookCopiesWithId(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Book Copies Failed");
		}
		return "Book Copy Added Successfully";
	}
	
	public String addBookLoans(BookLoans bl) throws SQLException
	{
		try
		{
			Integer bookId = bldao.saveBookLoansWithId(bl);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Book Loans Failed");
		}
		return "Book Loan Added Successfully";
	}
	
	public String addBorrower(Borrower b) throws SQLException
	{
		try
		{
			Integer bookId = bordao.saveBorrowerWithId(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Borrower Failed");
		}
		return "Borrower Added Successfully";
	}
	
	public String addBranch(Branch b) throws SQLException
	{
		try
		{
			Integer bookId = brdao.saveBranchWithId(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Library Branch Failed");
		} 
		return "Library Branch Added Successfully";
	}
	
	public String addGenre(Genre g) throws SQLException
	{
		try
		{
			Integer bookId = gdao.saveGenreWithId(g);
			if(g.getBooks()!=null)
			{
				for(Book b: g.getBooks())
					gdao.insertBookGenre(g.getGenreId(),b.getBookId());
			}
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Adding Genre Failed");
		}
		return "Genre Added Successfully";
	}
	
	/*
	 * UPDATE METHODS:
	 */
	public String editAuthor(Author author) throws SQLException
	{
		try
		{
			adao.editAuthor(author);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Author Failed");
		}
		return "Edit Author Successfully";
	}
	public String editBook(Book book) throws SQLException
	{
		try
		{
			bdao.editBook(book);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Failed");
		} 
		return "Book Edit Success";
	}
	public String editBookCopies(BookCopies bc) throws SQLException
	{
		try
		{
			bcdao.editBookCopies(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Copies Failed");
		}
		return "Book Copies Edit Success";
	}
	public String editBookLoans(BookLoans bl) throws SQLException
	{
		try
		{
			bldao.editBookLoans(bl);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Book Loans Failed");
		}
		return "Book Loans Edit Success";
	}
	public String editBorrower(Borrower b) throws SQLException
	{
		try
		{
			bordao.editBorrower(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Borrower Failed");
		}
		return "Borrower Edit Success";
	}
	public String editBranch(Branch b) throws SQLException
	{
		try
		{
			brdao.editBranch(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Branch Failed");
		}
		return "Branch Edit Success";
	}
	public String editGenre(Genre g) throws SQLException
	{
		try
		{
			gdao.editGenre(g);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Editing Genre Failed");
		}
		return "Genre Edit Success";
	}
	
	/*
	 * DELETE METHODS:
	 */
	public String deleteAuthor(Author author) throws SQLException
	{
		try
		{
			adao.deleteAuthor(author);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Deleting Author Failed");
		}
		return "Deleted Author";
	}

	public String deleteBook(Book book) throws SQLException
	{
		try
		{
			bdao.deleteBook(book);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Book Failed");
		}
		return "Book Delete Success";
	}

	public String deleteBookCopies(BookCopies bc) throws SQLException
	{
		try
		{
			bcdao.deleteBookCopies(bc);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Book Copies Failed");
		}
		return "Book Copies Delete Success";
	}

	public String deleteBookLoans(BookLoans bl) throws SQLException
	{
		try
		{
			bldao.deleteBookLoans(bl);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Book Loans Failed");
		}
		return "Book Loans Delete Success";
	}

	public String deleteBorrower(Borrower b) throws SQLException
	{
		try
		{
			bordao.deleteBorrower(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Borrower Failed");
		}
		return "Borrower Delete Success";
	}

	public String deleteBranch(Branch b) throws SQLException
	{
		try
		{
			brdao.deleteBranch(b);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Branch Failed");
		}
		return "Branch Delete Success";
	}
	
	public String deleteGenre(Genre g) throws SQLException
	{
		try
		{
			gdao.deleteGenre(g);
		} catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			System.out.println("Delete Genre Failed");
		}
		return "Genre Delete Success";
	}
}
