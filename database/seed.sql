-- このSQLファイルを実行する前に, 対象のDB(library_booking)に
-- 管理者(postgres)でログインしたうえで
-- (つまり、 psql -U postgres -d library_booking)
-- 以下のコマンドを一度だけ実行
-- CREATE EXTENSION IF NOT EXISTS pgcrypto;
-- その後、library_app ユーザで対象のDBにログインしなおしてから、このSQLファイルを実行する

SET client_encoding = 'UTF8';

TRUNCATE TABLE reservations, users, books RESTART IDENTITY CASCADE;

INSERT INTO users (
    username,
    password_hash,
    display_name,
    role
)
VALUES
    ('user01', 
    encode(digest('dummy_hash_01', 'sha256'), 'hex'), 
    '山田太郎', 
    'USER'),
    ('user02', 
    encode(digest('dummy_hash_02', 'sha256'), 'hex'), 
    '佐藤花子', 
    'USER'),
    ('admin', 
    encode(digest('dummy_hash_admin', 'sha256'), 'hex'), 
    '管理者', 
    'ADMIN');

INSERT INTO books (
    isbn,
    title,
    author,
    publisher,
    publication_year,
    available
)
VALUES
    (
        '9784873115658',
        'Java入門',
        '山田太郎',
        '技術出版',
        2022,
        TRUE
    ),
    (
        '9784873117386',
        'PostgreSQL入門',
        '佐藤花子',
        'DB出版',
        2021,
        TRUE
    ),
    (
        '9784873119038',
        'Webアプリケーション開発',
        '鈴木一郎',
        'Web技術社',
        2023,
        TRUE
    );

INSERT INTO reservations (
    user_id,
    book_id,
    status
)
VALUES
    (1, 1, 'RESERVED'),
    (2, 2, 'RESERVED');