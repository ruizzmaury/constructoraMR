package com.example.constructora.JDBCRepository;

import com.example.constructora.Utils;
import com.example.constructora.domain.Obra;
import com.example.constructora.domain.Pago;
import com.example.constructora.domain.Trabajador;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        System.out.println("DNI trabajador registro: " + trabajador_dni);
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setLong(1, pago.getId());
            stmt.setFloat(2, pago.getCantidad());
            stmt.setDate(3, Date.valueOf(pago.getFechaPago()));
            stmt.setInt(4, pago.getHoras());
            stmt.setLong(5, pago.getIdObra());
            stmt.setString(6, trabajador_dni);
            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("PAGO CON ID " + pago.getId() + " CREADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

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
                System.out.println(rs.getString("trabajador_dni"));
                Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));
                // Retrieve by column name
                receivedPagos.add(
                        new Pago(
                                rs.getInt("horas"),
                                referencedTrabajador,
                                rs.getLong("obra_id"),
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
            System.out.println("ID: " +  rs.getLong("pago_id"));
            System.out.println("--------------------------------------------------");

            System.out.println("GET PAGO SUCCESSFUL... ");
            System.out.println("--------------------------------------------------");
            Trabajador referencedTrabajador = trabajadorServiceJDBC.getTrabajador(rs.getString("trabajador_dni"));

            return new Pago(
                    rs.getInt("horas"),
                    referencedTrabajador,
                    rs.getLong("obra_id"),
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
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
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
                                rs.getLong("obra_id"),
                                rs.getDate("fecha_pago").toLocalDate(),
                                rs.getFloat("cantidad")
                        )
                );
                System.out.println("--------------------------------------------------");

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
                "obra_id = ? " +
                "WHERE obra_id = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            Trabajador referencedTrabajador = pago.getTrabajadorPago();

            stmt.setFloat(1, pago.getCantidad());
            stmt.setDate(2, Date.valueOf(pago.getFechaPago()));
            stmt.setInt(3, pago.getHoras());
            stmt.setString(4, referencedTrabajador.getTrabajador_dni());
            stmt.setLong(5, pago.getIdObra());
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
}
