package uts.poo.vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uts.poo.Autor;
import uts.poo.AutorDAO;

/**
 * Vista con la tabla de autores y botones CRUD (Nuevo, Editar, Archivar).
 * @author emilio
 */
public class VistaAutores extends javax.swing.JDialog {

    private final AutorDAO autorDAO;
    private int autorSeleccionadoId = -1;

    public VistaAutores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.autorDAO = new AutorDAO();
        initComponents();
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnArchivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Autores");
        setModal(true);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelTitulo.setText("Lista de Autores");

        tabla.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nombre", "Nacionalidad", "Libros"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit[columnIndex]; }
        });
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tabla.getSelectedRow();
                if (row >= 0) {
                    autorSeleccionadoId = (int) tabla.getValueAt(row, 0);
                }
            }
        });
        jScrollPane1.setViewportView(tabla);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(e -> btnNuevoActionPerformed());

        btnEditar.setText("Editar");
        btnEditar.addActionListener(e -> btnEditarActionPerformed());

        btnArchivar.setText("Archivar");
        btnArchivar.addActionListener(e -> btnArchivarActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(10, 10, 10)
                        .addComponent(btnEditar)
                        .addGap(10, 10, 10)
                        .addComponent(btnArchivar)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelTitulo)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnArchivar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        try {
            List<Object[]> autores = autorDAO.obtenerTodosConLibros();
            for (Object[] a : autores) {
                modelo.addRow(a);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar autores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnNuevoActionPerformed() {
        VistaAutorForm dialog = new VistaAutorForm(this, true, null);
        dialog.setVisible(true);
        cargarTabla();
    }

    private void btnEditarActionPerformed() {
        if (autorSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un autor de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            List<Autor> autores = autorDAO.obtenerTodos();
            Autor seleccionado = null;
            for (Autor a : autores) {
                if (a.getId() == autorSeleccionadoId) {
                    seleccionado = a;
                    break;
                }
            }
            if (seleccionado != null) {
                VistaAutorForm dialog = new VistaAutorForm(this, true, seleccionado);
                dialog.setVisible(true);
                cargarTabla();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnArchivarActionPerformed() {
        if (autorSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un autor de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Archivar este autor? También se archivará su historial.", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                autorDAO.archivar(autorSeleccionadoId);
                autorSeleccionadoId = -1;
                cargarTabla();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArchivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
