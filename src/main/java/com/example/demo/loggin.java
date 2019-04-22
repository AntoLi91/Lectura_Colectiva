package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.example.model.Usuario;

public class loggin {
		public static boolean intentarLoguearse(HttpSession session, String nombre, String contrasenia) throws SQLException{
			Connection connection;
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics","postgres","5432");
			
			PreparedStatement consulta = 
					connection.prepareStatement("SELECT * FROM usuarios  WHERE nombre = ? AND contrasenia = ?;");

			consulta.setString(1, nombre);
			consulta.setString(2, contrasenia);
			
			ResultSet resultado = consulta.executeQuery();
			
			if ( resultado.next() ) {
				String codigo = UUID.randomUUID().toString();
				session.setAttribute("codigo-autorizacion", codigo);

				consulta =connection.prepareStatement("UPDATE usuarios SET  codigo = ? WHERE nombre = ?");
				consulta.setString(1, codigo);
				consulta.setString(2, nombre);

				consulta.executeUpdate();
				
				return true;
			} else {
				
				return false;
			}
		}
		
		public static Usuario usuarioLogueado(HttpSession session) throws SQLException{
			String codigo = (String)session.getAttribute("codigo-autorizacion");

			if (codigo != null) {
				Connection connection;
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics","postgres","5432");
				
				PreparedStatement consulta = 
						connection.prepareStatement("SELECT * FROM usuarios WHERE codigo = ?;");
				consulta.setString(1, codigo);
				
				ResultSet resultado = consulta.executeQuery();
				
				if ( resultado.next() ) {
					
					Usuario logueado = new Usuario( resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("contrasenia"), false, codigo, codigo);
					
					return logueado;
				} else {
					
					return null;
				}
				
				
			} else {
				
				return null;
			}
		}
		
		public static void cerrarSesion(HttpSession session) throws SQLException{

			String codigo = (String)session.getAttribute("codigo-autorizacion");
			
			session.removeAttribute("codigo-autorizacion");
			
			Connection connection;
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comics","postgres","5432");
			
			PreparedStatement consulta = 
					connection.prepareStatement("UPDATE usuarios SET codigo = null WHERE codigo = ?;");

			consulta.setString(1, codigo);
			
			consulta.executeUpdate();
			connection.close();
			
		}
	}




