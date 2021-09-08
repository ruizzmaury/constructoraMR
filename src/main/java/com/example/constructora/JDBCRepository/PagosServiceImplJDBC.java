package com.example.constructora.JDBCRepository;

import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PagosServiceImplJDBC implements PagosServiceJDBC {

    final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
    final String USER = "constructora";
    final String PASS = "constructora";

    @Autowired
    private TrabajadorServiceJDBC trabajadorServiceJDBC = new TrabajadorServiceImplJDBC();

    @Override
    public Pago create(Pago pago, @NotNull @Valid final String trabajador_dni) {
        final String QUERY = "INSERT INTO pago VALUES " +
                "(?, ?, ?, ?, ?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setLong(1, pago.getId());
            stmt.setFloat(2, pago.getCantidad());
            stmt.setDate(3, Date.valueOf(pago.getFechaPago()));
            stmt.setInt(4, pago.getHoras());
            stmt.setString(5, pago.getObraDescriptor());
            stmt.setString(6, trabajador_dni);
            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));

            stmt.close();
            return pago;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pago> getPagos() {
        List<Pago> receivedPagos = new ArrayList<>();

        final String QUERY = "SELECT * FROM pago";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {


            // Extract data from result set
            while (rs.next()) {
                Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));
                // Retrieve by column name
                receivedPagos.add(
                        new Pago(
                                rs.getInt("horas"),
                                referencedTrabajador,
                                rs.getString("obra_descriptor"),
                                rs.getDate("fecha_pago").toLocalDate(),
                                rs.getFloat("cantidad")
                        ));

            }
            return receivedPagos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pago getPago(long id) {
        final String QUERY = "SELECT * FROM pago WHERE pago.pago_id= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            System.out.println("PAGO: ");
            System.out.println("ID: " + rs.getLong("pago_id"));
            System.out.println("--------------------------------------------------");

            System.out.println("GET PAGO SUCCESSFUL... ");
            System.out.println("--------------------------------------------------");
            Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));

            return new Pago(
                    rs.getInt("horas"),
                    referencedTrabajador,
                    rs.getString("obra_descriptor"),
                    rs.getDate("fecha_pago").toLocalDate(),
                    rs.getFloat("cantidad")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pago> findByTrabajadorDNI(Trabajador trabajador) {
        return null;
    }

    @Override
    public List<Pago> findByObraDescriptor(Obra obra) {
        return null;
    }

    @Override
    public List<Pago> findByDate(LocalDate fechaPago) {
        return null;
    }

    @Override
    public List<Pago> findBetweenDates(LocalDate fechaInicioFilter, LocalDate fechaFinFilter) {
        List<Pago> receivedPayments = new ArrayList<>();

        final String QUERY = "SELECT * FROM pago WHERE pago.fecha_pago >= ? AND" +
                " pago.fecha_pago <= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaInicioFilter));
            stmt.setDate(2, Date.valueOf(fechaFinFilter));

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));
                // Retrieve by column name
                receivedPayments.add(
                        new Pago(
                                rs.getInt("horas"),
                                referencedTrabajador,
                                rs.getString("obra_descriptor"),
                                rs.getDate("fecha_pago").toLocalDate(),
                                rs.getFloat("cantidad")
                        )
                );

            }
            return receivedPayments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pago> findByDateForward(LocalDate fechaInicioFilter) {
        List<Pago> receivedPayments = new ArrayList<>();

        final String QUERY = "SELECT * FROM pago WHERE pago.fecha_pago >= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaInicioFilter));

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));
                // Retrieve by column name
                receivedPayments.add(
                        new Pago(
                                rs.getInt("horas"),
                                referencedTrabajador,
                                rs.getString("obra_descriptor"),
                                rs.getDate("fecha_pago").toLocalDate(),
                                rs.getFloat("cantidad")
                        )
                );

            }
            return receivedPayments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pago> findByDateBackward(LocalDate fechaFinFilter) {
        List<Pago> receivedPayments = new ArrayList<>();

        final String QUERY = "SELECT * FROM pago WHERE pago.fecha_pago <= ?";  // Obras empezadas o finalizadas entre las dos fechas especificadas

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setDate(1, Date.valueOf(fechaFinFilter));

            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {
                Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));
                // Retrieve by column name
                receivedPayments.add(
                        new Pago(
                                rs.getInt("horas"),
                                referencedTrabajador,
                                rs.getString("obra_descriptor"),
                                rs.getDate("fecha_pago").toLocalDate(),
                                rs.getFloat("cantidad")
                        )
                );

            }
            return receivedPayments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pago> findByNameAndDate(String workerName, LocalDate fechaInicioFilter, LocalDate fechaFinFilter) {
        List<Pago> receivedPayments = new ArrayList<>();

        String QUERY =
                "SELECT pago.* FROM pago " +
                        "JOIN trabajador ON trabajador.trabajador_dni=pago.trabajador_dni AND trabajador.nombre LIKE ?";
        if (fechaInicioFilter != null && fechaFinFilter != null) {
            QUERY = QUERY + " WHERE pago.fecha_pago >= ? AND pago.fecha_pago <= ?";
        } else if (fechaInicioFilter != null) {
            QUERY = QUERY + " WHERE pago.fecha_pago >= ? ";
        } else if (fechaFinFilter != null) {
            QUERY = QUERY + " WHERE pago.fecha_pago <= ?";
        }

        // Open a connection
        try (Connection conn = DriverManager.getConnection(JDBCUtils.DB_URL, JDBCUtils.USER, JDBCUtils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {
            stmt.setString(1, "%" + workerName + "%");

            if (fechaInicioFilter != null || fechaFinFilter != null) {
                if (fechaInicioFilter != null && fechaFinFilter != null) {
                    stmt.setDate(2, Date.valueOf(fechaInicioFilter));
                    stmt.setDate(3, Date.valueOf(fechaFinFilter));

                } else stmt.setDate(2, Date.valueOf(Objects.requireNonNullElse(fechaInicioFilter, fechaFinFilter)));
            }
            ResultSet rs = stmt.executeQuery();


            // Extract data from result set
            while (rs.next()) {
                Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));
                // Retrieve by column name
                receivedPayments.add(
                        new Pago(
                                rs.getInt("horas"),
                                referencedTrabajador,
                                rs.getString("obra_descriptor"),
                                rs.getDate("fecha_pago").toLocalDate(),
                                rs.getFloat("cantidad")
                        )
                );

            }
            return receivedPayments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pago update(Pago pago) {
        final String QUERY = "UPDATE pago SET " +
                "cantidad = ?, " +
                "fecha_pago = ?, " +
                "horas = ?, " +
                "trabajador_dni = ?, " +
                "obra_descriptor = ? " +
                "WHERE obra_descriptor = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            Trabajador referencedTrabajador = pago.getTrabajadorPago();

            stmt.setFloat(1, pago.getCantidad());
            stmt.setDate(2, Date.valueOf(pago.getFechaPago()));
            stmt.setInt(3, pago.getHoras());
            stmt.setString(4, referencedTrabajador.getTrabajador_dni());
            stmt.setString(5, pago.getObraDescriptor());
            stmt.setLong(6, pago.getId());

            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("PAGO CON ID " + pago.getId() + " ACTUALIZADA CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();

            return pago;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(long pago_id) {
        final String QUERY = "DELETE FROM pago WHERE pago.pago_id = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setLong(1, pago_id);

            stmt.executeUpdate();

            System.out.println("PAGO CON ID " + pago_id + " ELIMINADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void createSelectedObrasToReportView(List<String> selectedObras) {
//        StringBuilder selectedObrasToQuery = new StringBuilder();
//
//        selectedObrasToQuery.append("pago.obra_descriptor = "+"'").append(selectedObras.get(0)).append("'");
//        for (int i = 1; i < selectedObras.size(); i++)
//            selectedObrasToQuery.append(" OR ").append("pago.obra_descriptor = "+"'").append(selectedObras.get(i)).append("'");
//
//        System.out.println(selectedObrasToQuery);
//        final String QUERY =
//                "CREATE VIEW selected_obras AS " +
//                "SELECT " +
//                "pago.obra_descriptor, " +
//                "pago.trabajador_dni, " +
//                "trabajador.nombre, " +
//                "SUM(pago.horas) AS horas, " +
//                "SUM(pago.cantidad) AS cantidad " +
//                "FROM pago " +
//                "INNER JOIN trabajador ON " +
//                "pago.trabajador_dni = trabajador.trabajador_dni " +
//                "WHERE " + selectedObrasToQuery +
//                " GROUP BY pago.obra_descriptor, pago.trabajador_dni, trabajador.nombre";
//
//        System.out.println(QUERY);
//
//        // Open a connection
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement  stmt = conn.createStatement()) {
//            System.out.println("desde query " + selectedObrasToQuery);
//
//            // stmt.setString(1, selectedObrasToQuery);
//            stmt.executeQuery(QUERY);
//            System.out.println(QUERY);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
