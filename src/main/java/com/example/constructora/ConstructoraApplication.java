package com.example.constructora;

import com.example.constructora.JDBCRepository.PagosServiceImplJDBC;
import com.example.constructora.JDBCRepository.PagosServiceJDBC;
import com.example.constructora.JDBCRepository.utilsJDBC.JDBCUtils;
import com.example.constructora.view.MainMenu;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class ConstructoraApplication {



	public static void main(String[] args)  {


		SpringApplication.run(ConstructoraApplication.class, args);
		System.setProperty("java.awt.headless", "false");
		MainMenu m = new MainMenu();



	}

//	public static Trabajador getTrabajador(long id) {
//
//		final String QUERY = "SELECT * FROM Trabajador WHERE Trabajador.trabajador_id= ?";
//		final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
//		final String USER = "constructora";
//		final String PASS = "constructora";
//
//
//		// Open a connection
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			 PreparedStatement stmt = conn.prepareStatement(QUERY)) {
//			stmt.setLong(1, id);
//
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			System.out.println("TRABAJADOR: ");
//			System.out.println("ID: " + rs.getLong("trabajador_id"));
//			System.out.println("Nombre: " + rs.getString("nombre"));
//			System.out.println("Email: " + rs.getString("email"));
//			System.out.println("Telefono: " + rs.getInt("telefono"));
//			System.out.println("Categoria Laboral: " + CategoriaLaboral.values()[rs.getInt("cat_laboral")]);
//			System.out.println("Género: " + Genero.values()[rs.getInt("genero")]);
//			System.out.println("Fecha Nacimiento: " + rs.getDate("fecha_nacimiento").toLocalDate());
//			System.out.println("Direccion: " +  rs.getString("direccion"));
//
//			System.out.println("GUARDADO CON ÉXITO... ");
//			System.out.println("--------------------------------------------------");
//
//			return new Trabajador(
//					rs.getLong("trabajador_id"),
//					rs.getString("nombre"),
//					Genero.values()[rs.getInt("genero")],
//					rs.getInt("telefono"),
//					rs.getString("email"),
//					rs.getDate("fecha_nacimiento").toLocalDate(),
//					rs.getString("direccion"),
//					CategoriaLaboral.values()[rs.getInt("cat_laboral")]
//			);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//
//	public static List<Trabajador> showTrabajadores() {
//		List<Trabajador> receivedTrabajadores = new ArrayList<>();
//
//		final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
//		final String USER = "constructora";
//		final String PASS = "constructora";
//		final String QUERY = "SELECT trabajador_id, nombre, telefono, email, direccion," +
//				" cat_laboral, genero, fecha_nacimiento FROM Trabajador";
//		System.out.println("KFUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//		// Open a connection
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			 Statement stmt = conn.createStatement();
//			 ResultSet rs = stmt.executeQuery(QUERY);) {
//			System.out.println("TAMOS DENTROOOOOOOOOO");
//
//			int i = 0;
//			// Extract data from result set
//			while (rs.next()) {
//
//				// Retrieve by column name
//				receivedTrabajadores.add(
//						new Trabajador(
//								rs.getLong("trabajador_id"),
//								rs.getString("nombre"),
//								Genero.values()[rs.getInt("genero")],
//								rs.getInt("telefono"),
//								rs.getString("email"),
//								rs.getDate("fecha_nacimiento").toLocalDate(),
//								rs.getString("direccion"),
//								CategoriaLaboral.values()[rs.getInt("cat_laboral")]
//						));
//
//				System.out.println("TRABAJADOR: ");
//				System.out.println(receivedTrabajadores.get(i).getId());
//				System.out.println(receivedTrabajadores.get(i).getNombre());
//				System.out.println(receivedTrabajadores.get(i).getEmail());
//				System.out.println(receivedTrabajadores.get(i).getTelefono());
//				System.out.println(receivedTrabajadores.get(i).getCatLaboral());
//				System.out.println(receivedTrabajadores.get(i).getGenero());
//				System.out.println(receivedTrabajadores.get(i).getFechaNacimiento());
//				System.out.println("GUARDADO CON ÉXITO... ");
//				i++;
////                System.out.print("ID: " + rs.getLong("trabajador_id"));
////                System.out.print(", nombre: " + rs.getString("nombre"));
////                System.out.print(", telefono: " + rs.getInt("telefono"));
////              System.out.println(", Last: " + rs.getString("last"));
//			}
//			return receivedTrabajadores;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	public static List<Trabajador> findByNombreStartingWith(String nombre) {
//		List<Trabajador> receivedTrabajadores = new ArrayList<>();
//		final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
//		final String USER = "constructora";
//		final String PASS = "constructora";
//		final String QUERY = "SELECT * FROM Trabajador WHERE Trabajador.nombre LIKE ?";
//		System.out.println("TRABAJADORES EMPEZADOS POR " + nombre);
//		// Open a connection
//		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			 PreparedStatement stmt = conn.prepareStatement(QUERY)) {
//
//			stmt.setString(1, "%" + nombre + "%" );
//			ResultSet rs = stmt.executeQuery();
//			int i = 0;
//			// Extract data from result set
//			while (rs.next()) {
//
//				// Retrieve by column name
//				receivedTrabajadores.add(
//						new Trabajador(
//								rs.getLong("trabajador_id"),
//								rs.getString("nombre"),
//								Genero.values()[rs.getInt("genero")],
//								rs.getInt("telefono"),
//								rs.getString("email"),
//								rs.getDate("fecha_nacimiento").toLocalDate(),
//								rs.getString("direccion"),
//								CategoriaLaboral.values()[rs.getInt("cat_laboral")]
//						));
//
//				System.out.println("TRABAJADOR: ");
//				System.out.println("ID: " + receivedTrabajadores.get(i).getId());
//				System.out.println("Nombre: " + receivedTrabajadores.get(i).getNombre());
//				System.out.println("Email: " + receivedTrabajadores.get(i).getEmail());
//				System.out.println("Telefono: " + receivedTrabajadores.get(i).getTelefono());
//				System.out.println("Categoria Laboral: " + receivedTrabajadores.get(i).getCatLaboral());
//				System.out.println("Género: " + receivedTrabajadores.get(i).getGenero());
//				System.out.println("Fecha Nacimiento: " + receivedTrabajadores.get(i).getFechaNacimiento());
//				System.out.println("GUARDADO CON ÉXITO... ");
//				System.out.println("--------------------------------------------------");
//				i++;
//
//			}
//			return receivedTrabajadores;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}