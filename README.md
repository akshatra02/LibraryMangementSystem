# Zoho-LibraryMangementSystem
# Zoho Library Management System

## Overview
The Library Management System (LMS) is a software application designed to automate library operations, including book management and user interactions. It serves both administrators and students, providing features such as browsing books, borrowing books, and managing reservations. The system is built using Kotlin programming language, Jetpack Compose for UI development, and SQLite for data storage.

## Technologies Used
- **Kotlin**: Main programming language for backend logic and business logic implementation.
- **Jetpack Compose**: Modern UI toolkit for building native Android apps with declarative UI.
- **SQLite**: Lightweight relational database management system for local data storage.

## Components
### User Interface (UI)
- Designed using Jetpack Compose, providing a modern and intuitive user experience.
- Consists of screens for admin login, admin dashboard, student login, student dashboard, book management, and borrowing.

### Backend Logic
- Implemented in Kotlin, handling business logic, data validation, and database operations.
- Manages book records, user records, borrowing history, and updates availability status.

### Data Storage
- Utilizes SQLite database for storing book records, user records, borrowing history, etc.
- Tables include books, users, borrowed_books, fines, etc.

## Welcome Page
- The welcome screen serves as the initial interface for accessing the Library Management System (LMS) application. It offers convenient access for both administrators and students through separate login portals.
  ![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/HomeScreen.png)
  ![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/AdminLogin.png)


## Admin Page
1. **View All Books**:
    - Allows administrators to view a list of all available books in the library.
    - Displays book details such as title, author, category, and availability status.

2. **Issue Book to Student**:
   - Allows administrators to issue books to students for borrowing.
   - Requires input of student ID and book ID, and updates book availability status accordingly.

3. **Add Book**:
    - Enables administrators to add new books to the library inventory.
    - Requires input of book details such as title, author, and category.

3. **Remove Books**:
    - Provides functionality to remove existing books from the library inventory.
    - Administrators can select books to be removed based on book ID.
![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/AdminScreen.png)
      ![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/AllBooks.png)
      ![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/AddBook.png)

## Student Page
1. **View Books**:
    - Allows students to browse the list of available books in the library.
    - Displays book details and availability status.

2. **Student Reserved Books**:
    - Provides students with a view of books they have reserved for borrowing.
    - Displays reserved book details and status.

3. **Return Book**:
    - Allows students to return borrowed books to the library.
    - Requires input of book ID and updates book availability status accordingly.

      ![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/StudentPage.png)
      ![Library Management System](/home/akshatra-pt7566/IdeaProjects/LIB_COMPOSE_SQLITE/app/images/StudentReservedBooks.png)
## Conclusion
The Library Management System (LMS) simplifies library operations, enhances user experience, and improves efficiency for administrators and students alike. Leveraging Kotlin, Jetpack Compose, and SQLite, the system offers a modern and robust solution for managing library resources effectively.

