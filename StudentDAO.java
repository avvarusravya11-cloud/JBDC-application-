package com.example.dao;

import com.example.model.Student;
import java.sql.*;

public class StudentDAO {

    private static final String URL =
        "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public boolean save(Student student) throws SQLException {

        String sql = "INSERT INTO student(id, name, email) VALUES (?, ?, ?)";

        try (Connection con =
                 DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());

            return ps.executeUpdate() > 0;
        }
    }
}
