/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Abogado;
import Vista.frmAbogado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Contr
 */
public class CtrlAbogado implements MouseListener, KeyListener, ActionListener {

    private Abogado Modelo;
    private frmAbogado Vista;

    //2- Crear el constructor
    public CtrlAbogado(Abogado Modelo, frmAbogado Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;

        this.Vista.btnAgregar.addActionListener(this);
        Modelo.Mostrar(Vista.jtbAbogado);
        this.Vista.btnEliminar.addActionListener(this);
        Vista.jtbAbogado.addMouseListener(this);
        this.Vista.btnActualizar.addActionListener(this);
        this.Vista.btnLimpiarCampos.addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.jtbAbogado) {
            Modelo.cargarDatosTabla(Vista);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Vista.btnAgregar) {

            String nombre = Vista.txtNombre.getText();
            String correo = Vista.txtCorreo.getText();
            int edad;
            double peso;

            // Validaciones
            if (Vista.txtNombre.getText().isEmpty() || Vista.txtEdad.getText().isEmpty()
                    || Vista.txtPeso.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return; // Detenemos la ejecución si faltan datos
            }
            // Validación de que el nombre solo contenga texto
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                JOptionPane.showMessageDialog(Vista, "El nombre solo puede contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validación de correo
            if (!Vista.txtCorreo.getText().contains("@")) {
                JOptionPane.showMessageDialog(Vista, "El correo electrónico debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación de edad
            try {
                edad = Integer.parseInt(Vista.txtEdad.getText());
                if (edad <= 0) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Vista, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación de peso
            try {
                peso = Double.parseDouble(Vista.txtPeso.getText());
                if (peso <= 0) {
                    JOptionPane.showMessageDialog(Vista, "El peso debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Vista, "El peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Si las validaciones son correctas, guardar los datos
            Modelo.setNombre_Abogado(nombre);
            Modelo.setEdad_Abogado(edad);
            Modelo.setPeso_Abogado(peso);
            Modelo.setCorreo_Abogado(correo);
            Modelo.Guardar();
            Modelo.Mostrar(Vista.jtbAbogado);
        }

        if (e.getSource() == Vista.btnEliminar) {
            Modelo.Eliminar(Vista.jtbAbogado);
            Modelo.Mostrar(Vista.jtbAbogado);
        }

        if (e.getSource() == Vista.btnActualizar) {
            String nombre = Vista.txtNombre.getText();
            String correo = Vista.txtCorreo.getText();
            int edad;
            double peso;

            // Validaciones
            if (Vista.txtNombre.getText().isEmpty() || Vista.txtEdad.getText().isEmpty()
                    || Vista.txtPeso.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return; // Detenemos la ejecución si faltan datos
            }
            // Validación de que el nombre solo contenga texto
            if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(Vista, "El correo electrónico debe ser válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validación de correo
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                JOptionPane.showMessageDialog(Vista, "El nombre solo puede contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación de edad
            try {
                edad = Integer.parseInt(Vista.txtEdad.getText());
                if (edad <= 0) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Vista, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validación de peso
            try {
                peso = Double.parseDouble(Vista.txtPeso.getText());
                if (peso <= 0) {
                    JOptionPane.showMessageDialog(Vista, "El peso debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Vista, "El peso debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Si las validaciones son correctas, actualizar los datos
            Modelo.setNombre_Abogado(nombre);
            Modelo.setEdad_Abogado(edad);
            Modelo.setPeso_Abogado(peso);
            Modelo.setCorreo_Abogado(correo);
            Modelo.Actualizar(Vista.jtbAbogado);
            Modelo.Mostrar(Vista.jtbAbogado);
        }

        if (e.getSource() == Vista.btnLimpiarCampos) {
            Modelo.limpiar(Vista);
        }
    }

}
