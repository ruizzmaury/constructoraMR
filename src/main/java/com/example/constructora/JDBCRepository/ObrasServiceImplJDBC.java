package com.example.constructora.JDBCRepository;

import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.domain.Obra;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObrasServiceImplJDBC implements ObrasServiceJDBC {

    @Override
    public Obra create(Obra obra) {
        final String QUERY = "INSERT INTO obra VALUES " +
                "(?, ?, ?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

//            stmt.setLong(1, obra.getId());
            stmt.setString(1, obra.getDescriptor());
            stmt.setDate(2, Date.valueOf(obra.getFechaFin()));
            stmt.setDate(3, Date.valueOf(obra.getFechaInicio()));
            stmt.setString(4, obra.getUbicacion());

            int rowAffected = stmt.executeUpdate();

            stmt.close();
            System.out.println("obra creada con exito");
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
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        ));

            }
            return receivedObras;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getObrasDescriptor() {
        List<String> receivedObrasDescriptor = new ArrayList<>();

        final String QUERY = "SELECT obra.descriptor FROM obra";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObrasDescriptor.add(
                        rs.getString("descriptor")
                );

            }
            return receivedObrasDescriptor;

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
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
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
    public Obra getObra(String descriptor) {
        final String QUERY = "SELECT * FROM obra WHERE obra.descriptor = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, descriptor);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Obra(
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
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setString(1, "%" + ubicacion + "%");
            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );
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
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setString(1, "%" + descriptor + "%");
            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {

                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );

            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> findByDateForward(LocalDate fechaInicioFilter) {
        List<Obra> receivedObras = new ArrayList<>();

        final String QUERY = "SELECT * FROM obra WHERE obra.fecha_inicio >= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaInicioFilter));

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );
            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> findByDateBackward(LocalDate fechaFinFilter) {
        List<Obra> receivedObras = new ArrayList<>();

        final String QUERY = "SELECT * FROM obra WHERE obra.fecha_inicio <= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaFinFilter));

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );

            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obra> findByNameAndDate(String obraDescriptor, LocalDate fechaInicioFilter, LocalDate fechaFinFilter) {
        List<Obra> receivedObras = new ArrayList<>();
        System.out.println(obraDescriptor);
        String QUERY =
                "SELECT obra.* FROM obra WHERE obra.descriptor LIKE ?";
        if (fechaInicioFilter != null && fechaFinFilter != null) {
            QUERY = QUERY + " AND obra.fecha_inicio >= ? AND obra.fecha_inicio <= ?";
        } else if (fechaInicioFilter != null){
            QUERY = QUERY + " AND obra.fecha_inicio >= ? ";
        } else if (fechaFinFilter != null) {
            QUERY = QUERY + " AND obra.fecha_inicio <= ?";
        }

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {
            stmt.setString(1, "%" + obraDescriptor + "%");


            if (fechaInicioFilter != null || fechaFinFilter != null) {
                if (fechaInicioFilter != null && fechaFinFilter != null) {
                    stmt.setDate(2, Date.valueOf(fechaInicioFilter));
                    stmt.setDate(3, Date.valueOf(fechaFinFilter));

                } else stmt.setDate(2, Date.valueOf(Objects.requireNonNullElse(fechaInicioFilter, fechaFinFilter)));
            }

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );

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

        final String QUERY = "SELECT * FROM obra WHERE obra.fecha_inicio >= ? AND" +
                " obra.fecha_fin <= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas
// Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaInicioFilter));
            stmt.setDate(2, Date.valueOf(fechaFinFilter));

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {

                // Retrieve by column name
                receivedObras.add(
                        new Obra(
                                rs.getString("ubicacion"),
                                rs.getString("descriptor"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                        )
                );
            }
            return receivedObras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Obra update(Obra obra) {
        System.out.println(obra);
        final String QUERY = "UPDATE obra SET " +
                "ubicacion = ?, " +
                "descriptor = ?, " +
                "fecha_inicio = ?, " +
                "fecha_fin = ?, " +
                "WHERE descriptor = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setString(1, obra.getUbicacion());
            stmt.setString(2, obra.getDescriptor());
            stmt.setDate(3, Date.valueOf(obra.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(obra.getFechaFin()));
            stmt.setString(5, obra.getDescriptor());

            stmt.close();

            return obra;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(String descriptor) {
        final String QUERY = "DELETE FROM obra WHERE obra.descriptor = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, descriptor);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
