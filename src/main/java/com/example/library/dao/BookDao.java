package com.example.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.library.model.Book;
import com.example.library.util.DatabaseConnection;

public class BookDao {

	public List<Book> findAll() {
		String sql = """
				SELECT id, isbn, title, author, publisher, publication_year, available
				FROM books
				ORDER BY id
				""";

		List<Book> books = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setIsbn(resultSet.getString("isbn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPublisher(resultSet.getString("publisher"));
				book.setPublicationYear(resultSet.getInt("publication_year"));
				book.setAvailable(resultSet.getBoolean("available"));

				books.add(book);
			}

			return books;
		} catch (SQLException exception) {
			throw new RuntimeException("Failed to load books", exception);
		}
	}
}
