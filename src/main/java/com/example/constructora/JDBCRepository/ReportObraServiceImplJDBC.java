package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.ReportByObra;

import java.sql.*;
import java.util.List;

public class ReportObraServiceImplJDBC implements ReportObraServiceJDBC {

    final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
    final String USER = "postgres";
    final String PASS = "postgres";


    @Override
    public void createViewTableObras(List<String> selectedObras) {
        StringBuilder selectedObrasToQuery = new StringBuilder();

        selectedObrasToQuery.append("pago.obra_descriptor = "+"'").append(selectedObras.get(0)).append("'");
        for (int i = 1; i < selectedObras.size(); i++)
            selectedObrasToQuery.append(" OR ").append("pago.obra_descriptor = "+"'").append(selectedObras.get(i)).append("'");

        System.out.println(selectedObrasToQuery);
        final String QUERY =
                "CREATE VIEW reportByObra AS " +
                        "SELECT " +
                        "pago.obra_descriptor, " +
                        "pago.trabajador_dni, " +
                        "trabajador.nombre, " +
                        "SUM(pago.horas) AS horas, " +
                        "SUM(pago.cantidad) AS cantidad " +
                        "FROM pago " +
                        "INNER JOIN trabajador ON " +
                        "pago.trabajador_dni = trabajador.trabajador_dni " +
                        "WHERE " + selectedObrasToQuery +
                        " GROUP BY pago.obra_descriptor, pago.trabajador_dni, trabajador.nombre";

        System.out.println(QUERY);

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement  stmt = conn.createStatement()) {
            System.out.println("desde query " + selectedObrasToQuery);

            // stmt.setString(1, selectedObrasToQuery);
            int rs = stmt.executeUpdate(QUERY);
            System.out.println(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReportByObra create(ReportByObra reportByObra) {
        final String QUERY = "INSERT INTO reportByObra VALUES " +
                "(?, ?, ?, ?, ?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));

            stmt.close();
            return reportByObra;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReportByObra> getSelectedObras() {
        return null;
    }

    @Override
    public ReportByObra getSelectedObra() {
        return null;
    }

    @Override
    public ReportByObra update(ReportByObra reportByObra) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void dropViewTableObras() {
        final String QUERY = "DROP VIEW reportByObra";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            int rowAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
