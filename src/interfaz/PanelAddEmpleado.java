package interfaz;

import mundo.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAddEmpleado extends JPanel implements ActionListener {

    VentanaPrincipal ventanaPrincipal;

    JTextField nombre;
    JTextField apellido;
    JTextField cedula;
    JTextField cargo;
    JTextField email;
    JTextField telefono;
    JTextField salarioBase;
    JTextField horasExtras;
    JTextField deducciones;
    
    JButton botonGuardar;
    JButton botonCancelar;

    public PanelAddEmpleado(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new GridLayout(5, 2, 10, 5));
        nombre = new JTextField();
        apellido = new JTextField();
        cedula = new JTextField();
        cargo = new JTextField();
        email = new JTextField();
        telefono = new JTextField();
        salarioBase = new JTextField();
        horasExtras = new JTextField();
        deducciones = new JTextField();

        add(new JLabel("Nombre:"));
        add(nombre);

        add(new JLabel("Apellido:"));
        add(apellido);

        add(new JLabel("Cédula:"));
        add(cedula);

        add(new JLabel("Cargo:"));
        add(cargo);

        add(new JLabel("Email:"));
        add(email);

        add(new JLabel("Teléfono:"));
        add(telefono);

        add(new JLabel("Salario Base:"));
        add(salarioBase);

        add(new JLabel("Horas Extras:"));
        add(horasExtras);

        add(new JLabel("Deducciones:"));
        add(deducciones);

        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(this);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);

        add(botonGuardar);
        add(botonCancelar);
    }

    public void addEmpleado() {
        try {
            double salarioBaseVal = Double.parseDouble(salarioBase.getText());
            double horasExtrasVal = Double.parseDouble(horasExtras.getText());
            double deduccionesVal = Double.parseDouble(deducciones.getText());
            
            Persona persona = new Persona(
                nombre.getText(),
                apellido.getText(), 
                cedula.getText(),
                cargo.getText(),
                email.getText(),
                telefono.getText(),
                salarioBaseVal,
                horasExtrasVal,
                deduccionesVal
            );
            
            ventanaPrincipal.addEmpleado(persona);
            limpiarFormulario();
            
        } catch (NumberFormatException e) {
            System.out.println("Error: valores numéricos inválidos");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGuardar) {
            addEmpleado();
        } else if (e.getSource() == botonCancelar) {
            limpiarFormulario();
        }
    }
    
    private void limpiarFormulario() {
        nombre.setText("");
        apellido.setText("");
        cedula.setText("");
        cargo.setText("");
        email.setText("");
        telefono.setText("");
        salarioBase.setText("");
        horasExtras.setText("");
        deducciones.setText("");
    }
}
