package com.example.constructora;

import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.view.MenuLateral;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.beans.Statement;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
///
@SpringBootApplication
public class ConstructoraApplication {

    public static void main(String[] args) throws IOException, SQLException {

        LocalDate currentdate = LocalDate.now();

        if (currentdate.getMonthValue() >= 6 && currentdate.getYear() >= 2022) {
            JOptionPane.showMessageDialog(null, "FECHA LÍMITE USO GRATUITO.\n Contacte con el desarrollador. \n ruizzmaury@gmail.com"
                    , "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            // TESTING REST API
            // SpringApplication.run(ConstructoraApplication.class, args);

            if (createDatabase()) SpringApplication.run(ConstructoraApplication.class, args);

            System.setProperty("java.awt.headless", "false");
            MenuLateral menuLateral = new MenuLateral();
        }

    }

    private static boolean createDatabase() {
        final String QUERY = "CREATE DATABASE constructorabd";
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL_HOST, "postgres", "postgres");
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            int rowAffected = stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return false;
    }

}