package interfaz;

import mundo.Persona;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelResumenMensual extends JPanel implements ActionListener {
    
    private VentanaPrincipal ventanaPrincipal;
    private JTextArea areaResumen;
    private JButton botonGenerar;
    
    public PanelResumenMensual(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Resumen Mensual"));
        
        botonGenerar = new JButton("Generar Resumen");
        botonGenerar.addActionListener(this);
        add(botonGenerar, BorderLayout.NORTH);
        
        areaResumen = new JTextArea(8, 50);
        areaResumen.setEditable(false);
        areaResumen.setFont(new Font("Arial", Font.PLAIN, 11));
        
        JScrollPane scrollPane = new JScrollPane(areaResumen);
        add(scrollPane, BorderLayout.CENTER);
        
        generarResumen();
    }
    
    public void generarResumen() {
        ArrayList<Persona> empleados = ventanaPrincipal.getPersonal();
        
        if (empleados.isEmpty()) {
            areaResumen.setText("No hay empleados registrados.");
            return;
        }
        
        StringBuilder resumen = new StringBuilder();
        resumen.append("RESUMEN MENSUAL DE PAGOS\n");
        resumen.append("========================\n\n");
        
        double totalSalarioBase = 0;
        double totalHorasExtras = 0;
        double totalDeducciones = 0;
        double totalSalarioNeto = 0;
        
        for (Persona empleado : empleados) {
            totalSalarioBase += empleado.getSalarioBase();
            totalHorasExtras += empleado.getHorasExtras();
            totalDeducciones += empleado.getDeducciones();
            totalSalarioNeto += empleado.calcularSalarioNeto();
        }
        
        resumen.append("Total empleados: " + empleados.size() + "\n");
        resumen.append("Total salarios base: $" + totalSalarioBase + "\n");
        resumen.append("Total horas extras: $" + totalHorasExtras + "\n");
        resumen.append("Total deducciones: $" + totalDeducciones + "\n");
        resumen.append("TOTAL NOMINA: $" + totalSalarioNeto + "\n\n");
        
        resumen.append("DETALLE POR EMPLEADO:\n");
        resumen.append("---------------------\n");
        for (Persona empleado : empleados) {
            resumen.append(empleado.getNombre() + " " + empleado.getApellido() + 
                          " - Salario Neto: $" + empleado.calcularSalarioNeto() + "\n");
        }
        
        areaResumen.setText(resumen.toString());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonGenerar) {
            generarResumen();
        }
    }
}