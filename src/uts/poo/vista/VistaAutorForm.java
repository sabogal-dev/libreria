package uts.poo.vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import uts.poo.Autor;
import uts.poo.AutorDAO;

/**
 * Formulario para crear o editar un autor.
 * @author emilio
 */
public class VistaAutorForm extends javax.swing.JDialog {

    private final AutorDAO autorDAO;
    private final Autor autorEditar;

    public VistaAutorForm(java.awt.Dialog parent, boolean modal, Autor autorExistente) {
        super(parent, modal);
        this.autorDAO = new AutorDAO();
        this.autorEditar = autorExistente;

        initComponents();
        if (autorEditar != null) {
            txtNombre.setText(autorEditar.getNombre());
            txtNacionalidad.setText(autorEditar.getNacionalidad());
            setTitle("Editar Autor");
        } else {
            setTitle("Nuevo Autor");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNacionalidad = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        lblNombre.setText("Nombre:");
        txtNombre.setColumns(25);

        lblNacionalidad.setText("Nacionalidad:");
        txtNacionalidad.setColumns(25);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(e -> btnGuardarActionPerformed());

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblNacionalidad))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(txtNacionalidad))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnGuardar)
                .addGap(20, 20, 20)
                .addComponent(btnCancelar)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNacionalidad)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed() {
        String nombre = txtNombre.getText().trim();
        String nacionalidad = txtNacionalidad.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (autorEditar == null) {
                // Nuevo
                Autor a = new Autor();
                a.setNombre(nombre);
                a.setNacionalidad(nacionalidad);
                autorDAO.crear(a);
            } else {
                // Editar
                autorEditar.setNombre(nombre);
                autorEditar.setNacionalidad(nacionalidad);
                autorDAO.actualizar(autorEditar);
            }
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
