# library-booking-system-java


# Directory Structure

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