package com.example.library.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Optional;

import com.example.library.dao.UserDao;
import com.example.library.model.User;

public class AuthService {

    private final UserDao userDao;

    public AuthService() {
        this(new UserDao());
    }

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> authenticate(String username, String password) {
        if (username == null || username.isBlank() || password == null) {
            return Optional.empty();
        }
        // User.javaに定義されている、DBに保存されているハッシュ化されたパスワードの getter を使い、それと
        // loginブラウザで入力された「パスワード」 をSHA-256でハッシュ化したもの を比較する。
        return userDao.findByUsername(username)
                .filter(user -> user.getPasswordHash().equals(hashPassword(password)));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 入力されたpasswordをSHA-256でハッシュ化する。
            byte[] hashed = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashed);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is not available", exception);
        }
    }
}