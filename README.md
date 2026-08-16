# library-booking-system-java


# Directory Structure

```
library-booking-system-java/
│
├─ pom.xml
├─ README.md
├─ .gitignore
│
├─ database/
│  ├─ schema.sql
│  └─ seed.sql
│
└─ src/
   ├─ main/
   │  ├─ java/
   │  │  └─ com/
   │  │     └─ example/
   │  │        └─ library/
   │  │           ├─ controller/
   │  │           ├─ service/
   │  │           ├─ dao/
   │  │           ├─ model/
   │  │           ├─ filter/
   │  │           └─ util/
   │  │
   │  ├─ resources/
   │  │
   │  └─ webapp/
   │     ├─ index.jsp
   │     ├─ css/
   │     │  └─ style.css
   │     │
   │     └─ WEB-INF/
   │        ├─ web.xml
   │        └─ views/
   │           ├─ book-list.jsp
   │           ├─ book-detail.jsp
   │           ├─ login.jsp
   │           └─ reservation-list.jsp
   │
   └─ test/
      └─ java/
```

## Database ER Structure

`library-booking` database.

```
users
 ├─ id PK
 ├─ username
 ├─ password_hash
 ├─ display_name
 └─ role
        │
        │ 1
        │
        │ N
reservations
 ├─ id PK
 ├─ user_id FK
 ├─ book_id FK
 ├─ reserved_at
 └─ status
        │
        │ N
        │
        │ 1
books
 ├─ id PK
 ├─ isbn
 ├─ title
 ├─ author
 ├─ publisher
 ├─ publication_year
 └─ available
```