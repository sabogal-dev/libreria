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

/**
 * Vista que muestra la lista de préstamos y permite crear uno nuevo.
 * @author emilio
 */
public class VistaPrestamos extends javax.swing.JDialog {

    private final PrestamoDAO prestamoDAO;

    public VistaPrestamos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.prestamoDAO = new PrestamoDAO();
        initComponents();
        cargarPrestamos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        btnNuevoPrestamo = new javax.swing.JButton();
        jScrollPanePrestamos = new javax.swing.JScrollPane();
        tablaPrestamos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Préstamos");
        setModal(true);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTitulo.setText("Lista de Préstamos");

        btnNuevoPrestamo.setText("Nuevo Préstamo");
        btnNuevoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPrestamoActionPerformed(evt);
            }
        });

        tablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Libro", "Prestado a", "Fecha préstamo", "Fecha devolución", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPanePrestamos.setViewportView(tablaPrestamos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoPrestamo)
                    .addComponent(jScrollPanePrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTitulo))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelTitulo)
                .addGap(8, 8, 8)
                .addComponent(btnNuevoPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPanePrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cargarPrestamos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaPrestamos.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        try {
            List<Object[]> prestamos = prestamoDAO.obtenerTodos();
            for (Object[] p : prestamos) {
                modelo.addRow(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar préstamos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnNuevoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPrestamoActionPerformed
        // Abrir el diálogo de nuevo préstamo
        VistaNuevoPrestamo dialogo = new VistaNuevoPrestamo(this, true);
        dialogo.setVisible(true);
        // Al cerrar el diálogo, refrescar la tabla
        cargarPrestamos();
    }//GEN-LAST:event_btnNuevoPrestamoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevoPrestamo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPanePrestamos;
    private javax.swing.JTable tablaPrestamos;
    // End of variables declaration//GEN-END:variables
}