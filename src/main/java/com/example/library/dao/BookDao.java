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

    private static final int PAGE_SIZE = 10;

    public List<Book> findAll() {
        return findByKeyword(null, 0, PAGE_SIZE);
    }

    public List<Book> findByKeyword(String keyword) {
        return findByKeyword(keyword, 0, PAGE_SIZE);
    }

    public List<Book> findByKeyword(String keyword, int offset, int limit) {
        String sql = """
                SELECT id, isbn, title, author, publisher, publication_year, available
                FROM books
                WHERE (? IS NULL OR ? = '' OR isbn LIKE ? OR title LIKE ? OR author LIKE ?)
                ORDER BY
                    CASE
                        WHEN ? IS NULL OR ? = '' THEN isbn
                        ELSE title
                    END ASC,
                    id ASC
                LIMIT ? OFFSET ?
                """;

        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = keyword == null ? "" : keyword.trim();
            String likeKeyword = "%" + searchKeyword + "%";

            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, likeKeyword);
            statement.setString(4, likeKeyword);
            statement.setString(5, likeKeyword);
            statement.setString(6, searchKeyword);
            statement.setString(7, searchKeyword);
            statement.setInt(8, limit);
            statement.setInt(9, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
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
            }

            return books;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to load books", exception);
        }
    }

    public int countByKeyword(String keyword) {
        String sql = """
                SELECT COUNT(*)
                FROM books
                WHERE (? IS NULL OR ? = '' OR isbn LIKE ? OR title LIKE ? OR author LIKE ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = keyword == null ? "" : keyword.trim();
            String likeKeyword = "%" + searchKeyword + "%";

            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, likeKeyword);
            statement.setString(4, likeKeyword);
            statement.setString(5, likeKeyword);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            return 0;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to count books", exception);
        }
    }
}

