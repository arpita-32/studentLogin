# studentLogin

## How It Works

### 1. StudentController (Servlet)
- The `StudentController` servlet processes the **POST** request when a user submits the form from `addStudent.jsp`.
- It creates a `Student` object using the form data and calls the `addstudent()` method in `StudentDao` to insert the data into the database.
- If the insertion is successful, a success message is returned to the JSP. If there's an error, the error stack trace is displayed.

### 2. StudentDao (DAO)
- The `StudentDao` handles all interactions with the **MySQL** database. It has:
  - A method to establish a connection to the database using JDBC.
  - A method `addstudent()` to insert a new student into two tables: `login` and `student`.
  - The `addstudent()` method ensures that data is inserted into both tables in a single transaction.

### 3. Student (Model)
- The `Student` class represents the student entity with fields such as `studentId`, `name`, `email`, `mobile`, `age`, `userName`, and `password`.

### 4. JSP Page (View)
- The `addStudent.jsp` page displays a simple form where users can input the student details.
- After submission, the result (success or error) is displayed as a message.

## Setup Instructions

### Prerequisites

- **JDK 8+**
- **Apache Tomcat 9+**
- **MySQL** (Ensure MySQL server is running)
- **Eclipse IDE** (or any Java IDE)

### Database Setup

1. Create a MySQL database named `student_information`:

    ```sql
    CREATE DATABASE student_information;
    ```

2. Create the required tables:

    ```sql
    CREATE TABLE login (
        user_name VARCHAR(50) PRIMARY KEY,
        password VARCHAR(50),
        type VARCHAR(10)
    );

    CREATE TABLE student (
        student_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100),
        email VARCHAR(100),
        mobile VARCHAR(20),
        age INT,
        user_name VARCHAR(50),
        FOREIGN KEY (user_name) REFERENCES login(user_name)
    );
    ```

3. Modify the connection string in `StudentDao.java` to match your MySQL credentials:

    ```java
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_information", "your_username", "your_password");
    ```

### Project Deployment

1. **Import the Project**: 
   - Clone or download the project to your machine.
   - Open your IDE and import the project.

2. **Configure Tomcat**: 
   - Add **Apache Tomcat** to your IDE and configure it for this project.

3. **Run the Project**:
   - Start the server and navigate to `http://localhost:8080/your_project_name/addStudent.jsp`.

4. **Adding a Student**:
   - Fill in the form and submit. If successful, the student will be added to the database, and a success message will appear. If an error occurs, the error will be shown on the same page.

## Additional Notes

- **Transactions**: The system uses transactions to ensure that either both the `login` and `student` tables are updated, or none of them are, to avoid partial updates.
- **Error Handling**: If an error occurs during insertion, the transaction is rolled back, and the full stack trace is displayed to help debug the issue.
- **PreparedStatements**: The project uses `PreparedStatement` to prevent SQL injection attacks.

## Future Enhancements

- Add validation for the form inputs.
- Implement user authentication for secure access to the registration page.
- Add more features like updating and deleting student records.

## License

This project is open-source and available under the MIT License.
