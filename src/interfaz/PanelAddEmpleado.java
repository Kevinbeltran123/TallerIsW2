package interfaz;

import mundo.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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
    
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

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

        add(new JLabel("Nombre: *"));
        add(nombre);

        add(new JLabel("Apellido: *"));
        add(apellido);

        add(new JLabel("Email: *"));
        add(email);

        add(new JLabel("Teléfono:"));
        add(telefono);

        add(new JLabel("Cédula: *"));
        add(cedula);

        add(new JLabel("Cargo: *"));
        add(cargo);

        add(new JLabel("Salario Base: *"));
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
        String errores = validarCamposObligatorios();
        
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(this, errores, "Errores de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double salarioBaseVal = Double.parseDouble(salarioBase.getText());
            double horasExtrasVal = horasExtras.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(horasExtras.getText());
            double deduccionesVal = deducciones.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(deducciones.getText());
            
            if (salarioBaseVal < 0 || horasExtrasVal < 0 || deduccionesVal < 0) {
                JOptionPane.showMessageDialog(this, "Los valores monetarios no pueden ser negativos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Persona persona = new Persona(
                nombre.getText().trim(),
                apellido.getText().trim(), 
                cedula.getText().trim(),
                cargo.getText().trim(),
                email.getText().trim(),
                telefono.getText().trim(),
                salarioBaseVal,
                horasExtrasVal,
                deduccionesVal
            );
            
            ventanaPrincipal.addEmpleado(persona);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Valores numéricos inválidos. Por favor ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    private String validarCamposObligatorios() {
        StringBuilder errores = new StringBuilder();
        
        if (nombre.getText().trim().isEmpty()) {
            errores.append("- El nombre es obligatorio\n");
        }
        
        if (apellido.getText().trim().isEmpty()) {
            errores.append("- El apellido es obligatorio\n");
        }
        
        if (cedula.getText().trim().isEmpty()) {
            errores.append("- La cédula es obligatoria\n");
        }
        
        if (cargo.getText().trim().isEmpty()) {
            errores.append("- El cargo es obligatorio\n");
        }
        
        if (email.getText().trim().isEmpty()) {
            errores.append("- El email es obligatorio\n");
        } else if (!isValidEmail(email.getText().trim())) {
            errores.append("- El email no tiene un formato válido\n");
            errores.append("  Ejemplo correcto: usuario@dominio.com\n");
        }
        
        if (salarioBase.getText().trim().isEmpty()) {
            errores.append("- El salario base es obligatorio\n");
        }
        
        return errores.toString();
    }
    
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return emailPattern.matcher(email.trim()).matches();
    }
}
