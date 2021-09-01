package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroServiceImplJDBC implements GeneroServiceJDBC {

    final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
    final String USER = "constructora";
    final String PASS = "constructora";

    @Override
    public Genero create(Genero genero) {
        final String QUERY = "INSERT INTO genero VALUES (?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setString(1, genero.getNombreGenero());


            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("Genero " + genero.getNombreGenero() + " CREADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();
            return genero;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Genero> createAll(List<Genero> generos) {
        final String QUERY = "INSERT INTO genero VALUES (?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            int i = 0;

            while (i < generos.size()) {
                stmt.setString(1, generos.get(i).getNombreGenero());

                int rowAffected = stmt.executeUpdate();
                System.out.println(String.format("Row affected %d", rowAffected));
                i++;
            }

            stmt.close();
            return generos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Genero> getGeneros() {
        List<Genero> receivedGeneros = new ArrayList<>();

        final String QUERY = "SELECT * FROM genero";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedGeneros.add(
                        new Genero(
                                rs.getString("genero")

                        ));
                i++;

            }
            return receivedGeneros;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Genero getGenero(String nombreGenero) {
        final String QUERY = "SELECT * FROM genero WHERE genero.nombre_genero= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, nombreGenero);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Genero (
                    rs.getString("nombre_genero")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Genero update(Genero genero) {
        return null;
    }

    @Override
    public Genero delete(String nombreGenero) {
        return null;
    }
}
