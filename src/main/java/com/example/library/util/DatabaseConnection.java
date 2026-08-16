package com.example.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    //PostgreSQLへの接続先。ホスト名 : localhost ポート番号 : 5432 のPostgreSQLに接続し、DB名 library_booking というDBに接続する。
    private static final String URL =
            "jdbc:postgresql://localhost:5432/library_booking";

    // PostgreSQLに接続するためのユーザー名とパスワード。PostgreSQL に library_appというユーザがすでにいることを前提としている。
    private static final String USER =
            "library_app";

    private static final String PASSWORD =
            "library_app";   // ここは本当はgithubにcommitしない方がいい。
    
    private DatabaseConnection() {
    }

    public static Connection getConnection()
            throws SQLException {
        //JDBCドライバを使ってPostgreSQLに接続する。接続先のURL、ユーザー名、パスワードを指定する。
        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        );
    }
}
