package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.RepWorkerDate;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class RepWorkerServiceImplJDBC implements RepWorkerServiceJDBC {

    final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
    final String USER = "postgres";
    final String PASS = "postgres";


    @Override
    public void createViewTableWorkerDate(List<String> selectedWorkers, LocalDate init, LocalDate end) {
        StringBuilder selectedWorkersToQuery = new StringBuilder();

        selectedWorkersToQuery.append("trabajador.nombre = "+"'").append(selectedWorkers.get(0)).append("'");
        for (int i = 1; i < selectedWorkers.size(); i++)
            selectedWorkersToQuery.append(" OR ").append("trabajador.nombre = "+"'").append(selectedWorkers.get(i)).append("'");

        System.out.println(init.toString() + " AND " + end.toString());
        final String QUERY =
                "CREATE VIEW reportByWorker AS " +
                "SELECT " +
                        "pago.trabajador_dni," +
                        "trabajador.nombre," +
                        "pago.obra_descriptor," +
                        "pago.fecha_pago," +
                        "pago.horas," +
                        "pago.cantidad " +
                        "FROM pago " +
                        "INNER JOIN trabajador ON " +
                        "pago.trabajador_dni = trabajador.trabajador_dni " +
                        "WHERE pago.fecha_pago BETWEEN '" + init.toString() + "' AND '" + end.toString() +
                        "' AND " + selectedWorkersToQuery +
                        " ORDER BY pago.trabajador_dni, pago.obra_descriptor";

        System.out.println(QUERY);

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            System.out.println("desde query " + selectedWorkersToQuery);

            // stmt.setString(1, selectedWorkersToQuery);
            int rs = stmt.executeUpdate(QUERY);
            System.out.println(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RepWorkerDate create(RepWorkerDate repWorkerDate) {
        return null;
    }

    @Override
    public List<RepWorkerDate> getReports() {
        return null;
    }

    @Override
    public RepWorkerDate getReport() {
        return null;
    }

    @Override
    public RepWorkerDate update(RepWorkerDate repWorkerDate) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void dropViewTableWorkerDate() {
        final String QUERY = "DROP VIEW reportByWorker";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            int rowAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
