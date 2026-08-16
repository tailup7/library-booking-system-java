package com.example.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.example.library.model.User;
import com.example.library.util.DatabaseConnection;


// PostgreSQLのusersテーブルからユーザー情報を取得し、その結果をJavaのUserオブジェクトとして返すためのクラス。DAOはData Access Objectの略で、データベースへのアクセスを担当するクラスのこと。
public class UserDao {
    //usernameを指定して、該当するユーザをusersテーブルから探すメソッド。
    public Optional<User> findByUsername(String username) {
        String sql = """
                SELECT id, username, password_hash, display_name, role
                FROM users
                WHERE username = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // SQLの?にusernameをセットする (1は1番目の?を意味する)
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setDisplayName(resultSet.getString("display_name"));
                user.setRole(resultSet.getString("role"));

                return Optional.of(user);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to load user by username: " + username, exception);
        }
    }

    //id を指定して、該当するユーザをusersテーブルから探すメソッド。
    public Optional<User> findById(long id) {
        String sql = """
                SELECT id, username, password_hash, display_name, role
                FROM users
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setDisplayName(resultSet.getString("display_name"));
                user.setRole(resultSet.getString("role"));

                return Optional.of(user);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to load user by id: " + id, exception);
        }
    }
}