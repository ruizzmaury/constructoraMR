package com.example.constructora.JDBCRepository;


import com.example.constructora.Utils;
import com.example.constructora.domain.CategoriaLaboral;
import com.example.constructora.domain.Genero;
import com.example.constructora.domain.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorServiceImplJDBC implements TrabajadorServiceJDBC {

    private final CatLaboralServiceJDBC catLaboralServiceJDBC = new CatLaboralServiceImplJDBC();

    @Override
    public Trabajador create(Trabajador trabajador) {
        final String QUERY = "INSERT INTO trabajador VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?)";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
//            stmt.setLong(1, trabajador.getId());
            stmt.setString(1, trabajador.getTrabajador_dni());
            stmt.setString(2, trabajador.getDireccion());
            stmt.setString(3, trabajador.getEmail());
            stmt.setDate(4, Date.valueOf(trabajador.getFechaNacimiento()));
            stmt.setString(5, trabajador.getNombre());
            stmt.setInt(6, trabajador.getTelefono());
            stmt.setString(7, trabajador.getCatLaboral().getNombreCategoria());
            stmt.setString(8, trabajador.getGenero().getNombreGenero());


            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("TRABAJADOR CON DNI " + trabajador.getTrabajador_dni() + " CREADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();
            return trabajador;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Trabajador> getTrabajadores() {
        List<Trabajador> receivedTrabajadores = new ArrayList<>();

        final String QUERY = "SELECT * FROM Trabajador";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedTrabajadores.add(
                        new Trabajador(
//                                rs.getLong("trabajador_id"),
                                rs.getString("trabajador_dni"),
                                rs.getString("nombre"),
                                new Genero(rs.getString("genero")),
                                rs.getInt("telefono"),
                                rs.getString("email"),
                                rs.getDate("fecha_nacimiento").toLocalDate(),
                                rs.getString("direccion"),
                                new CategoriaLaboral(
                                        rs.getString("cat_laboral"),
                                        catLaboralServiceJDBC.getPrecioHora((rs.getString("cat_laboral")))
                                )
                        ));

            }
            return receivedTrabajadores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getTrabajadoresDNIs() {
        List<String> receivedTrabajadoresDNIs = new ArrayList<>();

        final String QUERY = "SELECT trabajador.trabajador_dni FROM trabajador";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedTrabajadoresDNIs.add(
                        rs.getString("trabajador_dni")
                );

            }
            return receivedTrabajadoresDNIs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getTrabajadoresNames() {
        List<String> receivedTrabajadoresDNIs = new ArrayList<>();

        final String QUERY = "SELECT trabajador.nombre FROM trabajador";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {

            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                receivedTrabajadoresDNIs.add(
                        rs.getString("nombre")
                );
                i++;
            }
            return receivedTrabajadoresDNIs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Trabajador getTrabajador(String DNI) {

        final String QUERY = "SELECT * FROM trabajador WHERE trabajador.trabajador_dni= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, DNI);

            ResultSet rs = stmt.executeQuery();
            rs.next();


            return new Trabajador(
//                    rs.getLong("trabajador_id"),
                    rs.getString("trabajador_dni"),
                    rs.getString("nombre"),
                    new Genero(rs.getString("genero")),
                    rs.getInt("telefono"),
                    rs.getString("email"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getString("direccion"),
                    new CategoriaLaboral(
                            rs.getString("cat_laboral"),
                            catLaboralServiceJDBC.getPrecioHora((rs.getString("cat_laboral")))
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getCategoriaLaboral(String DNI) {
        final String QUERY = "SELECT trabajador.cat_laboral FROM trabajador WHERE trabajador.trabajador_dni= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, DNI);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return rs.getString("cat_laboral");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Trabajador findByDNI(String DNI) {
        final String QUERY = "SELECT * FROM trabajador WHERE trabajador.trabajador_dni= ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, DNI);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Trabajador(
//                    rs.getLong("trabajador_id"),
                    rs.getString("trabajador_dni"),
                    rs.getString("nombre"),
                    new Genero(rs.getString("genero")),
                    rs.getInt("telefono"),
                    rs.getString("email"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getString("direccion"),
                    new CategoriaLaboral(
                            rs.getString("cat_laboral"),
                            catLaboralServiceJDBC.getPrecioHora((rs.getString("cat_laboral")))
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Trabajador> findByNombreStartingWith(String nombre) {
        List<Trabajador> receivedTrabajadores = new ArrayList<>();

        final String QUERY = "SELECT * FROM Trabajador WHERE Trabajador.nombre LIKE ?";
        System.out.println("TRABAJADORES EMPEZADOS POR " + nombre);
        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY);
        ) {

            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            // Extract data from result set
            while (rs.next()) {

                // Retrieve by column name
                receivedTrabajadores.add(
                        new Trabajador(
//                                rs.getLong("trabajador_id"),
                                rs.getString("trabajador_dni"),
                                rs.getString("nombre"),
                                new Genero(rs.getString("genero")),
                                rs.getInt("telefono"),
                                rs.getString("email"),
                                rs.getDate("fecha_nacimiento").toLocalDate(),
                                rs.getString("direccion"),
                                new CategoriaLaboral(
                                        rs.getString("cat_laboral"),
                                        catLaboralServiceJDBC.getPrecioHora((rs.getString("cat_laboral")))
                                )
                        ));


            }
            return receivedTrabajadores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Trabajador> findByEmailStartingWith(String email) {
        return null;
    }

    @Override
    public Trabajador update(Trabajador trabajador) {
        final String QUERY = "UPDATE trabajador SET " +
                "trabajador_dni = ?, " +
                "nombre = ?, " +
                "telefono = ?, " +
                "email = ?, " +
                "direccion = ?, " +
                "cat_laboral = ?, " +
                "genero = ?, " +
                "fecha_nacimiento = ? " +
                "WHERE trabajador_dni = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, trabajador.getTrabajador_dni());
            stmt.setString(2, trabajador.getNombre());
            stmt.setInt(3, trabajador.getTelefono());
            stmt.setString(4, trabajador.getEmail());
            stmt.setString(5, trabajador.getDireccion());
            stmt.setString(6, trabajador.getCatLaboral().getNombreCategoria());
            stmt.setString(7, trabajador.getGenero().getNombreGenero());
            stmt.setDate(8, Date.valueOf(trabajador.getFechaNacimiento()));
            stmt.setString(9, trabajador.getTrabajador_dni());

            int rowAffected = stmt.executeUpdate();

            System.out.println(String.format("Row affected %d", rowAffected));
            System.out.println("TRABAJADOR CON DNI " + trabajador.getTrabajador_dni() + " ACTUALIZADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

            stmt.close();
            return trabajador;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void delete(String DNI) {
        final String QUERY = "DELETE FROM Trabajador WHERE Trabajador.trabajador_dni = ?";

        // Open a connection
        try (Connection conn = DriverManager.getConnection(Utils.DB_URL, Utils.USER, Utils.PASS);
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
            stmt.setString(1, DNI);

            stmt.executeUpdate();

            System.out.println("TRABAJADOR CON DNI " + DNI + " ELIMINADO CON ÉXITO... ");
            System.out.println("--------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
