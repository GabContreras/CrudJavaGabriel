/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.frmAbogado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Contr
 */
public class Abogado {
    //parámetros, getter y setters

    private String UUID_Abogado;
    private String Nombre_Abogado;
    private int Edad_Abogado;
    private double Peso_Abogado;
    private String Correo_Abogado;

    public String getUUID_Abogado() {
        return UUID_Abogado;
    }

    public void setUUID_Abogado(String UUID_Abogado) {
        this.UUID_Abogado = UUID_Abogado;
    }

    public String getNombre_Abogado() {
        return Nombre_Abogado;
    }

    public void setNombre_Abogado(String Nombre_Abogado) {
        this.Nombre_Abogado = Nombre_Abogado;
    }

    public int getEdad_Abogado() {
        return Edad_Abogado;
    }

    public void setEdad_Abogado(int Edad_Abogado) {
        this.Edad_Abogado = Edad_Abogado;
    }

    public double getPeso_Abogado() {
        return Peso_Abogado;
    }

    public void setPeso_Abogado(double Peso_Abogado) {
        this.Peso_Abogado = Peso_Abogado;
    }

    public String getCorreo_Abogado() {
        return Correo_Abogado;
    }

    public void setCorreo_Abogado(String Correo_Abogado) {
        this.Correo_Abogado = Correo_Abogado;
    }

    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addAbogado = conexion.prepareStatement("INSERT INTO tbAbogado(UUID_Abogado, Nombre_Abogado, Edad_Abogado, Peso_Abogado, Correo_Abogado) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addAbogado.setString(1, UUID.randomUUID().toString());
            addAbogado.setString(2, getNombre_Abogado());
            addAbogado.setInt(3, getEdad_Abogado());
            addAbogado.setDouble(4, getPeso_Abogado());
            addAbogado.setString(5, getCorreo_Abogado());

            //Ejecutar la consulta
            addAbogado.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Abogado", "Nombre_Abogado", "Edad_Abogado", "Peso_Abogado", "Correo_Abogado"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbAbogado");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Abogado"), rs.getString("Nombre_Abogado"),
                    rs.getString("Edad_Abogado"),
                    rs.getInt("Peso_Abogado"),
                    rs.getString("Correo_Abogado")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String uuidAbogado = tabla.getValueAt(filaSeleccionada, 0).toString();

        //borramos 
        try {
            PreparedStatement deleteAbogado = conexion.prepareStatement("delete from tbAbogado where UUID_Abogado = ?");
            deleteAbogado.setString(1, uuidAbogado);
            deleteAbogado.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

    public void cargarDatosTabla(frmAbogado Vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = Vista.jtbAbogado.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = Vista.jtbAbogado.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = Vista.jtbAbogado.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = Vista.jtbAbogado.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTb = Vista.jtbAbogado.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTb = Vista.jtbAbogado.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            Vista.txtNombre.setText(NombreDeTB);
            Vista.txtEdad.setText(EdadDeTb);
            Vista.txtPeso.setText(PesoDeTb);
            Vista.txtCorreo.setText(CorreoDeTb);
        }
    }

    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbAbogado set Nombre_Abogado= ?, Edad_Abogado = ?, Peso_Abogado = ?, Correo_Abogado = ? where UUID_Abogado = ?");
                updateUser.setString(1, getNombre_Abogado());
                updateUser.setInt(2, getEdad_Abogado());
                updateUser.setDouble(3, getPeso_Abogado());
                updateUser.setString(4, getCorreo_Abogado());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    public void limpiar(frmAbogado Vista) {
        Vista.txtNombre.setText("");
        Vista.txtEdad.setText("");
        Vista.txtPeso.setText("");
        Vista.txtCorreo.setText("");
    }

}
