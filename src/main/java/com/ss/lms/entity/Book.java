/**
 * 
 */
package com.ss.lms.entity;

import java.util.List;

/**
 * @author Ashian
 *
 */
public class Book 
{
	private int bookId;
	private String title;
	private int pubId;
	private List<Author> authors;
	private List<Genre> genres;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPubId() {
		return pubId;
	}
	public void setPubId(int pubId) {
		this.pubId = pubId;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}
