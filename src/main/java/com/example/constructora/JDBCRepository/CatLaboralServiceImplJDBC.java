package com.example.constructora.JDBCRepository;

import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.domain.CategoriaLaboral;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CatLaboralServiceImplJDBC implements CatLaboralServiceJDBC {


    @Override
    public CategoriaLaboral create(CategoriaLaboral categoriaLaboral) {
        final String QUERY = "INSERT INTO categoria_laboral VALUES " +
                "(?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setString(1, categoriaLaboral.getNombreCategoria());
            stmt.setFloat(2, categoriaLaboral.getPrecioHora());

            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("CATEGORIA " + categoriaLaboral.getNombreCategoria() + " CREADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();
            return categoriaLaboral;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CategoriaLaboral> createAll(List<CategoriaLaboral> categoriaLaborales) {
        final String QUERY = "INSERT INTO categoria_laboral VALUES " +
                "(?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            int i = 0;

            while (i < categoriaLaborales.size()) {
                stmt.setString(1, categoriaLaborales.get(i).getNombreCategoria());
                stmt.setFloat(2, categoriaLaborales.get(i).getPrecioHora());

                int rowAffected = stmt.executeUpdate();
                System.out.println(String.format("Row affected %d", rowAffected));
                i++;
            }

            stmt.close();
            return categoriaLaborales;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CategoriaLaboral> getCatLaborales() {
        List<CategoriaLaboral> receivedCatLaborales = new ArrayList<>();

        final String QUERY = "SELECT * FROM categoria_laboral";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {


            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedCatLaborales.add(
                        new CategoriaLaboral(
//                                rs.getLong("trabajador_id"),
                                rs.getString("nombre_categoria"),
                                rs.getFloat("precio_hora")
                        ));
                i++;

            }
            return receivedCatLaborales;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getCatLaboralNames() {
        List<String> receivedCategoriesNames = new ArrayList<>();

        final String QUERY = "SELECT categoria_laboral.nombre_categoria FROM categoria_laboral";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedCategoriesNames.add(
                        rs.getString("nombre_categoria")
                );
                i++;
            }
            return receivedCategoriesNames;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CategoriaLaboral getCatLaboral(String nombreCategoria) {
        final String QUERY = "SELECT * FROM categoria_laboral WHERE categoria_laboral.nombre_categoria= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, nombreCategoria);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new CategoriaLaboral(
//                                rs.getLong("trabajador_id"),
                    rs.getString("nombre_categoria"),
                    rs.getFloat("precio_hora")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public float getPrecioHora(String nombreCategoria) {
        final String QUERY = "SELECT categoria_laboral.precio_hora " +
                "FROM categoria_laboral WHERE categoria_laboral.nombre_categoria= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, nombreCategoria);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return rs.getFloat("precio_hora");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public CategoriaLaboral update(CategoriaLaboral categoriaLaboral) {
        return null;
    }

    @Override
    public CategoriaLaboral delete(String nombreCategoria) {
        return null;
    }
}
