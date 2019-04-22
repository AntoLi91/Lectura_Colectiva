package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Comics;
import com.example.model.Usuario;

@Controller
public class ComicsController<Usuarios> {

	@GetMapping("/")

	public String Bienvenido() {
		return "IndexInvitado";
	}

	@GetMapping("/formulario")
	public String formulario() {
		return "formulario";
	}
	
	
	@GetMapping("/loginUsuarioCreado")
	public String loginusuariocreado() {
		return "loginUsuarioCreado";
	}

	@GetMapping("/registro")
	public String registro() {
		return "registro";
	}

	@GetMapping("/registroUsuario")
	public String registrousuario() {
		return "registroUsuario";
	}

	@GetMapping("/IndexAdmin")
	public String indexadmin() {
		return "IndexAdmin";

	}

	@GetMapping("/IndexInvitado")
	public String indexinvitado() {
		return "IndexInvitado";
	}

	@GetMapping("/loginUsuario")
	public String loginusario() {
		return "loginUsuario";
	}

	@GetMapping("/IndexUsuario")
	public String IndexUsuario() {
		return "IndexUsuario";
	}

	@GetMapping("/registroAdmin")
	public String registroadmin() {
		return "registroAdmin";
	}

	@GetMapping("/insertar-loginUsuario")
	public String insertarlogin() {
		return "insertar-loginUsuario";

	}

	@GetMapping("/eliminarcomics/{codigo}")
	public String eliminar(@PathVariable int codigo) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("DELETE FROM comics WHERE codigo = ?;");
		consulta.setInt(1, codigo);

		consulta.executeUpdate();

		connection.close();

		return "redirect:/listadoComicsAdmin";
	}

	@GetMapping("/Index")
	public String Index(Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Comics> listadoComics = new ArrayList<Comics>();

		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nombre = resultado.getString("nombre");
			String autor = resultado.getString("autor");
			String editorial = resultado.getString("editorial");
			String resumen= resultado.getString("resumen");
			String genero= resultado.getString("genero");

			Comics x = new Comics(codigo, nombre, editorial, autor,resumen,genero);
			listadoComics.add(x);
		}

		template.addAttribute("listadoComics", listadoComics);

		return "Index";
	}

	@GetMapping("/listadoComicsAdmin")
	public String listadoComicsAdmin(Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Comics> listadoComicsAdmin = new ArrayList<Comics>();

		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nombre = resultado.getString("nombre");
			String autor = resultado.getString("autor");
			String editorial = resultado.getString("editorial");
			String resumen = resultado.getString("resumen");
			String genero = resultado.getString("genero");
			
			Comics x = new Comics(codigo, nombre, autor, editorial,resumen,genero);
			listadoComicsAdmin.add(x);
		}

		return "listadoComicsAdmin";
	}

	@GetMapping("/listadoComicsUsuario")
	public String listadoComicsUsuario(Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Comics> listadoComicsUsuario = new ArrayList<Comics>();

		while (resultado.next()) {
			int codigo = resultado.getInt("codigo");
			String nombre = resultado.getString("nombre");
			String autor = resultado.getString("autor");
			String editorial = resultado.getString("editorial");
			String resumen= resultado.getString("resumen");
			String genero = resultado.getString("genero");

			Comics x = new Comics(codigo, nombre, autor, editorial,resumen,genero);
			listadoComicsUsuario.add(x);
		}

		return "listadoComicsUsuario";
	}
	@GetMapping("/listadeusuarios")
	public String listadeusuarios(Model template) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM usuarios;");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Usuario> listadeusuarios = new ArrayList<Usuario>();

		while (resultado.next()) {
			int id= resultado.getInt("id");
			String nombre = resultado.getString("nombre");
			String contrasenia= resultado.getString("contrasenia");
			Boolean admin= resultado.getBoolean("admin");
			String email=resultado.getString("email");
			String apellido= resultado.getString("apellido");
			
			Usuario x = new Usuario(id, nombre, contrasenia, admin, email, apellido);
			listadeusuarios.add(x);
		}

		return "listadeusuarios";
	}

	

	
	
	@GetMapping("/procesarBusquedaUsuario")
	public String procesarBusquedaUsuario(Model template, @RequestParam String palabraBuscada) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics WHERE nombre LIKE ?;");
		consulta.setString(1, "%" + palabraBuscada + "%");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Comics> listadoComicsUsuario = new ArrayList<Comics>();

		while (resultado.next()) {
			String nombre = resultado.getString("nombre");
			String editorial = resultado.getString("editorial");
			String autor = resultado.getString("autor");
			int codigo = resultado.getInt("codigo");
			String resumen= resultado.getString("resumen");
			String genero= resultado.getString("genero");

			Comics x = new Comics(codigo, nombre, editorial, autor,resumen,genero);
			listadoComicsUsuario.add(x);
		}

		template.addAttribute("listadoComicsUsuario", listadoComicsUsuario);

		return "listadoComicsUsuario";
	}

	@GetMapping("/procesarBusquedaAdmin")
	public String procesarBusquedaAdmin(Model template, @RequestParam String palabraBuscada) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics WHERE nombre LIKE ?;");
		consulta.setString(1, "%" + palabraBuscada + "%");

		ResultSet resultado = consulta.executeQuery();

		ArrayList<Comics> listadoComics = new ArrayList<Comics>();

		while (resultado.next()) {
			String nombre = resultado.getString("nombre");
			String editorial = resultado.getString("editorial");
			String autor = resultado.getString("autor");
			int codigo = resultado.getInt("codigo");
			String resumen= resultado.getString("resumen");
			String genero= resultado.getString("genero");

			Comics x = new Comics(codigo, nombre, editorial, autor,resumen,genero);
			listadoComics.add(x);
		}

		template.addAttribute("listadoComicsAdmin", listadoComics);

		return "listadoComicsAdmin";
	}

	@GetMapping("/detalleComicsUsuario/{codigo}")
	public String detalleComicsUsuario(Model template, @PathVariable int codigo) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics WHERE codigo = ?;");

		consulta.setInt(1, codigo);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
			String nombre = resultado.getString("nombre");
			String editorial = resultado.getString("editorial");
			String autor = resultado.getString("autor");
			String resumen = resultado.getString("resumen");

			template.addAttribute("nombre", nombre);
			template.addAttribute("editorial", editorial);
			template.addAttribute("autor", autor);
			template.addAttribute("resumen", resumen);

		}

		return "detalleComicsUsuario";
	}

	@GetMapping("/detalleComicsAdmin/{codigo}")
	public String detalleComicsAdmin(Model template, @PathVariable int codigo) throws SQLException {

		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM comics WHERE codigo = ?;");

		consulta.setInt(1, codigo);

		ResultSet resultado = consulta.executeQuery();

		if (resultado.next()) {
			String nombre = resultado.getString("nombre");
			String editorial = resultado.getString("editorial");
			String autor = resultado.getString("autor");
			String resumen = resultado.getString("resumen");

			template.addAttribute("nombre", nombre);
			template.addAttribute("editorial", editorial);
			template.addAttribute("autor", autor);
			template.addAttribute("resumen",resumen);

		}

		return "detalleComicsAdmin";
	}

	@PostMapping("/insertar-usuario")
	public String insertarUsuario(@RequestParam String nombre, @RequestParam String contrasenia, @RequestParam String email, @RequestParam String apellido) throws

	SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres",

				"5432");

		PreparedStatement consulta = connection
				.prepareStatement("INSERT INTO usuarios(nombre, contrasenia, email, apellido) VALUES(?,?,?,?);");
		consulta.setString(1, nombre);
		consulta.setString(2, contrasenia);
		consulta.setString(3, email);
		consulta.setString(4, apellido);
		consulta.executeUpdate();
		return "IndexUsuario";
	}

	@PostMapping("/insertar-comicsUsuario")
	public String insertarComics(@RequestParam String nombre, @RequestParam String editorial,
			@RequestParam String autor, @RequestParam String resumen, @RequestParam String genero) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection
				.prepareStatement("INSERT INTO comics(nombre, editorial, autor, resumen, genero ) VALUES(?, ?, ?, ?, ?);");
		consulta.setString(1, nombre);
		consulta.setString(2, editorial);
		consulta.setString(3, autor);
		consulta.setString(4, resumen);
		consulta.setString(5, genero);
		consulta.executeUpdate();
		return "listadoComicsUsuario";

	}

	@PostMapping("/insertar-comicsAdmin")
	public String insertarComicsAdmin(@RequestParam String nombre, @RequestParam String editorial,
			@RequestParam String autor, @RequestParam String resumen, @RequestParam String genero) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics", "postgres", "5432");

		PreparedStatement consulta = connection
				.prepareStatement("INSERT INTO comics(nombre, editorial, autor, resumen, genero ) VALUES(?, ?, ?, ?, ?);");
		consulta.setString(1, nombre);
		consulta.setString(2, editorial);
		consulta.setString(3, autor);
		consulta.setString(4, resumen);
		consulta.setString(5, genero);
		consulta.executeUpdate();
		return "redirect:/listadoComicsAdmin";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/procesar-loginAdmin")
	public String procesarLogin(HttpSession session, Model template, @RequestParam String nombre,
			@RequestParam String contrasenia) throws SQLException {
		boolean sePudo = loggin.intentarLoguearse(session, nombre, contrasenia);

		if (sePudo) {

			return "IndexAdmin";
		} else {
			

			return "loginfallido";
		}
	}

	@PostMapping("/procesar-loginUsuario")
	public String procesarLoginUsuario(HttpSession session, Model template, @RequestParam String nombre,
			@RequestParam String contrasenia) throws SQLException {
		boolean sePudo = loggin.intentarLoguearse(session, nombre, contrasenia);

		if (sePudo) {

			return "redirect:/IndexUsuario";
		} else {
			

			return "loginUsuariofallido";
		}
	}

	@PostMapping("/procesar-loginUsuarioCreado")
	public String procesarLoginUsuarioCreado(HttpSession session, Model template, @RequestParam String nombre,
			@RequestParam String contrasenia) throws SQLException {
		boolean sePudo = loggin.intentarLoguearse(session, nombre, contrasenia);

		if (sePudo) {

			return "redirect:/IndexUsuario";
		} else {
			

			return "loginUsuarioCreadofallido";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) throws SQLException {
		loggin.cerrarSesion(session);
		return "redirect:/login";
	}

}
