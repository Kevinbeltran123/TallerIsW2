package interfaz;

import mundo.GestionNomina;
import mundo.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    private GestionNomina mundo;
    private PanelTablaEmpleados panelTablaEmpleados;
    private PanelAddEmpleado panelAddEmpleado;
    public PanelResumenMensual panelResumenMensual;

    public VentanaPrincipal() {
        mundo = new GestionNomina();

        this.setTitle("Sistema de Gestión de Nómina");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelTablaEmpleados = new PanelTablaEmpleados(this);
        contentPanel.add(panelTablaEmpleados, BorderLayout.CENTER);

        panelAddEmpleado = new PanelAddEmpleado(this);
        contentPanel.add(panelAddEmpleado, BorderLayout.NORTH);

        panelResumenMensual = new PanelResumenMensual(this);
        contentPanel.add(panelResumenMensual, BorderLayout.SOUTH);

        this.setContentPane(contentPanel);

        panelTablaEmpleados.updateTable();

        this.setVisible(true);
    }

    public ArrayList<Persona> getPersonal() {
        return mundo.getPersonal();
    }

    public void addEmpleado(Persona persona) {
        mundo.addPersona(persona);
        panelTablaEmpleados.updateTable();
        panelResumenMensual.generarResumen();
    }

    public void deleteEmpleado(Persona persona) {
        mundo.removePersona(persona);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}
