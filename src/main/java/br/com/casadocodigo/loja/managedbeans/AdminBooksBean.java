package br.com.casadocodigo.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	
	private List<Author> authors = new ArrayList<Author>();
	
	@Inject
	private BookDAO bookDAO;
	
	@Transactional
	public void save() {
		populateBookAuthor();
		bookDAO.save(product);
	}
	
	private void populateBookAuthor() {
		selectedAuthorsIds.stream().map( (id) -> {
		return new Author(id);
		}).forEach(product :: add);
		}
	
	public Book getProduct() {
		return product;
	}
	
	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}
	
	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
