package com.example.constructora.JDBCRepository;

import com.example.constructora.domain.Obra;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ObrasServiceImplJDBC implements ObrasServiceJDBC {

    final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
    final String USER = "constructora";
    final String PASS = "constructora";


    @Override
    public Obra create(Obra obra) {
        final String QUERY = "INSERT INTO obra VALUES " +
                "(?, ?, ?, ?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setLong(1, obra.getId());
            stmt.setString(2, obra.getDescriptor());
            stmt.setDate(3, Date.valueOf(obra.getFechaFin()));
            stmt.setDate(4, Date.valueOf(obra.getFechaInicio()));
            stmt.setString(5, obra.getUbicacion());

            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("OBRA CON ID " + obra.getId() + " CREADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();
            return obra;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> getObras() {
        List<Obra> receivedObras = new ArrayList<>();

        final String QUERY = "SELECT * FROM obra";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {


            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getLong("obra_id"),
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        ));

                System.out.println("OBRA: ");
                System.out.println("ID: " + receivedObras.get(i).getId());
                System.out.println("Ubicacion: " + receivedObras.get(i).getUbicacion());
                System.out.println("Descripcion: " + receivedObras.get(i).getDescriptor());
                System.out.println("FechaInicio: " + receivedObras.get(i).getFechaInicio());
                System.out.println("FechaFinal: " + receivedObras.get(i).getFechaFin());
                System.out.println("--------------------------------------------------");
                i++;

            }
            return receivedObras;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Long> getObrasIDs() {
        List<Long> receivedObrasIDs = new ArrayList<>();

        final String QUERY = "SELECT obra.obra_id FROM obra";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObrasIDs.add(
                        rs.getLong("obra_id")
                );
                i++;
            }
            return receivedObrasIDs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getObrasDescriptors() {
        List<String> receivedObrasDescriptors = new ArrayList<>();

        final String QUERY = "SELECT obra.descriptor FROM obra";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObrasDescriptors.add(
                        rs.getString("descriptor")
                );
                i++;
            }
            return receivedObrasDescriptors;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Obra getObra(long id) {
        final String QUERY = "SELECT * FROM obra WHERE obra.obra_id= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            System.out.println("OBRA: ");
            System.out.println("ID: " + rs.getLong("obra_id"));
            System.out.println("Ubicacion: " + rs.getString("ubicacion"));
            System.out.println("Descripcion: " + rs.getString("descriptor"));
            System.out.println("FechaInicio: " + rs.getDate("fecha_inicio").toLocalDate());
            System.out.println("FechaFinal: " + rs.getDate("fecha_final").toLocalDate());
            System.out.println("--------------------------------------------------");

            System.out.println("GUARDADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            return new Obra(
                    rs.getLong("obra_id"),
                    rs.getString("ubicacion"),
                    rs.getString("descriptor"),
                    rs.getDate("fecha_inicio").toLocalDate(),
                    rs.getDate("fecha_final").toLocalDate()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> findByUbicacionStartingWith(String ubicacion) {
        List<Obra> receivedObras = new ArrayList<>();

        final String QUERY = "SELECT * FROM obra WHERE obra.ubicacion LIKE ?";
        System.out.println("OBRAS CON UBICACIÓN EMPEZADA POR " + ubicacion);
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setString(1, "%" + ubicacion + "%");
            ResultSet rs = stmt.executeQuery();

            int i = 0;
            // Extract data from result set
            while (rs.next()) {

                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getLong("obra_id"),
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );

                System.out.println("OBRA: ");
                System.out.println("ID: " + receivedObras.get(i).getId());
                System.out.println("Ubicacion: " + receivedObras.get(i).getUbicacion());
                System.out.println("descriptor: " + receivedObras.get(i).getDescriptor());
                System.out.println("FechaInicio: " + receivedObras.get(i).getFechaInicio());
                System.out.println("FechaFin: " + receivedObras.get(i).getFechaFin());
                System.out.println("--------------------------------------------------");
                i++;

            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> findByDescriptorStartingWith(String descriptor) {
        List<Obra> receivedObras = new ArrayList<>();

        final String QUERY = "SELECT * FROM obra WHERE obra.descriptor LIKE ?";
        System.out.println("OBRAS CON DESCRIPTOR EMPEZADO POR " + descriptor);
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setString(1, "%" + descriptor + "%");
            ResultSet rs = stmt.executeQuery();

            int i = 0;
            // Extract data from result set
            while (rs.next()) {

                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getLong("obra_id"),
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );

                System.out.println("OBRA: ");
                System.out.println("ID: " + receivedObras.get(i).getId());
                System.out.println("Ubicacion: " + receivedObras.get(i).getUbicacion());
                System.out.println("descriptor: " + receivedObras.get(i).getDescriptor());
                System.out.println("FechaInicio: " + receivedObras.get(i).getFechaInicio());
                System.out.println("FechaFin: " + receivedObras.get(i).getFechaFin());
                System.out.println("--------------------------------------------------");
                i++;

            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> findBetweenDates(LocalDate fechaInicioFilter, LocalDate fechaFinFilter) {
        List<Obra> receivedObras = new ArrayList<>();

        final String QUERY = "SELECT * FROM obra WHERE obra.fecha_inicio OR obra.fecha_fin >= ? AND" +
                " obra.fecha_inicio OR obra.fecha_fin <= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas
// Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaInicioFilter));
            stmt.setDate(2, Date.valueOf(fechaFinFilter));

            ResultSet rs = stmt.executeQuery();

            int i = 0;
            // Extract data from result set
            while (rs.next()) {

                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getLong("obra_id"),
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );

                System.out.println("OBRA: ");
                System.out.println("ID: " + receivedObras.get(i).getId());
                System.out.println("Ubicacion: " + receivedObras.get(i).getUbicacion());
                System.out.println("descriptor: " + receivedObras.get(i).getDescriptor());
                System.out.println("FechaInicio: " + receivedObras.get(i).getFechaInicio());
                System.out.println("FechaFin: " + receivedObras.get(i).getFechaFin());
                System.out.println("--------------------------------------------------");
                i++;

            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Obra update(Obra obra) {
        final String QUERY = "UPDATE obra SET " +
                "ubicacion = ?, " +
                "descriptor = ?, " +
                "fecha_inicio = ?, " +
                "fecha_fin = ?, " +
                "WHERE obra_id = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setString(1, obra.getUbicacion());
            stmt.setString(2, obra.getDescriptor());
            stmt.setDate(3, Date.valueOf(obra.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(obra.getFechaFin()));
            stmt.setLong(5, obra.getId());

            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("OBRA CON ID " + obra.getId() + " ACTUALIZADA CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();

            return obra;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(long obra_id) {
        final String QUERY = "DELETE FROM obra WHERE obra.obra_id = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setLong(1, obra_id);

            stmt.executeUpdate();

            System.out.println("OBRA CON ID " + obra_id + " ELIMINADA CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
