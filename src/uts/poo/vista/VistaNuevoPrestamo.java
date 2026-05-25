/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package uts.poo.vista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import uts.poo.Libro;
import uts.poo.LibroDAO;
import uts.poo.PrestamoDAO;
import uts.poo.Usuario;
import uts.poo.UsuarioDAO;

/**
 * Vista para crear un nuevo préstamo.
 * @author emilio
 */
public class VistaNuevoPrestamo extends javax.swing.JDialog {

    private final LibroDAO libroDAO;
    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamoDAO;

    public VistaNuevoPrestamo(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        this.libroDAO = new LibroDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.prestamoDAO = new PrestamoDAO();
        initComponents();
        cargarLibros();
        cargarUsuarios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        lblLibro = new javax.swing.JLabel();
        cmbLibro = new javax.swing.JComboBox();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox();
        lblFechaDevolucion = new javax.swing.JLabel();
        txtFechaDevolucion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Préstamo");
        setModal(true);
        setResizable(false);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTitulo.setText("Nuevo Préstamo");

        lblLibro.setText("Libro:");

        lblUsuario.setText("Usuario:");

        lblFechaDevolucion.setText("Fecha devolución (YYYY-MM-DD):");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLibro)
                            .addComponent(cmbLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblFechaDevolucion)
                    .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelTitulo)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLibro)
                    .addComponent(lblUsuario))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblFechaDevolucion)
                .addGap(5, 5, 5)
                .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cargarLibros() {
        cmbLibro.removeAllItems();
        try {
            List<Libro> libros = libroDAO.obtenerDisponibles();
            for (Libro l : libros) {
                cmbLibro.addItem(l);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar libros: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarUsuarios() {
        cmbUsuario.removeAllItems();
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            for (Usuario u : usuarios) {
                cmbUsuario.addItem(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Libro libroSeleccionado = (Libro) cmbLibro.getSelectedItem();
        Usuario usuarioSeleccionado = (Usuario) cmbUsuario.getSelectedItem();

        if (libroSeleccionado == null || usuarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un libro y un usuario.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String fechaDevolucion = txtFechaDevolucion.getText().trim();
        if (fechaDevolucion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la fecha de devolución (YYYY-MM-DD).",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar formato de fecha
        try {
            LocalDate.parse(fechaDevolucion, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String fechaPrestamo = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        try {
            prestamoDAO.crearPrestamo(
                    libroSeleccionado.getId(),
                    usuarioSeleccionado.getId(),
                    fechaPrestamo,
                    fechaDevolucion
            );
            JOptionPane.showMessageDialog(this, "Préstamo registrado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el préstamo: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbLibro;
    private javax.swing.JComboBox cmbUsuario;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel lblFechaDevolucion;
    private javax.swing.JLabel lblLibro;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtFechaDevolucion;
    // End of variables declaration//GEN-END:variables
}