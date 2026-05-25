package uts.poo.vista;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uts.poo.Libro;
import uts.poo.LibroDAO;

/**
 * Vista con la tabla de libros y botones CRUD (Nuevo, Editar, Archivar).
 * @author emilio
 */
public class VistaLibros extends javax.swing.JDialog {

    private final LibroDAO libroDAO;
    private int libroSeleccionadoId = -1;

    public VistaLibros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.libroDAO = new LibroDAO();
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
        setTitle("Gestión de Libros");
        setModal(true);

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelTitulo.setText("Lista de Libros");

        tabla.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Título", "Género", "Calificación", "Estado", "Autor ID"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit[columnIndex]; }
        });
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tabla.getSelectedRow();
                if (row >= 0) {
                    libroSeleccionadoId = (int) tabla.getValueAt(row, 0);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            List<Libro> libros = libroDAO.obtenerTodos();
            for (Libro l : libros) {
                modelo.addRow(new Object[]{
                    l.getId(), l.getTitulo(), l.getGenero(),
                    l.getCalificacion(), l.getEstado(), l.getAutorId()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnNuevoActionPerformed() {
        VistaLibroForm dialog = new VistaLibroForm(this, true, null);
        dialog.setVisible(true);
        cargarTabla();
    }

    private void btnEditarActionPerformed() {
        if (libroSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            List<Libro> libros = libroDAO.obtenerTodos();
            Libro seleccionado = null;
            for (Libro l : libros) {
                if (l.getId() == libroSeleccionadoId) {
                    seleccionado = l;
                    break;
                }
            }
            if (seleccionado != null) {
                VistaLibroForm dialog = new VistaLibroForm(this, true, seleccionado);
                dialog.setVisible(true);
                cargarTabla();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnArchivarActionPerformed() {
        if (libroSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Archivar este libro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                libroDAO.archivar(libroSeleccionadoId);
                libroSeleccionadoId = -1;
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
