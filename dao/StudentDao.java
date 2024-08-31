package sis.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import sis.model.Student;

public class StudentDao {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_information", "", "");
    }

    public boolean addstudent(Student stud) {
        Connection con = null;
        PreparedStatement st = null;
        boolean flag = false;

        try {
            con = StudentDao.getConnection();
            con.setAutoCommit(false);

            st = con.prepareStatement("INSERT INTO login (user_name,password,type) VALUES (?, ?, 'st');");
            st.setString(1, stud.getUserName());
            st.setString(2, stud.getPassword());  // Fix index to 2 for password

            int value = st.executeUpdate();

            if (value == 1) {
                st = con.prepareStatement("INSERT INTO student (student_id, name, email, mobile, age, user_name) VALUES ('3', ?, ?, ?, ?, ?);");
       
                st.setString(1, stud.getName());
                st.setString(2, stud.getEmail());
                st.setString(3, stud.getMobile());
                st.setInt(4, stud.getAge());
                st.setString(5, stud.getUserName());

                if (st.executeUpdate() == 1) {
                    con.commit();
                    flag = true;
                } else {
                    con.rollback();
                }
            }

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();  // Print the exception for debugging
        } finally {
            try {
                if (st != null) {
                    st.close();  // Close PreparedStatement if not null
                }
                if (con != null) {
                    con.close();  // Close Connection if not null
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return flag;
    }

	
}
