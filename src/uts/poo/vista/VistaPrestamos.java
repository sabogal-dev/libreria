package uts.poo.vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uts.poo.PrestamoDAO;

/**
 * Vista que muestra la lista de préstamos con campos de detalle y botones fuera de la tabla.
 * @author emilio
 */
public class VistaPrestamos extends javax.swing.JDialog {

    private final PrestamoDAO prestamoDAO;
    private int prestamoSeleccionadoId = -1;

    public VistaPrestamos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.prestamoDAO = new PrestamoDAO();
        initComponents();
        cargarPrestamos();
        limpiarCamposDetalle();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabelTitulo = new javax.swing.JLabel();
        btnNuevoPrestamo = new javax.swing.JButton();
        jScrollPanePrestamos = new javax.swing.JScrollPane();
        tablaPrestamos = new javax.swing.JTable();
        lblDetalle = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblLibro = new javax.swing.JLabel();
        txtLibro = new javax.swing.JTextField();
        lblPrestadoA = new javax.swing.JLabel();
        txtPrestadoA = new javax.swing.JTextField();
        lblFechaPrestamo = new javax.swing.JLabel();
        txtFechaPrestamo = new javax.swing.JTextField();
        lblFechaDevolucion = new javax.swing.JLabel();
        txtFechaDevolucion = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        btnDevolver = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Préstamos");
        setModal(true);
        setResizable(false);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelTitulo.setText("Lista de Préstamos");

        btnNuevoPrestamo.setText("Nuevo Préstamo");
        btnNuevoPrestamo.addActionListener(evt -> btnNuevoPrestamoActionPerformed());

        tablaPrestamos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Libro", "Prestado a", "Fecha Préstamo", "Fecha Devolución", "Estado"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
        });
        tablaPrestamos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tablaPrestamos.getSelectedRow();
                if (row >= 0) {
                    prestamoSeleccionadoId = (int) tablaPrestamos.getValueAt(row, 0);
                    mostrarDetalle(row);
                } else {
                    prestamoSeleccionadoId = -1;
                    limpiarCamposDetalle();
                }
            }
        });
        jScrollPanePrestamos.setViewportView(tablaPrestamos);

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDetalle.setText("Detalle del préstamo seleccionado:");

        lblId.setText("ID:");
        txtId.setEditable(false);
        txtId.setColumns(5);

        lblLibro.setText("Libro:");
        txtLibro.setEditable(false);
        txtLibro.setColumns(20);

        lblPrestadoA.setText("Prestado a:");
        txtPrestadoA.setEditable(false);
        txtPrestadoA.setColumns(20);

        lblFechaPrestamo.setText("Fecha préstamo:");
        txtFechaPrestamo.setEditable(false);
        txtFechaPrestamo.setColumns(12);

        lblFechaDevolucion.setText("Fecha devolución:");
        txtFechaDevolucion.setEditable(false);
        txtFechaDevolucion.setColumns(12);

        lblEstado.setText("Estado:");
        txtEstado.setEditable(false);
        txtEstado.setColumns(10);

        btnDevolver.setText("Devolver");
        btnDevolver.addActionListener(e -> {
            if (prestamoSeleccionadoId > 0) {
                devolverPrestamo(prestamoSeleccionadoId);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un préstamo de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCancelar.setText("Cancelar Préstamo");
        btnCancelar.addActionListener(e -> {
            if (prestamoSeleccionadoId > 0) {
                cancelarPrestamo(prestamoSeleccionadoId);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un préstamo de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addComponent(btnNuevoPrestamo)
                    .addComponent(jScrollPanePrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDetalle)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLibro)
                            .addComponent(txtLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrestadoA)
                            .addComponent(txtPrestadoA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaPrestamo)
                            .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaDevolucion)
                            .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstado)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDevolver)
                        .addGap(15, 15, 15)
                        .addComponent(btnCancelar)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelTitulo)
                .addGap(8, 8, 8)
                .addComponent(btnNuevoPrestamo)
                .addGap(10, 10, 10)
                .addComponent(jScrollPanePrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblDetalle)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(lblLibro)
                    .addComponent(lblPrestadoA)
                    .addComponent(lblFechaPrestamo)
                    .addComponent(lblFechaDevolucion)
                    .addComponent(lblEstado))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrestadoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDevolver)
                    .addComponent(btnCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Muestra los datos del préstamo seleccionado en los campos de texto.
     */
    private void mostrarDetalle(int row) {
        txtId.setText(String.valueOf(tablaPrestamos.getValueAt(row, 0)));
        txtLibro.setText(String.valueOf(tablaPrestamos.getValueAt(row, 1)));
        txtPrestadoA.setText(String.valueOf(tablaPrestamos.getValueAt(row, 2)));
        txtFechaPrestamo.setText(String.valueOf(tablaPrestamos.getValueAt(row, 3)));
        txtFechaDevolucion.setText(String.valueOf(tablaPrestamos.getValueAt(row, 4)));
        txtEstado.setText(String.valueOf(tablaPrestamos.getValueAt(row, 5)));
    }

    /**
     * Limpia los campos de detalle.
     */
    private void limpiarCamposDetalle() {
        txtId.setText("");
        txtLibro.setText("");
        txtPrestadoA.setText("");
        txtFechaPrestamo.setText("");
        txtFechaDevolucion.setText("");
        txtEstado.setText("");
    }

    private void cargarPrestamos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaPrestamos.getModel();
        modelo.setRowCount(0);
        try {
            List<Object[]> prestamos = prestamoDAO.obtenerTodos();
            for (Object[] p : prestamos) {
                modelo.addRow(new Object[]{
                    p[0], p[1], p[2], p[3], p[4], p[5]
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar préstamos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        limpiarCamposDetalle();
    }

    private void btnNuevoPrestamoActionPerformed() {
        VistaNuevoPrestamo dialogo = new VistaNuevoPrestamo(this, true);
        dialogo.setVisible(true);
        cargarPrestamos();
    }

    /**
     * Ejecuta la acción de devolver un préstamo.
     */
    private void devolverPrestamo(int prestamoId) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmar devolución del préstamo #" + prestamoId + "?",
                "Confirmar Devolución", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                prestamoDAO.devolver(prestamoId);
                cargarPrestamos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al devolver: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Ejecuta la acción de cancelar un préstamo.
     */
    private void cancelarPrestamo(int prestamoId) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmar cancelación del préstamo #" + prestamoId + "?",
                "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                prestamoDAO.cancelar(prestamoId);
                cargarPrestamos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cancelar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnNuevoPrestamo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPanePrestamos;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaDevolucion;
    private javax.swing.JLabel lblFechaPrestamo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLibro;
    private javax.swing.JLabel lblPrestadoA;
    private javax.swing.JTable tablaPrestamos;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFechaDevolucion;
    private javax.swing.JTextField txtFechaPrestamo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLibro;
    private javax.swing.JTextField txtPrestadoA;
    // End of variables declaration//GEN-END:variables
}