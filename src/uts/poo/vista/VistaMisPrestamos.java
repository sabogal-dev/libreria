/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package uts.poo.vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uts.poo.PrestamoDAO;
import uts.poo.Usuario;

/**
 * Vista que muestra el historial de préstamos del usuario autenticado,
 * con alertas de préstamos atrasados.
 * @author emilio
 */
public class VistaMisPrestamos extends javax.swing.JDialog {

    private final PrestamoDAO prestamoDAO;
    private final Usuario usuario;

    public VistaMisPrestamos(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        this.prestamoDAO = new PrestamoDAO();
        this.usuario = usuario;
        initComponents();
        setTitle("Mis Préstamos - " + usuario.getNombre());
        cargarMisPrestamos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabelTitulo = new javax.swing.JLabel();
        lblAtrasados = new javax.swing.JLabel();
        txtAtrasados = new javax.swing.JTextField();
        lblActivos = new javax.swing.JLabel();
        txtActivos = new javax.swing.JTextField();
        jScrollPaneMisPrestamos = new javax.swing.JScrollPane();
        tablaMisPrestamos = new javax.swing.JTable();
        btnDevolver = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mis Préstamos");
        setModal(true);
        setResizable(false);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelTitulo.setText("Historial de Préstamos");

        lblAtrasados.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblAtrasados.setForeground(java.awt.Color.RED);
        lblAtrasados.setText("Atrasados:");

        txtAtrasados.setEditable(false);
        txtAtrasados.setForeground(java.awt.Color.RED);
        txtAtrasados.setFont(new java.awt.Font("Tahoma", 1, 12));
        txtAtrasados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAtrasados.setColumns(5);

        lblActivos.setText("Activos:");

        txtActivos.setEditable(false);
        txtActivos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtActivos.setColumns(5);

        tablaMisPrestamos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado", "Atrasado"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
        });
        tablaMisPrestamos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tablaMisPrestamos.getSelectedRow();
                btnDevolver.setEnabled(row >= 0 && "Activo".equals(tablaMisPrestamos.getValueAt(row, 4)));
            }
        });
        jScrollPaneMisPrestamos.setViewportView(tablaMisPrestamos);

        btnDevolver.setText("Devolver Seleccionado");
        btnDevolver.setEnabled(false);
        btnDevolver.addActionListener(e -> {
            int row = tablaMisPrestamos.getSelectedRow();
            if (row >= 0) {
                int prestamoId = (int) tablaMisPrestamos.getValueAt(row, 0);
                devolverPrestamo(prestamoId);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblActivos)
                        .addGap(5, 5, 5)
                        .addComponent(txtActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblAtrasados)
                        .addGap(5, 5, 5)
                        .addComponent(txtAtrasados, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneMisPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDevolver)
                        .addGap(15, 15, 15)
                        .addComponent(btnCerrar)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelTitulo)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActivos)
                    .addComponent(txtActivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAtrasados)
                    .addComponent(txtAtrasados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPaneMisPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDevolver)
                    .addComponent(btnCerrar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Carga los préstamos del usuario en la tabla y actualiza contadores.
     */
    private void cargarMisPrestamos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaMisPrestamos.getModel();
        modelo.setRowCount(0);

        try {
            List<Object[]> prestamos = prestamoDAO.obtenerPorUsuario(usuario.getId());
            int activos = 0;
            int atrasados = 0;

            for (Object[] p : prestamos) {
                String estado = (String) p[4];
                String atrasado = (String) p[5];

                if ("Activo".equals(estado)) activos++;
                if ("Sí".equals(atrasado)) atrasados++;

                modelo.addRow(new Object[]{p[0], p[1], p[2], p[3], p[4], p[5]});
            }

            txtActivos.setText(String.valueOf(activos));
            txtAtrasados.setText(String.valueOf(atrasados));

            // Alerta si hay atrasados
            if (atrasados > 0) {
                JOptionPane.showMessageDialog(this,
                    "¡Atención! Tienes " + atrasados + " préstamo(s) atrasado(s).\n"
                    + "Por favor devuelve los libros cuanto antes.",
                    "Préstamos Atrasados", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar préstamos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Devuelve un préstamo seleccionado.
     */
    private void devolverPrestamo(int prestamoId) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Confirmar devolución del préstamo #" + prestamoId + "?",
            "Confirmar Devolución", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                prestamoDAO.devolver(prestamoId);
                cargarMisPrestamos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al devolver: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPaneMisPrestamos;
    private javax.swing.JLabel lblActivos;
    private javax.swing.JLabel lblAtrasados;
    private javax.swing.JTable tablaMisPrestamos;
    private javax.swing.JTextField txtActivos;
    private javax.swing.JTextField txtAtrasados;
    // End of variables declaration//GEN-END:variables
}