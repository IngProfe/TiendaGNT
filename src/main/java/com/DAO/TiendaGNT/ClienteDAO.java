package com.DAO.TiendaGNT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.BO.TiendaGNT.*;
import com.DTO.TiendaGNT.ClienteVO;
import com.DTO.TiendaGNT.Conexion;


/**
 * Clase que permite el acceso a la base de datos
 * 
 *
 */
public class ClienteDAO 
{

 /**
  * Permite registrar un Cliente nuevo
  * @param persona
  */
	
	
 public void registrarPersona(ClienteVO persona) 
 {
  Conexion conex= new Conexion();
  try {
   Statement estatuto = conex.getConnection().createStatement();
   estatuto.executeUpdate("INSERT INTO T_Clientes VALUES ('"+persona.getIdcli()+"', '"
     +persona.getNombre()+"', '"+persona.getApellido()+"')");
   JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
   estatuto.close();
   conex.desconectar();
   
  } catch (SQLException e) {
            System.out.println(e.getMessage());
   JOptionPane.showMessageDialog(null, "No se Registro la persona");
  }
 }
   
 
 
/**
 * permite consultar el Cliente asociado al documento enviado
 * como parametro 
 * @param documento 
 * @return
 */
public ArrayList<ClienteVO> consultarPersona(int documento) {
  ArrayList< ClienteVO> miCliente = new ArrayList< ClienteVO>();
  Conexion conex= new Conexion();
    
  try {
   PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM T_Clientes where id_Cli = ? ");
   consulta.setInt(1, documento);
   ResultSet res = consulta.executeQuery();
   
  if(res.next()){
    ClienteVO persona= new ClienteVO();
    persona.setIdcli(Integer.parseInt(res.getString("id_Cli")));
    persona.setNombre(res.getString("Nombre"));
    persona.setApellido(res.getString("Apellido"));
 
    miCliente.add(persona);
          }
          res.close();
          consulta.close();
          conex.desconectar();
   
  } catch (Exception e) {
   JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
  }
  return miCliente;
 }



/**
 * permite consultar la lista de Clientes
 * @return
 */
public ArrayList< ClienteVO> listaDePersonas() {
  ArrayList< ClienteVO> miCliente = new ArrayList< ClienteVO>();
  Conexion conex= new Conexion();
    
  try {
   PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM T_Clientes");
   ResultSet res = consulta.executeQuery();
   while(res.next()){
    ClienteVO persona= new ClienteVO();
    persona.setIdcli(Integer.parseInt(res.getString("id_Cli")));
    persona.setNombre(res.getString("Nombre"));
    persona.setApellido(res.getString("Apellido"));
  
    miCliente.add(persona);
          }
          res.close();
          consulta.close();
          conex.desconectar();
   
  } catch (Exception e) {
   JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
  }
  return miCliente;
 }

}
