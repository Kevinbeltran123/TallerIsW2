package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mundo.Persona;

public class PanelTablaEmpleados extends JPanel {
    private DefaultTableModel model;
    private JTable tablaEmpleados;
    private VentanaPrincipal principal;

    public PanelTablaEmpleados(VentanaPrincipal principal) {
        this.principal = principal;

        model = new DefaultTableModel(new Object[]{
            "Cédula", "Nombre", "Apellido", "Cargo", "Salario Base", 
            "Horas Extras", "Deducciones", "Salario Neto", "Email", "Teléfono", "Acciones"
        }, 0);
        tablaEmpleados = new JTable(model);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.setAutoCreateRowSorter(true);
        tablaEmpleados.setRowSelectionAllowed(true);

        TableColumnModel columnModel = tablaEmpleados.getColumnModel();
        columnModel.getColumn(10).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(10).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scroll = new JScrollPane(tablaEmpleados);
        this.setLayout(new BorderLayout());
        this.add(scroll, BorderLayout.CENTER);
    }

    public void updateTable() {
        model.setRowCount(0);
        for (Persona p : principal.getPersonal()) {
            model.addRow(new Object[]{
                    p.getCedula(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getCargo(),
                    String.format("%.2f", p.getSalarioBase()),
                    String.format("%.2f", p.getHorasExtras()),
                    String.format("%.2f", p.getDeducciones()),
                    String.format("%.2f", p.calcularSalarioNeto()),
                    p.getEmail(),
                    p.getTelefono(),
                    "Eliminar"
            });
        }
    }

    public void deleteEmpleado(Persona p) {
        principal.deleteEmpleado(p);
        updateTable();
        principal.panelResumenMensual.generarResumen();
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    Persona p = principal.getPersonal().get(row);
                    deleteEmpleado(p);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
